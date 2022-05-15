import java.util.*;

public class treee {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public class Node{
        Node left,right;
        int data;
        public ArrayList<Node> children;

        public Node(int data){
            this.data = data;
            this.left = this.right = null;
            this.children = new ArrayList<>();
        }
    }

    public class DirectedGraphNode {
         int label;
         ArrayList<DirectedGraphNode> neighbors;
         DirectedGraphNode(int x) { 
            label = x; 
            neighbors = new ArrayList<DirectedGraphNode>(); 
        }
    }

    // 105. Construct Binary Tree from Preorder and Inorder Traversal
    public TreeNode constructPreIn(int[] pre,int[] in,int preSt,int preEnd,int inSt,int inEnd){
        if(preSt > preEnd){
            return null;
        }
        TreeNode root = new TreeNode(pre[preSt]);

        // find presence of root node in order with index 
        int idx = inSt;
        while(in[idx] != pre[preSt]){
            idx++;
        }
        int elCount = idx-inSt;
        root.left = constructPreIn(pre, in, preSt + 1, preSt+elCount, inSt, idx-1);
        root.right = constructPreIn(pre, in, preSt+elCount+1, preEnd, idx+1, inEnd);
        return root;
    }
    
    public TreeNode buildTreePreIn(int[] preorder, int[] inorder) {
        if(preorder.length == 0) return null;  
        return constructPreIn(preorder, inorder, 0,preorder.length-1,0,inorder.length-1);
    }
    
