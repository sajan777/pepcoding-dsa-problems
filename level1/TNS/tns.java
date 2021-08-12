import java.util.*;

public class tns {

    public static int linearSearch(int[] arr,int data){
        // LS O(n)
        for(int i=0;i<arr.length;i++){
            if(arr[i] == data) return i;
        }
        return -1;
    }
    // BS(recursive)
    public static boolean binarySearchRec(int[] arr,int low,int high,int data){
        // O(logn)
        if(low>high){
            return false;
        }
        int mid = low+(high-low)/2;

        boolean res =false;
        if(arr[mid] == data){
            res = true;
        }else if(arr[mid] > data){
            res = binarySearchRec(arr, low, mid-1, data);
        }else{
            res = binarySearchRec(arr, mid+1, high, data);
        }

        return res;
    }
    // BS(recursive)
    public static int binarySearchRecIdx(int[] arr,int low,int high,int data){
        if(low>high){
            return -1;
        }
        int mid = low+(high-low)/2;

        int res = -1;
        if(arr[mid] == data){
            res =  mid;
        }else if(arr[mid] > data){
            res = binarySearchRecIdx(arr, low, mid-1, data);
        }else{
            res = binarySearchRecIdx(arr, mid+1, high, data);
        }
        return res;
    }
    

    public static void searching(){
        int[] arr = {10,20,30,35,40,50,60,70,80};
        int data = 35;
        // int idx = linearSearch(arr,data);

        System.out.println(Arrays.toString(arr));
        // Boolean ans = binarySearchRec(arr, 0, arr.length-1, data);
        int ans = binarySearchRecIdx(arr, 0, arr.length-1, data);
        System.out.println(ans);
    }
      // used for swapping ith and jth elements of array
    public static void swap(int[] arr, int i, int j) {
        System.out.println("Swapping index " + i + " and index " + j);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void print(int[] arr){
        for(int i = 0 ; i < arr.length; i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    
    public static void segregateOddEven(){
        int[] arr = {4,7,6,9,3,8,10,11,14,16,13,20};
        print(arr);
        int i=0;
        int j = 0;
        while(i<arr.length){
            if(arr[i]%2 == 0){
                i++;
            }else{
                swap(arr, i, j);
                i++;
                j++;
            }
        }
        print(arr);

    }

    public static void segregateOddEven2(){
        int[] arr = {4,7,6,9,3,8,10,11,14,16,13,20};
        print(arr);
        int i=arr.length-1;
        int j = arr.length-1;
        while(i>=0){
            if(arr[i]%2 == 0){
                i--;
            }else{
                swap(arr, i, j);
                i--;
                j--;
            }
        }
        print(arr);

    }

    public static void sort012(int[] arr){
        int i=0;
        int j = 0;
        int k = arr.length-1;
        while(i<=k){
            if(arr[i] == 1){
                i++;
            }else if(arr[i] == 0){
                swap(arr,i,j);
                i++;
                j++;
            }else{
                swap(arr,i,k);
                k--;
            }
        }
    }

    public static void sort01(int[] arr){
        int i=0;
        int j=0;
        while(i<arr.length){
            if(arr[i] == 1){
                i++;
            }else{
                swap(arr,i,j);
                i++;
                j++;
            }
        }
        
      }

    public static void polynomial(){
        int x = 2;
        int n = 5;
        // o(n) do it reversely
        int ans = 0;
        int xval = x;
        while(n>0){
            ans += n*xval;
            xval *= x;
            n--;
        }

        // o(nlog(n))
        // int ans = 0;
        // int counter =1;
        // for(int i=n;i>0;i--){
        //     ans = ans + counter*(int)Math.pow(x,i);
        //     counter++;
        // }
        System.out.println(ans);
    }  
    
    public static int[] arrayFiller(int size,int digit){
        int power = (int)Math.pow(10,digit);
        int[] arr = new int[size];
        for(int i=0;i<size;i++){
            arr[i] = (int)(Math.random() * power);
        }
        return arr;
    }

    // O(n/-m)
    public static void printPrime(int [] arr){
        for(int i=0;i<arr.length;i++){
            int isPrime = 0;
            if(arr[i] == 0 || arr[i] == 1) continue;
            for(int k=2;k*k<=arr[i];k++){
                if(arr[i]%k == 0){
                    isPrime++;
                    break;
                }
            }
            if(isPrime == 0){
                System.out.println(arr[i]+" is Prime");
            }
        } 
    }
    
    // 0(m) Base Version
    public static void sieveAlgoForPrimes(int[] queries,int hi){
        boolean[] isPrime = new boolean[hi+1];
        // mark every element as true can be modified just for convience
        Arrays.fill(isPrime,true); //O(n)
        // pre calc prime
        for(int i=2;i*i<=hi;i++){
            if(isPrime[i] == true){
                // if i==2 j=4,6,8,10,12,14,16.....hi
                for(int j=i+i;j<=hi;j+=i){
                    isPrime[i] = false;
                }
            }
        }

        // now call for every query
        for(int i=0;i<queries.length;i++){
            int num = queries[i];
            if(num == 0 || num == 1) continue;
            if(isPrime[num] == true){
                System.out.println(num+" is Prime");
            }else{
                System.out.println(num+" is not Prime");
            }
        }
    }
    
    
    public static int partitionIndex(int[] arr,int lo,int hi,int pivot){
        int i=lo;
        int j=lo;
        while(i<=hi){
            if(arr[i]<=pivot){
                swap(arr,i,j);
                i++;
                j++;
            }else{
                i++;
            }
        }
        return j-1;
    }
    // random data O(logn),sorted Data O(n2) 
    public static void quickSort(int[] arr,int lo,int hi){
        if(lo >= hi) return;  
         
        int pivot = arr[hi];
        int pivotIdx = partitionIndex(arr, lo, hi, pivot);
        quickSort(arr, lo, pivotIdx-1);
        quickSort(arr, pivotIdx+1, hi);
    }

    public static void countSortBaseVersion(int[] arr,int hi){
        // create frequency map
        int[] freqencyMap = new int[hi+1];
        for(int i=0;i<arr.length;i++){
            freqencyMap[arr[i]]++;
        }

        // refill the arr
        int idx = 0;
        for(int i=0;i<freqencyMap.length;i++){
            int fq = freqencyMap[i];
            int val = i; 
            for(int j=0;j<fq;j++){
                arr[idx] = val;
                idx++;
            }  
        }
    }

    public static void countSort(int[] arr, int min, int max) {

        int[] fmap = new int[max-min+1];
        // create freq map
          for(int i=0;i<arr.length;i++){
              int val = arr[i]-min;
              fmap[val]++;
          }
      
        //   refill
        int indx = 0;
        for(int i=0;i<fmap.length;i++){
            int val = i+min;
            int fq = fmap[i];
            for(int j=0;j<fq;j++){
                arr[indx] = val;
                indx++;
            }
        }
        
      }
    
    public static void stableCountSort(int[] arr,int min,int max,String[] charr){
        int[] fmap = new int[max-min+1];
        // 1.fill frequency map
        for(int i=0;i<arr.length;i++){
            int indx = arr[i]-min;     
            fmap[indx]++;
        }
        // 2.Generate Prefix Sum Array
        // subtract first frequency for 0 based indexing
        fmap[0]--;
        for(int i=1;i<fmap.length;i++){
            fmap[i]+=fmap[i-1];
        }
        // 3.make a new array and fill in reverse dir also decrease prefix sum[i]
        int[] narr = new int[arr.length];
        for(int i=arr.length-1;i>=0;i--){
            // val to place
            int val = arr[i];
            // index in fmap
            int indx = arr[i] - min;
            // pos to place in the new arr
            int pos = fmap[indx];
            // place it
            narr[pos] = val;
            // reduce the prefix sum
            fmap[indx]--;
        }
        // 4.place it back in the same array
        for(int i=0;i<narr.length;i++){
            arr[i] = narr[i];
        }

    }
    
    public static void countSortForRadix(int[] arr,int exp){
        int[] fmap = new int[10];

        for(int i=0;i<arr.length;i++){
            int indx = (arr[i] / exp) % 10;     
            fmap[indx]++;
        }

        fmap[0]--;
        for(int i=1;i<fmap.length;i++){
            fmap[i]+=fmap[i-1];
        }

        int[] narr = new int[arr.length];
        for(int i=arr.length-1;i>=0;i--){

            int val = (arr[i] / exp) % 10;
            int indx = val;
            int pos = fmap[indx];
            narr[pos] = arr[i];
            fmap[indx]--;
        }

        for(int i=0;i<narr.length;i++){
            arr[i] = narr[i];
        }
    }

    public static void radixSort(int[] arr){
        int exp = 1;
        int maxVal = Integer.MIN_VALUE;
        for(Integer i:arr){
            if(i>maxVal){
                maxVal = i;
            }
        } 
        while(exp <= maxVal){
            countSortForRadix(arr,exp);
            exp*=10;
        }

    }
    
    
    public static void ques(){
        
        // searching();
        
        // segregateOddEven2();
        
        // polynomial();

        // int[] arr = arrayFiller(10000, 2);
        // print(arr);
        // printPrime(arr);
        
        // int[] queries = arrayFiller(50, 1);
        // sieveAlgoForPrimes(queries,40);

        // int[] arr = {90,70,20,60,50,30,80,40,10,45};
        // print(arr);
        // System.out.println(partitionIndex(arr,0,arr.length-1,45));

        // int[] arr = {90,70,20,60,50,30,80,40,10,45};
        // print(arr);
        // quickSort(arr, 0,arr.length-1);
        // print(arr);


        // int[] arr = arrayFiller(30, 1);
        // int[] arr = {0,1,5,5,3,9,8,6,7,4,7,9,10,10,9,6,4,3,3,2,1,0,7,6,5};
        // print(arr);
        // countSortBaseVersion(arr,10);
        // print(arr);



        // int[] arr = {3,7,6,2,8,9,4,5,7,6,5,3};
        // String[] charr ={"a","b","c","d","f","g","h","i","j","k","l"};
        // print(arr);
        // stableCountSort(arr,2,9,charr);
        // System.out.println();
        // print(arr);


        int[] arr =arrayFiller(9, 5);
        print(arr);
        radixSort(arr);
        print(arr);
    }









    

    public static void main(String[] args){
        ques();
    }
}
