import java.util.*;

// min priority queue
class priority{
    private ArrayList<Integer> data;
    private boolean flag;
    // flag-> false (MIN prioritu)
    // flag->  true (MAX priority)
    
    public priority(){
        data = new ArrayList<>();
        flag = false;
    }
    public priority(boolean flag){
        data = new ArrayList<>();
        this.flag = flag;
    }
    
    private void processArray(int[] arr){
        // COMPLEXITY GREATER THEN 0(NLOGN)
        // for(int i=0;i<arr.length;i++){
        //     add(arr[i]);
        // }

        // COMPLEXITY 0(N)
        //1. fill data list from array
        for(int i=0;i<arr.length;i++){
            data.add(arr[i]);
        }
        // 2. call downHeapify from end
        for(int i=arr.length-1;i>=0;i--){
            downheapify(i);
        }
    }
    public priority(int[] arr){
        data = new ArrayList<>();
        flag = false;
        processArray(arr);
    }

    public priority(int[] arr,boolean flag){
        data = new ArrayList<>();
        this.flag = flag;
        processArray(arr);
    }

    private int checkPriority(int child,int parent){
        if(flag == true){
            // max Priority
            if(data.get(child) > data.get(parent)){
                return 1;
            }
        }else{
            // min Priority
            if(data.get(child) < data.get(parent)){
                return 1;
            }
        }
        return 0;
    }

    private void swap(int i,int j){
        int temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }

    private void upheapify(int idx){
        if (idx == 0) return;

        int pi = (idx-1)/2;
        if(checkPriority(idx, pi) > 0){ //priority for minheap voilated
            swap(idx,pi);
            upheapify(pi);
        }
    }

    public void add(int val){
        data.add(val);
        // maintain heapify
        upheapify(data.size()-1);
    }

    private void downheapify(int idx){

       int minIndx = idx;
       int lci = 2*idx+1;
       int rci = 2*idx+2;

        // by changing the conditions we can convert to max PQ
       if(lci < data.size() && checkPriority(lci, minIndx) > 0){
            minIndx = lci;
       }
       if(rci < data.size() && checkPriority(rci, minIndx) > 0){
           minIndx = rci;
       }
       if(minIndx != idx){
           swap(idx, minIndx);
           downheapify(minIndx);
       }
    }

    public int remove(){
        if(data.size() == 0){
            System.out.println("Underflow");
            return -1;      
        }                            
        swap(0,data.size()-1);           
        int val = data.remove(data.size()-1);
        // maintain heap Order property
        downheapify(0);
        return val;
    }
    public int peek(){
        if(data.size() == 0){
            System.out.println("Underflow");
            return -1;
        }
        return data.get(0);
    }
    
    public int size(){
        return data.size();
    }
    
    public void display(){
        System.out.println(data);
    }
}

public class pq {
    
    public static void fun() {

        System.out.println("MIN HEAP::::::::::::::::::::::::::::::::::::::::::");
        int[] arr = {10,20,15,35,30,25,27,40,42};
        priority pq = new priority(arr);
        // pq.add(10);
        // pq.add(20);
        // pq.add(15);
        // pq.add(35);
        // pq.add(30);
        // pq.add(25);
        // pq.add(27);
        // pq.add(40);
        // pq.add(42);
        pq.display();
        System.out.println(pq.peek());
        System.out.println(pq.remove());
        pq.display();

        System.out.println("MAX HEAP::::::::::::::::::::::::::::::::::::::::::");
        priority pq2 = new priority(arr,true);
        // pq2.add(10);
        // pq2.add(20);
        // pq2.add(15);
        // pq2.add(35);
        // pq2.add(30);
        // pq.add(25);
        // pq2.add(27);
        // pq2.add(40);
        // pq2.add(42);
        pq2.display();
        System.out.println(pq2.peek());
        System.out.println(pq2.remove());
        pq2.display();
    }

    public static void main(String[] args) {
        fun();
    }
}
 