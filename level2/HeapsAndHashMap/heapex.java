import java.util.*;

public class heapex {

    private ArrayList<Integer> data;
    private boolean flag;

    public heapex(){
        this.data = new ArrayList<>();
        this.flag = false;
    }
    public heapex(boolean flag){
        this.data = new ArrayList<>();
        this.flag = flag;
    }

    private int checkPriority(int childIdx,int parentIdx){
        if(this.flag == true){
            if(data.get(parentIdx) < data.get(childIdx)){
                return 1;
            }    
        }else{
            if(data.get(parentIdx) > data.get(childIdx)){
                return 1;
            }
        }
        return 0;
    }

    private void swap(int i,int j){
        int temp = data.get(i);
        data.set(i,data.get(j));
        data.set(j,temp);
    }

    private void upheapify(int indx){
        // while(this.data.size() > 0){
        //     System.out.println("remved"+this.remove());
        // }
        if(indx == 0) return;
        int pi = (indx-1)/2;
        if(checkPriority(indx,pi) > 1){
            swap(indx,pi);
            upheapify(pi);
        }
    }

    public void add(int val){
        data.add(val);
        upheapify(data.size()-1);
    }

    private void downheapify(int indx){
        int tempIndx = indx;

        int lc = 2*indx+1;
        int rc = 2*indx+2;

        if(lc < this.data.size() && checkPriority(lc, tempIndx) > 0){
            tempIndx = lc;
        }

        if(rc < this.data.size() && checkPriority(rc, tempIndx) > 0){
            tempIndx = rc;
        }

        if(indx != tempIndx){
            swap(indx, tempIndx);
            downheapify(tempIndx);
        }
    }

    public int remove(){
        if(data.size() == 0) return -1;
        swap(0, data.size()-1);
        int val = data.remove(data.size()-1);
        downheapify(0);
        return val;
    }

    public int peek(){
        if(data.size() == 0) return -1;
        return data.get(0);
    }

    public int size(){
        return data.size();
    }

    public void display(){
        System.out.println(data);
    }
    public static void main(String[] args) {
        heapex obj = new heapex(true);
        obj.add(10);
        obj.add(20);
        obj.add(15);
        obj.add(35);
        obj.add(30);
        obj.add(25);
        obj.add(27);
        obj.add(40);
        obj.add(42);
        while(obj.size() > 0){
            System.out.println(obj.remove());
        }
    }
}
