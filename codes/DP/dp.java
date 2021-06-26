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
            minCost = Math.min(minCost, min_cost_maze_traversal_rec(x, y+1, maze));
        }
        
        // down call
        if(x+1<maze.length){
            minCost = Math.min(minCost, min_cost_maze_traversal_rec(x+1, y, maze));
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
        if(x-1>=0 && y+1<arr[x].length){
            maxGold = Math.max(maxGold, gold_mine_rec(arr, x-1, y+1));
        }
        if(y+1<arr[x].length){
            maxGold = Math.max(maxGold, gold_mine_rec(arr, x, y+1));
        }

        if(x+1<arr.length && y+1<arr[x].length){
            maxGold = Math.max(maxGold, gold_mine_rec(arr, x+1, y+1));
        }
        return maxGold+arr[x][y];
    }
    public static int gold_mine_mem(int[][] arr,int x,int y,int[][] dp){
        if(y == arr[x].length-1){
            return dp[x][y] = arr[x][y];
        }

        if(dp[x][y] != 0){
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
        int[][] dp = new int[arr.length][arr[0].length];
        for(int r=0;r<arr.length;r++){
            // max_gold = Math.max(max_gold,gold_mine_rec(arr, r, 0));
            max_gold = Math.max(max_gold,gold_mine_mem(arr, r, 0,dp));
        }
        return max_gold;

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
        int[][] arr = {
            {0,1,4},
            {1,3,2},
            {4,2,3}
        };
        int res = gold_mine(arr);
        System.out.println(res);
    }


    public static void main(String[] args) {
        ques();
    }
}
