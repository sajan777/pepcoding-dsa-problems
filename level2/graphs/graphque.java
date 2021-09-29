import java.util.*;

class grapque{
    
    public static class Edge{
        int nbr;
        int wt;

        public Edge(int nbr,int wt){
            this.nbr = nbr;
            this.wt = wt;
        }
    }
    public static void addEdge(ArrayList<Edge>[] graph,int v1,int v2,int wt){
        graph[v1].add(new Edge(v2,wt));
        graph[v2].add(new Edge(v1,wt));
    }

    public static void solve(){
        int edges = 7;
        ArrayList<Edge>[] graph = new ArrayList[edges];
        for(int i=0;i<edges;i++){
            graph[i] = new ArrayList<>();
        }
        addEdge(graph,0,1,10);
    }

    // DFS (has Path) (from Src->Dest)  (internal Stack)
    public static boolean DFS(ArrayList<Edge>[] graph,int src,int dest,boolean[] vis,String psf){
        if(src == dest){
            psf+=" -> "+dest;
            System.out.println(psf);
            return true;
        }

        vis[src] = true;
        boolean res = false;
        for(Edge e:graph[src]){
            int nbr = e.nbr;
            if(vis[nbr] == false){
                res = res || DFS(graph,nbr,dest,vis,psf+" -> "+src);
                // 1st iteration
                // res = false || call
                // res = true || call (socho path milgya from call in 1st iteration) (ab yha or me first true aagya to aage call nhi lagega)
                // agar false aaya 1st call se
                // res = false || call
                // check scnsht for explanation
                
            }
        }
        return res;
    }

    // BFS (4 Steps) (Queue)
    public static class BPair{
        int vtx;
        String psf;
        int wsf;
        public BPair(int vtx,int wsf,String psf){
            this.vtx = vtx;
            this.psf = psf;
            this.wsf = wsf;
        }
    }
    public static boolean BFS(ArrayList<Edge>[] graph,int src,int dest,boolean[] vis){
        // 1. GET + REMOVE
        // 2. MARK *
        // 3. WORK
        // 4. ADD NEIGHBORS
        Queue<BPair> qu = new LinkedList<>();
        qu.add(new BPair(src,0,src+""));
        boolean res = false;
        while(qu.size() > 0){
            // 1. GET + REMOVE
            BPair rem = qu.remove();
            // 2. MARK *
            if(vis[rem.vtx] == true) continue;
            vis[rem.vtx] = true;
            // 3. WORK
            if(src == dest){
                res = true;
                break;
            }
            System.out.println(rem.vtx+"  " + rem.psf + " @ " +rem.wsf);
            // 4. ADD NEIGHBORS
            for(Edge e:graph[rem.vtx]){
                int nbr = e.nbr;
                if(vis[nbr] == false){
                    qu.add(new BPair(nbr,rem.wsf+e.wt,rem.psf+nbr));
                }
            }
        }
        return res;
    }
    
    
    int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
    String[] directions = {"T","R","B","L"};

    // 200. Number of Islands
    public void numIslandsUtil(char[][] grid,int r,int c){
        grid[r][c] = '0';
        for(int d=0;d<dirs.length;d++){
            int rr = r+dirs[d][0];
            int cc = c+dirs[d][1];
            if(rr >=0 && cc >=0 && rr<grid.length && cc<grid[0].length && grid[rr][cc] == '1'){
                numIslandsUtil(grid, rr, cc);
            }
        }
    }
    public int numIslands(char[][] grid) {
        int count = 0;
        for(int i=0;i<grid.length;i++){
          for(int j=0;j<grid[0].length;j++){
              if(grid[i][j] == '1'){
                  count+=1;
                  numIslandsUtil(grid,i,j);
              }
          }
        }
        return count;  
    }
    // https://leetcode.com/problems/count-sub-islands/ (DIY)
    
