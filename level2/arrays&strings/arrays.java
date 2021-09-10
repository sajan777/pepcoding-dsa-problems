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
            int h = Math.max(height[i], height[j]);
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
            if(i >0 && nums[i] == nums[i-1]) continue;
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
    // Segmented Sieve (will do later!!!!!!!!!!!)
    public static void segmentedSieveAlgo(int a, int b) {

    }

    // Find Pair Given Difference  https://practice.geeksforgeeks.org/problems/find-pair-given-difference1559/1
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

    public static void main(String[] args) {

    }       
}
