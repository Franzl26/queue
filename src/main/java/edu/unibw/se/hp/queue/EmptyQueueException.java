package edu.unibw.se.hp.queue;

public class EmptyQueueException extends RuntimeException{
    public EmptyQueueException(String message) {
        super(message);
    }
}
