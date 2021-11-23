import java.util.*;

public class code {

    public static class Students implements Comparable<Students>{
        int phy;
        int chem;
        int maths;
        public Students(int phy,int chem,int maths){
            this.chem = chem;
            this.maths = maths;
            this.phy = phy;
        }
        public int compareTo(Students o){
           if(this.phy == o.phy){
            // decission depend on chemistry
            if(this.chem == o.chem){
                return this.maths - o.maths;
            }else{
                return o.chem - this.chem;
            }
           }else{
            return this.phy - o.phy;
           }
        }
    }
    public void customSort (int phy[], int chem[], int math[], int N){
        Students[] sarr = new Students[N];
        
        for(int i=0;i<N;i++){
            sarr[i] = new Students(phy[i],chem[i],math[i]);
        }
        Arrays.sort(sarr);
        for(int i=0;i<phy.length;i++){
            phy[i] = sarr[i].phy;
            chem[i] = sarr[i].chem;
            math[i] = sarr[i].maths;
        }
    }


    // Union Of Two Sorted Arrays
    // https://practice.geeksforgeeks.org/problems/union-of-two-sorted-arrays/1
    public static ArrayList<Integer> findUnion(int arr1[], int arr2[], int n, int m){
    
        ArrayList<Integer> res = new ArrayList<>();

        int i = 0;
        int j = 0;
        while(i < n && j < m){
            if(arr1[i] == arr2[j]){
                if(res.size() == 0 || res.get(res.size()-1) != arr1[i]){
                    res.add(arr1[i]);
                }
                i++;
                j++;
            }else if(arr1[i] < arr2[j]){
                if(res.size() == 0 || res.get(res.size()-1) != arr1[i]){
                    res.add(arr1[i]);
                }
                i++;
            }else{
                if(res.size() == 0 || res.get(res.size()-1) != arr2[j]){
                    res.add(arr2[j]);
                }
                j++;
            }
        }
        while(i < n){
            if(res.size() == 0 || res.get(res.size()-1) != arr1[i]){
                res.add(arr1[i]);
            }
            i++;
        }
        while(j < m){
            if(res.size() == 0 || res.get(res.size()-1) != arr2[j]){
                res.add(arr2[j]);
            }
            j++;
        }
        return res;
    }

