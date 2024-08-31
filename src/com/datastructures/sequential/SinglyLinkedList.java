import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements Iterable<T> {

    public static class Node<T> {
        T key; // Key
        Node<T> next; // Pointer to the next node

        public Node(T key) { // Constructor
            this.key = key; // Initialize the data
            this.next = null; // Initialize the next pointer to null
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
    
    // Top Front Node -> O(1)
    public Node<T> topFrontNode() { //Returns the front node
            if (head == null) {
            throw new IllegalStateException("List is empty");
        }
        return head;
    }

    // Pop Front -> O(1)
    public void popFront() { // Removes the front node
        if (head == null) { // Checks if the list is empty
            throw new IllegalStateException("List is empty");
        }

        head = head.next; // Head points to the head next node

        if (head == null) { // Checks if the list is empty after removing the front node
            tail = null;
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

    // Pop Back -> O(n)
    public void popBack() { // Removes the back node
        if (head == null) { // Checks if the list is empty
            throw new IllegalStateException("List is empty");
        }

        if (head == tail) { // Checks if there is only one node
            head = null;
            tail = null;
            return;
        }

        Node<T> current = head;
        Node<T> previous = null;

        while (current.next != null) { // Traverse the list
            previous = current;
            current = current.next;
        }

        previous.next = null; // Tail node points to null
        tail = previous; // Previous node becomes the tail
    }

    // Find -> O(n)
    public boolean find(T key) { // Is the key in the list?
        Node<T> current = head; // Start from the head

        while (current != null) { // Traverse the list
            if (current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Erase -> O(n)
    public void erase(T key) { // Removes a node with a specific data from the list
        if (head == null) { // Checks if the list is empty
            throw new IllegalStateException("List is empty");
        }

        if (head.key.equals(key)) { // Checks if the head node has the data
            head = head.next; // Actual head points to the old head next's node
            return;
        }

        Node<T> current = head; // Start from the head
        Node<T> previous = null; // Previous node to the current node

        while (current != null) { // Traverse the list
            if (current.key.equals(key)) {
                previous.next = current.next; // Previous node points to the current next node
                return;
            }
            previous = current; // Previous node becomes the current node
            current = current.next; // Current node becomes the current next node
        }
    }

    // Empty -> 0(1)
    public boolean isEmpty() { // Is it empty?
        return head == null;
    }
    
    // Clear -> O(1)
    public void clear() { //Clears the list
        head = null; 
        tail = null; 
    }

    // Add Before -> O(n)
    public void addBefore(T key, T newKey) { // Inserts a new node before a node with a specific key, if the key is
        // duplicated, it inserts the new node before the first occurrence
        if (head == null) { // Checks if the list is empty
            throw new IllegalStateException("List is empty");
        }

        if (head.key.equals(key)) { // Checks if the head node has the key
            Node<T> newNode = new Node<>(newKey); // Create a new node
            newNode.next = head;
            head = newNode;
            return;
        }

        Node<T> current = head;
        Node<T> previous = null;

        while (current != null) { // Traverse the list
            if (current.key.equals(key)) {
                Node<T> newNode = new Node<>(newKey); // Create a new node
                previous.next = newNode;
                newNode.next = current;
                return;
            }
            previous = current;
            current = current.next;
        }

        throw new IllegalArgumentException("Key not found in the list");
    }

    // Add After -> O(n)
    public void addAfter(T key, T newKey) { // Inserts a new node after a node with a specific key, if the key is
        // duplicated, it inserts the new node after the first occurrence
        if (head == null) { // Checks if the list is empty
            throw new IllegalStateException("List is empty");
        }

        Node<T> current = head;

        while (current != null) { // Traverse the list
            if (current.key.equals(key)) {
                Node<T> newNode = new Node<>(newKey); // Create a new node
                newNode.next = current.next;
                current.next = newNode;
                return;
            }
            current = current.next;
        }

        throw new IllegalArgumentException("Key not found in the list");
    }

    public void display() { // Display the list
        Node<T> current = head;

        if (current == null) {
            System.out.println("List is empty");
            return;
        }

        System.out.print("Nodes of singly linked list: ");
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        while (current.next != null) {
            sb.append(current.key + ", ");
            current = current.next;
        }

        sb.append(current.key);
        sb.append("]");
        System.out.println(sb);
    }

    public Iterator<T> iterator(){
        return new linkedListIterator();
    }

    private class linkedListIterator implements Iterator<T>{
        private Node<T> current=head;

        @Override
        public boolean hasNext() {
            return current!=null;
        }

        public T next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            T dataNode=current.key;
            current=current.next;
            return dataNode;
        }
    }

}


