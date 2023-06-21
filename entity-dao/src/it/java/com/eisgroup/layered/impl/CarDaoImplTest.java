package com.eisgroup.layered.impl;

import com.eisgroup.layered.entity.Bus;
import com.eisgroup.layered.entity.Car;
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

public class CarDaoImplTest {
    protected static EntityManagerFactory emf;
    protected static EntityManager em;
    protected static CarDaoImpl carDao;

    @BeforeClass
    public static void init() throws FileNotFoundException, SQLException {
        emf = Persistence.createEntityManagerFactory("transport-test");
        em = emf.createEntityManager();
        carDao = new CarDaoImpl(em);
    }

    @Before
    public void initializeDatabase(){
        Session session = em.unwrap(Session.class);
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                try {
                    File file = new File("src/it/resources/car-data.sql");
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
    public void testFindAllCars() {
        List<Car> cars = carDao.findAll();
        assertEquals(1, cars.size());
    }

    @Test
    public void testSaveCar() {
        Producer producer = new Producer();
        producer.setName("BMW Corp.");
        Car car = new Car("BMW", 27000, true, producer,1000, 1500);
        car.setId(5);

        carDao.save(car);
        List<Car> cars = carDao.findAll();
        assertNotNull(cars);
        assertEquals(2, cars.size());
    }
}
