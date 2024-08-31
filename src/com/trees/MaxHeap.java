package com.trees;

public class MaxHeap {
    int[] array;
    int maxSize;
    int n;

    public MaxHeap(int maxSize) {
        array = new int[maxSize+1];
        this.maxSize = maxSize;
        n = 0;
    }

    private int parent(int i) {
        return (i / 2);
    }

    private int leftSon(int i) {
        return (2 * i);
    }

    private int rightSon(int i) {
        return ((2 * i) + 1);
    }

    public int getMax() {
        return array[1];
    }

    private void siftUp(int i) {
        while (i > 1 && array[i] > array[parent(i)]) {
            int temp = array[i];
            array[i] = array[parent(i)];
            array[parent(i)] = temp;
            i = parent(i);
        }
    }

    private void resize() {
        this.maxSize = 2 * maxSize;
        int[] nArray = new int[maxSize];
        System.arraycopy(array, 1, nArray, 1, n);
        this.array = nArray;
    }


    public void insert(int item) {
        if(n==maxSize){
            resize();
        }
        this.n += 1;
        array[n] = item;
        siftUp(n);
    }

    private void siftDown(int i){
        int left=leftSon(i);
        int right=rightSon(i);
        int maxi=i;
        if(left<n && array[left]>array[maxi]){
            maxi=left;
        }
        if(right<n && array[right]>array[maxi]){
            maxi=right;
        }
        if(maxi!=i){
            int temp=array[i];
            array[i]=array[maxi];
            array[maxi]=temp;
            siftDown(maxi);
        }
    }

    public int extractMax(){
        int r=array[1];
        array[1]=array[n];
        n--;
        siftDown(1);
        return r;
    }

    public void changePriority(int i, int value){
        int old=array[i];
        array[i]=value;
        if(old>array[i]){
            siftDown(i);
        }
        else{
            siftUp(i);
        }
    }

    public void delete(int i){
        array[i]=Integer.MAX_VALUE;
        siftUp(i);
        extractMax();
    }

    public int[] heapSort1(int[] a ){
        MaxHeap heap=new MaxHeap(a.length);
        int i=a.length-1;
        while(i>-1){
            heap.insert(a[i]);
            i--;
        }
        System.out.println(heap);
        i=a.length-1;
        while(i>-1){
            a[i]=heap.extractMax();
            i--;
        }
        return a;
    }


    public String toString(){
        String elements="";
        int f=n;
        for(int i=1; i<n; i++){
            elements+=array[i]+", ";
        }
        elements+=array[f];
        return elements;
    }

    public static void main(String []args){
        MaxHeap heap=new MaxHeap(2);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4);
        System.out.println(heap);


    }

}
