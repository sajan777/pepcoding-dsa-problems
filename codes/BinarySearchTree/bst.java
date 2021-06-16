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


    // replace with sum of larger values
    static int sum = 0;
    public static void rwsol(Node node){
        if(node == null) return;
        
        rwsol(node.right);
        // work in InArea
        int data = node.data;  
        node.data = sum;
        sum += data;
        rwsol(node.left);
    }


    // abandoned code 
    // public static Node findForLCA(Node node, int data){
        //     // write your code here
        //         if(node == null) return null;
        //         if(node.data == data){
    //             return node;
    
    //         }else if(data < node.data){
    //             // go to left
    //             Node lres = find(node.left,data);
    //             if(lres.data == data){
        //                 return node;
    //             }
    //         }else{
        //             // data > node.data go to right
        //             Node rres = find(node.right,data);
        //             if(rres.data == data){
            //                 return node;
    //             }
    
    //         }
    //         return null;
    // }
    
    // LCA
    // Node to root path for lca Very bad for LCA dont use this
    public static ArrayList<Integer> n2rp(Node node,int data){
        if(node == null) return new ArrayList<Integer>();
        if(node.data == data){
            ArrayList<Integer> base = new ArrayList<>();
            base.add(node.data);
            return base;
        }else if(data < node.data){
            ArrayList<Integer> llres = n2rp(node.left,data);
            if(llres.size() > 0){
                llres.add(node.data);
                return llres;
            }
        }else{
            ArrayList<Integer> rres = n2rp(node.right,data);
            if(rres.size() > 0){
                rres.add(node.data);
                return rres;
            }
        }
        return new ArrayList<>();
    }

    public static int lca(Node node, int d1, int d2) {
        if(d1 > node.data && d2 > node.data){
            // on the right side;
            return lca(node.right, d1, d2);
        }else if(d1 < node.data && d2 < node.data){
            // on the left side;
            return lca(node.left,d1,d2);
        }else{
            // this is it d1 in left and d2 in right
            return node.data;
        }
    }

    // Very bad approach for BST o(n) never do this
    // public static int lca2(Node node, int d1, int d2) {
    
    //     ArrayList<Integer> l1 = n2rp(node,d1);
    //     ArrayList<Integer> l2 = n2rp(node,d2);

    //     int res = -1;
    //     int i = l1.size()-1;
    //     int j = l2.size()-1;
    //     while(i>=0 && j>=0 && l1.get(i) == l2.get(j)){
    //         res = l1.get(i);
    //         i--;
    //         j--;
    //     }
    //     return res;
    // }

    // print in range
    public static void pir(Node node, int d1, int d2) {
        if(node == null) return;
        
        if(d1 < node.data && d2 < node.data){
            // left side
            pir(node.left,d1,d2);
        }else if(d1 > node.data && d2 > node.data){
            // right side
            pir(node.right,d1,d2);
        }else{
            // answer
            // as we have to print inInorder we go to left first then print and then right side
            pir(node.left,d1,d2);
            System.out.println(node.data);
            pir(node.right,d1,d2);
        }
    }

    // Target Sum Pair in BST
    // method 1 0(nh)+0(h) h-height
    public static void printTargetSumPair1(Node node,Node root,int  target) {
        if(node == null) return;
        int n1 = node.data;
        int n2 = target-n1;
        printTargetSumPair1(node.left, root, target);
        //  in ORDER
        if(n1 < n2 && find(root,n2) == true){
            System.out.println(n1+" "+n2);
        }
        printTargetSumPair1(node.right, root, target);
    }
    // method 2 0(n)+0(n)
    public static void inOrderFiller(Node node,ArrayList<Integer> list){
        if(node == null) return;
        inOrderFiller(node.left, list);
        list.add(node.data);
        inOrderFiller(node.right, list);
    }
    public static void printTargetSumPair2(Node node,int  target) {
        ArrayList<Integer> inorder = new ArrayList<>();
        inOrderFiller(node, inorder);
        int i = 0;
        int j = inorder.size()-1;
        while(i<j){
            int n1 = inorder.get(i);
            int n2 = inorder.get(j);
            if(n1 + n2 > target){
                j--;
            }else if(n1+n2 < target){
                i++;
            }else{
                System.out.println(n1+" "+n2);
                i++;
                j--;
            }
        }
    } 

    
    // method 3 0(n)+0(h) h-height
    // Two stack iterative Approach because we can stop recurstion
    public static class Pair {
        Node node;
        int state;
    
        Pair(Node node, int state) {
          this.node = node;
          this.state = state;
        }
      }

      public static int inorderItr(Stack<Pair> st) {
        while(st.size() > 0) {
            Pair p = st.peek();

            if(p.state == 0) {
                // left child
                if(p.node.left != null) {
                    st.push(new Pair(p.node.left, 0));
                }
                p.state++;
            } else if(p.state == 1) {
                // right child
                if(p.node.right != null) {
                    st.push(new Pair(p.node.right, 0));
                }
                p.state++;
                return p.node.data;
            } else {
                // pop
                st.pop();
            }
        }
        return -1;
    }

    public static int revInorderItr(Stack<Pair> st) {
        while(st.size() > 0) {
            Pair p = st.peek();

            if(p.state == 0) {
                // right child
                if(p.node.right != null) {
                    st.push(new Pair(p.node.right, 0));
                }
                p.state++;
            } else if(p.state == 1) {
                // left child
                if(p.node.left != null) {
                    st.push(new Pair(p.node.left, 0));
                }
                p.state++;
                return p.node.data;
            } else {
                // pop
                st.pop();
            }
        }
        return -1;
    }


    public static void printTargetSumPair3(Node node, int target) {
        Stack<Pair> ls = new Stack<>();
        Stack<Pair> rs = new Stack<>();

        ls.push(new Pair(node, 0));
        rs.push(new Pair(node, 0));


        int left = inorderItr(ls);
        int right = revInorderItr(rs);

        while(left < right) {
            int sum = left + right;
            if(sum > target) {
                right = revInorderItr(rs);
            } else if(sum < target) {
                left = inorderItr(ls);
            } else {
                System.out.println(left + " " + right);
                left = inorderItr(ls);
                right = revInorderItr(rs);
            }
        }
    }


    // remove a node BST
    public static Node remove(Node node, int data) {
        // write your code here 
        if(node == null) return null;
        if(data < node.data){
            node.left = remove(node.left, data);
            return node;
        }else if(data > node.data){
            node.right = remove(node.right, data);
            return node;
        }else{
            // 1 case
            // leaf node
            if(node.left == null && node.right == null){
                return null;
            // node has only left child
            }else if(node.left == null){
                return node.right;
            //  node has only right child
            }else if(node.right == null){
                return node.left;
            }else{
                // node has both left and right child
                int lmax = max(node.left);
                node.data = lmax;
                node.left = remove(node.left,lmax);
                return node;
            }
        }
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
