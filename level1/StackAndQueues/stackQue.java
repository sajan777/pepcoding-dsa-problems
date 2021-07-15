import java.util.Arrays;
import java.util.*;
import java.util.Scanner;

public class stackQue {
    

    // ngr next Greater on Right
    public static int[] ngr(int[] arr){
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(0);
        for(int i=1;i<arr.length;i++){
            while(st.size()>0 && arr[st.peek()] < arr[i]){
                int idx  = st.pop();
                ans[idx] = arr[i];
            }
            st.push(i);
        }
        while(st.size()>0){
            ans[st.pop()] = -1;
        } 
         
       return ans;
    }
    // ngr2 Leetcode Approach 1
    public static int[] ngr2(int[] arr){
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(0);
        for(int i=1;i<arr.length;i++){
            while(st.size() > 0 && arr[st.peek()] < arr[i]){
              res[st.pop()]=arr[i];
            }
            st.push(i);
        }
         
        
        for(int i=0;i<arr.length;i++){
            while(st.size()>0 && arr[st.peek()] < arr[i]){
              res[st.pop()]=arr[i];
            }
        }
        while(st.size() > 0){
            res[st.pop()]=-1;
        }
         
       return res;
    }
    // ngr2 leetcode approach 2 Elegant better approach;
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res,-1);
        Stack<Integer> st = new Stack<>();
        st.push(0);
        for(int i=1;i<2*n;i++){
            while(st.size() > 0 && nums[st.peek()] < nums[i%n]){
                res[st.pop()] = nums[i%n];
            }
            st.push(i%n);
        }
        return res;
        
    }
    // ngl -> next greater on left
    public static int[] ngl(int[] arr){
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(arr.length-1);
        for(int i=arr.length-2;i>=0;i--){
            while(st.size()>0 && arr[st.peek()] < arr[i]){
                ans[st.pop()] = arr[i];
            }
            st.push(i);
        }
        while(st.size()>0){
            ans[st.pop()] = -1;
        }

        return ans;
    }

    // nsr -> next smaller on right
    public static int[] nsr(int[] arr){
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(0);
        for(int i=1;i<arr.length;i++){
            while(st.size()>0 && arr[st.peek()] > arr[i]){
                int idx  = st.pop();
                ans[idx] = arr[i];
            }
            st.push(i);
        }
        while(st.size()>0){
            ans[st.pop()] = -1;
        } 
         
       return ans;
    }

    // nsl -> next smaller on left
    public static int[] nsl(int[] arr){
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(arr.length-1);
        for(int i=arr.length-2;i>=0;i--){
            while(st.size()>0 && arr[st.peek()] > arr[i]){
                ans[st.pop()] = arr[i];
            }
            st.push(i);
        }
        while(st.size()>0){
            ans[st.pop()] = -1;
        }

        return ans;
    }

    
    
    public static int[] stockSpan(int[] arr){
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(arr.length-1);
        for(int i=arr.length-2;i>=0;i--){
            while(st.size()>0 && arr[st.peek()] < arr[i]){
                ans[st.pop()] = i;
            }
            st.push(i);
        }
        while(st.size()>0){
            ans[st.pop()] = -1;
        }
        for(int i=0;i<ans.length;i++){
            ans[i] = i-ans[i];
        }
        return ans;
    }


    // left smaller index for histogram 
    public static int[] lsi(int[] arr){
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(arr.length-1);
        for(int i=arr.length-2;i>=0;i--){
            while(st.size()>0 && arr[st.peek()] > arr[i]){
                ans[st.pop()] = i;
            }
            st.push(i);
        }
        while(st.size()>0){
            ans[st.pop()] = -1;
        }
        return ans;
    }
    // right  small index for histogram
    public static int[] rsi(int[] arr){
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(0);
        for(int i=1;i<arr.length;i++){
            while(st.size()>0 && arr[st.peek()] > arr[i]){
                ans[st.pop()] = i;
            }
            st.push(i);
        }
        while(st.size()>0){
            ans[st.pop()] = arr.length;
        } 
         
       return ans;
    }


    // histogram
    public static int histogram(int[] a){
        int[] lsi = lsi(a);
        int[] rsi = rsi(a);
        int area = 0;

        // if asked for index of largest area
        // int st = 0;
        // int end = 0;

        for(int i=0;i<lsi.length;i++){
            int width = rsi[i]-lsi[i]-1;
            int height = a[i];
            // area = Math.max(area,width*height);
            
            if(area < width*height){
                area = width*height;
                // st = lsi[i];
                // end = rsi[i];
            }
        }
        // System.err.println("Max Area Exist in"+(st+1)+" "+(end-1));
        // System.out.println(area);
        return area;
    }

    // leetcode 85 Maximal Rectangle
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int[] arr = new int[matrix[0].length];
        int res = 0;
        for(int i=0;i<matrix.length;i++){
            // prepare array for largest area histogram
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j] == '0'){
                    arr[j] = 0;
                }else{
                    arr[j] += 1;
                }
            }
            res = Math.max(res, histogram(arr));
        }

        return res;
    }




    // right greater index
    public static int[] rightGreaterIndex(int[] arr){
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(0);
        for(int i=1;i<arr.length;i++){
            while(st.size()>0 && arr[st.peek()] < arr[i]){
                int idx  = st.pop();
                ans[idx] = i;
            }
            st.push(i);
        }
        while(st.size()>0){
            ans[st.pop()] = arr.length;
        } 
         
       return ans;
    }

    public static void slidingWindowMax(int[] a,int k){
        int[] rgi = rightGreaterIndex(a);

        int j = 0;
        for(int i=0;i<=a.length-k;i++){
            if(i > j){
                j = i;
            }
            while(i+k > rgi[j]){
                j = rgi[j];
            }
            System.out.println(a[j]);
        }
    }

    
    
    // celebrity problem
    public static void findCelebrity(int[][] arr) {
        Stack<Integer> st = new Stack<>();
        for(int i=0;i<arr.length;i++){
            st.push(i);
        }
        while(st.size()>1){
          int i = st.pop();
          int j = st.pop();
          if(arr[i][j] == 1){
              st.push(j);
          }else{
              st.push(i);
          }
        }
        int potential = st.pop();
        boolean celeb = true;
        for(int k=0;k<arr[0].length;k++){
            if(arr[potential][k] == 1){
                celeb =false;
                break;
            }
        }
        for(int k=0;k<arr.length;k++){
            if(k!=potential && arr[k][potential] == 0){
                celeb =false;
                break;
            }
        }
          
        if(celeb == true){
            System.out.println(potential);
        }else{
            System.out.println("none");
        }
          
      }
    

    //   for sorting 2d array start time in mergeInterval
    public static class Pair  implements Comparable<Pair>{
        int st = 0;
        int end = 0;

        public Pair(int st,int end){
            this.st = st;
            this.end = end;
        }
        public int compareTo(Pair o){
            return this.st-o.st;
            // + this is greater
            // - this is smaller
            // 0 this is equal
        }
    }
    // merge overlapping interval
    public static void mergeOverlappingIntervals(int[][] arr) {
        
        Pair[] pairs = new Pair[arr.length];
        for(int i=0;i<arr.length;i++){
            pairs[i] = new Pair(arr[i][0],arr[i][1]);
        }
        Arrays.sort(pairs);
        
        for(Pair p:pairs){
            System.out.println(p.st+" "+p.end);
        }
        Stack<Pair> st = new Stack<>();
        st.push(pairs[0]);
        for(int i=1;i<pairs.length;i++){
            Pair p = pairs[i];

            if(p.st <= st.peek().end){
                // there can be merging (end time may update or not)
                if(p.end > st.peek().end){
                    st.peek().end = p.end;
                }
            }else{
                st.push(p);
            }
        }
        Stack<Pair> rst = new Stack<>(); //reverse stack
        while(st.size() > 0){
            rst.push(st.pop());
        }
        while(rst.size() > 0){
            Pair rem = rst.pop();
            // System.out.println("Ans:--------------------");
            // System.out.println(rem.st+" "+rem.end);
        }
        
    }

    // smallest-number-following-pattern
    public static void smallestNumberFollowingPattern(String str){
        Stack<Integer> st = new Stack<>();
        int count  = 1;
        for(int i=0;i<str.length();i++){

            if(str.charAt(i) == 'd'){
                st.push(count);
                count++;
            }else{
                st.push(count);
                count++;
                while(st.size()>0){
                    System.out.print(st.pop()+"");
                }
            }

        }
        st.push(count);
        while(st.size()>0){
            System.out.print(st.pop()+"");
        }
    }
    
    public static void que(){
        // int[] arr = {10,6,12,5,3,2,4,8,1};
        // int[] ngr = ngr(arr);
        // System.out.println(Arrays.toString(ngr));
        // int[] ngl = ngl(arr);
        // System.out.println(Arrays.toString(ngl));
        // int[] nsr = nsr(arr);
        // System.out.println(Arrays.toString(nsr));
        // int[] nsl = nsl(arr);
        // System.out.println(Arrays.toString(nsl));

        // int[] stockspan = stockSpan(arr);
        // System.out.println(Arrays.toString(stockspan));

        int[][] arr = {
            {22,28},
            {1,8},
            {25,27},
            {14,19},
            {27,30},
            {5,12}
        };
        mergeOverlappingIntervals(arr);
    }
    
    public static void main(String[] args){
        que();
    }
}