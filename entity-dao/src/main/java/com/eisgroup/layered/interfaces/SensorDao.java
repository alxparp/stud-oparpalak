package com.eisgroup.layered.interfaces;

import com.eisgroup.layered.entity.Sensor;

import java.util.List;

public interface SensorDao {

    List<Sensor> findAll();

    Sensor getById(int id);

    void save(Sensor sensor);

    void delete(Sensor sensor);
}
