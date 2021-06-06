import java.util.*;

class stack {
    private int[] arr;
    private int idx = 0;
    public stack(int cap){
        arr = new int[cap];
    }
    public void push(int val){
        if(this.idx == arr.length){
            System.out.println("Stack OverFlow");
            return;
        }
        arr[this.idx] = val;
        this.idx++;
    }
    public int pop(){
        if(this.idx == 0){
            System.out.println("Stack UnderFlow");
            return -1;
        }
        int rem = arr[this.idx-1];
        this.idx--;
        return rem;
    }
    public int peek(){
        if(this.idx == 0){
            System.out.println("Stack UnderFlow");
            return -1;
        }
        int rem = arr[this.idx-1];
        return rem;
    }
    public boolean isEmpty(){
        return this.idx == 0;
    }
    public int size(){
        return this.idx;
    }
    public void display(){
        System.out.print("[");
        for(int i=0;i<this.idx;i++){
            System.out.print(arr[i]+", ");
        }
        System.out.print("]");
    }
}

class DynamicStack {
    private ArrayList<Integer> arr;
    private int idx = 0;
    public DynamicStack(){
        arr = new ArrayList<>();
    }
    public void push(int val){
        if(this.arr.size() == 10000){
            System.out.println("Stack Overflow");
            return;
        }
        arr.add(val);
        this.idx++;
    }
    public int pop(){
        if(idx == 0){
            System.out.println("Stack Underflow");
            return -1;
        }
        int rem = arr.remove(this.idx - 1);
        this.idx--;
        return rem;
    }
    public int peek(){
        if(this.idx == 0){
            System.out.println("Stack UnderFlow");
            return -1;
        }
        int rem = arr.get(this.idx - 1);
        return rem;
    }
    public boolean isEmpty(){
        return this.arr.size() == 0;
    }
    public int size(){
        return this.arr.size();
    }
    public void display(){
       System.out.println(this.arr);
    }

}

public class mystack {
    // Demo---------------------------
    public static void demo(){
        // 1.creation
        Stack<Integer> st = new Stack<>();
        // 2.push
        st.push(10);
        st.push(20);
        st.push(30);
        st.push(40);
        st.push(50);
        // 3.printing

        System.out.println(st);
        
        // 4.removal + peek
        while(st.size() > 0){
            // get
            // int rem  = st.peek();

            // remove
            // st.pop();
            
            // get+rem
            int rem = st.pop();
            // print
            System.out.println(rem+"   "+ st);
        }

    }

    public static void main(String[] args){
        // demo();
        // stack st = new stack(10);
        // st.push(10);
        // st.push(15);
        // st.push(20);
        // st.push(30);
        // st.push(50);
        // st.display();
        // System.out.println();
        // System.out.println("size: "+st.size());
        // System.out.println("isEmpty: "+st.isEmpty());
        // System.out.println("pop: "+st.pop());
        // System.out.println("peek: "+st.peek());
        // st.display();
        // System.out.println();
        // System.out.println("pop: "+st.pop());
        // System.out.println("peek: "+st.peek());


        DynamicStack dst = new DynamicStack();
        dst.push(10);
        dst.push(20);
        dst.push(30);
        dst.push(40);
        dst.push(50);
        dst.display();
        System.out.println("size: "+dst.size());
        System.out.println("isEmpty: "+dst.isEmpty());
        System.out.println("pop: "+dst.pop());
        System.out.println("peek: "+dst.peek());
        dst.display();
        System.out.println();
        System.out.println("pop: "+dst.pop());
        System.out.println("peek: "+dst.peek());
        
    }


}