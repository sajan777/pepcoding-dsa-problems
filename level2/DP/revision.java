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

    public static void main(String[] args) {
        
    }
}
