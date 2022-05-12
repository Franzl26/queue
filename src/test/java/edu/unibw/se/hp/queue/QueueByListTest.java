package edu.unibw.se.hp.queue;

class QueueByListTest extends QueueTest{
    @Override
    Queue createQueue() {
        return new QueueByList();
    }
}
