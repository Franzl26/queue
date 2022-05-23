package edu.unibw.se.hp.queue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class QueueWithPriorityTest extends QueueTest{
    Queue<Object> createQueue() {
        return new QueueWithPriority<>();
    }

    @Test
    @Disabled
    void queueInsertMultiple1() {

    }

    @Test
    void queueRemove() {
        Queue<Object> queue = new QueueWithPriority<>();
        Object[] arr = {144, 0, 26, -145, 4, 144, 4, 26};
        Object[] rem = {26, 144, 4, 0, -145};
        int[] size = {6, 4, 2, 1, 0};

        for (Object o : arr) queue.insert(o);
        for (int i = 0; i < rem.length; i++) {
            queue.remove(rem[i]);
            assertFalse(queue.contains(rem[i]));
            assertEquals(size[i], queue.size());
        }
    }

    @Test
    @Override
    void createEmptyQueue() {
        Queue<Object> queue = new QueueWithPriority<>();
        queue.insert(5);
        queue.enqueue(7);
        Queue<Object> newQueue = queue.createEmptyQueue();
        assertEquals(0, newQueue.size());
        assertTrue(newQueue.isEmpty());
    }

    @Test
    @Override
    void queueClear() {
        Queue<Object> queue = new QueueWithPriority<>();
        Object[] arr = {144, -145, 4};
        Arrays.stream(arr).forEach(queue::insert);

        queue.clear();
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    @Disabled
    void queueInsertNull() {

    }

    @Test
    @Disabled
    void queueIterator() {

    }

    @Test
    @Disabled
    void queueRemove2(){

    }

    @Test
    @Disabled
    void queueToString() {

    }
}
