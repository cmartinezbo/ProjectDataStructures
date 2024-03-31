package com.datastructures.sequential;

public class DoublyLinkedList<T> {

    public static class Node<T> {
        T key; // Key
        Node<T> next; // Pointer to the next node
        Node<T> prev; // Pointer to the previous node

        public Node(T key) { // Constructor
            this.key = key; // Initialize the data
            this.next = null; // Initialize the next pointer to null
            this.prev = null; // Initialize the previous pointer to null
        }
    }

    Node<T> head = null; // Head node
    Node<T> tail = null; // Tail node

    // Push Front -> O(1)
    public void pushFront(T key) { // Insert a new node at the front
        Node<T> newNode = new Node<>(key); // Create a new node and set its data

        if (head == null) { // Checks if the list is empty
            head = newNode; // New node becomes the head
            tail = newNode; // New node becomes the tail
        } else {
            newNode.next = head; // New node points to old head
            head.prev = newNode; // Old head points to new node
            head = newNode; // New node becomes the actual head
        }
    }

    // Top Front -> O(1)
    public T topFront() { // Returns the data of the front node
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }
        return head.key;
    }

    // Pop Front -> O(1)
    public void popFront() { // Removes the front node
        if (head == null) { // Checks if the list is empty
            throw new IllegalStateException("List is empty");
        }

        head = head.next; // Head points to the head next node

        if (head == null) { // Checks if the list is empty after removing the front node
            tail = null;
        } else {
            head.prev = null; // Head previous points to null
        }
    }

    // Push Back -> O(1)
    public void pushBack(T key) { // Inserts a new node at the end
        Node<T> newNode = new Node<>(key); // Create a new node and set its data

        if (head == null) { // Checks if the list is empty
            head = newNode; // New node becomes the head
            tail = newNode; // New node becomes the tail
        } else {
            tail.next = newNode; // Old tail next's points to new node
            newNode.prev = tail; // New node points to old tail
            tail = newNode; // New node becomes the tail
        }
    }

    // Top Back -> O(1)
    public T topBack() { // Returns the data of the tail node
        if (tail == null) {
            throw new IllegalStateException("List is empty");
        }
        return tail.key;
    }

    // Pop Back -> O(1)
    public void popBack() { // Removes the back node
        if (tail == null) { // Checks if the list is empty
            throw new IllegalStateException("List is empty");
        }

        tail = tail.prev; // Tail points to the tail previous node

        if (tail == null) { // Checks if the list is empty after removing the back node
            head = null;
        } else {
            tail.next = null; // Tail next points to null
        }
    }

    // Find -> O(n)
    public boolean find(T key) { // Searches for a key in the list
        Node<T> current = head; // Start from the head

        while (current != null) { // Traverse the list
            if (current.key.equals(key)) { // Checks if the key is found
                return true; // Key found
            }
            current = current.next; // Move to the next node
        }

        return false; // Key not found
    }

    // Erase -> O(n)
    public void erase(T key) { // Removes a node with a specific key
        Node<T> current = head; // Start from the head

        while (current != null) { // Traverse the list
            if (current.key.equals(key)) { // Checks if the key is found
                if (current == head) { // Checks if the node is the head
                    head = head.next; // Head points to the head next node

                    if (head == null) { // Checks if the list is empty after removing the front node
                        tail = null;
                    } else {
                        head.prev = null; // Head previous points to null
                    }
                } else if (current == tail) { // Checks if the node is the tail
                    tail = tail.prev; // Tail points to the tail previous node

                    if (tail == null) { // Checks if the list is empty after removing the back node
                        head = null;
                    } else {
                        tail.next = null; // Tail next points to null
                    }
                } else {
                    current.prev.next = current.next; // Current previous next points to current next
                    current.next.prev = current.prev; // Current next previous points to current previous
                }
                return;
            }
            current = current.next; // Move to the next node
        }

    }

    // isEmpty -> O(1)
    public boolean isEmpty() { // Checks if the list is empty
        return head == null;
    }

    // Add Before -> O(n)
    public void addBefore(T key, T newKey) { // Inserts a new node before a specific key
        Node<T> current = head; // Start from the head

        while (current != null) { // Traverse the list
            if (current.key.equals(key)) { // Checks if the key is found
                Node<T> newNode = new Node<>(newKey); // Create a new node and set its data

                newNode.next = current; // New node next points to current
                newNode.prev = current.prev; // New node previous points to current previous
                current.prev = newNode; // Current previous points to new node

                if (newNode.prev != null) { // Checks if the new node is not the head
                    newNode.prev.next = newNode; // New node previous next points to new node
                } else {
                    head = newNode; // New node becomes the head
                }
                return;
            }
            current = current.next; // Move to the next node
        }
    }

    // Add After -> O(n)
    public void addAfter(T key, T newKey) { // Inserts a new node after a specific key
        Node<T> current = head; // Start from the head

        while (current != null) { // Traverse the list
            if (current.key.equals(key)) { // Checks if the key is found
                Node<T> newNode = new Node<>(newKey); // Create a new node and set its data

                newNode.next = current.next; // New node next points to current next
                newNode.prev = current; // New node previous points to current
                current.next = newNode; // Current next points to new node

                if (newNode.next != null) { // Checks if the new node is not the tail
                    newNode.next.prev = newNode; // New node next previous points to new node
                } else {
                    tail = newNode; // New node becomes the tail
                }
                return;
            }
            current = current.next; // Move to the next node
        }
    }

    public void display() {
        Node<T> current = head; // Start from the head

        System.out.print("Nodes of doubly linked list: ");
        while (current != null) { // Traverse the list
            System.out.print(current.key + " "); // Print the current node key
            current = current.next; // Move to the next node
        }
        System.out.println();
    }
}
