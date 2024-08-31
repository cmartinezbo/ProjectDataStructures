package com.trees;

//import queue.QueueArrayGeneric;

import java.util.LinkedList;
import java.util.Queue;

import java.util.ArrayList;

public class BinarySearchTree<T> {

    private Node root;

    //Constructor of BST
    public BinarySearchTree(){
        root=null;
    }

    //class node
    private class Node <T extends Comparable<T>> {

        private T key;
        private Node left;
        private Node right;
        private Node parent;

        public Node(T key){
            this.key=key;
            left=null;
            right=null;
            parent=null;
        }

        public String toString(){
            return key.toString();
        }

        //Methods for Node class
    }

    private int height(Node node){
        if(node==null){
            return 0;
        }
        return 1+Math.max(height(node.left),height(node.right));
        //return 1+Math.max(0,1);
    }

    public int height(){
        return height(root);
    }

    private int size(Node node){
        if(node==null){
            return 0;
        }
        return 1 +size(node.left) +size(node.right);
    }

    public int size(){
        return size(root);
    }

    //Depth-first method
    private void inOrder(Node node){
        if (node==null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.key);
        inOrder(node.right);
    }

    public void inOrder(){
        inOrder(root);
    }

    private void preOrder(Node node){
        if (node==null){
            return;
        }
        System.out.println(node.key);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void preOrder(){
        preOrder(root);
    }

    private void postOrder(Node node){
        if (node==null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.key);
    }

    public void postOrder(){
        postOrder(root);
    }

    //breadth-first
    public void levelTraversal(){
        Queue<Node>queue=new LinkedList<>();
        //QueueArrayGeneric<Node> queue= new QueueArrayGeneric<>(size());
        if(root==null){
            System.out.println("Tree is empty");
        }
        else{
            queue.add(root);
            while (!queue.isEmpty()){
                Node node=queue.remove();
                System.out.println(node.key);

                if (node.left!=null){
                    queue.add(node.left);
                }
                if (node.right!=null){
                    queue.add(node.right);
                }
            }
        }

    }

    private Node find(Node node, T key){
        if(node==null) {
            return null;
        }
        else{
            if (node.key.compareTo(key)==0){
                return node;
            }
            else if(node.key.compareTo(key)<0){
                return find(node.right,key);
            }
            else if(node.key.compareTo(key)>0){
                return find(node.left,key);
            }
            else{
                return null;
            }
        }
    }

    public Node find(T key){
        if (root==null){
            return null;
        }
        return find(root, key);
    }

    private Node leftDescendent(Node node){
        if (node.left==null){
            return node;
        }
        else{
            return leftDescendent(node.left);
        }
    }

    private Node rightAncestor(Node node){
        if(node.parent==null){
            return null;
        }
        else{
            if (node.key.compareTo(node.parent.key)<0){
                return node.parent;
            }
            else{
                return rightAncestor(node.parent);
            }
        }
    }

    private Node next(T key){
        //Confiando en que el método find funcione correctamente
        Node node=find(key);
        if (node==null){
            System.err.println("Error: Key not in tree");
            throw new RuntimeException("Key not in tree");
        }
        else{
            if (node.right!=null){
                return leftDescendent(node.right);
            }
            else{
                return rightAncestor(node);
            }
        }
    }

    //Implement of range search (on hold)
    public ArrayList<T> rangeSearch(T lower, T higher){
        ArrayList<T> range=new ArrayList<>();
        Node limit=find(lower);
        if (limit==null){
            insert(lower);
            limit=next(lower);
            delete(lower);
        }
        while(limit.key.compareTo(higher)<=0){
            range.add((T) limit.key);
            limit=next((T) limit.key);
        }
        return range;
    }

    public Node insert(T key){
        if(find(key)==null){
            Node current=root;
            if(root==null){
                Node node=new Node((Comparable) key);
                root=node;
                return node;
            }
            while(current!=null){
                if(current.key.compareTo(key)>0){
                    if(current.left==null){
                        Node node=new Node((Comparable) key);
                        current.left=node;
                        node.parent=current;
                        return node;
                    }
                    else{
                        current=current.left;
                    }
                }
                else{
                    if(current.right==null){
                        Node node=new Node((Comparable) key);
                        current.right=node;
                        node.parent=current;
                        return node;
                    }
                    else{
                        current=current.right;
                    }
                }
            }
        }
        System.err.println("Error: key is already in the tree");
        throw new RuntimeException("Key is already in the tree");
    }

    //Determina si el hijo es izquierdo
    private boolean leftSon(Node father, Node son){
        if (father.left==son){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean rightSon(Node father, Node son){
        if(father.right==son){
            return true;
        }
        else{
            return false;
        }
    }

    //promueve el hijo derecho a la posición del padre
    private void promote(Node parent, Node son){
        if(leftSon(parent.parent,parent)){
            parent.parent.left=son;
            son.parent=parent.parent;
        }
        else{
            parent.parent.right=son;
            son.parent=parent.parent;
        }
    }

    public Node delete(T key){
        Node node=find(key);
        if(node==null){
            return null;
        }
        else{
            if (node==root){
                if (root.right==null && root.left==null){
                    root=null;
                    return node;
                }
                else if(node.right==null){
                    root=node.left;
                    return node;
                }
                else{
                    //Arreglar esta parte, con el caso que esta en el main
                    Node n=next(key);
                    //System.out.println("n "+n);
                    n.left=root.left;
                    if(root.left!=null){
                        root.left.parent=n;
                    }
                    if(n.right!=null){
                        promote(n,n.right);
                    }
                    if (node.right!=n){
                        n.right=node.right;
                        node.right.parent=n;
                    }
                    if(leftSon(n.parent,n)){
                        n.parent.left=null;
                    }
                    else if(rightSon(n.parent,n)){
                        n.parent.right=null;
                    }
                    root=n;
                    n.parent=null;
                    //System.out.println(root);
                    //System.out.println(root.right.parent);
                    return node;
                }
            }
            else if(node.right==null){
                if(node==node.parent.left){
                    node.parent.left=node.left;
                    if(node.left!=null){
                        node.left.parent=node.parent;
                    }
                    return node;
                }
                else{
                    node.parent.right=node.left;
                    if(node.left!=null){
                        node.left.parent=node.parent;
                    }
                    return node;
                }
            }
            else{
                Node n=next(key);
                //System.out.println(n);
                if(n.right==null){
                    if(node.parent.left==node){
                        node.parent.left=n;
                        n.left=node.left;
                        if(node.left!=null){
                            node.left.parent=n;
                        }
                        if(node.right!=n){
                            n.right=node.right;
                            node.right.parent=n;
                        }
                        if(leftSon(n.parent,n)){
                            n.parent.left=null;
                        }
                        else if (rightSon(n.parent,n)){
                            n.parent.right=null;
                        }
                        n.parent=node.parent;
                        return node;
                    }
                    else{
                        node.parent.right=n;
                        n.left=node.left;
                        if(node.left!=null){
                            node.left.parent=n;
                        }
                        //System.out.println(n.parent);
                        //System.out.println(n.parent.left);
                        //System.out.println(n.parent.right);
                        if(node.right!=n){
                            //System.out.println("jhi");
                            //System.out.println(node.right);
                            n.right=node.right;
                            node.right.parent=n;
                        }
                        if(leftSon(n.parent,n)){
                            //System.out.println("hi");
                            n.parent.left=null;
                        }
                        else{
                            n.parent.right=null;
                        }
                        n.parent=node.parent;
                        //Aqui esta el problema, ver mañana
                        //System.out.println(node.right.left);
                        return node;
                    }
                }
                else {
                    //System.out.println("nodo "+node+" node.parent" +node.parent);
                    //System.out.println(node.right);
                    //System.out.println(node.left);
                    if(node.parent.left==node){
                        node.parent.left=n;
                        n.left=node.left;
                        if(node.left!=null){
                            node.left.parent=n;
                        }
                        promote(n,n.right);
                        n.parent=node.parent;
                        if(node.right!=n){
                            n.right=node.right;
                            node.right.parent=n;
                        }
                        return node;
                    }
                    else{
                        node.parent.right=n;
                        n.left=node.left;
                        if(node.left!=null){
                            node.left.parent=n;
                        }
                        promote(n,n.right);
                        if(node.right!=n){
                            n.right=node.right;
                            node.right.parent=n;
                        }
                        n.parent=node.parent;
                        //System.out.println("n  es "+n);
                        //System.out.println("n.parent es"+n.parent);
                        //System.out.println("node.parent es "+node.parent);
                        //System.out.println(n.left);
                        //System.out.println(n.right);
                        //System.out.println(n.right.left);
                        //System.out.println(n.right);
                        return node;
                    }
                }
            }

        }
    }

    public static void main(String []args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(7);
        bst.insert(4);
        bst.insert(1);
        bst.insert(6);
        bst.insert(13);
        bst.insert(10);
        bst.insert(15);
        //bst.inOrder();
        //System.out.println(bst.delete(30));
        //bst.delete(45);
        //bst.insert(44);
        //bst.delete(44);
        //bst.delete(48);
        //bst.delete(50);
        //System.out.println();
        bst.levelTraversal();
    }

}