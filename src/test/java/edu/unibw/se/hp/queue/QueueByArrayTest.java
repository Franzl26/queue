package edu.unibw.se.hp.queue;

class QueueByArrayTest extends QueueTest{
    @Override
    Queue createQueue() {
        return new QueueByArray();
    }
}
