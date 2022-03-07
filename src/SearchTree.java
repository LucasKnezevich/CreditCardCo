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
     * String used in generateInOrderString methods.
     */
    private String str;

    /**
     * Represents a Node.
     * @param <T>   The type of Node.
     */
    private static class Node<T> {
        /**
         * The data of the Node
         */
        public T data;

        /**
         * The left child of the Node
         */
        public Node<T> left;

        /**
         * The right child of the Node
         */
        public Node<T> right;

        /**
         * Constructor for a Node
         * @param data      The data of the Node
         */
        public Node(T data) {
            this.data = data;
        }

    }

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
        size = 0;
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
     * @param data      The data to be added, cannot be null.
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
     * @return              The starting node.
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
     * @param data      The value being looked for.  Cannot be null.
     * @return          Whether the value is present in the search tree.
     */
    public boolean contains(E data) {
        return contains(data, root);
    }

    /**
     * Method for checking whether a value is present in a search tree.
     * @param data          The value being looked for.
     * @param startNode     The Node to start looking at.
     * @return              Whether the value is present in the search tree.
     */
    private boolean contains(E data, Node<E> startNode) {
        if (startNode == null) {
            return false;
        } else if(data.compareTo(startNode.data) == 0) {
            return true;
        } else if (data.compareTo(startNode.data) < 0) {
            return contains(data, startNode.left);
        } else {
            return contains(data, startNode.right);
        }
    }

//    /**
//     * Method for printing the nodes in order.
//     */
//    public void printInOrder() {
//        printInOrder(root);
//    }
//
//    /**
//     * Method for printing the nodes in the order.
//     * @param root      The current node being printed.
//     */
//    private void printInOrder(Node<E> root) {
//        if (root != null) {
//            printInOrder(root.left);
//            System.out.println(root.data);
//            printInOrder(root.right);
//        }
//    }

    /**
     * Method for printing the nodes in order.
     * @return The in order String.
     */
    public String generateInOrderString() {
        str = "";
        generateInOrderString(root);
        return str;
    }

    /**
     * Method for printing the nodes in the order.
     * @param root      The current node being printed.
     */
    private void generateInOrderString(Node<E> root) {
        if (root != null) {
            generateInOrderString(root.left);
            str = str.concat(root.data.toString() + "\n");
            generateInOrderString(root.right);
        }
    }

    /**
     * Method for removing a Node
     * @param data      The data of the Node to be deleted
     */
    public void delete(E data) {
        root = delete(root, data);
    }

    /**
     * Method for removing a Node
     * @param startNode     The starting Node
     * @param data          The data of the Node to be deleted
     * @return              A Node
     */
    private Node<E> delete(Node<E> startNode, E data) {
        if (startNode == null) {
            return startNode;
        }
        if (data.compareTo(startNode.data) < 0) {
            startNode.left = delete(startNode.left, data);
        } else if (data.compareTo(startNode.data) > 0) {
            startNode.right = delete(startNode.right, data);
        } else {
            if (startNode.left == null) {
                return startNode.right;
            } else if (startNode.right == null) {
                return startNode.left;
            }
            startNode.data = deleteHelper(startNode.right);
            startNode.right = delete(startNode.right, startNode.data);
            // This isn't working right
            size--;
        }
        return startNode;
    }

    /**
     * Helper method for deleting a node
     * @param startNode     The starting Node
     * @return              The data of the Node.
     */
    private E deleteHelper(Node<E> startNode) {
        E data = startNode.data;
        while (startNode.left != null) {
            data = startNode.left.data;
            startNode = startNode.left;
        }
        return data;
    }
}
