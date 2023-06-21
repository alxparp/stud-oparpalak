package com.eisgroup.layered.impl;

import com.eisgroup.layered.entity.Motorcycle;
import com.eisgroup.layered.interfaces.MotorcycleDao;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.List;

public class MotorcycleDaoImpl implements MotorcycleDao {

    private static final Logger LOGGER = Logger.getLogger(MotorcycleDaoImpl.class);

    private EntityManager em;

    public MotorcycleDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Motorcycle> findAll() {
        List<Motorcycle> motorcycles = em.createNamedQuery("Motorcycle.getAll", Motorcycle.class).getResultList();
        LOGGER.info("Found " + motorcycles.size() + " motorcycle objects");
        return motorcycles;
    }

    @Override
    public Motorcycle getById(int motorcycleID) {
        Motorcycle motorcycle = em.find(Motorcycle.class, motorcycleID);
        LOGGER.info("Found motorcycle with id " + motorcycleID);
        return motorcycle;
    }

    @Override
    public void save(Motorcycle motorcycle) {
        if(motorcycle == null) {
            LOGGER.error("Attempt to save null motorcycle");
            throw new IllegalArgumentException("Can't save null motorcycle");
        }
        em.getTransaction().begin();
        em.merge(motorcycle);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Motorcycle motorcycle) {
        if(motorcycle == null) {
            LOGGER.error("Attempt to delete null motorcycle");
            throw new IllegalArgumentException("Can't delete null motorcycle");
        }
        em.getTransaction().begin();
        em.remove(motorcycle);
        em.getTransaction().commit();
        LOGGER.info("Motorcycle with id " + motorcycle.getId() + " was deleted");
    }
}