    // search in 2D matrix,Portal
    private static int findRowIndx(int[][] matrix,int target){
        int indx = -1;
        int low = 0;
        int high = matrix.length-1;

        while(low <= high){
            int midRow = low + (high-low)/2;
            if(matrix[midRow][0] <= target && target <= matrix[midRow][matrix[0].length-1]){
                indx = midRow;
                break;
            }else if(target < matrix[midRow][0]){   
                high = midRow-1;
            }else{
                low = midRow+1;
            }
        }
        return indx;
    }
    private static int findInIthRow(int[][] matrix,int target,int row){
        int indx = -1;
        int low = 0;
        int high = matrix[0].length-1;

        while(low <= high){
            int mid = low + (high-low)/2;
            if(matrix[row][mid] == target){
                indx = mid;
                break;
            }else if(matrix[row][mid] < target){   
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return indx;
    }
    public static boolean search(int[][]matrix,int target) {
        int rowIndx = findRowIndx(matrix,target);
        if(rowIndx == - 1) return false;
        int colIdx = findInIthRow(matrix,target,rowIndx);
        return colIdx != -1;
    }


    // Search A 2d Matrix - 2
    public static boolean search2(int[][]matrix,int target) {
        int i = 0;
        int j = matrix[0].length-1;
        while(i >=0 && i<matrix.length && j>=0 && j<matrix[0].length){

            if(matrix[i][j] == target){
                return true;
            }else if(matrix[i][j] < target){
                i++;
            }else{
                j--;
            }
        }
        return false;
    }


    // Find Pivot Index (Equilibrium point)
    // https://leetcode.com/problems/find-pivot-index/
    // 724. Find Pivot Index

    public static int pivot_index(int[]nums) {
        int tsum = 0;
        for(int i:nums){
            tsum += i;
        }
        int lsum = 0;

        int idx = -1;
        for(int i=0;i<nums.length;i++){
            if(i >0 ){
                lsum += nums[i-1];
            }
            tsum -= nums[i];
            if(lsum == tsum){
                idx = i;
                break;
            }
        }
        return idx;
    }

    // /Find K Closest Elements
    // PriorityQueue Approach
    public static class Pair implements Comparable<Pair>{
        int val;
        int dist;
        public Pair(int val,int dist){
            this.val = val;
            this.dist = dist;
        }
        public int compareTo(Pair o){
            if(this.dist == o.dist){
                return this.val-o.val;
            }else{
                return this.dist-o.dist;
            }
        }
    }
    public static ArrayList<Integer> findClosest(int[]arr,int k,int x) {
        ArrayList<Integer> res = new ArrayList<>();

        PriorityQueue<Pair> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i =0;i<arr.length;i++){
            if(i < k){
                pq.add(new Pair(arr[i],Math.abs(arr[i]-x)));
            }else{
                if(pq.peek().dist > Math.abs(arr[i]-x)){
                    pq.remove();
                    pq.add(new Pair(arr[i],Math.abs(arr[i]-x)));
                }
            }
        }
        while(pq.size() > 0){
            res.add(pq.remove().val);
        }
        Collections.sort(res);
        return res;
    }

    // Find Pair Given Difference 
    // https://practice.geeksforgeeks.org/problems/find-pair-given-difference1559/1
    public boolean findPair(int arr[], int size, int n){
        if(size == 0) return false;
        Arrays.sort(arr);
        int left  = 0;
        int right = 1;
        while(right < size){
            int diff = arr[right] - arr[left];
            if(diff == n){
                return true;
            }else if(diff < n){
                right++;
            }else{
                left++;
            }
        }
        return false;
    }

    // Roof Top
    // https://practice.geeksforgeeks.org/problems/roof-top-1587115621/1
    public static int findMaxSteps(int[] arr) {
        int i = 1;
        int res = 0;
        while(i < arr.length){
            int count = 0;
            while(i < arr.length && arr[i] > arr[i-1]){
                count++;
                i++;
            }
            res = Math.max(res,count);
            i++;
        }
        return res;
    }

    //Maximize Sum Of Arr[i]*i Of An Array
    // https://practice.geeksforgeeks.org/problems/maximize-arrii-of-an-array0026/1
    public static int maximise(int[] arr) {
        long sum = 0;
        int mod = (int)(1e9) + 7;
        Arrays.sort(arr);
        for(int i=0;i<arr.length;i++){
            sum += (long)(arr[i]*i);
        }
        return (int)(sum%mod);
    }

    // Max Sum In The Configuration,portal
    public static int maximise2(int[]arr) {
        int sum = 0;
        int sIm1 = 0; //s[i-1]
        for(int i=0;i<arr.length;i++){
            sum += arr[i];
            sIm1 += arr[i]*i;
        }
        int n = arr.length;
        int res = Integer.MIN_VALUE;
        for(int i=1;i<arr.length;i++){
            int sI = sIm1+sum-n*arr[n-i];
            res = Math.max(sI,sIm1);
            sIm1 = sI;
        }
        return res;
    }
    // Binary Search
    public int BS(int[] arr,int t){
        int lo = 0;
        int hi = arr.length-1;
        int indx = -1;
        while(lo < hi){
            int mid = lo + (hi-lo)/2;
            if(arr[mid] == t){
                indx = mid;
                break;
            }else if(arr[mid] < t){
                lo = mid+1;
            }else{
                hi = mid-1;
            }
        }
        return indx;
    }
    // first Indx
    public int firstIndx(int[] arr,int t){
        int lo = 0;
        int hi = arr.length-1;
        int indx = -1;
        while(lo < hi){
            int mid = lo + (hi-lo)/2;
            if(arr[mid] == t){
                indx = mid;
                hi = mid-1;
            }else if(arr[mid] < t){
                lo = mid+1;
            }else{
                hi = mid-1;
            }
        }
        return indx;
    }
    // last Indx
    public int lastIndx(int[] arr,int t){
        int lo = 0;
        int hi = arr.length-1;
        int indx = -1;
        while(lo < hi){
            int mid = lo + (hi-lo)/2;
            if(arr[mid] == t){
                indx = mid;
                lo = mid+1;
            }else if(arr[mid] < t){
                lo = mid+1;
            }else{
                hi = mid-1;
            }
        }
        return indx;
    }

    //findTransition
    // https://practice.geeksforgeeks.org/problems/find-transition-point/1
    public static int findTransition(int[] arr) {
        int lo = 0;
        int hi = arr.length-1;
        int indx = -1;
        while(lo <= hi){
            int mid = lo + (hi-lo)/2;
            if(arr[mid] == 1){
                indx = mid;
                hi = mid-1;
            }else if(arr[mid] < 1){
                lo = mid+1;
            }else{
                hi = mid-1;
            }
        }
        return indx;
    }

    //First Bad Version
    // https://leetcode.com/problems/first-bad-version/
    // public static int firstBadVersion(int n) {
    //     int lo = 1;
    //     int hi = n;
    //     int indx = -1;
    //     while(lo <= hi){
    //         int mid = lo + (hi-lo)/2;
    //         if(isBadVersion(mid) == true){
    //             indx = mid;
    //             hi = mid-1;
    //         }else{
    //             lo = mid+1;
    //         }
    //     }
    //     return indx;
    // }

    // Guess Number Higher Or Lower
    // public static int guessNumber(int n) {
    //    int lo = 1;
    //    int hi = n;
    //    int idx = -1;
    //     while(lo <= hi){
    //         int mid = lo+(hi-lo)/2;
    //         int res = guess(mid);
    //         if(res == 0){
    //             idx = res;
    //             break;
    //         }else if(res == -1){
    //             hi = mid-1;
    //         }else{
    //             lo = mid+1;
    //         }
    //     }
    //    return idx;
    // }

    // k closest element BinarySearchApproachs
    // https://leetcode.com/problems/find-k-closest-elements/
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        LinkedList<Integer> res = new LinkedList<>();

        int lo = 0;
        int hi = arr.length-1;
        int indx = 0;
        while(lo <= hi){
            int mid = lo + (hi - lo)/2;
            indx = Math.abs(arr[indx] - x) > Math.abs(arr[mid] - x) ? mid : indx;
            if(arr[mid] == x){
                break;
            }else if(arr[mid] > x){
                hi = mid-1;
            }else{
                lo = mid+1;
            }
        }
        lo = indx-1;
        hi = indx;
        while(res.size() < k && lo >=0 && hi < arr.length){
            if(Math.abs(x - arr[lo]) <= Math.abs(arr[hi] -x)){
                res.addFirst(arr[lo]);
                lo--;
            }else{
                res.addLast(arr[hi]);
                hi++;
            }
        }
        while(res.size() < k && lo >= 0){
            res.addFirst(arr[lo]);
            lo--;   
        }
        while(res.size() < k && hi < arr.length){
            res.addLast(arr[hi]);
            hi++;   
        }
        return res;
    }

