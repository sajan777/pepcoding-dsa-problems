import java.util.*;

public class dp {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~Fibonacci Sequence~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static int fibRecursion(int n){
        if(n == 0 || n==1 ) return 1;
        int fibnm1 = fibRecursion(n-1);
        int fibnm2 = fibRecursion(n-2);
        int fibn = fibnm1+fibnm2;
        return fibn;
    }
    public static int fib_memo(int n,int[] dp){
        if(n == 0 || n==1 ){
            // always fill in dp in the base case;
            // Store in DP and return
            return dp[n] = n; 
        };
        // 1. if problem is already solved , then return the answer
        if(dp[n] != 0){
            return dp[n];
        }
        int fibnm1 = fib_memo(n-1,dp);
        int fibnm2 = fib_memo(n-2,dp);
        int fibn = fibnm1+fibnm2;
        // if not solved solve the problem and store it in DP
        // Store in DP and return
        return dp[n] = fibn; 
    }
    // Tabulation
    public static int fib_tabulation(int n,int[] dp){
        // 1. figure out repetition -> repetition on n
        // smallest problem is n==0;

        // assign meaning to the cell
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2;i<dp.length;i++){
            // direction from smallest to largest
            dp[i] = dp[i-1]+dp[i-2];
        }
        
        return dp[n];
    }
    
    // Convert mem->tab
    public static int fib_tab2(int N, int[] dp) {
        for(int n = 0; n <= N; n++) {
            if(n == 0 || n == 1) {
                dp[n] = n;
                continue;
            }
    
            int fibnm1 = dp[n - 1];// fib_memo(n - 1, dp);
            int fibnm2 = dp[n - 2]; // fib_memo(n - 2, dp);
            
            int fibn = fibnm1 + fibnm2;
            // 2. If not solved, then solve the problem and store it in the dp
            dp[n] = fibn;
        }
        return dp[N];
    }

    public static void fib() {
        int n = 5;
        // int res = fibRecursion(n);

        // make a storage of size n+1
        int[] dp = new int[n+1];
        // int res = fib_memo(n, dp);
        // System.out.println(res);

        int res = fib_tabulation(n, dp);
        System.out.println(res);
    }


    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Climb Stairs~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static int climb_stairs_rec(int n){
        if(n == 0){
            return 1;
        }

        int stairs = 0;
        for(int i=1;i<=3;i++){
            if(n-i >= 0){
                stairs += climb_stairs_rec(n-i);
            }
        }
        return stairs;
    }
    
    public static int climb_stairs_dp(int n,int[] dp){
        if(n == 0){
            return dp[n] = 1;
        }
        if(dp[n] != 0){
            return dp[n];
        }
        int stairs = 0;
        for(int i=1;i<=3;i++){
            if(n-i >= 0){
                stairs += climb_stairs_dp(n-i,dp);
            }
        }
        return dp[n] = stairs;
    }
    
    public static int climb_stairs_tab(int n,int[] dp){
        dp[0] = 1;
        for(int i=1;i<dp.length;i++){
            int count = 0;
            for(int j=1;j<=3;j++){
                if(i-j>=0){
                    count += dp[i-j];
                }
            }
            dp[i] =count;
        }
        return dp[n];
    }
    // BETTER APPROACH use this for tabulation
    public static int climb_stairs_tab1(int n,int[] dp){
        dp[0] = 1;
        for(int i=1;i<dp.length;i++){
           if(i >= 3){
               dp[i] = dp[i-1]+dp[i-2]+dp[i-3];
           }else if(i >= 2){
               dp[i] = dp[i-1]+dp[i-2];
           }else{
               dp[i] = dp[i-1];
           }
        }
        return dp[n];
    }

    // convert memoization code to tabulation
    public static int climb_stairs_tab2(int N,int[] dp){
        for(int n=0;n<N;n++){
            if(n == 0){
                dp[0] = 1;
                continue;
            }
            int stairs = 0;
            for(int i=1;i<=3;i++){
                if(n-i >= 0){
                    stairs += dp[n-i];
                }
            }
            dp[n] = stairs;
        }
        return dp[N];
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Climb stairs with variable jumps i==current stare ,n==total stares,jumps[] jump that can be taken allowed at ith stare~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static int climb_stairs_var_jumps_rec(int i,int n,int[] jumps){
        if(i == n){
            return 1;
        }
        int count = 0;
        for(int jump=1;jump<=jumps[i] && jump+i <= n;jump++){
            count += climb_stairs_var_jumps_rec(i+jump, n, jumps);
        }
        return count;
    }

    public static int climb_stairs_var_jumps_mem(int i,int n,int[] jumps,int[] dp){
        if(i == n){
            return dp[i] = 1;
        }
        if(dp[i] != 0){
            return dp[i];
        }
        int count = 0;
        for(int jump=1;jump<=jumps[i] && jump+i <= n;jump++){
            count += climb_stairs_var_jumps_mem(i+jump, n, jumps,dp);
        }
        return dp[i] = count;
    }

    public static int climb_stairs_var_jumps_tab(int n,int[] jumps,int[] dp){
        // int[] dp = new int[n+1];
        for(int i=n;i>=0;i--){
            if(i == n){
                dp[i] = 1;
                continue;
            }
            int count = 0;
            for(int jump=1;jump<=jumps[i] && jump+i <= n;jump++){
                count += dp[i+jump]; //climb_stairs_var_jumps_mem(i+jump, n, jumps,dp);
            }
            dp[i] = count;
       }
       return dp[0];
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~climb stairs min move~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static int climb_stairs_min_move_rec(int i,int n,int[] jumps){
        if(i == n){
            return 0;
        }
        
        int minJumps = (int)1e9; //suppose there is no path so we don't add 1 in infinity to make it -ve infinity as the numbers are in circular and -veinfinity will become the smallest jump;
        // -veinf ... -3 -2 -1 0 1 2 3 ... +veinf (+veinf+1 = -veinf)
        for(int jump = 1;jump<=jumps[i] && jump+i<=n;jump++){
            minJumps = Math.min(minJumps, climb_stairs_min_move_rec(i+jump, n, jumps));
        }
        // return minJumps+1;
        return minJumps == 1e9 ? minJumps : minJumps+1; //for portal
    }
    
    public static int climb_stairs_min_move_mem(int i,int n,int[] jumps,int[] dp){
        if(i == n){
            return dp[i] = 0;
        }
        if(dp[i] != 0){
            return dp[i];
        }
        int minJumps = (int)1e9;
        for(int jump = 1;jump<=jumps[i] && jump+i<=n;jump++){
            minJumps = Math.min(minJumps, climb_stairs_min_move_mem(i+jump, n, jumps,dp));
        }
        // return minJumps == 1e9 ? minJumps : minJumps+1;
        return minJumps == 1e9 ? (dp[i] = minJumps) : (dp[i] = minJumps + 1); //for portal
    }

    public static int climb_stairs_min_move_tab(int i,int n,int[] jumps,int[] dp){
        for(i=n;i>=0;i--){
            if(i==n){
                dp[i] = 0;
                continue;
            }
            int minJumps = (int)1e9;
            for(int jump = 1;jump<=jumps[i] && jump+i<=n;jump++){
                minJumps = Math.min(minJumps, dp[i+jump]);
            }
            if(minJumps == 1e9){
                dp[i] = (int)1e9;  //for portal
            }else{
                dp[i] = minJumps+1;
            }
        }
        return dp[0];
    }
    // int[] dp = new int[n+1];
    // int res = climb_stairs_min_move_rec(0,n,arr);
    // if(res == 1e9){
    //     System.out.println("null");
    // }else{
    //     System.out.println(res);
    // }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~min cost in the maze traversal 0(n2)~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static int min_cost_maze_traversal_rec(int x,int y,int[][] maze){    
        if(x == maze.length-1 && y==maze[0].length-1){
            return maze[x][y];
        }

        int minCost = (int)1e9;
        // right call
        if(y+1<maze[0].length){
            minCost = Math.min(minCost, min_cost_maze_traversal_rec(x, y+1, maze));
        }
        
        // down call
        if(x+1<maze.length){
            minCost = Math.min(minCost, min_cost_maze_traversal_rec(x+1, y, maze));
        }

        return minCost+maze[x][y];
    }
    
    public static int min_cost_maze_traversal_mem(int x,int y,int[][] maze,int[][] dp){    
        if(x == maze.length-1 && y==maze[0].length-1){
            return dp[x][y] = maze[x][y];
        }
        if(dp[x][y] != 0){
            return dp[x][y];
        }

        int minCost = (int)1e9;
        // right call
        if(y+1<maze[0].length){
            minCost = Math.min(minCost, min_cost_maze_traversal_mem(x, y+1, maze,dp));
        }
        
        // down call
        if(x+1<maze.length){
            minCost = Math.min(minCost, min_cost_maze_traversal_mem(x+1, y, maze,dp));
        }

        return dp[x][y] = minCost+maze[x][y];
    }
    
    // conversion
    public static int min_cost_maze_traversal_tab1(int[][] maze,int[][] dp){    
        for(int x=maze.length-1;x>=0;x--){
            for(int y=maze[0].length-1;y>=0;y--){
                if(x == maze.length-1 && y==maze[0].length-1){
                    dp[x][y] = maze[x][y];
                    continue;
                }
        
                int minCost = (int)1e9;
                // right call
                if(y+1<maze[0].length){
                    minCost = Math.min(minCost,dp[x][y+1]);
                }
                
                // down call
                if(x+1<maze.length){
                    minCost = Math.min(minCost,dp[x+1][y]);
                }
        
                dp[x][y] = minCost+maze[x][y];
            }
        }
        return dp[0][0];
    }

    // print all min paths in maze traversal using DP
    public static void printMazeTraversalPath(int x,int y,int[][] dp,String psf){
        if(x == dp.length-1 && y== dp[0].length-1){
            System.out.println(psf);
        }else if(x == dp.length-1){
            printMazeTraversalPath(x, y+1, dp, psf+"R");
        }else if(y == dp[0].length-1){
            printMazeTraversalPath(x+1, y, dp, psf+"D");
        }else{
            if(dp[x][y+1]  == dp[x+1][y]){
                // both cALLS to explore all min paths
                printMazeTraversalPath(x, y+1, dp, psf+"R");
                printMazeTraversalPath(x+1, y, dp, psf+"D");
            }else if(dp[x][y+1] < dp[x+1][y]){
                // right
                printMazeTraversalPath(x, y+1, dp, psf+"R");
            }else{
                // down
                printMazeTraversalPath(x+1, y, dp, psf+"D");
            }
        }
    }

    public static int min_cost_maze_traversal_tab2(int[][] maze,int[][] dp){    
        for(int x=maze.length-1;x>=0;x--){
            for(int y=maze[0].length-1;y>=0;y--){
                if(x == maze.length-1 && y==maze[0].length-1){
                    // bottom right corner
                    dp[x][y] = maze[x][y];
                }else if(x == maze.length-1){
                    // last row
                    dp[x][y] = dp[x][y+1]+maze[x][y];
                }else if(y == maze[0].length-1){
                    // last col
                    dp[x][y] = dp[x+1][y]+maze[x][y];
                }else{  
                    // in the middle part
                    dp[x][y] = maze[x][y] + Math.min(dp[x][y+1], dp[x+1][y]);
                }
            }
        }
        return dp[0][0];
    }

    //     Scanner scn = new Scanner(System.in);
    //     int x = scn.nextInt();
    //     int y = scn.nextInt();
    //     int[][] maze = new int[x][y];
    //     for(int i=0;i<x;i++){
    //         for(int j=0;j<y;j++){
    //             maze[i][j] = scn.nextInt();
    //         }
    //     }
    //     int[][] dp = new int[x][y];
    //  //   System.out.println(min_cost_maze_traversal_rec(0,0,maze));
    //  //   System.out.println(min_cost_maze_traversal_mem(0,0,maze,dp));
    //     System.out.println(min_cost_maze_traversal_tab2(maze,dp));
    

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~GOLDMINE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    public static int gold_mine_rec(int[][] arr,int x,int y){
        if(y == arr[x].length-1){
            return arr[x][y];
        }
        
        int maxGold = -1;
        // top-right
        if(x-1>=0 && y+1<arr[x].length){
            maxGold = Math.max(maxGold, gold_mine_rec(arr, x-1, y+1));
        }
        // right
        if(y+1<arr[x].length){
            maxGold = Math.max(maxGold, gold_mine_rec(arr, x, y+1));
        }
        // bottom-right
        if(x+1<arr.length && y+1<arr[x].length){
            maxGold = Math.max(maxGold, gold_mine_rec(arr, x+1, y+1));
        }
        return maxGold+arr[x][y];
    }
    
    public static int gold_mine_mem(int[][] arr,int x,int y,Integer[][] dp){
        if(y == arr[x].length-1){
            return dp[x][y] = arr[x][y];
        }

        if(dp[x][y] != null){
            return dp[x][y];
        }
        
        int maxGold = -1;
        if(x-1>=0 && y+1<arr[x].length){
            maxGold = Math.max(maxGold, gold_mine_mem(arr, x-1, y+1,dp));
        }
        if(y+1<arr[x].length){
            maxGold = Math.max(maxGold, gold_mine_mem(arr, x, y+1,dp));
        }

        if(x+1<arr.length && y+1<arr[x].length){
            maxGold = Math.max(maxGold, gold_mine_mem(arr, x+1, y+1,dp));
        }
        return dp[x][y] = maxGold+arr[x][y];
    }

    public static int gold_mine(int[][] arr){

        int max_gold = Integer.MIN_VALUE;
        Integer[][] dp = new Integer[arr.length][arr[0].length];
        for(int r=0;r<arr.length;r++){
            // max_gold = Math.max(max_gold,gold_mine_rec(arr, r, 0));
            max_gold = Math.max(max_gold,gold_mine_mem(arr, r, 0,dp));
        }
        return max_gold;

    }

    public static int gold_mine_tab1(int[][] arr,int[][] dp){
        int res = 0;
        for(int y=arr[0].length-1;y>=0;y--){
            for(int x=0;x<arr.length;x++){
                if(y == arr[0].length - 1){
                    // last col
                    dp[x][y] = arr[x][y];
                }else if(x == 0){
                    // first row
                    dp[x][y] = Math.max(dp[x][y+1],dp[x+1][y+1])+arr[x][y];
                }else if(x == arr.length -1){
                    // last row
                    dp[x][y] = Math.max(dp[x][y+1],dp[x-1][y+1])+arr[x][y];
                }else{
                    dp[x][y] = Math.max(dp[x-1][y+1], Math.max(dp[x][y+1], dp[x+1][y+1]))+arr[x][y];
                }
                res = Math.max(res, dp[x][y]);
            }
        }
        return res;
    }
     
    public static int gold_mine_tab2(int[][] arr,int[][] dp){
        int res = 0;
        for(int y=arr[0].length -1;y>=0;y--){
            for(int x=0;x<arr.length;x++){
                if(y == arr[x].length-1){
                    dp[x][y] = arr[x][y];
                    res = Math.max(dp[x][y],res); //if the size is only 2
                    continue;
                }
                int maxGold = -1;
                if(x-1>=0 && y+1<arr[x].length){
                    maxGold = Math.max(maxGold, dp[x-1][y+1]);
                }
                if(y+1<arr[x].length){
                    maxGold = Math.max(maxGold, dp[x][y+1]);
                }
        
                if(x+1<arr.length && y+1<arr[x].length){
                    maxGold = Math.max(maxGold, dp[x+1][y+1]);
                }
                dp[x][y] = maxGold+arr[x][y];
                res = Math.max(dp[x][y],res);
            }
        }
        return res;
    }


    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~TARGET SUM SUBSET~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    // wrong approach by me isme pre area m work ho rha hai, in this hame repetition nhi dikhega
    // public static boolean targetSumSubsets_rec_bad_approach(int[] arr,int target,int idx,int ssf){

    //     if(idx == arr.length){
    //         if(target == ssf){
    //             return true;
    //         }
    //     }
    //     boolean rres = false;
    //     // yes call
    //     if(ssf+arr[idx] <= target){
    //         rres = targetSumSubsets_rec_bad_approach(arr, target, idx+1, ssf+arr[idx]);
    //         if(rres == true) return true; 
    //     }
    //     // no call
    //     rres = targetSumSubsets_rec_bad_approach(arr, target, idx+1, ssf);
    //     return rres;
    // }   

    // VVVVVIMP
    public static boolean targetSumSubset_rec(int[] arr,int idx,int target){
        if(target == 0){
            // System.out.println(idx+""+target);
            return true;
        }
        if(idx == arr.length){
            return false;
        }
        boolean rres = false;
        if(target-arr[idx] >= 0){
            // yes call
            System.out.println("yes Calling "+(idx+1)+"->"+(target-arr[idx]));
            rres = targetSumSubset_rec(arr, idx+1, target-arr[idx]);
        }
        // no call
        System.out.println("no Calling "+(idx+1)+"->"+target);
        rres = rres || targetSumSubset_rec(arr, idx+1, target);
        return rres;
    }
    public static boolean targetSumSubset_mem(int[] arr,int idx,int target,Boolean[][] dp){
        if(target == 0){
            return dp[idx][target] = true;
        }
        if(idx == arr.length){
            return dp[idx][target] = false;
        }
        if(dp[idx][target] != null) {
            return dp[idx][target];
        }
        boolean rres = false;
        if(target-arr[idx] >= 0){
            // yes call
            rres = targetSumSubset_mem(arr, idx+1, target-arr[idx],dp);
            if(rres == true) return dp[idx][target] = true;
        }
        // no call
        rres = targetSumSubset_mem(arr, idx+1, target,dp);
        return dp[idx][target] = rres;
    }

    public static boolean targetSumSubset_tab1(int[] arr, int target) {
        boolean[][] dp = new boolean[arr.length + 1][target + 1];
        
        for(int indx = 0; indx < dp.length; indx++) {
            for(int targ = 0; targ < dp[0].length; targ++) {
                if(targ == 0) {
                    dp[indx][targ] = true;
                } else if(indx == 0) {
                    dp[indx][targ] = false;
                } else {
                    int val = arr[indx - 1];
                    if(targ < val) {
                        // only no call
                        dp[indx][targ] = dp[indx - 1][targ];
                    } else {
                        // no call OR(||) yes call
                        dp[indx][targ] = dp[indx - 1][targ] || dp[indx - 1][targ - val];
                    }
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    public static boolean targetSumSubset_tab2(int[] arr, int target) {
        boolean[][] dp = new boolean[arr.length + 1][target + 1];
        for(int indx = arr.length; indx >= 0; indx--) {
            for(int targ = 0; targ <= target; targ++) {
                if(targ == 0) {
                    dp[indx][targ] = true; 
                    continue;
                }
                    
                if(indx == arr.length) {
                    dp[indx][targ] = false;
                    continue;
                }

                boolean res = false;
                // yes call
                if(targ - arr[indx] >= 0) {
                    res = dp[indx + 1][targ - arr[indx]];
                }
                // no call
                res = res || dp[indx + 1][targ];
                dp[indx][targ] = res;
            }
        }
        return dp[0][target];
    }

    // Scanner scn = new Scanner(System.in);
    // int n = scn.nextInt();
    // int[] arr = new int[n];
    // for(int i=0;i<n;i++){
    //      arr[i] = scn.nextInt();
    // }
    // int target = scn.nextInt();
    // Boolean[][] dp = new Boolean[n+1][target+1];
    // System.out.println(targetSumSubset_rec(arr,0,target));
    // //   System.out.println(targetSumSubset_mem(arr,0,target,dp));
        
    // //   System.out.println(targetSumSubset_tab2(arr,target));

    // ~~~~~~~~~~~~~~~~~~~~~~~~Coin Change Permutation~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public static int coin_change_permutation_rec(int[] coins,int target,String psf){
        if(target == 0){
            System.out.println(psf);
            return 1; //0 has one move and that is don't move
        } 

        int permutations = 0;
        for(int coin:coins){
            if(target-coin >= 0){
                permutations += coin_change_permutation_rec(coins, target-coin,psf+coin+" ");
            }
        }
        return permutations;   

    }
    
    public static int coin_change_permutation_mem(int[] coins,int target,int[] dp){
        if(target == 0){
            return dp[target] = 1; //0 has one move and that is don't move
        } 
        if(dp[target] != 0){
            return dp[target];
        }
        int permutations = 0;
        for(int coin:coins){
            if(target-coin >= 0){
                permutations += coin_change_permutation_mem(coins, target-coin,dp);
            }
        }
        return dp[target] = permutations;   
    }

    // my app
    public static int coin_change_permutation_tab1(int[] coins,int target,int[] dp){
        for(int targ=0;targ<=target;targ++){
            if(targ == 0){
                dp[targ] = 1; 
                continue;
            } 
            int permutations = 0;
            for(int coin:coins){
                if(targ-coin >= 0){
                    permutations += dp[targ-coin];
                }
            }
            dp[targ] = permutations;   

        }
        return dp[target];
    }

    // shreesh
    public static int coin_change_permutation_tab2(int[] coins,int target,int[] dp){
        dp[0] = 1;
        for(int i=1;i<=target;i++){
            for(int coin:coins){
                if(i-coin >= 0){
                    dp[i] += dp[i-coin];
                }
            }
        }
        return dp[target];
    }
    // Scanner scn = new Scanner(System.in);
    // int n = scn.nextInt();
    // int[] coins = new int[n];
    // for(int i=0;i<n;i++){
    //     coins[i] = scn.nextInt();
    // }
    // int target = scn.nextInt();
    // int[] dp = new int[target+1];
    // // System.out.println(coin_change_permutation_rec(coins,target));
    // // System.out.println(coin_change_permutation_mem(coins,target,dp));
    // // System.out.println(coin_change_permutation_tab1(coins,target,dp));
    // System.out.println(coin_change_permutation_tab2(coins,target,dp));

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Coin Change Combination Rec>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        
    // yes and no call
    public static HashMap<String, Integer> map = null;
    public static int coinChange_comb_rec_SubseqStyle(int[] coins, int indx, int target,
                 String psf, Integer[][] dp) {
        // System.out.println(indx + " " + target);
        if(target == 0) {
            // System.out.println(psf);
            return dp[indx][target] = 1;
        }

        if(indx == coins.length) {
            return dp[indx][target] = 0;
        }

        if(dp[indx][target] != null) return dp[indx][target];

        String str = "" + indx + " " + target;
        if(map.containsKey(str) == true) {
            map.put(str, map.get(str) + 1);
        } else {
            map.put(str, 1);
        }

        int count = 0;
        if(target - coins[indx] >= 0) {
            count += coinChange_comb_rec_SubseqStyle(coins, indx, target - coins[indx], psf + coins[indx] + " ", dp);
        }
        count += coinChange_comb_rec_SubseqStyle(coins, indx + 1, target, psf, dp);
        return dp[indx][target] = count;
    }
    public static int mem(int[] nums,int target,int idx,Integer[] dp){
        if(target == 0){
            return dp[target] = 1;
        }
        if(idx == nums.length){
            return dp[target] = 0;
        }
        if(dp[target] != null){
            return dp[target];
        }
        int res = 0;
        // yes call
        if(target - nums[idx] >= 0){
            res += mem(nums,target-nums[idx],idx,dp); 
        }
        //no call
        res += mem(nums,target,idx+1,dp);
        return dp[target] = res;
    }

    public static int coinChange_comb_tab(int[] coins, int target) {
        int[] dp = new int[target + 1];

        dp[0] = 1;
        // what we are doing here is taking a single coin and exhausting it by asking every target 
        for(int coin : coins) {
            for(int i = coin; i <= target; i++) {
                if(i - coin >= 0)
                    dp[i] += dp[i - coin];
            }
        }
        return dp[target];
    }  

    public static int coinChange_tab2(int[] coins, int targ, Integer[][] dp) {
        for(int indx = coins.length; indx >= 0; indx--) {
            for(int target = 0; target <= targ; target++) {
                if(target == 0) {
                    dp[indx][target] = 1;
                    continue;
                }
        
                if(indx == coins.length) {
                    dp[indx][target] = 0;
                    continue;
                }
        
                int count = 0;
                if(target - coins[indx] >= 0) {
                    count += dp[indx][target - coins[indx]];
                }
                count += dp[indx + 1][target];
                dp[indx][target] = count;
            }
        }
        return dp[0][targ];
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~01Knapsack~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static int zero_one_knapsack_rec(int[] wts,int[] values,int indx,int cap){
        if(indx == -1){
            return 0;    
        }
        int v1=0;
        if(cap - wts[indx] >= 0){
            // yes call
            v1 = zero_one_knapsack_rec(wts, values, indx-1, cap-wts[indx]) + values[indx];
        }
        // no call
        int v2 = zero_one_knapsack_rec(wts, values, indx-1, cap);
        return Math.max(v1, v2);
    }
    public static int zero_one_knapsack_mem(int[] wts,int[] values,int indx,int cap,int[][] dp){
        if(indx == -1){
            return dp[indx+1][cap] = 0;
        }
        // as we are deacrising the value of indx so we will check for indx+1 as dp[-1] will not exist
        if(dp[indx+1][cap] != 0){
            return dp[indx+1][cap];
        }
        int v1=0;
        if(cap - wts[indx] >= 0){
            // yes call
            v1 = zero_one_knapsack_mem(wts, values, indx-1, cap-wts[indx],dp) + values[indx];
        }
        // no call
        int v2 = zero_one_knapsack_mem(wts, values, indx-1, cap,dp);
        return dp[indx+1][cap] = Math.max(v1, v2);
    }
    public static int zero_one_knapsack_tab(int[] wts,int[] values,int indx,int Cap,int[][] dp){
        for(indx=1;indx<=values.length;indx++){
            for(int cap=1;cap<=Cap;cap++){
                if(cap < wts[indx-1]){
                    dp[indx][cap] = dp[indx-1][cap];
                }else{
                    int v1 = dp[indx-1][cap-wts[indx-1]] + values[indx-1]; //yes call
                    int v2 = dp[indx-1][cap]; //no call
                    dp[indx][cap] = Math.max(v1,v2);
                }
            }
        }
        return dp[wts.length][Cap];
    }        
    //  Scanner scn = new Scanner(System.in);
    // int n = scn.nextInt();
    // int[] values = new int[n];
    // for(int i=0;i<n;i++){
    //     values[i] = scn.nextInt();
    // }
    // int[] wts = new int[n];
    // for(int i=0;i<n;i++){
    //     wts[i] = scn.nextInt();
    // }
    // int cap = scn.nextInt();
    // int[][] dp = new int[n+1][cap+1];
    // // System.out.println(zero_one_knapsack_rec(wts,values,n-1,cap));
    // // System.out.println(zero_one_knapsack_mem(wts,values,n-1,cap,dp));
    // System.out.println(zero_one_knapsack_tab(wts,values,n-1,cap,dp));

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~UnboundedKnapSack~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static int unboundedKnapsack_rec(int[] wts,int[] vals,int indx,int cap){
        if(cap == 0 || indx == -1){
            return 0;
        }

        int v1 = 0;
        // yes call
        if(cap-wts[indx] >= 0){
            v1 = unboundedKnapsack_rec(wts, vals, indx, cap-wts[indx]) + vals[indx];
        }
        // no call
        int v2 = unboundedKnapsack_rec(wts, vals, indx-1,cap);
        return Math.max(v1,v2);
    }

    public static int unboundedKnapsack_mem(int[] wts,int[] vals,int indx,int cap,int[][] dp){
        if(cap == 0 || indx == -1){
            return dp[indx+1][cap] = 0;
        }
        if(dp[indx+1][cap] != 0){
            return dp[indx+1][cap];
        }

        int v1 = 0;
        // yes call
        if(cap-wts[indx] >= 0){
            v1 = unboundedKnapsack_mem(wts, vals, indx, cap-wts[indx],dp) + vals[indx];
        }
        // no call
        int v2 = unboundedKnapsack_mem(wts, vals, indx-1,cap,dp);
        return dp[indx+1][cap] = Math.max(v1,v2);
    }

    public static int unboundedKnapsack_tab(int[] wts,int[] vals,int cap){
        int[] dp = new int[cap+1];
        // outer loop for box/wt
        // inner loop for cap
        for(int i=0;i<wts.length;i++){
            for(int c=wts[i];c<=cap;c++){
                // no call
                int v1 = dp[c];
                // yes call
                int v2 = dp[c-wts[i]]+vals[i];
                dp[c] = Math.max(v1,v2);
            }
        }
        return dp[cap];
    }
    // Scanner scn = new Scanner(System.in);
    // int n = scn.nextInt();
    // int[] values = new int[n];
    // for(int i=0;i<n;i++){
    //     values[i] = scn.nextInt();
    // }
    // int[] wts = new int[n];
    // for(int i=0;i<n;i++){
    //     wts[i] = scn.nextInt();
    // }
    // int cap = scn.nextInt();
    // int[][] dp = new int[n+1][cap+1];
    // // System.out.println(unboundedKnapsack_rec(wts,values,n-1,cap));
    // // System.out.println(unboundedKnapsack_mem(wts,values,n-1,cap,dp));
    // System.out.println(unboundedKnapsack_tab(wts,values,cap));

    // ~~~~~~~~~~~~~~~~~~~~~~fractional Knapsack~~~~~~~~~~~~~~~~~~
    public static class FPair implements Comparable<FPair>{
        int val;
        int wt;
        double fraction;

        public FPair(int val,int wt){
            this.val = val;
            this.wt = wt;
            this.fraction = val*1.0/wt;
        }

        // we are doing this because of fraction is doubnle
        @Override
        public int compareTo(FPair other){
            if(this.fraction > other.fraction){
                return 1;
            }else if(this.fraction < other.fraction){
                return -1;
            }else{
                return 0;
            }
        }

    }
    public static void printFractionalKnapsack(int[] vals,int[] wts,int cap){
        // max PQ
        PriorityQueue<FPair> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<wts.length;i++){
            pq.add(new FPair(vals[i],wts[i]));
        }
        double profit = 0;
        while(pq.size() > 0 && cap > 0){
            FPair rem = pq.remove();
            if(rem.wt <= cap){
                profit += rem.val;
                cap -= rem.wt;
            }else{
                profit += rem.fraction * cap;
                cap = 0;
            }
        }
        System.out.println(profit);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Count Binary String~~~~~~~~~~~~~~~~~
    // start point as size = 0;
    public static int countBinaryString_rec(int n,int size,int lastPassedVal,String psf){
        if(size == n){
            return 1;
        }
        int count = 0;
        if(lastPassedVal == 1){
            count += countBinaryString_rec(n, size+1, 0, psf+"0");
            count += countBinaryString_rec(n, size+1, 1, psf+"1");
        }else{
            count += countBinaryString_rec(n, size+1, 1, psf+"1");
        }
        return count;
    }
    // in this recursion we can see repetition very eaisly Start point as size
    public static int countBinaryString_rec2(int n,int lastPassedVal,String psf){
        if(n == 0){
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        if(lastPassedVal == 1){
            count += countBinaryString_rec2(n-1, 0,psf+"0");
            count += countBinaryString_rec2(n-1, 1,psf+"1");
        }else{
            // if 0 then only 1 can be lastPassedVal as consequtive 0's are not allowed
            count += countBinaryString_rec2(n-1, 1,psf+"1");
        }
        return count;
    }
    public static int countBinaryString_mem(int n,int lastPassedVal,int[][] dp){
        if(n == 0){
            return dp[n][lastPassedVal]= 1;
        }
        if(dp[n][lastPassedVal] != 0){
            return dp[n][lastPassedVal];
        }
        int count = 0;
        if(lastPassedVal == 1){
            count += countBinaryString_mem(n-1, 0,dp);
            count += countBinaryString_mem(n-1, 1,dp);
        }else{
            count += countBinaryString_mem(n-1, 1,dp);
        }
        return dp[n][lastPassedVal] = count;
    }
    public static int countBinaryString_tab(int N){
        int[][] dp = new int[2][N];
        dp[0][0] = 1;
        dp[1][0] = 1;

        for(int n=1;n<N;n++){
            dp[0][n] = dp[1][n-1];
            dp[1][n] = dp[0][n-1]+dp[1][n-1];
        }
        return dp[0][N-1]+dp[1][N-1];
    }
    // most optimizied
    public static int countBinaryString_optimize(int n){
        int zero = 1;
        int one = 1;
        for(int i=2;i<=n;i++){
            int n_zero = one;
            int n_one = zero+one;
            zero = n_zero;
            one = n_one;
        }
        return zero+one;
    }
//     Scanner scn = new Scanner(System.in);
//     int n = scn.nextInt();
//  //   System.out.println(countBinaryString_rec(n,0,1,""));
//  //   System.out.println(countBinaryString_rec2(n,1));
//      int[][] dp = new int[n+1][2];
//  //   System.out.println(countBinaryString_mem(n,1,dp));
//  //   System.out.println(countBinaryString_tab(n));
//     System.out.println(countBinaryString_optimize(n));


    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Arrange Buildings~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public static long arrange_buildings_rec(int n,char lastPassed){
        if(n == 0){
            return 1;
        }
        int count = 0;
        if(lastPassed == 'S'){
            count += arrange_buildings_rec(n-1, 'S');
            count += arrange_buildings_rec(n-1, 'B');
        }else{
            count += arrange_buildings_rec(n-1, 'S');
        }
        return count;
    }
    public static long arrange_buildings_optimized(int n){
        long space = 1;
        long building = 1;
        for(int i=2;i<=n;i++){
            long n_space = building;
            long n_building = space+building;
            space = n_space;
            building = n_building;
        }
        long res = space+building;
        return res*res;
    }
    // Scanner scn = new Scanner(System.in);
    // int n = scn.nextInt();
    // long res = arrange_buildings_rec(n,'S');
    // System.out.println(res*res);
    // // System.out.println(arrange_buildings_optimized(n)); 


    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Count Encodings~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static int countEncodings_rec(String str,int indx){
        if(indx == str.length()){
            return 1;
        }
        if(str.charAt(indx) == '0'){
            return 0;
        }
        int count = 0;
        int n1 = str.charAt(indx)-'0';
        count += countEncodings_rec(str, indx+1);
        if(indx + 1 < str.length()){
            int n = str.charAt(indx+1)-'0';
            int n2 = n1*10+n;
            if(n2 <= 26){
                count += countEncodings_rec(str, indx+2);

            }
        }
        return count;
    }
    public static int countEncodings_mem(String str,int indx,int[] dp){
        if(indx == str.length()){
            return dp[indx] = 1;
        }
        if(str.charAt(indx) == '0'){
            return dp[indx] = 0;
        }
        if(dp[indx] != 0){
             return dp[indx];
        }
        int count = 0;
        int n1 = str.charAt(indx)-'0';
        count += countEncodings_mem(str, indx+1,dp);
        if(indx + 1 < str.length()){
            int n = str.charAt(indx+1)-'0';
            int n2 = n1*10+n;
            if(n2 <= 26){
                count += countEncodings_mem(str, indx+2,dp);

            }
        }
        return dp[indx] = count;
    }
    public static int countEncodings_tab(String str){
        int[] dp = new int[str.length()+1];
        for(int indx=str.length();indx>=0;indx--){
            if(indx == str.length()){
                dp[indx] = 1;
                continue;
            }
            if(str.charAt(indx) == '0') {
                dp[indx] = 0;
                continue;
            }
            int count = 0;
            int n1 = str.charAt(indx)-'0';
            count += dp[indx+1]; //countEncodings_mem(str, indx+1,dp);
            if(indx + 1 < str.length()){
                int n = str.charAt(indx+1)-'0';
                int n2 = n1*10+n;
                if(n2 <= 26){
                    count += dp[indx+2]; //countEncodings_mem(str, indx+2,dp);
    
                }
            }
            dp[indx] = count;
        }
        return dp[0];
    }



    public static void ques() {
        // fib();
        // int[][] maze = {
        //     {10,8,9},
        //     {3,7,15},
        //     {9,6,11}
        // };
        // int[][] dp = new int[maze.length][maze[0].length];
        
        // min_cost_maze_traversal_tab2(maze,dp);
        // for(int i=0;i<dp.length;i++){
        //     for(int j=0;j<dp[0].length;j++){
        //         System.out.print(dp[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        // printMazeTraversalPath(0,0,dp,"");
        // int[][] arr = {
        //     {0,1,4},
        //     {1,3,2},
        //     {4,2,3}
        // };
        // int res = gold_mine(arr);
        // System.out.println(res);

        map = new HashMap<>();
        int[] coins = {2, 3, 5, 6, 10};
        int target = 90;

        Integer[][] dp = new Integer[coins.length + 1][target + 1];
        // int[] dp = new  int[target + 1];
        // int res = coinChange_perm_memo(coins, target, dp);
        // System.out.println(res);

        coinChange_comb_rec_SubseqStyle(coins, 0, target, "", dp);
        
        for(String key : map.keySet()) {
            // if(map.get(key) > 1)
            System.out.println(key + " -> " + map.get(key));
        }
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ a+b+c seq~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static int abcSeq(String str){
        int a_count = 0;
        int b_count = 0;
        int c_count = 0;
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if(ch == 'a'){
                a_count = 2*a_count+1;
            }else if(ch == 'b'){
                b_count = a_count+b_count*2;
            }else{
                c_count = b_count+2*c_count;
            }
        }
        return c_count;
    }
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~MAX SUM NON ADJACENT~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static int maxSumNonAdj_rec(int[] arr,int indx,int status){
        if(indx == -1){
            return 0;
        }
        int maxSum = (int)-1e9;
        if(status == 0){
            maxSum = Math.max(maxSum,maxSumNonAdj_rec(arr, indx-1, 1))+arr[indx]; //yes call
            maxSum = Math.max(maxSum,maxSumNonAdj_rec(arr, indx-1, 0)); //no call
        }else{
            // only no call
            maxSum = Math.max(maxSum,maxSumNonAdj_rec(arr, indx-1, 0)); //no call
        }
        return maxSum;
    }
    public static int maxSumNonAdj_mem(int[] arr,int indx,int status,int[][] dp){
        if(indx == -1){
            return dp[status][indx+1] = 0;
        }
        if(dp[status][indx+1] != 0){
            return dp[status][indx+1];
        }
        int maxSum = (int)-1e9;
        if(status == 0){
            maxSum = Math.max(maxSum,maxSumNonAdj_mem(arr, indx-1, 1,dp))+arr[indx]; //yes call
            maxSum = Math.max(maxSum,maxSumNonAdj_mem(arr, indx-1, 0,dp)); //no call
        }else{
            // only no call
            maxSum = Math.max(maxSum,maxSumNonAdj_mem(arr, indx-1, 0,dp)); //no call
        }
        return dp[status][indx+1] = maxSum;
    }
    // best optimizied Tabulation
    public static int maxSumNonAdj_greedy(int[] arr){
        int include = 0;
        int exclude = 0;
        for(int i=0;i<arr.length;i++){
            int n_include = exclude+arr[i];
            int n_exclude = Math.max(include,exclude);
            include = n_include;
            exclude = n_exclude;
        }
        return Math.max(include,exclude);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~PAINT HOUSE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // TODO figure this approach of mine
    // public static int paintHouse(int[][] arr){
    //     int minCost = 0;
    //     for(int i=0;i<arr.length;i++){
    //         int r_count = 0;
    //         int b_count = 0;
    //         int g_count = 0;
    //         for(int j=0;j<arr[0].length;j++){

    //         }
    //     }
    //     return minCost;
    // }

    public static int paintHouse(int[][] cost){
        int red = 0;        
        int blue = 0;        
        int green = 0;       
        
        for(int i=0;i<cost.length;i++){
            // red  -> index = 0 
            int n_red = Math.min(green,blue)+cost[i][0];
            // green ->index = 1
            int n_green = Math.min(blue,red)+cost[i][1];
            // blue -> index = 2
            int n_blue = Math.min(red,green)+cost[i][2];
            
            red = n_red;
            blue = n_blue;
            green = n_green;
        }
        return Math.min(red,Math.min(blue,green));
    }
    // Scanner scn = new Scanner(System.in);
    // int n = scn.nextInt();
    // int k = 3; // no of colors
    // int[][] cost = new int[n][k];
    // for(int i=0;i<n;i++){
    //     for(int j=0;j<k;j++){
    //         cost[i][j] = scn.nextInt();
    //     }
    // }
    // int res = paintHouse(cost);
    // System.out.println(res);

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~PAINT HOUSE many colors~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // 0(n2)
    public static int paintHouseManyColors(int[][] cost){
        int n = cost.length;
        int k = cost[0].length;
        int[][] dp = new int[n][k];

        int min1 = (int)1e9;
        int min2 = (int)1e9;

        for(int i=0;i<n;i++){
            int temp_min1 = (int)1e9; 
            int temp_min2 = (int)1e9;
            for(int j=0;j<k;j++){
                if(i==0){
                    dp[i][j] = cost[i][j];
                }else{
                    if(dp[i-1][j] == min1){
                        dp[i][j] = cost[i][j] + min2;
                    }else{
                        dp[i][j] = cost[i][j] + min1;
                    }
                }
                if(dp[i][j] <= temp_min1){
                    temp_min2 = temp_min1;
                    temp_min1 = dp[i][j];
                }else if(dp[i][j] <= temp_min2){
                    temp_min2 = dp[i][j];
                }
            }
            min1 = temp_min1;
            min2 = temp_min2; 
        }
        return min1;
    }


    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~PAINT FENCE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static long paintFence(int n,int k){
        long same = 0;
        long distinct = k;
        for(int i=1;i<n;i++){
            long n_same = distinct;
            long n_distinct = (same+distinct)*(k-1);
            same = n_same;
            distinct = n_distinct;
        }
        return same+distinct;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~TILE QUESTIONS~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Tile with 2*1;
    // long should be used here but on portal int working
    public static long tileWith2X1(int n){
        long a = 1; //1size floor
        long b = 2; //2size floor
        for(int i=1;i<n;i++){
            long c = a + b;
            a = b;
            b = c;
        }
        return a;
        
    }
    // GFG
    static Long numberOfWays(int N) {
        // code here
        long a = 1; //1size floor
        long b = 2; //2size floor
        for(int i=1;i<N;i++){
            long c = (a + b)%1000000007;
            a = b;
            b = c;
        }
        return a;
    }
    // Tile with M*1
    public static long tilingMX1(int m,int n){
        long[] dp = new long[n+1];
        for(int i=0;i<=n;i++){
            if(i<m){
                dp[i] = 1;
            }else{
                dp[i] = dp[i-1]+dp[i-m];
            }
        }
        return dp[n];
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Friends Pairing~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static int friendsPairing(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2;i<=n;i++){
            // single + pairing 
            dp[i] = dp[i-1]+(i-1)*dp[i-2];
        }
        return dp[n];
    }
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Partition into K subset~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static long partitionKSubset(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= i && j <= k; j++) {
                if(j == 0) {
                    dp[i][j] = 0;
                } else if(i == j) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + j * dp[i - 1][j];
                }
            }
        }
        return dp[n][k];
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Buy and sell stocls~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static int buyAndSellOneTransaction(int[] price){
        int profit = 0;
        int minPrice = price[0];
        for(int day=1;day<price.length;day++){
            profit = Math.max(profit,price[day] - minPrice);
            minPrice = Math.min(minPrice,price[day]);
            // if(price[day] < minPrice){
            //     minPrice = price[day];
            // }else{
            //     profit = Math.max(profit,price[day] - minPrice);
            // }
        }

        return profit;
    }

    // Multiple  tractions allowed
    public static int buyAndSellMultipleTransaction(int[] price){
        int profit = 0;
        int bd = 0; //buying day
        int sd = 0; //selling day
        for(int i=1;i<price.length;i++){
            if(price[i-1] > price[i]){
                // beeta hua kal maybe local maxima ho sakta hai agar aaj kam hai 
                profit += price[sd] - price[bd];
                bd = i;
                sd = i;
            }else{
                sd = i;
            }
        }
        profit += price[sd] - price[bd];
        return profit;
    }

    // Infinite transaction with fees
    public static int buyAndSellMultipleTransactionWithFees(int[] price,int fees){
        int pwb = -price[0]; //profit with buy
        int pws = 0; //profit with sell
        for(int i=1;i<price.length;i++){
            int n_pwb = Math.max(pwb,pws-price[i]);
            int n_pws = Math.max(pws,pwb+price[i]-fees);
            pwb = n_pwb;
            pws = n_pws;
        }
        return pws;
    }

    // Infinite transaction with cooldown 1 day
    public static int buyAndSellMultipleCooldown(int[] price){
        int pwb = -price[0];
        int pws = 0;
        int pwc = 0;
        for(int i=1;i<price.length;i++){
            int n_pwb = Math.max(pwb,pwc-price[i]);
            int n_pws = Math.max(pws,price[i]+pwb);
            int n_pwc = Math.max(pwc,pws);
            pwb = n_pwb;
            pws = n_pws;
            pwc = n_pwc;
        }
        return pws;
    }


    public static void main(String[] args) {
        ques();
    }
}
