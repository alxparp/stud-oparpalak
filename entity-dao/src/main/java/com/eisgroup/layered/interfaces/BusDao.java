package com.eisgroup.layered.interfaces;

import com.eisgroup.layered.entity.Bus;

import java.util.List;

public interface BusDao {

    List<Bus> findAll();

    Bus getById(int busID);

    void save(Bus bus);

    void delete(Bus bus);

}
