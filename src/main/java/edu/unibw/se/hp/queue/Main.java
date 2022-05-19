package edu.unibw.se.hp.queue;

public class Main {
    public static void main(String[] args) {
        Queue<Integer> queueByArray = new QueueByArray<>();
        Queue<Integer> queueByList = new QueueByList<>();
        Queue<Integer> queueWithPrioritiy = new QueueWithPriority<>();
        for (int i = 0; i < 100; i++) {
            queueByArray.enqueue(i);
            queueByList.enqueue(i);
            queueWithPrioritiy.enqueue(i);
        }
        for (Integer i: queueByArray) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (Integer i: queueByList) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (Integer i: queueWithPrioritiy) {
            System.out.print(i + " ");
        }
    }
}
