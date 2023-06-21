package com.eisgroup.layered;

import com.eisgroup.layered.annotation.Cacheable;
import java.text.ParseException;

public interface Validator {
    @Cacheable(Cacheable.ATTRIBUTES.NULL_SKIP)
    String getDate(String date) throws ParseException;
}
