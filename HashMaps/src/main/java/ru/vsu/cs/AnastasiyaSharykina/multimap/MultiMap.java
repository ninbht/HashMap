package ru.vsu.cs.AnastasiyaSharykina.multimap;
import java.util.Collection;
import java.util.Map;

public interface MultiMap<K, V> {
    int size();

    boolean isEmpty();

    boolean containsKey(K key);

    boolean containsValue(V value);

    Collection<V> get(K key);

    boolean putValue(K key, V value);

    boolean removeValue(K key, V value);

    Collection<V> removeAll(K key);

    void clear();

    Map<K, Collection<V>> asMap();
}
