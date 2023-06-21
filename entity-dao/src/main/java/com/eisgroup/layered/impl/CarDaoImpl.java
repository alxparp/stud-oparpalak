package com.eisgroup.layered.impl;

import com.eisgroup.layered.entity.Car;
import com.eisgroup.layered.interfaces.CarDao;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.List;

public class CarDaoImpl implements CarDao {

    private static final Logger LOGGER = Logger.getLogger(CarDaoImpl.class);

    private EntityManager em;

    public CarDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Car> findAll() {
        List<Car> car = em.createNamedQuery("Car.getAll", Car.class).getResultList();
        LOGGER.info("Found " + car.size() + " car objects");
        return car;
    }

    @Override
    public Car getById(int carID) {
        Car car = em.find(Car.class, carID);
        LOGGER.info("Found car with id " + carID);
        return car;
    }

    @Override
    public void save(Car car) {
        if(car == null) {
            LOGGER.error("Attempt to save null car");
            throw new IllegalArgumentException("Can't save null car");
        }
        em.getTransaction().begin();
        em.merge(car);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Car car) {
        if(car == null) {
            LOGGER.error("Attempt to delete null car");
            throw new IllegalArgumentException("Can't delete null car");
        }
        em.getTransaction().begin();
        em.remove(car);
        em.getTransaction().commit();
        LOGGER.info("Car with id " + car.getId() + " was deleted");
    }
}
