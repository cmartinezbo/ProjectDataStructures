package com.main.test;
import com.datastructures.sequential.SinglyLinkedList;

public class TestLinkedList {

    public static void main(String [] args){
        SinglyLinkedList<Integer> linkedlist=new SinglyLinkedList<>();
        linkedlist.pushFront(4);
        linkedlist.pushFront(3);
        linkedlist.pushFront(9);
        linkedlist.pushFront(23);
        for(Integer number: linkedlist){
            System.out.println(number);
        }

    }
}
