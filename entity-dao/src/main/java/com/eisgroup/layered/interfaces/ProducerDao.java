package com.eisgroup.layered.interfaces;

import com.eisgroup.layered.entity.Producer;

import java.util.List;

public interface ProducerDao {

    List<Producer> findAll();

    Producer getById(long id);

    void save(Producer producer);

    void delete(Producer producer);
}
