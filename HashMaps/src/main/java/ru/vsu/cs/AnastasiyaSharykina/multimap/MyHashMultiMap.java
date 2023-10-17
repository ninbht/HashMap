package ru.vsu.cs.AnastasiyaSharykina.multimap;

import ru.vsu.cs.AnastasiyaSharykina.map.MyHashMap;

import java.util.*;

public class MyHashMultiMap<K, V> extends MyHashMap<K, Collection<V>> implements MultiMap<K, V> {
    @Override
    public boolean putValue(K key, V value) {
        Collection<V> values = getOrCreateCollection(key);
        values.add(value);
        return true;
    }

    @Override
    public boolean removeValue(K key, V value) {
        Collection<V> values = get(key);
        if (values == null) {
            return false;
        }
        boolean removed = values.remove(value);
        if (values.isEmpty()) {
            super.remove(key);
        }
        return removed;
    }

    @Override
    public Collection<V> removeAll(K key) {
        Collection<V> values = super.remove(key);
        return values != null ? values : new ArrayList<>();
    }

    @Override
    public Map<K, Collection<V>> asMap() {
        Map<K, Collection<V>> map = new HashMap<>();
        for (Map.Entry<K, Collection<V>> entry : entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    private Collection<V> getOrCreateCollection(K key) {
        Collection<V> values = get(key);
        if (values == null) {
            values = new ArrayList<>();
            put(key, values);
        }
        return values;
    }
}