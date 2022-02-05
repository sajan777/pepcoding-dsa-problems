import java.util.*;

class questions {

    // stock buy and sell one transaction allowed
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-i/
    public int maxProfit(int[] stocks) {
        int oprofit = 0;
    
        int min = Integer.MAX_VALUE;
        for(int d=0;d<stocks.length;d++){
            int price = stocks[d];
            min = Math.min(min,price);
            oprofit = Math.max(oprofit,price-min);
        }
        return oprofit;
    }

    // stocks buy and sell infinite transactions allowed
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
    public int maxProfit2(int[] prices) {
        int bd = 0;
        int sd = 0;
        int profit = 0;

        for(int i=1;i<prices.length;i++){
            if(prices[i] > prices[i-1]) {
                sd++;
            } else {
                profit += prices[sd] - prices[bd];
                // reset after gaining profit from bd and sd
                bd = i;
                sd = i;
            }
        }
        profit += prices[sd] - prices[bd];
        return profit;
    }

    // stock buy and sell with transaction fees, infinte transactions allowed
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
    public int maxProfit3(int[] prices, int fee) {
        int profit = 0;
        int pibt = -prices[0]; //pibt -=> profit if buy today
        int pist = 0; // pist -> profit if sell today
        for(int i=1;i<prices.length;i++) {
            int npibt = Math.max(pibt,pist-prices[i]);
            // npibt -> new profit if buy today
            int npist = Math.max(pist,prices[i] + pibt - fee); 
            // npist -> new profit if sell today 
            pibt = npibt;
            pist = npist;
        }
        return pist;
    }

    // Buy and sell stocks infinte transactions with cooldown of 1 day after selling
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
    public int maxProfit4(int[] prices) {
        int pibt = -prices[0]; //pibt -=> profit if buy today
        int pist = 0; // pist -> profit if sell today
        int pwcd = 0;
        // pwcd -> profit with cooldown
        for(int i=1;i<prices.length;i++) {
            int npibt = Math.max(pibt,pwcd-prices[i]);
            pwcd = pist;
            // npibt -> new profit if buy today
            int npist = Math.max(pist,prices[i] + pibt); 
            // npist -> new profit if sell today 
            pibt = npibt;
            pist = npist;
        }
        return pist;
    }

    // stock buy and sell only two transactions allowed
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
    // nahi smajh aaya kuch b
    public int maxProfit5(int[] prices) {
        return 0;
    }


    // stock buy and sell k transactions allowed
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
    // O(n^3)
    public int maxProfit(int k, int[] prices) {
        if(prices.length == 0) return 0;
        int[][] dp = new int[k+1][prices.length];
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                int profit_no_transaction_today = dp[i][j-1];
                int profit_transaction_today = Integer.MIN_VALUE;
                for(int t=j-1;t>=0;t--){
                    profit_transaction_today = Math.max(profit_transaction_today,prices[j] - prices[t] + dp[i-1][t]);
                }
                dp[i][j] = Math.max(profit_no_transaction_today,profit_transaction_today);
            }
        }
        return dp[k][prices.length-1];
    }
    // check the O(n2) approach from shreesh code.

    // Longest increasing subsequence
    // https://leetcode.com/problems/longest-increasing-subsequence/
    // O(n^2)
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int ans = 1;
        for(int i=1;i<nums.length;i++){
            int max = 0;
            for(int j=i-1;j>=0;j--){
                if(nums[j] < nums[i]){
                    max = Math.max(max,dp[j]);
                }
            }
            dp[i] = max+1;
            ans = Math.max(ans,dp[i]);
        }
        return ans;
    }


    // maximum sum increasing subsequence
    // https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1/
    // O(n2)
    public int maxSumIS(int nums[], int n) {  
        if(nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int ans = nums[0];
        for(int i=1;i<nums.length;i++){
            int max = 0;
            for(int j=i-1;j>=0;j--){
                if(nums[j] < nums[i]){
                    max = Math.max(max,dp[j]);
                }
            }
            dp[i] = max + nums[i];
            ans = Math.max(ans,dp[i]);
        }
        return ans;
	}
    
    
    // Longest decreasing subsequence
    // O(n^2)
    // Travel backwards same logic of LIS
    public int lengthOfLDS(int[] nums) {
        if(nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[nums.length-1] = 1;
        int ans = 1;
        for(int i=nums.length-2;i>=0;i--){
            int max = 0;
            for(int j=i+1;j<nums.length;j++){
                if(nums[i] > nums[j]){
                    max = Math.max(max,dp[j]);
                }
            }
            dp[i] = max+1;
            ans = Math.max(ans,dp[i]);
        }
        return ans;
    }   

    // Bitonic Subsequence
    // DIY

    public static void main(String[] args) {
        
    }
}