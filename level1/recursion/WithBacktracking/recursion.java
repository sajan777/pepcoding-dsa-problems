public import java.util.*;

public class recursion {

    public static int[] rdir = {-1, 0, 1, 0};
    public static int[] cdir = {0, -1, 0, 1};
    public static char[] chArr = {'t', 'l', 'd', 'r'};

    public static void floodfill(int[][] board, int sr, int sc, String asf) {
        if(sr == board.length - 1 && sc == board[0].length - 1) {
            System.out.println(asf);
            return;
        }

        // mark
        board[sr][sc] = 1;

        for(int d = 0; d < rdir.length; d++) {
            int rr = sr + rdir[d];
            int cc = sc + cdir[d];
            char dir = chArr[d];

            if(rr >= 0 && rr < board.length && cc >= 0 && cc < board[0].length && board[rr][cc] != 1) {
                floodfill(board, rr, cc, asf + dir);
            }
        }

        // unmarking
        board[sr][sc] = 0;
    }

    public static void printTargetSumSubset(int[] arr, int indx, int ssf, int targ, String asf) {
        if(indx == arr.length) {
            if(targ == ssf) {
                System.out.println(asf + ".");
            }
            return;
        }
        // yes call
        if(ssf + arr[indx] <= targ) {
            printTargetSumSubset(arr, indx + 1, ssf + arr[indx], targ, asf + arr[indx] + ", ");
        }
        // no call
        printTargetSumSubset(arr, indx + 1, ssf, targ, asf);
    }


    // bsf -> box so far
    public static void printWaysToSelectBox(int cb, int tb, int bsf, String asf) {
        if(cb == tb) {
            if(bsf == 3)
                System.out.println(asf);

            return;
        }
        // yes call
        if(bsf + 1 <= 3)
            printWaysToSelectBox(cb + 1, tb, bsf + 1, asf + "b" + cb + " ");
        // no call
        printWaysToSelectBox(cb + 1, tb, bsf, asf);
    }

    // dimension of box -> n(total row) * m (total col),
    // r-> row, c -> col, bsf -> box so far, asf-> answer so far
    public static void printWaysIn2D(int n, int m, int r, int c, int bsf, String asf) {
        if(r == n) {
            if(bsf == 2)
                System.out.println(asf);
            return;
        }

        if(c + 1 < m) { // next column is valid
            // yes call
            printWaysIn2D(n, m, r + 1, 0, bsf + 1, asf + "(" + r + "-" + c +"), ");
            // no call
            printWaysIn2D(n, m, r, c + 1, bsf, asf);
        } else  { // next column is invalid
            // yes call
            printWaysIn2D(n, m, r + 1, 0, bsf + 1, asf + "(" + r + "-" + c +"), ");
            // no call
            printWaysIn2D(n, m, r + 1, 0, bsf, asf);
        }
    }

    public static boolean isValidToPlace(int[][] board, int r, int c) {
        int[][] dir = {
            {-1, 0},
            {-1, 1},
            {-1, -1}
        };
        
        int radius = board.length;
        for(int rad = 1; rad < radius; rad++) {
            for(int d = 0; d < dir.length; d++) {
                int rr = r + (rad * dir[d][0]);
                int cc = c + (rad * dir[d][1]);

                // calls
                if(rr >= 0 && rr < radius && cc >= 0 && cc < radius) {
                    if(board[rr][cc] == 1) 
                        return false;   
                }   
            }
        }
        return true;
    }

    // qpsf -> queen placed so far
    // asf -> answer so far
    public static void nqueen(int[][] board, int sr, int sc, int qpsf, String asf) {
        if(sr == board.length) {
            if(qpsf == board.length)
                System.out.println(asf + ".");
            return;
        }

        if(sc + 1 < board[0].length) { // next column is valid
            // yes + isvalid 
            if(isValidToPlace(board, sr, sc) == true) {
                board[sr][sc] = 1;
                nqueen(board, sr + 1, 0, qpsf + 1, asf + sr + "-" + sc + ", ");
                board[sr][sc] = 0;
            }
            // no call
            nqueen(board, sr, sc + 1, qpsf, asf);
        } else { // next column is not valid
            // yes + isvalid
            if(isValidToPlace(board, sr, sc) == true) {
                board[sr][sc] = 1;
                nqueen(board, sr + 1, 0, qpsf + 1, asf + sr + "-" + sc + ", ");
                board[sr][sc] = 0;
            }
            // no call
            nqueen(board, sr + 1, 0, qpsf, asf);            
        } 
    }

    public static void nqueen2(int[][] board, int row, String asf) {
        if(row == board.length) {
            System.out.println(asf);
            return;
        }

        for(int col = 0; col < board.length; col++) {
            if(isValidToPlace(board, row, col) == true) {
                board[row][col] = 1;
                nqueen2(board, row + 1, asf + row + "-" + col + ", ");
                board[row][col] = 0;
            }
        }
    }



    // Knights DIR
    public static int[] xdir = {-2, -1, 1, 2, 2, 1, -1, -2};
    public static int[] ydir = {1, 2, 2, 1, -1, -2, -2, -1};

    public static int counting = 1;

