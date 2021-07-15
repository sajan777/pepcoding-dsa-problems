import java.util.*;
class TestClass {

    public static void reactNewbie(ArrayList<Integer>[] graph,int osrc,int src,int dest,boolean[] vis){
        if(src == dest){
            System.out.println(osrc);
            return;
        }
        vis[src] = true;
        for(int nbr:graph[src]){
            if(vis[nbr] == false){
                reactNewbie(graph,src,nbr,dest,vis);
            }
        }
    }


    public static void main(String args[] ) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int vertices = 0;
        while(n-->0){
            vertices = Math.max(vertices,scn.nextInt());
        }
        int k = scn.nextInt();
        ArrayList<Integer>[] graph = new ArrayList[vertices+1];
        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0;i<k;i++){
            int sr = scn.nextInt();
            int nbr = scn.nextInt();
            graph[sr].add(nbr);
            graph[nbr].add(sr);
        }
        int dest = scn.nextInt();
        int src = scn.nextInt();

        boolean[] vis = new boolean[vertices+1];
        reactNewbie(graph,src,src,dest,vis);
    }
}
