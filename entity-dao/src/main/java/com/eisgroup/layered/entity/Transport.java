package com.eisgroup.layered.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "TRANSPORTS")
@Inheritance( strategy = InheritanceType.JOINED )
@NamedQueries(value = {@NamedQuery(name = "Transport.getAll", query = "SELECT t FROM Transport t")})
public class Transport implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="transports_seq")
    @SequenceGenerator(name="transports_seq", sequenceName="TRANSPORTS_SEQ")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "AVAILABILITY")
    private boolean availability;

    @ManyToOne(cascade = CascadeType.ALL)
    private Producer producer;

    @ManyToMany(mappedBy = "transports")
    private Set<Sensor> sensors;

    public Transport(String name, int price, boolean availability, Producer producer) {
        this.name = name;
        this.price = price;
        this.availability = availability;
        this.producer = producer;
    }

    public Transport(){}

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Set getSensors() {
        return sensors;
    }

    public void setSensors(Set sensor) {
        this.sensors = sensors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transport transport = (Transport) o;

        if (price != transport.price) return false;
        if (availability != transport.availability) return false;
        if (!name.equals(transport.name)) return false;
        return producer.equals(transport.producer);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + price;
        result = 31 * result + (availability ? 1 : 0);
        result = 31 * result + producer.hashCode();
        return result;
    }
}
