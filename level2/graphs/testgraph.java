import java.util.*;

public class testgraph {
    
    public static class Edge{
        String nbr;
        int wt;
        public Edge(String nbr,int wt){
            this.nbr = nbr;
            this.wt = wt;
        }
    }
    public static void addEdge( HashMap<String,ArrayList<Edge>> graph , String src,String dest,int wt){
        if(graph.containsKey(src)){
            graph.get(src).add(new Edge(dest,wt));
        }else{
            ArrayList<Edge> curr = new ArrayList<>();
            curr.add(new Edge(dest,wt));
            graph.put(src, curr);
        }
    }
    
    
    public static void main(String[] args) {
        HashMap<String,ArrayList<Edge>> graph = new HashMap<>();
        addEdge(graph,"Ajmer","Luckhnow",100);
    }
}
