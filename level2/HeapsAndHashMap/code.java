import java.util.*;

public class code {

    // Number Of Employees Under Every Manager
    public static int getSize( HashMap<String,HashSet<String>> tree,String man,HashMap<String,Integer> res ){
        if(tree.containsKey(man) == false){
            res.put(man,0);
            return 1;
        }
        int count = 0;
        //get all the employees of the manager
        for(String emp : tree.get(man)){    
            count += getSize(tree,emp,res);
        }
        res.put(man,count);
        return count+1;
    }
    public static void employeesUnderManager(String[][] relations){
        HashMap<String,HashSet<String>> tree = new HashMap<>();
        String ceo = "";    
        for(String[] relation:relations){
            String emp = relation[0];
            String man = relation[1];
            if(emp.equals(man) == true){
                ceo = emp;
                continue;
            }
            if(tree.containsKey(man) == true) {
                tree.get(man).add(emp);
            }else{  
                HashSet<String> set = new HashSet<String>();
                set.add(man);
                tree.put(man, set);
            }
        }
        HashMap<String,Integer> res = new HashMap<String,Integer>();
        getSize(tree,ceo,res);
        for(String str:res.keySet()){
            System.out.println(str+" "+res.get(str));
        }
    }
    // String[][] relations = new String[n][2];
    // for(int i=0;i<n;i++){
    //     relations[i][0] = scn.next();
    //     relations[i][1] = scn.next();
    // }
    // employeesUnderManager(relations);



    // Find Itinerary From Tickets (My Approach)
    public static String itenary = "";
    public static void findItineraryRec2(HashMap<String, String> map,String src,String dest){
        if(map.get(src) == "" || src.equals(dest)){
            itenary += dest+".";
            return;
        }
        itenary += src+" -> ";
        findItineraryRec2(map,map.get(src),dest);
    }
    public static void findItenaray2(HashMap<String,String> map){
        String src = "";
        String dest = "";
        for(String key:map.keySet()){
            // bad approach for finding the src as it's O(n2) to eradicate we create a hashmap of true/fasle value of cities as src...
            if(map.containsValue(key) == false && src.length() == 0){
                src = key;
            }
            if(map.containsKey(map.get(key)) == false && dest.length() == 0){
                dest = map.get(key);
            }
        }
        findItineraryRec2(map,src,dest);
        System.out.println(itenary);
    }
    public static void findItenaray(HashMap<String,String> map){
        HashMap<String,Boolean> hmap = new HashMap<String,Boolean>(); 
        for(String city1:map.keySet()){
            String city2 = map.get(city1);
            hmap.put(city2,false);
            if(hmap.containsKey(city1) == false){
                hmap.put(city1,true);
            }
        }
        String src = "";
        for(String city:hmap.keySet()){
            if(hmap.get(city) == true){
                src = city;
                break;
            }
        }
        String path = "";
        while(map.containsKey(src) == true){
            path += src+" -> ";
            src = map.get(src);
        }
        path += src+".";
        System.out.println(path);
    }
    // findItenaray(map);


    //https://practice.geeksforgeeks.org/problems/count-distinct-elements-in-every-window/1
    // Count distinct elements in every window 
    public static ArrayList<Integer> solution(int[] arr, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        HashMap<Integer,Integer> hmap = new HashMap<>();
        for(int i=0;i<k-1;i++){
            int fq = hmap.getOrDefault(arr[i],0);
            hmap.put(arr[i],fq+1);
        }
        int j = 0;
        for(int i=k-1;i<arr.length;i++){
            int fq = hmap.getOrDefault(arr[i],0);
            hmap.put(arr[i],fq+1);
            res.add(hmap.size());
            if(hmap.get(arr[j]) == 1){
                hmap.remove(arr[j]);
            }else{
                hmap.put(arr[j],hmap.get(arr[j])-1);
            }
            j++;
        }
        return res;
	}

    // Check If An Array Can Be Divided Into Pairs Whose Sum Is Divisible By K
    // https://practice.geeksforgeeks.org/problems/array-pair-sum-divisibility-problem3257/1
    public static boolean canPair(int[] nums, int k) {
        HashMap<Integer,Integer> fmap = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int fq = fmap.getOrDefault(nums[i]%k, 0);
            fmap.put(nums[i]%k,fq+1);
        }
        for(int rem1: fmap.keySet()){
            if(rem1 == 0 || (k%2 == 0 && rem1 == k/2 )){
                if(fmap.get(rem1) % 2 != 0){
                    return false;
                }
            }else{
                int rem2 = k-rem1;
                if(fmap.containsKey(rem2) == false || fmap.get(rem1) != fmap.get(rem2)){
                    return false;
                }
            }
        }
        return true;    
    }
    

    public static void main(String[] args) {
        
    }
}
