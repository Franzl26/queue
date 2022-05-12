package edu.unibw.se.hp.queue;

class QueueByListTest extends QueueTest{
    @Override
    Queue<Object> createQueue() {
        return new QueueByList<>();
    }
}
