import java.util.*;

public class hashmap {

    public static void fun() {
        HashMap<Character,Integer> map = new HashMap<>();

        // frequency map
        map.put('a', 1);
        map.put('b', 1);
        map.put('c', 1);
        map.put('d', 1);
        map.put('e', 1);
        map.put('f', 1);
        System.err.println(map);

        System.out.println(map.containsValue(10));
        System.out.println(map.containsKey('a'));
        if(map.containsKey('a')){
            map.put('a', map.get('a')+1);
        }
        System.out.println(map);
        System.out.println(map.keySet());

        // iterations
        Set<Character> st = map.keySet(); //keys are in random order!!!!!
        for(Character ch:st){
            System.out.print(ch+" ");
            map.remove(ch);
        }
        // remove
        map.remove("key1");


    }

    // frequency map
    public static void highestFrequencyChar() {
        String s = "zmszeqxllzvheqwrofgcuntypejcxovtaqbnqyqlmrwitc";
        HashMap<Character,Integer> hmap = new HashMap<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(hmap.containsKey(ch)){
                hmap.put(ch, hmap.get(ch)+1);
            }else{
                hmap.put(ch, 1);
            }
        }
        char c = ' ';
        int maxVal = 0;
        for(Character ch:hmap.keySet()){
            if(hmap.get(ch) > maxVal) {
                c=ch;
                maxVal = hmap.get(ch);
            }
        }
        System.out.println(c);
    }

    // CommonElemetns1 0(n)
    public static void getCommonElements1() {
        Scanner scn = new Scanner(System.in);
        int n1 = scn.nextInt();
        int[] arr1 = new int[n1];
        for(int i=0;i<arr1.length;i++){
            arr1[i] = scn.nextInt();
        }
        int n2 = scn.nextInt();
        int[] arr2 = new int[n2];
        for(int i=0;i<arr2.length;i++){
            arr2[i] = scn.nextInt();
        }
        scn.close();
        HashMap<Integer,Integer> hmap= new HashMap<>();
        for(Integer i:arr1){
            hmap.put(i, 1);
        }
        for(Integer i:arr2){
            if(hmap.containsKey(i)){
                System.out.println(i);
                hmap.remove(i);
            }
        }
    }

    // CommonElemetns2 0(n)
    public static void getCommonElements2() {
        Scanner scn = new Scanner(System.in);
        int n1 = scn.nextInt();
        int[] arr1 = new int[n1];
        for(int i=0;i<arr1.length;i++){
            arr1[i] = scn.nextInt();
        }
        int n2 = scn.nextInt();
        int[] arr2 = new int[n2];
        for(int i=0;i<arr2.length;i++){
            arr2[i] = scn.nextInt();
        }
        scn.close();
        HashMap<Integer,Integer> hmap= new HashMap<>();
        for(Integer i:arr1){
            if(hmap.containsKey(i)){
                hmap.put(i, hmap.get(i)+1);
            }else{
                hmap.put(i, 1);
            }
        }
        for(Integer i:arr2){
            if(hmap.containsKey(i)){
                if(hmap.get(i) > 0){
                    System.out.println(i);
                }
                hmap.put(i,hmap.get(i)-1);
            }
        }
    }

    // Largest common Subseuqnce
    public static void lcs() {
        Scanner scn = new Scanner(System.in);
        int n1 = scn.nextInt();
        int[] arr = new int[n1];
        for(int i=0;i<arr.length;i++){
            arr[i] = scn.nextInt();
        }
        scn.close();
        // Step 1 create Freq map based on presence and mark it as true
        HashMap<Integer,Boolean> hmap= new HashMap<>();
        for(Integer i:arr){
            hmap.put(i, true);
        }
        // Step 2 (find all starting points) check if val-1 exist in the hashmap
        for(Integer val:arr){
            if(hmap.containsKey(val-1)){
                hmap.put(val, false);
            }
        }
        // Step 3 get len and start point of seq
        int startPoint = 0;
        int maxSize = 0;
        // traverse over the array
        for(Integer val:arr){
            // if the value present in the hashmap is true then its a valid start point
            if(hmap.get(val) == true){
                // it is a valid starting point
                int len=1;
                int st=val;
                // if the val + 1 is present then increase the max len;
                while(hmap.containsKey(val+1) == true){
                    len++;
                    val++;
                }
                if(maxSize < len){
                    maxSize = len;
                    startPoint = st;
                }
                // put the hmap back false as to manage the duplicates!!!
                hmap.put(st, false);
            }
        }
        // print
        for(int i=0;i<maxSize;i++){
            System.out.println(startPoint);
            startPoint++;
        }
        
    }

    public static void main(String[] args) {
        fun();    
    }
}
