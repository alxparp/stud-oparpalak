package com.eisgroup.layered.impl;

import com.eisgroup.layered.entity.Transport;
import com.eisgroup.layered.interfaces.TransportDao;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.List;

public class TransportDaoImpl implements TransportDao {

    private static final Logger LOGGER = Logger.getLogger(TransportDaoImpl.class);

    private EntityManager em;

    public TransportDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Transport> findAll() {
        List<Transport> transports = em.createNamedQuery("Transport.getAll", Transport.class).getResultList();
        LOGGER.info("Found " + transports.size() + " transport objects");
        return transports;
    }

    @Override
    public Transport getById(int transportID) {
        Transport transport = em.find(Transport.class, transportID);
        LOGGER.info("Found transport with id " + transportID);
        return transport;
    }

    @Override
    public void save(Transport transport) {
        if(transport == null) {
            LOGGER.error("Attempt to save null transport");
            throw new IllegalArgumentException("Can't save null transport");
        }
        em.getTransaction().begin();
        em.merge(transport);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Transport transport) {
        if(transport == null) {
            LOGGER.error("Attempt to delete null transport");
            throw new IllegalArgumentException("Can't delete null transport");
        }
        em.getTransaction().begin();
        em.remove(transport);
        em.getTransaction().commit();
        LOGGER.info("Transport with id " + transport.getId() + " was deleted");
    }
}
