package com.eisgroup.layered.interfaces;

import com.eisgroup.layered.entity.Cargo;

import java.util.List;

public interface CargoDao {

    List<Cargo> findAll();

    Cargo getById(int id);

    void save(Cargo cargo);

    void delete(Cargo cargo);

}
