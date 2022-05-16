package edu.unibw.se.hp.queue;

import java.util.Iterator;
import java.util.PriorityQueue;

public class QueueWithPriority<E> implements Queue<E> {
    private final PriorityQueue<E> queue = new PriorityQueue<>();

    @Override
    public void enqueue(E element) {
        if (!queue.contains(element)) queue.offer(element);
    }

    @Override
    public E dequeue() {
        if (queue.size() == 0) throw new EmptyQueueException("Die Queue ist leer");
        return queue.poll();
    }

    @Override
    public void remove(E element) {
        queue.remove(element);
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public Iterator<E> iterator() {
        return queue.iterator();
    }

    @Override
    public Queue<E> createEmptyQueue() {
        return new QueueWithPriority<>();
    }
}
