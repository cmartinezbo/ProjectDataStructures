package com.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.List;

public class HashMap<K,V> implements Iterable<HashMap<K,V>.HashNode<K,V>> {

    public class HashNode<K, V> {
        K key;
        V value;
        int hashCode;
        HashNode<K,V> next;

        public HashNode(K key, V value){
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    private ArrayList<HashNode<K,V>> bucket;
    private int capacity;
    private int size;
    private Double loadFactor;

    public HashMap(double loadFactor){
        this.capacity = 10;
        this.loadFactor = loadFactor;
        this.size = 0;
        bucket = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            bucket.add(null);
        }
    }

    public int hash(K key){
        return Math.abs(key.hashCode()) % capacity;
    }

    public void add(K key, V value){
        int position = hash(key);
        HashNode<K,V> head = bucket.get(position);
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        head = bucket.get(position);
        HashNode<K,V> node = new HashNode<>(key, value);
        node.next = head;
        bucket.set(position, node);
        size++;

        if (size * 1.0 / capacity >= loadFactor) {
            resize();
        }
    }

    private void resize() {
        ArrayList<HashNode<K, V>> temp = bucket;
        bucket = new ArrayList<>();
        this.capacity = 2 * capacity;
        for (int i = 0; i < capacity; i++) {
            bucket.add(null);
        }
        size = 0;
        for (HashNode<K, V> headNode : temp) {
            while (headNode != null) {
                add(headNode.key, headNode.value);
                headNode = headNode.next;
            }
        }
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public V remove(K key){
        int position = hash(key);
        HashNode<K,V> head = bucket.get(position);
        HashNode<K,V> prev = null;
        while (head != null) {
            if (head.key.equals(key)) {
                break;
            }
            prev = head;
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        size--;
        if (prev != null) {
            prev.next = head.next;
        } else {
            bucket.set(position, head.next);
        }

        return head.value;
    }

    public V get(K key){
        int position = hash(key);
        HashNode<K,V> head = bucket.get(position);
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    // Implementación del método containsKey
    public boolean containsKey(K key) {
        int position = hash(key);
        HashNode<K,V> head = bucket.get(position);
        while (head != null) {
            if (head.key.equals(key)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    // Implementación del iterador para el HashMap
    @Override
    public Iterator<HashNode<K,V>> iterator() {
        return new HashMapIterator();
    }

    public class HashMapIterator implements Iterator<HashNode<K,V>> {
        private int currentBucketIndex;
        private HashNode<K, V> currentNode;

        public HashMapIterator() {
            currentBucketIndex = 0;
            currentNode = null;
            advanceToNextNonEmptyBucket();
        }

        // Avanza al siguiente bucket no vacío
        private void advanceToNextNonEmptyBucket() {
            while (currentBucketIndex < bucket.size() && (currentNode = bucket.get(currentBucketIndex)) == null) {
                currentBucketIndex++;
            }
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public HashNode<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            HashNode<K, V> nodeToReturn = currentNode;
            currentNode = currentNode.next;
            if (currentNode == null) {
                currentBucketIndex++;
                advanceToNextNonEmptyBucket();
            }
            return nodeToReturn;
        }
    }

    public List<V> values() {
        List<V> valueList = new ArrayList<>();
        for (HashNode<K, V> headNode : bucket) {
            while (headNode != null) {
                valueList.add(headNode.value);
                headNode = headNode.next;
            }
        }
        return valueList;
    }

    public static void main(String[] args) {
        HashMap<String, Integer> table = new HashMap<>(0.5);
        table.add("mira", 5);
        table.add("cuaderno", 1);
        table.add("loctron", 1);
        table.add("juga", 7);
        table.add("tiktok", 2);

        System.out.println(table.containsKey("mira"));  // true
        System.out.println(table.containsKey("nonexistent"));  // false

        // Iterando sobre los elementos
        for (HashMap<String, Integer>.HashNode<String, Integer> node : table) {
            System.out.println("Key: " + node.getKey() + ", Value: " + node.getValue());
        }
    }
}
