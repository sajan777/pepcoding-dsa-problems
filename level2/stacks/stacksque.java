import java.util.*;

public class stacksque {
    
    // Next greater element to the right
    public static int[] solve(int[] arr){
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

    // Next greater element to the left
    public static int[] solve2(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(n-1);
        for(int i=n-2;i>=0;i--){
            while(st.size() > 0 && arr[st.peek()] < arr[i]){
                int idx = st.pop();
                ans[idx] = arr[i];
            }
            st.push(i);
        }
        while(st.size() > 0){
            ans[st.pop()] = -1;
        }
        return ans;
    }
    // Next Smaller Element To The Right
    public static int[] solve3(int[] arr) {
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
    // Next Smaller Element To The Left
    public static int[] solve4(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(n-1);
        for(int i=n-2;i>=0;i--){
            while(st.size() > 0 && arr[st.peek()] > arr[i]){
                int idx = st.pop();
                ans[idx] = arr[i];
            }
            st.push(i);
        }
        while(st.size() > 0){
            ans[st.pop()] = -1;
        }
        return ans;
    }

    // Next Greater Element I
    // https://leetcode.com/problems/next-greater-element-i/submissions/
    public static int[] nextGreaterElement(int[] arr, int[] query) {
        HashMap<Integer,Integer> map = new HashMap<>();
        Stack<Integer> st = new Stack<>();
        st.push(0);
        for(int i=1;i<arr.length;i++){
            while(st.size()>0 && arr[st.peek()] < arr[i]){
                int idx  = st.pop();
                map.put(arr[idx],arr[i]);
            }
            st.push(i);
        }
        int[] ans = new int[query.length];
        for(int i=0;i<query.length;i++){
            ans[i] = map.getOrDefault(query[i], -1);
        }
        return ans;
    }
    //Next Greater Element Ii
    // https://leetcode.com/problems/next-greater-element-ii/
    public static int[] nextGreaterElementII(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(0);
        Arrays.fill(ans,-1);
        for(int i=1;i<2*n;i++){
            int indx = i%n;
            while(st.size()>0 && arr[st.peek()] < arr[indx]){
                int idx  = st.pop();
                ans[idx] = arr[indx];
            }
            st.push(indx);
        }
        return ans;
    }
    // Largest Area Histogram 2
    // https://leetcode.com/problems/largest-rectangle-in-histogram/
    // Next Smaller index To The Right
    public static int[] rsi(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(0);
        for(int i=1;i<arr.length;i++){
            while(st.size()>0 && arr[st.peek()] > arr[i]){
                int idx  = st.pop();
                ans[idx] = i;
            }
            st.push(i);
        }
        while(st.size()>0){
            ans[st.pop()] = n;
        } 
       return ans;
    }
    // Next Smaller index  To The Left
    public static int[] lsi(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(n-1);
        for(int i=n-2;i>=0;i--){
            while(st.size() > 0 && arr[st.peek()] > arr[i]){
                int idx = st.pop();
                ans[idx] = i;
            }
            st.push(i);
        }
        while(st.size() > 0){
            ans[st.pop()] = -1;
        }
        return ans;
    }
    public static int largestRectangleArea(int[] heights) {
        int[] lsi = lsi(heights);
        int[] rsi = rsi(heights);

        int area  = 0;
        for(int i=0;i<heights.length;i++){
            int height = heights[i];
            int width = rsi[i]-lsi[i]-1;
            area  = Math.max(area,width*height);
        }
        return area;
    }

    // Maximal Rectangle
    // https://leetcode.com/problems/maximal-rectangle/
    public static int maximalRectangle(int[][] arr) {
        int res = 0;
        int[] temp = new int[arr[0].length];
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                if(arr[i][j] == 0){
                    temp[j] = 0;
                }else{
                    temp[j] += 1;
                }
            }
            res = Math.max(res,largestRectangleArea(temp));
        }
        return res;           
    }

