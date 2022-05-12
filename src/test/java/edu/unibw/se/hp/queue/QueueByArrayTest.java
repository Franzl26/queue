package edu.unibw.se.hp.queue;

class QueueByArrayTest extends QueueTest{
    @Override
    Queue<Object> createQueue() {
        return new QueueByArray<>();
    }
}
