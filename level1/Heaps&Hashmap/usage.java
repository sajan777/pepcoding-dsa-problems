import java.util.*;
public class usage {
    
    public static void fun() {
        //default priority -> min
        PriorityQueue<Integer> pque = new PriorityQueue<>();
        pque.add(10);
        pque.add(20);
        pque.add(9);
        // System.out.println(pque.peek());
        pque.add(7);
        pque.add(11);
        // System.out.println(pque.peek());
        while(pque.size() > 0){
            int rem = pque.remove();
            System.out.print(rem+" ");
        }

        System.out.println();
        // priority -> max
        PriorityQueue<Integer> pque2 = new PriorityQueue<>(Collections.reverseOrder());
        pque2.add(10);
        pque2.add(20);
        pque2.add(9);
        // System.out.println(pque2.peek());
        pque2.add(7);
        pque2.add(11);
        // System.out.println(pque2.peek());
        while(pque2.size() > 0){
            System.out.println(pque2);
            int rem = pque2.remove();
            System.out.print(rem+" ");
        }
    }

    // k Largest Elements 
    // Shreesh Approach best Approach
    public static void kLargest(int[] arr,int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // Add K elements to the Queue
        for(int i=0;i<k;i++){
            pq.add(arr[i]);
        }
        // Process Remaining elements
        for(int i=k;i<arr.length;i++){
            // if the upcoming element is larger then the smallest element from the p.queue then remove the smallest/peek of the pqueue
            // to add next valid candidate
            if(arr[i] > pq.peek()){
                pq.remove();
                pq.add(arr[i]);
            }
        }
        // Print pQueue
        while (pq.size() > 0) {
            System.out.println(pq.remove());
        }
    }
    // My Approach Bad
    public static void kLargest2(int[] arr,int k){
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(Integer i:arr){
            pq.add(i);
        }
        while (pq.size() > 0  && k > 0) {
            ans.add(pq.remove());
            k--;
        }
        for(int i=ans.size()-1;i>=0;i--){
            System.out.println(ans.get(i));
        }
    }


    // Sort k Sorted Array
    public static void sortKSorted(int[] arr,int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // fill priority queue with k elements
        for(int i=0;i<k;i++){
            pq.add(arr[i]);
        }
        // process left out elements

        // shreesh approach
        for(int i=k;i<arr.length;i++){
            // add the el to queue first
            pq.add(arr[i]);
            // remove and print peek
            System.out.println(pq.remove());
        }
        
        // My Approach also good

        // for(int i=k;i<arr.length;i++){
        //     if(arr[i] > pq.peek()){
        //         System.out.println(pq.remove());
        //         pq.add(arr[i]);
        //     }else{
        //         System.out.println(arr[i]);
        //     }
        // }
        // Print pending elements
        while (pq.size() > 0) {
            System.out.println(pq.remove());
        }
    }

    // Median Priority Queue
    public static class MedianPriorityQueue {
        PriorityQueue<Integer> left;
        PriorityQueue<Integer> right;
    
        public MedianPriorityQueue() {
            //   max heap
            left = new PriorityQueue<>(Collections.reverseOrder());
            // min heap
            right = new PriorityQueue<>();
        }
    
        public void add(int val) {
          // write your code here
          if(right.size() > 0 && right.peek() < val){
            //   if the val is greater then the peek of the right PQ 
              right.add(val);
          }else{
              left.add(val);
          }
           //   diff mgmt
          //   if the differnce ever reach 2 then manage by removing from one and adding to anoteher
          if(left.size() - right.size() == 2){
            right.add(left.remove());
          }else if(right.size() - left.size() == 2){
              left.add(right.remove());
          }
        }
    
        public int remove() {
          // write your code here
            if(this.size() == 0){ 
                System.out.println("Underflow");
                return -1;
            }
            // if the right size is greater then median is on right side peek else left side peek
            if(right.size() > left.size()){
                return right.remove();
            }else{
                return left.remove();
            }
        
        }
    
        public int peek() {
          // write your code here
            if(this.size() == 0){ 
                System.out.println("Underflow");
                return -1;
            }
            // if the right size is greater then median is  right peek else left peek
            if(right.size() > left.size()){
                return right.peek();
            }else{
                return left.peek();
            }
        }
    
        public int size() {
          // write your code here
          return left.size()+right.size();

        }
    }

    // working code
    // public static void leetcode295() {
    //     PriorityQueue<Integer> left;    
    //     PriorityQueue<Integer> right;
    
    //     public MedianFinder() {
    // //         max PQ
    //         left = new PriorityQueue<>(Collections.reverseOrder());   
    // //         min PQ
    //         right = new PriorityQueue<>();
    //     }
        
    //     public void addNum(int num) {
            
    //         if(right.size() > 0 && num > right.peek()){
    //             right.add(num);
    //         }else{
    //             left.add(num);
    //         }
            
    //         if(left.size() - right.size() >= 2){
    //             right.add(left.remove());
    //         }else if(right.size() - left.size() >= 2){
    //             left.add(right.remove());
    //         }
    //     }
        
    //     public double findMedian() {
    //         if(right.size() + left.size() == 0) return 0;
            
    //         if(right.size() > left.size()){
    //             return right.peek();
    //         }else if(right.size() == left.size()){
    //             double d = (right.peek()+left.peek())/2.0;
    //             return d;
    //         }else{
    //             return left.peek();
    //         }
            
    //     }
    // }

    // merge K sorted ArrayList
    public static class Pair implements Comparable<Pair>{
        int li;
        // list index
        int di;
        // data index
        int val;
        // value

        Pair(int li,int di,int val){
            this.li = li;
            this.di = di;
            this.val = val;
        }
        public int compareTo(Pair p){
            return this.val - p.val;
        }
        // PQ calls the comapre To method with Objects example PQ has 2 Pair objects e1 and e2 then in order to compare them PQ calls compareTo fun
        // with e1.compareTo(e2) if +ve e1>e2 , -ve e2>e1 ,0 e2==e1
        // if compare To returns +ve then ->this.val is greater
        // if negative then ->p.val is greater
        // 0 if equal
    }
    public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists){
        ArrayList<Integer> rv = new ArrayList<>();
        
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for(int i=0;i<lists.size();i++){
            Pair p  = new Pair(i, 0, lists.get(i).get(0));
            pq.add(p);
        }
        
        while(pq.size() > 0){
            Pair rem = pq.remove();
            rv.add(rem.val);
            rem.di++;
            if(rem.di < lists.get(rem.li).size()){
                rem.val = lists.get(rem.li).get(rem.di);
                pq.add(rem);
            }
        }

        return rv;
     }
    

    public static void main(String[] args) {
        fun();
    }
}