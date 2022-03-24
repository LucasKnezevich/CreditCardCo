import com.sun.source.tree.Tree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeMapTest {

    @Test
    void constructor() {
        TreeMap<Integer,String> tree = new TreeMap<>();
        assertEquals(0,tree.getSize());
        assertNull(tree.get(2));
        tree.put(2,"Two");
        assertEquals("Two",tree.get(2));
    }

    @Test
    void clear() {
        TreeMap<Integer,String> tree = new TreeMap<>();
        tree.put(10,"Ten");
        tree.put(2,"Two");
        tree.put(20,"Twenty");
        tree.put(30,"Thirty");

        assertEquals(4,tree.getSize());
        tree.clear();
        assertEquals(0, tree.getSize());
    }

    @Test
    void getSize() {
        TreeMap<Integer,String> tree = new TreeMap<>();
        assertEquals(0,tree.getSize());

        tree.put(10,"Ten");
        tree.put(2,"Two");
        tree.put(20,"Twenty");
        tree.put(30,"Thirty");

        assertEquals(4, tree.getSize());
    }

    @Test
    void put() {
        TreeMap<String,String> tree = new TreeMap<>();
        tree.put("Ten","10");
        tree.put("Two","2");
        tree.put("Twenty","20");
        tree.put("Thirty","30");
        tree.put("Forty","40");
        tree.put("Thirty","30");

        assertEquals(5,tree.getSize());

        assertEquals("40", tree.get("Forty"));
        tree.put("Forty","Changed!");
        assertEquals("Changed!", tree.get("Forty"));

        assertThrows(IllegalArgumentException.class,
                () -> tree.put(null,""));
    }

    @Test
    void get() {
        TreeMap<Integer,String> tree = new TreeMap<>();
        assertNull(tree.get(10));

        tree.put(10,"Ten");
        tree.put(2,"Two");
        tree.put(20,"Twenty");
        tree.put(30,"Thirty");

        assertEquals("Two", tree.get(2));
        assertNull(tree.get(50));
        assertThrows(IllegalArgumentException.class,
                () -> tree.get(null));
    }

    @Test
    void containsKey() {
        TreeMap<Integer,String> tree = new TreeMap<>();
        assertFalse(tree.containsKey(10));

        tree.put(10,"Ten");
        tree.put(2,"Two");
        tree.put(20,"Twenty");
        tree.put(30,"Thirty");

        assertTrue(tree.containsKey(20));
        assertFalse(tree.containsKey(50));
        assertThrows(IllegalArgumentException.class,
                () -> tree.containsKey(null));
    }

    @Test
    void deleteKey() {
        TreeMap<Integer,String> tree = new TreeMap<>();
        tree.deleteKey(15);
        tree.put(15,"15");
        tree.put(10,"10");
        tree.put(30,"30");
        tree.put(5,"5");
        tree.put(12,"12");
        tree.put(50,"50");
        tree.put(20,"20");
        tree.put(2,"2");
        tree.put(14, "14");
        tree.put(13,"13");
        tree.put(40,"40");
        tree.put(60,"60");
        tree.put(70,"70");

        // 14 has left child only
        assertTrue(tree.containsKey(14));
        tree.deleteKey(14);
        assertFalse(tree.containsKey(14));

        // 60 has right child only
        assertTrue(tree.containsKey(60));
        tree.deleteKey(60);
        assertFalse(tree.containsKey(60));

        // 13 is a leaf node
        assertTrue(tree.containsKey(13));
        tree.deleteKey(13);
        assertFalse(tree.containsKey(13));

        // 50 has left/right children
        assertTrue(tree.containsKey(50));
        tree.deleteKey(50);
        assertFalse(tree.containsKey(50));

        assertTrue(tree.containsKey(5));
        tree.deleteKey(5);
        assertFalse(tree.containsKey(5));

        // 15 is the current root
        assertTrue(tree.containsKey(15));
        tree.deleteKey(15);
        assertFalse(tree.containsKey(15));

        assertTrue(tree.containsKey(70));
        assertTrue(tree.containsKey(10));
    }

    @Test
    void keyArray() {
        TreeMap<Integer,String> tree = new TreeMap<>();
        tree.put(15,"15");
        tree.put(10,"10");
        tree.put(30,"30");
        tree.put(5,"5");
        tree.put(12,"12");
        tree.put(50,"50");

        Integer[] intArr = new Integer[0];
        assertEquals(5, tree.keyArray(intArr)[0]);
        assertEquals(15, tree.keyArray(intArr)[3]);
    }
}