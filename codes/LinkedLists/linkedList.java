import java.util.*;

public class linkedList {

    private class Node{
        private int data;
        private Node next;
    
        public Node(){
            this.data = 0;
            this.next = null;
        }
        public Node(int data){
            this.data = data;
            this.next = null;
        }
        public Node(int data,Node next){
            this.data = data;
            this.next = next;
        }
    }

    public static class ListNode {
        int val = 0;
        ListNode next = null;
        ListNode random = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public linkedList(){
        this.head = this.tail = null;
        this.size = 0;
    }
    
    private void handleAddWhenSize0(int val) {
        Node nn = new Node(val);
        this.head = nn;
        this.tail = nn;
        this.size++;
    }
    // add
    public void addFirst(int val) {
        if(size == 0) {
            handleAddWhenSize0(val);
        } else {
            Node nn = new Node(val);
            nn.next = head;
            this.head = nn;
            this.size++;
        }
    }

    public void addLast(int val) {
        if(this.size == 0) {
            handleAddWhenSize0(val);
        } else {
            Node nn = new Node(val);
            this.tail.next = nn;
            this.tail = nn;
            this.size++;
        }
    }

    private Node getNthNode(int pos) {
        Node temp = this.head;

        while(pos > 0){
            temp = temp.next;
            pos--;
        }
        return temp;
    }

    public void addAt(int val, int indx) {
        if(indx < 0 || indx > this.size) {
            System.out.println("Invalid Position");
        } else if(indx == 0) {
            addFirst(val);
        } else if(indx == this.size) {
            addLast(val);
        } else {
             // get the pos before where we have to add
            Node nm1 = getNthNode(indx - 1);
            Node nn = new Node(val);
            // point the new node to the prev node next
            nn.next = nm1.next;
             // point the prev node next to the new node;
            nm1.next = nn;
            this.size++;
        }
    }

    // get
    public int getFirst() {
        if(this.size == 0) {
            return -1;
        }

        return this.head.data;
    }

    public int getLast() {
        if(this.size == 0) {
            return -1;
        }

        return this.tail.data;
    }

    public int getAt(int indx) {
        if(indx < 0 || indx >= this.size) {
            return -1;
        }

        Node n = getNthNode(indx);
        return n.data;
    }

    private int handleRemoveWhenSize1() {
        int val = this.head.data;
        this.head = this.tail = null;
        this.size = 0;
        return val;
    }

    // remove
    public int removeFirst() {
        if(this.size == 0) {
            return -1;
        } else if(this.size == 1) {
            return handleRemoveWhenSize1();
        } else {
            int val = this.head.data;
            this.head = this.head.next;
            this.size--;
            return val;
        }
    }

    public int removeLast() {
        if(this.size == 0) {
            return -1;
        } else if(this.size == 1) {
            return handleRemoveWhenSize1();
        } else {
            Node nm1 = getNthNode(this.size - 2);
            int val = this.tail.data;

            nm1.next = null;
            this.tail = nm1;
            this.size--;

            return val;
        }
    }

    public int removeAt(int indx) {
        if(indx < 0 || indx >= this.size) {
            return -1;
        } else if(indx == 0) {
            return removeFirst();
        } else if(indx == this.size - 1) {
            return removeLast();
        } else {
            Node nm1 = getNthNode(indx - 1);
            int val = nm1.next.data;
            nm1.next = nm1.next.next;

            this.size--;
            return val;
        }
    }

    public int size() {
        return this.size;
    }

