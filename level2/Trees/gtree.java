import java.util.*;

class GenericTree{
    private class Node{
        int data;
        ArrayList<Node> children;
    
        public Node(int data){
            this.data = data;
            this.children = new ArrayList<>();
        }
    }
    private Node root = null;
    private int size = 0;

    public Node construct(int[] arr){
        Stack<Node> st = new Stack<>();
        root = new Node(arr[0]);

        return this.root;
    }
    
    public GenericTree(int[] arr){
        construct(arr);
    }

    public int size(){
        return this.size;
    }
    private void display(Node node){
        System.out.print("[ "+node.data+" ] =>");
        for(Node child:node.children){
            System.out.print(child.data);
        }
        System.out.println( );
        for(Node child:node.children){
            display(child);
        }
    }

    public void display(){
        display(this.root);
    }

}
public class gtree {
    
    public static void fun(){
        int[] arr = {10,20,50,-1,60,-1,-1,30,70,-1,80,110,-1,120,-1,-1,90,-1,-1,40,100,-1,-1,-1};
        GenericTree gt = new GenericTree(arr);
        gt.display();
    }
    public static void main(String[] args) {
        fun();
    }
}
