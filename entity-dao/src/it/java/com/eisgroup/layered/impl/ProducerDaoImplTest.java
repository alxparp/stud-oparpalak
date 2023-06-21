package com.eisgroup.layered.impl;

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

public class ProducerDaoImplTest {
    protected static EntityManagerFactory emf;
    protected static EntityManager em;
    protected static ProducerDaoImpl producerDao;

    @BeforeClass
    public static void init() throws FileNotFoundException, SQLException {
        emf = Persistence.createEntityManagerFactory("transport-test");
        em = emf.createEntityManager();
        producerDao = new ProducerDaoImpl(em);
    }

    @Before
    public void initializeDatabase(){
        Session session = em.unwrap(Session.class);
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                try {
                    File file = new File("src/it/resources/producer-data.sql");
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
    public void testFindAllProducers() {
        List<Producer> producers = producerDao.findAll();
        assertEquals(1, producers.size());
    }

    @Test
    public void testSaveProducer() {
        Producer producer = new Producer();
        producer.setName("BMW Corp.");
        producer.setId(4);

        producerDao.save(producer);
        List<Producer> producers = producerDao.findAll();
        assertNotNull(producers);
        assertEquals(2, producers.size());
    }
}
