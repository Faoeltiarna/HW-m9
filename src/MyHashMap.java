public class MyHashMap<K, V> {

    Node<K, V>[] buckets = new Node[16];
    int size = 0;

    public int getKeyIndex(K key) {
        return Math.floorMod(key.hashCode(), buckets.length);
    }

    public void put(K key, V value) {
        int index = getKeyIndex(key);
        if (buckets[index] == null) {
            Node<K, V> node1 = new Node<>(key, value);
            buckets[index] = node1;
            size++;
        } else {
            Node<K, V> tempNode = buckets[index];
            while (tempNode.nextNode != null) {
                if (isKeyEquals(key, tempNode)) {
                    tempNode.value = value;
                    return;
                }
                tempNode = tempNode.nextNode;
            }
            tempNode.nextNode = new Node<>(key, value);
            size++;
        }
    }

    public void remove(K key) {
        int index = getKeyIndex(key);
        Node<K, V> tempNode = buckets[index];
        Node<K, V> tempNodeNext = tempNode.nextNode;
        if (isKeyEquals(key, tempNode) && tempNode.nextNode == null) {
            buckets[index] = null;
            return;
        }
        while (tempNodeNext.nextNode != null) {
            if (isKeyEquals(key, tempNodeNext)) {
                tempNode.nextNode = tempNodeNext.nextNode;
            }
            tempNode = tempNode.nextNode;
            tempNodeNext = tempNodeNext.nextNode;
        }
        if (isKeyEquals(key, tempNode)) {
            tempNode.nextNode = null;
        }
    }

    public int size() {
        return size;
    }

    public void clear() {
        size = 0;
        for (Node<K, V> node : buckets) {
            node = null;
        }
    }

    public V get(K key) {
        int index = getKeyIndex(key);
        Node<K, V> tempNode = buckets[index];
        while (tempNode.nextNode != null) {
            if (isKeyEquals(key, tempNode)) {
                return tempNode.value;
            }
            tempNode = tempNode.nextNode;
        }

            if (isKeyEquals(key, tempNode)) {
                return tempNode.value;
            } else return null;

        }

    private boolean isKeyEquals(K key, Node<K, V> tempNode) {
        return key.hashCode() == tempNode.hash && key.equals(tempNode.key);
    }

    private static class Node<K, V> {
        Node<K, V> nextNode;
        K key;
        V value;
        int hash;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            hash = key.hashCode();
        }
    }
}