    public void display() {
        Node temp = this.head;
        while(temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // data iterative reverse Linked List 0(n2)
    public void reverseDI() {
        int left  = 0;
        int right = this.size - 1;
       while(left < right){
            Node lnode = getNthNode(left);
            Node rnode = getNthNode(right);
            int temp = lnode.data;
            lnode.data = rnode.data;
            rnode.data = temp;
            left++;
            right--;
       }
    }

    // pointer interative Linked List reverse 0(n)
    public void reversePI(){
        Node prev = null;
        Node curr = this.head;
        while(curr != null){
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        // swap head-tail pointer to manage head tail
        Node temp = this.head;
        this.head =  this.tail;
        this.tail = temp;
        
        // if return head then return prev;
        // return prev;
    }

    // Create Stack using Linked List
    public static class LLToStackAdapter {
        LinkedList<Integer> list;

        public LLToStackAdapter() {
          list = new LinkedList<>();
        }
        int size() {
          return list.size();
        }
    
        void push(int val) {
          list.addLast(val);
        }
    
        int pop() {
            if(list.size() == 0){
                System.out.println("Stack underflow");
                return -1;
            }
            return list.removeLast();
        }
    
        int top() {
            if(list.size() == 0){
                System.out.println("Stack underflow");
                return -1;
            }
            return list.getLast();
        }
      }

    // Create Queue using Linked List  
    public static class LLToQueueAdapter {
        LinkedList<Integer> list;
    
        public LLToQueueAdapter() {
          list = new LinkedList<>();
        }
    
        int size() {
          return list.size();
        }
    
        void add(int val) {
          // write your code here
          list.addLast(val);
        }
    
        int remove() {
          // write your code here
          if(list.size() == 0){
              System.out.println("Queue underflow");
              return -1;
          }
          return list.removeFirst();
        }
    
        int peek() {
          
            if(list.size() == 0){
                System.out.println("Queue underflow");
                return -1;
            }
            return list.getFirst();
        }
      }

    // kth node from last
    public int kthFromLast(int k){
        Node slow = this.head;
        Node fast = this.head;
        // 1. move k step to fast ptr
        for(int i=0;i<k;i++){
            fast = fast.next;
        }
        // 2. nove slow and fast parallely till fast not reach at end
        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }

        return slow.data;
    }

    // Time 0(n+m),Space 0(n) According to Pepcoding Portal (Without changing Linkedlist) if the head of the linkedlist were not exposed we would have used two pointers for both lists and use the getAt(index) method everytime the order would be 0(n2)
    // kuch aisa karte agar head expose na hota
    // int h1 = 0;
    // int h2 = 0;
    // while(h1<l1.size && h2<l2.size){
    //     Node lnode = l1.getAt(h1);
    //     Node rnode = l2.getAt(h2);

    //     if(lnode.data<rnode.data){

    //     }

    // }
    public static LinkedList mergeTwoSortedLists(LinkedList l1, LinkedList l2) {
        LinkedList list = new LinkedList(); 
        Node head1 = l1.head;
        Node head2 = l2.head;
        while(head1 != null && head2 != null){
            if(head1.data < head2.data){
                list.addLast(head1.data);
                head1 = head1.next;
            }else{
                list.addLast(head2.data);
                head2 = head2.next;
            }
        }
        while(head1 != null){
            list.addLast(head1.data);
            head1 = head1.next;
        }
        while(head2 != null){
            list.addLast(head2.data);
            head2 = head2.next;
        }
        return list;
    
    }

    // changing in given linked list is allowed
    public static LinkedList mergeTwoSortedLists2(LinkedList l1, LinkedList l2) {
        LinkedList list = new LinkedList(); 
  
        while(l1.size() > 0 && l2.size() > 0){
            if(l1.getFirst() < l2.getFirst()){
                list.addLast(l1.removeFirst());
            }else{
                list.addLast(l2.removeFirst());
            }
        }
        // l1 left over
        while(l1.size() > 0){
            list.addLast(l1.removeFirst());
        }
        // l2 left over
        while(l2.size() > 0){
            list.addLast(l2.removeFirst());
        }
        return list;
    
    }
    
    // Important
    // Time 0(n+m) Space 0(1) Elegant Soltion 
    public static LinkedList mergeTwoSortedLists3(LinkedList l1, LinkedList l2) {
        Node head1 = l1.head;
        Node head2 = l2.head;
        
        Node t1 = head1;
        Node t2 = head2;
        Node dummy = new Node(-1);
        Node temp = dummy;
        

        while(t1 != null && t2 != null){
            if(t1.data < t2.data){
                temp.next = t1;
                temp = temp.next;
                t1 = t1.next;
            }else{
                temp.next = t2;
                temp = temp.next;
                t2 = t2.next;
            }
        }
        if(t1 == null){
            temp.next = t2;
        }else{
            temp.next = t1;
        }
        
        // return dummy.next;
        
        // but we have to return new LinkedList so
        linkedList res = new linkedlist();
        res.head = dummy.next;
        int sz = 0;
        Node tail = null;
        temp = dummy;
        while(temp.next != null){
            sz++;
            temp = temp.next;
        }
        res.tail = temp;
        res.size = sz;
        return res;
    }    
    
    // Leetcode Merge TwoSorted LinkedList 21
    public ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        ListNode t1 = head1;
        ListNode t2 = head2;
        
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        

        while(t1 != null && t2 != null){
            if(t1.val < t2.val){
                temp.next = t1;
                temp = temp.next;
                t1 = t1.next;
            }else{
                temp.next = t2;
                temp = temp.next;
                t2 = t2.next;
            }
        }
        if(t1 == null){
            temp.next = t2;
        }else{
            temp.next = t1;
        }
        
        return dummy.next;
        
    }                                                                                                                                                                                                                                                                    
    
    
    // helper function for megeSort
    public static Node midNodeForMergeSort(Node head, Node tail){
        Node fast = head;
        Node slow = head;
  
        while(fast != tail && fast.next != tail){
          fast = fast.next.next;
          slow = slow.next;
        }
  
        return slow;
    }
    // Merge Sort LinkedList
    public static LinkedList mergeSort(Node head, Node tail){
        if(head == tail){
            LinkedList base = new LinkedList();
            base.addLast(tail.data);
            return base;
        }
        Node mid = midNodeForMergeSort(head,tail);
        LinkedList l1 = mergeSort(head,mid);
        LinkedList l2 = mergeSort(mid.next,tail);
        LinkedList ans = mergeTwoSortedLists(l1,l2);
        return ans;
    }

    // remove Duplicates
    public void removeDuplicates(){
        Node itr = this.head.next;
        Node temp = this.head;
  
          while(itr != null){
              if(itr.data != temp.data){
                temp.next = itr;
                temp = temp.next;
              }
            itr = itr.next;
          }
          temp.next = itr;
    }
    // Leetcode 83 Remove Duplicates
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return head;
        ListNode itr = head.next;
        ListNode temp = head;
  
          while(itr != null){
              if(itr.val != temp.val){
                temp.next = itr;
                temp = temp.next;
              }
            itr = itr.next;
          }
          temp.next = itr;
          return head;
      }