    public static void display(int[][] board) {
        System.out.println("~~~~~~~" + counting + "~~~~~~");
        for(int[] arr : board) {
            for(int val : arr) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        System.out.println("~~~~~~~" + counting + "~~~~~~");
    }

    public static void printKnightsTour(int[][] board, int r, int c, int count) {
        if(count == board.length * board.length) {
            // board[r][c] = count;
            display(board);
            // counting++;
            // board[r][c] = 0;
            return;
        }
        // mark
        for(int d = 0; d < xdir.length; d++) {
            int rr = r + xdir[d];
            int cc = c + ydir[d];
            
            if(rr >= 0 && rr < board.length && cc >= 0 && cc < board.length && board[rr][cc] == 0) {
                board[rr][cc] = count + 1;
                printKnightsTour(board, rr, cc, count + 1);
                board[rr][cc] = 0; 
            }
        }
        // unmark
    }



    // SUDOKU
    public static boolean isSafeToPlace(char[][] board,int r,int c,char n){
        // row check
        for(int col=0;col<board[0].length;col++){
            if(board[r][col] == n) return false;
        }
        // col check
        for(int row=0;row<board.length;row++){
            if(board[row][c] == n) return false;
        }
       // sub matrix check
        int rr = r-r%3;
        int cc = c-c%3;
        for(int row=0;row<3;row++){
            for(int col=0;col<3;col++){
                if(board[row + rr][col + cc] == n) return false;
            }
        }
        return true;
    }
    public void solve(char[][] board,int r,int c,char[][] ans){
        if(r == board.length){
            for(int i=0;i<board.length;i++){
                for(int j=0;j<board.length;j++){
                    ans[i][j] = board[i][j];
                }
            }
            return;
        }
        if(c == board[0].length){
            solve(board,r+1,0,ans);
            return;
        }
        if(board[r][c] == '.'){
            for (char num = '1'; num <= '9'; num++) {
                if(isSafeToPlace(board, r, c, num) == true) {
                    board[r][c] = num;
                    solve(board, r,c+1,ans);
                    board[r][c] = '.';
                }
            }
        }else{
            solve(board,r,c+1,ans);
        }
    }
    public void solveSudoku(char[][] board) {
        char[][] ans = new char[board.length][board.length];
        solve(board,0,0,ans);
        for(int i=0;i<board.length;i++){
                for(int j=0;j<board.length;j++){
                    board[i][j] = ans[i][j];
                }
        }
    }

    public static void ques() {
        int[][] board = new int[3][3];
        // nqueen2(board, 0, "");
        printKnightsTour(board, 0, 0, 1);
        // nqueen(board, 0, 0, 0, "");
        // printWaysIn2D(2, 2, 0, 0, 0, "");
        // printWaysToSelectBox(0, 4, 0, "");
        // int[] arr = {2, 1, 3, 0};
        // int targ = 3;
        // printTargetSumSubset(arr, 0, 0, targ, "");
 
        // int[][] board = {
        //     {0, 1, 0, 0, 0, 0},
        //     {0, 1, 0, 1, 1, 0},
        //     {0, 1, 0, 1, 1, 0},
        //     {0, 0, 0, 0, 0, 0},
        //     {1, 1, 0, 1, 1, 0},
        //     {1, 1, 0, 0, 0, 0}
        // };

        // floodfill(board, 0, 0, "");
        return;
    }

    public static void demoForNormalRadius() {
        // dummy matrix for figure out the radius concept
        int n = 5;
        int m = 4;


        int r = 0;
        int c = 0;

        int[] rdir = {-1, 0, 1, 0};
        int[] cdir = {0, 1, 0, -1};

        int radius = Math.max(n,m);
        // for radius 1
        for(int rad = 1; rad <= radius; rad++) {
            for(int dir = 0; dir < rdir.length; dir++) {
                int rr = r + (rad * rdir[dir]);
                int cc = c + (rad * cdir[dir]);

                // calls
                if(rr >= 0 && rr < n && cc >= 0 && cc < m)
                    System.out.println("Radius : " + rad + ", dir : " + dir +" :->  row : " + rr + ", col : " + cc);
            }
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
        for(int dir = 0; dir < rdir.length; dir++) {
            for(int rad = 1; rad <= radius; rad++) {
                int rr = r + (rad * rdir[dir]);
                int cc = c + (rad * cdir[dir]);

                // calls
                if(rr >= 0 && rr < n && cc >= 0 && cc < m)
                    System.out.println("Radius : " + rad + ", dir : " + dir +" :->  row : " + rr + ", col : " + cc);
            }
        }
    }

    public static void demoForQueenTraversal() {
        int n = 10;
        int m = 10;

        int r = 0;
        int c = 0;

        int[][] dir = {
            {-1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1},
            {-1, -1}
        };

        int radius = Math.max(n,m);
        // for radius 1
        for(int rad = 1; rad <= radius; rad++) {
            for(int d = 0; d < dir.length; d++) {
                int rr = r + (rad * dir[d][0]);
                int cc = c + (rad * dir[d][1]);

                // calls
                if(rr >= 0 && rr < n && cc >= 0 && cc < m)
                    System.out.println("Radius : " + rad + ", dir : " + d +" :->  row : " + rr + ", col : " + cc);
            }
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
        for(int d = 0; d < dir.length; d++) {
            for(int rad = 1; rad <= radius; rad++) {
                int rr = r + (rad * dir[d][0]);
                int cc = c + (rad * dir[d][1]);

                // calls
                if(rr >= 0 && rr < n && cc >= 0 && cc < m)
                    System.out.println("Radius : " + rad + ", dir : " + d +" :->  row : " + rr + ", col : " + cc);
            }
        }
    }

    public static void demoForKnightTraversal() {
        int[] rdir = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] cdir = {1, 2, 2, 1, -1, -2, -2, -1};
        int r = 4;
        int c = 4;
        for(int d = 0; d < rdir.length; d++) {
            int rr = r + rdir[d];
            int cc = c + cdir[d];

            System.out.println("row : " + rr + ", col : " + cc);
        }
    }

    public static void demo() {
        // demoForNormalRadius();
        // demoForQueenTraversal();
        demoForKnightTraversal();
    }

    public static void main(String[] args) {
        ques();
        // demo();
    }

}class recursion {
    
}
