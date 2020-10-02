package datastructures;

public class MaxHeap {
    int maxSize;
    int[] ar;
    int size = 0;
    public static final int top = 1;
    public MaxHeap(int maxSize){
        this.maxSize = maxSize+1;
        ar = new int[this.maxSize];
        ar[size] = Integer.MAX_VALUE;
    }

    public static void main(String[] args){
        MaxHeap heap = new MaxHeap(2);
        heap.add(2);
        heap.add(5);
        heap.add(1);
        heap.printArray();
        System.out.println(heap.getMax());
        heap.remove();
        System.out.println(heap.getMax());
        heap.remove();
        System.out.println(heap.getMax());
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
    public int parent(int parentPos){
        return parentPos/2;
    }
    public int left(int parentPos){
        return 2*parentPos;
    }
    public int right(int parentPos){
        return 2*parentPos+1;
    }
    public void add(int element){
        if(size+1<maxSize){
            ar[++size] = element;
            heap_up(size);
        }else{
            System.out.println("Heap full, pop elements to add more");
        }
        
    }
    private void heap_up(int newPos){
        int current = size;
        while(ar[current]>ar[parent(current)]){
            swap(current,parent(current));
            current = parent(current);
        }
    }
    private void swap(int src, int target){
        int temp = ar[src];
        ar[src] = ar[target];
        ar[target] = temp;
    }

    public int getMax(){
        if(size>0)
            return ar[top];
        System.out.println("Heap is empty, returning default");
        return -1;
    }
    public int remove(){
        int currentMax = Integer.MIN_VALUE;
        if(size>0){
            currentMax = ar[top];
            swap(size,top);
            size--;
            heapify_down(top);
        }
        return currentMax;
    }
    private void heapify_down(int pos){
        int biggest = pos;
        if(left(pos)<=size && ar[left(pos)]>ar[biggest])
            biggest = left(pos);
        if(right(pos)<=size && ar[right(pos)]>ar[biggest])
            biggest = right(pos);
        if(biggest!=pos){
            swap(biggest,pos);
            heapify_down(biggest);
        }
    }
    public boolean isEmpty(){
        return this.size<=0;
    }

}