package com.eisgroup.layered.impl;

import com.eisgroup.layered.DayOfWeek;

import java.util.*;

public class DayOfWeekEnglish extends AbstractDayOfWeek implements DayOfWeek {

    @Override
    public String getDayOfWeek(Date date) {
        String[] weekDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return getDayOfWeek(date, weekDays);
    }

}
