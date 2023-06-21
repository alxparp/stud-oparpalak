package com.eisgroup.layered.impl;

import com.eisgroup.layered.entity.Bus;
import com.eisgroup.layered.interfaces.BusDao;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.List;

public class BusDaoImpl implements BusDao {

    private static final Logger LOGGER = Logger.getLogger(BusDaoImpl.class);

    private EntityManager em;

    public BusDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Bus> findAll() {
        List<Bus> buses = em.createNamedQuery("Bus.getAll", Bus.class).getResultList();
        LOGGER.info("Found " + buses.size() + " bus objects");
        return buses;
    }

    @Override
    public Bus getById(int busID) {
        Bus bus = em.find(Bus.class, busID);
        LOGGER.info("Found bus with id " + busID);
        return bus;
    }

    @Override
    public void save(Bus bus) {
        if(bus == null) {
            LOGGER.error("Attempt to save null bus");
            throw new IllegalArgumentException("Can't save null bus");
        }
        em.getTransaction().begin();
        em.merge(bus);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Bus bus) {
        if(bus == null) {
            LOGGER.error("Attempt to delete null bus");
            throw new IllegalArgumentException("Can't delete null bus");
        }
        em.getTransaction().begin();
        em.remove(bus);
        em.getTransaction().commit();
        LOGGER.info("Bus with id " + bus.getId() + " was deleted");
    }
}
