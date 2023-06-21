package com.eisgroup.layered.impl;

import com.eisgroup.layered.entity.Bus;
import com.eisgroup.layered.entity.Cargo;
import com.eisgroup.layered.entity.Producer;
import org.h2.tools.RunScript;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class CargoDaoImplTest {
    protected static EntityManagerFactory emf;
    protected static EntityManager em;
    protected static CargoDaoImpl cargoDao;

    @BeforeClass
    public static void init() throws FileNotFoundException, SQLException {
        emf = Persistence.createEntityManagerFactory("transport-test");
        em = emf.createEntityManager();
        cargoDao = new CargoDaoImpl(em);
    }

    @Before
    public void initializeDatabase(){
        Session session = em.unwrap(Session.class);
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                try {
                    File file = new File("src/it/resources/cargo-data.sql");
                    RunScript.execute(connection, new FileReader(file.getAbsoluteFile()));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException("could not initialize with script");
                }
            }
        });
    }

    @AfterClass
    public static void tearDown(){
        if(em != null) em.clear();
        if(em != null) em.close();
        if(emf!= null) emf.close();
    }

    @Test
    public void testFindAllCargos() {
        List<Cargo> cargo = cargoDao.findAll();
        assertEquals(1, cargo.size());
    }

    @Test
    public void testSaveCargo() {
        Producer producer = new Producer();
        producer.setName("BMW Corp.");
        Cargo cargo = new Cargo("BMW", 27000, true, producer, "Лесовоз");
        cargo.setId(5);

        cargoDao.save(cargo);
        List<Cargo> cargos = cargoDao.findAll();
        assertNotNull(cargos);
        assertEquals(2, cargos.size());
    }
}
