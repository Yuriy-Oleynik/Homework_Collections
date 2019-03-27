package hashmap;


public class HashMap<K, V> implements Map<K, V> {
    Node<K, V>[] table = null;
    int size;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    @Override
    public V put(K k, V v) {
        if (table == null) {
            table = new Node[DEFAULT_INITIAL_CAPACITY];
        }

        if (k == null) {
            Node<K, V> nullNode = table[0];
            while (nullNode != null) {
                if (nullNode.getKey().equals(k) || nullNode.getKey() == k) {
                    return nullNode.setValue(v);
                } else {
                    if (nullNode.next == null) {
                        table[0] = new Node<K, V>(k, v, table[0]);
                        size++;
                    }
                }
                nullNode = nullNode.next;
            }
        }


        if (size > DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR) {
            reSize();
        }

        int index = getIndex(k, table.length);
        Node<K, V> node = table[index];
        if (node == null) {
            node = new Node<K, V>(k, v, null);
            size++;
        } else {
            Node<K, V> newNode = node;
            while (newNode != null) {
                if (newNode.getKey().equals(k) || newNode.getKey() == k) {
                    return newNode.setValue(v);
                } else {
                    if (newNode.next == null) {
                        node = new Node<K, V>(k, v, node);
                        size++;
                    }
                }
                newNode = newNode.next;
            }

        }
        table[index] = node;
        return null;
    }

    private void reSize() {
        Node<K, V>[] newTable = new Node[DEFAULT_INITIAL_CAPACITY << 1];
        for (Node node : table) {
            Node<K, V> oldNode = node;
            while (oldNode != null) {
                node = null;
                K oldK = oldNode.k;
                int newIndex = getIndex(oldK, newTable.length);
                Node<K, V> oldNext = oldNode.next;
                oldNode.next = newTable[newIndex];
                newTable[newIndex] = oldNode;
                oldNode = oldNext;
            }
        }
        table = newTable;
        DEFAULT_INITIAL_CAPACITY = newTable.length;
        newTable = null;
    }

    public void print() {
        for (int i = 0; i < table.length; i++) {
            Node<K, V> node = table[i];
            while (node != null) {
                System.out.println("key: " + node.getKey() + " value: " + node.getValue());
                node = node.next;
            }
            System.out.println();
        }
    }

    public void printByMyself() {
        int i = 0;
        for (Node node : table) {
            while (node != null) {
                System.out.println("key: " + node.k + " value: " + node.v);
                node = node.next;
            }
            System.out.println();
        }
    }

    public int getIndex(K k, int length) {
        int hashcode = k.hashCode();
        return hashcode % length;
    }


    @Override
    public V get(K k) {
        Node<K, V> node = table[getIndex(k, DEFAULT_INITIAL_CAPACITY)];
        Node<K, V> returnNode = getNode(node, k);

        return returnNode == null ? null : returnNode.getValue();
    }

    private Node<K, V> getNode(Node<K, V> node, K k) {
        while (node != null) {
            if (node.getKey().equals(k)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }


    class Node<K, V> implements Entry<K, V> {
        private K k;
        private V v;
        private Node<K, V> next;

        public Node(K k, V v, Node<K, V> next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        @Override
        public K getKey() {
            return this.k;
        }

        @Override
        public V getValue() {
            return this.v;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.v;
            this.v = value;
            return oldValue;
        }
    }
}
