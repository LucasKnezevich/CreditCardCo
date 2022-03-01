/**
 * Search tree class.
 * @param <E>       The type.
 */
public class SearchTree<E extends Comparable<E>> {
    /**
     * The root node.
     */
    private Node<E> root;

    /**
     * Size of the search tree.
     */
    private int size;

    /**
     * Full constructor
     */
    public SearchTree() {
        root = null;
    }

    /**
     * Method for clearing the search tree.
     */
    public void clear() {
        root = null;
    }

    /**
     * Retrieves the size of the search tree.
     * @return      The size of the search tree.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Method for adding a node to a search tree.
     * @param data      The data to be added.
     */
    public void add(E data) {
        if (data == null) {
            throw new IllegalArgumentException("Data/value cannot be null.");
        } else {
            root = add(data, root);
            size++;
        }
    }

    /**
     * Method for adding a node to a search tree.
     * @param data          The data to be added.
     * @param startNode     The starting node.
     * @return
     */
    private Node<E> add(E data, Node<E> startNode) {
        if (startNode == null) {
            startNode = new Node<>(data);
        } else if (data.compareTo(startNode.data) <= 0) {
            startNode.left = add(data, startNode.left);
        } else {
            startNode.right = add(data, startNode.right);
        }
        return startNode;
    }

    /**
     * Method for checking to see if a value is present in a search tree.
     * @param data      The value being looked for.
     * @return          Whether the value is present in the search tree.
     */
    public boolean contains(E data) {
        if (data == null) {
            throw new IllegalArgumentException("Data/value cannot be null.");
        } else {
            return contains(data, root);
        }
    }

    /**
     * Method for checking whether a value is present in a search tree.
     * @param data          The value being looked for.
     * @param startNode     The Node to start looking at.
     * @return              Whether the value is present in the search tree.
     */
    private boolean contains(E data, Node<E> startNode) {
        if (root == null) {
            return false;
        } else if(data.compareTo(startNode.data) == 0) {
            return true;
        } else if (data.compareTo(startNode.data) < 0) {
            return contains(data, startNode.left);
        } else {
            return contains(data, startNode.right);
        }
    }

    /**
     * Method for printing the nodes in order.
     */
    public void printInOrder() {
        printInOrder(root);
    }

    /**
     * Method for printing the nodes in the order.
     * @param root      The current node being printed.
     */
    private void printInOrder(Node<E> root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.println(root.data);
            printInOrder(root.right);
        }
    }

    /**
     * Represents a Node.
     * @param <T>   The type of Node.
     */
    private static class Node<T> {
        public T data;
        public Node<T> left;
        public Node<T> right;

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

    }

}
