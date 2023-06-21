package com.eisgroup.layered.impl;

import com.eisgroup.layered.entity.Cargo;
import com.eisgroup.layered.entity.Motorcycle;
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

public class MotorcycleDaoImplTest {
    protected static EntityManagerFactory emf;
    protected static EntityManager em;
    protected static MotorcycleDaoImpl motorcycleDao;

    @BeforeClass
    public static void init() throws FileNotFoundException, SQLException {
        emf = Persistence.createEntityManagerFactory("transport-test");
        em = emf.createEntityManager();
        motorcycleDao = new MotorcycleDaoImpl(em);
    }

    @Before
    public void initializeDatabase(){
        Session session = em.unwrap(Session.class);
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                try {
                    File file = new File("src/it/resources/motorcycle-data.sql");
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
    public void testFindAllMotorcycles() {
        List<Motorcycle> motorcycles = motorcycleDao.findAll();
        assertEquals(1, motorcycles.size());
    }

    @Test
    public void testSaveMotorcycle() {
        Producer producer = new Producer();
        producer.setName("BMW Corp.");
        Motorcycle motorcycle = new Motorcycle("BMW", 27000, true, producer, 2009);
        motorcycle.setId(5);

        motorcycleDao.save(motorcycle);
        List<Motorcycle> motorcycles = motorcycleDao.findAll();
        assertNotNull(motorcycles);
        assertEquals(2, motorcycles.size());
    }
}
