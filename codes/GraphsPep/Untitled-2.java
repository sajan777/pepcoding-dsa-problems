import java.util.*;
class TestClass {

    public static class Edge{
        int src;
        int nbr;
        int wt;
        public Edge(int src,int nbr,int wt){
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static class JSPair implements Comparable<JSPair>{
        int vtx;
        int wsf;

        JSPair(int vtx,int wsf){
            this.vtx = vtx;
            this.wsf = wsf;
        }
        public int compareTo(JSPair o){
            return this.wsf - o.wsf;
        }
    }
    public static void learnJS(ArrayList<Edge>[] graph,int src,int dest){
        PriorityQueue<JSPair> pq = new PriorityQueue<>();
        pq.add(new JSPair(src,0));
        boolean[] vis = new boolean[graph.length];   
        while(pq.size() > 0){
            // 1. Get + remove
            JSPair rem = pq.remove();
            // 2 Mark
            if(vis[rem.vtx] == true){
                continue;
            }
            vis[rem.vtx] = true;
            // 3 Print

            if(rem.vtx == dest){
                System.out.println(rem.wsf);
                break;
            }

            // 4 add neighbors
            for(Edge e: graph[rem.vtx]){
                int nbr = e.nbr;
                if(vis[nbr] == false){
                    pq.add(new JSPair(nbr,rem.wsf+e.wt));
                }
            }
        }
    }
    public static void learnJSHelper(ArrayList<Edge>[] graph,int src,int dest){
        learnJS(graph,src,dest);
    }

    public static void main(String args[] ) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int vertices = 0;
        while(n-->0){
            vertices = Math.max(vertices,scn.nextInt());
        }
        int k = scn.nextInt();
        ArrayList<Edge>[] graph = new ArrayList[vertices+1];

        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0;i<k;i++){
            int sr = scn.nextInt();
            int nbr = scn.nextInt();
            int wt = scn.nextInt();
            graph[sr].add(new Edge(sr,nbr,wt));
        }
        int src = scn.nextInt();
        int dest = scn.nextInt();

        learnJSHelper(graph,src,dest);
    }
}
