import ru.vsu.cs.AnastasiyaSharykina.map.MyHashMap;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class MyHashMapTest {
    private Map<String, Integer> myMap;

    @Test
    public void testPutAndGet() {
        myMap = new MyHashMap<>();
        myMap.put("key1", 1);
        myMap.put("key2", 2);
        System.out.println("aaaa");
        assertEquals(1, myMap.get("key1").intValue());
        assertEquals(2, myMap.get("key2").intValue());
    }

    @Test
    public void testSize() {
        myMap = new MyHashMap<>();
        assertEquals(0, myMap.size());
        myMap.put("key1", 1);
        myMap.put("key2", 2);

        assertEquals(2, myMap.size());
    }

    @Test
    public void testIsEmpty() {
        myMap = new MyHashMap<>();
        assertTrue(myMap.isEmpty());
        myMap.put("key1", 1);
        myMap.put("key2", 2);

        assertFalse(myMap.isEmpty());
    }


    @Test
    public void testPutAndGetWithCollision() {
        myMap = new MyHashMap<>(2); // Уменьшаем capacity для создания коллизии
        myMap.put("key1", 1);
        myMap.put("key3", 3); // Должно вызвать коллизию с "key1"

        assertEquals(1, myMap.get("key1").intValue());
        assertEquals(3, myMap.get("key3").intValue());
    }

    @Test
    public void testPutAndGetNullKey() {
        myMap = new MyHashMap<>();
        myMap.put(null, 42);
        assertEquals(42, myMap.get(null));
    }

    @Test
    public void testRemove() {
        myMap = new MyHashMap<>();
        myMap.put("key1", 1);
        myMap.put("key2", 2);
        assertEquals(2, myMap.size());
        assertEquals(1, myMap.remove("key1").intValue());
        assertNull(myMap.get("key1"));
        assertEquals(1, myMap.size());
    }

    @Test
    public void testRemoveNonExistentKey() {
        myMap = new MyHashMap<>();
        assertNull(myMap.remove("nonExistentKey"));
    }

    @Test
    public void testContainsValue() {
        myMap = new MyHashMap<>();
        myMap.put("key1", 1);
        myMap.put("key2", 2);

        assertTrue(myMap.containsValue(1));
        assertFalse(myMap.containsValue(3));
    }

    @Test
    public void testContainsKey() {
        myMap = new MyHashMap<>();
        myMap.put("key1", 1);
        myMap.put("key2", 2);

        assertTrue(myMap.containsKey("key1"));
        assertFalse(myMap.containsKey("key3"));
    }

    @Test
    public void testRemove1() {
        myMap = new MyHashMap<>();
        myMap.put("key1", 1);
        myMap.put("key2", 2);

        assertEquals(2, myMap.size());
        assertEquals(1, myMap.remove("key1").intValue());
        assertNull(myMap.get("key1"));
        assertEquals(1, myMap.size());
    }

    @Test
    public void testPutAll() {
        myMap = new MyHashMap<>();
        MyHashMap<String, Integer> anotherMap = new MyHashMap<>();
        anotherMap.put("key3", 3);
        anotherMap.put("key4", 4);

        myMap.putAll(anotherMap);

        assertEquals(3, myMap.get("key3").intValue());
        assertEquals(4, myMap.get("key4").intValue());
    }

    @Test
    public void testClear() {
        myMap = new MyHashMap<>();
        myMap.put("key1", 1);
        myMap.put("key2", 2);

        myMap.clear();

        assertEquals(0, myMap.size());
        assertNull(myMap.get("key1"));
        assertNull(myMap.get("key2"));
    }

    @Test
    public void testKeySet() {
        myMap = new MyHashMap<>();
        myMap.put("key1", 1);
        myMap.put("key2", 2);

        assertTrue(myMap.keySet().contains("key1"));
        assertTrue(myMap.keySet().contains("key2"));
    }

    @Test
    public void testValues() {
        myMap = new MyHashMap<>();
        myMap.put("key1", 1);
        myMap.put("key2", 2);

        assertTrue(myMap.values().contains(1));
        assertTrue(myMap.values().contains(2));
    }

    @Test
    public void testEntrySet() {
        myMap = new MyHashMap<>();
        myMap.put("key1", 1);
        myMap.put("key2", 2);

        assertTrue(myMap.entrySet().contains(new AbstractMap.SimpleEntry<>("key1", 1)));
        assertTrue(myMap.entrySet().contains(new AbstractMap.SimpleEntry<>("key2", 2)));
    }

    @Test
    public void testResize() {
        myMap = new MyHashMap<>();
        for (int i = 0; i < 1000; i++) {
            myMap.put("key" + i, i);
        }

        assertEquals(1000, myMap.size());
        assertEquals(500, myMap.get("key500").intValue());
    }


    @Test
    public void testCollision() {
        myMap = new MyHashMap<>();
        // Создаем два ключа с одинаковыми хешами
        String key1 = "AaAa";
        String key2 = "BBBB";

        // Убедимся, что хеши ключей совпадают
        assertEquals(Objects.hashCode(key1), Objects.hashCode(key2));

        myMap.put(key1, 1);
        myMap.put(key2, 2);

        // Проверяем, что ключи с коллизией обрабатываются корректно
        assertEquals(1, myMap.get(key1).intValue());
        assertEquals(2, myMap.get(key2).intValue());

        assertTrue(myMap.containsKey(key1));
        assertTrue(myMap.containsKey(key2));
    }
    @Test
    public void testCollisionRemove() {
        myMap = new MyHashMap<>();
        // Создаем два ключа с одинаковыми хешами
        String key1 = "AaAa";
        String key2 = "BBBB";

        // Убедимся, что хеши ключей совпадают
        assertEquals(Objects.hashCode(key1), Objects.hashCode(key2));

        myMap.put(key1, 1);
        myMap.put(key2, 2);

        // Проверяем, что ключи с коллизией обрабатываются корректно
        assertEquals(1, myMap.get(key1).intValue());
        assertEquals(2, myMap.get(key2).intValue());

        assertTrue(myMap.containsKey(key1));
        assertTrue(myMap.containsKey(key2));

        assertEquals(2, myMap.size());
        assertEquals(1, myMap.remove(key1).intValue());
        assertNull(myMap.get(key1));
        assertEquals(2, myMap.get(key2).intValue());
        assertEquals(1, myMap.size());
    }
    @Test
    public void testCollision1() {
        myMap = new MyHashMap<>();
        // Создаем два ключа с одинаковыми хешами
        String key1 = "AaAa";
        String key2 = "BBBB";

        // Убедимся, что хеши ключей совпадают
        assertEquals(Objects.hashCode(key1), Objects.hashCode(key2));

        myMap.put(key1, 1);
        myMap.put(key2, 2);

        // Проверяем, что ключи с коллизией обрабатываются корректно
        assertEquals(1, myMap.get(key1).intValue());
        assertEquals(2, myMap.get(key2).intValue());

        assertTrue(myMap.containsKey(key1));
        assertTrue(myMap.containsKey(key2));

        assertEquals(2, myMap.size());
        assertEquals(2, myMap.remove(key2).intValue());
        assertNull(myMap.get(key2));
        assertEquals(1, myMap.get(key1).intValue());
        assertEquals(1, myMap.size());


    }

    @Test
    public void testCollisionRemove1() {
        myMap = new MyHashMap<>();
        String key1 = "AaAa";
        String key2 = "AaAa";

        // Убедимся, что хеши ключей совпадают
        assertEquals(Objects.hashCode(key1), Objects.hashCode(key2));

        myMap.put(key1, 1);
        myMap.put(key2, 2);

        // Проверяем, что ключи с коллизией обрабатываются корректно
        assertEquals(2, myMap.get(key1).intValue());
        assertEquals(2, myMap.get(key2).intValue());
    }

    // Добавьте другие тесты по аналогии
}