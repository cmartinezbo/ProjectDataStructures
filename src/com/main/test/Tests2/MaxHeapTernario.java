package pruebaArbolesHeaps;
import java.util.Random;

public class MaxHeapTernario {

    private int[] array;
    private int maxSize;
    private int n;

    public MaxHeapTernario(int maxSize) {
        array = new int[maxSize + 1];
        this.maxSize = maxSize;
        n = 0;
    }

    private int parent(int i) {
        return (i + 1) / 3;  // El índice del padre para un heap terciario
    }

    private int leftSon(int i) {
        return (3 * i) - 1;  // Índice del primer hijo
    }

    private int middleSon(int i) {
        return (3 * i);  // Índice del segundo hijo
    }

    private int rightSon(int i) {
        return (3 * i) + 1;  // Índice del tercer hijo
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
        maxSize *= 2;
        int[] nArray = new int[maxSize];
        System.arraycopy(array, 1, nArray, 1, n);
        array = nArray;
    }

    public void insert(int item) {
        if (n + 1 == maxSize) {
            resize();
        }
        n++;
        array[n] = item;
        siftUp(n);
    }

    private void siftDown(int i) {
        int left = leftSon(i);
        int middle = middleSon(i);
        int right = rightSon(i);
        int maxi = i;

        if (left <= n && array[left] > array[maxi]) {
            maxi = left;
        }
        if (middle <= n && array[middle] > array[maxi]) {
            maxi = middle;
        }
        if (right <= n && array[right] > array[maxi]) {
            maxi = right;
        }
        if (maxi != i) {
            int temp = array[i];
            array[i] = array[maxi];
            array[maxi] = temp;
            siftDown(maxi);
        }
    }

    public int extractMax() {
        int r = array[1];
        array[1] = array[n];
        n--;
        siftDown(1);
        return r;
    }

    public void changePriority(int i, int value) {
        int old = array[i];
        array[i] = value;
        if (old > value) {
            siftDown(i);
        } else {
            siftUp(i);
        }
    }

    public void delete(int i) {
        array[i] = Integer.MAX_VALUE;
        siftUp(i);
        extractMax();
    }

    public int[] heapSort1(int[] a) {
        MaxHeapTernario heap = new MaxHeapTernario(a.length);
        int i = a.length - 1;
        while (i >= 0) {
            heap.insert(a[i]);
            i--;
        }
        i = a.length - 1;
        while (i >= 0) {
            a[i] = heap.extractMax();
            i--;
        }
        return a;
    }

    @Override
    public String toString() {
        StringBuilder elements = new StringBuilder();
        for (int i = 1; i < n; i++) {
            elements.append(array[i]).append(", ");
        }
        if (n > 0) {
            elements.append(array[n]);
        }
        return elements.toString();
    }

public static void main(String[] args) {
        MaxHeapTernario heapT = new MaxHeapTernario(2);
        
        //Prueba insert (modificar veces que se realiza el bucle)
        for (int i = 1; i < 100; i++) {
            heapT.insert(i);
        }
        
        System.out.println(heapT);
        
        //Prueba delete (hacer por separado de la prueba extractMax)
        for (int i = 0; i < 20; i++){
            heapT.delete(10); //cambiar valor mientras más grande sea la prueba
        }
        
        System.out.println(heapT);
        
        //Prueba extractMax
        for (int i = 0; i < 20; i++){
            heapT.extractMax();
        }
        
        System.out.println(heapT);

        //Prueba heapSort
        int[] A = new int[100]; // Crea un array de x celdas
        Random random = new Random(); // Crea una instancia de Random

        // Rellena el array con números aleatorios
        for (int i = 0; i < A.length; i++) {
            A[i] = random.nextInt(100000); // Genera un número aleatorio entre 0 y 999999
        }
        
        //crea instancia de heap para ordenar el array y mostrarlo
        MaxHeapTernario heapOrdenarT = new MaxHeapTernario(2);
        heapOrdenarT.heapSort1(A);
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + ", ");
        }
        System.out.println("");
    }
}
