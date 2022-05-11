package edu.unibw.se.hp.queue;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

public interface Queue {
    void enqueue(Object element);

    Object dequeue();

    default Object first() {
        if (size() == 0) throw new EmptyQueueException("Queue is empty!");
        return get(0);
    }

    default void insert(Object element) {
        enqueue(element);
    }

    void remove(Object element);

    default boolean contains(Object element) {
        Iterator<Object> it = iterator();
        while (it.hasNext()) if (Objects.equals(it.next(), element)) return true;
        return false;
    }

    default Object get(int i) {
        if (i >= size() || i < 0) throw new QueueIndexOutOfBoundsException(i);
        Iterator<Object> it = iterator();
        for (int count = 0; count != i; count++) it.next();
        return it.next();
    }

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    void clear();

    String toString();

    Iterator<Object> iterator();

    default Queue filter(Predicate<Object> filter) {
        if (filter == null) return null;
        Queue q = createEmptyQueue();
        Iterator<Object> it = iterator();
        while (it.hasNext()) {
            Object o = it.next();
            if (filter.test(o)) q.insert(o);
        }
        return q;
    }

    Queue createEmptyQueue();
}
