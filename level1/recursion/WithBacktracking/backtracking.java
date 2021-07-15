public class backtracking {

    public static int[] rdir = {-1,0,1,0};
    public static int[] cdir = {0,-1,0,1};
    public static String[] chArr = {"t","l","d","r"};

    public static void floodfill(int[][] maze, int sr, int sc, String ans) {
        if(sr == maze.length-1 && sc == maze[0].length -1){
            System.out.println(ans);
            return;
        }
        // mark
        maze[sr][sc] = 1;
            
        for(int d=0;d<rdir.length;d++){
            int rr = sr + rdir[d];
            int cc = sc + cdir[d];
            String dir = chArr[d];
            if(rr>=0 && rr<maze.length && cc>=0 && cc<maze[0].length && maze[rr][cc] == 0){    
                floodfill(maze,rr,cc,ans+dir);
            }
            
        }
        // unmark
        maze[sr][sc] = 0;
    }

    // no of boxes selected so far
    // cb current boxes
    // tb total boxes
    // asf answer so far
    public static void printWaysToSelectBox(int cb,int tb,int bsf,String asf) {
        
        if(cb == tb){
            // print only those possibilty where boxes selected == 2
            if(bsf == 2)
                System.out.println(asf);
            return;
        }

        // yes call
        // generate selection of boxes(less possiblityy less then= 2)
        if(bsf+1 <= 2)
            printWaysToSelectBox(cb+1,tb,bsf+1,asf+"b"+cb+" ");
        // no call
        printWaysToSelectBox(cb+1, tb,bsf, asf);
    }

    public static void printWaysToSelectBox2D(int n,int m,int r,int c,int bsf,String asf) {
        if(n == r){
            if(bsf == 2)
                System.out.println(asf);
            return;
        }

        // // yes call
        // if(c+ 1< m){
        //     printWaysToSelectBox2D(n, m, r, c+1, bsf+1, asf+"("+r+"-"+c+"), ");
        // }else{
        //     printWaysToSelectBox2D(n, m, r+1, 0, bsf+1, asf+"("+r+"-"+c+"), ");
        // }
        
        // // no call
        // if(c+ 1< m){
        //     printWaysToSelectBox2D(n, m, r, c+1, bsf, asf);
        // }else{
        //     printWaysToSelectBox2D(n, m, r+1, 0, bsf, asf);
        // }

                 
        // only selexct one box fr5om every row
        if(c+ 1< m){ //next col is valid
            // col walid hai haa but jaise hi yes kara ek box ko means select kara
            // to r+1 kardo and col 0 kardo
            // no call to aage jaane do hame farark nhi padta means c+1 hone do
            // yes call 
            printWaysToSelectBox2D(n, m, r+1, 0, bsf+1, asf+"("+r+"-"+c+"), ");
            // no call
            printWaysToSelectBox2D(n, m, r, c+1, bsf, asf);
        }else{ //next valid is invalid
            // yes call
            printWaysToSelectBox2D(n, m, r+1, 0, bsf+1, asf+"("+r+"-"+c+"), ");
            // no call
            printWaysToSelectBox2D(n, m, r+1, 0, bsf, asf);
        }
    }


    // public static int[][] dirs = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
    // optimized dir
    public static int[][] dirs = {{-1,0},{-1,1},{-1,-1}};
    public static boolean isValidToPlace(int[][] board,int sr,int sc){
        int radius = board.length;
        for(int rad=1;rad<=radius;rad++){
            for(int dir=0;dir<dirs.length;dir++){
                int rr = sr + rad*dirs[dir][0];
                int cc = sc + rad*dirs[dir][1];
                if(rr>=0 && rr<radius && cc>=0 && cc<radius){
                    if(board[rr][cc] == 1) return false;
                }
            }
        }
        return true;
    }

    // qpsf -> queen placed so far
    // asf -> answer so far
    // (subsequence method) 2^n
    public static void nqueen(int[][] board,int sr,int sc,int qpsf,String asf){

        if(sr == board.length){
            if(qpsf == board.length)
                System.out.println(asf);
            return;
        }
        // Next column is valid
        if(sc+1 < board[0].length){
            // yes call if pos is valid
            if(isValidToPlace(board,sr,sc) == true){
                // mark the queen 
                board[sr][sc] = 1;
                nqueen(board, sr+1, 0, qpsf+1, asf+sr+" - "+sc+", ");
                board[sr][sc] = 0;
            }
            // no call if pos is valid
            nqueen(board, sr, sc+1, qpsf, asf);
        }else{ // next column is invalid
            // yes call if pos is valid
            if(isValidToPlace(board,sr,sc) == true){
                // mark the queen
                board[sr][sc] = 1;
                nqueen(board, sr+1, 0, qpsf+1, asf+sr+" - "+sc+", ");
                board[sr][sc] = 0;
            }
            // no call if pos is valid
            nqueen(board, sr+1, 0, qpsf, asf);
        }
    }

    // options level method n*n^2
    public static void nqueen2(int[][] board,int row,String asf){
        if(row == board.length){
            System.out.println(asf);
            return;
        }

        for(int col=0;col<board.length;col++){
            if(isValidToPlace(board, row, col) ==  true){
                board[row][col] = 1;
                nqueen2(board, row+1, asf+row+"-"+col+", ");
                board[row][col] = 0;
            }
        }
    }

    // knights Tour
    public static int[][] Knightdirs = {{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};
    public static void printKnightsTour(int[][] chess, int r, int c, int upcomingMove) {
        
        if(chess.length*chess.length == upcomingMove){
            chess[r][c] = upcomingMove;
            displayBoard(chess);
            chess[r][c] = 0;
            return;
        }
        // mark whee i came
        chess[r][c] = upcomingMove;
        for(int d=0;d<Knightdirs.length;d++){
            int rr = r+Knightdirs[d][0];
            int cc = c+Knightdirs[d][1];
            if(rr>=0 && rr<chess.length && cc>=0 && cc<chess.length && chess[rr][cc] == 0){
                    printKnightsTour(chess,rr,cc,upcomingMove+1);
                }
        }
        // unmark while going back
        chess[r][c] = 0;
    }

    public static void displayBoard(int[][] chess){
        for(int i = 0; i < chess.length; i++){
            for(int j = 0; j < chess[0].length; j++){
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean isSafeToPlace(int[][] board,int r,int c,int n){
        // row check
        for(int row=0;row<board.length;row++){
            if(board[row][c] == n) return false;
        }

        // col check
        for(int col=0;col<board[0].length;col++){
            if(board[r][col] == n) return false;
        }

        // sub matrix check
        int rr = r-r%3;
        int cc = c-c%3;
        for(int row=rr;row<rr+3;row++){
            for(int col=cc;col<cc+3;col++){
                if(board[row][col] == n) return false;
            }
        }

        return true;
    }

    public static void sudoku(int[][] board,int row,int colMarker){
        if(row == board.length){
            // sudoku is solved
        //    displayBoard(board);
            return;
        }

        for(int col=0;col<board.length;col++){
            if(board[row][col] == 0){
                for(int num=1;num<10;num++){
                    if(isSafeToPlace(board,row,col,num) == true){
                        board[row][col] = num;
                        sudoku(board, row+1,0);
                        board[row][col] = 0;
                    }
                }
            }
        }
    }


    public static void main(String[] args){
        // int[][] board = {
        //     {0, 1, 0, 0, 0, 0},
        //     {0, 1, 0, 1, 1, 0},
        //     {0, 1, 0, 1, 1, 0},
        //     {0, 0, 0, 0, 0, 0},
        //     {1, 1, 0, 1, 1, 0},
        //     {1, 1, 0, 0, 0, 0}
        // };
        // int[] arr = {10,20,30,40,50};

        // floodfill(board, 0, 0, "");
        // subsets(arr,0);
        // printWaysToSelectBox(0,4,0,"");
        // printWaysToSelectBox2D(2,2,0,0,0,"");

        int[][] board={
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0}

        };
        // nqueen(board,0,0,0,"");
        // nqueen2(board,0,"");
        // int[][] chess = new int[5][5];
        // printKnightsTour(chess,0,0,1);

        // int[][] sudokuBoard = { 
        //     { 3, 1, 6, 5, 7, 8, 4, 9, 2 },
        //     { 5, 2, 9, 1, 3, 4, 7, 6, 8 },
        //     { 4, 8, 7, 6, 2, 9, 5, 3, 1 },
        //     { 2, 6, 3, 0, 1, 5, 9, 8, 7 },
        //     { 9, 7, 4, 8, 6, 0, 1, 2, 5 },
        //     { 8, 5, 1, 7, 9, 2, 6, 4, 3 },
        //     { 1, 3, 8, 0, 4, 7, 2, 0, 6 },
        //     { 6, 9, 2, 3, 5, 1, 8, 7, 4 },
        //     { 7, 4, 5, 0, 8, 6, 3, 1, 0 } 
        // };
        int[][] sudokuBoard = new int[9][9];
        sudoku(sudokuBoard, 0,0);
    }
}



