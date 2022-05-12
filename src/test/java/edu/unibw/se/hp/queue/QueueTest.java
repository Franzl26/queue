package edu.unibw.se.hp.queue;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("QueueTest")
abstract class QueueTest {
    private Queue<Object> queue = null;

    abstract Queue<Object> createQueue();

    @BeforeEach
    void beforeEachTest() {
        queue = createQueue();
    }

    @Test
    void newQueueEmptySize0() {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    void emptyQueueException() {
        assertThrows(EmptyQueueException.class, () -> queue.first());
        assertThrows(EmptyQueueException.class, () -> queue.dequeue());

    }

    @Test
    void queueIndexOutOfBoundsException() {
        assertThrows(QueueIndexOutOfBoundsException.class, () -> queue.get(0));
        queue.insert(5);
        assertThrows(QueueIndexOutOfBoundsException.class, () -> queue.get(-1));
        assertThrows(QueueIndexOutOfBoundsException.class, () -> queue.get(2));
    }

    @Test
    void queueInsert144() {
        queue.insert(144);
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
        assertTrue(queue.contains(144));
        assertEquals(144, queue.first());
        assertEquals(144, queue.get(0));
    }

    @Test
    void queueInsertNull() {
        queue.insert(null);
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
        assertTrue(queue.contains(null));
        assertNull(queue.first());
        assertNull(queue.get(0));
    }

    @Test
    void queueInsertMultiple1() {
        Object[] arr = {144, null, "HP", -145, 4.5};
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) queue.insert(arr[i]);
            else queue.enqueue(arr[i]);

            assertFalse(queue.isEmpty());
            assertEquals(i + 1, queue.size());
            assertTrue(queue.contains(arr[i]));
            assertEquals(arr[0], queue.first());
            assertEquals(arr[i], queue.get(i));
        }
    }

    @Test
    void queueClear() {
        Object[] arr = {144, null, "HP", -145, 4.5};
        Arrays.stream(arr).forEach(o -> queue.insert(o));

        queue.clear();
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    void queueInsertMany() {
        int count = 100;
        for (int i = 0; i < count; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < count; i++) {
            assertEquals(count - i, queue.size());
            assertEquals(i, queue.dequeue());
            assertEquals(count - i - 1, queue.size());
        }
    }

    @Test
    void queueRemove() {
        Object[] arr = {144, null, "HP", -145, 4.5, 144, 4.5, null};
        Object[] rem = {"HP", 144, 4.5, null, -145};
        int[] size = {7, 5, 3, 1, 0};

        for (Object o : arr) queue.insert(o);
        for (int i = 0; i < rem.length; i++) {
            queue.remove(rem[i]);
            assertFalse(queue.contains(rem[i]));
            assertEquals(size[i], queue.size());
        }
    }

    @Test
    void queueRemove2() {
        queue.insert(4);
        queue.insert(4);
        queue.insert(40);
        queue.insert(44);
        queue.insert(44);
        queue.insert(44);
        queue.insert(43);
        queue.insert(5);
        queue.insert(5);
        assertEquals(9, queue.size());

        queue.remove(44);
        assertEquals(6, queue.size());
        assertFalse(queue.contains(44));

        queue.remove(4);
        assertEquals(4, queue.size());
        assertFalse(queue.contains(4));

        queue.remove(5);
        assertEquals(2, queue.size());
        assertFalse(queue.contains(5));
    }

    @Test
    void queueToString() {
        assertEquals("", queue.toString());
        queue.insert(5);
        assertEquals("5", queue.toString());
        queue.enqueue(24);
        assertEquals("5 <- 24", queue.toString());
        queue.enqueue(null);
        assertEquals("5 <- 24 <- null", queue.toString());
    }

    @Test
    void queueIterator() {
        Object[] arr = {144, null, "HP", -145, 4.5, 144, 4.5, null};
        Arrays.stream(arr).forEach(o -> queue.insert(o));
        Iterator<Object> it = queue.iterator();
        for (int i = 0; it.hasNext(); i++) {
            Object o = it.next();
            assertEquals(o, queue.get(i));
            assertEquals(arr[i], queue.get(i));
        }
    }

    @Test
    void createEmptyQueue() {
        queue.insert(5);
        queue.enqueue("hi");
        Queue<Object> newQueue = queue.createEmptyQueue();
        assertEquals(0, newQueue.size());
        assertTrue(newQueue.isEmpty());
    }

    @Test
    void queueFilter() {
        for (int i = 0; i < 100; i++) {
            queue.enqueue(i);
        }
        Queue<Object> q = queue.filter(o -> {
            if (o instanceof Integer k) {
                return k > 10 && k < 20;
            }
            return false;
        });
        assertEquals(9, q.size());
    }

    @Test
    void queueFilterNull() {
        Queue<Object> q = queue.filter(null);
        assertNull(q);
    }
}