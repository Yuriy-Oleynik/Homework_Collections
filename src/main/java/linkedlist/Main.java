package linkedlist;

public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.insert(40);

        list.insertAtStart(60);

        list.makeIndex();

        list.insertIntoIndex(2, 720);
        list.show();
        System.out.println(list);

        list.deleteLast();
        list.show();
        System.out.println(list);

        list.deleteAtIndex(2);
        list.show();
        System.out.println(list);



    }
}
