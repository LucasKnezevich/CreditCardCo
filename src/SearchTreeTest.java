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

        assertEquals(3, ST.getSize());

        assertThrows(IllegalArgumentException.class,
                () -> ST.contains(null));
        assertThrows(IllegalArgumentException.class,
                () -> ST.add(null));
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
}