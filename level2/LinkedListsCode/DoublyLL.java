import java.util.*;

class DoublyLinkedList{
    
    private class Node{
        int data;
        Node next;
        Node prev;
    

        public Node(){
            this.data = 0;
            this.next = this.prev = null;
        }
        public Node(int data){
            this.data = data;
            this.next = this.prev = null;
        }
        
        public Node(int data,Node next,Node prev){
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    private Node getNthNode(int pos){
        Node temp = this.head;
        while(pos-->0){
            temp = temp.next;
        }
        return temp;
    }

    // ADD
    private void handleAddWhenSize0(int val){
        Node nn = new Node(val);
        this.head = this.tail = nn;
        this.size++;
    }
    public void addFirst(int val){
        if(this.size == 0){
            handleAddWhenSize0(val);
        }else{
            Node nn = new Node(val);
            nn.next = this.head;
            this.head.prev = nn;
            this.head = nn;
            this.size++;
        }
    }
    public void addLast(int val){
        if(this.size == 0){
            handleAddWhenSize0(val);
        }else{
            Node nn = new Node(val);
            nn.prev = this.tail;
            this.tail.next = nn;
            this.tail = nn;
            this.size++;
        }
    }
    public void addAt(int val,int pos){
        if(pos < 0 || pos > this.size){
            System.out.println("Invalid position");
            return;
        }else if(pos == 0){
            addFirst(val);
        }else if(pos == this.size){
            addLast(val);
        }else{
            Node nm1 = getNthNode(pos-1);
            Node nn = new Node(val);
            nn.next = nm1.next;
            nm1.next.prev = nn;
            nm1.next = nn;
            nn.prev = nm1;
            this.size++;
        }
    }
    public void addAfter(int val,Node node){
        if(node == this.tail){
            addLast(val);
            return;
        }
        Node nn = new Node(val);
        nn.next = node.next;
        node.next.prev = nn;
        node.next = nn;
        nn.prev = node;
        this.size++;
    }
    public void addBefore(int val,Node node){
        if(node == this.head){
            addFirst(val);
            return;
        }
        Node nn = new Node(val);
        nn.prev = node.prev;
        node.prev.next = nn;
        nn.next = node;
        node.prev = nn;
        this.size++;
    }

    // GET
    public int getFirst(){
        if(this.size == 0){
            return -1;
        }
        return this.head.data;
    }
    public int getLast(){
        if(this.size == 0){
            return -1;
        }
        return this.tail.data;
    }
    public int getAt(int pos){
        if(pos < 0 || pos > this.size){
            return -1;
        }else{
            Node temp = getNthNode(pos);
            return temp.data;
        }
    }

    // DISPLAY
    public void forwardDisplay(){
        Node temp = this.head;
        while(temp != null){
            System.out.print(temp.data+" ");
            temp = temp.next;
        }
    }
    public void backwardDisplay(){
        Node temp = this.tail;
        while(temp != null){
            System.out.print(temp.data+" ");
            temp = temp.prev;
        }
    }

    // SIZE
    public int size(){
        return this.size;
    }

    // REMOVE
    public int handleRemoveWhenSize1(){
        int val = this.head.data;
        this.size = 0;
        this.head = this.tail = null;
        return val;
    }
    public int removeFirst(){
        if(this.size == 0){
            return -1;
        }else if(this.size == 1){
            return handleRemoveWhenSize1();
        }else{
            int val = this.head.data;
            this.head = this.head.next;
            this.head.prev = null;
            this.size--;
            return val;
        }
    }
    public int removeLast(){
        if(this.size == 0){
            return -1;
        }else if(this.size == 1){
            return handleRemoveWhenSize1();
        }else{
            int val = this.tail.data;
            this.tail = this.tail.prev;
            this.tail.next = null;
            this.size--;
            return val;
        }
    }
    public int removeAt(int pos){
        if(pos < 0 || pos > this.size){
            return -1;
        }else if(pos == 0){
            return removeFirst();
        }else if(pos == this.size){
            return removeLast();
        }else{
            // Node nm1 = getNthNode(pos-1);
            // int val = nm1.next.data;
            // nm1.next = nm1.next.next;
            // return val;
            Node node = getNthNode(pos);
            return removeNode(node);
        }
    }
    private int removeAfter(Node node){
        if(node.next == null) return -1;
        return removeNode(node.next);
    }
    private int removeBefore(Node node){
        if(node.next == null) return -1;
        return removeBefore(node.prev);
    }
    public int removeNode(Node node){
        if(node == this.head){
            return removeFirst();
        }else if(node == this.tail){
            return removeLast();
        }else{
            int val = node.data;
            Node nm1 = node.prev;
            Node np1 = node.next;
            nm1.next = np1;
            np1.prev = nm1;
            this.size--;
            return val;
        }
    }


}

public class DoublyLL {
    
    public static void main(String[] args) {
        DoublyLinkedList dbll = new DoublyLinkedList();
        dbll.addLast(10);
        dbll.addLast(20);
        dbll.addLast(30);
        dbll.addLast(40);
        dbll.addLast(50);
        dbll.addLast(60);
        dbll.addAt(35, 3);
        
        dbll.forwardDisplay();
        System.out.println();
        dbll.backwardDisplay();
    }
}
