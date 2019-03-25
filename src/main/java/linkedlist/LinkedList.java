package linkedlist;

public class LinkedList {
    Node head;

    class Node {
        int data;
        int index = 0;
        Node next;

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", index=" + index +
                    '}';
        }

        public Node(int data) {
            this.data = data;
        }

        public Node() {
        }
    }

    @Override
    public String toString() {
        return "LinkedList{" +
                "lenght=" + lenght +
                '}' + this.print();
    }

    private int lenght;

    public int getLenght() {
        return lenght;
    }

    private void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public void insert(int data) {
        Node node = new Node();
        node.data = data;
        node.next = null;
        if (head == null) {
            head = node;

        } else {
            Node n = head;
            while (n.next != null) {
                n = n.next;
            }
            n.next = node;
        }
        this.makeIndex();

    }

    void makeIndex() {
        if (head != null) {
            Node n = head;
            int i = 0;
            while (n.next != null) {
                n.index = i;
                n = n.next;
                i++;
            }
            n.index = i;
            this.lenght = i + 1;
        } else this.lenght = 0;
    }

    public void show() {
        if (head != null) {
            Node node = head;
            System.out.println(head);
            while (node.next != null) {

                node = node.next;
                System.out.println(node);
            }
        } else {
            System.out.println(head);
        }

    }

    private String print() {
        String st = "{";
        if (head != null) {
            Node node = head;
            st += (head.data) + ",";
            while (node.next != null) {

                node = node.next;
                st += (node.data) + ",";
            }
        } else {
            st += "}";
        }
        st += "}";
        return st;
    }

    public void insertAtStart(int data) {
        Node node = new Node();
        node.data = data;
        node.next = null;
        if (head == null) {
            head = node;
        } else {
            Node n = head;
            head = node;
            head.next = n;
        }
        this.makeIndex();
    }

    public void insertIntoIndex(int _index, int _data) {
        if (this.lenght >= _index) {
            Node node = new Node(_data);

            Node n = head;
            while (n.next != null) {
                if (n.index == _index - 1) {
                    node.next = (n.next).next;
                    n.next = node;
                    break;
                }
                n = n.next;
            }
        }
        this.makeIndex();
    }

    public void deleteLast() {
        if (this.lenght > 0) {
            Node n = head;
            for (int i = 0; i < this.lenght - 2; i++) {
                n = n.next;
            }
            n.next = null;
        }
    }

    public void deleteAtIndex(int _index) {
        Node node = head;
        if ((this.lenght - 1) > _index) {
            for (int i = 1; i < this.lenght - 2; i++) {

                if (node.index == (_index - 1)) {
                    node.next = (node.next).next;
                    break;
                }
                node = node.next;
            }
        } else if ((this.lenght - 1) == _index) {
            this.deleteLast();
        }
        this.makeIndex();
    }


}

