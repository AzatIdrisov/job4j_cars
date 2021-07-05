package ru.job4j.model;


import javax.persistence.*;

@Entity
@Table(name = "car_mark")
public class CarMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, updatable = false)
    private String name;

    public CarMark() {
    }

    public CarMark(String name) {
        this.name = name;
    }

    public CarMark(int id) {
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
