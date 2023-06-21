package com.eisgroup.layered.cache;

import org.apache.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRU<K, V> implements Cache<K, V> {

    private Logger LOGGER = Logger.getLogger(LRU.class);

    private static final int DEFAULT_CAPACITY = 10;

    private Map<K, V> storage;

    private final int maxMemorySize;

    public LRU() {
        this(DEFAULT_CAPACITY);
    }

    public static class LRUHashMap<K, V> extends LinkedHashMap<K, V> {
        private int capacity;

        private LRUHashMap(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry entry) {
            return size() > capacity;
        }
    }

    public LRU(int capacity) {
        if (capacity <= 0) {
            IllegalArgumentException ex = new IllegalArgumentException("capacity <= 0");
            LOGGER.warn(ex.getMessage(), ex);
            throw ex;
        }

        maxMemorySize = capacity;
        this.storage = new LRUHashMap<K, V>(maxMemorySize);
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            IllegalArgumentException ex = new IllegalArgumentException("Key or Value must not be null");
            LOGGER.warn(ex.getMessage(), ex);
            throw ex;
        }

        V previous = storage.put(key, value);
        trimToSize(maxMemorySize);
        return previous;
    }

    private void trimToSize(int maxSize) {
        while (true) {
            int memorySize = storage.size();
            if (memorySize <= maxSize) {
                break;
            }
            Map.Entry<K, V> toRemove = storage.entrySet().iterator().next();
            storage.remove(toRemove.getKey());
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            IllegalArgumentException ex = new IllegalArgumentException("Key or Value must not be null");
            LOGGER.warn(ex.getMessage(), ex);
            throw ex;
        }
        V value = storage.get(key);
        return value;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            IllegalArgumentException ex = new IllegalArgumentException("Key or Value must not be null");
            LOGGER.warn(ex.getMessage(), ex);
            throw ex;
        }
        V previous = storage.remove(key);
        return previous;
    }

    @Override
    public final void clear() {
        storage.clear();
    }

    @Override
    public int getMemorySize() {
        return storage.size();
    }

    @Override
    public int getMaxMemorySize() {
        return maxMemorySize;
    }


    public final Map<K, V> snapshot() {
        return new LinkedHashMap<>(storage);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry : storage.entrySet()) {
            sb.append(entry.getKey())
                    .append('=')
                    .append(entry.getValue())
                    .append(",");
        }
        sb.append("maxMemory=")
                .append(maxMemorySize)
                .append(",")
                .append("memorySize=")
                .append(storage.size());
        return sb.toString();
    }
}
