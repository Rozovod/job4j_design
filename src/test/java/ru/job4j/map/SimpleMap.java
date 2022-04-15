package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private int hash(int hashCode) {
        return hashCode % table.length;
    }

    private int indexFor(int hash) {
        return hash;
    }

    private void expand() {
        MapEntry<K, V>[] tableSecond = new MapEntry[capacity * 2];
        for (MapEntry<K, V> kvMapEntry : table) {
            if (kvMapEntry != null) {
                tableSecond[indexFor(hash(kvMapEntry.hashCode()))] = kvMapEntry;
            }
        }
        table = tableSecond;
    }

    @Override
    public boolean put(K key, V value) {
        int index = indexFor(hash(key.hashCode()));
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        boolean rsl = table[index] == null;
        if (rsl) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return rsl;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null && table[index].key.equals(key)) {
            rsl = table[index].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(hash(key.hashCode()));
        boolean rsl = table[index] != null && table[index].key.equals(key);
        if (rsl) {
            table[index] = null;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int expectedModCount = modCount;
            int i = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (i < table.length && table[i] == null) {
                    i++;
                }
                return i < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[i++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
