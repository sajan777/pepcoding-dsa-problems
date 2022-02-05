import java.util.*;

public class genericHmap<K,V>{
    
    public class Node {
        K key;
        V value;
        public Node(K key,V value){
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Node>[] bucket;
    private int size = 0;

    public void init(int cap){
        bucket = new LinkedList[cap];
        this.size = 0;
        for(int bi=0;bi<cap;bi++){
            bucket[bi] = new LinkedList<>();
        }
    }
    public genericHmap(){
        init(4);
    }
    public int hashFunction(K key){
        int bi = Math.abs(key.hashCode()) % bucket.length;
        return bi;
    }
    private int search(K key,int bi){
        int di = 0;

        for(Node node:bucket[bi]){
            if(node.key.equals(key) == true){
                return di;
            }
            di++;
        }

        return -1;
    }

    private void rehash(){
        LinkedList<Node>[] ob = bucket;
        init(2*bucket.length);

        for(int i=0;i<ob.length;i++){
            for(Node node:ob[i]){
                put(node.key, node.value);
            }
        }

    }

    public void put(K key,V value){
        int bi = hashFunction(key);
        int di = search(key,bi);
        if(di == -1){
            bucket[bi].addLast(new Node(key,value));
            this.size++;
        }else{
            bucket[bi].get(di).value = value;
        }
        int n = this.size;
        int N = bucket.length;
        double lambda = n*1.0/N;
        if(lambda > 2){
            rehash();
        }
    }

    public V get(K key){
        int bi = hashFunction(key);
        int di = search(key, bi);
        if(di == -1){
            return null;
        }else{
            Node node = bucket[bi].get(di);
            return node.value;
        }
    }

    public V remove(K key){
        int bi = hashFunction(key);
        int di = search(key, bi);
        if(di == -1){
            return null;
        }else{
            Node node = bucket[bi].remove(di);
            this.size--;
            return node.value;
        }
    }

    public int size(){
        return this.size;
    }

    public ArrayList<K> keySet() {
        ArrayList<K> list = new ArrayList<>();
        for(int bi=0;bi<bucket.length;bi++){
            for(Node node:bucket[bi]){
                list.add(node.key);
            }
        }
        return list;
    }

    public void display(){
        for(int bi=0;bi<bucket.length;bi++){
            for(Node node:bucket[bi]){
                System.out.print("["+node.key+" = " + node.value+ "], ");
                System.out.println();
            }
            System.out.println();
        }
        System.out.println(".");
    }
    public static void main(String[] args) {
        genericHmap<Integer,String> ghmap = new genericHmap<>();
        ghmap.put(1, "value1");
        ghmap.put(21, "value21");
        ghmap.put(11, "value11");
        ghmap.put(12, "value12");
        ghmap.put(111, "value111");
        ghmap.put(16, "value16");
        ghmap.put(1, "value-updated");
        ghmap.put(18, "value18");
        ghmap.put(1124, "value");
        ghmap.put(1098, "value");
        ghmap.put(16, "value16-updated");
        ghmap.put(18765, "value18765");
        
        ghmap.display();

        ghmap.remove(111);
        ghmap.remove(18765);

        ghmap.display();
    }
}
