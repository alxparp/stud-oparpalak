package com.eisgroup.layered.impl;

import com.eisgroup.layered.DayOfWeek;
import org.junit.After;
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
public class DayOfWeekEnglishTest {

    @Autowired
    private DayOfWeek dayOfWeekImpl;

    @Autowired
    private Date date;

    private GregorianCalendar calendar;
    private String dateString;

    @After
    public void after() throws Exception {
        dayOfWeekImpl = null;
        calendar = null;
        dateString = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void getDateByNull() {
        dayOfWeekImpl.getDayOfWeek(null);
    }

    @Test
    public void getDayOfWeekMonday() {
        calendar = new GregorianCalendar(2018, 2, 19);
        date = calendar.getTime();
        dateString = "Monday";
        checkDayOfWeek();
    }

    @Test
    public void getDayOfWeekTuesday() {
        calendar = new GregorianCalendar(2018, 2, 20);
        date = calendar.getTime();
        dateString = "Tuesday";
        checkDayOfWeek();
    }

    @Test
    public void getDayOfWeekWednesday() {
        calendar = new GregorianCalendar(2018, 2, 21);
        date = calendar.getTime();
        dateString = "Wednesday";
        checkDayOfWeek();
    }

    @Test
    public void getDayOfWeekThursday() {
        calendar = new GregorianCalendar(2018, 2, 22);
        date = calendar.getTime();
        dateString = "Thursday";
        checkDayOfWeek();
    }

    @Test
    public void getDayOfWeekFriday() {
        calendar = new GregorianCalendar(2018, 2, 23);
        date = calendar.getTime();
        dateString = "Friday";
        checkDayOfWeek();
    }

    @Test
    public void getDayOfWeekSaturday() {
        calendar = new GregorianCalendar(2018, 2, 24);
        date = calendar.getTime();
        dateString = "Saturday";
        checkDayOfWeek();
    }

    @Test
    public void getDayOfWeekSunday() {
        calendar = new GregorianCalendar(2018, 2, 25);
        date = calendar.getTime();
        dateString = "Sunday";
        checkDayOfWeek();
    }

    private void checkDayOfWeek() {
        String result = dayOfWeekImpl.getDayOfWeek(date);
        assertEquals(dateString, result);
    }
}
