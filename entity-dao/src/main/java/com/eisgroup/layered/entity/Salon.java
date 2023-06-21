package com.eisgroup.layered.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "SALONS")
@NamedQueries(value = {@NamedQuery(name = "Salon.getAll", query = "SELECT s FROM Salon s")})
public class Salon implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="salons_seq")
    @SequenceGenerator(name="salons_seq", sequenceName="SALONS_SEQ")
    private int id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "transport_id")
    private List<Transport> transports;

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

    public List<Transport> getTransports() {
        return transports;
    }

    public void setTransports(List<Transport> transports) {
        this.transports = transports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Salon salon = (Salon) o;

        if (!name.equals(salon.name)) return false;
        return transports.equals(salon.transports);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + transports.hashCode();
        return result;
    }
}