    // Search In Rotated Sorted Array
    // https://leetcode.com/problems/search-in-rotated-sorted-array/
    public int search(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length-1;
        int indx = -1;

        while(lo <= hi){
            int mid = lo + (hi-lo)/2;
            if(arr[mid] == target){
                // found
                indx = mid;
                break;
            }else if(arr[mid] < arr[hi]){
                // right side sorted
                // is my data on right side ?
                if(arr[mid] < target && target <= arr[hi]){
                    lo = mid+1;
                }else{
                    hi = mid-1;
                }
            }else{
                // left side sorted
                // is my data on left side ?
                if(arr[lo] <= target && target < arr[mid]){
                    hi = mid-1;
                }else{
                    lo = mid+1;
                }
            }
        }
        return indx;
    }                          

    // Find Minimum In Rotated Sorted Array
    // https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
    public static int findMinimum(int[]arr) {
        int lo = 0;
        int hi = arr.length-1;
        while(lo <= hi){
            int mid = lo + (hi-lo)/2;
            if(arr[mid] <  arr[hi]){
                hi = mid;
            }else{
                lo = mid+1;
            }
        }
        return arr[hi];
    }
    
    // Find Rotation Count (similar to min sorted rotated array) just return index meaning the rotation count
    public static int findRotationCount(int[]arr) {
        int lo = 0;
        int hi = arr.length-1;
        while(lo <= hi){
            int mid = lo + (hi-lo)/2;
            if(arr[mid] <  arr[hi]){
                hi = mid;
            }else{
                lo = mid+1;
            }
        }
        return hi;
    }

