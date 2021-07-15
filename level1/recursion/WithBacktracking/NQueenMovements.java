import java.util.*;


public class NQueenMovements {
    public static void main(String[] args){
        queenMovements();
        knightsMovements();
    }
    public static void knightsMovements(){
        int r = 4;
        int c  = 4;
        int[][] dirs = {{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};
        for(int d=0;d<dirs.length;d++){
            int rr = r+dirs[d][0];
            int cc = c+dirs[d][1];
            System.out.println(" Dir: "+d+" Row: "+rr+" Col: "+cc);
        }
    } 

    public static void queenMovements(){
        int n = 10;
        int m = 10;
        // queens pos gen on chess board
        // int r = 0;
        // int c = 0;
        int r = n/2;
        int c = m/2;
        // int[] rdir = {-1,-1,0,1,1,1,0,-1};
        // int[] cdir = {0,1,1,1,0,-1,-1,-1};
        int[][] dirs = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
        int radius = Math.max(n,m);
        // radius wise bfs kind of (first explore one dir fullly)
        for(int rad=1;rad<radius;rad++){
            for(int dir=0;dir<dirs.length;dir++){
                int rr = r + rad*dirs[dir][0];
                int cc = c + rad*dirs[dir][1];

                if(rr>=0 && rr<n && cc>=0 && cc<m){
                    System.out.println("Radius: "+rad+" Dir: "+dir+" Row: "+rr+" Col: "+cc);
                }
            }
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        // direction wise dfs kind of (first explore one radius fully)
        for(int dir=0;dir<dirs.length;dir++){
            for(int rad=1;rad<radius;rad++){
                int rr = r + rad*dirs[dir][0];
                int cc = c + rad*dirs[dir][1];

                if(rr>=0 && rr<n && cc>=0 && cc<m){
                    System.out.println("Radius: "+rad+" Dir: "+dir+" Row: "+rr+" Col: "+cc);
                }
            }
        }


    }
}
