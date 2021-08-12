import java.util.*;

public class linkedlistT {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public static class Node {
        int val;
        Node next;
        Node random;
    
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    // Reverse a linkedlist
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // Middle of a linked list
    public ListNode midNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next; //pep
        // ListNode fast = head; //leetcode mid1
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // pallindrome 
    public boolean isPalindrome(ListNode head) {
        ListNode head1 = head;
        ListNode mid = midNode(head);
        ListNode head2 = mid.next;
        head2 = reverseList(head2);
        mid.next = null;
        while(head1 != null && head2 != null){
            if(head1.val != head2.val) return false;
            head1 = head1.next;
            head2 = head2.next;
        }
        return true;
    }
    
    // Fold a linked list Leetcode->reorder linkedlist
    public void reorderList(ListNode head) {   
        ListNode head1 = head;
        ListNode mid = midNode(head);
        ListNode head2 = mid.next;
        mid.next = null;
        head2 = reverseList(head2);
        while(head1 != null && head2 != null){
            ListNode n1 = head1.next;
            ListNode n2 = head2.next;

            head1.next = n2;
            head1 = n1;
            head2.next = n1;
            head2 = n2;
        }
    }
    // unfold a linkedlist
    public void unfold(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return;
        ListNode oh = new ListNode(-1);
        ListNode t1 = oh;
        
        ListNode eh = new ListNode(-1);
        ListNode t2 = eh;
        
        int count = 0;
        for(ListNode i=head;i!=null;i = i.next){
            if(count % 2 == 0){
                t2.next = i;
                t2 = i;
            }else{
                t1.next = i;
                t1 = i;
            }
            count += 1;
        }
        t1.next = null;
        t2.next = null;
        oh = reverseList(oh.next);
        t2.next = oh;
    }

    // Merge two sorted list
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode t1 = l1;
        ListNode t2 = l2;
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        while(t1 != null && t2 != null){
            if(t1.val < t2.val){
                temp.next = t1;
                t1 = t1.next;
            }else{
                temp.next = t2;
                t2 = t2.next;
            }
            temp = temp.next;
        }
        if(t1 != null){
            temp.next = t1;
        }else if(t2 != null){
            temp.next = t2;
        }
        return dummy.next;   
    }
    // Merge k sorted Lists
    public class Pair implements Comparable<Pair>{
        ListNode node;