    // 106. Construct Binary Tree from Inorder and Postorder Traversal
    public TreeNode constructInPost(int[] in,int[] post,int inSt,int inEnd,int poSt,int poEnd){
        if(poSt > poEnd){
            return null;
        }
        TreeNode root  = new TreeNode(post[poEnd]);
        int idx = inSt;
        while(in[idx] != post[poEnd]){
            idx++;
        }
        int elCount = idx-inSt;
        root.left = constructInPost(in, post, inSt, idx-1, poSt, poSt+elCount-1);
        root.right = constructInPost(in, post, idx+1, inEnd,poSt+elCount,poEnd-1);

        return root;
    }
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder.length == 0) return null;
        return constructInPost(inorder, postorder,0,inorder.length-1,0,postorder.length-1);
    }

    // 889. Construct Binary Tree from Preorder and Postorder Traversal
    public TreeNode constructPrePost(int[] pre,int[] post,int preSt,int preEnd,int poSt,int poEnd){
        if(preSt == preEnd){
            return new TreeNode(pre[preSt]);
        }
        if(preSt > preEnd) return null;

        TreeNode root = new TreeNode(pre[preSt]);
        int ele = pre[preSt+1];
        int idx = poSt;
        while(post[idx] != ele){
            idx++;
        }
        int elCount = idx-poSt+1;

        root.left = constructPrePost(pre, post, preSt + 1, preSt+elCount, poSt, idx);
        root.right = constructPrePost(pre, post, preSt+elCount+1, preEnd, idx+1, poEnd-1);
        
        return root;
    }
    
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if(pre.length == 0) return null;
        return constructPrePost(pre,post,0,pre.length-1,0,post.length-1);
        
    }
    // Construct tree from Inorder and LevelOrder 
    public Node constructFromInLevel(int[] in ,ArrayList<Integer> level,int inSt,int inEnd){

        if(level.size() == 0) return null;

        Node root = new Node(level.get(0));
        int idx = inSt;
        HashSet<Integer> set = new HashSet<>();
        while(in[idx] != level.get(0)){
            set.add(in[idx]);
            idx++;
        }

        ArrayList<Integer> llvl = new ArrayList<>();
        ArrayList<Integer> rlvl = new ArrayList<>();

        for(int i=1;i<level.size();i++){
            int val = level.get(i);
            if(set.contains(val)){
                llvl.add(val);
            }else{
                rlvl.add(val);
            }
        }

        root.left = constructFromInLevel(in, llvl, inSt, idx-1);
        root.right = constructFromInLevel(in, rlvl, idx+1, inEnd);

        return root;
    }

    Node buildTreeInPre(int inord[], int level[]){

        if(level.length == 0) return null;

        ArrayList<Integer> lvl  = new ArrayList<>();
        for(int val:level){
            lvl.add(val);
        }
        return constructFromInLevel(inord,lvl,0,inord.length);

    }
    // Construct Bst From Inorder Traversal Leetcode 108
    public static TreeNode constructFromInOrderUtil(int[] in,int inSt,int inEnd){

        if(inSt > inEnd){
            return null;
        }
        int mid = inSt + (inEnd - inSt)/2;
        TreeNode root = new TreeNode(in[mid]);
        root.left = constructFromInOrderUtil(in,inSt,mid-1);
        root.right = constructFromInOrderUtil(in,mid+1,inEnd);
        return root;
    }
    public static TreeNode constructFromInOrder(int[] inOrder) {
       if(inOrder.length == 0) return null;
       return constructFromInOrderUtil(inOrder,0,inOrder.length-1);
    }

    //1008. Construct Binary Search Tree from Preorder Traversal
    public static TreeNode bstFromPreorderUtil(int[] pre,int indx,int leftMin,int leftMax){

        return null;

    }
    public static TreeNode bstFromPreorder(int[] pre) {
        if(pre.length == 0) return null;                                                                                            
        return null;
        // return bstFromPreorderUtil(pre,0,Integer.MIN_VALUE,pre[0],pre[0],Integer.MAX_VALUE);
    }
    // construct from Preorder,PostOrder,LevelOrder -> pending

    
    // 968. Binary Tree Cameras Google
    static int cameras = 0;
    // state 0:- Camera Present
    // state  1:- I'm covered
    // State 2 :- Unsafe
    public int minCameraCoverUtil(TreeNode root) {
        if(root == null){
            return 1;
        } 

        int lstate = minCameraCoverUtil(root.left);
        int rstate = minCameraCoverUtil(root.right);

        if(lstate == 1 && rstate == 1){
            return 2;
        }else if(lstate == 2 || rstate == 2){
            cameras++;
            return 0;
        }else{
            return 1;
        }

    }
    public int minCameraCover(TreeNode root) {
        cameras = 0;
        int state = minCameraCoverUtil(root);
        if(state == 2){
            cameras++;
        }
        return cameras;
    }

    //337. House Robber III
    public class rPair{
        int withRob;
        int withoutRob;
        public rPair(int withRob,int withoutRob){
            this.withRob = withRob;
            this.withoutRob = withoutRob;
        }
    }
    public rPair robUtil(TreeNode node){
        if(node == null) return new rPair(0,0);

        rPair left = robUtil(node.left);
        rPair right = robUtil(node.right);

        int a = left.withRob;
        int a_ = right.withRob;
        
        int b = left.withoutRob;
        int b_ = right.withoutRob;
    
        int c = node.val;
        // if robbery Done b+b'+c
        // if not robbbed Max(a+a',a+b',a'+b,b+b')
        // a' = a_ , b' = b_
        int withRob = b+b_+c;
        // int withoutRob = Math.max(Math.max(a+a_,a+b_),Math.max(a_+b,b+b_));
        int withoutRob = Math.max(a,b)+ Math.max(a_,b_); //better 
        return new rPair(withRob,withoutRob);
    }
    public int rob(TreeNode root) {
        rPair res = robUtil(root);
        return Math.max(res.withRob,res.withoutRob);
    }
    // 1372. Longest ZigZag Path in a Binary Tree
    public class zPair{
        int lStart;//if start at left
        int rStart;//if start at right
        int omax;
        public zPair(int lStart,int rStart,int omax){
            this.lStart = lStart;
            this.rStart = rStart;
            this.omax = omax;
        }
    }
    public zPair zigZagUtil(TreeNode root){
        if(root == null) return new zPair(-1,-1,0);

        zPair left = zigZagUtil(root.left);
        zPair right = zigZagUtil(root.right);

        int startAtL = left.rStart + 1;
        int startAtR = right.lStart + 1;
        int omax = Math.max(Math.max(left.omax,right.omax),Math.max(startAtL,startAtR));

        return new zPair(startAtL, startAtR, omax);
    }
    public int longestZigZag(TreeNode root) {
        zPair ans = zigZagUtil(root);
        return ans.omax;
    }
    // 98. Validate Binary Search Tree
    
    // 1. Pair method
    public class bstPair{
        Long maxVal;
        Long minVal;
        boolean isBST;

        public bstPair(){
            this.isBST = true;
            this.maxVal = Long.MIN_VALUE;
            this.minVal = Long.MAX_VALUE;
        }
    }
    public bstPair isValidBSTUtil(TreeNode root){
        if(root == null) return new bstPair();

        bstPair lres = isValidBSTUtil(root.left);
        bstPair rres = isValidBSTUtil(root.right);
        boolean status = lres.maxVal < root.val && rres.minVal > root.val;
        bstPair myres = new bstPair();
        myres.minVal = Math.min(root.val,Math.min(lres.minVal,rres.minVal));
        myres.maxVal = Math.max(root.val,Math.max(lres.maxVal,rres.maxVal));        
        myres.isBST = lres.isBST && rres.isBST && status;
        return myres;
    }
    public boolean isValidBST2(TreeNode root) {
        if(root.left == null && root.right == null) return true;
        bstPair res = isValidBSTUtil(root);
        return res.isBST;
    }

    // Is valid BST 2 pointer method
    TreeNode prev1 = null;
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        
        boolean lres = isValidBST(root.left);
        if(lres == false) return false;
        // Inside inorder
        if(prev1 != null && prev1.val >= root.val) return false;
        prev1 = root;

        boolean rres = isValidBST(root.right);
        if(rres == false) return false;

        return true;
    }

    // 99. Recover Binary Search Tree
    // pointers[0]->prev
    // pointers[1]->curr
    // pointers[2]->a
    // pointers[3]->b
    // no need for array we can use 4 static variables
    public void recoverTreeUtil(TreeNode root,TreeNode[] pointers){
        if(root == null) return;
        recoverTreeUtil(root.left,pointers);
        if(pointers[0] == null){
            // prev == null
            pointers[0] = root;
        }else{
            // prev != null
            pointers[1] = root;
            if(pointers[0].val > pointers[1].val){
                // occurence
                if(pointers[3] == null){
                    // b == null
                    // first encounter
                    pointers[2] = pointers[0]; // a = prev
                    pointers[3] = pointers[1]; // b = curr
                }else{
                    // 2nd encounter
                    pointers[3] = pointers[1]; // b = curr
                }
            }
            // move prev and curr
            // prev = curr
            pointers[0] = pointers[1];
        }
        recoverTreeUtil(root.right,pointers);

    } 
    public void recoverTree(TreeNode root) {
        TreeNode[] pointers = new TreeNode[4];
        recoverTreeUtil(root, pointers);
        // swap values of a and b
        int temp = pointers[2].val;
        pointers[2].val = pointers[3].val;
        pointers[3].val = temp;
    }

    // Convert Level Order Traversal to BST (GFG)
    public class LPair{
        Node parent;
        int lrange; //left range
        int rrange; // right range
        
        public LPair(Node parent,int lrange,int rrange){
            this.parent = parent;
            this.lrange = lrange;
            this.rrange = rrange;
        }

    }
    public Node constructBST(int[] arr){
        Queue<LPair> qu = new LinkedList<>();
        qu.add(new LPair(null, Integer.MIN_VALUE, Integer.MAX_VALUE));
        Node root = null;
        for(int i = 0;i<arr.length;i++){
            Node nn = new Node(arr[i]);

            while(qu.peek().lrange >= nn.data || qu.peek().rrange <= nn.data){
                qu.remove();
            }
            LPair rem = qu.remove(); 
            
            qu.add(new LPair(nn, rem.lrange, nn.data));
            qu.add(new LPair(nn, nn.data, rem.rrange));
            if(rem.parent == null){
                root = nn;
            }else if(rem.parent.data > nn.data){
                rem.parent.left = nn;
            }else{
                rem.parent.right = nn;
            }
        }
        return root;
    }

    // 297. Serialize and Deserialize Binary Tree
    // Encodes a tree to a single string.
    public void serializeUtil(TreeNode root,StringBuilder sb){
        if(root == null){
            sb.append("null#");
            return;
        }
        sb.append(root.val+"#");
        serializeUtil(root.left, sb);
        serializeUtil(root.right, sb);
    }
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeUtil(root,sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public class dPair{
        TreeNode node;
        int state;

        public dPair(TreeNode node,int state){
            this.node = node;
            this.state = state;
        }
    }
    public TreeNode deserialize(String str) {
        if(str.equals("null#")) return null;
        String[] data = str.split("#");
        int indx = 1;
        
        TreeNode root = new TreeNode(Integer.parseInt(data[0]));
        
        Stack<dPair> st  = new Stack<>();
        st.push(new dPair(root,0));
        
        while(indx < data.length){
            if(st.peek().state == 0){
                // left child
                if(data[indx].equals("null") == true){
                    st.peek().state++;
                    indx++;
                }else{
                    TreeNode nn = new TreeNode(Integer.parseInt(data[indx]));
                    indx++;
                    st.peek().state++;
                    st.peek().node.left = nn;
                    st.push(new dPair(nn,0));
                }
            }else if(st.peek().state == 1){
                // right child
                if(data[indx].equals("null") == true){
                    st.peek().state++;
                    indx++;
                }else{
                    TreeNode nn = new TreeNode(Integer.parseInt(data[indx]));
                    indx++;
                    st.peek().state++;
                    st.peek().node.right = nn;
                    st.push(new dPair(nn,0));
                }
            }else{
                st.pop();
            }
        }

        return root;
    }

    // Serialize and deserialize N-Array tree
    // Encodes a tree to a single string.
    public static void serializeNArrayTreeUtil(Node root,StringBuilder sb) {

        sb.append(root.val+"#");
        for(Node child:root.children){
            serializeNArrayTreeUtil(child, sb);
        }
        sb.append("null"+'#');
    }
   
    public static String serializeNArrayTree(Node root) {
        if(root == null) return "null#";
        StringBuilder sb = new StringBuilder();
        serializeNArrayTreeUtil(root, sb);
        return sb.toString();
    }
    
    public static Node deserializeNArrayTree(String str) {
        if(str.equals("null#")) return null;
        String[] data = str.split("#");
        Node root = new Node(Integer.parseInt(data[0]));

        Stack<Node> st = new Stack<>();
        st.push(root);
        for(int i=1;i<data.length;i++){
            if(data[i].equals("null")){
                st.pop();
            }else{
                Node nn = new Node(Integer.parseInt(data[i]));
                st.peek().children.add(nn);
                st.push(nn);
            }
        }
        return root;
    }
    
    
    // Left view of Btree
    // GFG
    public ArrayList<Integer> leftView(Node root){
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        Queue<Node> qu = new ArrayDeque<>();
        qu.add(root);
        while(qu.size() > 0){
            int sz = qu.size();
            int val = -1;
            while(sz-->0){
                Node rem = qu.remove();
                if(val == -1){
                    val = rem.data;
                }
                if(rem.left != null){
                    qu.add(rem.left);
                }
                if(rem.right != null){
                    qu.add(rem.right);
                }
            }
            ans.add(val);
        }
        return ans;
    }

    // right view of BTree (leetcode)
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        Queue<TreeNode> qu = new ArrayDeque<>();
        qu.add(root);
        while(qu.size() > 0){
            int sz = qu.size();
            int val = -101;
            while(sz-->0){
                TreeNode rem = qu.remove();
                if(rem.left != null){
                    qu.add(rem.left);
                }
                if(rem.right != null){
                    qu.add(rem.right);
                    
                }
                val = rem.val;
            }
            ans.add(val);
        }
        return ans;
    }

    // Width Of Shadow Of Binary Tree
    // https://practice.geeksforgeeks.org/problems/vertical-width-of-a-binary-tree/1   (GFG)
    static int minLH = 0; //left horz(min)
    static int maxRH = 0; //right horz(max)
    public static void widthUtil(TreeNode root,int count){
        if(root == null) return;
        if(count < minLH) minLH = count;
        if(count > maxRH) maxRH = count;
        widthUtil(root.left,count-1);
        widthUtil(root.right, count+1);
    }
    public static int width(TreeNode root) {
        if(root == null ) return 0;
        minLH = 0;
        maxRH = 0;
        widthUtil(root,0);
        return maxRH - minLH + 1;
    }

    // https://practice.geeksforgeeks.org/problems/print-a-binary-tree-in-vertical-order/1
    // Vertical Order 1
    public static class vPair implements Comparable<vPair>{
        TreeNode node;
        int count;

        public vPair(TreeNode node,int count){
            this.node = node;
            this.count = count;
        }
        public int compareTo(vPair o){
            return this.node.val - o.node.val;
        }
    }
    static ArrayList<Integer> verticalOrder(Node root){
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        Queue<vPair> qu = new LinkedList<>();
        qu.add(new vPair(root,0));
        
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();

        int lh = 0; // left horizontal
        int rh = 0; // right horizontal
        
        while(qu.size() > 0){
            // get + remove;
            vPair rem = qu.remove();
            // work
            if(map.containsKey(rem.count)){
                map.get(rem.count).add(rem.node.data);
            }else{
                ArrayList<Integer> li = new ArrayList<>();
                li.add(rem.node.data);
                map.put(rem.count,li);
            }
            if(rem.count < lh){
                lh = rem.count;
            }else if(rh < rem.count){
                rh = rem.count;
            }
            // add childrens
            if(rem.node.left != null){
                qu.add(new vPair(rem.node.left,rem.count-1));
            }
            if(rem.node.right != null){
                qu.add(new vPair(rem.node.right,rem.count+1));
            }
        }

        for(int i=lh;i<=rh;i++){
            for(int val:map.get(i)){
                ans.add(val);
            }
        }

        return ans;

    }   
    // w/o hashMap
    public static ArrayList<ArrayList<Integer>> verticalOrder_method2(TreeNode root){
        minLH = 0;
        maxRH = 0;
        int wd = width(root);
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for(int i=0;i<wd;i++){
            res.add(new ArrayList<>());
        }
        Queue<vPair> qu = new LinkedList<>();
        qu.add(new vPair(root,Math.abs(minLH)));
        while(qu.size() > 0){
            vPair rem = qu.remove();
            res.get(rem.count).add(rem.node.val);
            if(rem.node.left != null){
                qu.add(new vPair(rem.node.left,rem.count-1));
            }
            if(rem.node.right != null){
                qu.add(new vPair(rem.node.right,rem.count+1));
            }
        }
        return res; 
    }


    // https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
    // Vertical Order 2
    public List<List<Integer>> verticalTraversal(TreeNode root){
        minLH = 0;
        maxRH = 0;
        int wd = width(root);
        List<List<Integer>> res = new ArrayList<>();

        for(int i=0;i<wd;i++){
            res.add(new ArrayList<>());
        }

        PriorityQueue<vPair> mainQ = new PriorityQueue<>();
        PriorityQueue<vPair> childQ = new PriorityQueue<>();

        mainQ.add(new vPair(root, Math.abs(minLH)));

        while(mainQ.size() > 0){
            while(mainQ.size() > 0){
                vPair rem = mainQ.remove();
                res.get(rem.count).add(rem.node.val);
                if(rem.node.left != null){
                    childQ.add(new vPair(rem.node.left, rem.count-1));
                }
                if(rem.node.right != null){
                    childQ.add(new vPair(rem.node.right, rem.count+1));
                }
            }
            mainQ = childQ;
            childQ = new PriorityQueue<>();
        }

        return res;
    }


    // Top View of Binary Tree 
    // https://practice.geeksforgeeks.org/problems/top-view-of-binary-tree/1
    public static ArrayList<Integer> topView(Node root){
        ArrayList<Integer> res = new ArrayList<>();
        if(root == null) return res;

        minLH = 0;
        maxRH = 0;
        int wd = width(root);

        Queue<vPair> qu = new LinkedList<>();
        qu.add(new vPair(root,Math.abs(minLH)));

        
        for(int i=0;i<wd;i++){
            res.add(null);
        }
        qu.add(new vPair(root,Math.abs(minLH)));
        while(qu.size() > 0){
            vPair rem = qu.remove();
            if(res.get(rem.count) == null){
                res.set(rem.count,rem.node.data);
            }
            if(rem.node.left != null){
                qu.add(new vPair(rem.node.left,rem.count-1));
            }
            if(rem.node.right != null){
                qu.add(new vPair(rem.node.right,rem.count+1));
            }
        }
        return res;
    }

    // Diagonal Traversal of Binary Tree 
    // Portal
    public static ArrayList<ArrayList<Integer>> diagonalOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> qu = new LinkedList<>();
        qu.add(root);
        while(qu.size() > 0){
            int factorSize = qu.size();
            ArrayList<Integer> curr = new ArrayList<>();
            while(factorSize-->0){
                TreeNode factor = qu.remove();
                while(factor != null){
                    curr.add(factor.val);
                    if(factor.left != null) qu.add(factor.left);
                    factor = factor.right;
                }
            }
            res.add(curr);
        }
        return res;
    }

    // Diagonal Order (anti-clock Wise) Of A Binarytree
    public static ArrayList<ArrayList<Integer>> diagonalOrderAnti(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> qu = new LinkedList<>();
        qu.add(root);
        while(qu.size() > 0){
            int factorSize = qu.size();
            ArrayList<Integer> curr = new ArrayList<>();
            while(factorSize-->0){
                TreeNode factor = qu.remove();
                while(factor != null){
                    curr.add(factor.val);
                    if(factor.right != null) qu.add(factor.right);
                    factor = factor.left;
                }
            }
            res.add(curr);
        }
        return res;
    }

    // Vertical Order Sum Of A Binarytree
    public static ArrayList<Integer> verticalOrderSum(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if(root == null) return res;
        minLH = 0;
        maxRH = 0;
        int wd = width(root);
        Queue<vPair> qu = new LinkedList<>();
        qu.add(new vPair(root,Math.abs(minLH)));
        for(int i=0;i<wd;i++){
            res.add(0);
        }
        while(qu.size() > 0){
            vPair rem = qu.remove();
            res.set(rem.count, res.get(rem.count) + rem.node.val);
            if(rem.node.left != null){
                qu.add(new vPair(rem.node.left,rem.count-1));
            }
            if(rem.node.right != null){
                qu.add(new vPair(rem.node.right,rem.count+1));
            }
        }
        return res;
    }

    // Diagonal Order Sum Of A Binary Tree
    public static ArrayList<Integer> diagonalOrderSum(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> qu = new LinkedList<>();
        qu.add(root);
        while(qu.size() > 0){
            int factorSize = qu.size();
            int sum = 0;
            while(factorSize-->0){
                TreeNode factor = qu.remove();
                while(factor != null){
                    sum += factor.val;
                    if(factor.left != null) qu.add(factor.left);
                    factor = factor.right;
                }
            }
            res.add(sum);
        }
        return res;   
    }

    // GFG
    public ArrayList<Integer> diagonal(Node root){
        ArrayList<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Queue<Node> qu = new LinkedList<>();
        qu.add(root);
        while(qu.size() > 0){
            int factorSize = qu.size();
            while(factorSize-->0){
                Node factor = qu.remove();
                while(factor != null){
                    res.add(factor.data);
                    if(factor.left != null) qu.add(factor.left);
                    factor = factor.right;
                }
            }
        }
        return res;
    }

    // bottom view of binary Tree
    public ArrayList <Integer> bottomView(Node root){
        ArrayList<Integer> res = new ArrayList<>();
        if(root == null) return res;

        minLH = 0;
        maxRH = 0;
        int wd = width(root);

        Queue<vPair> qu = new LinkedList<>();
        qu.add(new vPair(root,Math.abs(minLH)));

        
        for(int i=0;i<wd;i++){
            res.add(null);
        }
        qu.add(new vPair(root,Math.abs(minLH)));
        while(qu.size() > 0){
            vPair rem = qu.remove();
            res.set(rem.count,rem.node.data);
            if(rem.node.left != null){
                qu.add(new vPair(rem.node.left,rem.count-1));
            }
            if(rem.node.right != null){
                qu.add(new vPair(rem.node.right,rem.count+1));
            }
        }
        return res;
    }

    public static ArrayList<TreeNode> nodeToRootPath(TreeNode node, int data) {
        if(node.val == data){
            ArrayList<TreeNode> base = new ArrayList<>();
            base.add(node);
            return base;
        }
        
        if(node.left != null){
            ArrayList<TreeNode> lres = nodeToRootPath(node.left,data);
            if(lres.size() > 0){
                lres.add(node);
                return lres;
            }
        }
        if(node.right != null){
            ArrayList<TreeNode> rres = nodeToRootPath(node.right,data);
            if(rres.size() > 0){
                rres.add(node);
                return rres;
            }
        }
        return new ArrayList<>();
    }

    // Root to all leaf;
    public static void Paths_Util(TreeNode root,ArrayList<ArrayList<Integer>> res,ArrayList<Integer> curr){
        if(root == null) return;
        if(root.left == null && root.right == null) {
            ArrayList<Integer> duplicates = new ArrayList<>();
            for(int i:curr){
                duplicates.add(i);
            }
            duplicates.add(root.val);
            res.add(duplicates);
            // curr.add(root.val);
            // res.add(new ArrayList<>(curr));
            return;
        }
        curr.add(root.val);
        Paths_Util(root.left, res, curr);
        Paths_Util(root.right, res, curr);
        curr.remove(curr.size()-1);
    }
    
    public static ArrayList<ArrayList<Integer>> rootToAllLeafPath(TreeNode root){
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> subres = new ArrayList<>();
        Paths_Util(root,res,subres);
        return res;
    }

    // All Single Child Parent In Binary Tree
    public static void exactlyOneChildUtil(TreeNode root,ArrayList<Integer> ans){
        if(root == null || (root.left == null && root.right == null)) return;

        if(root.left == null || root.right == null){
            ans.add(root.val);
        }

        exactlyOneChildUtil(root.left,ans);
        exactlyOneChildUtil(root.right,ans);
    }
    
    public static ArrayList<Integer> exactlyOneChild(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        exactlyOneChildUtil(root,ans);
        return ans;
    }


    // Count All Single Child Parent In Binary Tree (method 1)
    static int onechilds = 0;
    public static void countExactlyOneChildUtil1(TreeNode root){
        if(root == null || (root.left == null && root.right == null)) return;

        if(root.left == null || root.right == null){
            onechilds++;
        }   

        countExactlyOneChildUtil1(root.left);
        countExactlyOneChildUtil1(root.right);
    }
    public static int countExactlyOneChild1(TreeNode node) {
        onechilds = 0;
        countExactlyOneChildUtil1(node);
        return onechilds;
    }

    // (method2)
    public static int countExactlyOneChild(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) return 0;

        int res = 0;
        if(root.left == null || root.right == null){
            res = 1;
        }   

        res += countExactlyOneChild(root.left);
        res += countExactlyOneChild(root.right);

        return res;
    }

    // In Order Morris Traversal In Binarytree 0(1) space in In order of a Binary Tree
    public static TreeNode getRightMostNode(TreeNode temp,TreeNode curr){
        while(temp.right != null && temp.right != curr){
            temp = temp.right;
        }
        return temp;
    }
    public static ArrayList<Integer> morrisInTraversal(TreeNode node) {
        ArrayList<Integer> ans = new ArrayList<>();
        TreeNode curr = node;
        while(curr != null){
            TreeNode leftNode = curr.left;
            if(leftNode != null){
                TreeNode rightMostNode = getRightMostNode(leftNode,curr);
                if(rightMostNode.right != curr){
                    rightMostNode.right = curr;
                    curr = curr.left;
                }else{
                    ans.add(curr.val);
                    rightMostNode.right = null;
                    curr = curr.right;  
                }
            }else{
                // 1.print value
                ans.add(curr.val);
                // move toward rught
                curr = curr.right;
            }
        }
        return ans;
    }



    // 113. Path Sum II
    public void pathSumUtil(TreeNode root,int targetSum,int ssf,List<Integer> curr,List<List<Integer>> ans){
        if(root == null) return;

        if(root.left == null && root.right == null){
            // leaf
            if(ssf+root.val == targetSum){
                // SHREESH
                List<Integer> duplicate = new ArrayList<Integer>();
                for(Integer val:curr){
                    duplicate.add(val);
                }
                duplicate.add(root.val);
                ans.add(duplicate);
                // MY WAY
                // curr.add(root.val);
                // ans.add(new ArrayList<Integer>(curr));
                // curr.remove(curr.size()-1);
            }
            return;
        }
        curr.add(root.val);
        pathSumUtil(root.left,targetSum,ssf+root.val,curr,ans);
        pathSumUtil(root.right,targetSum,ssf+root.val,curr,ans);
        curr.remove(curr.size()-1);
    }
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        pathSumUtil(root,targetSum,0,new ArrayList<>(),ans);
        return ans;
    }



    // Diameter Of Binary Tree All Methods
    static int dia = 0;
    public static int diameterOfBinaryTree_Util1(TreeNode root){
        if(root == null) return -1;
        int lht = diameterOfBinaryTree_Util1(root.left);
        int rht = diameterOfBinaryTree_Util1(root.right);
    
        dia = Math.max(dia,lht+rht+2);
    
        return Math.max(lht,rht)+1;
    }
    static class diaPair{
        int ht;
        int dia;

        public diaPair(){
            ht = -1;
            dia = 0;
        }
    }
    public static diaPair diameterOfBinaryTree_Util2(TreeNode root){
        if(root == null) return new diaPair();

        diaPair lres = diameterOfBinaryTree_Util2(root.left);
        diaPair rres = diameterOfBinaryTree_Util2(root.right);
        diaPair myres = new diaPair();

        myres.ht = Math.max(lres.ht,rres.ht)+1;
        myres.dia = Math.max(lres.ht + rres.ht + 2, Math.max(lres.dia, rres.dia));
        return myres;
    }
    // util1 && util2 same TC
    public static int height(TreeNode root){
        if(root == null) return -1;
        int lht = height(root.left);
        int rht = height(root.right);
        return Math.max(lht,rht)+1;
    }
    // worst timeComplexity
    public static int diameterOfBinaryTree_Util3(TreeNode root){
        if(root == null) return -1;
        int ld = diameterOfBinaryTree_Util3(root.left);
        int rd = diameterOfBinaryTree_Util3(root.right);

        int lh = height(root.left);
        int rh = height(root.right);
        
        int rootDia = lh+rh+2;

        return Math.max(rootDia,Math.max(ld,rd));
    }


    public static int diameterOfBinaryTree(TreeNode root) {
        // return diameterOfBinaryTree_Util3(root);

        // util 2
        diaPair res = diameterOfBinaryTree_Util2(root);
        return res.dia;     
        
        // util 1
        // dia = 0;
        // diameterOfBinaryTree_Util1(root);
        // return dia;
    }       

    // Maximum Path Sum between 2 Leaf Nodes
    // https://practice.geeksforgeeks.org/problems/maximum-path-sum/1
    private int maxSum = Integer.MIN_VALUE;
    int maxPathSum_Util(Node root) {
        if(root == null) return 0;

        int lsum = maxPathSum_Util(root.left);
        int rsum = maxPathSum_Util(root.right);

        maxSum = Math.max(maxSum,lsum+rsum+root.data);
        return Math.max(lsum, rsum)+root.data;
    }
    int maxPathSum(Node root){ 
        maxSum = 0;
        maxPathSum_Util(root);
        return maxSum;
    }

    // All Nodes Distance K In Binary Tree https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
    public static ArrayList<TreeNode> nodeToRootPaths(TreeNode root,int target){
        if(root == null) return new ArrayList<>();
        
        if(root.val == target){
            ArrayList<TreeNode> res = new ArrayList<>();
            res.add(root);
            return res;
        }
        ArrayList<TreeNode> lres = nodeToRootPaths(root.left,target);
        if(lres.size() > 0){
            lres.add(root);
            return lres;
        }

        ArrayList<TreeNode> rres = nodeToRootPaths(root.right,target);
        if(rres.size() > 0){
            rres.add(root);
            return rres;
        }

        return new ArrayList<>();
    }

    private static void kdown(TreeNode node, TreeNode blockage, int k, ArrayList<Integer> res) {
        if(node == null || node == blockage) return;

        if(k == 0) {
            res.add(node.val);
            return;
        }

        kdown(node.left, blockage, k - 1, res);
        kdown(node.right, blockage, k - 1, res);
    }

    public static ArrayList<Integer> distanceK(TreeNode root, int target, int k) {
        ArrayList<TreeNode> n2rpath = nodeToRootPaths(root, target);

        ArrayList<Integer> res = new ArrayList<>();
        TreeNode blockage = null;
        for(int i = 0; i < n2rpath.size() && k - i >= 0; i++) {
            TreeNode node = n2rpath.get(i);
            kdown(node, blockage, k - i, res);
            blockage = node;
        }
        return res;
    }

    // k distance w/o any n2rp
    public boolean find(TreeNode root, TreeNode target){
        // check image
        return true;
    }


    // Burning Tree
    static int maxBurnTime = 0;
    public static void burningTreeUtil(TreeNode node,TreeNode blockage,int t){
        if(node == null || node == blockage) return;

        maxBurnTime =  Math.max(maxBurnTime,t);
        burningTreeUtil(node.left,blockage,t+1);
        burningTreeUtil(node.right,blockage,t+1);

    }
    public static int burningTree(TreeNode root, int fireNode) {
        ArrayList<TreeNode> n2rp = nodeToRootPaths(root,fireNode);
        maxBurnTime = 0;
        TreeNode blockage = null;
        for(int t=0;t<n2rp.size();t++){
            TreeNode node = n2rp.get(t);
            burningTreeUtil(node,blockage,t);
            blockage = node;
        }
        return maxBurnTime;
    }

    // Burning Tree 2
    static ArrayList<ArrayList<Integer>> res;
    public static void burningTree2Util(TreeNode root, TreeNode blockage,int t) {
        if(root == null || root == blockage) return;

        if(t < res.size()){
            res.get(t).add(root.val);
        }else{
            // time == res.size
            ArrayList<Integer> nW = new ArrayList<Integer>();
            nW.add(root.val); 
            res.add(nW);
        }

        burningTree2Util(root.left,blockage,t+1);
        burningTree2Util(root.right,blockage,t+1);
    }
    public static ArrayList<ArrayList<Integer>> burningTree2(TreeNode root, int data) {
        ArrayList<TreeNode> n2rp = nodeToRootPaths(root,data);
        res = new ArrayList<>();
        TreeNode blockage = null;
        for(int t=0;t<n2rp.size();t++){
            TreeNode node = n2rp.get(t);
            burningTree2Util(node,blockage,t);
            blockage = node;
        }
        return res;
    }

    // 662. Maximum Width of Binary Tree https:/z/leetcode.com/problems/maximum-width-of-binary-tree/
    private class WPair{
        TreeNode node;
        int indx;
        public WPair(TreeNode node,int indx){
            this.node = node;
            this.indx = indx;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {

        Queue<WPair> qu = new LinkedList<>();
        qu.add(new WPair(root, 0));
        int maxWidth = 0;
        while(qu.size()>0){
            int sz = qu.size();
            int lm = qu.peek().indx; //left most and right most indx
            int rm = qu.peek().indx;
            while(sz-->0){
                // GET + remove
                WPair rem = qu.remove();
                // work
                rm = rem.indx;
                // add childrens with indx
                if(rem.node.left != null){
                    qu.add(new WPair(rem.node.left,2*rem.indx+1));
                }
                if(rem.node.right != null){
                    qu.add(new WPair(rem.node.right,2*rem.indx+2));
                }
            }
            int width = rm-lm+1; //width of the current LEVEL
            maxWidth = Math.max(width,maxWidth);
            
        }
        return maxWidth;
    }

    // Convert Binary Search Tree To Doubly Linked List
    // three ways it can be done DFS.,STACK,MORRIS TRAVERSAL O(1) 
    
    // DFS Method
    // will do later
    // public static Node bToDLL(Node root) {


    // }


    // Convert Sorted Doubly Linked List To Binary Search Tree
       // left : prev, right : next
    public static Node getMid1(Node head){
        Node fast = head.right;
        Node slow = head;
        while(fast != null && fast.right != null){
            fast = fast.right.right;
            slow = slow.right;
        }
        return slow;
    }
    public static Node creationBST(Node head){
        if(head == null) return null;
        Node mid = getMid1(head);
        if(mid.left != null){
            mid.left.right = null;
            mid.left = null;
        }
        Node head2 = mid.right;
        if(mid.right != null){
            mid.right.left = null;
            mid.right = null;
        }
        if(mid != head){
            mid.left = creationBST(head);
        }
        mid.right = creationBST(head2);
        return mid;
    }
    public static Node SortedDLLToBST(Node head) {
        Node root = creationBST(head);
        return root;
    }       

    // Path Sum In Binary Tree
    public static boolean  hasPathSum_Util(TreeNode root,int target,int ssf){
        if(root == null) return false;
        if(root.left == null && root.right == null){
            ssf += root.val;
            if(ssf == target){
                return true;
            }
            return false;
        }
        boolean rres = hasPathSum_Util(root.left,target,ssf+root.val) || hasPathSum_Util(root.right,target,ssf+root.val);
        return rres;

    }
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        boolean res = hasPathSum_Util(root,targetSum,0);
        return res;
    }

    // Lowest Common Ancestor Of A Binary Tree
    static TreeNode lca = null;
    public boolean solveLCA(TreeNode root,TreeNode data1,TreeNode data2){
        if(root == null) return false;
        // self check
        boolean self = root == data1 || root == data2;
       
        boolean left = false;
        boolean right = false;
        
        //left check
        left = solveLCA(root.left,data1,data2);
        
        // right hceck
        if(lca == null){
            right = solveLCA(root.right,data1,data2);
        }
        if((self && left) || (self && right) || (left && right)){
            lca = root;
        }

        return self || left || right;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
          lca = null;
          solveLCA(root,p,q);
          return lca;  
    }



    // 124. Binary Tree Maximum Path Sum
    static int maxPath = 0;
    public int maxPathSumUtil(TreeNode root){
        if(root == null) return -(int)1e9;

        int lsum = maxPathSumUtil(root.left);
        int rsum = maxPathSumUtil(root.right);

        int val = Math.max(root.val,Math.max(lsum+root.val,rsum+root.val));
        maxPath = Math.max(maxPath,Math.max(val,lsum+rsum+root.val));

        return val;
    }
    public int maxPathSum(TreeNode root) {
        maxPath = Integer.MIN_VALUE;
        maxPathSumUtil(root);
        return maxPath;
    }
    
    
    
    
    
    public static void main(String[] args) {
        
    }    
}
