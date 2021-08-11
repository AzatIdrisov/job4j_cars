package ru.job4j.store;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.User;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class UserStore {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static final class Holder {
        private static final UserStore INSTANCE = new UserStore();
    }

    public static UserStore instOf() {
        return UserStore.Holder.INSTANCE;
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

    public void addUser(User user) {
        this.tx(
                session -> session.save(user)
        );
    }

    public User findUserById(int id) {
        return this.tx(
                session -> session.get(User.class, id)
        );
    }

    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        return this.tx(session -> {
            final Query query = session.createQuery("from User where email=:email and password=:password");
            query.setParameter("email", email);
            query.setParameter("password", password);
            User result = (User) query.uniqueResult();
            return Optional.ofNullable(result);
        });
    }

    public List<User> findUserByEmail(String email) {
        return this.tx(session -> {
            final Query query = session.createQuery("from User where email=:email");
            query.setParameter("email", email);
            return query.getResultList();
        });
    }
}
