package edu.unibw.se.hp.queue;

import java.util.Iterator;
import java.util.Objects;

public class QueueByList<E> implements Queue<E> {
    private int size = 0;
    private ListElement<E> head;
    private ListElement<E> tail;

    @Override
    public void enqueue(E element) {
        if (isEmpty()) {
            head = new ListElement<>(element, null);
            tail = head;
        } else {
            tail.next = new ListElement<>(element, null);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (head == null) throw new EmptyQueueException("Queue is empty!");
        E temp = head.element;
        head = head.next;
        if (size == 1) tail = null;
        size--;
        return temp;
    }

    @Override
    public void remove(E element) {
        ListElement<E> newL = null;
        ListElement<E> temp = head;
        head = null;
        size = 0;
        while (temp != null) {
            if (Objects.equals(element, temp.element)) {
                temp = temp.next;
                continue;
            }
            size++;
            if (head == null) {
                head = temp;
            } else {
                newL.next = temp;
            }
            newL = temp;
            temp = temp.next;
            newL.next = null;
        }
        tail = newL;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            E o = it.next();
            if (o != null) s.append(o);
            else s.append("null");
            if (it.hasNext()) s.append(" <- ");
        }
        return s.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    @Override
    public Queue<E> createEmptyQueue() {
        return new QueueByList<>();
    }

    private class ListIterator implements Iterator<E> {
        private ListElement<E> iter =  head;

        @Override
        public boolean hasNext() {
            return iter != null;
        }

        @Override
        public E next() {
            E temp = iter.element;
            iter = iter.next;
            return temp;
        }
    }

    private static class ListElement<E> {
        private final E element;
        private ListElement<E> next;

        private ListElement(E element, ListElement<E> next) {
            this.element = element;
            this.next = next;
        }
    }
}
