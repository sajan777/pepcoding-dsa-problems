import java.util.*;
class TestClass {

    public static boolean findReachability(ArrayList<Integer>[] graph,int src,int dest,boolean[] vis){
        if(src == dest){
            return true;
        }
        vis[src] = true;
        for(int nbr:graph[src]){
            if(vis[src] == false){
                boolean rres = findReachability(graph,nbr,dest,vis);
                if(rres == true) return true;

            }
        }
        return false;
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
        }
        int src = scn.nextInt();
        int dest = scn.nextInt();

        boolean[] vis = new boolean[graph.length];
        boolean res = findReachability(graph,src,dest,vis);
        if(res == true){
            System.out.println("1");
        }else{
            System.out.println("0");
        }
    }
}
