package edu.unibw.se.hp.queue;

public class QueueIndexOutOfBoundsException extends IndexOutOfBoundsException{
    public QueueIndexOutOfBoundsException(int index) {
        super("Queue index out of range: " + index);
    }
}