    // odd Even
    public void oddEven(){
        // make dummy nodes
        Node ehead = new Node();
        Node temp1 = ehead;

        Node ohead = new Node();
        Node temp2 = ohead;
        
        // itr help in movement
        Node itr = this.head;
        while(itr != null){
            if(itr.data % 2 == 0){
                // even
                temp1.next = itr;
                temp1 = temp1.next;
            }else{
                // odd
                temp2.next = itr;
                temp2 = temp2.next;
            }
            itr =itr.next;
        }
        // if (odd -> even)
        temp2.next = ehead.next;
        temp1.next = null;

        // manage head & tail for linked list as a call
        this.head = ohead.next;
        this.tail = temp2.next == null ? temp2 : temp1;
          
    }

    // k Reverse
    public void kReverse(int k) {
        LinkedList prev = null;

        while(this.size() > 0){
            LinkedList curr = new LinkedList();
            if(this.size() >= k){
                // removeFirst from this, addFirst in curr
                for(int i=0;i<k;i++){
                    int data = this.getFirst();
                    this.removeFirst();
                    curr.addFirst(data);
                }

            }else{

                // removeFirst from this,addLast in curr
                while(this.size() > 0){
                    
                    int data = this.getFirst();
                    this.removeFirst();
                    curr.addLast(data);
                }
            }
            if(prev == null){
                // change the refrence from prev to curr
                // as values till k have been removed and added to curr;
                prev = curr;
            }else{
                // as we are modifying the size we use data member not member function->size();
                prev.tail.next = curr.head;
                prev.tail = curr.tail;
                prev.size += curr.size;

            }
        }
        this.head = prev.head;
        this.tail = prev.tail;
        this.size = prev.size;
 
    }

