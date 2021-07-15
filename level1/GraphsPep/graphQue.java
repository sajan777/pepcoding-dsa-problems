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
    
    // ALLL PATH DFS
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
            // psf += dest;
            // smallest path
            if(spathwt > wsf){
                spath = psf;
                spathwt = wsf;
            }
            // largest path
            if(lpathwt < wsf){
                lpath = psf;
                lpathwt = wsf;
            }
            // ciel and floor
            if(wsf > criteria){
                // ciel
                if(wsf < cpathwt){
                    cpathwt = wsf;
                    cpath = psf;
                }
            }
            if(wsf < criteria){
                // floor
                if(wsf > fpathwt){
                    fpathwt = wsf;
                    fpath = psf;
                }
            }
            // kth largest
            if(pq.size() < k){
                pq.add(new Pair(wsf,psf));
            }else{
                if(pq.peek().wsf < wsf){
                    pq.remove();
                    pq.add(new Pair(wsf,psf));
                }
            }
            return;
        }
        visited[src] = true;
        for(Edge e:graph[src]){
            if(visited[e.nbr] == false){
                multisolver(graph, e.nbr, dest, visited, criteria, k, psf+e.nbr+"", wsf+e.wt);
            }
        }
        visited[src] = false; 
    }

    // Get Connect Components
    // Return type 0(n2)
    public static ArrayList<Integer> gcc2(ArrayList<Edge>[] graph,int src,boolean[] vis){
        ArrayList<Integer> mres = new ArrayList<>();
        mres.add(src);
        
        vis[src] = true;
        for(Edge e:graph[src]){
            int nbr = e.nbr;
            if(vis[nbr] == false){
                ArrayList<Integer> rres = gcc2(graph, nbr, vis);
                for(int val:rres){
                    mres.add(val);
                }
            }
        }
        return mres;
    }

    // 0(n)
    public static void getConnectedComponent(ArrayList<Edge>[] graph,int src,boolean[] vis,ArrayList<Integer> comp){
        vis[src] = true;
        comp.add(src);
        for(Edge e:graph[src]){
            int nbr = e.nbr;
            if(vis[nbr] == false){
                getConnectedComponent(graph, nbr, vis, comp);
            }
        }
    }
 
    
    public static ArrayList<ArrayList<Integer>> getConnectedComponents(ArrayList<Edge>[] graph){
        ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
        int n = graph.length;
        boolean[] vis = new boolean[n];
        for(int v=0;v<n;v++){
            if(vis[v] == false){
                // 0(n2) return type
                // ArrayList<Integer> comp = gcc2(graph,v,vis);
                // comps.add(comp);

                ArrayList<Integer> comp = new ArrayList<>();
                getConnectedComponent(graph,v,vis,comp);
                comps.add(comp);
            }
        }
        return comps;

    }
    // ArrayList<ArrayList<Integer>> comps = getConnectedComponents(graph);
    // System.out.println(comps);

    public static boolean IsConnected(ArrayList<Edge>[] graph){

        int n = graph.length;
        boolean[] vis = new boolean[n];
        int counter = 0;
        for(int v=0;v<n;v++){
            if(vis[v] == false){
                counter++;
                if(counter > 1){
                    return false;
                }
                ArrayList<Integer> comp = new ArrayList<>(); //no significance in case of isConnected
                getConnectedComponent(graph,v,vis,comp);
            }
        }
        return true;
    }
    
    // isGraphConnected
    public static void isGraphConnected(ArrayList<Edge>[] graph){
        
        System.out.println(IsConnected(graph));
        
        // ArrayList<ArrayList<Integer>> comps = getConnectedComponents(graph);
        // if(comps.size() > 1){
        //     System.out.println(false);
        // }else{
        //     System.out.println(true);
        // }
    }

    // number of islands 
    // Order for dir T,L,D,R
    static int[] xdir = {-1,0,1,0};
    static int[] ydir = {0,-1,0,1};

    public static void gccForIsland(int[][] graph,int x,int y){
        graph[x][y] = -1;
        for(int d=0;d<4;d++){
            int r = x+xdir[d];
            int c = y+ydir[d];

            if(r >= 0 && r<graph.length && c>=0 && c<graph[0].length && graph[r][c] == 0){
                gccForIsland(graph, r, c);
            }
        }
    }
    
    public static int noOfIsland(int[][] graph){
        int count = 0;
        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph[i].length;j++){
                if(graph[i][j] == 0){
                    count++;
                    gccForIsland(graph, i, j);
                }
            }
        }
        return count;
    }
    
    
    // Hamiltonian Path and Cycle
    public static void hamiltonianPathAndCycle(ArrayList<Edge>[] graph,int src,int osrc,HashSet<Integer> visited,String psf){

        if(visited.size() == graph.length-1){
            psf += src;
            // check whether the path is cyclic or normal
            boolean isCyclic = false;   
            for(Edge e:graph[osrc]){
                if(e.nbr == src){
                    isCyclic = true;
                    break;
                }
            }
            if(isCyclic == true){
                System.out.println(psf+"*");
            }else{
                System.out.println(psf+".");
            }
            return;
        }
        
        visited.add(src);
        for(Edge e:graph[src]){
            int nbr = e.nbr;
            if(visited.contains(nbr) == false){
                hamiltonianPathAndCycle(graph, nbr,osrc, visited, psf+src);
            }
        }
        visited.remove(src);
    }
    // HashSet<Integer> visited = new HashSet();
    // hamiltonianPathAndCycle(graph,src,src,visited,"");

    // Knights Tour
    public static void displayBoard(int[][] chess){
        for(int i = 0; i < chess.length; i++){
            for(int j = 0; j < chess[0].length; j++){
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    static int[][] dirs = {{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};
    public static void printKnightsTour(int[][] chess, int r, int c, int upcomingMove) {

        if(upcomingMove == chess.length*chess.length){
            chess[r][c] = upcomingMove;
            displayBoard(chess);
            chess[r][c] = 0;
            return;
        }

        chess[r][c] = upcomingMove;
        for(int d=0;d<dirs.length;d++){
            int rr = r+dirs[d][0];
            int cc = c+dirs[d][1];
            if(rr >=0 && cc >=0 && rr<chess.length && cc<chess.length && chess[rr][cc] == 0){
                printKnightsTour(chess, rr, cc, upcomingMove+1);
            }
        }
        chess[r][c] = 0;
    }
    // Scanner scn = new Scanner(System.in);
    // int n = scn.nextInt();
    // int row = scn.nextInt();
    // int col = scn.nextInt();
    // int[][] board = new int[n][n];
    // printKnightsTour(board,row,cols,1);


    // Breadth first travel
    public static class bfsPair{
        int vts;
        // vertex = vts
        String psf;
        bfsPair(int vts,String psf){
            this.vts = vts;
            this.psf = psf;
        }
    }
    // one src->every vortex
    // all paths need to be joined in graph
    public static void bfs(ArrayList<Edge>[] graph,int src){
        Queue<bfsPair> qu = new LinkedList<>();

        boolean[] vis = new boolean[graph.length];

        qu.add(new bfsPair(src, ""));
        // way2
        // qu.add(new bfsPair(src, "" + src));
        while(qu.size() > 0){
            // 1. get +remove
            bfsPair rem = qu.remove();
            // 2,mark *
            if(vis[rem.vts] == true){
                // already visited
                continue;
                // there is an alternate path but dont select it as it's already taken
                // this tells that the graph is cycle
            }else{
                // unvisited mark as visited
                vis[rem.vts] = true;
            }

            // 3 print
            // if(rem.vts == 6) for specific src to specific dest print inside
            // right now printing all paths in terms of edges src to desti
            System.out.println(rem.vts+"@"+rem.psf+rem.vts);
            // way2
            // System.out.println(rem.vts+"@"+rem.psf);

            // 4.add unvisited nbr
            for(Edge e:graph[rem.vts]){
                if(vis[e.nbr] == false){
                    qu.add(new bfsPair(e.nbr, rem.psf+rem.vts));
                    
                    // way2
                    // qu.add(new bfsPair(e.nbr, rem.psf+e.nbr));

                }
            }
        }
    }

    // is Graph Cyclic
    public static boolean bfsForCycle(ArrayList<Edge>[] graph,int src,boolean[] vis){
        Queue<Integer> qu = new LinkedList<>();
        qu.add(src);
        while(qu.size() > 0){
            // 1. get +remove
            int rem = qu.remove();
            // 2,mark *
            if(vis[rem] == true){
                // already visited cycle detected
                return true;
            }else{
                // unvisited mark as visited
                vis[rem] = true;
            }
            // 4.add unvisited nbr
            for(Edge e:graph[rem]){
                if(vis[e.nbr] == false){
                    qu.add(e.nbr);
                }
            }
        }
        return false;
    }

    // DFS IsCyclic
    public static boolean dfsForCycle(ArrayList<Edge>[] graph,int src,boolean[] vis,int par){
        vis[src] = true;
        for(Edge e:graph[src]){
            int nbr = e.nbr;
            if(vis[nbr] == true && nbr != par){
                // cycle is present
                return true;
            }
            if(vis[nbr] == false){
                boolean rres = dfsForCycle(graph, nbr, vis, src);
                if(rres == true) return false;
            }
        }
        return false;
    }
    
    public static boolean isCyclic(ArrayList<Edge>[] graph){
        int n = graph.length;
        boolean[] vis = new boolean[n];
        for(int v=0;v<n;v++){
            if(vis[v] == false){
                // boolean res = dfsForCycle(graph,v,vis,-1);
                boolean res = bfsForCycle(graph,v,vis);
                if(res == true) return true;
            }
        }
        return false;
    }

    // bipartite
    static class BPair{
        int vtx; //vertex
        int dtime; // discoveryTime

        BPair(int vtx,int dtime){
            this.vtx = vtx;
            this.dtime = dtime;
        }
    }

    public static boolean isGraphBipartiteBFS(ArrayList<Edge>[] graph,int src,int[] vis){

        Queue<BPair> qu = new LinkedList<>();
        qu.add(new BPair(src, 0));
        while(qu.size() > 0){
            BPair rem = qu.remove();
            if(vis[rem.vtx] != -1){
                // already discovered

                //1. if same level discover continue beacuase even size cycle is there and can be splitted
                // 2.if discovered with diff level return false because of odd size cycle is present in graph
                if(vis[rem.vtx] == rem.dtime) 
                    continue;
                else return false;
            }
            vis[rem.vtx] = rem.dtime;
            for(Edge e:graph[rem.vtx]){
                int nbr = e.nbr;
                if(vis[nbr] == -1){
                    qu.add(new BPair(nbr, rem.dtime+1));
                }
            }
        }
        return true;
    }    
    
    public static boolean isGraphBipartite(ArrayList<Edge>[] graph){
        int n = graph.length;
        int[] vis = new int[n];
        Arrays.fill(vis,-1);
        for(int v=0;v<n;v++){
            if(vis[v] == -1){
                boolean res = isGraphBipartiteBFS(graph,v,vis);
                if(res == false) return false;
            }
        }
        return true;
    }
    
    // Spread Infection
    public static int spreadInfection(ArrayList<Edge>[] graph,int src,int t){
        Queue<BPair> qu = new LinkedList<>();
        int[] vis = new int[graph.length];
        qu.add(new BPair(src,1));
        int count = 0;
        while(qu.size() > 0){
            // 1.GET+ remove
            BPair rem = qu.remove();
            // Mark *
            if(vis[rem.vtx] != 0){
                continue;
            }
            vis[rem.vtx] = rem.dtime;
            // work
            if(rem.dtime > t) {
                break;
            }
            // System.out.println(rem.vtx+" become sick at time "+rem.dtime);
            count++;
            // ADD neighbors
            for(Edge e:graph[rem.vtx]){
                int nbr = e.nbr;
                if(vis[nbr] == 0){
                    qu.add(new BPair(nbr,rem.dtime+1));
                }
            }
        }
        return count;
    }


    // Shortest path in weights -> DFS Dijkstra
    public static class DPair implements Comparable<DPair>{
        int vtx;
        int wsf;
        String psf;

        DPair(int vtx,String psf,int wsf){
            this.vtx = vtx;
            this.psf = psf;
            this.wsf = wsf;
        }
        public int compareTo(DPair o){
            return this.wsf - o.wsf;
        }
    }
    public static void dijkstras(ArrayList<Edge>[] graph,int src){
        PriorityQueue<DPair> pq = new PriorityQueue<>();
        pq.add(new DPair(src,""+src,0));
        boolean[]  vis = new boolean[graph.length];   
        while(pq.size() > 0){
            // 1. Get + remove
            DPair rem = pq.remove();
            // 2 Mark
            if(vis[rem.vtx] == true){
                continue;
            }
            vis[rem.vtx] = true;
            // 3 Print
            // if(rem.vtx == 6) for src to secific dest add print statement inside.
            // right now printing all shortest paths src to dest in terms of wt
            System.out.println(rem.vtx+" via "+rem.psf+" @ "+rem.wsf);

            // 4 add neighbors
            for(Edge e: graph[rem.vtx]){
                int nbr = e.nbr;
                if(vis[nbr] == false){
                    pq.add(new DPair(nbr,rem.psf+nbr,rem.wsf+e.wt));
                }
            }
        }
    }

    // minimum-wire-to-connect-all-pcs-official
    // Min Spanning TReee!!!!!
    // Prims ALgo
    public static class primsHelper implements Comparable<primsHelper>{
        int vtx;
        int parent;
        int wt;
        public primsHelper(int vtx,int parent,int wt){
            this.vtx = vtx;
            this.parent = parent;
            this.wt = wt;
        }

        public int compareTo(primsHelper o){
            return this.wt - o.wt;
        }
    }
    public static void prims(ArrayList<Edge>[] graph){

        PriorityQueue<primsHelper> pq = new PriorityQueue<>();
        // we can start from any vertex pq.add(new primsHelper(3, -1, 0));
        pq.add(new primsHelper(0, -1, 0));
        
        int n = graph.length;
        boolean[] vis = new boolean[n];
        
        ArrayList<Edge>[] mst = new ArrayList[n];
        for(int i=0;i<n;i++){
            mst[i] = new ArrayList<>();
        }
        while(pq.size() > 0){
            // 1. removal
            primsHelper rem = pq.remove();
            // 2. mark
            if(vis[rem.vtx] == true) continue;
            vis[rem.vtx] = true;
            // 3. work
            // add for MST graph pruint for ques
            if(rem.parent != -1){
                System.out.println("["+rem.vtx+"-"+rem.parent+"@"+rem.wt+"]");
                // addEdge(mst, rem.vtx, rem.parent, rem.wt);
            }

            //4. add nbr
            for(Edge e:graph[rem.vtx]){
                int nbr = e.nbr;
                if(vis[nbr] == false){
                    pq.add(new primsHelper(nbr, rem.vtx, e.wt));
                }
            }
        }
        // display(mst);

    }

    // order of compilation Valid for DAG(directed Acylic Graph)
    public static void TopologicalSort(ArrayList<Edge>[] graph,int src,boolean[] vis,Stack<Integer> ans){
        vis[src] = true;
        for(Edge e: graph[src]){
            int nbr = e.nbr;
            if(vis[nbr] == false){
                TopologicalSort(graph, nbr, vis, ans);
            }
        }
        ans.push(src);
    }

    public static void TopologicalSortMain(ArrayList<Edge>[] graph){
        int n = graph.length;
        boolean[] vis = new boolean[n];
        Stack<Integer> st = new Stack<>();
        for(int v=0;v<n;v++){
            if(vis[v] == false){
                TopologicalSort(graph, v, vis, st);
            }
        }

        // Order of Compilation is  just reverse of Topological Sort..
        while(st.size() > 0){
            System.out.println(st.pop());
        }
    }


    //DFS
    // Cycle detection in DAG  https://practice.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1 using DFS
    public boolean dagCycle(ArrayList<ArrayList<Integer>> graph,int src,boolean[] vis,boolean[] mypath){
        vis[src] = true;
        mypath[src] = true;
        for(int nbr:graph.get(src)){
            if(mypath[nbr] == true){
                // cycle the nbr is already visited in my path
                return true;
                // agar vis[nbr] == true hoga to use skip kardena hai means vha jane ka koi b fayda nhi hai vo already true marked hai vha se cycle nhi milega
            }else if(vis[nbr] == false){
                boolean res = dagCycle(graph, nbr, vis,mypath);
                if(res == true) return true;
            }
        }
        mypath[src] = false;
        return false;
    }


    public boolean isCyclic(int n, ArrayList<ArrayList<Integer>> graph){
        boolean[] vis = new boolean[n];
        boolean[] mypath = new boolean[n];
        for(int v=0;v<n;v++){
            if(vis[v] == false){
                boolean rres = dagCycle(graph,v,vis,mypath);
                if(rres == true) return true;
            }
        }
        return false;
    }

    

    // Rotten Oranges
    public class opair{
        int x;
        int y;  
        int t;
        
        public opair(int x,int y,int t){
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }
    public int orangesRotting(int[][] graph) {
        // 1. make a queue and add rotted oranges in it with t = 0 as well as mantain a count of fresh + rotted orange for final result;
        Queue<opair> qu = new LinkedList<>();
        int orange = 0;
        // oranges = rottedOrangesCount +  freshOrangeCount
        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph[i].length;j++){
                if(graph[i][j] == 2){
                    qu.add(new opair(i,j,0));
                    orange+=1;
                }else if(graph[i][j] == 1){
                    orange+=1;
                }
            }
        }
        if(orange == 0) return 0; // no fresh oranges to rot
        // 2. process bfs and find maxTime mark as -1;
        int time = 0; // time variable to return at last
        // dir array to travel in graph tldr
        int[][] RottingDirs = {{-1,0},{0,-1},{1,0},{0,1}};
        while(qu.size() > 0){
            int sz = qu.size();
            while(sz-->0){
                // get + remove
                opair rem = qu.remove();
                // mark
                if(graph[rem.x][rem.y] == -1){
                    // if already visited continue
                    continue;
                }
                graph[rem.x][rem.y] = -1; // mark as -1 visited
                time = rem.t; // final time updating at every queue removal
                orange--; // fresh Oranges are getting rotted
                // print
                // System.out.println("Orange at ["+rem.x+","+rem.y+"] time "+rem.t);
                for(int d=0;d<RottingDirs.length;d++){
                    int rr = rem.x+RottingDirs[d][0];
                    int cc = rem.y+RottingDirs[d][1];
                    if(rr >= 0 && rr<graph.length && cc>=0 && cc<graph[0].length && graph[rr][cc] == 1){
                        qu.add(new opair(rr, cc, rem.t+1));
                    }

                }
            }
        }
        // System.out.println("Total Time :"+time);
        // System.out.println("Orange Remain :"+orange);

        // if there are any remanining oranges that aren't rotted return -1;
        return orange == 0 ? time : -1;
    }

    public static void fun(){
        int edges = 7; // no of vertices
        ArrayList<Edge>[] graph = new ArrayList[edges];
        for(int i=0;i<edges;i++){
            graph[i] = new ArrayList<>();
        }

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 40);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 10);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 3);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);
        bfs(graph,0);
        
        int[][] grid = {
            {2,0,1,0,1},
            {0,1,2,0,0},
            {1,1,0,1,1},
            {0,1,1,1,1},
            {0,2,1,1,2}
        };
        // orangesRotting(grid);


        // int[][] data = {
        //     {0,1,10},
        //     {0,3,40},
        //     {1,2,10},
        //     {2,3,10},
        //     {3,4,2},
        //     {4,5,3},
        //     {4,6,8},
        //     {5,6,3},
        // };
        // for(int[] arr:data){
        //     addEdge(graph, arr[0], arr[1], arr[2]);
        // }

        // boolean[] visited1 = new boolean[edges];
        // allPath(graph, 0, 6, visited1, "", 0);
        // // display(graph);
        // boolean[] visited = new boolean[edges];
        // multisolver(graph,0,6,visited,40,3,"",0);
        // System.out.println("Smallest Path = "+spath+"@"+spathwt);
        // System.out.println("Largest Path = "+lpath+"@"+lpathwt);
        // System.out.println("Just Larger Path than "+30+"= "+cpath+"@"+cpathwt);
        // System.out.println("Just Smaller Path than "+30+"= "+fpath+"@"+fpathwt);
        // System.out.println(3 + "th largest path = " + pq.peek().psf + "@" + pq.peek().wsf);
    }

    public static void main(String[] args) {
       fun(); 
    }
}