    // lintcode 860. https://www.lintcode.com/problem/860/
    // Shreesh Approach
    static StringBuilder str;
    public void numberofDistinctIslandsUtil(int[][] grid,int r,int c){
        grid[r][c] = 0;
        for(int d=0;d<dirs.length;d++){
            int rr = r+dirs[d][0];
            int cc = c+dirs[d][1];
            if(rr >=0 && cc >=0 && rr<grid.length && cc<grid[0].length && grid[rr][cc] == 1){
                str.append(directions[d]);
                numberofDistinctIslandsUtil(grid, rr, cc);
            }
        }
        str.append("z");
    }
    public int numberofDistinctIslands(int[][] grid) {
        HashSet<String> set = new HashSet<>();
        for(int i=0;i<grid.length;i++){
          for(int j=0;j<grid[0].length;j++){
              if(grid[i][j] == 1){
                  str = new StringBuilder("x");
                  numberofDistinctIslandsUtil(grid,i,j);
                  set.add(str.toString());
              }
          }
        }
        return set.size();
    }

    // My Approach
    public void numberofDistinctIslandsUtil2(int[][] grid,int r,int c,String[] str){
        grid[r][c] = 0;
        for(int d=0;d<dirs.length;d++){
            int rr = r+dirs[d][0];
            int cc = c+dirs[d][1];
            if(rr >=0 && cc >=0 && rr<grid.length && cc<grid[0].length && grid[rr][cc] == 1){
                str[0] = str[0]+"1"+directions[d];
                numberofDistinctIslandsUtil2(grid, rr, cc,str);
            }
        }
    }
    public int numberofDistinctIslands2(int[][] grid) {
        HashSet<String> set = new HashSet<>();
        for(int i=0;i<grid.length;i++){
          for(int j=0;j<grid[0].length;j++){
              if(grid[i][j] == 1){
                  String[] str = {"1"};
                  numberofDistinctIslandsUtil2(grid,i,j,str);
                  set.add(str[0]);
              }
          }
        }
        return set.size();
    }

