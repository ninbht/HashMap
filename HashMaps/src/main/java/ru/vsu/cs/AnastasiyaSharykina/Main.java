package ru.vsu.cs.AnastasiyaSharykina;

import ru.vsu.cs.AnastasiyaSharykina.multimap.MyHashMultiMap;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyHashMultiMap<String, Integer> myHashMultiMap = new MyHashMultiMap<>();
        myHashMultiMap.putValue("key1", 1);
        myHashMultiMap.putValue("key2", 2);
        myHashMultiMap.putValue("key3", 3);
        myHashMultiMap.putValue("key3", 4);
        myHashMultiMap.putValue("key3", -10);
        myHashMultiMap.putValue("key2", 2);

        for (String key: myHashMultiMap.keySet()) {
            System.out.println(key);
            System.out.println(Arrays.toString(myHashMultiMap.get(key).toArray()));
        }
        System.out.println();
        myHashMultiMap.removeValue("key1", 1);
        myHashMultiMap.removeValue("key3", 4);
        for (String key: myHashMultiMap.keySet()) {
            System.out.println(key);
            System.out.println(Arrays.toString(myHashMultiMap.get(key).toArray()));
        }
        System.out.println("Hello world!");
    }
}