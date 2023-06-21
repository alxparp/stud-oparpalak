package com.eisgroup.layered.impl;

import com.eisgroup.layered.entity.Salon;
import com.eisgroup.layered.interfaces.SalonDao;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.List;

public class SalonDaoImpl implements SalonDao {

    private static final Logger LOGGER = Logger.getLogger(SalonDaoImpl.class);

    private EntityManager em;

    public SalonDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Salon> findAll() {
        List<Salon> salons = em.createNamedQuery("Salon.getAll", Salon.class).getResultList();
        LOGGER.info("Found " + salons.size() + " salon objects");
        return salons;
    }

    @Override
    public Salon getById(int salonID) {
        Salon salon = em.find(Salon.class, salonID);
        LOGGER.info("Found salon with id " + salonID);
        return salon;
    }

    @Override
    public void save(Salon salon) {
        if(salon == null) {
            LOGGER.error("Attempt to save null salon");
            throw new IllegalArgumentException("Can't save null salon");
        }
        em.getTransaction().begin();
        em.merge(salon);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Salon salon) {
        if(salon == null) {
            LOGGER.error("Attempt to delete null salon");
            throw new IllegalArgumentException("Can't delete null salon");
        }
        em.getTransaction().begin();
        em.remove(salon);
        em.getTransaction().commit();
        LOGGER.info("Salon with id " + salon.getId() + " was deleted");
    }
}
