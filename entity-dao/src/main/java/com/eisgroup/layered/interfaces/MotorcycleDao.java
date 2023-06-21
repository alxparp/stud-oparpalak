package com.eisgroup.layered.interfaces;

import com.eisgroup.layered.entity.Motorcycle;

import java.util.List;

public interface MotorcycleDao {

    List<Motorcycle> findAll();

    Motorcycle getById(int id);

    void save(Motorcycle motorcycle);

    void delete(Motorcycle motorcycle);
}