        public Pair(ListNode node){
            this.node = node;
        }
        public int compareTo(Pair o){
            return this.node.val - o.node.val;
        }
    }
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for(int i=0;i<lists.length;i++){
            if(lists[i] != null){
                ListNode node = lists[i];
                pq.add(new Pair(node));    
            }
        }
        while(pq.size() > 0){
            Pair rem = pq.remove();
            temp.next = rem.node;
            temp = temp.next;
            if(rem.node.next != null){
                pq.add(new Pair(rem.node.next));
            }
        }
        return dummy.next;
    }
    // MergeSort O(1); (in place)
    public ListNode sortList(ListNode head1) {
        if(head1 == null || head1.next == null) return head1;

        ListNode mid = midNode(head1);
        ListNode head2 = mid.next;
        mid.next = null;
        
        head1 = sortList(head1);
        head2 = sortList(head2);
        ListNode res = mergeTwoLists(head1, head2);
        return res;
    }
    //Leetcode 19 Remove Nth Node From End of List
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // step 1 (forward ptr move by n)
        ListNode forward = head;
        ListNode curr = null;
        // step 2 point head with current pointer
        for(int i=0;i<n;i++){
            forward = forward.next;
        }
        // in this case n is equal to size means remove head
        if(forward == null) return head.next;
        curr = head;
        // step 3 move curr and forward ptr simultaneously
        while(forward.next != null){
            forward = forward.next;
            curr = curr.next;
        }
        // step 4 disconnect the nth node
        curr.next = curr.next.next;
        return head;
    }
    // 141. Linked List Cycle
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
        return false;        
    }
    // 142. Linked List Cycle II
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) break;
        }
        // there is no cycle in linked list return null;
        if(fast == null || fast.next == null) return null;
        fast = head;
        while(fast != slow){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
    // 160. Intersection of Two Linked Lists
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tail = headA;
        while(tail.next != null){
            tail = tail.next;
        }
        // connect the tail to headA (make it a cycle)
        tail.next = headA;
        // now find cycle start point in headB List
        ListNode res = detectCycle(headB);
        // reset the tail
        tail.next = null;
        return res;   
    }
    // 328. Odd Even Linked List
    public ListNode oddEvenList(ListNode head) {
        int idx = 1;
        ListNode temp = head;
        
        ListNode oddHead = new ListNode();
        ListNode oddPtr = oddHead;

        ListNode evenHead = new ListNode();
        ListNode evenPtr = evenHead;

        while(temp != null){
            if(idx % 2 == 0){
                evenPtr.next = temp;
                evenPtr = evenPtr.next;
            }else{
                oddPtr.next = temp;
                oddPtr = oddPtr.next;
            }
            idx+=1;
            temp = temp.next;
        }
        oddPtr.next = evenHead.next;

        return oddHead.next;
    }
    // Leetcode 2 add two linked lists
    // on pep we need to reverse the lists
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp = new ListNode();
        ListNode res = temp;
        int carry = 0;  
        while(l1 != null || l2 != null || carry != 0){
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            int sum = val1+val2+carry;
            carry = sum/10;
            temp.next = new ListNode(sum%10);
            temp = temp.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if(carry > 0){
            temp.next = new ListNode(carry);
            temp = temp.next;
        }
        return res.next;
    }

    // Subtract two linkedlist
    public ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp = new ListNode(-1);
        ListNode res = temp;

        l1 = reverseList(l1);
        l2 = reverseList(l2);
        int borrow = 0;
        while(l1 != null || l2 != null){
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            int sub = 0;
            if(v1 > v2){
                sub = (v1+borrow)-v2;
            }else{
                sub = (v2+borrow)-v1;
            }
            if(sub < 10){
                sub += 10;
                borrow = -1;
            }else{
                borrow = 0;
            }
            temp.next = new ListNode(sub%10);
            temp = temp.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        res =  reverseList(res.next);
        return res;
    }
    // Reverse using add First Technique
    static ListNode thead;
    static ListNode ttail;
    public static void addFirst(ListNode node){
        if(thead == null){
            thead = ttail = node;
        }else{
            node.next = thead;
            thead = node;
        }
    }

    public static ListNode reverseUsingAddFirst(ListNode head){
        thead = ttail = null;
        ListNode next = head;
        while(next != null){
            ListNode i = next;
            next = i.next;
            i.next = null;
            addFirst(i);
        }
        return thead;
    }
    // Multiply two linked list
    public  ListNode multiplyTwoLL(ListNode l1, ListNode l2) {
        ListNode res = null;
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        int zeroCount = 0;
        ListNode i = l1;
        while(i != null){
            // num which we have to add in res
            ListNode num = new ListNode(-1);
            ListNode k = num;
            // add Zero in it
            for(int z=0;z<zeroCount;z++){
                ListNode node = new ListNode(0);
                k.next = node;
                k = node;
            }
            int ival = i.val;
            int carry = 0;
            ListNode j = l2;
            while(j != null || carry != 0){
                int jval =  j == null ? 0 : j.val;
                int prod = ival*jval+carry;
                carry = prod/10;
                k.next = new ListNode(prod%10);
                j = j == null ? null : j.next;
                k = k.next;
            }
            // res = res+num
            res = addTwoNumbers(res, num.next);   
            zeroCount++;
            i = i.next;
        }
        res = reverseList(res);
        return res;
    }
    // remove duplicates from sorted LinkedList
    public ListNode deleteDuplicates(ListNode head) {
        ListNode headt = head;
        ListNode curr = head;
        ListNode next = curr.next;
        while(next != null){
            if(curr.val != next.val){
                curr.next = next;
                curr = next;
            }
            next = next.next;
        }
        curr.next = next;
        return headt;
    }
    // delete all duplicates from sorted ll
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode thead =  new  ListNode(-1);
        ListNode i  = thead;
        ListNode curr = head;
        i.next = curr;
        while(curr != null){
            curr = curr.next;
            boolean flag = false;
            while(curr != null && i.next.val == curr.val){
                flag = true;
                curr= curr.next;
            }
            if(flag == true){
                i.next = curr;
            }else{
                i = i.next;
            }

        }
        return thead.next;
    }

    // copy linked list with random pointer
    public Node copyRandomList(Node head) {
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
    // clone random pointers in linedklist using hashmap
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

        Node head1 = head;
        Node head2 = dummy.next;
        t2 = head2;
        // 2. make a hashmap of node vs node
        HashMap<Node,Node> map = new HashMap<>();
        while(head1 != null){
            map.put(head1, head2);

            head1 = head1.next;
            head2 = head2.next;
        }
        // 3. Make connection of random pointers oNode = originalNode
        for(Node oNode:map.keySet()){
            Node cNode = map.get(oNode); //clonedNode
            Node orandom = oNode.random; //orginalNodeRandom
            
            cNode.random = orandom == null ? null : map.get(orandom);
        }
        return t2;
    }
    // mege k sorted linked list using divide and conquer
    public ListNode mergeKLists(ListNode[] lists,int st,int end){
        if(st == end){
            return lists[st];
        }
        int mid = (st+end)/2;
        ListNode l1 = mergeKLists(lists, st, mid);
        ListNode l2 = mergeKLists(lists, mid+1, end);
        ListNode res = mergeTwoLists(l1,l2);
        return res;
    }
    //Segregate Even And Odd Nodes In A Linkedlist 
    public static ListNode segregateEvenOdd(ListNode head) {
        ListNode odd_head = new ListNode(-1);
        ListNode t1 = odd_head;
        
        ListNode even_head = new ListNode(-1);
        ListNode t2 = even_head;
      
        ListNode temp = head;
        while(temp != null){
            if(temp.val%2  == 0){
                t2.next = temp;
                t2 = t2.next;
            }else{
                t1.next = temp;
                t1 = t1.next;
            }
            temp = temp.next;
        }
        t2.next = odd_head.next;
        t1.next = null;
        return even_head.next;

    }
    // Segregate 01 Node Of Linkedlist Over Swapping Nodes (pointers change)
    public static ListNode segregate01(ListNode head) {
        ListNode zero_head = new ListNode(-1);
        ListNode t1 = zero_head;
        
        ListNode one_head = new ListNode(-1);
        ListNode t2 = one_head;
      
        ListNode temp = head;
        while(temp != null){
            if(temp.val == 0){
                t1.next = temp;
                t1 = t1.next;
            }else{
                t2.next = temp;
                t2 = t2.next;
            }
            temp = temp.next;
        }
        t1.next = one_head.next;
        t2.next = null;
        return zero_head.next;
    }
    // Segregate 01 Node Of Linkedlist Over Swapping Nodes data
    public static ListNode segregate01_data(ListNode head) {
        ListNode j = head;
        ListNode i = head;
        while(i != null){
            if(i.val == 1){
                i = i.next;
            }else{
                int temp = i.val;
                i.val = j.val;
                j.val = temp;
                i=i.next;
                j=j.next;
            }
        }
        return head;
    }
    // Segregate 012 Node Of Linkedlist Over Swapping Nodes
    public static ListNode segregate012(ListNode head) {
        ListNode zero_head = new ListNode(-1);
        ListNode t0 = zero_head;
        ListNode one_head = new ListNode(-1);
        ListNode t1 = one_head;
        ListNode two_head = new ListNode(-1);
        ListNode t2 = two_head;

        ListNode temp = head;
        while(temp != null){

            if(temp.val == 0){
                t0.next = temp;
                t0 = t0.next;
            }else if(temp.val == 1){
                t1.next = temp;
                t1 = t1.next;
            }else if(temp.val == 2){
                t2.next = temp;
                t2 = t2.next;
            }

            temp = temp.next;
        }
        t0.next = null;
        t1.next = null;
        t2.next = null;

        t1.next = two_head.next;
        t0.next = one_head.next;

        return zero_head.next;
    }
    // Segregate 012 Node Of Linkedlist By Swapping Data
    public static void swap_nodes(ListNode v1,ListNode v2){
        int temp = v1.val;
        v1.val = v2.val;
        v2.val = temp;
    }
    public static ListNode segregate012_data(ListNode head) {
        ListNode i = head;
        ListNode j = head;
        ListNode k = head;
        while(i != null){
            if(i.val == 0){
                swap_nodes(i, j);
                swap_nodes(j, k);
                i = i.next;
                j = j.next;
                k = k.next;
            }else if(i.val == 1){
                swap_nodes(i, j);
                i = i.next;
                j = j.next;
            }else if(i.val == 2){
                i = i.next;
            }
        }
        return head;
    }
    // Segregate Node Of Linkedlist Over Last Index. (return pivot)(pivot will be at its sorted location)
    public static ListNode segregateOnLastIndex(ListNode head) {
        // find last indx
        ListNode pivot = head;
        while(pivot.next != null){
            pivot = pivot.next;
        }
        
        ListNode smaller_head = new ListNode(-1);
        ListNode small = smaller_head;
        ListNode larger_head = new ListNode(-1);
        ListNode large = larger_head;

        ListNode temp = head;
        while(temp != null){
            if(temp.val <= pivot.val){
                small.next = temp;
                small = small.next;
            }else{
                large.next = temp;
                large = large.next;
            }
            temp = temp.next;
        }
        large.next = null;

        small.next = larger_head.next;
        return pivot;
    }

    // Segregate Node Of Linkedlist Over Pivot Index (pivot can be any indx)
    public static ListNode segregate(ListNode head, int pivotIdx) {
        // find pivot 
        ListNode pivot = head;
        for(int i=0;i<pivotIdx;i++){
            pivot = pivot.next;
        }

        ListNode smaller_head = new ListNode(-1);
        ListNode small = smaller_head;
        ListNode larger_head = new ListNode(-1);
        ListNode large = larger_head;

        ListNode temp = head;
        while(temp != null){
            if(temp != pivot){
                if(temp.val <= pivot.val){
                    small.next = temp;
                    small = small.next;
                }else{
                    large.next = temp;
                    large = large.next;
                }
            }
            temp = temp.next;
        }
        large.next = null;

        small.next = pivot;
        pivot.next = larger_head.next;
        return smaller_head.next;
    }


    public static void main(String[] args) {
        
    }
}
