import java.util.*;

public class graphQue{
    
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
    public static void addEdge(ArrayList<Edge>[] graph,int v1,int v2,int wt){
        graph[v1].add(new Edge(v1,v2,wt));
        graph[v2].add(new Edge(v2,v1,wt));
    }
    public static void display(ArrayList<Edge>[] graph) {
        // System.out.println(graph);
        for(int v=0;v<graph.length;v++){
            ArrayList<Edge> vertices = graph[v];
            System.out.print("["+v+"] -> ");   
            for(int n=0;n<vertices.size();n++){
                Edge edge = vertices.get(n);
                System.out.print("("+edge.src+"<-->"+edge.nbr+" @ "+edge.wt+"), ");
            }
            System.out.println();
        }
    }

    // DFS-> depth first search
    public static boolean hasPath(ArrayList<Edge>[] graph,int src,int dest,boolean[] visited){
        if(src == dest) return true;

        // graph[src] -> ArrayList<Edge>
        visited[src] = true;
        for(Edge e:graph[src]){
            int nbr = e.nbr;
            if(visited[nbr] == false){
                // if nbr is unvisited then go to nbr
                boolean rres = hasPath(graph, nbr, dest,visited);
                if(rres == true){
                    return true;
                }
            }
        }
        return false;
    }
    // boolean[] visited = new boolean[edges];
    // System.out.println(hasPath(graph,src,dest,visited));
    
    // ALLL PATH
    public static void allPath(ArrayList<Edge>[] graph,int src,int dest,boolean[] visited,String psf,int wsf){
        if(src == dest){
            psf += src;
            System.out.println(psf+"@"+wsf);
            return;
        } 

        // graph[src] -> ArrayList<Edge>
        visited[src] = true;
        for(Edge e:graph[src]){
            int nbr = e.nbr; 
            int curr = e.src; 
            if(visited[nbr] == false){
                // if nbr is unvisited then go to nbr
                allPath(graph, nbr, dest,visited,psf+curr+"",wsf+e.wt);
            }
        }
        visited[src] = false;
    }

    // MultiSolver+++++++++++++++++++++++++++
    static class Pair implements Comparable<Pair> {
        int wsf;
        String psf;
  
        Pair(int wsf, String psf){
           this.wsf = wsf;
           this.psf = psf;
        }
  
        public int compareTo(Pair o){
           return this.wsf - o.wsf;
        }
     }
    static String spath;
    static Integer spathwt = Integer.MAX_VALUE;
    static String lpath;
    static Integer lpathwt = Integer.MIN_VALUE;
    static String cpath;
    static Integer cpathwt = Integer.MAX_VALUE;
    static String fpath;
    static Integer fpathwt = Integer.MIN_VALUE;
    static PriorityQueue<Pair> pq = new PriorityQueue<>();
    public static void multisolver(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, int criteria, int k, String psf, int wsf) {
       
        if(src == dest){
            psf += dest;
            if(spathwt > wsf){
                spath = psf;
                spathwt = wsf;
            }
            if(lpathwt < wsf){
                lpath = psf;
                lpathwt = wsf;
            }
            if(wsf > criteria){
                // ciel
                if(wsf < cpathwt){
                    cpathwt = wsf;
                    cpath = psf;
                }
            }else{
                // floor
                if(wsf > fpathwt){
                    fpathwt = wsf;
                    fpath = psf;
                }
            }
            pq.add(new Pair(wsf,psf));

        }
        visited[src] = true;
        for(Edge e:graph[src]){
            if(visited[e.nbr] == false){
                multisolver(graph, e.nbr, dest, visited, criteria, k, psf+e.nbr+"", wsf+e.wt);
            }
        }
        visited[src] = false; 
    }

    public static void fun(){
        int edges = 7; // no of vertices
        ArrayList<Edge>[] graph = new ArrayList[edges];
        for(int i=0;i<edges;i++){
            graph[i] = new ArrayList<>();
        }

        // addEdge(graph, 0, 1, 10);
        // addEdge(graph, 0, 3, 40);
        // addEdge(graph, 1, 2, 10);
        // addEdge(graph, 2, 3, 10);
        // addEdge(graph, 3, 4, 2);
        // addEdge(graph, 4, 5, 3);
        // addEdge(graph, 4, 6, 8);
        // addEdge(graph, 5, 6, 3);
        
        int[][] data = {
            {0,1,10},
            {0,3,40},
            {1,2,10},
            {2,3,10},
            {3,4,2},
            {4,5,3},
            {4,6,8},
            {5,6,3},
        };
        for(int[] arr:data){
            addEdge(graph, arr[0], arr[1], arr[2]);
        }

        boolean[] visited1 = new boolean[edges];
        allPath(graph, 0, 6, visited1, "", 0);
        // display(graph);
        boolean[] visited = new boolean[edges];
        multisolver(graph,0,6,visited,40,3,"",0);
        System.out.println("Smallest Path = "+spath+"@"+spathwt);
        System.out.println("Largest Path = "+lpath+"@"+lpathwt);
        System.out.println("Just Larger Path than "+30+"= "+cpath+"@"+cpathwt);
        System.out.println("Just Smaller Path than "+30+"= "+fpath+"@"+fpathwt);
        System.out.println(3 + "th largest path = " + pq.peek().psf + "@" + pq.peek().wsf);
    }

    public static void main(String[] args) {
       fun(); 
    }
}