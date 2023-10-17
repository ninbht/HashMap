package ru.vsu.cs.AnastasiyaSharykina.map;

import java.util.*;
import java.util.stream.Collectors;

public class MyHashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private int capacity;
    private int size;
    private Node<K, V>[] table;

    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashMap(int capacity) {
        this.capacity = capacity;
        this.table = new Node[capacity];
    }

    private static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int hash = Objects.hashCode(key);
        int index = getIndex(hash);
        Node<K, V> current = table[index];

        while (current != null) {
            if (current.hash == hash && Objects.equals(current.key, key)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (Node<K, V> node : table) {
            while (node != null) {
                if (Objects.equals(node.value, value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int hash = Objects.hashCode(key);
        int index = getIndex(hash);
        Node<K, V> current = table[index];

        while (current != null) {
            if (current.hash == hash && Objects.equals(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        if (size >= capacity * LOAD_FACTOR) {
            resize();
        }

        int hash = Objects.hashCode(key);
        int index = getIndex(hash);
        Node<K, V> current = table[index];
        Node<K, V> newNode = new Node<>(hash, key, value);

        if (current == null) {
            table[index] = newNode;
        } else {
            while (true) {
                if (current.hash == hash && Objects.equals(current.key, key)) {
                    V oldValue = current.value;
                    current.value = value;
                    return oldValue;
                }
                if (current.next == null) {
                    break;
                }
                current = current.next;
            }
            current.next = newNode;
        }

        size++;
        return null;
    }

    @Override
    public V remove(Object key) {
        int hash = Objects.hashCode(key);
        int index = getIndex(hash);
        Node<K, V> current = table[index];
        Node<K, V> prev = null;

        while (current != null) {
            if (current.hash == hash && Objects.equals(current.key, key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }

        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (Node<K, V> node : table) {
            while (node != null) {
                keySet.add(node.key);
                node = node.next;
            }
        }
        return keySet;
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for (Node<K, V> node : table) {
            while (node != null) {
                values.add(node.value);
                node = node.next;
            }
        }
        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = new HashSet<>();
        for (Node<K, V> node : table) {
            while (node != null) {
                entrySet.add(new AbstractMap.SimpleEntry<>(node.key, node.value));
                node = node.next;
            }
        }
        return entrySet;
    }

    private int getIndex(int hash) {
        return (capacity - 1) & hash;
    }

    private void resize() {
        int newCapacity = capacity * 2;
        Node<K, V>[] newTable = new Node[newCapacity];

        for (Node<K, V> node : table) {
            while (node != null) {
                int newIndex = (newCapacity - 1) & node.hash;
                Node<K, V> next = node.next;
                node.next = newTable[newIndex];
                newTable[newIndex] = node;
                node = next;
            }
        }

        table = newTable;
        capacity = newCapacity;
    }
}