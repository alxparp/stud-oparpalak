package com.eisgroup.layered;

import com.eisgroup.layered.impl.ValidatorImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:com.eisgroup.layered/service-it-context.xml")
public class ValidatorIT {

    @Autowired
    ValidatorImpl validatorImpl;

    @Test
    public void getDate() throws ParseException {
        String result = validatorImpl.getDate("12.05.2018");
        assertEquals("Saturday", result);
    }

}
