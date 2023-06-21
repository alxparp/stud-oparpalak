package com.eisgroup.layered.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CARS")
@PrimaryKeyJoinColumn(referencedColumnName="ID")
@NamedQueries(value = {@NamedQuery(name = "Car.getAll", query = "SELECT c FROM Car c")})
public class Car extends Transport implements Serializable {

    @Column(name = "MILEAGE_KM")
    private int mileageKm;

    @Column(name = "ENGINE_CAPACITY")
    private int engineCapacity;

    @OneToOne
    @JoinColumn(name = "ID")
    private Transport transport;

    public Car(String name, int price, boolean availability, Producer producer, int mileageKm, int engineCapacity) {
        super(name, price, availability, producer);
        this.mileageKm = mileageKm;
        this.engineCapacity = engineCapacity;
    }

    public Car() {
        super();
    }

    public int getMileageKm() {
        return mileageKm;
    }

    public void setMileageKm(int mileageKm) {
        this.mileageKm = mileageKm;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
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

        Car car = (Car) o;

        if (mileageKm != car.mileageKm) return false;
        if (engineCapacity != car.engineCapacity) return false;
        return transport.equals(car.transport);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + mileageKm;
        result = 31 * result + engineCapacity;
        result = 31 * result + transport.hashCode();
        return result;
    }
}
