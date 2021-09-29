import java.util.*;
class TestClass {
    public static class Edge{
        int nbr;
        public Edge(int nbr){
            this.nbr = nbr;
        }
    }
    
    public static void addEdge(ArrayList<Edge>[] graph,int v1,int v2){
        graph[v1].add(new Edge(v2));
        graph[v2].add(new Edge(v1));
    }
    
    public static class Pair implements Comparable<Pair>{
        int vtx;
        char color;
        int time;
        String psf;
        public Pair(int vtx,char color,int time,String psf){
            this.vtx = vtx;
            this.color = color;
            this.time = time;
            this.psf = psf;
        }
        public int compareTo(Pair p){
            return this.time - p.time;
        }
    }
    
    public static void shortestRoute(ArrayList<Edge>[] graph,int src,int dest,int t,int c){
        PriorityQueue<Pair> qu = new PriorityQueue<>();
        boolean[] vis = new boolean[graph.length];
        
        qu.add(new Pair(src,'G',0,""+src));

        int minTime = Integer.MAX_VALUE;
        String path = "";
        
        while(qu.size() > 0){
            Pair rem = qu.remove();
            if(vis[rem.vtx] == true) continue;
            vis[rem.vtx] = true;
            if(rem.vtx == dest){
                minTime = Math.min(rem.time,minTime);
                path = rem.psf;
                // continue;
            }
            int waitingTime = 0;
            if(rem.color == 'R'){
                int temp = rem.time - t;
                waitingTime = t - temp;
            }
            for(Edge e:graph[rem.vtx]){
                int nbr = e.nbr;
                if(vis[nbr] == false){
                    char childColor = 'G';
                    if(c >= t){
                        childColor = 'R';
                    }
                    int childTime = rem.time + c + waitingTime;
                    qu.add(new Pair(nbr,childColor,childTime,rem.psf+" "+nbr));
                }
            }
        }
        System.out.println(minTime);
        System.out.println(path);
    }
    public static void main(String args[] ) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int t = scn.nextInt();
        int c = scn.nextInt();

        ArrayList<Edge>[] graph = new ArrayList[n+1];
        for(int i=0;i<n+1;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0;i<m;i++){
            int v1 = scn.nextInt();
            int v2 = scn.nextInt();
            addEdge(graph,v1,v2);
        }
        shortestRoute(graph,1,n,t,c);
        
    }
}
