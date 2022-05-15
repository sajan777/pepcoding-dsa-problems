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
    // Shreesh app best
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
    // https://leetcode.com/problems/reconstruct-itinerary/submissions/ -> check this TLE


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
    // https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/submissions/ (also with -ve values do take a look the current code is failing)
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

    // Largest Subarray With Zero Sum O(N + N)
    // https://practice.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1/
    public static int solution(int[] arr) {
		int length = 0;
        int prefix = 0;
        HashMap<Integer,Integer> hmap = new HashMap<>();
        hmap.put(0,-1);
        for(int i=0;i<arr.length;i++){
            prefix += arr[i];
            if(hmap.containsKey(prefix)) {
                length = Math.max(length,i-hmap.get(prefix));
            }else{
                hmap.put(prefix,i);
            }
        }
        return length;
	}
    
    // Count Of All Subarrays With Zero Sum O(N + N)
    public static int solution2(int[] arr) {
		int count = 0;
        int prefix = 0;
        HashMap<Integer,Integer> hmap = new HashMap<>();
        hmap.put(0,1);
        for(int i=0;i<arr.length;i++){
            prefix += arr[i];
            if(hmap.containsKey(prefix)) {
                count += hmap.get(prefix);
                hmap.put(prefix,hmap.get(prefix)+1);
            }else{
                hmap.put(prefix,1);
            }
        }
        return count;
	}

    // Longest Subarray With Equal Number Of Zeroes And Ones
    public static int solution3(int[] arr) {
        for(int i=0;i<arr.length;i++){
            if(arr[i] == 0) arr[i] = -1;
        }
        return solution(arr);
    }

    // Count Of Subarrays With Equal Number Of Zeroes And Ones
    public static int solution4(int[] arr) {
        for(int i=0;i<arr.length;i++){
            if(arr[i] == 0) arr[i] = -1;
        }
        return solution2(arr);
    }

    // Maximum Size Subarray Sum Equals K
    public static int maxLenSubarray(int[] arr, int k) {
        int length = 0;
        int prefix = 0;
        HashMap<Integer,Integer> hmap = new HashMap<>();
        hmap.put(0,-1);
        for(int i=0;i<arr.length;i++){
            prefix += arr[i];
            if(hmap.containsKey(prefix - k)) {
                length = Math.max(length,i-hmap.get(prefix-k));
            }
            if(!hmap.containsKey(prefix)){
                hmap.put(prefix,i);
            }
        }
        return length;
    }
    // Count Of Subarrays Having Sum Equals To K
    public static int solution5(int[] arr, int target){
		int count = 0;
        int prefix = 0;
        HashMap<Integer,Integer> hmap = new HashMap<>();
        hmap.put(0,1);
        for(int i=0;i<arr.length;i++){
            prefix += arr[i];
            if(hmap.containsKey(prefix - target)) {
                count += hmap.get(prefix - target);
            }
            if(hmap.containsKey(prefix)){
                hmap.put(prefix,hmap.get(prefix)+1);
            }else{
                hmap.put(prefix,1);
            }
        }
        return count;
	}
    // Longest Subarray With Sum Divisible By K
    public static int solution6(int[] arr, int k) {
        int length = 0;
        int sum = 0;
        HashMap<Integer,Integer> hmap = new HashMap<>();
        hmap.put(0,-1);
        for(int i=0;i<arr.length;i++){
            sum += arr[i];
            int rem = sum % k;
            if(rem < 0) rem += k;
            if(hmap.containsKey(rem)){
                length = Math.max(length,i - hmap.get(rem));
            }else{
                hmap.put(rem,i);
            }
        }
        return length;
    }
    // Count Of Subarrays With Sum Divisible By K
    public static int solution7(int[] arr, int k) {
        int count = 0;
        int sum = 0;
        HashMap<Integer,Integer> hmap = new HashMap<>();
        hmap.put(0,1);
        for(int i=0;i<arr.length;i++){
            sum += arr[i];
            int rem = sum % k;
            if(rem < 0) rem += k;
            if(hmap.containsKey(rem)){
                count += hmap.get(rem);
                hmap.put(rem,hmap.get(rem)+1);
            }else{
                hmap.put(rem,1);
            }
        }
        return count;
    }

    // Longest Subarray With Equal Number Of 0s 1s And 2s
    public static int solution7(int[] arr) {
        int length = 0;
        int c0 = 0;
        int c1 = 0;
        int c2 = 0;
        HashMap<String,Integer> hmap = new HashMap<>();
        hmap.put("0#0",-1);
        for(int i=0;i<arr.length;i++){
            int val = arr[i];
            if(val == 0){
                c0 += 1;
            }else if(val == 1){
                c1 += 1;
            }else if(val == 2){
                c2 += 1;
            }
            String diff = (c1-c0)+"#"+(c2-c1);
            if(hmap.containsKey(diff)){
                length = Math.max(length,i-hmap.get(diff));
            }else{
                hmap.put(diff,i);
            }
        }
        return length;
    }
    // Count Of Subarrays With Equal Number Of 0s 1s And 2s
    public static int solution8(int[] arr) {
        int count = 0;
        int c0 = 0;
        int c1 = 0;
        int c2 = 0;
        HashMap<String,Integer> hmap = new HashMap<>();
        hmap.put("0#0",1);
        for(int i=0;i<arr.length;i++){
            int val = arr[i];
            if(val == 0){
                c0 += 1;
            }else if(val == 1){
                c1 += 1;
            }else if(val == 2){
                c2 += 1;
            }
            String diff = (c1-c0)+"#"+(c2-c1);
            if(hmap.containsKey(diff)){
                count += hmap.get(diff);
                hmap.put(diff,hmap.get(diff)+1);
            }else{
                hmap.put(diff,1);
            }
        }
        return count;
    }

    // Task Completion
    public static void completeTask(int n, int m, int[] arr) {
		HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> s1 = new ArrayList<>();
        ArrayList<Integer> s2 = new ArrayList<>();
        boolean flag = true;

        for(int i=0;i<m;i++){
            set.add(arr[i]);
        }
        for(int i=1;i<=n;i++){
            if(!set.contains(i)) {
                if(flag == true){
                   s1.add(i);
                   flag = false; 
                }else {
                    s2.add(i);
                    flag = true;
                }
            }
        }
        for(int val:s1){
            System.out.print(val+" ");
        }
        System.out.println();
        for(int val:s2){
            System.out.print(val+" ");
        }
	}

    // Maximum Consecutive Ones - 1
    public static int solution9(int[] arr){
        int j = -1;
        int length = 0;
        int count = 0; //count for 0's
        for(int i=0;i<arr.length;i++){
            int val = arr[i];
            if(val == 0) count += 1;
            while(count > 1){
                j++;
                if(arr[j] == 0) count--;
            }
            length = Math.max(length, i-j);
        }
        return length;
    }

    // Maximum Consecutive Ones - 2
    public static int solution10(int[] arr, int k){
        int j = -1;
        int length = 0;
        int count = 0; //count for 0's
        for(int i=0;i<arr.length;i++){
            int val = arr[i];
            if(val == 0) count += 1;
            while(count > k){
                j++;
                if(arr[j] == 0) count--;
            }
            length = Math.max(length, i-j);
        }
        return length;
    }
    // Largest Subarray With Contiguous Elements (On2)
    public static int solution11(int[] arr) {
		int n = arr.length;
        int length = 0;
        for(int i=0;i<n;i++){
        	int max = arr[i];
            int min = arr[i];
            HashSet<Integer> set = new HashSet<>();
            for(int j=i;j<n;j++){
                max = Math.max(max,arr[j]);
                min = Math.min(min,arr[j]);
                if(set.contains(arr[j])) {
                    break;
                }
                set.add(arr[j]);
                int diff = max - min;
                if(j-i == diff){
                    length = Math.max(length,j-i+1);
                }
            }
        }
		return length;
	}

    // Longest Substring With At Most K Unique Characters
    public static int solution12(String str, int k) {
        int length = 0;
		int i = 0;
        int j = 0;
        HashMap<Character,Integer> hmap = new HashMap<>();
        while(i < str.length()){
            char ch = str.charAt(i);
            if(hmap.containsKey(ch)) {
                hmap.put(ch,hmap.get(ch) + 1);
                length = Math.max(length,j-i);
            }else {
                hmap.put(ch,1);
                length = Math.max(length,j-i);
            }
            while(hmap.size() > k && j < i){
               char remChar = str.charAt(j);
               if(hmap.get(remChar) == 1){
                 hmap.remove(remChar);
               }else{
                  hmap.put(remChar,hmap.get(ch) - 1);
               }
               j++;
            }
        }
        return length;
	}

    public static void main(String[] args) {
        
    }
}
