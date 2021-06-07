import java.util.*;

public class bst {
    
    public static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
            this.left = this.right = null;
        }
        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
          }
    } 

    public static Node construct(int[] arr,int lo,int hi){
        if(lo > hi){
            return null;
        }
        int mid = lo + (hi-lo)/2;
        Node nn = new Node(arr[mid]);
        Node lres = construct(arr,lo,mid-1);
        Node rres = construct(arr, mid+1,hi);

        nn.left = lres;
        nn.right = rres;
        return nn;
    }   

    public static void display(Node node) {
        if(node == null) return;
        String str = node.left == null ? " ." : node.left.data +" ";
        str += " <- ["+node.data +"] -> ";
        str += node.right == null ? " ." : node.right.data + " "; 
        
        System.out.println(str);
        display(node.left);
        display(node.right);
    }

    public static int size(Node node) {
        // write your code here
        if(node == null){
            return 0;
        }
        int lres = size(node.left);
        int rres = size(node.right);
        return lres+rres+1;
    }
    
    public static int sum(Node node) {
        // write your code here
        if(node == null){
            return 0;
        }
        int lres = sum(node.left);
        int rres = sum(node.right);
        return lres+rres+node.data;
    }

    // guided traversal for max and min
    public static int max(Node node) {
        // write your code here
        if(node == null){
            return Integer.MIN_VALUE;  //if root is null
        } else if(node.right == null){
            return node.data;
        }else{
            // means there is still right
            return max(node.right);
        }
    }

    public static int min(Node node) {
    // write your code here
        if(node == null){
            return Integer.MAX_VALUE; // if root is null
        }else if(node.left  == null){
            return node.data;
        }else{
            // means there is still left
            return min(node.left);
        }
        
    }

    // o(h)
    public static boolean find(Node node, int data){
    // write your code here
        if(node == null) return false;
        if(node.data == data){
            return true;
            
        }else if(data < node.data){
            // go to left
            boolean lres = find(node.left,data);
            if(lres == true){
                return true;
            }
        }else{
            // data > node.data go to right
            boolean rres = find(node.right,data);
            if(rres == true){
                return true;
            }
            
        }
        return false;
    }
    
    // add to  BST
    public static Node add(Node node, int data) {
        // write your code here
        if(node == null){
            Node nn = new Node(data,null,null);
            return nn;
        } 

        if(data < node.data){
            node.left = add(node.left,data); 
        }else if(data > node.data){
            node.right = add(node.right,data);
        }else{

        }
        // else means dont do anything for equal val can be reomved case only for duplicate data on pep platform
        return node;
    }

    public static void fun () {
        int[] data= {10,20,30,40,50,60,70,80,90};
        Node root = construct(data,0,data.length-1);
        display(root);
    }

    public static void main(String[] args) {
        fun();
    }

}
