package com.eisgroup.layered.impl;

import com.eisgroup.layered.entity.Producer;
import com.eisgroup.layered.interfaces.ProducerDao;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.List;

public class ProducerDaoImpl implements ProducerDao {

    private static final Logger LOGGER = Logger.getLogger(ProducerDaoImpl.class);

    private EntityManager em;

    public ProducerDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Producer> findAll() {
        List<Producer> producers = em.createNamedQuery("Producer.getAll", Producer.class).getResultList();
        LOGGER.info("Found " + producers.size() + " producer objects");
        return producers;
    }

    @Override
    public Producer getById(long producerID) {
        Producer producer = em.find(Producer.class, producerID);
        LOGGER.info("Found producer with id " + producerID);
        return producer;
    }

    @Override
    public void save(Producer producer) {
        if(producer == null) {
            LOGGER.error("Attempt to save null producer");
            throw new IllegalArgumentException("Can't save null producer");
        }
        em.getTransaction().begin();
        em.merge(producer);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Producer producer) {
        if(producer == null) {
            LOGGER.error("Attempt to delete null producer");
            throw new IllegalArgumentException("Can't delete null producer");
        }
        em.getTransaction().begin();
        em.remove(producer);
        em.getTransaction().commit();
        LOGGER.info("Producer with id " + producer.getId() + " was deleted");
    }
}
