public class MyLinkedList<K> {

    private final Node<K> firstNode = new Node<K>();
    private final Node<K> lastNode = new Node<K>();

    /**
     * The add method adds a value to the LinkedList
     */
    public void add(K value) {
        // we check whether there are nodes in the LinkedList
        if (firstNode.nextNode == null) {
            firstNode.nextNode = new Node<K>(value);
            lastNode.prevNode = firstNode.nextNode;
        } else {
            Node<K> currentNode = lastNode.prevNode;

//            while (temp.nextNode != null){
//                temp = temp.nextNode;
//            }

            // we create the next node in linkedList
            currentNode.nextNode = new Node<K>(value);
            currentNode.nextNode.index = currentNode.index + 1;
            currentNode.nextNode.prevNode = currentNode;
            lastNode.prevNode = currentNode.nextNode;
        }
    }

    public void clear() {
        firstNode.nextNode = null;
        lastNode.prevNode = null;
    }

    public int size() {
        if (lastNode.prevNode != null) return lastNode.prevNode.index + 1;
        else return 0;
    }

    /**
     * method that searches for a node by index
     */
    private Node<K> findNode(int index) {
        // the first condition determines whether there is at least one value in our LinkedList,
        // the second condition checks whether the index we are looking for is not greater than
        // the index of the last node in the LinkedList,
        // the third condition checks whether the index does not have a negative value
        if (lastNode.prevNode == null || index > lastNode.prevNode.index || index < 0)
            throw new IndexOutOfBoundsException();
        // create a buffer node and assign a variable to which we assign a value
        // equal to half the length of the LinkedList
        Node<K> temp;
        int halfListLength = lastNode.prevNode.index / 2;
        // check in which range the index of the node is located
        // and determine from which side we will start the search
        if (index < halfListLength) {
            temp = firstNode.nextNode;
            while (index != temp.index) {
                temp = temp.nextNode;
            }
        } else {
            temp = lastNode.prevNode;
            while (index != temp.index) {
                temp = temp.prevNode;
            }
        }
        return temp;
    }

    /**
     * returns the value of the node at the given index
     */
    public K get(int index) {
        Node<K> searchedNode = findNode(index);
        return searchedNode.value;
    }

    /**
     * method that deletes a node by index
     */
    public void remove(int index) {
        //search for the node to be deleted by the specified index
        Node<K> searchedNode = findNode(index);
        //creation of a buffer node to which the next node after the deleted node is assigned
        Node<K> tempOne = searchedNode.nextNode;

        //check whether the node is not the first or the last in the list
        if (searchedNode.prevNode != null && searchedNode.nextNode != null) {
            //replacing links in the previous and next nodes relative to the node being deleted
            searchedNode.prevNode.nextNode = searchedNode.nextNode;
            searchedNode.nextNode.prevNode = searchedNode.prevNode;

            incrementIndex(tempOne, index);
        }
        //deleting the node if it is the first in the list
        else if (searchedNode.prevNode == null) {
            searchedNode.nextNode.prevNode = null;
            firstNode.nextNode = searchedNode.nextNode;

            incrementIndex(tempOne, index);
        }
        //deleting a node if it is the last one in the list
        else {
            lastNode.prevNode = searchedNode.prevNode;
        }
    }

    /**
     * method that redefines node indexes after deleting the searched node
     */
    private void incrementIndex(Node <K> tempOne, int index) {
        while (tempOne != null) {
            tempOne.index = index;
            tempOne = tempOne.nextNode;
            index++;
        }
    }

    private static class Node<T> {
        Node<T> nextNode = null;
        Node<T> prevNode = null;
        T value = null;
        int index = 0;

        public Node(T value) {
            this.value = value;
        }

        public Node() {
        }
    }
}