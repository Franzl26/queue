package edu.unibw.se.hp.queue;

import java.util.Iterator;
import java.util.Objects;

public class QueueByArray<E> implements Queue<E> {
    @SuppressWarnings("unchecked")
    private E[] elements = (E[]) new Object[16];
    private int count = 0;

    @Override
    @SuppressWarnings("unchecked")
    public void enqueue(E element) {
        if (size() == elements.length) {
            E[] temp = elements;
            elements = (E[]) new Object[2 * temp.length];
            System.arraycopy(temp, 0, elements, 0, temp.length);
        }
        elements[count++] = element;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) throw new EmptyQueueException("Queue is empty!");
        E temp = elements[0];
        System.arraycopy(elements, 1, elements, 0, count - 1);
        elements[--count] = null;
        return temp;
    }

    @Override
    public void remove(E element) {
        int alt = 0;
        int cnt;
        int newcount = count;
        for (cnt = 0; cnt < count; cnt++) {
            if (Objects.equals(elements[cnt], element)) {
                newcount--;
                continue;
            }
            elements[alt++] = elements[cnt];
        }
        for (; alt < count; alt++) elements[alt] = null;
        count = newcount;
    }

    @Override
    public int size() {
        return count;
    }

    public E get(int i) {
        if (count == 0 || i < 0 || i > count) throw new QueueIndexOutOfBoundsException(i);
        return elements[i];
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        elements = (E[]) new Object[16];
        count = 0;
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
        return new ArrayIterator();
    }

    @Override
    public Queue<E> createEmptyQueue() {
        return new QueueByArray<>();
    }

    private class ArrayIterator implements Iterator<E> {
        private int cnt = 0;

        @Override
        public boolean hasNext() {
            return cnt != count;
        }

        @Override
        public E next() {
            return elements[cnt++];
        }
    }
}
