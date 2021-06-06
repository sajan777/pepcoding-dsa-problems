import java.util.*;

public class gtree{

    public static class Node{
        int data;
        ArrayList<Node> children;

        public Node(){
            this.data = 0;
            this.children = new ArrayList<>();
        }
        public Node(int data){
            this.data = data;
            this.children = new ArrayList<>();
        }
    }
    public static Node construct(Integer[] arr){
        Node root = null;
        Stack<Node> st = new Stack<>();

        for(int i=0;i<arr.length;i++){
            Integer data = arr[i];
            if(data != null){
                Node nn = new Node(data);
                if(st.size() == 0){
                    // prepare root
                    root = nn;
                    st.push(nn);
                }else{
                    st.peek().children.add(nn);
                    st.push(nn);
                }
            }else{
                // pop
                st.pop();
            }
        }
        return root;
    }

    public static void display(Node root){
        if(root == null) return;

        String str = "[" + root.data + "] -> ";
        for(Node child : root.children){
            str += child.data +", ";
        }
        System.out.println(str+ " .");

        for(int i=0;i < root.children.size();i++){
            Node child =  root.children.get(i);
            display(child);
        }
    }

    public static int size(Node root){
        if(root == null){
            return 0;
        }
        int s = 0;
        for(Node child : root.children){
            s += size(child);
        }
        s+=1;
        return s;

    }
    
    public static int max(Node root){
        if(root == null){
            return -1;
        }
        int maxVal = Integer.MIN_VALUE;
        for(Node child : root.children){
            maxVal = Math.max(maxVal,max(child));
        }
        return Math.max(maxVal,root.data);

    }
    
    public static int min(Node root){
        if(root == null){
            return -1;
        }
        int minVal = Integer.MAX_VALUE;
        for(Node child : root.children){
            minVal = Math.min(minVal,min(child));
        }
        return Math.min(minVal,root.data);

    }

    public static int height(Node node) {
        int height = -1;
        for(Node child : node.children){
            height = Math.max(height,height(child));
        }
        return height + 1;
    }

    // Traversal
    public static void traversals(Node node){
        // Node Pre (before leaving node)
        System.out.println("Node Pre "+node.data);
        for(int i=0;i < node.children.size();i++){
            Node child =  node.children.get(i);
            // Edge Pre (before edge call)
            System.out.println("Edge Pre "+node.data+"--"+child.data);
            traversals(child);
            // Edge Post (after edge call)
            System.out.println("Edge Post "+node.data+"--"+child.data);
        }
        // Node Post (after leaving node)
        System.out.println("Node Post "+node.data);
    }

    // Level Order
    public static void levelOrder(Node node){
        // write your code here
            Queue<Node> qu = new ArrayDeque<>();
            qu.add(node);
            while(qu.size() > 0){
                Node pp = qu.remove();
                System.out.print(pp.data+" ");
                for(Node child:pp.children){
                    qu.add(child);
                }
            }
            System.out.print(".");
    }

    // Approach 1 - Two Queue LevelOrderLineWise
    public static void levelOrderLinewise(Node node){
        // write your code here
        Queue<Node> mainQueue = new ArrayDeque<>();
        Queue<Node> childQueue = new ArrayDeque<>();
        mainQueue.add(node);
        while(mainQueue.size() > 0){
            // 1 .get+remove
            Node pp = mainQueue.remove();
            // 2. print
            System.out.print(pp.data+" ");
            // 3 . add children
            for(Node child:pp.children){
                childQueue.add(child);
            }
            // 4. if size 0 then swap and hit enter
            if(mainQueue.size() == 0){
                System.out.println();
                mainQueue = childQueue;
                childQueue = new ArrayDeque<>();
            }
        }     
    }
    // Approach 2 - create queue using LinkedList as adding nulll in queue gives null pointer LevelOrderLineWise2
    public static void levelOrderLinewise2(Node node){
        // write your code here
        Queue<Node> qu = new LinkedList<>();
        qu.add(node);
        // add null as delimeter
        qu.add(null);
        while(qu.size() > 0){
            while(qu.peek() != null){
                // 1 .get+remove
                Node pp = qu.remove();
                // 2. print
                System.out.print(pp.data+" ");
                // 3. add Childrens
                for(Node child:pp.children){
                    qu.add(child);
                }
            }
            // 4. Encountered null hit enter remove null and add null if size > 0
            System.out.println();
            qu.remove();
            if(qu.size() == 0){
                qu.add(null);
            }

        }
    }

