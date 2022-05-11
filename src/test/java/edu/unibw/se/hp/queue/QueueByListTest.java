package edu.unibw.se.hp.queue;

public class QueueByListTest extends QueueTest{
    @Override
    public Queue createQueue() {
        return new QueueByList();
    }
}
