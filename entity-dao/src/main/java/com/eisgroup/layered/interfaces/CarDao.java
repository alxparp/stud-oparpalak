package com.eisgroup.layered.interfaces;

import com.eisgroup.layered.entity.Car;

import java.util.List;

public interface CarDao {

    List<Car> findAll();

    Car getById(int id);

    void save(Car car);

    void delete(Car car);
}
