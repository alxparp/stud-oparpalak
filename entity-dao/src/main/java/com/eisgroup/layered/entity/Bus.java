package com.eisgroup.layered.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BUSES")
@PrimaryKeyJoinColumn(referencedColumnName="ID")
@NamedQueries(value = {@NamedQuery(name = "Bus.getAll", query = "SELECT b FROM Bus b")})
public class Bus extends Transport implements Serializable {

    @Column(name = "SEATS_NUMBER")
    private int seatsNumber;

    @OneToOne
    @JoinColumn(name = "ID")
    private Transport transport;

    public Bus(String name, int price, boolean availability, Producer producer, int seatsNumber) {
        super(name, price, availability, producer);
        this.seatsNumber = seatsNumber;
    }

    public Bus(Transport transport, int seatsNumber) {
        this(transport.getName(), transport.getPrice(), transport.isAvailability(), transport.getProducer(), seatsNumber);
    }

    public Bus() {
        super();
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Bus bus = (Bus) o;

        if (seatsNumber != bus.seatsNumber) return false;
        return transport.equals(bus.transport);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + seatsNumber;
        result = 31 * result + transport.hashCode();
        return result;
    }
}
