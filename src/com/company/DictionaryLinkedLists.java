package com.company;

import java.util.Iterator;

public class DictionaryLinkedLists<TKey, TValue> {

    class HashNode<T1, T2> {

        public T1 key;
        public T2 value;

        public HashNode(T1 key, T2 value) {
            this.key = key;
            this.value = value;
        }

        public String toString() {
            return "(" + key + ", " + value + ")";
        }
    }

    private TwoWayLinkedList<HashNode<TKey, TValue>>[] array;
    private final int CAPACITY;
    private int elemAmount;

    public DictionaryLinkedLists(int CAPACITY) {
        this.CAPACITY = CAPACITY;
        array = new TwoWayLinkedList[CAPACITY];
        fill();
        elemAmount = 0;
    }

    public void put(TKey key, TValue value) throws DuplicateKeyException {
        HashNode<TKey, TValue> node = new HashNode<>(key, value);

        if(contains(key))
            throw new DuplicateKeyException();

        array[hashIndex(key)].add(node);
        elemAmount++;
    }

    public TValue get(TKey key) {
        HashNode<TKey, TValue> node = findInLinkedList(array[hashIndex(key)], key);

        if(node != null)
            return node.value;
        return null;
    }

    public TValue remove(TKey key) {
        HashNode<TKey, TValue> node = findInLinkedList(array[hashIndex(key)], key);

        if(node != null) {
            array[hashIndex(key)].remove(node);
            --elemAmount;
            return node.value;
        }
        return null;
    }

    public boolean contains(TKey key) {
        int idx = hashIndex(key);

        if(findInLinkedList(array[idx], key) == null)
            return false;
        return true;
    }

    public int size() {
        return elemAmount;
    }

    private HashNode<TKey, TValue> findInLinkedList(TwoWayLinkedList<HashNode<TKey, TValue>> linkedList, TKey key) {
        Iterator<HashNode<TKey, TValue>> iterator = linkedList.iterator();
        HashNode<TKey, TValue> node;

        while(iterator.hasNext()) {
            node = iterator.next();
            if(node.key.equals(key))
                return node;
        }

        return null;
    }

    private void fill() {
        for(int i = 0; i < CAPACITY; i++)
            array[i] = new TwoWayLinkedList<>();
    }

    private int hashIndex(TKey key) {
        return hash(key) % CAPACITY;
    }

    private int hash(TKey key) {
        if(key instanceof Integer)
            return 13 * (int) key ^ ((int) key >> 31);
        return 13 * key.hashCode() ^ (key.hashCode() >> 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(TwoWayLinkedList l : array) {
            if(!l.isEmpty())
                sb.append(l + "\n");
        }
        sb.replace(sb.length()-2, sb.length()-1, "]");
        return sb.toString();
    }

    public String toStringWithNulls() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(TwoWayLinkedList l : array) {
            sb.append(l + "\n");
        }
        sb.replace(sb.length()-2, sb.length()-1, "]");
        return sb.toString();
    }
}
