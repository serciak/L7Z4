package com.company;

public class Main {

    public static void main(String[] args) throws DuplicateKeyException {
        DictionaryLinkedLists<Integer, Integer> dict = new DictionaryLinkedLists<>(10);

        dict.put(1, 3);
        dict.put(2, 51);
        dict.put(3, 4);
        dict.put(4, 12);
        dict.put(5, 1);
        dict.put(6, 51);
        dict.put(7, 41);
        dict.put(8, 11);
        dict.put(9, 1);
        dict.put(10, 2);
        dict.put(11, 123);
        dict.put(12, 3);
        dict.put(23, 51);
        dict.put(35, 4);
        dict.put(44, 12);
        dict.put(51, 1);
        dict.put(67, 51);
        dict.put(75, 41);
        dict.put(82, 11);
        dict.put(80, 1);

        System.out.println(dict.toStringWithNulls());
    }
}
