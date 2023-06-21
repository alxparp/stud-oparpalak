package com.eisgroup.layered.impl;

import com.eisgroup.layered.DayOfWeek;
import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AbstractDayOfWeek {

    private Logger LOGGER = Logger.getLogger(DayOfWeek.class);

    private static final int COUNT_DAYS_IN_WEEK = 7;

    protected String getDayOfWeek(Date date, String[] weekDays) {
        if (date == null || weekDays == null) {
            IllegalArgumentException ex = new IllegalArgumentException("Date or weekDays must not be null");
            LOGGER.warn(ex.getMessage(), ex);
            throw ex;
        }

        if (weekDays.length != COUNT_DAYS_IN_WEEK) {
            IllegalArgumentException ex = new IllegalArgumentException("Week must have 7 days");
            LOGGER.warn(ex.getMessage(), ex);
            throw ex;
        }

        for (int i = 0; i < weekDays.length; i++) {
            if (weekDays[i] == "") {
                IllegalArgumentException ex = new IllegalArgumentException("Days in week must not be empty");
                LOGGER.warn(ex.getMessage(), ex);
                throw ex;
            }
        }

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(date.getTime());
        String stringDay = weekDays[gregorianCalendar.get(Calendar.DAY_OF_WEEK)-1];
        return stringDay;
    }

}
