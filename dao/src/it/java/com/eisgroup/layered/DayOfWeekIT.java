package com.eisgroup.layered;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:com.eisgroup.layered/dao-it-context.xml")
public class DayOfWeekIT {

    @Autowired
    DayOfWeek dayOfWeek;

    @Test
    public void getDate() throws ParseException {
        GregorianCalendar calendar = new GregorianCalendar(2018, 2, 23);
        String result = dayOfWeek.getDayOfWeek(calendar.getTime());
        assertEquals( result, "Friday");
    }
}
