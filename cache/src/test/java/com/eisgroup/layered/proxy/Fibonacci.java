package com.eisgroup.layered.proxy;

import com.eisgroup.layered.annotation.Cacheable;

@Cacheable
public interface Fibonacci {
    int getNumberByIndex(int index);
    int getIndexByNumber(int number);
}
