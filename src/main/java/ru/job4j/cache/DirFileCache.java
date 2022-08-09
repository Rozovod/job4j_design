package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringJoiner;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader br = new BufferedReader(new FileReader(String.format("%s/%s", cachingDir, key)))) {
            br.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
