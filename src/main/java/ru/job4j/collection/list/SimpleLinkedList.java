package ru.job4j.collection.list;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {
    private int size = 0;
    private Node<E> first;
    private Node<E> last;
    private int modCount;

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E value, Node<E> next) {
            this.item = value;
            this.next = next;
        }

        public E getItem() {
            return item;
        }
    }

    @Override
    public void add(E value) {
        Node<E> l = last;
        last = new Node(value, null);
        if (first == null) {
            first = last;
        } else {
            l.next = last;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl = first;
        for (int i = 0; i < index; i++) {
            rsl = rsl.next;
        }
        return rsl.getItem();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            final int expectedModCount = modCount;
            Node<E> current = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E element = current.getItem();
                current = current.next;
                return element;
            }
        };
    }
}
