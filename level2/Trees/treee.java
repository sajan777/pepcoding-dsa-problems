import java.util.*;

import jdk.nashorn.api.tree.Tree;
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

        public Node(int data){
            this.data = data;
            this.left = this.right = null;
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
        return bstFromPreorderUtil(pre,0,Integer.MIN_VALUE,pre[0],pre[0],Integer.MAX_VALUE);
    }










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

    public boolean isValidBST(TreeNode root) {
        return false;
    }



    public static void main(String[] args) {
        
    }    
}
