package com.eisgroup.layered.impl;

import com.eisgroup.layered.entity.Motorcycle;
import com.eisgroup.layered.entity.Sensor;
import com.eisgroup.layered.interfaces.SensorDao;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.List;

public class SensorDaoImpl implements SensorDao {

    private static final Logger LOGGER = Logger.getLogger(MotorcycleDaoImpl.class);

    private EntityManager em;

    public SensorDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Sensor> findAll() {
        List<Sensor> sensors = em.createNamedQuery("Sensor.getAll", Sensor.class).getResultList();
        LOGGER.info("Found " + sensors.size() + " sensor objects");
        return sensors;
    }

    @Override
    public Sensor getById(int sensorID) {
        Sensor sensor = em.find(Sensor.class, sensorID);
        LOGGER.info("Found sensor with id " + sensorID);
        return sensor;
    }

    @Override
    public void save(Sensor sensor) {
        if(sensor == null) {
            LOGGER.error("Attempt to save null sensor");
            throw new IllegalArgumentException("Can't save null sensor");
        }
        em.getTransaction().begin();
        em.merge(sensor);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Sensor sensor) {
        if(sensor == null) {
            LOGGER.error("Attempt to delete null sensor");
            throw new IllegalArgumentException("Can't delete null sensor");
        }
        em.getTransaction().begin();
        em.remove(sensor);
        em.getTransaction().commit();
        LOGGER.info("Sensor with id " + sensor.getId() + " was deleted");
    }
}
