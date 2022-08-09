package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public V get(K key) {
        V value;
        if (cache.containsKey(key)) {
            SoftReference<V> softValue = cache.get(key);
            value = softValue.get();
        } else {
            cache.put(key, new SoftReference<>(load(key)));
            SoftReference<V> secondValue = cache.get(key);
            value = secondValue.get();
        }
        return value;
    }

    protected abstract V load(K key);
}