    // leetcode 1020. https://leetcode.com/problems/number-of-enclaves/
    public void numEnclavesUtil(int[][] grid,int r,int c){
        grid[r][c] = 0;
        for(int d=0;d<dirs.length;d++){
            int rr = r+dirs[d][0];
            int cc = c+dirs[d][1];
            if(rr >=0 && cc >=0 && rr<grid.length && cc<grid[0].length && grid[rr][cc] == 1){
                numEnclavesUtil(grid, rr, cc);
            }
        }
    }
    public int numEnclaves(int[][] grid) {
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                // only travel at boundaries and mark 1's
                if((i == 0 || i == grid.length-1 || j == 0 || j == grid[0].length-1) && (grid[i][j] == 1)){
                    numEnclavesUtil(grid,i,j);
                }
            }
        }
        int enclaves = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j] == 1){
                    enclaves++;
                }
            }
        }
        return enclaves;
    }

    // leetcode 994. https://leetcode.com/problems/rotting-oranges/
    public class opair{
        int r;
        int c;
        int t;
        public opair(int r,int c,int t){
            this.r = r;
            this.c = c;
            this.t = t;
        }
    }
    public int orangesRotting(int[][] grid) {
        int oranges = 0;
        Queue<opair> qu = new LinkedList<>();
        // 1. travel on grid add rotten oranges in queue and count oranges
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j] == 2){
                    qu.add(new opair(i,j,0));
                }
                if(grid[i][j] == 1 || grid[i][j] == 2){
                    oranges++;
                }
            }
        }
        // 2. apply BFS and find remaining orange count 
        int time = 0;
        while(qu.size()  > 0){
            opair rem = qu.remove();
            if(grid[rem.r][rem.c] == -2){
                continue;
                // already marked
            }
            grid[rem.r][rem.c] = -2;
            oranges--;
            time = rem.t;
            for(int d=0;d<dirs.length;d++){
                int rr = rem.r+dirs[d][0];
                int cc = rem.c+dirs[d][1];
                if(rr >=0 && cc>=0 && rr<grid.length && cc<grid[0].length && grid[rr][cc] == 1){
                    qu.add(new opair(rr,cc,rem.t+1));
                }
            }
        }
        return oranges == 0 ? time : -1;
    }

    // https://www.lintcode.com/problem/663/ (DIY) Walls and Gates

    // 815. Bus Routes
    private void makeBusRoute(int[][] routes,HashMap<Integer,ArrayList<Integer>> map){
        for(int r=0;r<routes.length;r++) { //r=>busNumber
            for(int c=0;c<routes[r].length;c++){
                int busStand = routes[r][c];
                if(map.containsKey(busStand) == true){
                    map.get(busStand).add(r);
                }else{
                    ArrayList<Integer> busNo = new ArrayList<>();
                    busNo.add(r);
                    map.put(busStand,busNo);
                }
            }
        }
    }
    public int numBusesToDestination(int[][] routes, int source, int target) {
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
        makeBusRoute(routes,map);

        HashSet<Integer> visRoute  = new HashSet<>();
        HashSet<Integer> visStand  = new HashSet<>();
        Queue<Integer> qu = new LinkedList<>();

        qu.add(source);
        int level = -1;
        while(qu.size() > 0){
            int sz = qu.size();
            level++;
            while(sz-->0){
                int rem = qu.remove();
                if(rem == target) return level;
                for(int busNum : map.get(rem)){
                    if(visRoute.contains(busNum) == true){
                        continue;
                    }else{
                        for(int busStand:routes[busNum]){
                            if(visStand.contains(busStand) == false){
                                visStand.add(busStand);
                                qu.add(busStand);
                            }
                        }
                        visRoute.add(busNum);
                    }
                }
            }
        }
        return -1;
    }


    // Minimum Cost To Connect All Cities
    public static class primsHelper implements Comparable<primsHelper>{
        int vtx;
        int wt;
        public primsHelper(int vtx,int wt){
            this.vtx = vtx;
            this.wt = wt;
        }

        public int compareTo(primsHelper o){
            return this.wt - o.wt;
        }
    }
    public static void prims(ArrayList<ArrayList<Edge>> graph){
        PriorityQueue<primsHelper> pq = new PriorityQueue<>();
        pq.add(new primsHelper(0,0));
        
        int n = graph.size();
        boolean[] vis = new boolean[n];
        int ans = 0;
        while(pq.size() > 0){
            // 1. removal
            primsHelper rem = pq.remove();
            // 2. mark
            if(vis[rem.vtx] == true) continue;
            vis[rem.vtx] = true;
            ans = ans + rem.wt;
            // 3. work
            //4. add nbr
            for(Edge e:graph.get(rem.vtx)){
                int nbr = e.nbr;
                if(vis[nbr] == false){
                    pq.add(new primsHelper(nbr,e.wt));
                }
            }
        }
        System.out.println(ans);
    }
    


    // https://leetcode.com/problems/01-matrix/
    // 542. 01 Matrix
    public class pair01{
        int x, y;
        public pair01(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    public int[][] updateMatrix(int[][] mat) {
        Queue<pair01> qu = new LinkedList<>();
        boolean[][] vis = new boolean[mat.length][mat[0].length];
        // 1. Travel and fill qu with init 0's
        for (int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[0].length;j++){
                if(mat[i][j] == 0){
                    qu.add(new pair01(i,j));
                    vis[i][j] = true;
                }
            }
        }
        // 2.RUN BFS and mark level in given matrix and use a visited matrix
        int level = -1;
        while(qu.size() > 0){
            level++;
            int sz = qu.size();
            while(sz-->0){
                pair01 rem = qu.remove();
                mat[rem.x][rem.y] = level;
                for(int d=0;d<dirs.length;d++){
                    int r = rem.x+dirs[d][0];
                    int c = rem.y+dirs[d][1];
                    if(r>=0 && r<mat.length && c>=0 && c<mat[0].length && vis[r][c] == false){
                        qu.add(new pair01(r,c));
                        vis[r][c] = true;
                    }
                }
            }
        }
        return mat;
    }

    // 1162. As Far from Land as Possible
    public int maxDistance(int[][] grid) {
        Queue<pair01> qu = new LinkedList<>();
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j] == 1){
                    qu.add(new pair01(i,j));
                    grid[i][j] = -1;
                }
            }
        }
        if(qu.size() == 0 || qu.size() == grid.length*grid[0].length) return -1;
        int level = -1;
        while(qu.size() > 0){
            level++;
            int sz = qu.size();
            while(sz-->0){
                pair01 rem = qu.remove();
                grid[rem.x][rem.y] = level;
                for(int d=0;d<dirs.length;d++){
                    int r = rem.x+dirs[d][0];
                    int c = rem.y+dirs[d][1];
                    if(r>=0 && r<grid.length && c>=0 && c<grid[0].length && grid[r][c] == 0){
                        qu.add(new pair01(r,c));
                        grid[r][c] = -1;
                    }
                }
            }
        }
        return level;
    }

    // Mother Vertex
    // https://practice.geeksforgeeks.org/problems/mother-vertex/1
    private static void dfsForStack(ArrayList<ArrayList<Integer>> graph,int src,boolean[] vis,Stack<Integer> st){
        vis[src] = true;
        for(int nbr:graph.get(src)){
            if(vis[nbr] == false){
                dfsForStack(graph,nbr,vis,st);
            }
        }
        st.push(src);
    }
    private static int count = 0;
    private static void dfsForCount(ArrayList<ArrayList<Integer>> graph,int src,boolean[] vis){
        vis[src] = true;
        count++;
        for(int nbr:graph.get(src)){
            if(vis[nbr] == false){
                dfsForCount(graph,nbr,vis);
            }
        }
    }
    public static int findMotherVertex(int N, ArrayList<ArrayList<Integer>> graph){
        boolean[] vis = new boolean[N];
        Stack<Integer> st = new Stack<>();
        for(int v=0;v<N;v++){
            if(vis[v] == false){
                dfsForStack(graph,v,vis,st);
            }
        }
        int top = st.peek();
        count = 0;
        vis = new boolean[N];
        dfsForCount(graph,top,vis);
        // top + 1 is of portal as we are taking vtx-1 in graph
        return count == N ? top + 1 : -1;	
    }

    // 547. Number of Provinces
    // Similar to kosaraju's Algorithm DIY

    // 934. Shortest Bridge
    public void dfsShortestBridge(int[][] grid,int r,int c,Queue<pair01> qu){
        grid[r][c] = -1;
        qu.add(new pair01(r,c));
        for(int d=0;d<dirs.length;d++){
            int rr = r+dirs[d][0];
            int cc = c+dirs[d][1];
            if(rr>=0 && cc>=0 && rr<grid.length && cc<grid[0].length && grid[rr][cc] == 1){
                dfsShortestBridge(grid,rr,cc,qu);
            }
        }
    }
    
    public int shortestBridge(int[][] grid) {
        // 1. Add all 1's in queue using dfs from 1st 1 encountered
        Queue<pair01> qu = new LinkedList<>();
        loop1 : for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j] == 1){
                    dfsShortestBridge(grid,i,j,qu);
                    break loop1;
                }
            }
        }
        // 2. Apply BFS on queue and return level when one is encountered
        int level = -1;
        while(qu.size() > 0){
            int sz = qu.size();
            level++;
            while(sz-->0){
                pair01 rem = qu.remove();
                for(int d=0;d<dirs.length;d++){
                    int rr = rem.x+dirs[d][0];
                    int cc = rem.y+dirs[d][1];
                    if(rr>=0 && cc>=0 && rr<grid.length && cc<grid[0].length && grid[rr][cc] != -1){
                        if(grid[rr][cc] == 1){
                            return level;
                        }
                        grid[rr][cc] = -1;
                        qu.add(new pair01(rr,cc));
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args){

    }
}