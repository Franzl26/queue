package edu.unibw.se.hp.queue;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

public interface Queue<E> {
    void enqueue(E element);

    E dequeue();

    default E first() {
        if (size() == 0) throw new EmptyQueueException("Queue is empty!");
        return get(0);
    }

    default void insert(E element) {
        enqueue(element);
    }

    void remove(E element);

    default boolean contains(E element) {
        Iterator<E> it = iterator();
        while (it.hasNext()) if (Objects.equals(it.next(), element)) return true;
        return false;
    }

    default E get(int i) {
        if (i >= size() || i < 0) throw new QueueIndexOutOfBoundsException(i);
        Iterator<E> it = iterator();
        for (int count = 0; count != i; count++) it.next();
        return it.next();
    }

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    void clear();

    String toString();

    Iterator<E> iterator();

    default Queue<E> filter(Predicate<E> filter) {
        if (filter == null) return null;
        Queue<E> q = createEmptyQueue();
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            E o = it.next();
            if (filter.test(o)) q.insert(o);
        }
        return q;
    }

    Queue<E> createEmptyQueue();
}