    // we can also calculate the levels here as well like counter when the level changes
    // (many questions can be done using this approach)
    // Approach 3 single que base on size  (best elegant) 
    public static void levelOrderLinewise3(Node node){
        // write your code here
        Queue<Node> qu = new ArrayDeque<>();
        qu.add(node);
        while(qu.size() > 0){ 
            // 1. get size of quue
            int sz = qu.size();
            // 2. process size elements for the level
            while(sz-- > 0){
                // 1 .get+remove
                Node pp = qu.remove();
                // 2. print
                System.out.print(pp.data+" ");
                // 3. add Childrens
                for(Node child:pp.children){
                    qu.add(child);
                }
            }
            // 3. hit enter next level
            System.out.println();

        }
    }

    // Level Order ZigZag (Two Stack Apporach)
    public static void levelOrderLinewiseZZ(Node node){
        // ms = mainStack
        // cs = childStack
        Stack<Node> ms = new Stack<>();
        Stack<Node> cs = new Stack<>();
        ms.add(node);
        int level = 1;
        while(ms.size() > 0){
            int sz = ms.size();
            while(sz -- > 0){
                // 1 .get+remove
                Node pp = ms.pop();
                // 2. print                                 
                System.out.print(pp.data+" ");
                if(level%2 == 0){
                    // right to left
                    for(int i=pp.children.size()-1;i>=0;i--){
                        Node child = pp.children.get(i);
                        cs.push(child);
                    }
                }else{
                    // left to right
                    for(Node child:pp.children){
                        cs.push(child);
                    }
                }
            }
            System.out.println();
            level++;
            if(ms.size() == 0){
                ms = cs;
                cs = new Stack<>();
            }
        }
    }

    // Approach 2
    public static void levelOrderLinewiseZZ2(Node node){
        // ms = mainStack
        // cs = childStack
        Stack<Node> ms = new Stack<>();
        Stack<Node> cs = new Stack<>();
        ms.add(node);
        int level = 1;
        while(ms.size() > 0){
            // 1 .get+remove
            Node pp = ms.pop();
            // 2. print                                 
            System.out.print(pp.data+" ");
            if(level%2 == 0){
                // right to left
                for(int i=pp.children.size()-1;i>=0;i--){
                    Node child = pp.children.get(i);
                    cs.push(child);
                }
            }else{
                // left to right
                for(Node child:pp.children){
                    cs.push(child);
                }
            }
            // this will run as soon as stack size == 0 means new level is there 
            if(ms.size() == 0){
                System.out.println();
                level++;
                ms = cs;
                cs = new Stack<>();
            }

        }
    }

    // mirror a generic tree
    public static void mirror(Node node){
        for(Node child:node.children){
            mirror(child);
        }
        
        // reverse children of current node
        int left = 0;
        int right = node.children.size()-1;
        while(left<right){
            Node temp = node.children.get(left);
            node.children.set(left,node.children.get(right));
            node.children.set(right,temp);
            left++;
            right--; 
        }
        // Collections.reverse(node.children);
    }

    // remove Leaves
    public static void removeLeaves(Node node) { 
        // write your code here
        for(int i =node.children.size()-1;i>=0;i--){
            Node child = node.children.get(i);
            if(child.children.size() == 0){
                node.children.remove(i);
            }
        }
        for(Node child:node.children){
            removeLeaves(child);
        }
    }
    
    // remove Leaves approach 2
    public static void removeLeaves2(Node node) {
        // write your code here
        ArrayList<Node> st = new ArrayList<>();
        for(Node child:node.children){
            if(child.children.size() != 0){
                st.add(child);
            }
        }
        node.children = st;
        for(Node child:node.children){
            removeLeaves(child);
        }
    }

    // tail for linearize 0(n2)
    public static Node getTail(Node node){
        Node tail = node;
        while(tail.children.size() != 0){
            tail = tail.children.get(0);
        }
        return tail;
    }

