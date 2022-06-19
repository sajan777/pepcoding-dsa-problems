public class revision {
    
    // 121. Best Time to Buy and Sell Stock
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int profit = 0;
        for(int i=0;i<prices.length;i++){
            min = Math.min(min,prices[i]);
            profit = Math.max(profit,prices[i] - min);
        }
        return profit;
    }
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
    // 122. Best Time to Buy and Sell Stock II
    public int maxProfit2(int[] prices) {
        int oprofit = 0; //overallprofit;
        int buyDay = 0;
        int sellDay = 0;
        for(int i=1;i<prices.length;i++){
            if(prices[i] > prices[i-1]) {
                sellDay++;
            } else {
                oprofit += prices[sellDay] - prices[buyDay];
                buyDay = sellDay = i;
            }
        }
        oprofit += prices[sellDay] - prices[buyDay];
        return oprofit;
    }

    // 714. Best Time to Buy and Sell Stock with Transaction Fee
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
    public int maxProfit3(int[] prices, int fee) {
       int pibt = -prices[0];
    //    init -ve because we dont have money to buy
       int pist = 0;
    //    init 0 because there is no meaning to sell first day
       for(int i=1;i<prices.length;i++){
        int npibt = Math.max(pibt,pist-prices[i]);
        // new profit if buy today is (previous profit if buy today vs (previous profit if sell today - current price))
        int npist = Math.max(pist,prices[i]+pibt-fee);
        // new profit if sell today is (previous profit if sell today vs current price + profit if buy today - fee)
        pibt = npibt;
        pist = npist;
       }
       return pist;
    }

    // 309. Best Time to Buy and Sell Stock with Cooldown
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
    // infinite transactions allowed
    public int maxProfit4(int[] prices) {
        int pibt = -prices[0];
        int pist = 0;
        int cooldown = 0;
       for(int i=1;prices.length;i++){
        int npibt = Math.max(pibt,cooldown-prices[i]);
        cooldown = pist;
        int npist = Math.max(pist,prices[i]+pibt);
        pibt = npibt;
        pist = npist;
       }
       return pist;
    }

    // 123. Best Time to Buy and Sell Stock III
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
    // atmost 2 transactions allowed
    public int maxProfit5(int[] prices) {
        // dp = profit if sell today
        int[] dp = new int[prices.length];
        int profit = 0;
        // fill from left to right
        // profit if we sell stock today;
        int min = prices[0];
        for(int i=1;i<prices.length;i++){
            min = Math.min(min,prices[i]);
            dp[i] = Math.max(dp[i-1],prices[i]-min);
        }
        // fill from right to left;
        int max = Integer.MIN_VALUE;
        for(int i=prices.length-1;i>=0;i--){
            max = Math.max(max,prices[i]);
            int profitFromBuyToday = max - prices[i];
            profit = Math.max(profit,dp[i] + profitFromBuyToday);
        }
        return profit;
    }   
    // stock buy and sell k transactions allowed
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
    // O(n^3)
    public int maxProfit6(int k, int[] prices) {
        if(prices.length == 0) return 0;
        int[][] dp = new int[k+1][prices.length];
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                int no_transaction = dp[i][j-1];
                int max_profit_if_transaction = 0;
                for(int r=j-1;r>=0;r--){
                    max_profit_if_transaction = Math.max(max_profit_if_transaction,prices[j] - prices[r] + dp[i-1][r]);
                }
                dp[i][j] = Math.max(no_transaction,max_profit_if_transaction);
            }
        }
        return dp[k][prices.length-1];
    }
    
    // https://leetcode.com/problems/longest-increasing-subsequence/
    // 300. Longest Increasing Subsequence
    public int lengthOfLIS(int[] arr) {
        int length = 0;
        int[] dp = new int[arr.length];
        dp[0] = 1;
        for(int i=1;i<arr.length;i++){
            int max = 0;
            for(int j=i-1;j>=0;j--){
                if(arr[j] < arr[i]) {
                    max = Math.max(max,dp[j]);
                }
            }
            dp[i] = max+1;
            length = Math.max(length,dp[i]);
        }
        return length;
    }
    // Maximum sum increasing subsequence 
    // https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1/
    public int maxSumIS(int arr[], int n) {  
	    if(n == 0) return 0;
	    int sum = arr[0];
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        for(int i=1;i<arr.length;i++){
            int max = 0;
            for(int j=i-1;j>=0;j--){
                if(arr[j] < arr[i]) {
                    max = Math.max(max,dp[j]);
                }
            }
            dp[i] = max+arr[i];
            sum = Math.max(sum,dp[i]);
        }
        return sum;
	}
    
    // Longest Decreasing Subsequence
    // Travel backwards same logic of LIS
    public static int lengthOfLDS(int[] arr) {
        int length = 0;
        int[] dp = new int[arr.length];
        dp[arr.length-1] = 1;
        for(int i=arr.length-2;i>=0;i--){
            int max = 0;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j] < arr[i]) {
                    max = Math.max(max,dp[j]);
                }
            }
            dp[i] = max+1;
            length = Math.max(length,dp[i]);
        }
        return length;      
    }
    // https://practice.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1/
    // Longest Bitonic subsequence
    public int[] lengthOfLIS2(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = 1;
        for(int i=1;i<arr.length;i++){
            int max = 0;
            for(int j=i-1;j>=0;j--){
                if(arr[j] < arr[i]) {
                    max = Math.max(max,dp[j]);
                }
            }
            dp[i] = max+1;
        }
        return dp;
    } 
    public static int[] lengthOfLDS2(int[] arr) {
        int[] dp = new int[arr.length];
        dp[arr.length-1] = 1;
        for(int i=arr.length-2;i>=0;i--){
            int max = 0;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j] < arr[i]) {
                    max = Math.max(max,dp[j]);
                }
            }
            dp[i] = max+1;
        }
        return dp;      
    }
    public int LongestBitonicSequence(int[] nums){
        int[] lis = lengthOfLIS2(nums);
        int[] lds = lengthOfLDS2(nums);
        int len = 0;
        for(int i=0;i<nums.length;i++){
            len = Math.max(len,lis[i]+lds[i]-1);
        }
        return len;
    }
    // Maximum Non-overlapping Bridges
    // application of LIS
    private static class Pair implements Comparable<Pair> {
        int north;
        int south;
        public Pair(int north,int south){
            this.north = north;
            this.south = south;
        }
        public int compareTo(Pair o){
            if(this.north == o.north){
                return this.south - o.south;
            } else {
                return this.north - o.north;
            }
        }
    }
    public static int MaxNonOverlappingBridges(int[][] bridges){
        Pair[] arr = new Pair[bridges.length];
        for(int i=0;i<bridges.length;i++){
            arr[i] = new Pair(bridges[i][0],bridges[i][1]);
        }
        Arrays.sort(arr);
        // LIS on South Pole
        int length = 0;
        int[] dp = new int[bridges.length];
        dp[0] = 1;
        for(int i=1;i<arr.length;i++){
            int max = 0;
            for(int j=i-1;j>=0;j--){
                if(arr[j].south < arr[i].south) {
                    max = Math.max(max,dp[j]);
                }
            }
            dp[i] = max+1;
            length = Math.max(length,dp[i]);
        }
        return length;
    }

    // Scanner scn = new Scanner(System.in);
    //     int n = scn.nextInt();
    //     int[][] arr = new int[n][2];
    //     for(int i=0;i<n;i++){
    //         arr[i][0] = scn.nextInt();
    //         arr[i][1] = scn.nextInt();
    //     }
    //     int res = MaxNonOverlappingBridges(arr);
    //     System.out.println(res);
    

    // Russian Doll Envelopes
    // https://leetcode.com/problems/russian-doll-envelopes/ - Leetcode TLE (because LIS can be done in O(nlogn))
    private static class Pair1 implements Comparable<Pair1> {
        int width;
        int height;
        public Pair1(int width,int height){
            this.width = width;
            this.height = height;
        }
        public int compareTo(Pair1 o){
            if(this.height == o.height){
                return this.width - o.width;
            } else {
                return this.height - o.height;
            }
        }
    }
    public static int russianDolls(int[][] envelopes){
        Pair1[] arr = new Pair1[envelopes.length];
        for(int i=0;i<envelopes.length;i++){
            arr[i] = new Pair1(envelopes[i][0],envelopes[i][1]);
        }
        Arrays.sort(arr);
        // LIS on width
        int length = 0;
        int[] dp = new int[envelopes.length];
        dp[0] = 1;
        for(int i=1;i<arr.length;i++){
            int max = 0;
            for(int j=i-1;j>=0;j--){
                if(arr[j].width < arr[i].width) {
                    max = Math.max(max,dp[j]);
                }
            }
            dp[i] = max+1;
            length = Math.max(length,dp[i]);
        }
        return length;
    }

    // Maximum Length Of Pair Chain
    public static int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs , (a,b)->{
            return a[1] - b[1];
        });
        // LIS
        int[] dp = new int[pairs.length];
        dp[0] = 1;
        int length = dp[0];
        for(int i=1;i<pairs.length;i++){
            int max = 0;
            for(int j=i-1;j>=0;j--){
                if(pairs[j][1] < pairs[i][0]) {
                    max = Math.max(max,dp[j]);
                }
            }
            dp[i] = max+1;
            length = Math.max(length,dp[i]);
        }
        return length;
    }

    // Box Stacking
    // https://practice.geeksforgeeks.org/problems/box-stacking/1/
    public static class Box implements Comparable<Box> {
        int l;
        int b;
        int h;
        int area; // in terms of base;
        public Box(int l,int b,int h){
            this.l = l;
            this.b = b;
            this.h = h;
            this.area = this.l * this.b;
        }
        public int compareTo(Box o) {
            if(o.area != this.area)
                return o.area - this.area; // decreasing order  
            else
                return o.h - this.h; // if area not equal do it with height.... (decreasing order)

            // return o.l*o.b*o.h - this.l*this.b*this.h; (using volume)
        }
    }
    public static int maxHeight(int[] height, int[] width, int[] length, int n){
        Box[] arr = new Box[3 * n];
        int j = 0;
        for(int i=0;i<n;i++){
            // LBH
            if(length[i] > width[i]){
                arr[j++] = new Box(length[i],width[i],height[i]);
            }else{
                arr[j++] = new Box(width[i],length[i],height[i]);
            }
            // HBL
            if(height[i] > width[i]){
                arr[j++] = new Box(height[i],width[i],length[i]);
            }else{
                arr[j++] = new Box(width[i],height[i],length[i]);
            }
            // LHB
            if(length[i] > height[i]){
                arr[j++] = new Box(length[i],height[i],width[i]);
            }else{
                arr[j++] = new Box(height[i],length[i],width[i]);
            }
        }
        Arrays.sort(arr);
        int[] dp = new int[3 * n];
        dp[0] = arr[0].h;
        int res = dp[0];
        for(int i=1;i<3*n;i++){
            int max = 0;
            for(int k=i-1;k>=0;k--){
                if(arr[k].l > arr[i].l && arr[k].b > arr[i].b){
                    max = Math.max(max,dp[k]);
                }
            }
            dp[i] = max + arr[i].h;
            res = Math.max(res,dp[i]);
        }
        return res;
    }
    // https://leetcode.com/problems/maximum-height-by-stacking-cuboids/
    public int maxHeight2(int[][] cuboids) {
        Box[] arr = new Box[cuboids.length];
        int j = 0;
        for(int[] box : cuboids) {
            Arrays.sort(box);
            arr[j++] = new Box(box[0], box[1], box[2]); // only 1 config as we are maximizing height so once sorted height is maximized in case of rotating of box
        }
        Arrays.sort(arr);
        int[] dp = new int[arr.length];
        dp[0] = arr[0].h;
        int maxHeight = dp[0];
        for(int i = 1; i < arr.length; i++) {
            int max = 0;
            for(int k = i - 1; k >= 0; k--) {
                if(arr[k].l >= arr[i].l && arr[k].b >= arr[i].b && arr[k].h >= arr[i].h) {
                    max = Math.max(max, dp[k]);
                }
            }
            dp[i] = max + arr[i].h;
            maxHeight = Math.max(maxHeight, dp[i]);
        }
        return maxHeight;
    }

    // https://leetcode.com/problems/palindromic-substrings/
    // 647. Palindromic Substrings
    // (Very important)
    // O(n2)
    public int countSubstrings(String s) {
        int n = s.length();
        int count = 0;
        boolean[][] dp = new boolean[n][n];
        for(int gap = 0;gap < n;gap++){
            for(int i=0,j=gap;j<n;i++,j++){
                if(gap == 0){
                    dp[i][j] =  true;
                } else if(gap == 1){
                    if(s.charAt(i) == s.charAt(j)){
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = false;
                    }
                } else {
                    dp[i][j] = ((s.charAt(i) == s.charAt(j)) && dp[i+1][j-1] );
                }
                if (dp[i][j]) count += 1;
            }
        }
        return count;
    }

    // https://leetcode.com/problems/longest-palindromic-substring/
    // 5. Longest Palindromic Substring
    public String longestPalindrome(String s) {
        int n = s.length();
        int x = 0;
        int y = 0;
        boolean[][] dp = new boolean[n][n];
        for(int gap = 0;gap < n;gap++){
            for(int i=0,j=gap;j<n;i++,j++){
                if(gap == 0){
                    dp[i][j] =  true;
                } else if(gap == 1){
                    if(s.charAt(i) == s.charAt(j)){
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = false;
                    }
                } else {
                    dp[i][j] = ((s.charAt(i) == s.charAt(j)) && dp[i+1][j-1] );
                }
                if (dp[i][j]) {
                    x = i;
                    y = j;
                }
            }
        }
        return s.substring(x, y+1);
        // return s.substring(x, y+1).length(); if length
    }
    // Print All Paths With Minimum Jumps
    private static class minJumps {
        int indx;
        int jumps;
        int minJumps;
        String psf;
        public minJumps(int indx,int jumps,int minJumps,String psf) {
            this.indx = indx;
            this.jumps = jumps;
            this.minJumps = minJumps;
            this.psf = psf;
        }
    }
    // Video - 0 pending.....
    public static void printAllPathsMinJump(int arr[]){
        int n = arr.length;
        int[] dp = new int[n];
        dp[n-1] = 0;
        for(int i=n-2;i>=0;i--){
            int minJumps = Integer.MAX_VALUE;
            for(int jump=1;jump<=arr[i] && i+jump<n;j++){
                minJumps = Math.min(minJumps,dp[i + jump]);
            }
            dp[i] = minJumps == Integer.MAX_VALUE ? minJumps : minJumps + 1; //becuase if we add 1 in inf it goes circle
        }
        Queue<minJumps> qu = new LinkedList<>();
        qu.add(new minJumps(0, arr[0], dp[0], "0"));
        while(qu.size() > 0){
            int sz = qu.size();
            minJumps rem = qu.remove();
            for(int jump=1;jump<rem.jumps && jump+rem.indx < n;jump++){
                if(dp[rem.indx + jump] == rem.minJumps - 1){
                    qu.add(new minJumps(rem.indx+jump, arr[rem.indx + jump], rem.minJumps - 1, rem.psf + " -> " +(rem.indx+jump)));
                }
            }

        }
    }

    // Print All Paths With Target Sum Subset
    // https://practice.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1/
    // Fractional Knapsack 
    double fractionalKnapsack(int W, Item arr[], int n) {
        
    }

    public static void main(String[] args) {
        
    }
}
