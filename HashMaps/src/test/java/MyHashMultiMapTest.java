import ru.vsu.cs.AnastasiyaSharykina.multimap.MultiMap;
import ru.vsu.cs.AnastasiyaSharykina.multimap.MyHashMultiMap;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class MyHashMultiMapTest {
    private MultiMap<String, Integer> myMultiMap;


    @Test
    public void testPutAndGetSingleValue() {
        myMultiMap = new MyHashMultiMap<>();
        myMultiMap.putValue("key1", 1);
        myMultiMap.putValue("key2", 2);

        assertEquals(1, myMultiMap.get("key1").size());
        assertEquals(1, myMultiMap.get("key2").size());
        assertEquals(1, myMultiMap.get("key1").toArray()[0]);
        assertEquals(2, myMultiMap.get("key2").toArray()[0]);
    }

    @Test
    public void testPutAndGetMultipleValues() {
        myMultiMap = new MyHashMultiMap<>();
        myMultiMap.putValue("key1", 1);
        myMultiMap.putValue("key1", 2);

        assertEquals(2, myMultiMap.get("key1").size());
    }

    @Test
    public void testRemoveSingleValue() {
        myMultiMap = new MyHashMultiMap<>();
        myMultiMap.putValue("key1", 1);
        myMultiMap.putValue("key1", 1);

        myMultiMap.putValue("key2", 2);

        assertEquals(2, myMultiMap.get("key1").size());
        assertEquals(1, myMultiMap.get("key2").size());

        assertTrue(myMultiMap.removeValue("key1", 1));

        assertEquals(1, myMultiMap.get("key1").size());
        assertEquals(1, myMultiMap.get("key2").size());

        assertTrue(myMultiMap.removeValue("key1", 1));

        assertFalse(myMultiMap.containsKey("key1"));

        assertFalse(myMultiMap.removeValue("not a key", 1));
    }

    @Test
    public void testRemoveAllValuesForKey() {
        myMultiMap = new MyHashMultiMap<>();
        myMultiMap.putValue("key1", 1);
        myMultiMap.putValue("key1", 2);

        Collection<Integer> removedValues = myMultiMap.removeAll("key1");
        assertEquals(2, removedValues.size());
        assertTrue(myMultiMap.isEmpty());
    }

    @Test
    public void testAsMap() {
        myMultiMap = new MyHashMultiMap<>();
        myMultiMap.putValue("key1", 1);
        myMultiMap.putValue("key2", 200);

        assertEquals(1, myMultiMap.asMap().get("key1").size());
        assertEquals(1, myMultiMap.asMap().get("key2").size());

        assertEquals(1, myMultiMap.asMap().get("key1").toArray()[0]);
        assertEquals(200, myMultiMap.asMap().get("key2").toArray()[0]);
    }

    @Test
    public void testClear() {
        myMultiMap = new MyHashMultiMap<>();
        myMultiMap.putValue("key1", 1);
        myMultiMap.putValue("key2", 2);
        assertFalse(myMultiMap.isEmpty());
        myMultiMap.clear();

        assertTrue(myMultiMap.isEmpty());
    }

    // Добавьте другие тесты по аналогии
}
