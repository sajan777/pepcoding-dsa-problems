import java.util.*;

public class arrays {
    // 925. Long Pressed Name (faulty keyboard)  O(N)
    public boolean isLongPressedName(String name, String typed) {
        if(name.length() > typed.length()) return false;
        int i = 0; //name string
        int j = 0; //typed string
        while(i<name.length() && j<typed.length()) {
            if(name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            }else if(0 <= i-1  && name.charAt(i-1) == typed.charAt(j)){
                j++;
            }else{
                return false;
            }
        }
        while(j<typed.length()){
            if(typed.charAt(j) != name.charAt(i-1)) return false;
            j++;
        }
        return i < name.length() ? false : true;
    }
    
    // 11. Container With Most Water  O(N)
    public int maxArea(int[] height) {
        int maxWater = 0;
        int i = 0;
        int j = height.length-1;
        while(i<j){
            int l = j-i;
            int h = Math.min(height[i], height[j]);
            int water = l*h;
            maxWater = Math.max(maxWater, water);
            if(height[i] < height[j]){
                i++;
            }else{
                j--;
            }
        }
        return maxWater;
    }

    // 977. Squares of a Sorted Array  O(N)
    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        int i = 0;
        int j = nums.length-1;
        for(int k=nums.length-1;k>=0;k--){
            int val1 = nums[i]*nums[i];
            int val2 = nums[j]*nums[j];
            if(val1 > val2){
                res[k] = val1;
                i++;
            }else{
                res[k] = val2;
                j--;
            }
        }
        return res;
    }

    // 169. Majority Element (Moore's Voting Algorithm)  O(N)
    public int majorityElement(int[] nums) {
        int val = nums[0];
        int count = 1;
        for(int i=1;i<nums.length;i++){
            if(nums[i] == val){
                count++;
            }else{
                if(count > 0){
                    // Pair distinct elements
                    count--;
                }else{
                    val = nums[i];
                    count = 1;
                }
            }
        }
        return val; // for leetcode as there it says there will always be a majorityElement 

        // Otherwise just check by again traversing
        // count = 0;
        // for(int i:nums){
        //     if(i == val) count++;
        // }
        // if(count > nums.length/2){
        //     System.out.println(val);
        // }else{
        //     System.out.println("No Majority Element exist");
        // }
    }

    // 229. Majority Element II  O(N)
    private boolean checkFreqn3(int[] nums,int val){
        int count = 0;
        for(int e:nums){
            if(e == val) count++;
        }
        return count > nums.length/3;
    }
    
    public List<Integer> majorityElement2(int[] nums) { 
        int val1 = nums[0];
        int count1 = 1;//count of val1 in current window to make triplets
        
        int val2 = nums[0];
        int count2 = 0;
        for(int i=1;i<nums.length;i++){
            if(val1 == nums[i]){
                count1++;
            }else if(val2 == nums[i]){
                count2++;
            }else{
                if(count1 ==  0){
                    val1 = nums[i];
                    count1 = 1;
                }else if(count2 == 0){
                    val2 = nums[i];
                    count2 = 1;
                }else{
                    count1--;
                    count2--;
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        boolean check1 = checkFreqn3(nums,val1);
        if(check1 == true){
            res.add(val1);
        }
        if(val1 == val2) return res;

        boolean check2 = checkFreqn3(nums,val2);
        if(check2 == true){
            res.add(val2);
        }
        return res;
    }
    // Majority Element General
    public static ArrayList<Integer> majorityElement(int[] arr, int k) {
        HashMap<Integer,Integer> hmap = new HashMap<>();
        for(int i:arr){
            if(hmap.containsKey(i)){
                hmap.put(i,hmap.get(i)+1);
            }else{
                hmap.put(i,1);
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        for(int i:hmap.keySet()){
            int val = hmap.get(i);
            if(val > arr.length/k){
                res.add(i);
            }
        }
        return res;
    }

    // 556. Next Greater Element III  O(N)
    private static int dipIndex(char[] arr){
        int indx = arr.length-2;
        while(indx >= 0 && (arr[indx] >= arr[indx+1])){
            indx--;
        }
        return indx;
    }
    
    private static int ceilIndex(char[] arr,int dipIndex){
        for(int i=arr.length-1;i>=0;i--){
            if(arr[i] > arr[dipIndex]) return i;
        }
        return -1;
    }
    
    private static void reverse(char[] arr,int left,int right){
        while(left < right){
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
    
    private static void swap(char[] arr,int i,int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static String nextGreaterElement_(String str) {
        if(str.length() == 1) return "-1";
        char[] arr = str.toCharArray();
        int dipIndx = dipIndex(arr);
        if(dipIndx == -1){
            return "-1";
        }
        int ceilIndx = ceilIndex(arr,dipIndx);
        swap(arr,dipIndx,ceilIndx);
        reverse(arr,dipIndx+1,arr.length-1);
        return String.valueOf(arr);
    }
    
    public int nextGreaterElement(int n) {
        String str = n+"";
        String res_ = nextGreaterElement_(str);
        long res = Long.parseLong(res_);
        if(res <= Integer.MAX_VALUE){
            return (int)res;
        }else{
            return -1;
        }
    }
    
    // 628. Maximum Product of Three Numbers O(N)
    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for(int i:nums){

            if(i < min1){
                min2 = min1;
                min1 = i;
            }else if(i < min2){
                min2 = i;
            }

            if(i > max1){
                max3 = max2;
                max2 = max1;
                max1 = i;
            }else if(i > max2){
                max3 = max2;
                max2 = i;
            }else if(i > max3){
                max3 = i;
            }
        }

        return Math.max(max1*max2*max3,min1*min2*max1);
    }

    // 747. Largest Number At Least Twice of Others O(n)
    public int dominantIndex(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        int maxIndx = -1;

        for(int i=0;i<nums.length;i++){
            int val = nums[i];
            if(val > max1){
                max2 = max1;
                max1 = val;
                maxIndx = i;
            }else if(val > max2){
                max2 = val;
            }
        }
        if(max1 >= max2+max2){
            return maxIndx;
        }
        return -1;
    }

    // 769. Max Chunks To Make Sorted o(n)
    public int maxChunksToSorted(int[] arr) {
        int chunks = 0;
        int max = 0;
        for(int i=0;i<arr.length;i++){
            max = Math.max(max,arr[i]);
            if(max == i) chunks++;
        }

        return chunks;
    }

    // 768. Max Chunks To Make Sorted II o(n)+o(n)
    public int maxChunksToSorted2(int[] arr) { 
        int n  = arr.length;
        int[] rightMin = new int[n+1];
        rightMin[n] = Integer.MAX_VALUE;
        int chunks = 0;
        // 1. prepare right min
        for(int i=n-1;i>=0;i--){
            rightMin[i]  = Math.min(rightMin[i+1],arr[i]);
        }
        // 2. solve chunks using left Max Var and right min
        int leftMax = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            leftMax = Math.max(leftMax,arr[i]);
            if(leftMax <= rightMin[i+1]) chunks++;
        }
        return chunks;
    }

    //345. Reverse Vowels of a String o(n)+o(n)
    private boolean isVowel(char c){
        if(c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i' || c == 'I' || c == 'o' || c =='O'  || c == 'u' || c == 'U'){
            return true;
        }
        return false;
    }
    
    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int left = 0;
        int right = arr.length-1;
        while(left < right){
            char leftChar = arr[left];
            char rightChar = arr[right];
            if(isVowel(leftChar) && isVowel(rightChar)){
                arr[left] = rightChar;
                arr[right] = leftChar;
                left++;
                right--;
            }else if(isVowel(leftChar)){
                right--;
            }else if(isVowel(rightChar)){
                left++;
            }else{
                left++;
                right--;
            }
        }
        return String.valueOf(arr);
    }

    // 238. Product of Array Except Self o(n)+o(n)
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] leftProd =  new int[n];
        int[] rightProd =  new int[n];
        int[] ans = new int[n];

        // left 
        leftProd[0] = nums[0];
        for(int i=1;i<n;i++){
            leftProd[i] = leftProd[i-1]*nums[i];
        }
        // right
        rightProd[n-1] = nums[n-1];
        for(int i=n-2;i>=0;i--){
            rightProd[i] = rightProd[i+1]*nums[i];
        }
        // res
        ans[0] = rightProd[1];
        ans[n-1] = leftProd[n-2];
        for(int i=1;i<n-1;i++){
            ans[i] = leftProd[i-1]*rightProd[i+1];
        }
        return ans;
    }

    // (Wave Array ) https://practice.geeksforgeeks.org/problems/wave-array-1587115621/1# (GFG Different check but do it)
    // Wiggle Sort 1 0(n)
    public static void swapArr(int[] arr,int left,int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
    public static void wiggleSort(int[] arr) {
        for(int i=0;i<arr.length-1;i++){
            if(i%2 == 0){
                // even idx
                if(arr[i] > arr[i+1]) swapArr(arr,i,i+1);
            }else{
                // odd idx
                if(arr[i] < arr[i+1]) swapArr(arr,i,i+1);
            }
        }
    }    
    
    // 324. Wiggle Sort II 0(nlogn)+0(n)
    public void wiggleSort2(int[] nums) {
        int n = nums.length;
        // 1. create duplicate
        int[] dup = new int[n];
        for(int i=0;i<n;i++){
            dup[i] = nums[i];
        }
        // 2 sort duplicate
        Arrays.sort(dup);
        // 3 fill indexes
        int j = n-1;
        int i = 1;
        // fill odd indexes
        while(i < n){
            nums[i] = dup[j];
            i+=2;
            j--;
        }
        // fill even indexes
        i = 0;
        while(i<n){
            nums[i] = dup[j];
            i+=2;
            j--;
        }
    }

    // 795. Number of Subarrays with Bounded Maximum HARD Question 0(n)
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int prevCount = 0;
        int overallCount = 0;
        int i = 0;
        int j = 0;
        while(j < nums.length){
            if(left <= nums[j] && nums[j] <= right){
                prevCount = j-i+1;
                overallCount += prevCount;
            }else if(nums[j] < left){
                overallCount += prevCount;
            }else{
                prevCount = 0;
                i = j+1;
            }
            j++;
        }
        return overallCount;
    }

    // 849. Maximize Distance to Closest Person 0(n)
    public int maxDistToClosest(int[] seats) {
        int zeroes = 0;
        int distance = 0;
        // left sub part
        int indx = 0;
        while(indx<seats.length && seats[indx] != 1){
            zeroes++;
            indx++;
        }
        distance = zeroes;
        indx++;
        zeroes = 0;
        // segements
        while(indx < seats.length){
            zeroes = 0;
            while(indx<seats.length && seats[indx] != 1){
                zeroes++;
                indx++;
            }
            if(indx == seats.length) break;
            indx++;
            distance = Math.max(distance,(zeroes+1)/2);
            zeroes = 0;
        }
        // right sub part
        return Math.max(distance,zeroes);
    }


    // 41. First Missing Positive 0(n)
    public int firstMissingPositive(int[] nums) {
        // step1 Find occurence of 1 and mark out of range elements
        boolean one = false;
        int n = nums.length;
        for(int i=0;i<n;i++){
            if(nums[i] == 1){
                one = true;
            }
            if(nums[i] < 1 || nums[i] > n){
                nums[i] = 1;
            }
        }
        if(one == false) return 1;
        // step2 Mark elemtns in array
        for(int i=0;i<n;i++){
            int val = Math.abs(nums[i]);
            int idx = val-1;
            nums[idx] = -Math.abs(nums[idx]); // if already -ve still -ve if +ve then -ve
        }    
        // step3 find missing positive
        for(int i=0;i<n;i++){
            if(nums[i] > 0){
                return i+1;
            }
        }
        return n+1;        
    }
    // 0(nlogn) + 0(1)
    public int firstMissingPositive1(int[] nums) {
        int missingPositive = 1;
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++){
            if(nums[i] == missingPositive){
                missingPositive++;
            }
        }
        return missingPositive;
    }

    // 905. Sort Array By Parity
    public int[] sortArrayByParity(int[] nums) {
        int i = 0;
        int j = 0;
        int n = nums.length;
        while(i < n){
            if(nums[i]%2 == 0){
                swapArr(nums,i,j);
                j++;
                i++;
            }else{
                i++;       
            }
        }
        return nums;  
    }

    // https://www.lintcode.com/problem/903/
    // 903 · Range Addition 0(Q+N) (do range addition 2)
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];
        // Make impact of query on res;
        for(int i=0;i<updates.length;i++){
            int sI = updates[i][0]; //start indx
            int eI = updates[i][1]; // end idx
            int inc = updates[i][2]; //incr

            res[sI] += inc;
            if(eI != length-1){
                res[eI+1] -= inc;
            }
        }
        // aPPLY impace using prefix sum
        for(int i=1;i<length;i++){
            res[i] += res[i-1];
        }
        return res;
    }

    // https://www.lintcode.com/problem/912/
    // 912 · Best Meeting Point 0(n)
    public int minTotalDistance(int[][] grid) {
        ArrayList<Integer> xcord = new ArrayList<>();
        // fill xcord in sorted order row wise traverse
        for(int r=0;r<grid.length;r++){
            for(int c=0;c<grid[0].length;c++){
                if(grid[r][c] == 1){
                    xcord.add(r);
                }
            }
        }
        ArrayList<Integer> ycord = new ArrayList<>();
        // fill xcord in sorted order col wise
        for(int c=0;c<grid[0].length;c++){
            for(int r=0;r<grid.length;r++){
                if(grid[r][c] == 1){
                    ycord.add(c);
                }
            }
        }
        // find meeting point (using median for both xcord and ycord);
        int x = xcord.get(xcord.size()/2);
        int y = ycord.get(xcord.size()/2);

        int dist = 0;
        for(int r=0;r<grid.length;r++){
            for(int c=0;c<grid[0].length;c++){
                if(grid[r][c] == 1){
                   dist += Math.abs(x-r) + Math.abs(y-c);
                }
            }
        }
     
        return dist;
    }   

    // 670. Maximum Swap
    // leetcode    
    public int maximumSwap(int n) {
        String num  = n+"";
        //  convert to tstring 
        char[] arr = num.toCharArray();
        int[] lastIndxOfDigit = new int[10];

        // travel and fil llast indx
        for(int i=0;i<arr.length;i++){
            lastIndxOfDigit[arr[i]-'0'] = i;
        }

        // travel and find swapping point
        for(int i=0;i<arr.length;i++){
            int digit = arr[i] - '0';
            int indx = i;
            for(int j=9;j>digit;j--){
                if(lastIndxOfDigit[j] > i){
                    indx = lastIndxOfDigit[j];
                    break;
                }
            }
            if(indx != i){
                swap(arr,i,indx);
                break;
            }
        }
        String res_ = String.valueOf(arr);
        // convert string to num
        int res = Integer.parseInt(res_);
        return res;
    }

    // 119. Pascal's Triangle II
    public List<Integer> getRow(int n) {
        List<Integer> res = new ArrayList<>();
        long val = 1;
        for(int r = 0;r<=n;r++){
            res.add((int)val);
            val = val*(n-r)/(r+1);
        }
        return res;
    }

    //537. Complex Number Multiplication 0(n)
    public String complexNumberMultiply(String num1, String num2) {
        int a1 = Integer.parseInt(num1.substring(0,num1.indexOf("+")));
        int b1 = Integer.parseInt(num1.substring(num1.indexOf("+")+1,num1.length()-1));
        
        int a2 = Integer.parseInt(num2.substring(0,num2.indexOf("+")));
        int b2 = Integer.parseInt(num2.substring(num2.indexOf("+")+1,num2.length()-1));

        int a = a1*a2 - b1*b2;
        int b = a1*b2 + a2*b1;
        return a +"+"+b+"i";
    }

    // 2 Sum - Target Sum Unique Pairs 0(nlogn)
    public static List<List<Integer>> twoSum(int[] arr, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(arr);
        int left = 0;
        int right = arr.length-1;
        while(left < right){
            if((left > 0) && (arr[left] == arr[left-1])){
                left++;
                continue;
            } 
            int tsum = arr[left]+arr[right];
            if(tsum == target){
                List<Integer> curr = new ArrayList<>();
                curr.add(arr[left]);
                curr.add(arr[right]);
                res.add(curr);
                left++;
                right--;
            }else if(tsum < target){
                left++;
            }else if(tsum > target){
                right--;
            }
        }
        return res;
    }

    // 3 Sum - Target Sum Unique Triplet 0(n2)
    // two sum for 3 sum;
    private static List<List<Integer>> twoSum_(int[] arr,int st,int end,int target) {
        List<List<Integer>> res = new ArrayList<>();
        int left = st;
        int right = end;
        while(left < right){
            if((left > st) && (arr[left] == arr[left-1])){
                left++;
                continue;
            } 
            int tsum = arr[left]+arr[right];
            if(tsum == target){
                List<Integer> curr = new ArrayList<>();
                curr.add(arr[left]);
                curr.add(arr[right]);
                res.add(curr);
                left++;
                right--;
            }else if(tsum < target){
                left++;
            }else if(tsum > target){
                right--;
            }
        }
        return res;
    }
    public static List<List<Integer>> threeSum(int[] nums, int targ) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0;i<nums.length-2;i++){
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int val = nums[i];
            int smallTarget = targ - val;
            List<List<Integer>> subres = twoSum_(nums,i+1,nums.length-1,smallTarget);
            for(List<Integer> list:subres){
                list.add(val);
                res.add(list);
            }
        }
        return res;
    }

    // 4 Sum - Target Sum With Unique Quadruple
    // 3 sum for 4 sum 0(n3)
    private static List<List<Integer>> threeSum_(int[] nums,int st,int targ) {
        List<List<Integer>> res = new ArrayList<>();
        for(int i=st;i<nums.length-2;i++){
            if(i >st && nums[i] == nums[i-1]) continue;
            int val = nums[i];
            int smallTarget = targ - val;
            List<List<Integer>> subres = twoSum_(nums,i+1,nums.length-1,smallTarget);
            for(List<Integer> list:subres){
                list.add(val);
                res.add(list);
            }
        }
        return res;
    }
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-3;i++){
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int val = nums[i];
            int smallTarget = target-val;
            List<List<Integer>> subres = threeSum_(nums,i+1, smallTarget);
            for(List<Integer> list:subres){
                list.add(val);
                res.add(list);
            }
        }
        return res;
    }

    // K Sum - Target Sum Unique Set 0(n^k-1) == 0(n^k)
    public static List<List<Integer>> kSum_Util(int[] arr,int target,int si,int k){
        if(k == 2){
            // base
            return twoSum_(arr,si,arr.length-1,target);
        }
        
        List<List<Integer>> res = new ArrayList<>();
        for(int i=si;i<arr.length-(k-1);i++){
            if(i > si && arr[i] == arr[i-1]) continue;
           int val1 = arr[i];
           int smallTarget = target-val1;
           List<List<Integer>> subres = kSum_Util(arr,smallTarget,i+1,k-1);
           for(List<Integer> list:subres){
               list.add(val1);
               res.add(list);
           } 
        }
        return res;
    }

    
    public static List<List<Integer>> kSum(int[] arr, int target, int k) {
        Arrays.sort(arr);
        List<List<Integer>> res = kSum_Util(arr,target,0,k);
        return res;
    }
    // Sieve Of Eratosthenes
    public static void printPrimeUsingSieve(int n) {
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime,true);
        for(int i=2;i*i<=n;i++){
            if(isPrime[i] == true){
                int count = 2;
                int factor = i*count;
                while(factor<=n){
                    isPrime[factor] = false;
                    factor=i*count;
                    count++;
                }
            }
        }
        for(int i=2;i<isPrime.length;i++){
            if(isPrime[i] == true) System.out.print(i+" ");
        }
    }

    // // Segmented Sieve (will do later!!!!!!!!!!!)
    // public static void segmentedSieveAlgo(int a, int b) {

    // }

    // Find Pair Given Difference  https://practice.geeksforgeeks.org/problems/find-pair-given-difference1559/1 0(n)
    public boolean findPair(int arr[], int size, int n){
        if(size == 0) return false;
        Arrays.sort(arr);
        int i = 0;
        int j = 1;
        while(j < size){
            int diff = arr[j]-arr[i];
            if(diff == n){
                return true;
            }else if(diff > n){
                i++;
            }else{
                j++;
            }
        }
        return false;
    }
    
    // 838. Push Dominoes 0(n)
    private void solveConfig(char[] arr,int i,int j){
        if(arr[i] == 'L' && arr[j] == 'L'){
            // case 1
            for(int k=i+1;k<j;k++) arr[k] = 'L';
        }else if(arr[i] == 'R' && arr[j] == 'R'){
            // case 2
            for(int k=i+1;k<j;k++) arr[k] = 'R';
        }else if(arr[i] == 'L' && arr[j] == 'R'){
            // case 3 (nothing to do)
        }else{
            // case 4
            i++; 
            j--;
            while(i < j){
                arr[i] = 'R';
                arr[j] = 'L';
                i++;
                j--;
            }
        }
    }
    public String pushDominoes(String dominoes) {
        int l = dominoes.length();
        char[] arr = new char[l+2];
        arr[0] = 'L';
        arr[l+1] = 'R';
        for(int i=1;i<=l;i++){
            arr[i] = dominoes.charAt(i-1);
        }
        int i = 0;
        int j = 0;
        while(j < arr.length){
            while(arr[j] == '.') j++;
            if(j - i > 1){
                solveConfig(arr,i,j);
            }
            i = j;
            j++;
        }                                                
        StringBuilder res = new StringBuilder();
        for(int k=1;k<=l;k++) res.append(arr[k]);
        return res.toString();
    }

    // 829. Consecutive Numbers Sum
    // public int consecutiveNumbersSum(int n) {
        
    // }

    // 680. Valid Palindrome II 0(n)
    private boolean isPallindrome(String s,int st,int end){
        while(st<end){
            if(s.charAt(st) != s.charAt(end)) return false;
            st++;
            end--;
        }
        return true;
    }
    public boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length()-1;
        while(i < j){
            if(s.charAt(i) == s.charAt(j)){
                i++;
                j--;
            }else{
                return isPallindrome(s,i+1,j) || isPallindrome(s,i,j-1);
            }
        }
        return true;
    }
    
    // 754. Reach a Number 0(n)
    public int reachNumber(int target) {
        target = Math.abs(target);
        int jump = 0;
        int s = 0;
        while(s<target){
            jump++;
            s+=jump;
        }
        if(s == target){
            return jump;
        }else if((s-target)%2 == 0){
            // even diff
            return jump;
        }else if((s+jump+1-target)%2 == 0){
            // odd diff
            return jump+1;
        }else{
            // odd again
            return jump+2;
        }
    }
    // Transponse matrix (in place) N*N
    public int[][] transpose1(int[][] matrix) {
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<i;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        return matrix;
    }
    // /867. Transpose Matrix (leetcode) M*N
    public int[][] transpose(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] res = new int[col][row];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                res[j][i] = matrix[i][j];
            }
        }
        return res;
    }

    // 48. Rotate Image 0(n2)
    private void reverseRow(int[][] arr){
        for(int r=0;r<arr.length;r++){
            int left = 0;
            int right = arr[r].length-1;
            while(left < right){
                int temp = arr[r][left];
                arr[r][left] = arr[r][right];
                arr[r][right] = temp;
                left++;
                right--;
            }
        }
    }
    public void rotate(int[][] matrix) {
        transpose1(matrix);
        reverseRow(matrix);
    }

    // 763. Partition Labels 0(n)+)(n)
    public List<Integer> partitionLabels(String s) {
        // 1. Make hashmap of last Occurrnence
        HashMap<Character,Integer> hmap = new HashMap<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            hmap.put(ch,i);
        }
        List<Integer> res = new ArrayList<>();
        // 2. solve using chaining technique
        int max = 0;
        int prev = 0;
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            int lastOccurence = hmap.get(ch);
            max = Math.max(max,lastOccurence);
            if(max == i){
                res.add(max-prev+1);
                prev = i+1;
            }
        }
        return res;
    }

    // 881. Boats to Save People  0(nlogn)
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0;
        int right = people.length-1;
        int boats = 0;
        while(left <= right){
            int sum = people[left] + people[right];
            if(sum <= limit){
                // both people can go;
                left++; 
            }else{
                // only heavy can go;
                right--;
            }
            boats++;
        }
        return boats;
    }
    // Minimum Platforms  https://practice.geeksforgeeks.org/problems/minimum-platforms-1587115620/1 0(nlogn)
    static int findPlatform(int arr[], int dep[], int n){
        Arrays.sort(arr);
        Arrays.sort(dep);
        int omax = 0;
        int cmax = 0;
        int i = 0;
        int j = 0;
        while(i < n){
            if(arr[i] <= dep[j]){
                cmax++;
                i++;
            }else{
                cmax--;
                j++;
            }
            omax = Math.max(cmax,omax);
        }
        return omax;
    }                   
    
    // 53. Maximum Subarray 0(n) kadane's algo most important for the world to solve covid problems.....
    public int maxSubArray(int[] nums) {
        int omax = Integer.MIN_VALUE;
        int csum = 0;
        for(int i=0;i<nums.length;i++){
            if(csum < 0){
                csum = nums[i];
            }else{
                csum += nums[i];
            }
            omax = Math.max(omax,csum);
        }
        return omax;
    }
    public int maxSubArray_print(int[] nums) {
        int omax = Integer.MIN_VALUE;
        int csum = 0;
        int cstart = 0;
        int cend = 0;
        int ostart = 0;
        int oend = 0;

        for(int i=0;i<nums.length;i++){
            if(csum < 0){
                csum = nums[i];
                cstart = i;
            }else{
                csum += nums[i];
                cend = i;
            }
            if(csum > omax){
                csum = omax;
                ostart = cstart;
                oend = cend;
            }
        }
        while(ostart < oend){
            System.out.println(nums[ostart]+" ");
            ostart++;
        }
        return omax;
    }    

    // 1191. K-Concatenation Maximum Sum
    // public int kConcatenationMaxSum(int[] arr, int k) {

    // }
    //    915. Partition Array into Disjoint Intervals
    // method 1 0(n)+0(n)
    public int partitionDisjoint1(int[] nums) {
        int n = nums.length;
        int[] rightmin = new int[n];
        rightmin[n-1] = nums[n-1];
        for(int i=n-2;i>=0;i--){
            rightmin[i] = Math.min(nums[i],rightmin[i+1]);
        }
        // travel and solve also mantain leftmax simultaneously
        int leftmax = Integer.MIN_VALUE;
        for(int i=0;i<n-1;i++){
            leftmax = Math.max(leftmax,nums[i]);
            if(leftmax <= rightmin[i+1]) return i+1;
        }
        return -1;
    }
    // 0(n)+0(1)
    public int partitionDisjoint(int[] nums) {
        int leftmax = nums[0];
        int maxInRun = nums[0];
        int ans = 0;
        for(int i=1;i<nums.length;i++){
            if(nums[i] > maxInRun){
                maxInRun = nums[i];
            }else if(nums[i] < leftmax){
                ans = i;
                leftmax = maxInRun;
            }
        }
        return ans+1;
    }

    // 56. Merge Intervals0(nlogn)
    public int[][] merge(int[][] intervals) {
        if(intervals.length == 0) return intervals;
        Arrays.sort(intervals,(val,val2)->Integer.compare(val[0],val2[0]));
        ArrayList<int[]> list = new ArrayList<>(); 

        int lsp = intervals[0][0]; //last interval starting point
        int lep = intervals[0][1]; //last interval ending point

        for(int i=1;i<intervals.length;i++){
            int sp = intervals[i][0];
            int ep = intervals[i][1];

            if(lep < sp){
                // new interval is found (add the prev one as its not overlapping)
                int[] sublist = {lsp,lep};
                list.add(sublist);
                lsp = sp;
                lep = ep;
            }else if(lep < ep){
                // overlapping then only update the lep of prev one with new ep
                lep = ep;
            }else{
                // nothing to do
                // fully overlapped
            }
        }
        int[] sublist = {lsp,lep};
        list.add(sublist);
        return list.toArray(new int[list.size()][]);
    }
    
    // 920 · Meeting Rooms https://www.lintcode.com/problem/920/
    public class Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public boolean canAttendMeetings(List<Interval> intervals) {
        if(intervals.size() == 0) return true;
        Collections.sort(intervals,(a,b)->Integer.compare(a.start,b.start));
        Interval interval = intervals.get(0);

        int lsp = interval.start; //last interval starting point
        int lep = interval.end; //last interval ending point

        for(int i=1;i<intervals.size();i++){
            int sp = intervals.get(i).start;
            int ep = intervals.get(i).end;
            
            if(lep <= sp){
                lsp = sp;
                lep = ep;
            }else{
                return false;
            }

        }
        return true;
    }

    // 919 · Meeting Rooms II (portal) 0(nlogn)
    public int minMeetingRooms(List<Interval> intervals) {
        int n = intervals.size();
        int[] start = new int[n];
        int[] end = new int[n];
        for(int i=0;i<n;i++){
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }
        Arrays.sort(start);
        Arrays.sort(end);

        int omax = 0;
        int cmax = 0;
        int i = 0;
        int j = 0;
        while(i < n){
            if(start[i] < end[j]){
                cmax++;
                i++;
            }else{
                cmax--;
                j++;
            }
            omax = Math.max(cmax,omax);
        }
        return omax;
    }

    // leetcode 239. https://leetcode.com/problems/sliding-window-maximum/
    public int[] nextGreaterRightIdx(int[] nums){
        int[] res = new int[nums.length];
        Stack<Integer> st = new Stack<>();
        st.push(0);
        for(int i=1;i<nums.length;i++){
            while(st.size() > 0 && nums[st.peek()] < nums[i]) res[st.pop()] = i;
            st.push(i);
        }
        while(st.size() > 0){
            res[st.pop()] = nums.length;
        }
        return res;
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ngr = nextGreaterRightIdx(nums);
        int n = nums.length;
        int j = 0;
        int[] res = new int[n-(k-1)];
        for(int i=0;i<res.length;i++){
            if(j < i) j = i;
            while(ngr[j] < i+k) {
                j = ngr[j];
            }
            res[i] = nums[j];
        }
        return res;
    }

    // digit multiplier, https://practice.geeksforgeeks.org/problems/digit-multiplier3000/1
    static String getSmallest(Long num) {
        if(num == 1) return "1";
        String res = "";
        for(int i=9;i>=2;i--){
            while(num%i==0){
                res = i+res;
                num = num/i;
            }
        }
        return res.length() == 0 || num != 1 ? "-1" : res;
    }

    // first -ve in k size window, https://practice.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1
    public long[] printFirstNegativeInteger(long A[], int N, int K) {
        long[] res = new long[N-(K-1)];
        int idx = N;
        // mark -ve in last k size window
        for(int i=N-1;i>=N-K;i--){
            if(A[i] < 0) idx = i;
        }
        // travel from n-k to 0 and set in res;
        for(int i=N-K;i>=0;i--){
            if(A[i] < 0){
                idx = i;
            }
            if(idx < i+K){
                res[i] = A[idx];
            }
        }
        return res;
    }

    // leetcode 853. https://leetcode.com/problems/car-fleet/
    // public int carFleet(int target, int[] position, int[] speed) {
        
    // }

    // leetcode 1191. https://leetcode.com/problems/k-concatenation-maximum-sum/
    // private int kadanes12(int[] arr){
    //     int n = arr.length;
    //     int[] narr = new int[2*n];
    //     for(int i=0;i<n;i++){
    //         narr[i] = arr[i];
    //         narr[n+i] = arr[i];
    //     }
    //     return maxSubArray(arr);
    // }
    // public int kConcatenationMaxSum(int[] arr, int k) {
    //     int sum = 0;
    //     for(int e:arr) sum+=e;
    //     int kadane12 = kadanes12(arr);
    //     if(sum < 0){
    //         return kadane12;
    //     }else{
    //         return kadane12+(sum*(k-2));
    //     }
    // }

    // https://practice.geeksforgeeks.org/problems/max-sum-in-sub-arrays0824/1
    // Max sum in sub-arrays 
    // public static long pairWithMaxSum(long arr[], long n){
            
    // }

    // leetcode 57. https://leetcode.com/problems/insert-interval/
    // public int[][] insert(int[][] intervals, int[] newInterval) {
    //     boolean isUsed = false;
    //     int lsp = 0;
    //     int lep = 0;

    //     if(intervals[0][0] < newInterval[0]){
    //         lsp = intervals[0][0];
    //         lep = intervals[0][1];
    //     }else{
    //         isUsed = true;
    //         lsp = newInterval[0];
    //         lep = newInterval[0];
    //     }
    //     int i = 1;
    //     while(i < intervals.length){
    //         int sp = intervals[i][0];
    //         int ep = intervals[i][1];
    //         if(isUsed == false &&  sp > newInterval[0]){
    //             sp = newInterval[0];
    //             ep = newInterval[1];
    //         }else{
    //             i++;
    //         }
    //         if(lep < sp){
                
    //         }
    //     }
    //     return new int[0][0];
    // }

    // leetcode 1007. https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/ 0(N)
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int val1 = tops[0];
        int val2 = bottoms[0];

        int count1 = 0; //Rotations for top as val1
        int count2 = 0; //Rotations for top as val2
        int count3 = 0; //Rotations for Bottom as val1
        int count4 = 0; //Rotations for Bottom as val2

        for(int i=0;i<tops.length;i++){
            if(count1 != Integer.MAX_VALUE){
                if(tops[i] == val1){
                    // nothing to do
                }else if(bottoms[i] == val1){
                    // rotation req
                    count1++;
                }else{
                    count1 = Integer.MAX_VALUE;
                }
            }

            if(count2 != Integer.MAX_VALUE){
                if(tops[i] == val2){
                    // nothing to do
                }else if(bottoms[i] == val2){
                    // rotation req
                    count2++;
                }else{
                    count2 = Integer.MAX_VALUE;
                }
            }

            if(count3 != Integer.MAX_VALUE){
                if(bottoms[i] == val1){
                    // nothing to do
                }else if(tops[i] == val1){
                    // rotation req
                    count3++;
                }else{
                    count3 = Integer.MAX_VALUE;
                }
            }

            if(count4 != Integer.MAX_VALUE){
                if(bottoms[i] == val2){
                    // nothing to do
                }else if(tops[i] == val2){
                    // rotation req
                    count4++;
                }else{
                    count4 = Integer.MAX_VALUE;
                }
            }
        }
        int res = Math.min(Math.min(count1,count2),Math.min(count3,count4));
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    
    // Concise App
    private int solveRotations(int[] arr1,int[] arr2,int val,int count,int index){
        if(count != Integer.MAX_VALUE){
            if(arr1[index] == val){
                //nothing to do
            }else if(arr2[index] == val){
                count++;
            }else{
                count = Integer.MAX_VALUE;
            }
        }
        return count;
    }
    public int minDominoRotations2(int[] tops, int[] bottoms) {
        int val1 = tops[0];
        int val2 = bottoms[0];
        int c1 = 0; //top as val1
        int c2 = 0; //top as val2
        int c3 = 0; //bottom as val1
        int c4 = 0; //bottom as val2
        for(int i=0;i<tops.length;i++){
            c1 = solveRotations(tops,bottoms,val1,c1,i);
            c2 = solveRotations(tops,bottoms,val2,c2,i);
            c3 = solveRotations(bottoms,tops,val1,c3,i);
            c4 = solveRotations(bottoms,tops,val2,c4,i);
        }
        int res = Math.min(Math.min(c1,c2),Math.min(c3,c4));
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    
    
    // 134. Gas Station
    // public int canCompleteCircuit(int[] gas, int[] cost) {
        
    // }

    // 891. Sum of Subsequence Widths
    // public int sumSubseqWidths(int[] nums) {
    //     Arrays.sort(nums);
    //     int mod = (int)1e9+7;
    //     long sum = 0;
    //     // make power array
    //     int n = nums.length;
    //     int[] power = new int[n];
    //     power[0] = 1;
    //     for(int i=1;i<n;i++){
    //         power[i] = (power[i-1]*2)%mod;
    //     }
    //     // cal sum
    //     for(int i=0;i<n;i++){
    //         // sum += 
    //     }
    //     return sum%mod;
    // }

    // leetcode 632. https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
    // private class Pair{
    //     int val; //value in list
    //     int i; //row number
    //     int j; //col number

    //     public Pair(int val,int i,int j){
    //         this.val = val;
    //         this.i = i;
    //         this.j = j;
    //     }
    // }
    // public int[] smallestRange(List<List<Integer>> nums) {
        
    // }

    // leetcode 1094. https://leetcode.com/problems/car-pooling/
    // rows = no of trips
    // cols[0] = no of passengers
    // cols[1] -> cols[2]  (starting of trip -> ending of trip)
    public boolean carPooling(int[][] trips, int capacity) {
        int len = 0; //as constraint is 1000
        int[] arr = new int[1001];
        // travel on query and mark impact on arr
        for(int i=0;i<trips.length;i++){
            int start = trips[i][1];
            int end = trips[i][2];
            int passengers = trips[i][0];

            arr[start] += passengers;
            arr[end] -= passengers;
            len = Math.max(len,end);
        }
        // make prefix sum to create impact of passenger's count;
        int prefix = 0;
        for(int i=0;i<len;i++){
            prefix += arr[i];
            arr[i] = prefix;
            if(arr[i] > capacity) return false;
        }
        return true;
    }

    // leetcode 152. https://leetcode.com/problems/maximum-product-subarray/
    public int maxProduct(int[] nums) {
        int prod = 1;
        int res = Integer.MIN_VALUE;

        // left product
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                res = Math.max(res, nums[i]);
                prod = 1; // for identity of product
            } else {
                prod *= nums[i];
                res = Math.max(res, prod);
            }
        }

        // right product
        prod = 1;
        for(int i = nums.length - 1; i >= 0; i--) {
            if(nums[i] == 0) {
                res = Math.max(res, nums[i]);
                prod = 1; // for identity of product
            } else {
                prod *= nums[i];
                res = Math.max(res, prod);
            }
        }
        return res;
    }

    // leetcode 209. https://leetcode.com/problems/minimum-size-subarray-sum/ 0(N)
    public int minSubArrayLen(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        int left = 0;
        int csum = 0;
        for(int right=0;right<nums.length;right++){
            if(nums[right] >= target){
                return 1;
            }
            csum += nums[right];
            while(csum >= target){
                res = Math.min(res,right-left+1);
                csum -= nums[left];
                left++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    // leetcode 643. https://leetcode.com/problems/maximum-average-subarray-i/
    public double findMaxAverage(int[] nums, int k) {
        // 1. find sum of first k-1 ele
        int sum = 0;
        for(int i=0;i<k-1;i++){
            sum += nums[i];
        }
        // 2. Calculate average in k size window
        double res = Integer.MIN_VALUE * 1.0;

        for(int i=k-1;i<nums.length;i++){
            sum += nums[i];
            double avg = sum * 1.0 / k;
            res = Math.max(res,avg);
            sum -= nums[i-k+1];
        }
        return res;
    }

    // Do other parts (DIY) 2,3,4,5
    // https://www.lintcode.com/problem/617/ (max Average 2)

    // leetcode 1750. https://leetcode.com/problems/minimum-length-of-string-after-deleting-similar-ends/
    public int minimumLength(String s) {
        int left = 0;
        int right = s.length()-1;
        while(left < right && s.charAt(left) == s.charAt(right)){
            char ch = s.charAt(left);
            // move left ahead for same char
            while(left <= right && s.charAt(left) == ch) left++;
            // move right backward for same char
            while(right > left && s.charAt(right) == ch) right--;
        }
        return right-left+1;
    }

    // leetcode 442. https://leetcode.com/problems/find-all-duplicates-in-an-array/
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            int idx = Math.abs(nums[i])-1;
            if(nums[idx] < 0){
                res.add(idx+1); //idx+1 is repeated
            }else{
                nums[idx] *= -1; // mark -ve
            }
        }
        return res;
    }

    // your work
    /* 
        1. https://leetcode.com/problems/fruit-into-baskets/
        2. https://practice.geeksforgeeks.org/problems/minimize-the-heights3351/1
        3. https://practice.geeksforgeeks.org/problems/minimize-the-heights-i/0/
    */

    public static void main(String[] args) {

    }       
}
