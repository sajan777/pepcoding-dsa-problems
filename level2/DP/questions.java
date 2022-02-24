import java.util.*;

class questions {

    // stock buy and sell one transaction allowed
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-i/
    public int maxProfit(int[] stocks) {
        int oprofit = 0;

        int min = Integer.MAX_VALUE;
        for (int d = 0; d < stocks.length; d++) {
            int price = stocks[d];
            min = Math.min(min, price);
            oprofit = Math.max(oprofit, price - min);
        }
        return oprofit;
    }

    // stocks buy and sell infinite transactions allowed
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
    public int maxProfit2(int[] prices) {
        int bd = 0;
        int sd = 0;
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
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
        int pibt = -prices[0]; // pibt -=> profit if buy today
        int pist = 0; // pist -> profit if sell today
        for (int i = 1; i < prices.length; i++) {
            int npibt = Math.max(pibt, pist - prices[i]);
            // npibt -> new profit if buy today
            int npist = Math.max(pist, prices[i] + pibt - fee);
            // npist -> new profit if sell today
            pibt = npibt;
            pist = npist;
        }
        return pist;
    }

    // Buy and sell stocks infinte transactions with cooldown of 1 day after selling
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
    public int maxProfit4(int[] prices) {
        int pibt = -prices[0]; // pibt -=> profit if buy today
        int pist = 0; // pist -> profit if sell today
        int pwcd = 0;
        // pwcd -> profit with cooldown
        for (int i = 1; i < prices.length; i++) {
            int npibt = Math.max(pibt, pwcd - prices[i]);
            pwcd = pist;
            // npibt -> new profit if buy today
            int npist = Math.max(pist, prices[i] + pibt);
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
        if (prices.length == 0)
            return 0;
        int[][] dp = new int[k + 1][prices.length];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                int profit_no_transaction_today = dp[i][j - 1];
                int profit_transaction_today = Integer.MIN_VALUE;
                for (int t = j - 1; t >= 0; t--) {
                    profit_transaction_today = Math.max(profit_transaction_today, prices[j] - prices[t] + dp[i - 1][t]);
                }
                dp[i][j] = Math.max(profit_no_transaction_today, profit_transaction_today);
            }
        }
        return dp[k][prices.length - 1];
    }
    // check the O(n2) approach from shreesh code.

    // Longest increasing subsequence
    // https://leetcode.com/problems/longest-increasing-subsequence/
    // O(n^2)
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0)
            return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i < nums.length; i++) {
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // maximum sum increasing subsequence
    // https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1/
    // O(n2)
    public int maxSumIS(int nums[], int n) {
        if (nums.length == 0)
            return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + nums[i];
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // Longest decreasing subsequence
    // O(n^2)
    // Travel backwards same logic of LIS
    public static int lengthOfLDS(int[] nums) {
        if (nums.length == 0)
            return 0;
        int[] dp = new int[nums.length];
        dp[nums.length - 1] = 1;
        int ans = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            int max = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // longest Bitonic Subsequence
    // https://practice.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1/
    public int[] lds(int[] nums) {
        int[] dp = new int[nums.length];
        dp[nums.length - 1] = 1;
        int ans = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            int max = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            ans = Math.max(ans, dp[i]);
        }
        return dp;
    }

    public static int[] lis(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i < nums.length; i++) {
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            ans = Math.max(ans, dp[i]);
        }
        return dp;
    }

    public int LongestBitonicSequence(int[] nums) {
        int[] lis = lis(nums);
        int[] lds = lds(nums);
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans = Math.max(ans, lis[i] + lds[i] - 1);
        }
        return ans;
    }

    // Maximum Non-overlapping Bridges
    // pending question
    // exactly similar to max non overlap same code fully
    // https://leetcode.com/problems/russian-doll-envelopes/
    // Shreesh got TLE
    public int maxEnvelopes(int[][] envelopes) {
        return 0;
    }

    // https://leetcode.com/problems/maximum-length-of-pair-chain/
    // 646. Maximum Length of Pair Chain
    public int findLongestChain(int[][] pairs) {
        return 0;
    }

    // https://practice.geeksforgeeks.org/problems/box-stacking/1/
    // Box Stacking
    public static int maxHeight(int[] height, int[] width, int[] length, int n) {
        // Code here
        return 0;
    }

    // https://leetcode.com/problems/palindromic-substrings/
    // 647. Palindromic Substrings
    // (Very important)
    // O(n2)
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; j++, i++) {
                if (gap == 0) {
                    dp[i][j] = true;
                } else if (gap == 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }
                if (dp[i][j] == true)
                    count++;
            }
        }

        return count;
    }

    // https://leetcode.com/problems/longest-palindromic-substring/
    // 5. Longest Palindromic Substring
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int len = 0;
        int x = 0;
        int y = 0;
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; j++, i++) {
                if (gap == 0) {
                    dp[i][j] = true;
                } else if (gap == 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }
                if (dp[i][j] == true) {
                    len = gap;
                    x = i;
                    y = j;
                }
            }
        }

        return s.substring(x, y + 1);
    }
    // 11/feb/2022

    // https://classroom.pepcoding.com/myClassroom/the-switch-program-2/dynamic-programming-l2/min-jumps-re-official/ojquestion
    // Print all paths with min jump
    private static class minJumpsHelper {

    }

    public static void printPathMinJumps(int arr[]) {
        int n = arr.length;
        int[] dp = new int[n];
        dp[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            int minJumps = Integer.MAX_VALUE;
            for (int j = 1; j <= arr[i] && i + j < n; j++) {
                minJumps = Math.min(minJumps, dp[i + j]);
            }
            dp[i] = minJumps != Integer.MAX_VALUE ? minJumps + 1 : minJumps;
        }
    }

    // 15 feb 2022
    // Fractional Knapsack
    // https://practice.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1/
    class Item {
        int value, weight;

        Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    class fHelper implements Comparable<fHelper> {
        int val;
        int wt;
        double fraction;

        public fHelper(int val, int wt) {
            this.val = val;
            this.wt = wt;
            this.fraction = val * 1.0 / wt;
        }

        @Override
        public int compareTo(fHelper other) {
            // if (this.fraction > other.fraction) {
            // return 1;
            // } else if (this.fraction < other.fraction) {
            // return -1;
            // } else {
            // return 0;
            // }
            return Double.compare(this.fraction, other.fraction);
        }
    }

    double fractionalKnapsack(int cap, Item arr[], int n) {
        double profit = 0.0;
        PriorityQueue<fHelper> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (Item i : arr) {
            pq.add(new fHelper(i.value, i.weight));
        }
        while (pq.size() > 0 && cap > 0) {
            fHelper rem = pq.remove();
            if (rem.wt <= cap) {
                profit += rem.val;
                cap -= rem.wt;
            } else {
                profit += cap * rem.fraction;
                cap = 0;
            }
        }
        return profit;
    }

    // 279. Perfect Squares
    // https://leetcode.com/problems/perfect-squares/
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            int count = 1;
            while (i - (count * count) >= 0) {
                min = Math.min(min, dp[i - (count * count)]);
                count++;
            }
            dp[i] = min + 1;
        }
        return dp[n];
    }

    // Print All Longest Increasing Subsequences
    public static void printAllLIS(int[] arr) {
        int[] dp = lis(arr);
        // check code later
    }

    // https://leetcode.com/problems/maximal-square/
    // 221. Maximal Square
    public int maximalSquare(char[][] matrix) {
        int max = 0;
        int r = matrix.length;
        int c = matrix[0].length;
        int[][] dp = new int[r][c];
        for (int i = r - 1; i >= 0; i--) {
            for (int j = c - 1; j >= 0; j--) {
                if (i == r - 1 || j == c - 1 || matrix[i][j] == '0') {
                    dp[i][j] = matrix[i][j] - '0';
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i][j + 1], dp[i + 1][j + 1]), dp[i + 1][j]) + 1;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max * max;
    }

    // Catalan Number
    // O(n2) + O(n)
    public static int catalanNumber(int n) {
        if (n == 0 || n == 1)
            return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int sum = 0;
            int i1 = 0;
            int i2 = i - 1;
            while (i1 < i && i2 >= 0) {
                sum += dp[i1] * dp[i2];
                i1++;
                i2--;
            }
            dp[i] = sum;
        }
        return dp[n];
    }

    // Total number of BST's from n nodes
    // https://practice.geeksforgeeks.org/problems/unique-bsts-1587115621/1
    static int numTrees(int n) {
        long mod = (int) (1e9 + 7);
        if (n == 0 || n == 1)
            return 1;
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            long sum = 0;
            int i1 = 0;
            int i2 = i - 1;
            while (i1 < i && i2 >= 0) {
                long val = dp[i1] * dp[i2];
                sum = ((sum % mod) + (val % mod)) % mod;
                i1++;
                i2--;
            }
            dp[i] = sum;
        }
        return (int) (dp[n] % mod);
    }

    // Count Of Valleys And Mountains
    // https://classroom.pepcoding.com/myClassroom/the-switch-program-2/dynamic-programming-l2/count-valleys-mountains-official/ojquestion
    public int ValleysAndMountain(int n) {
        if (n == 1 || n == 0)
            return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int i1 = 0;
            int i2 = i - 1;
            int sum = 0;
            while (i1 < i && i2 >= 0) {
                sum += dp[i1] * dp[i2];
                i1++;
                i2--;
            }
            dp[i] = sum;
        }
        return dp[n];
    }

    // Count Brackets
    public static int CountBrackets(int n) {
        if (n == 1 || n == 0)
            return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int i1 = 0;
            int i2 = i - 1;
            int sum = 0;
            while (i1 < i && i2 >= 0) {
                sum += dp[i1] * dp[i2];
                i1++;
                i2--;
            }
            dp[i] = sum;
        }
        return dp[n];
    }

    // circle and chords
    public static long NumberOfChords(int n) {
        if (n == 1 || n == 0)
            return 1;
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int i1 = 0;
            int i2 = i - 1;
            long sum = 0;
            while (i1 < i && i2 >= 0) {
                sum += dp[i1] * dp[i2];
                i1++;
                i2--;
            }
            dp[i] = sum;
        }
        return dp[n];
    }

    public static void main(String[] args) {

    }
}