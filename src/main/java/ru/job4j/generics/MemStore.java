package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {
    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        String id = model.getId();
        storage.putIfAbsent(id, model);
    }

    @Override
    public boolean replace(String id, T model) {
        return storage.replace(id, model) != null;
    }

    @Override
    public boolean delete(String id) {
        T model = findById(id);
        return storage.remove(id, model);
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}
