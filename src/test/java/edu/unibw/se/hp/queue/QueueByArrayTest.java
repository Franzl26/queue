package edu.unibw.se.hp.queue;

public class QueueByArrayTest extends QueueTest{
    @Override
    public Queue createQueue() {
        return new QueueByArray();
    }
}
