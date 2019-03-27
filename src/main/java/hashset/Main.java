package hashset;

public class Main {
    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>(5);
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);

        System.out.println(set.size());

        System.out.println(set);

        set.remove(3);

        System.out.println(set.size());
    }
}
