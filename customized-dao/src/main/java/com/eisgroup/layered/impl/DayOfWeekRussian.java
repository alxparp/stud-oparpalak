package com.eisgroup.layered.impl;

import com.eisgroup.layered.DayOfWeek;

import java.util.Date;

public class DayOfWeekRussian extends AbstractDayOfWeek implements DayOfWeek {

    @Override
    public String getDayOfWeek(Date date) {
        String[] weekDays = {"Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"};
        return getDayOfWeek(date, weekDays);
    }

}
