import java.util.*;

public class articulationPoint {

    static int time = 0;
    private static void dfsArticulation(ArrayList<ArrayList<Integer>> graph,int src,boolean[] vis,int[] parent,int[] disc,int[] low,int[] arti){

        disc[src] = low[src] = time;
        time++;
        for(int nbr:graph.get(src)){
            if(vis[nbr] == true && parent[src] != nbr){
                // visited but not parent
                low[src] = Math.min(low[src],disc[nbr]);
            }else if(vis[nbr] == false){
                // unvisited
                parent[nbr] = src;
                dfsArticulation(graph,nbr,vis,parent,disc,low,arti);
                low[src] = Math.min(low[src],low[nbr]);
            }
        }

    }

    public static int articulationPoints(ArrayList<ArrayList<Integer>> graph){
        int count = 0;
        int n = graph.size();
        int[] parent = new int[n];
        int[] disc = new int[n];
        int[] low = new int[n];
        boolean[] vis = new boolean[n];
        int[] arti = new int[n];

        time = 0;
        parent[0] = -1;
        dfsArticulation(graph,0,vis,parent,disc,low,arti);
        
        return count;
    }
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
           
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i=0;i<n;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<m;i++){
            int v1 = scn.nextInt()-1;
            int v2 = scn.nextInt()-1;
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }
        scn.close();
        int count = articulationPoints(graph);
        System.out.println(count);
    }
}