    // Validate Stack Sequences
    // https://leetcode.com/problems/validate-stack-sequences/
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> st = new Stack<>();
        int j = 0;
        for(int i=0;i<pushed.length;i++){
            st.push(pushed[i]);
            while(st.size() > 0 && popped[j] == st.peek()){
                st.pop();
                j++;
            }
        }
        return st.size() == 0;
    }

    // Minimum Add To Make Parentheses Valid
    // https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
    public static int minAddToMakeValid(String s) {
        Stack<Character> st = new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == ')'){
                if(st.size() > 0 && st.peek() == '('){
                    st.pop();
                }else{
                    st.push(ch);
                }
            }else{
                st.push(ch);
            }
        }
        return st.size();
    }

    // https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/
    // 1963. Minimum Number of Swaps to Make the String Balanced
    public int minSwaps(String s) {
        Stack<Character> st = new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == ']'){
                if(st.size() > 0 && st.peek() == '['){
                    st.pop();
                }else{
                    st.push(ch);
                }
            }else{
                st.push(ch);
            }
        }
        int pairs = st.size() / 2;
        return (int)Math.ceil(pairs/2.0);
    }

    // Count the Reversals 
    // https://practice.geeksforgeeks.org/problems/count-the-reversals0401/1
    int countRev (String s){
        Stack<Character> st = new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == '}'){
                if(st.size() > 0 && st.peek() == '{'){
                    st.pop();
                }else{
                    st.push(ch);
                }
            }else{
                st.push(ch);
            }
        }
        if(st.size() %  2 != 0) return -1;
        int opening = 0;
        int closing = 0;
        while(st.size() > 0){
            char ch = st.pop();
            if(ch == '{'){
                opening += 1;
            }else{
                closing += 1;
            }
        }
        return (int)(Math.ceil(opening/2.0)+Math.ceil(closing/2.0));
    }

    // Remove Outermost Parentheses
    // https://leetcode.com/problems/remove-outermost-parentheses/
    // Without Stack
    public String removeOuterParentheses(String s) {
        int op = 0;
        int cl = 0;
        int stIdx = 0;
        StringBuilder str = new StringBuilder("");
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == '('){
                op++;
            }else if(ch == ')'){
                cl++;
            }
            if(op == cl){
                str.append(s.substring(stIdx+1,i));
                // for(int j=stIdx+1;j<i;j++){
                    // str.append(s.charAt(j));
                // }
                op = 0;
                cl = 0;
                stIdx = i+1;
            }
        }
        return str.toString();
    }

    // Score Of Parentheses
    // https://leetcode.com/problems/score-of-parentheses/
    // -1 means opening bracket
    // and number is score ()->1,(A) -> 2*A,AB->A+B
    public int scoreOfParentheses(String s) {
        Stack<Integer> st = new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == '('){
                st.push(-1);
            }else if(st.peek() == -1){
                st.pop();
                st.push(1);
            }else{
                // st.peek() has a number
                int sum = 0;
                while(st.size() > 0 && st.peek() != -1){
                    sum += st.pop();
                }
                st.pop();
                st.push(2*sum);
            }
        }
        int add = 0;
        while(st.size() > 0){
            add += st.pop();
        }
        return add;   
    }

    // Reverse Substrings Between Each Pair Of Parentheses
    // https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
    public String reverseParentheses(String s) {
        Stack<Character> st = new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch != ')'){
                st.push(ch);
            }else{
                StringBuilder str = new StringBuilder();
                while(st.peek() != '('){
                    str.append(st.pop());
                }
                st.pop();
                for(int j=0;j<str.length();j++){
                    st.push(str.charAt(j));
                }
            }
        }
        StringBuilder ans = new StringBuilder();
        while(st.size() > 0){
            ans.append(st.pop());
        }
        ans.reverse();
        return ans.toString();
    }

    // vMinimum Remove To Make Valid Parentheses
    // https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> st = new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == ')'){
                if(st.size() > 0 && s.charAt(st.peek()) == '('){
                    st.pop();
                }else{
                    st.push(i);
                }
            }else if(ch == '('){
                st.push(i);
            }
        }
        StringBuilder str = new StringBuilder();
        for(int i=s.length()-1;i>=0;i--){
            if(st.size() > 0 && st.peek() == i){
                st.pop();
            }else{
                str.append(s.charAt(i));
            }
        }
        return str.reverse().toString();
    }


    // 739. Daily Temperatures
    // https://leetcode.com/problems/daily-temperatures/
    public int[] dailyTemperatures(int[] arr) {
        int[] res = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(0);
        for(int i=1;i<arr.length;i++){
            while(st.size() > 0 && arr[i] > arr[st.peek()]){
                int idx = st.pop();
                res[idx] = i-idx;
            }
            st.push(i);
        }
        return res;   
    }

    // https://leetcode.com/problems/online-stock-span/
    // 901. Online Stock Span
    class StockSpanner {
        private class Pair{
            int val;
            int idx;
            Pair(int val,int idx){
                this.val = val;
                this.idx = idx;
            }
        }
        Stack<Pair> st;
        int indx;
        public StockSpanner() {
            st = new Stack<>();
            indx = -1;
            st.push(new Pair(Integer.MAX_VALUE,indx));  
            indx++;
        }
        
        public int next(int price) {
            while(st.size() > 0 && st.peek().val <= price){
                st.pop();
            }
            int span = indx - st.peek().idx;
            st.push(new Pair(price,indx));
            indx++;
            return span;
        }
    }

    // 1541. Minimum Insertions to Balance a Parentheses String
    public int minInsertions(String s) {
        return 9;
    }

    // https://leetcode.com/problems/backspace-string-compare/
    // 844. Backspace String Compare
    private Stack<Character> createStack(String s){
        Stack<Character> st = new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == '#'){
                if(st.size() > 0){
                    st.pop();
                }
            }else{
                st.push(ch);
            }
        }
        return st;
    } 
    public boolean backspaceCompare(String s, String t) {
        Stack<Character> s1 = createStack(s);
        Stack<Character> s2 = createStack(t);
        if(s1.size() != s2.size()) return false;
        while(s1.size() > 0 && s2.size() > 0){
            if(s1.pop() != s2.pop()) return false;
        }

        return true;
    }

    // Pending questions (9/12/2021)
    
    // https://classroom.pepcoding.com/the-switch-program-2/122/classvideos/6328

    // 636. Exclusive Time of Functions
    // https://leetcode.com/problems/exclusive-time-of-functions/
    
    // 456. 132 Pattern
    // https://leetcode.com/problems/132-pattern/    

    // 735. Asteroid Collision
    // https://leetcode.com/problems/asteroid-collision/
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        for(int val:asteroids){
            if(val > 0){
                st.push(val);
                continue;
            }
            // peek is +ve and smaller then -val in term of size.;
            while(st.size() > 0 && st.peek() < -val && st.peek() > 0 ){
                st.pop();
            }
            // equal in size but opposite in directions
            if(st.size() > 0 && st.peek() == -val){
                st.pop();
            }else if(st.size() == 0 || st.peek() < 0){
                st.push(val);
            }
        }
        int[] res = new int[st.size()];
        for(int i=res.length-1;i>=0;i--){
            res[i] = st.pop();
        }        
        return res;
    }
    

    // 402. Remove K Digits
    // https://leetcode.com/problems/remove-k-digits/
    public String removeKdigits(String num, int k) {
        // Use linkedlist as stack (to remove leading zeroes)
        LinkedList<Character> st = new LinkedList<>();
        for(int i=0;i<num.length();i++){
            char ch = num.charAt(i);
            while(k >0 &&st.size()>0 && st.getLast() > ch){
                k--;
                st.removeLast();
            }
            st.addLast(ch);
        }
        // manage remaining k's
        while(k > 0){
            st.removeLast();
            k--;
        }
        // manage leading zeroes
        while(st.size() > 0 && st.getFirst() == '0'){
            st.removeFirst();
        }
        StringBuilder str = new StringBuilder();
        for(char ch:st){
            str.append(ch);
        }
        return str.length() == 0 ? "0" : str.toString();
    }
    
    // 316. Remove Duplicate Letters
    // https://leetcode.com/problems/remove-duplicate-letters/
    public String removeDuplicateLetters(String s) {
        HashMap<Character,Integer> fmap = new HashMap<>(); //frequency
        HashMap<Character,Boolean> pmap = new HashMap<>(); //presence in stack
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            pmap.put(ch, false);
            int of = fmap.getOrDefault(ch, 0);
            fmap.put(ch,of+1);
        }

        LinkedList<Character> st = new LinkedList<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            fmap.put(ch, fmap.get(ch)-1);
            if(pmap.get(ch) == true){
                continue;
            }
            while(st.size() > 0 && st.getLast() > ch && fmap.get(st.getLast()) > 0){
                char removedChar = st.removeLast();
                pmap.put(removedChar, false);
            }
            st.addLast(ch);
            pmap.put(ch, true);
        }

        StringBuilder str = new StringBuilder();
        for(char ch:st){
            str.append(ch);
        }
        return str.toString();
    }

    // https://leetcode.com/problems/trapping-rain-water/
    // 42. Trapping Rain Water O(N)+O(1)
    public int trap(int[] height) {
        int left = 0;
        int right = height.length-1;
        int lmax = Integer.MIN_VALUE;
        int rmax = Integer.MIN_VALUE;
        int water = 0;
        while(left < right){
            lmax = Math.max(lmax,height[left]);
            rmax = Math.max(rmax,height[right]);
            
            if(lmax < rmax){
                water += (lmax - height[left]);
                left++;
            }else{
                water += (rmax - height[right]);
                right--;
            }
        }
        return water;
    }
    // O(N)+O(1) (using dual iteration)
    public int trap2(int[] height) {
        int owater = 0;
        int maxIndx = 0;
        int rwater = 0; //running water
        for(int i=0;i<height.length;i++){
            if(height[i] >= height[maxIndx]){
                owater += rwater;
                rwater = 0;
                maxIndx = i;
            }
            rwater += (height[maxIndx] - height[i]);
        }
        // some amt of water is not safe;
        rwater = 0;
        int rmaxIndx = height.length-1;
        for(int i=height.length-1;i>=maxIndx;i--){
            if(height[i] >= height[rmaxIndx]){
                owater += rwater;
                rwater = 0;
                rmaxIndx = i;
            }
            rwater += (height[rmaxIndx] - height[i]);
        }
        return owater;
    }
    // O(N)+O(N) (using STACK)
    public int trap3(int[] height) {
        Stack<Integer> st = new Stack<>();
        int water = 0;
        for(int i=0;i<height.length;i++){
            int val = height[i];
            int rmax = val;
            int rmaxIndx = i;

            while(st.size() > 0 && height[st.peek()] < val){
                int cht = height[st.pop()];
                if(st.size() == 0) break;
                int lmaxIndx = st.peek();
                int lmax = height[lmaxIndx];
                water += (Math.min(lmax,rmax) - cht) * (rmaxIndx - lmaxIndx - 1);
            }
            st.push(i);
        }
        return water;
    }

    // 407. Trapping Rain Water II
    // https://leetcode.com/problems/trapping-rain-water-ii/
    public class trwHelper implements Comparable<trwHelper>{
        int x;
        int y;
        int ht;
        public trwHelper(int x,int y,int ht){
            this.x = x;
            this.y = y;
            this.ht = ht;
        }
       public int compareTo(trwHelper o){
           return this.ht - o.ht;
       }
    }
    public void addBoundaryTrappingRainWater(PriorityQueue<trwHelper> pq,int[][] hts,boolean[][] vis){
       // top wall
       for(int c=0;c<hts[0].length;c++){
           if(vis[0][c] == false){
               vis[0][c] = true;
               pq.add(new trwHelper(0, c, hts[0][c]));
           }
       }
       // left wall
       for(int r=0;r<hts.length;r++){
           if(vis[r][0] == false){
               vis[r][0] = true;
               pq.add(new trwHelper(r, 0, hts[r][0]));
           }
       }
       // down wall
       for(int c=0;c<hts[0].length;c++){
           if(vis[hts.length-1][c] == false){
               vis[hts.length-1][c] = true;
               pq.add(new trwHelper(hts.length-1, c, hts[hts.length-1][c]));
           }
       }
       // right wall
       for(int r=0;r<hts.length;r++){
           if(vis[r][hts[0].length-1] == false){
               vis[r][hts[0].length-1] = true;
               pq.add(new trwHelper(r, hts[0].length-1, hts[r][hts[0].length-1]));
           }
       }
   }
   public int trapRainWater(int[][] hts) {
       boolean[][] vis = new boolean[hts.length][hts[0].length];
       PriorityQueue<trwHelper> pq = new PriorityQueue<>();
       int[] xdir = {-1,0,1,0};
       int[] ydir = {0,-1,0,1};

       // add Boundaries
       addBoundaryTrappingRainWater(pq,hts,vis);
       int water = 0;
       while(pq.size() > 0){
           trwHelper rem = pq.remove();
           for(int d=0;d<4;d++){
               int rr = rem.x+xdir[d];
               int cc = rem.y+ydir[d];

               if(rr >= 0 && rr<hts.length && cc>=0 && cc<hts[0].length && vis[rr][cc] == false){
                   vis[rr][cc] = true;
                   if(hts[rr][cc] < rem.ht){
                       water += rem.ht - hts[rr][cc];
                       pq.add(new trwHelper(rr,cc,rem.ht));
                   }else{
                       pq.add(new trwHelper(rr,cc,hts[rr][cc]));
                   }
               }
           }
       }
       return water;
    }

    // 224. Basic Calculator
    // https://leetcode.com/problems/basic-calculator/
    public int calculate(String s) {
        Stack<Integer> st = new Stack<>();
        int sign = 1;
        int sum = 0;
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == ' '){
                continue;
            }else if(ch >= '0' && ch <= '9'){
                long n = 0;
                while(i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                    n *= 10;
                    n += (int)(s.charAt(i)-'0');
                    i++;
                }
                i--;
                n *= sign;
                sum += (int)n;
                sign = 1;
            }else if(ch == '('){
                st.push(sum);
                st.push(sign);
                sign = 1;
                sum = 0;
            }else if(ch == ')'){
                sum *= st.pop(); //multiply sign from stack
                sum += st.pop(); //add the sum to the olsum;
            }else if(ch == '-'){
                sign *= -1;
            }else{
                // ch is + nothing to do
            }
        }   
        return sum;
    }

    // // 227. Basic Calculator II
    // // Sames as infix evaluation
    // public int calculate(String s) {
        // https://leetcode.com/problems/basic-calculator-ii/submissions/
    // }

    // 849 · Basic Calculator III
    // // Sames as infix evaluation
    // public int calculate3(String s) {
    //     // https://www.lintcode.com/problem/849/
    //     // Write your code here
    // }


    // Number Of Valid Subarrays /(using next smaller to the right)
    public static int[] nsr(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(0);
        for(int i=1;i<arr.length;i++){
            while(st.size()>0 && arr[st.peek()] > arr[i]){
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
    public static int validSubarrays(int[] nums) {
        int[] nsr = nsr(nums);
        int res = 0;
        for(int i=0;i<nums.length;i++){
            res += nsr[i]-i;
        }
        return res;
    }

    // Lexicographically Smallest Subsequence
    public static int[] smallest(int[] nums, int k) {
        Stack<Integer> st = new Stack<>();
        int removals = nums.length - k;
        st.push(0);
        for(int i=1;i<nums.length;i++){
            while(st.size() > 0 && nums[st.peek()] > nums[i] && removals>0){
                removals-=1;
                st.pop();
            }
            st.push(i);
        }
        while(removals > 0 && st.size() > 0){
            removals-=1;
            st.pop();
        }
        int f = st.size()-1;
        int[] res = new int[st.size()];
        while(st.size() > 0){
            res[f] = nums[st.pop()];
            f-=1;
        }
        return res;
    }

    // https://leetcode.com/problems/design-a-stack-with-increment-operation
    // 1381. Design a Stack With Increment Operation
    class CustomStack {
        int[] val;
        int[] inc;
        int top = -1;

        public CustomStack(int maxSize) {
            val = new int[maxSize];
            inc = new int[maxSize];
            top = -1;
        }
        
        public void push(int x) {
            if(top+1 == val.length) return;
            val[top+1] = x;
            top++;
        }
        
        public int pop() {
            if(top == -1) return -1;
            int value = val[top]+inc[top];
            if(top != 0){
                inc[top-1] += inc[top];
            }
            inc[top] = 0;
            top--;
            return value;
        }
        
        public void increment(int k, int val) {
            if(top == -1) return;
            if(k > top+1){
                inc[top] += val;
            }else{
                inc[k-1] += val;
            }
        }
    }

    // https://leetcode.com/problems/design-circular-deque/
    // 641. Design Circular Deque
    // using linkedlist (making it time and space complexity good)
    class MyCircularDeque {
        private class Node {
            int data;
            Node next;

            public Node(int data){
                this.data = data;
                this.next = null;
            }
        }
        private Node head = null;
        private Node tail = null;
        private int size = 0;
        private int limit = 0;

        public MyCircularDeque(int k) {
            this.limit = k;
        }
        
        private void handleAddWhenSize0(int value){
            Node nn = new Node(value);
            this.head = this.tail = nn;
            this.size = 1;
        }


        public boolean insertFront(int value) {
            if(this.size == this.limit) return false;
            if(this.size == 0){
                handleAddWhenSize0(value);
            }else{
                Node nn = new Node(value);
                nn.next = this.head;
                this.head = nn;
                this.size++;
            }
            return true;
        }
        
        public boolean insertLast(int value) {
            if(this.size == this.limit) return false;
            if(this.size == 0){
                handleAddWhenSize0(value);
            }else{
                Node nn = new Node(value);
                tail.next = nn;
                this.tail = nn;
                this.size++;
            }
            return true;
        }
        
        private void handleDeleteWhenSize1(){
            this.head = this.tail = null;
            this.size = 0;
        }

        public boolean deleteFront() {
            if(this.size == 0) return false;
            if(this.size == 1){
                handleDeleteWhenSize1();
            }else{
                this.head = this.head.next;
                this.size--;
            }
            return true;
        }

        public boolean deleteLast() {
            if(this.size == 0) return false;
            if(this.size == 1){
                handleDeleteWhenSize1();
            }else{
                Node temp = this.head;
                while(temp.next != this.tail){
                    temp = temp.next;
                }
                temp.next = null;
                this.tail = temp;
                this.size--;
            }
            return true;
        }
        
        public int getFront() {
            if(this.size == 0){
                return -1;
            }
            return this.head.data;
        }
        
        public int getRear() {
            if(this.size == 0){
                return -1;
            }
            return this.tail.data;
        }
        
        public boolean isEmpty() {
            return this.size == 0;
        }
        
        public boolean isFull() {
            return this.size == this.limit;
        }
    }

    // https://www.lintcode.com/problem/859/
    // 859 · Max Stack
    class MaxStack {
        Stack<Integer> vStack;
        Stack<Integer> mStack;
        public MaxStack() {
            vStack = new Stack<>();
            mStack = new Stack<>();
        }
        public void push(int x) {
            vStack.push(x);
            if(mStack.size() == 0){
                mStack.push(x);
            }else{
                mStack.push(Math.max(x,mStack.peek()));
            }
        }
        public int pop() {
            mStack.pop();
            return vStack.pop();
        }  
        public int top() {
            if(vStack.size() == 0){
                return -1;
            }
            return vStack.peek();
        }
        public int peekMax() {
            if(mStack.size() == 0){
                return -1;
            }
            return mStack.peek();
        }
        public int popMax() {
            int max = mStack.peek();
            Stack<Integer> helper = new Stack<>();
            while(vStack.peek() != max){
                mStack.pop();
                helper.push(vStack.pop());
            }
            vStack.pop();
            mStack.pop();

            while(helper.size() > 0){
                push(helper.pop());
            }
            return max;
        }
    }

    // Check If Word Is Valid After Insertion
    public static boolean isValid(String S) {
        // write your code here
    
        return false;
    }

    // https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
    // 1209. Remove All Adjacent Duplicates in String II
    public String removeDuplicates(String s, int k) {
        return s;        
    }

    // Design Hit Counter
    static class HitCounter {
        Queue<Integer> qu;
        /** Initialize your data structure here. */
        public HitCounter() {
            qu = new ArrayDeque<>();
        }
    
        /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
        public void hit(int timestamp) {
            qu.add(timestamp);
            int start = timestamp - 300 + 1;

            while( qu.size() > 0 &&  qu.peek() < start){
                qu.remove();
            }
        }
    
        /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
        public int getHits(int timestamp) {
            int start = timestamp - 300 + 1;
            while(qu.size() > 0 &&  qu.peek() < start){
                qu.remove();
            }
            return qu.size();
        }
    }

    // Number Of Recent Calls
    // https://leetcode.com/problems/number-of-recent-calls/
    class RecentCounter {
        Queue<Integer> qu;
        public RecentCounter() {
            qu = new ArrayDeque<>();
        }
        
        public int ping(int t) {
            qu.add(t);
            int start = t - 3000;
            while(qu.size() > 0 && qu.peek() < start){
                qu.remove();
            } 
            return qu.size();
        }
    }

    // Moving Average From Data Stream
    public static class MovingAverage {
        Queue<Integer> qu;
        double sum;
        int k;
        public MovingAverage(int size) {
            qu = new ArrayDeque<>();
            sum = 0;
            k = size;
        }
    
        public double next(int val) {
            if(qu.size() < k){
                sum += val;
                qu.add(val);
                return sum/qu.size();
            }else{
                sum -= qu.remove();
                sum += val;
                qu.add(val);
                return sum/k;
            }
        }
    }


    public static void main(String[] args) {
        
    }
}
