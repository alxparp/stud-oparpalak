package com.eisgroup.layered.impl;

import com.eisgroup.layered.DayOfWeek;
import com.eisgroup.layered.Validator;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

public class ValidatorImpl implements Validator {

    private DayOfWeek dayOfWeek;

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    private Logger LOGGER = Logger.getLogger(ValidatorImpl.class);

    @Override
    public String getDate(String string) throws ParseException, IllegalArgumentException {
        if (string == null) {
            IllegalArgumentException ex = new IllegalArgumentException("Date with type \"String\" must not be null");
            LOGGER.warn(ex.getMessage(), ex);
            throw ex;
        }

        StringTokenizer tokenizer = new StringTokenizer(string, ".");
        while(tokenizer.hasMoreTokens()) {
            if(!tokenizer.nextToken().matches("\\d+")) {
                IllegalArgumentException ex = new IllegalArgumentException("Day, month or year must be a number");
                LOGGER.warn(ex.getMessage(), ex);
                throw ex;
            }
        }

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar cal = dateFormat.getCalendar();
        cal.setLenient(false);
        dateFormat.setCalendar(cal);

        ParsePosition pos = new ParsePosition(0);
        Date date = dateFormat.parse(string, pos);
        if (pos.getIndex() == 0) {
            ParseException ex = new ParseException("Unparseable date: \"" + string + "\"", pos.getErrorIndex());
            LOGGER.warn(ex.getMessage(), ex);
            throw ex;
        }

        return dayOfWeek.getDayOfWeek(date);
    }
}
