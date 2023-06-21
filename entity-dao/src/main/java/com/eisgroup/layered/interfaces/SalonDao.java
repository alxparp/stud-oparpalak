package com.eisgroup.layered.interfaces;

import com.eisgroup.layered.entity.Salon;

import java.util.List;

public interface SalonDao {

    List<Salon> findAll();

    Salon getById(int id);

    void save(Salon salon);

    void delete(Salon salon);
}
