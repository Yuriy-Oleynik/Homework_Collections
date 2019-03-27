package hashmap;

public interface Map<K,V> {
    public V put(K k,V v);

    public V get(K k);

    //包含元素的个数
    public int size();


    //entry的作用==Node结点
    interface Entry<K,V>{
        K getKey();

        V getValue();

        V setValue(V value);
    }
}