package edu.unibw.se.hp.queue;

import java.util.Iterator;
import java.util.Objects;

public class QueueByList implements Queue {
    private int size = 0;
    private ListElement head;
    private ListElement tail;

    @Override
    public void enqueue(Object element) {
        if (isEmpty()) {
            head = new ListElement(element, null);
            tail = head;
        } else {
            tail.next = new ListElement(element, null);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public Object dequeue() {
        if (head == null) throw new EmptyQueueException("Queue is empty!");
        Object temp = head.element;
        head = head.next;
        if (size == 1) tail = null;
        size--;
        return temp;
    }

    @Override
    public void remove(Object element) {
        ListElement newL = null;
        ListElement temp = head;
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
        Iterator<Object> it = iterator();
        while (it.hasNext()) {
            Object o = it.next();
            if (o != null) s.append(o);
            else s.append("null");
            if (it.hasNext()) s.append(" <- ");
        }
        return s.toString();
    }

    @Override
    public Iterator<Object> iterator() {
        return new ListIterator();
    }

    @Override
    public Queue createEmptyQueue() {
        return new QueueByList();
    }

    private class ListIterator implements Iterator<Object> {
        private ListElement iter = head;

        @Override
        public boolean hasNext() {
            return iter != null;
        }

        @Override
        public Object next() {
            Object temp = iter.element;
            iter = iter.next;
            return temp;
        }
    }

    private static class ListElement {
        private final Object element;
        private ListElement next;

        private ListElement(Object element, ListElement next) {
            this.element = element;
            this.next = next;
        }
    }
}
