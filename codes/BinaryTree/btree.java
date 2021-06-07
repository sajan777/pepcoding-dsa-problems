import java.util.*;

public class btree {

    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
            this.left = this.right = null;
        }
        
        public Node(int data,Node left,Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    public static class TreeNode {
        TreeNode left, right;
        int data;
      
        TreeNode(int d) {
          data = d;
          left = right = null;
        }
      
      }

    public static class Pair {
        int state;
        Node node;

        public Pair(Node node,int state){
            this.node = node;
            this.state = state;
        }
    }
    
    public static Node construct(Integer[] arr){
        Node root = new Node(arr[0]);
        Stack<Pair> st = new Stack<>();
        st.push(new Pair(root,0));
        int indx = 0;
        while(st.size() > 0){
            Pair p = st.peek();
            if(p.state == 0){
                // left child
                indx++;
                if(arr[indx] != null){

                    Node nn = new Node(arr[indx]);
                    p.node.left = nn;
                    st.push(new Pair(nn,0));
                }
                p.state++;
            }else if(p.state == 1){
                // right child
                indx++;
                if(arr[indx] != null){
                    Node nn = new Node(arr[indx]);
                    p.node.right = nn;
                    st.push(new Pair(nn,0));
                }
                p.state++;
            }else{
                // pop out node Pair from stack
                st.pop();
            }
        }

        return root;
    }

    public static void display(Node node){
        if(node == null){
            return;
        }
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
            return  0;
        }
        // my approach
        // int sz = 0;
        // sz += size(node.left);
        // sz += size(node.right);
        // return sz+1;

        // shreesh
        int lsz = size(node.left);
        int rsz = size(node.right);
        return lsz+rsz+1;

    }

    public static int sum(Node node) {
        // write your code here
        if(node == null){
            return  0;
        }
        // shreesh appraoacj
        int lsum = sum(node.left);
        int rsum = sum(node.right);
        return lsum+rsum+node.data;

        // my apporach
        // int tsum = 0;
        // tsum += sum(node.left);
        // tsum += sum(node.right);
        // return tsum+node.data;
    }

    // call variations check shreesh code!!!!
    public static int sum1(Node node) {
        // if(node == null) return 0; // root == null

        if(node.left != null && node.right != null) {
            int lsum = sum1(node.left);
            int rsum = sum1(node.right);
            return lsum + rsum + node.data;
        } else if(node.left != null) {
            int lsum = sum1(node.left);
            return lsum + node.data;
        } else if(node.right != null) {
            int rsum = sum1(node.right);
            return rsum + node.data;
        } else {
            return node.data;
        }
    }

    public static int sum2(Node node) {

        int sum = 0;
        if(node.left != null) {
            sum += sum2(node.left);
        }

        if(node.right != null) {
            sum += sum2(node.right);
        }
        return sum + node.data;
    }
    
    public static int max(Node node) {
        if(node == null){
            return Integer.MIN_VALUE;
            // my app
            // return 0;
        }
        // shreesh approach
        int lmaxVal = max(node.left);
        int rmaxVal = max(node.right);
        return Math.max(Math.max(rmaxVal,lmaxVal),node.data);

        // my approach
        // int maxVal = Integer.MIN_VALUE;
        // maxVal = Math.max(maxVal,max(node.left));
        // maxVal = Math.max(maxVal,max(node.right));
        // return Math.max(maxVal,node.data);
    }

    public static int min(Node node){
        if(node == null){
            return Integer.MAX_VALUE;
        }
        int lmin = min(node.left);
        int rmin = min(node.right);
        return Math.min(node.data,Math.min(lmin,rmin));
    }

    public static int height(Node node) {
        // shreesh approach
        if(node == null){
            return -1;
        }
        int lh = height(node.left);
        int rh = height(node.right);
        return Math.max(lh,rh)+1;

        // my approach
        // if(node == null){
        //     return -1;
        // }
        // int ht = -1;
        // ht = Math.max(height(node.left),height(node.right));
        // return ht+1;
    }

    // Traversal
    // Pre Area Before the calls
    public static void preOrder(Node node){

        if(node == null){
            return;
        }
        System.out.print(node.data +" ");
        preOrder(node.left);
        preOrder(node.right);
    }
    
    // Post Area After the calls
    public static void postOrder(Node node){

        if(node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data +" ");
    }
    
    // In order Area b/w the calls
    public static void inOrder(Node node){

        if(node == null){
            return;
        }
        inOrder(node.left);
        System.out.print(node.data +" ");
        inOrder(node.right);
    }

    // Level Order Traversal
    public static void levelOrder(Node node) {
        if(node == null) return;
        Queue<Node> qu = new ArrayDeque<>();
        qu.add(node);
        while(qu.size() > 0){
            int sz = qu.size();
            while(sz-->0){
                Node rem = qu.remove();
                System.out.print(rem.data+" ");
                if(rem.left != null){
                    qu.add(rem.left);
                }
                if(rem.right != null){
                    qu.add(rem.right);
                }
            }
            System.out.println();
        }
    }

    // iterativePrePostInTravel
    public static void iterativePrePostInTraversal(Node node) {
        if(node == null) return;
        Stack<Pair> st = new Stack<>();
        ArrayList<Integer> pre = new ArrayList<>();
        ArrayList<Integer> in = new ArrayList<>();
        ArrayList<Integer> post = new ArrayList<>();
        st.push(new Pair(node,0));
        
        while(st.size() > 0){
            Pair p = st.peek();
            if(p.state == 0){
                // ADd to pre
                pre.add(p.node.data);
                // increase State
                p.state++;
                // check left child
                if(p.node.left != null){
                    st.push(new Pair(p.node.left,0));
                }
            }else if(p.state == 1){
                // Add to In
                in.add(p.node.data);
                // increase state
                p.state++;
                // check for right child
                if(p.node.right != null){
                    st.push(new Pair(p.node.right,0));
                }
            }else{
                // add to post
                post.add(p.node.data);
                // pop
                st.pop();
            }
        }
        for(Integer p:pre){
            System.out.print(p+" ");
        }
        System.out.println();
        
        for(Integer p:in){
            System.out.print(p+" ");
        }
        System.out.println();
        
        for(Integer p:post){
            System.out.print(p+" ");
        }
    }

    // Find 
    public static boolean find(Node node, int data){

        // shreesh approach
        if(node == null){
            return false;
        }

        if(node.data == data) return true;

        // boolean res = false;
        // res = find(node.left,data);
        // res = res || find(node.right,data);

        // return res;

        boolean lres = find(node.left,data);
        if(lres == true){
            return true;
        }
        
        boolean rres = find(node.right,data);
        if(rres == true){
            return true;
        }

        return false;
        
        // my approach
        // if(node.data == data){
        //     return true;
        // }
        
        // if(node.left != null){
        //     boolean lfind = find(node.left,data);
        //     if(lfind == true){
        //         return true;
        //     }
        // }
        // if(node.right != null){
        //     boolean rfind = find(node.right,data);
        //     if(rfind == true){
        //         return true;
        //     }

        // }
        // return false;
    }

    // node To Root Path
    public static ArrayList<Integer> nodeToRootPath(Node node, int data){

        // shreesh approach
        if(node == null) return new ArrayList<Integer>();
        if(node.data == data){
            ArrayList<Integer> base = new ArrayList<>();
            base.add(node.data);
            return base;
        }
        
        ArrayList<Integer> lres = nodeToRootPath(node.left, data);
        if(lres.size() > 0){
            lres.add(node.data);
            return lres;
        }
        ArrayList<Integer> rres = nodeToRootPath(node.right, data);
        if(rres.size() > 0){
            rres.add(node.data);
            return rres;
        }

        return new ArrayList<Integer>();

        // my approach
        // if(node == null) return new ArrayList<Integer>();
        // if(node.data == data){
        //     ArrayList<Integer> base = new ArrayList<>();
        //     base.add(node.data);
        //     return base;
        // }
        // ArrayList<Integer> ans = new ArrayList<>();
        // if(node.left != null){
        //     ArrayList<Integer> ln2rp = nodeToRootPath(node.left, data);
        //     if(ln2rp.size() > 0){
        //         for(Integer val:ln2rp){
        //             ans.add(val);
        //         }
        //         ans.add(node.data);
        //     }
        // }
        
        // if(node.right != null){
        //     ArrayList<Integer> rn2rp = nodeToRootPath(node.right, data);
        //     if(rn2rp.size() > 0){
        //         for(Integer val:rn2rp){
        //             ans.add(val);
        //         }
        //         ans.add(node.data);
        //     }
        // }

        // return ans;

    }

    // Print K Level Down
    public static void printKLevelsDown(Node node, int k){
        if(node == null || k < 0) return;
        if(k == 0){
            System.out.println(node.data);
            return;
        }
        printKLevelsDown(node.left, k-1);
        printKLevelsDown(node.right, k-1);
    }
    

    
    // node to Root Path (node terms)
    public static ArrayList<Node> nodeToRootPathNode(Node node, int data){

        if(node == null) return new ArrayList<Node>();
        if(node.data == data){
            ArrayList<Node> base = new ArrayList<>();
            base.add(node);
            return base;
        }
        
        ArrayList<Node> lres = nodeToRootPathNode(node.left, data);
        if(lres.size() > 0){
            lres.add(node);
            return lres;
        }
        ArrayList<Node> rres = nodeToRootPathNode(node.right, data);
        if(rres.size() > 0){
            rres.add(node);
            return rres;
        }

        return new ArrayList<Node>();

    }
    
    // kDown for printKNodesSoFar
    public static void printKDown(Node node,Node blockage,int k){
        if(node == null || node == blockage) return;

        if(k == 0){
            System.out.println(node.data);
            return;
        }
        printKDown(node.left, blockage, k-1);
        printKDown(node.right, blockage, k-1);
    }   
    
    // k nodes so far
    public static void printKNodesFar(Node root, int data, int k) {
        // write your code here
        ArrayList<Node> n2rp = nodeToRootPathNode(root, data);
        
        Node blockage = null;
        for(int i=0;i<n2rp.size() && k>=0;i++){
            Node node = n2rp.get(i);
            printKDown(node,blockage,k);
            k--;
            blockage = node;
        }
    }
    
    // path to leaf from root
    public static void pathToLeafFromRoot(Node node, String path, int sum, int lo, int hi){
        if(node == null) return;

        if(node.left != null && node.right != null){
            pathToLeafFromRoot(node.left, path+node.data+" ", sum+node.data, lo, hi);
            pathToLeafFromRoot(node.right, path+node.data+" ", sum+node.data, lo, hi);
        }else if(node.left != null){
            pathToLeafFromRoot(node.left, path+node.data+" ", sum+node.data, lo, hi);

        }else if(node.right != null){
            pathToLeafFromRoot(node.right, path+node.data+" ", sum+node.data, lo, hi);
        }else{
            // leaf
            sum += node.data;
            path += node.data;
            if(sum >= lo && sum<= hi){
                System.out.println(path);
            }
            // if(sum+node.data >= lo && sum+node.data <= hi){
            //     System.out.println(path+node.data+" ");
            // }
        }
    }
    
    // left cloned  tree
    public static Node createLeftCloneTree(Node node){
        
        if(node == null) return null;

        Node lcn = createLeftCloneTree(node.left);
        Node rcn = createLeftCloneTree(node.right);

        Node nn = new Node(node.data,lcn,null);
        node.left = nn;
        node.right = rcn;
        return node;

    }

    // Left Clone Tree to Normal Tree
    public static Node transBackFromLeftClonedTree(Node node){

        if(node == null) return null;

        Node lcn = transBackFromLeftClonedTree(node.left.left);
        Node rcn = transBackFromLeftClonedTree(node.right);

        node.left = lcn;
        node.right = rcn;
        return node;
    }
    
    // Parent Single Child Nodesd 
    // My app Method -0 (this might fail in one test case !!!!!!!!!)
    public static void printSingleChildNodes(Node node, Node parent){
        if(node == null) return;

        if(node.left !=null && node.right == null){
            System.out.println(node.left.data);
        }else if(node.right != null && node.left == null){
            System.out.println(node.right.data);
        }


        printSingleChildNodes(node.left, parent);
        printSingleChildNodes(node.right, parent);

    }
    // Shreesh Arrpaochj Method -0
    public static void printSingleChildNodes2(Node node, Node parent){
        if(node == null) return;

        if(parent != null && parent.left == node && parent.right == null){
            System.out.println(node.data);
        }
        if(parent != null && parent.right == node && parent.left == null){
            System.out.println(node.data);
        }

        printSingleChildNodes2(node.left, node);
        printSingleChildNodes2(node.right, node);

    }

    // REmove LEaVES
    // will fail if root is leaf
    public static void removeLeavesHelper(Node node,Node parent){
        if(node == null) return;

        if(parent != null && node.left == null && node.right == null && parent.left == node){
            parent.left = null;
        }
        if(parent != null && node.left == null && node.right == null && parent.right == node){
            parent.right = null;
        }
        removeLeavesHelper(node.left, node);
        removeLeavesHelper(node.right, node);
    }
    // w/o return type!!!!   
    public static Node removeLeaves(Node node){
        // write your code here
        Node temp = node;
        removeLeavesHelper(node,null);
        return temp;
    }
    // return type method
    public static Node removeLeaves2(Node node){
        // write your code here
        if(node == null) return null;

        if(node.left != null && node.right != null){
            Node lres = removeLeaves2(node.left);
            Node rres = removeLeaves2(node.right);
            node.left = lres;
            node.right = rres;
        }else if(node.left != null){
            Node lres = removeLeaves2(node.left);
            node.left = lres;

        }else if(node.right != null){
            Node rres = removeLeaves2(node.right);
            node.right = rres;
        }else{
            // leaf
            // shreesh approach
            node = null;
            
            // my approach
            // node.left = null;
            // node.right = null;
            // return null;
        }

        return node;
    }


    // tilt a binary tree
    static int tilt = 0;
    public static int tilt(Node node){
      // write your code here to set the tilt data member
        if(node == null) return 0;  

        int lsm = tilt(node.left);
        int rsm = tilt(node.right);

        tilt += Math.abs(lsm-rsm);
        return lsm+rsm+node.data;
    }

    // shreesh approach
    public static int sumForTilt(Node node){
        if(node == null) return 0;  

        int lsm = sumForTilt(node.left);
        int rsm = sumForTilt(node.right);

        tilt += Math.abs(lsm-rsm);
        return lsm+rsm+node.data;
    }
    public static int tilt2(Node node){
      // write your code here to set the tilt data member
        tilt = 0; // init static var as 0 for number of test cases for leetcode
        sumForTilt(node);
        return tilt;
    }



    // Diameter of BinaryTree
    static int diameter = 0;
    public int diameterHelper(TreeNode root){
        if(root == null) return -1;
        
        int lht = diameterHelper(root.left);
        int rht = diameterHelper(root.right);
        diameter = Math.max(diameter,lht+rht+2);
        
        return Math.max(lht,rht)+1;
    }
    
    static class DiaPair {
        int dia;
        int ht;

        public DiaPair() {
            this.dia = 0;
            this.ht = -1;
        }
    }

    public DiaPair diameter2(TreeNode root) {
        if(root == null) return new DiaPair();

        DiaPair lres = diameter2(root.left);
        DiaPair rres = diameter2(root.right);

        DiaPair mres = new DiaPair();

        mres.ht = Math.max(lres.ht, rres.ht) + 1;
        mres.dia = Math.max(lres.ht + rres.ht + 2, Math.max(lres.dia, rres.dia));
        return mres;
    }


    public int diameterOfBinaryTree(TreeNode root) {

        // DiaPair res = diameter2(root);
        // return res.dia;

        //Write your code here
        diameter = 0;
        diameterHelper(root);
        return diameter;
    }

    // BST  0(n2) Approach
    public static boolean isBST1(Node node){
        if(node == null) return true;

        // self check
        int lmax = max(node.left);
        int rmin = min(node.right);
        if(lmax > node.data || rmin < node.data){
            return false;
        }
        // left + right check
        boolean res = isBST1(node.left) && isBST1(node.right);

        return res;
    }

    public static class BSTPair {
        int min;
        int max;
        boolean isBST;
        int size;

        public BSTPair(){
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            isBST = true;
            size = 0;
        }
    }

    // 0(n)
    public static BSTPair isBST2(Node node){
        if(node == null) return new BSTPair();
        BSTPair lres = isBST2(node.left);
        BSTPair rres = isBST2(node.right);

        boolean status = lres.max < node.data && rres.min > node.data;
        BSTPair mres = new BSTPair();
        mres.min = Math.min(node.data,Math.min(lres.min,rres.min));
        mres.max = Math.max(node.data,Math.max(lres.max,rres.max));
        mres.isBST = lres.isBST && rres.isBST && status;

        return mres;
    }
    // System.out.println(isBST2(root).isBST);

    // isBalanced 0(n2)
    public static boolean isBalanced1(Node node){
        if(node == null) return true;

        // self check
        int lht = height(node.left);
        int rht = height(node.right);
        int balancedht = Math.abs(lht-rht);
        if(balancedht > 1 ){
            return false;
        }
        // left + right check
        boolean res = isBalanced1(node.left) && isBalanced1(node.right);
        return res;
    }
    // 0(n)
    static boolean isBal = true; 
    public static int isBalanced2(Node node){
        if(node == null) return 0;

        int lht = isBalanced2(node.left);
        int rht = isBalanced2(node.right);
        int balancedht = Math.abs(lht-rht);
        if(balancedht > 1 ){
            isBal = false;
        }

        return Math.max(lht,rht)+1;
    }
    
    public static class isBalancedPair{
        int ht;
        boolean isBalanced;

        public isBalancedPair(){
            this.ht = -1;
            this.isBalanced = true;
        }
    }
    // 0(n)
    public static isBalancedPair isBalanced3(Node node){
        if(node == null) return new isBalancedPair();

        isBalancedPair lht = isBalanced3(node.left);
        isBalancedPair rht = isBalanced3(node.right);
        
        // shreesh
        boolean factor = Math.abs(lht.ht - rht.ht) >= 1;
        isBalancedPair mres = new isBalancedPair();
        mres.ht = Math.max(lht.ht,rht.ht)+1;
        mres.isBalanced = factor && lht.isBalanced && rht.isBalanced;
        
        // my app
        // int balancedht = Math.abs(lht.ht - rht.ht);
        // isBalancedPair mres = new isBalancedPair();
        // mres.ht = Math.max(lht.ht,rht.ht)+1;
        // if(balancedht > 1 || lht.isBalanced == false || rht.isBalanced == false){
        //     mres.isBalanced = false;
        // }

        return mres;
    }


    // largestBST
    // 0(n)
    static int sz = 0;
    static Node bstNode = null;
    public static BSTPair largestBSTSubtree(Node node){
        if(node == null) return new BSTPair();

        BSTPair lres = largestBSTSubtree(node.left);
        BSTPair rres = largestBSTSubtree(node.right);


        boolean status = lres.max < node.data && rres.min > node.data;
        BSTPair mres = new BSTPair();
        mres.min = Math.min(node.data,Math.min(lres.min,rres.min));
        mres.max = Math.max(node.data,Math.max(lres.max,rres.max));
        mres.isBST = lres.isBST && rres.isBST && status;
        mres.size = lres.size + rres.size + 1;

        if(mres.isBST == true && mres.size > sz){
            bstNode = node;
            sz = mres.size;
        }

        return mres;
    }
    // largestBSTSubtree(root);
    // System.out.println(bstNode.data+"@"+sz);

    static ArrayList<Integer> pre;
    static ArrayList<Integer> in;
    static ArrayList<Integer> post;
    public static void traversal(Node root){
        if(root == null) return;

        pre.add(root.data);
        traversal(root.left);
        in.add(root.data);
        traversal(root.right);
        post.add(root.data);
    }


    public static  void fun() {
        Integer[] arr = {50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
        Node root = construct(arr);
        // display(root);
        // System.out.println(size(root));
        // System.out.println(sum(root));
        // System.out.println(max(root));
        // System.out.println(height(root));
        // System.out.println("Pre Order:- >");
        // preOrder(root);
        // System.out.println();
        // System.out.println("Post Order:- >");
        // postOrder(root);
        // System.out.println();
        // System.out.println("In Order:-  >");
        // inOrder(root);
        // System.out.println();

        // pre = new ArrayList<>();
        // in = new ArrayList<>();
        // post = new ArrayList<>();
        // traversal(root);
        // System.out.println(pre);
        // System.out.println(in);
        // System.out.println(post);

        // levelOrder(root);
        // System.out.println(find(root,112));
        // System.out.println(nodeToRootPath(root, 70));
        // printKLevelsDown(root,3);
        pathToLeafFromRoot(root,"",0,100,250);
    }





    public static void main(String[] args) {
        fun();
    }
}