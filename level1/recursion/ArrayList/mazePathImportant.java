import java.io.*;
import java.util.*;

public class mazePathImportant {
    //(dioganl included)
    // only 1 jump 
    public static ArrayList<String> mazePath_HVD(int sr,int sc,int dr,int dc){
        ArrayList<String> myres = new ArrayList<>();
        if(sr == dr && sc == dc){
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        
        // horizontal
        if(sc+1 <= dc){
            ArrayList<String> horizontalPaths = mazePath_HVD(sr,sc+1,dr,dc);
            for(String s:horizontalPaths){
                myres.add("h"+s);
            }
        }   

         // vertical
         if(sr+1 <= dr){
            ArrayList<String> verticalPaths = mazePath_HVD(sr+1,sc,dr,dc);
            for(String s:verticalPaths){
                myres.add("v"+s);
            }
        }   

        // diagonal
         if(sr+1 <= dr && sc+1 <= dc){
            ArrayList<String> digonalPaths = mazePath_HVD(sr+1,sc+1,dr,dc);
            for(String s:digonalPaths){
                myres.add("d"+s);
            }
        }   

        return myres;

    }
    
    // with jumps 
    public static ArrayList<String> getMazePaths(int sr,int sc,int dr,int dc){
        ArrayList<String> myres = new ArrayList<>();
        if(sr == dr && sc == dc){
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        
        // horizontal
        for(int jump=1;sc+jump<=dc;jump++){
            ArrayList<String> horizontalPaths = getMazePaths(sr,sc+jump,dr,dc);
            for(String s:horizontalPaths){
                myres.add("h"+jump+s);
            }
        }   

         // vertical
         for(int jump=1;sr+jump<=dr;jump++){
            ArrayList<String> verticalPaths = getMazePaths(sr+jump,sc,dr,dc);
            for(String s:verticalPaths){
                myres.add("v"+jump+s);
            }
        }   

        // diagonal
         for(int jump=1;sr+jump<=dr && sc+jump<=dc;jump++){
            ArrayList<String> digonalPaths = getMazePaths(sr+jump,sc+jump,dr,dc);
            for(String s:digonalPaths){
                myres.add("d"+jump+s);
            }
        }   
        return myres;

    }
    
    // generic with 1 jump
    public static ArrayList<String> mazePath_HVDGenric(int sr,int sc,int dr,int dc,int[][] dir,String[] dirS){
        if(sr == dr && sc == dc){
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> myres = new ArrayList<>();

        for(int d=0;d<dir.length;d++){
            int c = sc+dir[d][1]; //vertical
            int r = sr+dir[d][0]; //horizontal
            if(r>=0 && c>=0 && r<=dr && c<=dc){ //need to check overflow matrix se bhaar na jaye
                ArrayList<String> rreq = mazePath_HVDGenric(r, c, dr, dc, dir, dirS);
                for(String s:rreq){
                    myres.add(dirS[d]+s);
                }
            }
        }
        return myres;
    }

    // generic with radius jumps bfs approach goes through radius by radius level
    public static ArrayList<String> mazePath_HVDGenricRad(int sr,int sc,int dr,int dc,int[][] dir,String[] dirS,int RAD){
        if(sr == dr && sc == dc){
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> myres = new ArrayList<>();

        for(int radius=1;radius<=RAD;radius++){
            for(int d=0;d<dir.length;d++){
                int c = sc+radius*dir[d][1]; //vertical
                int r = sr+radius*dir[d][0]; //horizontal
                if(r>=0 && c>=0 && r<=dr && c<=dc){ //need to check overflow matrix se bhaar na jaye
                    ArrayList<String> rreq = mazePath_HVDGenricRad(r, c, dr, dc, dir, dirS,RAD);
                    for(String s:rreq){
                        myres.add(dirS[d]+"R"+radius+s);
                    }
                }
            }
        }
        return myres;
    }
    
        // generic with radius jumps avoid multiple Calls DFS explore the radius of one dir'n first 
    public static ArrayList<String> mazePath_HVDGenricRadVertical(int sr,int sc,int dr,int dc,int[][] dir,String[] dirS,int RAD){
            if(sr == dr && sc == dc){
                ArrayList<String> base = new ArrayList<>();
                base.add("");
                return base;
            }
            ArrayList<String> myres = new ArrayList<>();
    
            for(int d=0;d<dir.length;d++){
                for(int radius=1;radius<=RAD;radius++){
                    int c = sc+radius*dir[d][1]; //vertical
                    int r = sr+radius*dir[d][0]; //horizontal
                    if(r>=0 && c>=0 && r<=dr && c<=dc){ //need to check overflow matrix se bhaar na jaye
                        ArrayList<String> rreq = mazePath_HVDGenricRadVertical(r, c, dr, dc, dir, dirS,RAD);
                        for(String s:rreq){
                            myres.add(dirS[d]+"R"+radius+s);
                        }
                    }else{
                        break;
                    }
                }
            }
            return myres;
        }
    public static void main(String[] args) throws Exception {
        // 1 jump
        // int[][] dir = {{0,1},{1,1},{1,0}};
        // String[] dirs ={"H","D","V"};
        // ArrayList<String> allPaths = mazePath_HVDGenric(0, 0, 2, 2,dir,dirs);
        // System.out.println(allPaths);

        // radius jump
        // int[][] dir = {{0,1},{1,1},{1,0}};
        // String[] dirs ={"H","D","V"};
        // int n = 4;
        // int m = 7;
        // ArrayList<String> allPaths = mazePath_HVDGenricRad(0, 0, n-1, m-1,dir,dirs,Math.max(n,m));
        // System.out.println(allPaths);

        // radius jump vertical
        int[][] dir = {{0,1},{1,1},{1,0}};
        String[] dirs ={"H","D","V"};
        int n = 4;
        int m = 4;
        ArrayList<String> allPaths = mazePath_HVDGenricRadVertical(0, 0, n-1, m-1,dir,dirs,Math.max(n,m));
        System.out.println(allPaths);
    }   
}
 