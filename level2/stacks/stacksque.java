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


    public static void main(String[] args) {
        
    }
}
