package edu.unibw.se.hp.queue;

import org.junit.jupiter.api.*;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("QueueTest")
public abstract class QueueTest {
    private Queue queue = null;

    public abstract Queue createQueue();

    @BeforeEach
    public void beforeEachTest() {
        queue = createQueue();
    }

    @Test
    public void newQueueEmpty() {
        assertTrue(queue.isEmpty());
    }

    @Test
    public void newQueueSize0() {
        assertEquals(0, queue.size());
    }

    @Test
    public void emptyQueueException() {
        assertThrows(EmptyQueueException.class, () -> queue.first());
        assertThrows(EmptyQueueException.class, () -> queue.dequeue());

    }

    @Test
    public void queueIndexOutOfBoundsException() {
        assertThrows(QueueIndexOutOfBoundsException.class, () -> queue.get(0));
        queue.insert(5);
        assertThrows(QueueIndexOutOfBoundsException.class, () -> queue.get(-1));
        assertThrows(QueueIndexOutOfBoundsException.class, () -> queue.get(2));
    }

    @Test
    public void queueInsert144() {
        queue.insert(144);
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
        assertTrue(queue.contains(144));
        assertEquals(144, queue.first());
        assertEquals(144, queue.get(0));
    }

    @Test
    public void queueInsertNull() {
        queue.insert(null);
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
        assertTrue(queue.contains(null));
        assertNull(queue.first());
        assertNull(queue.get(0));
    }

    @Test
    public void queueInsertMultiple1() {
        queue.insert(144);
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
        assertTrue(queue.contains(144));
        assertEquals(144, queue.first());
        assertEquals(144, queue.get(0));

        queue.enqueue(null);
        assertFalse(queue.isEmpty());
        assertEquals(2, queue.size());
        assertTrue(queue.contains(null));
        assertEquals(144, queue.first());
        assertEquals(null, queue.get(1));

        queue.insert("HP");
        assertFalse(queue.isEmpty());
        assertEquals(3, queue.size());
        assertTrue(queue.contains(144));
        assertEquals(144, queue.first());
        assertEquals("HP", queue.get(2));

        queue.enqueue(-145);
        assertFalse(queue.isEmpty());
        assertEquals(4, queue.size());
        assertTrue(queue.contains(144));
        assertEquals(144, queue.first());
        assertEquals(-145, queue.get(3));

        queue.insert(4.5);
        assertFalse(queue.isEmpty());
        assertEquals(5, queue.size());
        assertTrue(queue.contains(144));
        assertEquals(144, queue.first());
        assertEquals(4.5, queue.get(4));
    }

    @Test
    public void queueClear() {
        queue.insert("hi");
        queue.insert(null);
        queue.insert(45);
        queue.clear();
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    public void queueInsertMany() {
        for (int i = 0; i < 100; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < 100; i++) {
            assertEquals(100 - i, queue.size());
            assertEquals(i, queue.dequeue());
            assertEquals(100 - i - 1, queue.size());
        }
    }

    @Test
    public void queueRemove() {
        queue.insert(144);
        queue.insert(null);
        queue.insert("HP");
        queue.insert(-145);
        queue.enqueue(4.5);
        queue.enqueue(144);
        queue.enqueue(4.5);
        queue.enqueue(null);

        queue.remove("HP");
        assertFalse(queue.contains("HP"));
        assertEquals(7, queue.size());

        queue.remove(144);
        assertFalse(queue.contains(144));
        assertEquals(5, queue.size());

        queue.remove(4.5);
        assertFalse(queue.contains(4.5));
        assertEquals(3, queue.size());

        queue.remove(null);
        assertFalse(queue.contains(null));
        assertEquals(1, queue.size());

        queue.remove(-145);
        assertFalse(queue.contains(-145));
        assertEquals(0, queue.size());
    }

    @Test
    public void queueRemove2() {
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
    public void queueToString() {
        assertEquals("", queue.toString());
        queue.insert(5);
        assertEquals("5", queue.toString());
        queue.enqueue(24);
        assertEquals("5 <- 24", queue.toString());
        queue.enqueue(null);
        assertEquals("5 <- 24 <- null", queue.toString());
    }

    @Test
    public void queueIterator() {
        queue.insert(144);
        queue.insert(null);
        queue.insert("HP");
        queue.insert(-145);
        queue.enqueue(4.5);
        queue.enqueue(144);
        queue.enqueue(4.5);
        queue.enqueue(null);
        Iterator<Object> it = queue.iterator();
        for (int i = 0; it.hasNext(); i++) {
            Object o = it.next();
            assertEquals(o, queue.get(i));
        }
    }

    @Test
    public void createEmptyQueue() {
        queue.insert(5);
        queue.enqueue("hi");
        Queue newQueue = queue.createEmptyQueue();
        assertEquals(0, newQueue.size());
        assertTrue(newQueue.isEmpty());
    }

    @Test
    public void queueFilter() {
        for (int i = 0; i < 100; i++) {
            queue.enqueue(i);
        }
        Queue q = queue.filter(o -> {
           if (o instanceof Integer k) {
               return k > 10 && k < 20;
           }
           return false;
        });
        assertEquals(9, q.size());
    }

    @Test
    public void queueFilterNull() {
        Queue q = queue.filter(null);
        assertNull(q);
    }
}