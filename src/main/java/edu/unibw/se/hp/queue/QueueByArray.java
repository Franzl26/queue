package edu.unibw.se.hp.queue;

import java.util.Iterator;
import java.util.Objects;

public class QueueByArray implements Queue {
    private Object[] elements = new Object[16];
    private int count = 0;

    @Override
    public void enqueue(Object element) {
        if (size() == elements.length) {
            Object[] temp = elements;
            elements = new Object[2 * temp.length];
            System.arraycopy(temp, 0, elements, 0, temp.length);
        }
        elements[count++] = element;
    }

    @Override
    public Object dequeue() {
        if (isEmpty()) throw new EmptyQueueException("Queue is empty!");
        Object temp = elements[0];
        System.arraycopy(elements, 1, elements, 0, count - 1);
        elements[--count] = null;
        return temp;
    }

    @Override
    public void remove(Object element) {
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

    public Object get(int i) {
        if (count == 0 || i < 0 || i > count) throw new QueueIndexOutOfBoundsException(i);
        return elements[i];
    }

    @Override
    public void clear() {
        elements = new Object[16];
        count = 0;
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
        return new ArrayIterator();
    }

    @Override
    public Queue createEmptyQueue() {
        return new QueueByArray();
    }

    private class ArrayIterator implements Iterator<Object> {
        private int cnt = 0;

        @Override
        public boolean hasNext() {
            return cnt != count;
        }

        @Override
        public Object next() {
            return elements[cnt++];
        }
    }
}
