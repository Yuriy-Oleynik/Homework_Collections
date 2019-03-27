package hashset;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HashSet<T> implements Set<T> {
    private List<T>[] table;
    private int size;

    public HashSet(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "capacity must be a positive, non-zero value! Provided: " + capacity);
        }

        List<T>[] t = new LinkedList[capacity];
        table = t;
        size = 0;

    }


    public boolean add(T elem) {
        if (contains(elem))
            return false;
        int hashFunction = Math.abs(elem.hashCode() % table.length);
        if (table[hashFunction] == null) {
            List<T> bucket = new LinkedList<>();
            bucket.add(elem);
            table[hashFunction] = bucket;
        } else
            table[hashFunction].add(elem);
        size++;
        return true;
    }


    public boolean remove(T elem) {
        if (!contains(elem))
            return false;
        int hashFunction = Math.abs(elem.hashCode() % table.length);
        if (table[hashFunction].size() > 1)
            table[hashFunction].remove(hashFunction);
        else
            table[hashFunction] = null;
        size--;
        return true;
    }


    public boolean contains(T elem) {
        int hashFunction = Math.abs(elem.hashCode() % table.length);

        if (table[hashFunction] == null)
            return false;
        else
            return table[hashFunction].contains(elem);
    }


    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return Arrays.toString(table);
    }
}
