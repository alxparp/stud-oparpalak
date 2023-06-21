package com.eisgroup.layered.interfaces;

import com.eisgroup.layered.entity.Transport;

import java.util.List;

public interface TransportDao {

    List<Transport> findAll();

    Transport getById(int id);

    void save(Transport transport);

    void delete(Transport transport);
}
