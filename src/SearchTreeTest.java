import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchTreeTest {

    @Test
    void constructor() {
        SearchTree<String> ST = new SearchTree<>();

        ST.add("C");
        ST.add("D");
        ST.add("A");

        assertEquals(true, ST.contains("C"));
        assertEquals(true, ST.contains("D"));
        assertEquals(true, ST.contains("A"));
        assertEquals(false, ST.contains("E"));

        assertEquals(3, ST.getSize());

        assertEquals("A\nC\nD\n",ST.generateInOrderString());

        assertThrows(IllegalArgumentException.class,
                () -> ST.add(null));

        ST.clear();
        assertEquals(0,ST.getSize());

    }

    @Test
    void getSize() {
    }

    @Test
    void add() {
    }

    @Test
    void contains() {
    }

    @Test
    void delete() {
        SearchTree<Integer> ST = new SearchTree<>();
        ST.delete(15);
        ST.add(15);
        ST.add(10);
        ST.add(30);
        ST.add(5);
        ST.add(12);
        ST.add(50);
        ST.add(20);
        ST.add(2);
        ST.add(13);
        ST.add(40);
        ST.add(60);
        ST.add(70);

        int size = ST.getSize();

        assertEquals(true, ST.contains(13));
        ST.delete(13);
        assertEquals(false, ST.contains(13));

        assertEquals(true, ST.contains(50));
        ST.delete(50);
        assertEquals(false, ST.contains(50));

        assertEquals(true, ST.contains(5));
        ST.delete(5);
        assertEquals(false, ST.contains(5));

        assertEquals(true, ST.contains(15));
        ST.delete(15);
        assertEquals(false, ST.contains(15));
    }
}