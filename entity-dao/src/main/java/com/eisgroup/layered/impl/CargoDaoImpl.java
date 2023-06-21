package com.eisgroup.layered.impl;

import com.eisgroup.layered.entity.Cargo;
import com.eisgroup.layered.interfaces.CargoDao;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.List;

public class CargoDaoImpl implements CargoDao {

    private static final Logger LOGGER = Logger.getLogger(CargoDaoImpl.class);

    private EntityManager em;

    public CargoDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Cargo> findAll() {
        List<Cargo> cargo = em.createNamedQuery("Cargo.getAll", Cargo.class).getResultList();
        LOGGER.info("Found " + cargo.size() + " cargo objects");
        return cargo;
    }

    @Override
    public Cargo getById(int cargoID) {
        Cargo cargo = em.find(Cargo.class, cargoID);
        LOGGER.info("Found car with id " + cargoID);
        return cargo;
    }

    @Override
    public void save(Cargo cargo) {
        if(cargo == null) {
            LOGGER.error("Attempt to save null cargo");
            throw new IllegalArgumentException("Can't save null cargo");
        }
        em.getTransaction().begin();
        em.merge(cargo);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Cargo cargo) {
        if(cargo == null) {
            LOGGER.error("Attempt to delete null cargo");
            throw new IllegalArgumentException("Can't delete null cargo");
        }
        em.getTransaction().begin();
        em.remove(cargo);
        em.getTransaction().commit();
        LOGGER.info("Cargo with id " + cargo.getId() + " was deleted");
    }
}
