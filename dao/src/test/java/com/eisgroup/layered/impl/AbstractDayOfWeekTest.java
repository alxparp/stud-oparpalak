package com.eisgroup.layered.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:com.eisgroup.layered/dao-test-context.xml")
public class AbstractDayOfWeekTest {

    String[] weekDays = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    String[] weekDaysNotCorrespond = new String[] {"Sunday", "Monday", "Tuesday"};
    String[] emptyDaysInWeek = new String[] {"Sunday", "Monday", "Tuesday", "", "Thursday", "Friday", ""};
    String day = "Sunday";

    @Autowired
    Date date;

    @Autowired
    GregorianCalendar calendar;

    @Autowired
    AbstractDayOfWeek adow;

    @Before
    public void before() throws Exception {
        date = calendar.getTime();
    }

    @Test(expected = IllegalArgumentException.class)
    public void argumentsNull() {
        adow.getDayOfWeek(null, null);
        adow.getDayOfWeek(null, weekDays);
        adow.getDayOfWeek(date, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notCorrespondNumberDays() {
        adow.getDayOfWeek(date, weekDaysNotCorrespond);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyDaysInWeek() {
        adow.getDayOfWeek(date, emptyDaysInWeek);
    }

    @Test
    public void getDayOfWeek() {
        String result = adow.getDayOfWeek(date, weekDays);
        assertEquals(day, result);
    }

}
