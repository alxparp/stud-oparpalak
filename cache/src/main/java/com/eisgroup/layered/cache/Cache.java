package com.eisgroup.layered.cache;


public interface Cache<K, V> {
    V put(K key, V value);
    V get(K key);
    V remove(K key);
    void clear();
    int getMaxMemorySize();
    int getMemorySize();
}