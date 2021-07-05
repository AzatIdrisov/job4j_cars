package ru.job4j.model;

import javax.persistence.*;

@Entity
@Table(name = "car_body")
public class CarBody {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, updatable = false)
    private String name;

    public CarBody() {
    }

    public CarBody(String name) {
        this.name = name;
    }

    public CarBody(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