    private void displayReverseHelper(Node node){
        if(node == null){
            return;
        }
        displayReverseHelper(node.next);
        System.out.print(node.data+" ");
      }
  
      public void displayReverse(){
        displayReverseHelper(head);
        System.out.println();
    }

    // reverse linked list recursive my approach
    // private void reversePRHelper(Node curr,Node prev){
    //     // write your code here
    //     if(curr == null){
    //           Node temp = this.head;
    //           this.head =  this.tail;
    //           this.tail = temp;
    //           return;
    //     }
        
    //     Node next = curr.next;
    //     curr.next = prev;
    //     prev = curr;
    //     curr = next;
    //     reversePRHelper(curr,prev);
        
    //   }
  
    // public void reversePR(){
    //     // write your code here
    //     reversePRHelper(head,null);
    // }

    // reverse Linked List recursion Shreesh approach 
    private void reversePRHelper(Node node){
        // write your code here
          if(node.next == null) {
              this.head = node;
              return;
          }   
          reversePRHelper(node.next);
          node.next.next = node;
    }
  
    public void reversePR(){
        // write your code here
        Node temp = this.head;
        reversePRHelper(temp);
        temp.next = null;
        this.tail = temp;
    }
    

    // Is llink list pallindrome
    public boolean IsPalindrome() {
        // write your code here
        // 0(n2)
        int left = 0;
        int right = this.size - 1;
        while(left < right){
            int lnode = this.getAt(left);
            int rnode = this.getAt(right);
            if(lnode != rnode) return false;
            left++;
            right--;
        }
        return true;
    }
    
