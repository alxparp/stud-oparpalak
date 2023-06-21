package com.eisgroup.layered.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PRODUCERS")
@NamedQueries(value = {@NamedQuery(name = "Producer.getAll", query = "SELECT p FROM Producer p")})
public class Producer implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="producers_seq")
    @SequenceGenerator(name="producers_seq", sequenceName="PRODUCERS_SEQ")
    private long id;

    @Column(name = "NAME")
    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Producer producer = (Producer) o;

        return name.equals(producer.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