    // linearize 0(n)*0(n2(tail)) = o(n3)+0(1)
    public static void linearize(Node node){
        // write your code here
        for(Node child: node.children){
            linearize(child);
        }

        for(int i=node.children.size()-2;i>=0;i--){
            // lastNode
            Node last = node.children.get(i+1);
            //secondlastNode  
            Node slast = node.children.get(i);
            // removelast
            node.children.remove(i+1);
            Node tail = getTail(slast);
            // add last to tail of secondlast;
            tail.children.add(last);
        }
    }

    // linearize 0(n)+o(n1) Elegant Approach (linearize with tail)
    public static Node linearize2(Node node){
        if(node.children.size() == 0) return node;

        Node lastNode = node.children.get(node.children.size()-1);
        Node tail = linearize2(lastNode);

        for(int i=node.children.size()-2;i>=0;i--){
            Node rem = node.children.remove(i+1);
            Node stail = linearize2(node.children.get(i));
            stail.children.add(rem);
        }
        return tail;
    }
    // public static void linearize(Node node){
    //     // write your code here
    //     linearize2(node);
    // }
    
    // find in a generic tree!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public static boolean find(Node node, int data) {
        // write your code here
        if(node.data == data){
            return true;
        }

        boolean res = false;
        for(Node child:node.children){
            res  = find(child,data);
            if(res == true){
                return true;
            }
            // res = res || find(child,data); (Not very good app dont use this)
        }
        return res;
    }

    // nodeToRootPath 
    public static ArrayList<Integer> nodeToRootPath(Node node, int data){
        // write your code here
        if(node.data == data){
            ArrayList<Integer> base = new ArrayList<>();
            base.add(node.data);
            return base;
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for(Node child:node.children){
            ArrayList<Integer> rres = nodeToRootPath(child, data);
            if(rres.size() > 0){
                for(Integer d:rres)
                    ans.add(d);
                ans.add(node.data);
            }
        }
        return ans;
    }
    // nodeToRootPath (shreesh)
    public static ArrayList<Integer> nodeToRootPath2(Node node, int data){
        // write your code here
        if(node.data == data){
            ArrayList<Integer> base = new ArrayList<>();
            base.add(node.data);
            return base;
        }
        for(Node child:node.children){
            ArrayList<Integer> rres = nodeToRootPath2(child, data);
            if(rres.size() > 0){
                rres.add(node.data);
                return rres;
            }
            // if(rres != null){
            //     rres.add(node.data);
            //     return rres;
            // }
        }
        return new ArrayList<>();
        // return null;
    }

    // LCA (Lowest common ancestor)
    public static int lca(Node node, int d1, int d2) {
        // write your code here
        ArrayList<Integer> lca1 = nodeToRootPath(node, d1);
        ArrayList<Integer> lca2 = nodeToRootPath(node, d2);

        // one can also travel from beg and use the concept of intersection of two linkedList
        int i = lca1.size()-1;
        int j = lca2.size()-1;
        int res = -1;
        while(i>=0 && j>=0  && lca1.get(i) == lca2.get(j)){
            res = lca1.get(i);
            i--;
            j--;
        }

        return res;
    }

    // Distance b/w two nodes
    public static int distanceBetweenNodes(Node node, int d1, int d2){
        // write your code here
        ArrayList<Integer> lca1 = nodeToRootPath(node, d1);
        ArrayList<Integer> lca2 = nodeToRootPath(node, d2);
        int i = lca1.size()-1;
        int j = lca2.size()-1;
        while(i>=0 && j>=0  && lca1.get(i) == lca2.get(j)){
            i--;
            j--;
        }
        // i+1+j+1
        return i+j+2;
    }
 
    // Tree shape similiar
    public static boolean areSimilar(Node n1, Node n2) {
        // write your code here
        if(n1.children.size() != n2.children.size()){
            return false;
        }

        for(int i=0;i<n1.children.size() && i<n2.children.size();i++){
            Node c1 = n1.children.get(i);
            Node c2 = n2.children.get(i);
            boolean rres = areSimilar(c1, c2);
            if(rres == false){
                return false;
            }

        }
        return true;
    }

    // are mirror
    public static boolean areMirror(Node n1, Node n2) {
        // write your code here
        if(n1.children.size() != n2.children.size()){
            return false;
        } 
        int i = 0;
        int j = n2.children.size()-1;
        while(i<n1.children.size() && j>=0){
            Node c1 = n1.children.get(i);
            Node c2 = n2.children.get(j);
            boolean rres = areMirror(c1, c2);
            if(rres == false){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    // are mirror shreesh app
    public static boolean areMirror2(Node n1, Node n2) {
        // write your code here
        if(n1.children.size() != n2.children.size()){
            return false;
        } 
        int sz = n1.children.size();
        for(int i=0;i<sz;i++){
            Node c1 = n1.children.get(i);
            Node c2 = n2.children.get(sz- i- 1);
            boolean rres = areMirror2(c1, c2);
            if(rres == false){
                return false;
            }
        }
        return true;
    }

    // symmetric
    public static boolean IsSymmetric(Node node) {
        return areMirror(node,node);
    }

    static int min;
    static int max;
    static int ht;
    static int sz;
    // multiSolver1-----------------------------------------------------------------//
    // using global var
    public static void multiSolver1(Node node,int depth){
        min = Math.min(node.data,min);
        max = Math.max(node.data,max);
        ht = Math.max(ht,depth);
        sz++;
        for(Node child:node.children){
            multiSolver1(child, depth+1);
        }
    }

    // multiSolver2-----------------------------------------------------------------//
    // using return type
    public static class multiSolver{
        int min;
        int max;
        int ht;
        int sz;

        public multiSolver(){
            this.min = Integer.MAX_VALUE;
            this.max = Integer.MIN_VALUE;
            this.ht = -1;
            this.sz = 0;
        }

        public multiSolver(int min,int max,int ht,int sz){
            this.min = min;
            this.max = max;
            this.ht = ht;
            this.sz = sz;
        }
    }
        
    public static multiSolver multiSolver2(Node node){
        multiSolver myres = new multiSolver(node.data,node.data,-1,1);

        for(Node child:node.children){
            multiSolver rres = multiSolver2(child);
            myres.min = Math.min(myres.min,rres.min);
            myres.max = Math.max(myres.max,rres.max);
            myres.ht = Math.max(myres.ht,rres.ht);
            myres.sz += rres.sz;
        }
        myres.ht += 1;
        return myres;
    }

    public static void multiSolution(Node root){
        // min = Integer.MAX_VALUE;
        // max = Integer.MIN_VALUE;
        // ht = 0;
        // sz = 0;

        // multiSolver1(root,0);
        // System.out.println("Min: "+min);
        // System.out.println("Max: "+max);
        // System.out.println("Height: "+ht);
        // System.out.println("Size: "+sz);

        multiSolver res = multiSolver2(root);
        System.out.println("Min: "+res.min);
        System.out.println("Max: "+res.max);
        System.out.println("Height: "+res.ht);
        System.out.println("Size: "+res.sz);

    }

    // Predecessor & Successor
    static Node predecessor;
    static Node successor;
    static int state=0;
    public static void predecessorAndSuccessor(Node node, int data) {
        if(state == 0){
            if(node.data == data){
                state++;
            }else{
                predecessor = node;
            }
        }else if(state == 1){
            successor = node;
            state++;
            return;
        }
        for(Node child:node.children){
            if(state < 2){
                predecessorAndSuccessor(child, data);
            }
        }
    }

    // ciel & Floor
    static int ceil = Integer.MAX_VALUE; //qualified min
    static int floor = Integer.MIN_VALUE; //qualified max
    public static void ceilAndFloor(Node node, int data) {

        if(node.data > data){
            // ciel bnane me help karega
            if(node.data < ceil){
                ceil = node.data;
            }
        }else if(node.data < data){
            // floor bnane me help karega
            if(node.data > floor){
                floor = node.data;
            }
        }
        
        // if(node.data > data && node.data < ceil){
        //     // ciel bnane me help karega
        //     ceil = node.data;
        // }else if(node.data < data && node.data > floor){
        //     // floor bnane me help karega
        //     floor = node.data;
        // }

        for(Node child:node.children){
            ceilAndFloor(child, data);
        }
    }

    // kth largest element
    public static int kthLargest(Node node, int k){
        // write your code here
        int data = Integer.MAX_VALUE;
        for(int i=0;i<k;i++){
            floor = Integer.MIN_VALUE;
            ceilAndFloor(node, data);
            data = floor;
        }
        return data;
    }
    
    // MaxTreeSumg
    static int nodeData = 0;
    static int maxSum = Integer.MIN_VALUE;
    public static int treeSum(Node node){
        int sum = 0;
        for(Node child:node.children){
            sum += treeSum(child);
        }
        sum += node.data;
        if(sum > maxSum){
            nodeData = node.data;
            maxSum = sum;
        }
        return sum;
    }
    // treeSum(root);
    // System.out.println(nodeData+"@"+maxSum); 
    // Total Generic tree Sum
    public static int subTreeSum(Node node){
        int SubTreeSum = 0;
        for(Node child: node.children){
            SubTreeSum += subTreeSum(child);
        }
        SubTreeSum += node.data;
        System.out.println(node.data+"->"+SubTreeSum);
        return SubTreeSum;
    }
    
    // Diameter of the gtree! (method 1) 0(n2)
    public static int diameter1(Node node){
        int mh = -1; //max Height
        int smh = -1; //second maxHeight
        // finding jheight from child
        for(Node child:node.children){
            int ht = height(child);
            if(ht >= mh){
                smh = mh;
                mh = ht;
            }else if(ht > smh){
                smh = ht;
            }
        }
        // find max diameter from child
        int dfc = 0; //diameter from child
        for(Node child:node.children){
            dfc = Math.max(diameter1(child),dfc);
        }
        // compare my max height + second max height + 2 and child diameter
        return Math.max(dfc,mh+smh+2);  
    }

    public static int diameter = 0;
    // Diameter of the gtree! (method 2) 0(n)
    public static int heightForDiameter(Node node){
        int maxHt = -1; //max Height
        int smaxHt = -1; //smax Height

        for(Node child: node.children){
            int ht = heightForDiameter(child);

            if(ht >= maxHt){
                smaxHt = maxHt; 
                maxHt = ht;
            }else if(ht > smaxHt){
                smaxHt = maxHt;
            }
        }        
        diameter = Math.max(diameter,maxHt+smaxHt+2);
        // for height
        return maxHt + 1;
    }

    public static class Pair {
        Node node;
        int state;

        public Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void IterativePreandPostOrder(Node node) {
        Stack<Pair> st = new Stack<>();

        st.push(new Pair(node, 0));

        ArrayList<Integer> pre = new ArrayList<>();
        ArrayList<Integer> post = new ArrayList<>();

        while(st.size() > 0) {
            // get the pair 
            Pair p = st.peek();
            if(p.state == 0) {
                pre.add(p.node.data);
                p.state++;
            } else if(p.state <= p.node.children.size()) {
                Node child = p.node.children.get(p.state - 1);
                p.state++;
                st.push(new Pair(child, 0));
            } else {
                post.add(p.node.data);
                st.pop();
            }
        }

        for(int val : pre) {
            System.out.print(val + " ");
        }
        System.out.println();

        for(int val : post) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public static class SSPair {
        String ques;
        int state;
        String ans;

        public SSPair(String ques, int state, String ans) {
            this.ques = ques;
            this.state = state;
            this.ans = ans;
        }
    }

    public static void printSubseq(String str) {

        Stack<SSPair> st = new Stack<>();

        st.push(new SSPair(str, 0, ""));
        ArrayList<String> res = new ArrayList<>();
        while(st.size() > 0) {
            SSPair p = st.peek();

            if(p.ques.length() == 0) {
                res.add(p.ans);
                System.out.println(p.ans);
                st.pop();
                continue;
            }

            String roq = p.ques.substring(1);
            char ch = p.ques.charAt(0);
            if(p.state == 0) {
                p.state++;
                st.push(new SSPair(roq, 0, p.ans + ch + " "));
            } else if(p.state == 1) {
                p.state++;
                st.push(new SSPair(roq, 0, p.ans + "- "));
            } else {
                st.pop();
            }
        }

        for(String val : res) {
            System.out.println(val);
        }
        // return res;
        // res can be returened here
    }
    public static void fun() {
        Integer[] data = {10,20,50,null,60,null,null,30,70,null,80,110,null,120,null,null,90,null,null,40,100,null,null,null};
        Node root = construct(data);
        // System.out.println(root.data);
        // display(root);
        // System.out.println(size(root));
        // System.out.println(max(root));
        // System.out.println(height(root));
        // levelOrder(root);
        // levelOrderLinewiseZZ2(root);

        multiSolution(root);
        subTreeSum(root);
    }

    public static void main(String[] args) {
        fun();
    }
}