import java.util.*;

public class ques {

    public static class QueueToStackAdapter {
        Queue<Integer> mainQ;
        Queue<Integer> helperQ;
    
        public QueueToStackAdapter() {
          mainQ = new ArrayDeque<>();
          helperQ = new ArrayDeque<>();
        }
    
        int size() {
          // write your code here
          return mainQ.size();
        }
    
        void push(int val) {
          // write your code here
          mainQ.add(val);
        }
    
        int pop() {
            if(mainQ.size == 0){
                System.out.println("Stack underflow");
                return -1;
            }
          // write your code here
        //   n -1 remove from mainQ add in helper Q
          while(mainQ.size() > 1){
            helperQ.add(mainQ.remove());  
          }
        //   get the value of nth element from mainQ
          int val = mainQ.remove();
        //   change Refrence
          mainQ = helperQ;
          helperQ = new ArrayDeque<>();
        //   return nth element
          return val;
        }
    
        int top() {
            if(mainQ.size == 0){
                System.out.println("Stack underflow");
                return -1;
            }
            //   n -1 remove from mainQ add in helper Q
          while(mainQ.size() > 1){
            helperQ.add(mainQ.remove());  
          }
          //   get the value of nth element from mainQ
          int val = mainQ.remove();
        //   add the nth element to helper
          helperQ.add(val);
          //   change Refrence
          mainQ = helperQ;
          helperQ = new ArrayDeque<>();
          return val;
        }
      }

    public static class QueueToStackAdapter {
        Queue<Integer> mainQ;
        Queue<Integer> helperQ;
    
        public QueueToStackAdapter() {
          mainQ = new ArrayDeque<>();
          helperQ = new ArrayDeque<>();
        }
    
        int size() {
          // write your code here
          return mainQ.size();
        }
    
        void push(int val) {
          // write your code here
        //   if(mainQ.size() == 0){
        //       mainQ.add(val);
        //       return;
        //   }
        // //   Fill helper Q from main Q
        // while(mainQ.size() > 0){
        //   helperQ.add(mainQ.remove());
        // }
        // //   add new element in mainQ
        // mainQ.add(val);
        // // refill mainQ from helperQ
        // while(helperQ.size() > 0){
        //     mainQ.add(helperQ.remove());
        // }
        helperQ.add(val);
        while(mainQ.size() > 0){
            helperQ.add(mainQ.remove());
        }
        mainQ = helperQ;
        helperQ =new ArrayDeque<>();
            
        }
    
        int pop() {
            if(mainQ.size() == 0){
                System.out.println("Stack undeflow");
                return -1;
            }
          // write your code here
          return mainQ.remove();
        }
    
        int top() {
          // write your code here
          if(mainQ.size() == 0){
                System.out.println("Stack undeflow");
                return -1;
            }
          // write your code here
          return mainQ.peek();
        }
      }
    
    public static class StackToQueueAdapter {
        Stack<Integer> mainS;
        Stack<Integer> helperS;
    
        public StackToQueueAdapter() {
          mainS = new Stack<>();
          helperS = new Stack<>();
        }
    
        int size() {
          // write your code here
          return mainS.size();
        }
    
        void add(int val) {
          // write your code here
          mainS.push(val);
        }
    
        int remove() {
            if(mainS.size() == 0){
                System.out.println("Queue underflow");
                return -1;
            }
          // write your code here
        //   MOVE N-1 elemente main->helper
          while(mainS.size() > 1){
            helperS.push(mainS.pop());  
          }
        //   pop nth element
          int val = mainS.pop();
        //   refill mainStack from helperStack
          while(helperS.size() > 0){
              mainS.push(helperS.pop());
          }
          return val;
        }
    
        int peek() {
            if(mainS.size() == 0){
                System.out.println("Queue underflow");
                return -1;
            }
          // write your code here
          while(mainS.size() > 1){
            helperS.push(mainS.pop());  
          }
          int val = mainS.pop();
          helperS.push(val);
          while(helperS.size() > 0){
              mainS.push(helperS.pop());
          }
          return val;
        }
      }
    
    public static class StackToQueueAdapter {
        Stack<Integer> mainS;
        Stack<Integer> helperS;
    
        public StackToQueueAdapter() {
          mainS = new Stack<>();
          helperS = new Stack<>();
        }
    
        int size() {
          // write your code here
          return mainS.size();
        }
    
        void add(int val) {
          // write your code here
        //   fill helper stack from main s
        while(mainS.size() > 0){
            helperS.push(mainS.pop());
        }
        // push val to mainS
        mainS.push(val);
        // refill the mainS using helper
        while(helperS.size() > 0){
            mainS.push(helperS.pop());
        }
          
        }
    
        int remove() {
            if(mainS.size() == 0){
                System.out.println("Queue underflow");
                return -1;
            }
          // write your code here
          return mainS.pop();
        }
    
        int peek() {
            if(mainS.size() == 0){
                System.out.println("Queue underflow");
                return -1;
            }
          // write your code here
          return mainS.peek();
        }
      }
    
    public static class TwoStack {
        int[] data;
        int tos1;
        int tos2;
    
        public TwoStack(int cap) {
          // write your code here
          data = new int[cap];
          tos1 = -1;
          tos2 = cap;
        }
    
        int size1() {
          // write your code here
          return tos1 + 1;
        }
    
        int size2() {
          // write your code here
          return data.length - tos2;
        }
    
        void push1(int val) {
          // write your code here
          if(tos1 + 1 < tos2){
                data[tos1 + 1] = val;
                tos1++;
            }else{
              System.out.println("Stack overflow");
            }

        }
    
        void push2(int val) {
          // write your code here
          if(tos2 - 1 > tos1){
              data[tos2 - 1] = val;
              tos2--;
            }else{
                System.out.println("Stack overflow");
            }
        }
    
        int pop1() {
          // write your code here
          if(tos1 == -1){
              System.out.println("Stack underflow");
              return -1;
          }
          
          int val = data[tos1];
          tos1--;
          return val;
        }
    
        int pop2() {
          // write your code here
          if(tos2 == data.length){
              System.out.println("Stack underflow");
              return -1;
          }
          
          int val = data[tos2];
          tos2++;
          return val;
        }
    
        int top1() {
          // write your code here
            if(tos1 == -1){
                System.out.println("Stack underflow");
                return -1;
            }
            return data[tos1];
        }
    
        int top2() {    
          // write your code here
            if(tos2 == data.length){
                System.out.println("Stack underflow");
                return -1;
            }
            return data[tos2];
        }
    }  

      public static void Questions() {
        // QueueToStackAdapter q = new QueueToStackAdapter();
    }


    public static void main(String[] args) {
        Questions();
    }
}
