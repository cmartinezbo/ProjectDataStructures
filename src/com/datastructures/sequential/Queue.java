package com.datastructures.sequential;

public class Queue<T> {
    private final SinglyLinkedList<T> queue;

    public Queue() {
        queue = new SinglyLinkedList<>();
    }

    public void enqueue(T key) {
        queue.pushBack(key);
    }

    public T dequeue() {
        if (queue.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        T frontElement = queue.topFront(); // Retrieve the front element
        queue.popFront(); // Remove the front element from the queue

        return frontElement; // Return the retrieved element
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void display() {
        queue.display();
    }
}
