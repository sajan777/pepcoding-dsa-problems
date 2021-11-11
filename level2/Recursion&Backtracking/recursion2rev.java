import java.util.*;

public class recursion2rev {
    
    public static void queensCombinations(int qpsf, int tq, int row, int col, String asf){
        if(row == tq){
            if(qpsf == tq){
                System.out.println(asf);
            }
            return;
        }

        if(col + 1 == tq){
            // yes call
            if(qpsf<=tq){
                queensCombinations(qpsf+1, tq, row, col+1, asf+"q");
            }
            // no call
            queensCombinations(qpsf, tq, row, col+1, asf+"-");
        }else{
            // yes call
            if(qpsf<=tq){
                queensCombinations(qpsf+1, tq, row+1, 0, asf+"q"+"\n");
            }
            // no call
            queensCombinations(qpsf, tq, row+1, 0, asf+"-"+"\n");
        }
    }

    public static void queensPermutations(int qpsf, int tq, int[][] chess){
        if(qpsf == tq){
            for(int i=0;i<chess.length;i++){
                for(int j=0;j<chess[0].length;j++){
                    System.out.print(chess[i][j] == 1 ? "q"+j+" " : "-"+"");
                }
                System.out.println();
            }
        }
        for(int i=0;i<chess.length;i++){
            for(int j=0;j<chess[0].length;j++){
                if(chess[i][j] == 0){
                    chess[i][j] = 1;
                    queensPermutations(qpsf+1, tq, chess);
                    chess[i][j] = 0;
                }
            }
        }
    }



    // Queens Combinations - 2d As 1d - Queen Chooses
    public static void queensCombinations(int qpsf, int tq, boolean[][] chess, int lcno) {
        // write your code here
    }



    // K-partitions
    // rssf = room selected so far
    // k = rooms
    static int ctr = 1;
	public static void solution(int i, int n, int k, int rssf, ArrayList<ArrayList<Integer>> ans) {
		if(i > n){
            if(rssf == k){
                System.out.print(ctr+". ");
                for(ArrayList<Integer> li:ans){
                    System.out.print(li+" ");
                } 
                System.out.println();
                ctr++;
            }
            return;
        }
		
        for(int j=0;j<k;j++) {
            if(ans.get(j).size() == 0){
                // last option for i (empty room)
                ans.get(j).add(i);
                solution(i+1,n,k,rssf+1,ans);
                ans.get(j).remove(ans.get(j).size()-1);
                break;
            }else{
                ans.get(j).add(i);
                solution(i+1, n, k, rssf, ans);
                ans.get(j).remove(ans.get(j).size()-1);
            }
        }
		
	}

    public boolean wordBreak_Util(String os,String s,String ssf,List<String> wordDict){
        if(s.length() == 0){
            if(ssf == os){
                return true;
            }
            return false;
        }
        for(int i=0;i<os.length();i++){
            String substr = os.substring(0,i+1);
            String ros = os.substring(i+1);
            if(wordDict.contains(substr) == true){
                boolean rres = wordBreak_Util(os, ros, ssf, wordDict);
                if(rres == true) return true;
            }
        }
        return false;
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean res = wordBreak_Util(s,s, "", wordDict);
        return res;
    }

    public static void main(String[] args) {
        
    }    
}
