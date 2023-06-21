package com.eisgroup.layered.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MOTORCYCLES")
@PrimaryKeyJoinColumn(referencedColumnName="ID")
@NamedQueries(value = {@NamedQuery(name = "Motorcycle.getAll", query = "SELECT m FROM Motorcycle m")})
public class Motorcycle extends Transport implements Serializable {

    @Column(name = "ISSUE_YEAR")
    private int issueYear;

    @OneToOne
    @JoinColumn(name = "ID")
    private Transport transport;

    public Motorcycle(String name, int price, boolean availability, Producer producer, int issueYear) {
        super(name, price, availability, producer);
        this.issueYear = issueYear;
    }

    public Motorcycle() {
        super();
    }

    public int getIssueYear() {
        return issueYear;
    }

    public void setIssueYear(int issueYear) {
        this.issueYear = issueYear;
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

        Motorcycle that = (Motorcycle) o;

        if (issueYear != that.issueYear) return false;
        return transport.equals(that.transport);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + issueYear;
        result = 31 * result + transport.hashCode();
        return result;
    }
}