    // Count Inversions
    // https://practice.geeksforgeeks.org/problems/inversion-of-array-1587115620/1
    static long c_inv = 0;
    private static long[] mergeSortedArr(long [] arr1,long[] arr2){
        int n1 = arr1.length;
        int n2 = arr2.length;
        long[] res = new long[n1+n2];
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < n1 && j < n2){
            if(arr1[i] > arr2[j]){
                res[k] = arr2[j];
                j++;
                c_inv += n1-i;
            }else{
                res[k] = arr1[i];
                i++;
            }
            k++;
        }
        while(i < n1){
            res[k] = arr1[i];
            k++;
            i++;
        }
        while(j < n2){
            res[k] = arr2[j];
            k++;
            j++;
        }
        return res;
    }
    private static long[] mergeSort(long[] arr,int lo,int hi){
        if(lo == hi){
            long[] bres = {arr[lo]};
            return bres;
        }
        int mid = lo + (hi-lo)/2;
        long[] lres = mergeSort(arr, lo, mid);
        long[] rres = mergeSort(arr, mid+1, hi);
        long[] res = mergeSortedArr(lres,rres);
        return res;
    }
    static long inversionCount(long arr[], long N)
    {
        c_inv = 0;
        long[] res = mergeSort(arr,0,arr.length-1);
        return c_inv;    
    }


    // Median Of Two Sorted Arrays
    public static int[] merge(int[] arr1,int[] arr2){
        int k = 0;
        int n1 = arr1.length;
        int n2 = arr2.length;
        int i = 0;
        int j = 0;
        int[] res = new int[n1+n2];

        while(i < n1 && j < n2){
            if(arr1[i] < arr2[j]){
                res[k] = arr1[i];
                i++;
            }else{
                res[k] = arr2[j];
                j++;
            }
            k++;
        }
        while(i < n1){
            res[k] = arr1[i];
            i++;
            k++;
        }
        while(j < n2){
            res[k] = arr2[j];
            j++;
            k++;
        }
        return res;
    }
    // find median of two sorted arrays
    // BRute force merge two arrays approach
    // O(n+m)
    public static double find(int[]a1,int[]a2) {
        int[] merged = merge(a1, a2);
        int n = merged.length;
        double res = 0.0;
        if(n % 2 == 0){
            int mid1 = (n/2)-1;
            int mid2 = (n/2);
            res = (merged[mid1]+merged[mid2])/2.0;
        }else{
            int mid = (n/2);
            res = merged[mid]/1.0;
        }
        return res;
    }
    // optimized approach 0(log(min(m,n)))
    // public static double find2(int[] a,int[]b) {
    //     int n1 = a.length;
    //     int n2 = b.length;
    //     if(n1 > n2){
    //         int[] temp = a;
    //         a = b;
    //         b = temp;

    //         int temp2 = n1;
    //         n1 = n2;
    //         n2 = temp2;
    //     }
    //     int lo = 0;
    //     int hi = n1;
    //     int te = n1+n2;
    //     while(lo <= hi){
    //         int ali = lo + (hi-lo)/2; //aleftindex
    //         int bli = ((te + 1) / 2)-ali;
    //         // bleftindex


    //     }
    // }






    // count zeroes in sorted martrix of 0,1
    public static int countZeros(int[][]mat) {
        int count = 0;

        int i = 0;
        int j = mat[0].length-1;
        while(i < mat.length && j >= 0){
            if(mat[i][j] == 0){
                count += (j+1);
                i++;
            }else{
                j--;
            }
        }
        return count;
    }
    // find ciel Index
    public static int cielIdx(int[] arr,int val){
        
        int cielIdx = -1;
        int low = 0;
        int high = arr.length-1;
        while(low <= high){
            int mid = low + (high- low)/2;
            if(arr[mid] > val){
                cielIdx = mid;
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        return cielIdx;
    }
    // floor index
    public static int floorIdx(int[] arr,int val){
        
        int floorIdx = -1;
        int low = 0;
        int high = arr.length-1;
        while(low <= high){
            int mid = low + (high- low)/2;
            if(arr[mid] < val){
                floorIdx = mid;
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return floorIdx;
    }
    // Counting Elements In Two Arrays
    // O(nlogm)
    public static int[] find2(int[]arr1, int[]arr2) {
        int n = arr1.length;
        Arrays.sort(arr2);
        int[] res = new int[n];
        for(int i=0;i<res.length;i++){
            int count = cielIdx(arr2, arr1[i]);
            res[i] = count == -1 ? arr2.length : count;
        }
        return res;
    }
    // O(n)+O(n)
    public static int[] find3(int[]arr1, int[]arr2) {
        int n = 0;
        // find max
        for(int i:arr1){
            n = Math.max(i,n);
        }
        for(int i:arr2){
            n = Math.max(i,n);
        }
        // create fmap
        int[] fmap = new int[n+1];
        for(int el:arr2){
            fmap[el] += 1;
        }
        // prefix sum
        for(int i=1;i<fmap.length;i++){
            fmap[i] += fmap[i-1];
        }
        // make res using fmap
        int[] res = new int[arr1.length];
        for(int i=0;i<res.length;i++){
            res[i] = fmap[arr1[i]];
        }
        return res;
    }

    // Count Zeros Xor Pairs
    // O(n)+O(n)
    public static int countPairs(int[]arr) {
        HashMap<Integer,Integer> hmap = new HashMap<>();
        for(int i:arr){
            int c = hmap.getOrDefault(i, 0);
            hmap.put(i, c+1);
        }
        int count = 0;
        for(int key:hmap.keySet()){
            int t  = hmap.get(key);
            count += (t*(t-1))/2;
        }
        return count;
    }

    // Facing The Sun
    // 0(n)
    public static int countBuildings(int[]ht) {
        int max = ht[0];
        int count =  1;
        for(int i=1;i<ht.length;i++){
            if(max < ht[i]){
                max = ht[i];
                count++;
            }
        }
        return count;
    }

    // Pending question
    // Distinct Absolute Array Elements
    // public static int count(int[]arr) {
        // Pending question
    // }





    // Find element that appear once.
    // https://leetcode.com/problems/single-element-in-a-sorted-array/
    public static int singleNonDuplicate(int[]arr) {

        int lo = 0;
        int hi = arr.length-1;
        while(lo <= hi){
            int mid = lo + (hi-lo)/2;
            if((mid == 0 || mid == arr.length-1) || (arr[mid-1] != arr[mid] && arr[mid] != arr[mid+1])){
                return arr[mid];
            }else if(arr[mid+1] == arr[mid]){
                if((hi-mid+1)%2 == 0){
                    hi = mid-1;
                }else{
                    lo = mid;
                }
            }else{
                if((mid-lo+1)%2 == 0){
                    lo = mid+1;
                }else{
                    hi = mid;
                }
            }
        }
        return -1;
    }

    // Punish the students
    public static int swapCount(int[] arr){
        // Bubble Sort
        int swaps = 0;
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j] < arr[j+1]){
                    swaps += 2;
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return swaps;
    }
    public static boolean shouldPunish(int[]roll, int[]marks, double avg) {
        int swaps = swapCount(roll);
        int sum = 0;
        for(int m:marks){
            sum += m;
        }
        return ((sum-swaps)/marks.length >= avg);
    }


    // Largest Number
    // https://leetcode.com/problems/largest-number/submissions/
    public static String largestNumber(int[]nums) {
        String[] starr = new String[nums.length];
        for(int i=0;i<nums.length;i++){
            starr[i] = nums[i]+"";
        }
        Arrays.sort(starr,(a,b)->{
            long d1 = Long.parseLong(a+b);
            long d2 = Long.parseLong(b+a);
            if(d1 > d2){
                return 1;
            }else if(d1 < d2){
                return -1;
            }else{
                return 0;
            }
        });
        StringBuilder st = new StringBuilder("");
        for(int i=starr.length-1;i>=0;i--){
            String s = starr[i];
            st.append(s);
        }
        String res = st.toString();
        return res.indexOf("0") == 0 ? "0" : res;
    }

    // Largest Perimeter Triangle
    public static int largestPerimeter(int[]nums) {
        Arrays.sort(nums);
        int a = 0;
        int b = 0;
        int c = 0;
        int indx = nums.length-3;
        while(indx >= 0){
            a = nums[indx];
            b = nums[indx+1];
            c = nums[indx+2];
            if(a+b > c){
                return a+b+c;
            }
            indx--;
        }
        return 0;
    }

    


    public static void main(String[] args) {
        int[] arr = {1,1,2,2,3,3,4,5,6,7,7,8,9,10,10};
        System.out.println("Ciel Index");
        System.out.println(cielIdx(arr, 1));
        System.out.println(cielIdx(arr, 2));
        System.out.println(cielIdx(arr, 3));
        System.out.println(cielIdx(arr, 7));
        System.out.println(cielIdx(arr, 4));
        System.out.println(cielIdx(arr, 5));
        System.out.println("Floor Index");
        System.out.println(floorIdx(arr, 2));
        System.out.println(floorIdx(arr, 3));
        System.out.println(floorIdx(arr, 5));
        System.out.println(floorIdx(arr, 4));
        System.out.println(floorIdx(arr, 10));
        System.out.println(floorIdx(arr, 12));
    }
}
