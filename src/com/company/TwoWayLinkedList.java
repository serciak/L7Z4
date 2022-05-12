package com.company;

import java.util.Iterator;

public class TwoWayLinkedList<E> {

    private class Element {
        private E value; 
        private Element next;
        private Element prev;

        @Override
        public String toString() {
            return value.toString();
        }

        public E getValue() {
            return value;
        }
        public void setValue(E value) {
            this.value = value;
        }
        public Element getNext() {
            return next;
        }
        public void setNext(Element next) {
            this.next = next;
        }
        public Element getPrev() {
            return prev;
        }
        public void setPrev(Element prev) {
            this.prev = prev;
        }
        Element(E data){
            this.value=data;
        }
        public void insertAfter(Element elem){
            elem.setNext(this.getNext());
            elem.setPrev(this);
            this.getNext().setPrev(elem);
            this.setNext(elem);
        }
        public void insertBefore(Element elem){
            elem.setNext(this);
            elem.setPrev(this.getPrev());
            this.getPrev().setNext(elem);
            this.setPrev(elem);
        }
        public void remove(){
            this.getNext().setPrev(this.getPrev());
            this.getPrev().setNext(this.getNext());
        }
    }

    Element sentinel;

    public TwoWayLinkedList() {
        sentinel=new Element(null);
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);
    }

    @Override
    public String toString() {

        if(isEmpty())
            return "[]";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        if(!isEmpty()) {
            Element current = sentinel.getNext();
            while(current != sentinel) {
                stringBuilder.append(current + ", ");
                current = current.getNext();
            }
        }
        stringBuilder.replace(stringBuilder.length()-2, stringBuilder.length()-1, "]");
        return stringBuilder.toString();
    }

    private Element getElement(int index){
        if(index<0)
            throw new IndexOutOfBoundsException();
        Element elem=sentinel.getNext();
        int counter=0;
        while(elem!=sentinel && counter<index) {
            counter++;
            elem=elem.getNext();
        }
        if(elem==sentinel)
            throw new IndexOutOfBoundsException();
        return elem;
    }

    private Element getElement(E value){
        Element elem=sentinel.getNext();

        while(elem!=sentinel && !value.equals(elem.getValue())) {
            elem=elem.getNext();
        }
        if(elem==sentinel)
            return null;
        return elem;
    }

    public boolean isEmpty() {
        return sentinel.getNext()==sentinel;
    }

    public void clear() {
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);
    }

    public boolean contains(E value) {
        return indexOf(value)!=-1;
    }

    public E get(int index) {
        Element elem=getElement(index);
        return elem.getValue();
    }
    public E set(int index, E value) {
        Element elem=getElement(index);
        E retValue=elem.getValue();
        elem.setValue(value);
        return retValue;

    }

    public boolean add(E value) {
        Element newElem=new Element(value);
        sentinel.insertBefore(newElem);
        return true;
    }

    public boolean add(int index, E value) {
        Element newElem=new Element(value);

        if(index==0) {
            sentinel.insertAfter(newElem);
        } else {
        Element elem=getElement(index-1);
        elem.insertAfter(newElem);
        }
        return true;
    }

    public int indexOf(E value) {
        Element elem=sentinel.getNext();
        int counter=0;
        while(elem!=sentinel && !elem.getValue().equals(value)) {
            counter++;
            elem=elem.getNext();
        }

        if(elem==sentinel) {
            return -1;
        }
        return counter;
    }

    public E remove(int index) {
        Element elem=getElement(index);
        elem.remove();
        return elem.getValue();
    }

    public boolean remove(E value) {
        Element elem=getElement(value);
        if(elem==null)
            return false;
        elem.remove();
        return true;
    }

    public int size() {
        Element elem=sentinel.getNext();
        int counter=0;
        while(elem!=sentinel){
            counter++;
            elem = elem.getNext();
        }
        return counter;
    }

    public Iterator<E> iterator() { return new TWIterator();}

    private class TWIterator implements Iterator<E>{
        Element _current = sentinel;

        public boolean hasNext() {
            return _current.getNext() != sentinel;
        }
        public E next() {
            _current=_current.getNext(); return _current.getValue();
        }
    }

    public void insertListAtIndex(int index, TwoWayLinkedList<E> list) {
        if(size() - 1 >= index) {
            Element temp = getElement(index);

            temp.getPrev().setNext(list.sentinel.getNext());
            temp.setPrev(list.sentinel.getPrev());
            list.sentinel.getPrev().setNext(temp);
        }
    }

    public void insertListAtElement(E elem, TwoWayLinkedList<E> list) {
        if(contains(elem)) {
            Element temp = getElement(indexOf(elem));

            temp.getPrev().setNext(list.sentinel.getNext());
            temp.setPrev(list.sentinel.getPrev());
            list.sentinel.getPrev().setNext(temp);
        }
    }

    public void insertList(TwoWayLinkedList<E> list) {
        if(!list.isEmpty()) {
            sentinel.getPrev().setNext(list.sentinel.getNext());
            sentinel.setPrev(list.sentinel.getPrev());
            list.sentinel.getPrev().setNext(sentinel);
        }
    }
}
