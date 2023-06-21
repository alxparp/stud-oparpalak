package com.eisgroup.layered.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CARGO")
@PrimaryKeyJoinColumn(referencedColumnName="ID")
@NamedQueries(value = {@NamedQuery(name = "Cargo.getAll", query = "SELECT c FROM Cargo c")})
public class Cargo extends Transport implements Serializable {

    @Column(name = "BODY_TYPE")
    private String bodyType;

    @OneToOne
    @JoinColumn(name = "ID")
    private Transport transport;

    public Cargo(String name, int price, boolean availability, Producer producer, String bodyType) {
        super(name, price, availability, producer);
        this.bodyType = bodyType;
    }

    public Cargo() {
        super();
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
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

        Cargo cargo = (Cargo) o;

        if (!bodyType.equals(cargo.bodyType)) return false;
        return transport.equals(cargo.transport);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + bodyType.hashCode();
        result = 31 * result + transport.hashCode();
        return result;
    }
}
