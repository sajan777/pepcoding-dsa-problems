import java.util.*;

public class HashMapEx {
    LinkedList<Integer>[] bucket;
    private int size;

    private void init(int cap){
        bucket = new LinkedList[cap];
        for(int i=0;i<bucket.length;i++){
            bucket[i] = new LinkedList<>();
        }
        this.size = 0;
    }
    public int search(int bi,Integer key){
        int di = 0;
        for(int i=0;i<bucket[bi].size();i++){
            Integer val = bucket[bi].get(i);
            if(key == val){
                return di;
            }
            di++;
        }
        return -1;
    }
    public int hashFunction(Integer key){
        int bi = Math.abs(key.hashCode()) % bucket.length;
        return bi;
    }
    public int get(Integer key){
        int bi = hashFunction(key);
        int di = search(bi,key);
        if(di == -1) return -1;
        return bucket[bi].get(di);
    }

    public void put(Integer key,int val){
        int bi = hashFunction(key);
        int di = search(bi,key);
        if(di == -1){
            bucket[bi].add(val);
            this.size++;
        }else{
            bucket[bi].set(di, val);
        }
        int n = this.size;
        int N = bucket.length;
        int lambda = n / N;
        if(lambda > 2){
            rehash();
        }
    }

    public int size(){
        return this.size;
    }
    public HashMapEx(int cap){
        init(cap); 
    }
    public static void main(String[] args) {
        HashMapEx hmap = new HashMapEx(4);
    }
}
