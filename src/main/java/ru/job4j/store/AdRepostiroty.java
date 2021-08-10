package ru.job4j.store;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import ru.job4j.model.Car;
import ru.job4j.model.CarBody;
import ru.job4j.model.CarMark;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class AdRepostiroty {
    private static final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static final class Holder {
        private static final AdRepostiroty INSTANCE = new AdRepostiroty();
    }

    public static AdRepostiroty instOf() {
        return Holder.INSTANCE;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void saveCarAd(Car car) {
        this.tx(
                session -> session.save(car)
        );
    }

    public List<Car> findByMark(String markName) {
        return this.tx(
                session -> {
                    Query query = session.createQuery(
                            "select distinct c from Car c "
                                    + "join fetch c.mark cm "
                                    + "join fetch c.body cb "
                                    + "join fetch c.user u "
                                    + "where cm.name = :mName", Car.class);
                    query.setParameter("mName", markName);
                    return query.list();
                }
        );
    }

    public List<Car> findWithPhoto() {
        return this.tx(
                session -> {
                    Query query = session.createQuery(
                            "select distinct c from Car c "
                                    + "join fetch c.mark cm "
                                    + "join fetch c.body cb "
                                    + "join fetch c.user u "
                                    + "where c.photoStatus = true", Car.class);
                    return query.list();
                }
        );
    }

    public List<Car> findSinceLastDay() {
        return this.tx(
                session -> session.createCriteria(Car.class)
                        .add(Restrictions.gt(
                                "created",
                                new Date(System.currentTimeMillis() - MILLIS_PER_DAY)
                        )).list()
        );
    }

    public CarMark findMarkByName(String carMark) {
        return (CarMark) this.tx(session -> {
            final javax.persistence.Query query = session.createQuery("from CarMark where name=:carMark");
            query.setParameter("carMark", carMark);
            return query.getSingleResult();
        });
    }

    public CarBody findBodyByName(String carBody) {
        return (CarBody) this.tx(session -> {
            final javax.persistence.Query query = session.createQuery("from CarBody where name=:carBody");
            query.setParameter("carBody", carBody);
            return query.getSingleResult();
        });
    }

    public void changeStatusToSold(int id) {
        this.tx(session -> session.createQuery("update Car "
                + "SET status = true "
                + " where id = " + id)
                .executeUpdate());
    }

    public void changePhotoStatus(int id) {
        this.tx(session -> session.createQuery("update Car "
                + "SET photoStatus = true "
                + " where id = " + id)
                .executeUpdate());
    }

    public List<CarBody> getAllCarBodies() {
        return tx(session ->
                session.createQuery("from ru.job4j.model.CarBody").list());
    }

    public List<CarMark> getAllCarMarks() {
        return tx(session ->
                session.createQuery("from ru.job4j.model.CarMark").list());
    }

    public List<Car> getAllCars() {
        return this.tx(
                session -> {
                    Query query = session.createQuery(
                            "select distinct c from Car c "
                                    + "join fetch c.mark cm "
                                    + "join fetch c.body cb "
                                    + "join fetch c.user u ", Car.class);
                    return query.list();
                }
        );
    }

    public int findIdLastCar() {
        return this.tx(session -> {
            final javax.persistence.Query query = session.createQuery("from Car order by id desc");
            return query.getFirstResult();
        });
    }
}
