import java.util.*;
public class QuickSort {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // partition pair
    public static class PPair{
        ListNode pnm1 = null; //partition nodeminus1
        ListNode pn = null; // parittion node
    }

    // quick pair
    public static class qPair{
        // QPair -> quickSortPair
        ListNode nhead;
        ListNode ntail;
    }

    public static PPair partition(ListNode head,ListNode pivot){
        ListNode small_head = new ListNode(-1);
        ListNode small = small_head;

        ListNode greater_head = new ListNode(-1);
        ListNode greater = greater_head;

        ListNode temp = head;
        ListNode pnm1 = null; // pivot node minus 1;
        while(temp != pivot){
            if(temp.val <= pivot.val){
                small.next = temp;
                small = small.next;
                
            }else{
                greater.next = temp;
                greater = greater.next;
            }
            temp = temp.next;
        }
        pnm1 = small == small_head ? null : small;
        small.next = pivot;
        pivot.next = greater_head.next;
        greater.next = null;

        PPair res = new PPair();
        res.pn = pivot;
        res.pnm1 = pnm1;
        return res;
    }

    public static qPair quickSortHelp(ListNode head,ListNode tail){

        ListNode pivot = tail;
        PPair pp = partition(head,pivot);
        
        ListNode head1 = head;
        ListNode tail1 = pp.pnm1;
        tail1.next = null;

        ListNod head2 = pivot.next;
        ListNode tail2 = tail;
        pivot.next = null;

        qPair left = null,right = null;
        if(tail1 != null){
            left = quickSortHelp(head1, tail1);
        }
        if(head2 != null){
            right = quickSortHelp(head2, tail2);
        }

        left.ntail.next = pivot;
        pivot.next = right.nhead;

        qPair res = new qPair();
        res.nhead = left.nhead;
        res.ntail = right.ntail;
        return res;
    }

    // Quick sort ll
    public static ListNode quickSort(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode tail = head;
        while(tail.next != null){
            tail = tail.next;
        }

        qPair res = quickSortHelp(head,tail);
        return res.nhead;

    }

    public static void main(String[] args) {
        
    }
}