    private Node getMidNode(Node node){
        Node fast = node.next;
        Node slow = node;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    
    private Node reversePointer(Node node){
        Node prev = null;
        Node curr = node;
        while(curr != null){
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public boolean IsPalindrome2() {
        // 0(n) approach elegant sol
        Node head1 = this.head;
        
        Node mid = getMidNode(this.head);
        
        Node head2 = mid.next;
        
        mid.next = null;
        head2 = reversePointer(head2);
        
        Node t1 = head1;
        Node t2 = head2;
        while(t1 != null && t2 != null){
            if(t1.data !=  t2.data) return false;
            t1 = t1.next;
            t2 = t2.next;
        }
        head2 = reversePointer(head2);
        mid.next = head2;
        return true;
    }


    // fold a linked list
    public void fold() {
        if(this.head == null || this.head.next == null || this.head.next.next == null) return;

        Node head1 = this.head;
        Node mid = getMidNode(this.head);
        
        Node head2 = mid.next;
        mid.next = null;
        head2 = reversePointer(head2);

        Node t1 = head1;
        Node t2 = head2;
        Node prev = head1;
        while(t1 != null && t2 != null){
            Node n1 = t1.next;
            Node n2 = t2.next;
            
            t1.next = t2;
            t1 = n1;
            prev = t1 == null ? prev : t1;
            
            t2.next = t1;
            t2 = n2;
            prev = t2 == null ? prev : t2;
        }

        // manage head & tail
        this.head = head1;
        this.tail = prev;
        // find tail
        // t1 = head1;
        // while(t1.next != null){
        //     t1 = t1.next;
        // }
        // this.tail = t1;

    }
    // LEETCODE 143 RE-ORDER List
    public void reorderList(ListNode head) {
        
        if(head == null || head.next == null || head.next.next == null) return;

        ListNode head1 = head;
        ListNode mid = getMidNode(head1);
        
        ListNode head2 = mid.next;
        mid.next = null;
        head2 = reversePointer(head2);

        ListNode t1 = head1;
        ListNode t2 = head2;
        while(t1 != null && t2 != null){
            ListNode n1 = t1.next;
            ListNode n2 = t2.next;
            
            t1.next = t2;
            t1 = n1;
            
            t2.next = t1;
            t2 = n2;
        }
        
    }

    // add two linked list dont change the original linked list
    public static LinkedList addTwoLists(LinkedList one, LinkedList two) {
        // write your code here
        Node head1 = one.head;
        Node head2 = two.head;

        // 1. reverse
        head1 = reversePointer(head1);
        head2 = reversePointer(head2);
        
        // 2.add
        Node i = head1;
        Node j = head2;
        int carry = 0;  

        LinkedList res = new LinkedList();

        while(i != null || j != null || carry != 0 ){
            int ival = i == null ? 0 : i.data;
            i = i==null ? null : i.next;
            
            int jval = j == null ? 0 : j.data;
            j = j==null ? null : j.next;

            int sum = ival + jval + carry;
            
            int val = sum % 10;
            carry = sum / 10;
            res.addFirst(val);
        }

        // 3. make original list again,reverse head1 and head2
        head1 = reversePointer(head1);
        head2 = reversePointer(head2);

        // 4.return reult
        return res;
    }

    public static int addTwoLists2Helper(Node one,int i1,Node two,int i2,LinkedList res){
       if(one == null && two == null){
           return 0;
       }

       int d1 = one.data;
       int d2 = two.data;
       int sum = 0;
       if(i1 > i2){
            int carry = addTwoLists2Helper(one.next, i1 - 1, two, i2, res);
            sum = d1+carry;

       } else if(i1 < i2){
            int carry = addTwoLists2Helper(one, i1, two.next, i2 - 1, res);
            sum = d2+carry;

       }else{
            //    i1 == i2
            int carry = addTwoLists2Helper(one.next, i1 - 1, two.next, i2 - 1, res);
            sum = d1+d2+carry;
       }
       res.addFirst(sum % 10);
       return sum / 10;
    }

    // recursive approach
    public static LinkedList addTwoLists2(LinkedList one,LinkedList two){
        LinkedList res = new LinkedList();
        int carry = addTwoLists2Helper(one.head,one.size,two.head,two.size,res);
        if(carry > 0){
            res.addFirst(carry);
        }
        return res;
    }
    
    
    // find intersection
    public static int findIntersection(LinkedList one, LinkedList two){

        Node head1 = one.head;
        Node head2 = two.head;

        int size1 = one.size();
        int size2 = two.size();

        int diff = 0;
        if(size1 > size2){
            diff = size1 - size2;
            while(diff-- > 0){
                head1 = head1.next;
            }
        }else{
            diff = size2 - size1;
            while(diff-- > 0){
                head2 = head2.next;
            }
        }
        while(head1 != null && head2 != null){
            if(head1 == head2) return head1.data;
            head1 = head1.next;
            head2 = head2.next;
        }

        return -1;
        
    }

    
    // Leetcode 160 find intersection
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        int size1 = 0;
        int size2 = 0;
        
        ListNode temp1 = headA;
        ListNode temp2 = headB;
        
        while(temp1 != null){
            size1++;
            temp1 = temp1.next;
        }
        
        while(temp2 != null){
            size2++;
            temp2 = temp2.next;
        }
        
        ListNode head1 = headA;
        ListNode head2 = headB;
        
        int diff = 0;
        if(size1 > size2){
            diff = size1 - size2;
            while(diff-- > 0){
                head1 = head1.next;
            }
        }else{
            diff = size2 - size1;
            while(diff-- > 0){
                head2 = head2.next;
            }
        }
        while(head1 != null && head2 != null){
            if(head1 == head2) return head1;
            head1 = head1.next;
            head2 = head2.next;
        }

        return null;
    }
    
    
    // copy ll with random pointers
    public static ListNode copyRandomList(ListNode head) {
        // the no of nodes are even
        ListNode dummy = new ListNode(-1);
        ListNode t1 = dummy;
        ListNode t2 = head;

        // 1. clone w/o random
        while(t2 != null){
            ListNode nn = new ListNode(t2.val);
            t1.next = nn;
            t1 = nn;

            t2 = t2.next;
        }

        ListNode head2 = dummy.next;
        // 2. connect in zigzag order
        t1 = head;
        t2 = head2;
        while(t1 != null && t2 !=null){
            ListNode n1 = t1.next;
            ListNode n2 = t2.next;

            t1.next = t2;
            t2.next = n1;
            t1 = n1;
            t2 = n2;
        }
        // 3. set random pointers
        t1 = head;
        while(t1 != null){
            t1.next.random = t1.random == null ? null : t1.random.next;
            t1 = t1.next.next;
        }

        // 4. rearrange original list
        ListNode d1 = new ListNode(-1);
        t1 = d1;
        ListNode d2 = new ListNode(-1);
        t2 = d2;

        ListNode temp = head;
        while(temp != null){
            t1.next = temp;
            t2.next = temp.next;

            t1 =  t1.next;
            t2 = t2.next;
            temp = temp.next.next;
        }
        t1.next = null;
        t2.next = null;

        // set clonned head
        return d2.next;

    }


    // Leetcode 138 clone linkedlist
    public Node copyRandomList2(Node head) {
        Node dummy = new Node(-1);
        Node t1 = dummy;
        Node t2 = head;

        // 1. clone w/o random
        while(t2 != null){
            Node nn = new Node(t2.val);
            t1.next = nn;
            t1 = nn;

            t2 = t2.next;
        }

        Node head2 = dummy.next;
        // 2. connect in zigzag order
        t1 = head;
        t2 = head2;
        while(t1 != null && t2 !=null){
            Node n1 = t1.next;
            Node n2 = t2.next;

            t1.next = t2;
            t2.next = n1;
            t1 = n1;
            t2 = n2;
        }
        // 3. set random pointers
        t1 = head;
        while(t1 != null){
            t1.next.random = t1.random == null ? null : t1.random.next;
            t1 = t1.next.next;
        }

        // 4. rearrange original list
        Node d1 = new Node(-1);
        t1 = d1;
        Node d2 = new Node(-1);
        t2 = d2;

        Node temp = head;
        while(temp != null){
            t1.next = temp;
            t2.next = temp.next;

            t1 =  t1.next;
            t2 = t2.next;
            temp = temp.next.next;
        }
        t1.next = null;
        t2.next = null;

        // set clonned head
        return d2.next;
        
    }


    // mid1 (for the pepcoding question using this as we've to use the first half for even)
    // for odd mid1 & mid2 both will work 
    public int mid1(){
        if(head == null) return -1;
        Node fast = this.head.next;
        Node slow = this.head;
        while(fast!= null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow.data;
    }
    
    // mid2 
    public int mid2(){
        if(head == null) return -1;
        Node fast = this.head;
        Node slow = this.head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow.data;
    } 

    public static void main(String[] args) {

        // LLToStackAdapter list = new LLToStackAdapter();

        linkedList list = new linkedList();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);
        list.addLast(50);
        list.addLast(60);
        // list.addLast(70);
        // list.addLast(80);
        // System.out.println(list.mid1());
        System.out.println(list.mid1());
        // list.display();
        // list.reversePI();
        // list.display();

        // list.addLast(30);
        // list.addFirst(9);
        // list.display();
        // list.addFirst(7);
        // list.addLast(40);
        // list.display();
        // System.out.println(list.size());

        // System.out.println(list.removeFirst());
        // list.addAt(40, 2);
        // list.display();

        // System.out.println(list.removeAt(3));
        // System.out.println(list.getAt(3));
        // list.addLast(90);
        // list.addLast(85);
        // list.addLast(40);
        // list.addLast(70);
        // list.addLast(60);

        // list.display();

        // System.out.println(list.size());
        // list.removeFirst();
        // list.removeFirst();
        // list.removeFirst();
        // list.removeAt(3);
        // System.out.println(list.size());

        // list.display();

        // list.removeLast();
        // list.removeLast();
        // list.display();


    }    
}
