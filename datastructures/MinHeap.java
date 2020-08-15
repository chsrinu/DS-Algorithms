package datastructures;

/**
 * Create an API for MIN heap with method add,getMin,remove methods
 */

public class MinHeap {
    int[] ar;
    int maxSize;
    int size = 0;
    private static final int minPosition = 1;
    public MinHeap(int maxSize){
        this.maxSize = maxSize+1;
        ar = new int[maxSize+1];
        ar[0] = Integer.MIN_VALUE;
    }
    public static void main(String[] args){
        MinHeap heap = new MinHeap(2);
        heap.add(5);
        heap.add(2);
        heap.printArray();
        System.out.println(heap.getMin());
        heap.remove();
        System.out.println(heap.getMin());
        heap.remove();
        System.out.println(heap.getMin());
        heap.remove();
        heap.printArray();
        heap.add(20);
        heap.add(19);
        heap.printArray();
    }
    public void printArray(){
        System.out.println("Printing array");
        for(int i:ar)
            System.out.println(i);
    }
    public int getMin(){
        if(size>0)
            return ar[minPosition];
        System.out.println("Heap is empty, returning default value");
        return -1;
    }

    public int parent(int position){
        return position/2;
    }
    public void add(int element){
        if(size<maxSize){
            ar[++size] = element;
            heapify_up(size); 
        }else{
            System.out.println("Notional limit breach");
        }
    }
    public void heapify_up(int newElementIndex){
        if(newElementIndex>0 && ar[parent(newElementIndex)]>ar[newElementIndex]){
            swap(parent(newElementIndex),newElementIndex);
            heapify_up(parent(newElementIndex));
        }
    }
    public int left(int parent){
        return (2*parent);
    }
    public int right(int parent){
        return (2*parent)+1;
    }

    private void heapify_down(int parent){
        int left = left(parent);
        int right = right(parent);
        int smallest = parent;
        if(left<size && ar[left]<ar[parent])
            smallest = left;
        if(right<size && ar[right]<ar[smallest])
            smallest = right;
        if(smallest!=parent){
            swap(smallest,parent);
            heapify_down(smallest);
        }
            
    }
    public void swap(int src, int target){
        int temp = ar[src];
        ar[src] = ar[target];
        ar[target]=temp;
    }
    public void remove(){
        if(size>0){
            swap(size,minPosition);
            ar[size--]=0;
            heapify_down(minPosition);
        }else{
            System.out.println("No Min Available");
        }
    }
}