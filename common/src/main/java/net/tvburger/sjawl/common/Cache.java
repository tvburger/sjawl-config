package net.tvburger.sjawl.common;

public interface Cache<K, V> {

    void put(K key, V value);

    V get(K key);

    boolean has(K key);

    void remove(K key);

    int size();

}
