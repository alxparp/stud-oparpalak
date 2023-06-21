package com.eisgroup.layered.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "SENSORS")
@NamedQueries(value = {@NamedQuery(name = "Sensor.getAll", query = "SELECT s FROM Sensor s")})
public class Sensor implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sensors_seq")
    @SequenceGenerator(name="sensors_seq", sequenceName="SENSORS_SEQ")
    private int id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "SENSOR_TRANSPORT",
            joinColumns = @JoinColumn(name = "SENSOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "TRANSPORT_ID")
    )
    private Set<Transport> transports;

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

    public Set<Transport> getTransports() {
        return transports;
    }

    public void setTransports(Set<Transport> transports) {
        this.transports = transports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sensor sensor = (Sensor) o;

        if (!name.equals(sensor.name)) return false;
        return transports.equals(sensor.transports);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + transports.hashCode();
        return result;
    }
}
