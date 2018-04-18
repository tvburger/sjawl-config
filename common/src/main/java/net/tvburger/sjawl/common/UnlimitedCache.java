package net.tvburger.sjawl.common;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public final class UnlimitedCache<K, V> implements Cache<K, V> {

    private final Map<K, V> cache = new HashMap<>();

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public V get(K key) {
        if (!cache.containsKey(key)) {
            throw new NoSuchElementException();
        }
        return cache.get(key);
    }

    @Override
    public boolean has(K key) {
        return cache.containsKey(key);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
    }

    @Override
    public int size() {
        return cache.size();
    }

}
