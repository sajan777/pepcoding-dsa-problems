import java.util.*;

public class LRUCache{
    
    class Node{
        int key;
        int val;
        Node next;
        Node prev;
    
        public Node(int key,int val){
            this.key = key;
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }
    private Node head = null;
    private Node tail = null;
    private int size = 0;
    private int capacity;
    private HashMap<Integer,Node> map = null;

    // add
    public void addLast(Node node){
        if(this.size == 0){
            this.head = this.tail = node;
        }else{
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        this.size++;
    }

    // Remove
    public int removeFirst(){
        Node rem = this.head;
        this.head = this.head.next;
        this.head.prev = null;
        rem.next = null;
        this.size--;
        return rem.key;
    }
    public void removeNode(Node node){
        if(this.size == 1){
            this.head = this.tail = null;
        }else if(this.head == node){
            this.head = this.head.next;
            this.head.prev = null;
        }else if(node == this.tail){
            this.tail = this.tail.prev;
            this.tail.next = null;
        }else{
            Node nm1 = node.prev;
            Node np1 = node.next;
            nm1.next = np1;
            np1.prev = nm1;
        }
        this.size--;
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            // get kara to top par le aao latest used app and return the value of it
            this.put(key, map.get(key).val); 
            return map.get(key).val;
        }else{
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            // updation
            Node node = map.get(key);
            node.val = value;
            map.put(key, node);
            removeNode(node);
            addLast(node);
        }else{
            // insertion
            // 1.add new app in recent tab
            Node nn = new Node(key,value);
            addLast(nn);
            map.put(key, nn);
            // 2.if no of tabs crossed cap remove least used app (remove first)
            if(this.size > this.capacity){
                int remKey = removeFirst();
                map.remove(remKey);
            }
        }
    }

}