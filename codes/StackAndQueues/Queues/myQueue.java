import java.util.*;

class Queue{
    int[] data;
    int front;
    int size;

    public Queue(int cap){
        data = new int[cap];
        front = 0;
        size = 0;
    }
    public void add(int val){
        if( size == data.length){
            System.out.println("Queue 0verflow");
            return;
        }
        int idx = (front+size)%data.length;
        data[idx] = val;
        size++;        
    }
    public int remove(){
        if(size == 0){
            System.out.println("Queue underflow");
            return -1;
        }
        front = (front+1) % data.length;
        int val = data[front];
        size--;
        return val;
    }
    public int peek(){
        if(size == 0){
            System.out.println("Queue underflow");
            return -1;
        }
        return data[front];
    }
    public int size(){
        return size;
    }
    public void display(){
        for(int i=0;i<size;i++){
            int indx = (i+front)%data.length;
            System.out.print(data[indx]+", ");
        }
        System.out.println();
    }
}



public class myQueue {
    public static void demo(){
        // Queue is an interface that is implemented by dataayDeque interface that is implemented by dequeu
        // Queue<Integer> qu = new dataayDeque<>();
        // qu.add(10);
        // qu.add(20);
        // qu.add(30);
        // qu.add(40);
        // qu.add(50);
        // qu.add(60);
        // System.out.println(qu);
        // System.out.println(qu.peek());
        // System.out.println(qu.remove());
        // System.out.println(qu.peek());
        // System.out.println(qu.remove());
        // System.out.println(qu.peek());
        // System.out.println(qu.remove());
        // System.out.println(qu.size());

        Queue q = new Queue(10);
        q.add(10);
        q.add(20);
        q.add(30);
        q.add(40);
        q.add(50);
    }

    public static void main(String[] args) {
        demo();    
    }
}
