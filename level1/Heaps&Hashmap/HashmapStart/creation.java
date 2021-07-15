import java.util.*;

class hashmap{
    private class Node{
        String key;
        int value;

        public Node(String key,int value){
            this.key = key;
            this.value = value;
        }
    }
    private int size = 0;
    private LinkedList<Node>[] bucket;
    
    private void init(int cap){
        bucket = new LinkedList[cap];
        // initalise every location of bucket arr
        for(int i=0;i<cap;i++){
            bucket[i] = new LinkedList<>();
        }
        this.size = 0;
    }
   
    public hashmap(){
        // loop and init the bucket array with LL as default is null
        init(4);
    }
    private int hashFunction(String key){
        // in java every data type has hashcode function hashcode->Always integer
        int bi = Math.abs(key.hashCode()) % bucket.length;
        // 0<=bi<bucket.length;
        return bi;
    }
    private int searchInBucket(String key,int bi){
        int di = 0;

        for(Node node:bucket[bi]){
            if(node.key.equals(key) == true){
                return di;
            }
            di++;
        }
        
        // ntimes Overall 0(n^2)
        // for(int i=0;i<bucket[bi].size();i++){
        //     Node node = bucket[bi].get(i); //0(n)
        // }
        return -1 ;
    }
    private void rehash(){
        LinkedList<Node>[] ob = bucket;
        init(2 * bucket.length);

        // travel on old bucket and fill the new bucket
        for(int i=0;i<ob.length;i++){
            for(Node node:ob[i]){
                put(node.key, node.value);
            }
        }
    }

    public void put(String key,int value) {
        
        int bi = hashFunction(key);

        int di = searchInBucket(key,bi);

        if(di == -1){
            // not present -> insert
            bucket[bi].addLast(new Node(key,value));
            this.size++;
        }else{
            // present -> update
            bucket[bi].get(di).value = value;
        }
        int n = this.size;
        int N = bucket.length;
        double lambda = n*1.0/N;
        if(lambda > 2){
            rehash();
        }
        
    }
    public int remove(String key) {
        int bi = hashFunction(key);
        int di = searchInBucket(key, bi);
        if(di == -1){
            return -1;
        }else{
            // remove node in linkedlist
            Node rem = bucket[bi].remove(di);
            // return value;
            this.size--;
            return rem.value;
        }
    }
    public int get(String key) {
        int bi = hashFunction(key);
        int di = searchInBucket(key, bi);
        if(di == -1){
            return -1;
        }else{
            // remove node in linkedlist
            Node g = bucket[bi].get(di);
            // return value;
            return g.value;
        }
    }
    public boolean containsKey(String key) {
        int bi = hashFunction(key);
        int di = searchInBucket(key, bi);
        if(di == -1){
            return false;
        }else{
            return true;
        }
    }


    public ArrayList<String> keySet() {
        ArrayList<String> list = new ArrayList<>();
        for(int bi=0;bi<bucket.length;bi++){
            for(Node node:bucket[bi]){
                list.add(node.key);
            }
        }
        return list;
    }

    public int size() {
        return this.size;
    }
    
    public void display() {
        for(int bi=0;bi<bucket.length;bi++){
            for(Node node:bucket[bi]){
                System.out.print("["+node.key+" = " + node.value+ "], ");
                System.out.println();
            }
            System.out.println();
        }
        System.out.println(".");
    }

    public void hashMapView() {
        for(int bi=0;bi<bucket.length;bi++){
            System.out.print("Bucket: "+bi+ " -> ");
            for(Node node:bucket[bi]){
                System.out.print("["+node.key+" = " + node.value+ "], ");
            }
            System.out.println(".");
        }
    }
}

public class creation {
    public static void fun() {
        hashmap hmap = new hashmap();
        hmap.put("India", 125);
        hmap.put("Pakistan", 90);
        hmap.put("US", 70);
        hmap.put("Australia", 100);
        hmap.put("Japan", 50);
        hmap.put("Nepal", 12);
        hmap.put("Bhutan", 55);
        hmap.put("Egypt", 75);
        hmap.put("Bhutan", 55);
        hmap.display();
        hmap.hashMapView();
        hmap.put("India", 130);
        hmap.display();
        hmap.hashMapView();
    }
    public static void main(String[] args) {
        fun();
    }    
}
