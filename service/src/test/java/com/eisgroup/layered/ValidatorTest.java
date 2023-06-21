package com.eisgroup.layered;

import java.text.ParseException;

import com.eisgroup.layered.impl.ValidatorImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:com.eisgroup.layered/service-test-context.xml")
public class ValidatorTest {

    @Autowired
    private Validator validatorImpl;

    private String normalDateWithSlash = "12/05/2018";
    private String incorrectMonth = "12.14.2018";
    private String incorrectDay = "34.05.2018";
    private String randomStringsWithDots = "dd.MM.yyyy";
    private String randomStrings = "12.05.2018fsggree";
    private String day = "Суббота";

    @Test(expected = IllegalArgumentException.class)
    public void getDateByNull() throws ParseException {
        validatorImpl.getDate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getDateWithSlash() throws ParseException {
        String result = validatorImpl.getDate(normalDateWithSlash);
        assertEquals(day, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getDateRandomStringsWithDots() throws Exception {
        String result = validatorImpl.getDate(randomStringsWithDots);
        assertEquals(day, result);
    }

    @Test(expected = ParseException.class)
    public void getDateIncorrectMonth() throws ParseException {
        validatorImpl.getDate(incorrectMonth);
    }

    @Test(expected = ParseException.class)
    public void getDateIncorrectDay() throws ParseException {
        validatorImpl.getDate(incorrectDay);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getDateRandomStrings() throws ParseException {
        validatorImpl.getDate(randomStrings);
    }

}
