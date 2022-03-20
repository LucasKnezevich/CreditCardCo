/**
 * TreeMap class.
 * @param <K>       The type.
 * @param <V>       The value.
 */
public class TreeMap<K extends Comparable<K>, V> {

    /**
     * For testing/debugging purposes.
     */
    private static final boolean DEBGUG = false;

    /**
     * The root node.
     */
    private TreeNode<K,V> root;

    /**
     * Size of the TreeMap.
     */
    private int size;

    /**
     * Full constructor
     */
    public TreeMap() {
        root = null;
    }

    /**
     * Represents a TreeNode.
     * @param <K>   The key.
     * @param <V>   The value.
     */
    private static class TreeNode<K,V> {
        /**
         * The key.
         */
        public K key;

        /**
         * The value.
         */
        public V value;

        /**
         * The left child.
         */
        public TreeNode<K,V> left;

        /**
         * The right child.
         */
        public TreeNode<K,V> right;

        /**
         * Constructor for a Node
         * @param key       The key.
         * @param value     The value.
         */
        public TreeNode(K key,V value) {
            this.key = key;
            this.value = value;
        }

    }

    /**
     * Removes all mappings from this TreeMap, emptying the TreeMap.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the size of this TreeMap.
     * @return      The size of the TreeMap.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Adds the specified value with the specified key in this map.  Updates the value if specified key already exists.
     * @param key       The key to be added or updated.  Cannot be null.
     * @param value     The value to be added or updated.
     */
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        } else {
            root = put(key, value, root);
        }
    }

    /**
     * Adds the specified value with the specified key in this map.  Updates the value if specified key already exists.
     * @param key           The key to be added or updated.
     * @param value         The value to be added.
     * @param startNode     The starting node.
     * @return              The starting node.
     */
    private TreeNode<K,V> put(K key, V value, TreeNode<K,V> startNode) {
        if (!containsKey(key)) {    // If the key isn't already in the TreeMap
            if (startNode == null) {    // Found its home
                startNode = new TreeNode<>(key, value);
                size++;
            } else {       // Looking for home
                if (key.compareTo(startNode.key) <= 0) {
                    startNode.left = put(key, value, startNode.left);
                } else {
                    startNode.right = put(key, value, startNode.right);
                }
            }
        } else {    // The key is already in the TreeMap
            if (key.compareTo(startNode.key) == 0) {    // Found the TreeNode that matches the key
                startNode.value = value;
            } else if (key.compareTo(startNode.key) <= 0) {
                startNode.left = put(key, value, startNode.left);
            } else {
                startNode.right = put(key, value, startNode.right);
            }
        }

        return startNode;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if they key does not exist.
     * @param key       The key being searched for.  Cannot be null.
     * @return          The value of the specified key.
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key being searched for cannot be null.");
        }
        return get(key, root);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if they key does not exist.
     * @param key           The key being searched for.
     * @param startNode     The starting TreeNode.
     * @return              The value of the specified key.
     */
    private V get(K key, TreeNode<K,V> startNode) {
        if (startNode == null) {
            return null;
        } else if (key.compareTo(startNode.key) == 0) {
            return startNode.value;
        } else if (key.compareTo(startNode.key) < 0) {
            return get(key, startNode.left);
        } else {
            return get(key, startNode.right);
        }
    }

    /**
     * Method for checking whether a key is present in the TreeMap.
     * @param key       The key being looked for.  Cannot be null.
     * @return          Returns true if the specified key is in this TreeMap.
     */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key being searched for cannot be null");
        }
        return containsKey(key, root);
    }

    /**
     * Method for checking whether a key is present in the TreeMap.
     * @param key               The key being searched for.
     * @param startNode         The TreeNode to start looking from.
     * @return                  Returns true if the specified key is in this TreeMap.
     */
    private boolean containsKey(K key, TreeNode<K,V> startNode) {
        if (startNode == null) {
            return false;
        } else if(key.compareTo(startNode.key) == 0) {
            return true;
        } else if (key.compareTo(startNode.key) < 0) {
            return containsKey(key, startNode.left);
        } else {
            return containsKey(key, startNode.right);
        }
    }



    /**
     * Removes a key from the TreeMap, if present.
     * @param key      The key to be removed.
     */
    public void deleteKey(K key) {
        root = deleteKey(key, root);
    }

    /**
     * Removes a key from the TreeMap, if present.
     * @param key           The key to be removed.
     * @param startNode     The starting TreeNode
     * @return              A TreeNode.
     */
    private TreeNode<K,V> deleteKey(K key, TreeNode<K,V> startNode) {
        if (startNode == null) {
            return null;
        }
        if (key.compareTo(startNode.key) < 0) {
            startNode.left = deleteKey(key, startNode.left);
        } else if (key.compareTo(startNode.key) > 0) {
            startNode.right = deleteKey(key, startNode.right);
        } else {
            if (startNode.left == null) {  // Only right child exists
                size--;
                return startNode.right;
            } else if (startNode.right == null) {   // Only left child exists
                size--;
                return startNode.left;
            }
            startNode.key = successor(startNode.right);
            startNode.right = deleteKey(startNode.key, startNode.right);
        }
        return startNode;
    }

    /**
     * Returns the leftmost key of the right subtree.
     * @param startNode     The starting position.
     * @return              The key of the appropriate successor.
     */
    private K successor(TreeNode<K,V> startNode) {
        K succ = startNode.key;
        while(startNode.left != null) {
            succ = startNode.left.key;
            startNode = startNode.left;
        }
        return succ;
    }


    /**
     * Returns an array containing the keys in a TreeMap, in order.
     * @param kArray        The empty array.
     * @return              The array.
     */
    public K[] keyArray(K[] kArray) {
        keyArray(kArray, root, 0);
        return kArray;
    }

    /**
     * Returns an array containing the keys in a TreeMap, in order.
     * @param kArray        The empty array.
     * @param startNode     The starting TreeNode.
     * @param idx           The index.
     * @return              The array.
     */
    private int keyArray(K[] kArray, TreeNode<K,V> startNode, int idx) {
        if (startNode == null) {
            return idx;
        }

        idx = keyArray(kArray, startNode.left, idx);
        kArray[idx] = startNode.key;
        if (DEBGUG) {
            System.out.println(idx);
            System.out.println(startNode.key);
        }
        idx = keyArray(kArray, startNode.right, idx + 1);

        return idx;
    }
}
