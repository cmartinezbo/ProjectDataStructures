package com.main.test.Tests2;

public class MinHeapForTareas {

    private long[] array;            // Array para las prioridades
    private String[] tarea;          // Array para los Strings asociados
    private String[] fecha;          // Array para las fechas asociadas
    private int maxSize;
    private int n;

    public MinHeapForTareas(int maxSize) {
        array = new long[maxSize + 1];
        tarea = new String[maxSize + 1]; // Inicializa el array de Strings
        fecha = new String[maxSize + 1]; // Inicializa el array de fechas
        this.maxSize = maxSize;
        n = 0;
    }

    private int parent(int i) {
        return i / 2;
    }

    private int leftSon(int i) {
        return 2 * i;
    }

    private int rightSon(int i) {
        return 2 * i + 1;
    }

    public long getMinPriority() {
        if (n == 0) throw new IllegalStateException("Heap is empty");
        return array[1];
    }

    public String getMinTarea() {
        if (n == 0) throw new IllegalStateException("Heap is empty");
        return tarea[1];
    }

    public String getMinFecha() {
        if (n == 0) throw new IllegalStateException("Heap is empty");
        return fecha[1];
    }
    
    public int getN(){
        return n;
    }
    
    public boolean isEmpty(){
        return n == 0;
    }

    private void siftUp(int i) {
        while (i > 1 && array[i] < array[parent(i)]) {
            // Intercambia las prioridades
            long tempPriority = array[i];
            array[i] = array[parent(i)];
            array[parent(i)] = tempPriority;

            // Intercambia los Strings de tareas
            String tempTarea = tarea[i];
            tarea[i] = tarea[parent(i)];
            tarea[parent(i)] = tempTarea;

            // Intercambia las fechas
            String tempFecha = fecha[i];
            fecha[i] = fecha[parent(i)];
            fecha[parent(i)] = tempFecha;

            i = parent(i);
        }
    }

    private void resize() {
        maxSize *= 2;
        long[] newArray = new long[maxSize + 1];
        String[] newTareas = new String[maxSize + 1];
        String[] newFechas = new String[maxSize + 1];
        System.arraycopy(array, 1, newArray, 1, n);
        System.arraycopy(tarea, 1, newTareas, 1, n);
        System.arraycopy(fecha, 1, newFechas, 1, n);
        array = newArray;
        tarea = newTareas;
        fecha = newFechas;
    }

    public void insert(long priority, String ingresaFecha, String ingresaTarea) {
        if (n + 1 == maxSize) {
            resize();
        }
        n++;
        array[n] = priority;
        fecha[n] = ingresaFecha;
        tarea[n] = ingresaTarea;
        siftUp(n);
    }

    private void siftDown(int i) {
        int left = leftSon(i);
        int right = rightSon(i);
        int mini = i;
        if (left <= n && array[left] < array[mini]) {
            mini = left;
        }
        if (right <= n && array[right] < array[mini]) {
            mini = right;
        }
        if (mini != i) {
            // Intercambia las prioridades
            long tempPriority = array[i];
            array[i] = array[mini];
            array[mini] = tempPriority;

            // Intercambia las tareas
            String tempTarea = tarea[i];
            tarea[i] = tarea[mini];
            tarea[mini] = tempTarea;

            // Intercambia las fechas
            String tempFecha = fecha[i];
            fecha[i] = fecha[mini];
            fecha[mini] = tempFecha;

            siftDown(mini);
        }
    }

    public String[] extractMin() {
        if (n == 0) throw new IllegalStateException("Heap is empty");
        long minPriority = array[1];
        String minTarea = tarea[1];
        String minFecha = fecha[1];
        String[] result = {String.valueOf(minPriority), minFecha, minTarea};
        array[1] = array[n];
        tarea[1] = tarea[n];
        fecha[1] = fecha[n];
        n--;
        siftDown(1);
        return result;
    }

    public void show() {
        for (int i = 1; i <= n; i++) {
            System.out.println("(" + array[i] + ", " + fecha[i] + ", " + tarea[i] + ")");
        }
    }
    
    public String[] getTarea(){
        return tarea;
    }
    
    public String[] getFecha(){
        return fecha;
    }
}
