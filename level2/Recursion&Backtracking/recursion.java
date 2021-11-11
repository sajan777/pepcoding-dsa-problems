import java.util.*;
public class recursion{
    
    // Permutation 1
    // ci  current items ,ti total items
    public static void permutations(int[] boxes, int ci, int ti){
        if(ci > ti){
            for(int val:boxes ){
                System.out.print(val);
            }
            System.out.println();
            return;
        }
        for(int b=0;b<boxes.length;b++){
            if(boxes[b] == 0){
                // box is empty
                boxes[b] = ci; //place
                permutations(boxes,ci+1,ti);
                boxes[b] = 0; //unplace
            }
        }
    }
    // combinations 1
    // cb-currentbox,tb-totalbox,isf-itemsplacesofar,ti-totalItem,asf
    public static void combinations(int cb, int tb, int isf, int ti, String asf){
        if(cb > tb){
            if(isf == ti){
                System.out.println(asf);
            }
            return;
        }

        // yes call
        if(isf + 1 <= ti){
            combinations(cb+1, tb, isf+1, ti, asf+"i");
        }
        // no call
        combinations(cb+1, tb, isf, ti, asf+"-");
    } 
    // Permuations2
    // cb- currentbox,tb-totalbox,isf->itemsofar,ti->totalitems 
    public static void permutations(int cb, int tb, int[] items, int isf, int ti, String asf){
        if(cb > tb){
            if(isf == ti){
                System.out.println(asf);
            }
            return;
        }
        // yes call
        for(int i=0;i<items.length && isf<ti;i++){
            if(items[i] == 0){
                items[i] = 1; //select
                permutations(cb+1, tb, items, isf+1, ti, asf+(i+1));
                items[i] = 0; //unselect
            }
        }
        // no call
        permutations(cb+1, tb, items, isf, ti, asf+"0");
    }
    // Combinations2 
    //ci = current item
    //ti = total item
    // lb = last box(sorted index)
    public static void combinations(int[] boxes, int ci, int ti, int lb){
        if(ci > ti){
            for(int i=0;i<boxes.length;i++){
                int bo = boxes[i];
                if(bo == 0){
                    System.out.print("-");
                }else{
                    System.out.print("i");
                }
            }
            System.out.println();
            return;
        }

        for(int b=lb+1;b<boxes.length;b++){
            boxes[b] = ci; //place current item on bth box
            combinations(boxes,ci+1,ti,b);
            boxes[b] = 0; //unplace
        }
    }
    // Queens combinations 2D as 2D Box Chooses
    public static void queensCombinations(int qpsf, int tq, int row, int col, String asf){
        if(row == tq){
            if(qpsf == tq){
                System.out.println(asf);
            }
            return;
        }

        if(col + 1 < tq){
            // yes call
            if(qpsf < tq){
                queensCombinations(qpsf+1, tq, row, col+1, asf+"q");
            }
            //no call
            queensCombinations(qpsf, tq, row, col+1, asf+"-");
        }else{
            // yes call
            if(qpsf < tq){ //if all the queens have already been placed then dont do yes call (it helps reduce overall calls)
                queensCombinations(qpsf+1, tq, row+1, 0, asf+"q\n");
            }
            //no call
            queensCombinations(qpsf, tq, row+1, 0, asf+"-\n");
        }
    }
    
    //Queens Permutations - 2d As 2d - Queen Chooses
    public static void queensPermutations(int qpsf, int tq, int[][] chess){
        if(qpsf == tq){
            for(int i=0;i<chess.length;i++){
                for(int j=0;j<chess[i].length;j++){
                    if(chess[i][j] == 0){
                        System.out.print("-\t");
                    }else{
                        System.out.print("q"+chess[i][j]+"\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return;
        }


        for(int i=0;i<chess.length;i++){
            for(int j=0;j<chess[i].length;j++){
                if(chess[i][j] == 0){
                    chess[i][j] = qpsf+1; //place queen
                    queensPermutations(qpsf+1, tq, chess);
                    chess[i][j] = 0; //unplace queen
                }
            }
        }
    }
    // Queens Permutations - 2d As 2d - Box Chooses
    public static void queensPermutations(int qpsf, int tq, int row, int col, String asf, boolean[] queens) {
        if(row == tq){
            if(qpsf == tq){
                System.out.println(asf);
                System.out.println();
            }
            return;
        }
        // yes call
        for(int q=0;q<queens.length;q++){
            if(queens[q] == false){
                queens[q] = true;
                if(col+1<tq){
                    queensPermutations(qpsf+1, tq, row, col+1, asf+"q"+(q+1)+"\t", queens);
                }else{
                    queensPermutations(qpsf+1, tq, row+1, 0, asf+"q"+(q+1)+"\n", queens);
                }
                queens[q] = false;
            }
        }
        // no call
        if(col+1<tq){
            queensPermutations(qpsf, tq, row, col+1, asf+"-\t", queens);
        
        }else{
            queensPermutations(qpsf, tq, row+1, 0, asf+"-\n", queens);
        }
    }
    
    //Queens Combinations - 2d As 2d - Queen Chooses
    public static void queensCombinations(int qpsf, int tq, boolean[][] chess, int i, int j){
        if(qpsf == tq){
            for(int p=0;p<chess.length;p++){
                for(int q=0;q<chess[i].length;q++){
                    if(chess[p][q] == false){
                        System.out.print("-\t");
                    }else{
                        System.out.print("q\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return;
        }
        // travel in remaingn columns in current row
        for(int c=j+1;c<chess[0].length;c++){
            int r = i;
            // place 
            chess[r][c] = true;
            queensCombinations(qpsf+1,tq,chess,r,c);
            // unplace
            chess[r][c] = false;
        }
        
        // travel in remaining rows and columns
        for(int r=i+1;r<chess.length;r++){
            for(int c=0;c<chess[0].length;c++){
                // place 
                chess[r][c] = true;
                queensCombinations(qpsf+1,tq,chess,r,c);
                // unplace
                chess[r][c] = false;
            }
        }
    }
    //Queens Combinations - 2d As 1d - Queen Chooses
    // lcno - last cell number
    public static void queensCombinations(int qpsf, int tq, boolean[][] chess, int lcno) {
        if(qpsf == tq){
            for(int i=0;i<chess.length;i++){
                for(int j=0;j<chess[0].length;j++){
                    if(chess[i][j] == false){
                        System.out.print("-\t");
                    }else{
                        System.out.print("q\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for(int b=lcno+1;b<chess.length*chess[0].length;b++){
            int r = b / chess.length;
            int c = b % chess[0].length;
            chess[r][c] = true;    // place
            queensCombinations(qpsf+1,tq,chess,b);
            chess[r][c] = false; //unplace
        }
    }
    //Nqueens Combinations - 2d As 1d - Queen Chooses
    public static boolean IsQueenSafe(boolean[][] chess, int row, int col) {
        int rad = chess.length;
        int[][] dirs = {{0,-1},{-1,-1},{-1,0},{-1,1}};
        for(int r=1;r<=rad;r++){
            for(int d=0;d<dirs.length;d++){
                int rr = row+(r*dirs[d][0]);
                int cc = col+(r*dirs[d][1]);
                if(rr >=0 && cc>=0 && rr<chess.length && cc<chess.length){
                    if(chess[rr][cc] == true) return false;
                }
            }
        }
        return true;
    }
    public static void nqueens(int qpsf, int tq, boolean[][] chess, int lcno) {
        if(qpsf == tq){
            for(int i=0;i<chess.length;i++){
                for(int j=0;j<chess[0].length;j++){
                    if(chess[i][j] == false){
                        System.out.print("-\t");
                    }else{
                        System.out.print("q\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for (int b = lcno + 1; b < chess.length * chess.length; b++) {
            int row = b / chess.length;
            int col = b % chess.length;

            if (IsQueenSafe(chess, row, col)) {
                chess[row][col] = true;
                nqueens(qpsf + 1, tq, chess,b);
                chess[row][col] = false;
            }
        }
    }
    //Nqueens Permutations - 2d As 1d - Queen Chooses
    public static boolean IsQueenSafe(int[][] chess, int row, int col) {
        int rad = chess.length;
        // permutation hai na beg se loop lga hai so check in every direction
        int[][] dirs = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
        for(int r=1;r<=rad;r++){
            for(int d=0;d<dirs.length;d++){
                int rr = row+(r*dirs[d][0]);
                int cc = col+(r*dirs[d][1]);
                if(rr >=0 && cc>=0 && rr<chess.length && cc<chess.length){
                    if(chess[rr][cc] != 0) return false;
                }
            }
        }
        return true;
    }

    public static void nqueens(int qpsf, int tq, int[][] chess) {
        if(qpsf == tq){
            for(int i=0;i<chess.length;i++){
                for(int j=0;j<chess[0].length;j++){
                    if(chess[i][j] == 0){
                        System.out.print("-\t");
                    }else{
                        System.out.print("q"+chess[i][j]+"\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        // permutation hai na isly beginning se loop chalega hameha
        for (int b = 0; b < chess.length * chess[0].length - 1; b++) {
            int row = b / chess.length;
            int col = b % chess.length;
            // permutation hai na isly chess[r][c] == 0
            if (chess[row][col] == 0 && IsQueenSafe(chess, row, col)) {
                chess[row][col] = qpsf+1;
                nqueens(qpsf + 1, tq, chess);
                chess[row][col] = 0;
            }
        }
    }
    //Nknights Combinations - 2d As 1d - Knight Chooses
    public static boolean IsKnightSafe(boolean[][] chess, int i, int j) {
        // combination m only (0,0) se upar wale points only to check
        int[][] dirs = {{-2,1},{-1,2},{-1,-2},{-2,-1}};
        for(int d=0;d<dirs.length;d++){
            int rr = i+dirs[d][0];
            int cc = j+dirs[d][1];
            if(rr >=0 && cc>=0 && rr<chess.length && cc<chess.length){
                if(chess[rr][cc] == true) return false;
            }
        }
        return true;
    }
    // knights place so far = kpsf
    // tk total knights
    // lcno = last cell no
    public static void nknights(int kpsf, int tk, boolean[][] chess, int lcno) {
        if(kpsf == tk){
            for(int i=0;i<chess.length;i++){
                for(int j=0;j<chess[0].length;j++){
                    if(chess[i][j] == false){
                        System.out.print("-\t");
                    }else{
                        System.out.print("k\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return;
        }
        for(int b=lcno+1;b<chess.length*chess.length;b++){
            int row = b/chess.length;
            int col = b%chess.length;
            if(IsKnightSafe(chess,row,col)){
                chess[row][col] = true;
                nknights(kpsf+1, tk, chess, b);
                chess[row][col] = false;
            }
        }
    }
    // Permutations - Words - 1
    // cs current box
    // ts total box
    // fmap frequency map of all characters
    public static void generateWords(int cs, int ts, HashMap<Character, Integer> fmap, String asf) {
        if(cs > ts){
            System.out.println(asf);
            return;
        }
        for(char ch:fmap.keySet()){
            if(fmap.get(ch) > 0){
                int oldFreq = fmap.get(ch);
                fmap.put(ch, oldFreq-1); //mark
                generateWords(cs+1, ts, fmap, asf+ch);
                fmap.put(ch, oldFreq); //unmark
            }
        }
    }
    // Permutations - Words - 2
    // li index hashmap for char
    // cc  current char
    // spots char array for characters marking
    public static void generateWords(int cc, String str, Character[] spots,HashMap<Character, Integer> li) {
        if(cc == str.length()){
            for(char c:spots){
                System.out.println(c);
            }
            System.out.println();
            return;
        }


        char ch = str.charAt(cc);
        int lsi = li.get(ch); //last spot index
        for(int box=lsi+1;box<spots.length;box++){
            if(spots[box] == null){
                // mark
                spots[box] = ch;
                li.put(ch, box);
                generateWords(cc+1, str, spots, li);
                // unmark
                spots[box] = null;
                li.put(ch, lsi);
            }
        }
    }
    // Words - K Selection - 1
    // ustr => unique string
    // ts = total selection (k)
    // ssf = current selections
    // i = current index
    public static void combination(int i,String ustr,int ssf, int ts, String asf ){
        
        if(i == ustr.length()){
            if(ssf == ts){
                System.out.println(asf);
            }
            return;
        }
        char ch = ustr.charAt(i);
        // yes call
        combination(i+1, ustr, ssf+1, ts, asf+ch);
        // no call
        combination(i+1, ustr, ssf, ts, asf);

        // My Approach
        // if(ssf == ts){
        //     System.out.println(asf);
        //     return;
        // }
        // for(int idx = i;idx<ustr.length();idx++){
        //     char ch = ustr.charAt(idx);
        //     combination(idx+1, ustr, ssf+1, ts, asf+ch);
        // }
    }
    //Words - K Selection - 2
    // lc last character used
    public static void combination(String ustr,int ssf,int ts,String asf,int lc){
        if(ssf == ts){
            System.out.println(asf);
            return;
        }

        for(int i=lc+1;i<ustr.length();i++){
            char ch = ustr.charAt(i);
            combination(ustr, ssf+1, ts, asf+ch, i);
        }
    }
    // Words - K Length Words - 1
    public static void permutation(String ustr,int ssf,int i,Character[] slots){

        if(i == ustr.length()){
            if(ssf == slots.length){
                for(char c:slots){
                    System.out.print(c);
                }
                System.out.println();
            }
            return;
        }

        char ch = ustr.charAt(i);
        // yes call
        for(int s=0;s<slots.length;s++){
            if(slots[s] == null){
                slots[s] = ch; //mark
                permutation(ustr, ssf+1, i+1, slots);
                slots[s] = null; //unmark
            }
        }
        // no call
        permutation(ustr, ssf, i+1, slots);
    }
    // permutation(ustr,0,0,new Character[k]);
    
    // Words - K Length Words - 2
    public static void permutation(String ustr,HashSet<Character> visited,int cs,int ts,String asf){
        if(cs == ts){
            System.out.println(asf);
            return;
        }
        for(int i=0;i<ustr.length();i++){
            char ch = ustr.charAt(i);
            if(visited.contains(ch) == false){
                visited.add(ch);
                permutation(ustr, visited, cs+1, ts, asf+ch);
                visited.remove(ch);
            }

        }
    }
    // permutation(ustr,new HashSet<Character>(),0,k,"");

    // Words - K Selection - 3
    // cc = current character
    public static void combination(String ustr,int cc,HashMap<Character,Integer> fmap,String asf,int k){
        int ssf = asf.length();
        // jo ans print hona hoga yhi hojayega otherwise agar ham last index par aagye hai to neeche wale base case me aajayge    
        if(ssf == k){
            System.out.println(asf);
            return;
        }
        if(cc == ustr.length()) return; //length over

        char ch = ustr.charAt(cc);
        int freq = fmap.get(ch);

        // yes call
        for(int i=freq;i>0;i--){
            if(i+ssf <= k){

                String str = "";
                for(int j=0;j<i;j++){
                    str += ch;
                }
                combination(ustr, cc+1, fmap, asf+str, k);
            }
        }
        // no call
        combination(ustr, cc+1, fmap, asf, k);

    }
    // combination(ustr,0,unique,"",k);
    
    // Words - K Selection - 4
    // li last index of char used, cs=>current spot,ts=>total spot
    public static void combination(String ustr,HashMap<Character,Integer> fmap,int li,String asf,int cs,int ts){
        if(cs == ts){
            System.out.println(asf);
            return;
        }
        for(int i=li;i<ustr.length();i++){
            char ch = ustr.charAt(i);
            int freq = fmap.get(ch);
            if(freq > 0){
                fmap.put(ch, freq-1); //mark
                combination(ustr, fmap, i, asf+ch, cs+1, ts);
                fmap.put(ch, freq); //unmark
            }
        }
    }
    // combination(ustr,unique,0,"",0,k);

    // Words - K Length Words - 3
    public static void permutation(String str,int cc,int ssf,int ts,Character[] slots,HashMap<Character,Integer> map){
        if(cc == str.length()){
            if(ssf == ts){
                for(char ch:slots){
                    System.out.print(ch);
                }
                System.out.println();
            }
            return;
        }

        char ch = str.charAt(cc); //current character
        int loc = map.get(ch); //last occurence of a character
        // yes call
        for(int i=loc+1;i<slots.length;i++){
            if(slots[i] == null){
                // change last occurence of character
                map.put(ch, i);
                //place
                slots[i] = ch;

                permutation(str,cc+1,ssf+1,ts,slots,map);
                
                // unplace
                slots[i] = null;
                // reset last occurence of character
                map.put(ch,loc);
            }
        }
        // no call
        if(loc == -1){
            permutation(str, cc+1, ssf, ts, slots, map);
        }
    }
    // HashMap<Character,Integer> map = new HashMap<>();
    // for(int i=0;i<str.length();i++){
    //     char ch = str.charAt(i);
    //     map.put(ch,-1);
    // }
    // permutation(str,0,0,k,new Character[k],map);
    
    // Words - K Length Words - 4
    public static void permutation(String ustr,String asf,int cs,int ts,HashMap<Character,Integer> fmap){
        if(cs == ts){
            System.out.println(asf);
            return;
        }
        for(int i=0;i<ustr.length();i++){
            char ch = ustr.charAt(i);
            int oldFreq = fmap.get(ch);
            if(oldFreq > 0){
                fmap.put(ch, oldFreq-1); //mark
                permutation(ustr,asf+ch,cs+1,ts,fmap);
                fmap.put(ch, oldFreq); //unmark
            }
        }
    }
    // String ustr = "";
    // HashMap<Character,Integer> fmap = new HashMap<>();
    // for(int i=0;i<str.length();i++){
    //     char ch  = str.charAt(i);
    //     if(fmap.containsKey(ch) == false){
    //         fmap.put(ch, 1);
    //         ustr += ch;
    //     }else{
    //         fmap.put(ch,fmap.get(ch)+1);
    //     }
    // }
    // permutation(ustr,"",0,k,fmap);

    // Coin Change - Combinations - 1
    public static void coinChange(int i, int[] coins, int amtsf, int tamt, String asf){
        if(i == coins.length){
            if(amtsf == tamt){
                System.out.println(asf+'.');
            }
            return;
        }

        // yes call
        if(coins[i]+amtsf <= tamt){
            coinChange(i+1, coins, amtsf+coins[i], tamt, asf+coins[i]+"-");
        }
        // no call
        coinChange(i+1, coins, amtsf, tamt, asf);
    }

    // Coin Change - Combinations - 2
    public static void coinChange2(int i, int[] coins, int amtsf, int tamt, String asf){
        if(i == coins.length){
            if(amtsf == tamt){
                System.out.println(asf+".");
            }
            return;
        }

        // yes call
        if(coins[i]+amtsf <= tamt){
            coinChange2(i, coins, amtsf+coins[i], tamt, asf+coins[i]+"-");
        }
        // no call
        coinChange2(i+1, coins, amtsf, tamt, asf);
    }
    // Coin Change - Permutations - 1
    public static void coinChange(int[] coins, int amtsf, int tamt, String asf, boolean[] used){
        if(amtsf == tamt){
            System.out.println(asf+'.');
            return;
        }

        for(int i=0;i<coins.length;i++){
            if(used[i] == false && coins[i] + amtsf <= tamt){
                used[i] = true;
                coinChange(coins,amtsf+coins[i],tamt,asf+coins[i]+'-',used);
                used[i] = false;
            }
        }
    }
    // Coin Change - Permutations - 2
    public static void coinChange(int[] coins, int amtsf, int tamt, String asf) {
        if(amtsf == tamt){
            System.out.println(asf+'.');
            return;
        }

        for(int i=0;i<coins.length;i++){
            if(coins[i] + amtsf <= tamt){
                coinChange(coins,amtsf+coins[i],tamt,asf+coins[i]+'-');
            }
        }
    }
    // Abbreviation Using Backtracking
    public static void solution(String str, String asf,int count, int pos){
        if(pos == str.length()){
            if(count == 0){
                System.out.println(asf);
            }else{
                System.out.println(asf+count);
            }
            return;
        }
        char ch = str.charAt(pos);
        // yes call
        if(count != 0){
            solution(str, asf+count+ch, 0, pos+1);
        }else{
            solution(str, asf+ch, 0, pos+1);
        }
        // no call
        solution(str, asf, count+1, pos+1);
    }

    // N Queens - Branch And Bound
    static boolean[] cols;
    static boolean[] nd; //normal diagonal
    static boolean[] rd; //reverse diagonal
    public static boolean isSafeToPlace(int r,int c){
        // Safety accross col
        if(cols[c] == true) return false;

        // Safety across normal diagoanl
        if(nd[r+c] == true) return false;

        // Safety across rev diagonal
        if(rd[r-c+cols.length-1] == true) return false;

        return true;
    }

    public static void nQueens(int row,int n,String asf){
        if(row == n){
            System.out.println(asf+".");
            return;
        }
        for(int col=0;col<n;col++){
            if(isSafeToPlace(row,col) == true){
                // mark
                cols[col] = true;
                nd[row+col] = true;
                rd[row-col+n-1] = true;

                nQueens(row+1, n, asf+row+"-"+col+", ");

                // unmark
                cols[col] = false;
                nd[row+col] = false;
                rd[row-col+n-1] = false;
            }
        }
    }
    // for main
    // we don't need chess booard because we are creating boolean arrays for checking validity
    // cols = new boolean[n];
    // nd = new boolean[2*n-1];
    // rd = new boolean[2*n-1];
    // nQueens(0,n,"");

    // Max Score
    public static int solution(String[] words, int[] farr, int[] score, int idx) {
        if(idx == words.length){
            return 0;
        }
        // yes call
        String word = words[idx];
        int myScore = 0;
        boolean flag = true;
        // exhaust frequency at current level
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            int charIndx = ch - 'a';
            myScore += score[charIndx];
            if(farr[charIndx] <= 0){
                flag = false;
            }
            farr[charIndx]--;
        }
        int y_ans = 0;
        if(flag == true){
            y_ans = myScore + solution(words, farr, score, idx+1);
        }
        // reset frequency
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            int charIndx = ch - 'a';
            myScore += score[charIndx];
            farr[charIndx]++;
        }
        //no call
        int n_ans = solution(words,farr,score,idx+1);
		return Math.max(y_ans, n_ans);
	}
    // Josephus Problem
    public static int solution(int n, int k){
        if(n==1) return 0;
        return (solution(n-1,k)+k)%n;
    }
    // if(n == 1) return 0;
    
    // int rres = solution(n - 1, k);
    
    // return (rres + k) % n;
    // Lexicographical Numbers
    public static void lexicography(int val,int n){
        if(val > n) return;

        // self printing
        System.out.println(val);
        // family
        for(int i=0;i<10;i++){
            lexicography(10 * val+i, n);
        }
    }
    // for(int i=1;i<10;i++){
    //     lexicography(i, n);
    // }

    // Gold Mine - 2
    static int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
    public static int dfs(int[][] arr,int r,int c){
        int t_gold = arr[r][c];
        // mark
        arr[r][c] *= -1;
        for(int d=0;d<dirs.length;d++){
            int rr = r+dirs[d][0];
            int cc = c+dirs[d][1];
            if(rr>=0 && rr<arr.length && cc>=0 && cc<arr[0].length && arr[rr][cc] > 0){
                t_gold +=dfs(arr, rr, cc);
            }
        }
        return t_gold;
    }
    static int max = 0;
    public static void getMaxGold(int[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                if(arr[i][j] > 0){
                    max = Math.max(max,dfs(arr, i, j));
                }
            }
        }
	}
    // SolveSudoku
    public static void display(int[][] board){
        for(int i = 0; i < board.length; i++){
          for(int j = 0; j < board[0].length; j++){
            System.out.print(board[i][j] + " ");
          }
          System.out.println();
        }
    }
    public static boolean isSudkoSafe(int[][] board,int row,int col,int num){
        // current row check
        for(int c=0;c<board[0].length;c++){
            if(board[row][c] == num) return false;
        }
        // check in col
        for(int r=0;r<board.length;r++){
            if(board[r][col] == num) return false;
        }
        // check in submatrix
        // rr&cc are starting point of the submatrix
        int rr = row-(row%3);
        int cc = col-(col%3);
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i+rr][j+cc] == num) return false;
            }
        }
        return true;
    }
    public static void solveSudoku(int[][] board, int i, int j) {
        if(i == board.length){
            display(board);
            return;
        }
        if(board[i][j] == 0){
            for(int num=1;num<10;num++){
                if(isSudkoSafe(board,i,j,num)){
                    board[i][j] = num; //place
                    if(j == board[0].length - 1){
                        solveSudoku(board,i+1,0);
                    }else{
                        solveSudoku(board, i, j+1);
                    }
                    board[i][j] = 0; //unplace
                }
            }
        }else{
            // already kuch filled hai aage badh jao
            if(j == board[0].length - 1){
                solveSudoku(board,i+1,0);
            }else{
                solveSudoku(board, i, j+1);
            }
        }
    }
    //Sudoku using 1D array using box number
    public static void solveSudoku1D(int[][] board,ArrayList<Integer> list,int indx){
        if(indx == list.size()){
            display(board);
            return;
        }
        int bno = list.get(indx);
        int r = bno / board.length;
        int c = bno % board.length;

        for(int num=1;num<10;num++){
            if(isSudkoSafe(board, r, c, num)){
                board[r][c] = num;
                solveSudoku1D(board,list,indx+1);
                board[r][c] = 0;
            }
        }

    }
    public static void solveSudoku1(int[][] board){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board.length;j++){
                if(board[i][j] == 0){
                    int boxNo = board.length*i+j;
                    list.add(boxNo);
                }
            }
        }
        solveSudoku1D(board,list,0);
    }


    //Cryptarithmetic 
    public static int findCRYPT(String s, HashMap<Character, Integer> map) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
          char ch = s.charAt(i);
          int n = map.get(ch);
          res = res * 10 + n;
        }
        return res;
    }
    public static void solution(String unique, int idx, HashMap<Character, Integer> charIntMap, boolean[] usedNumbers, String s1, String s2, String s3) {
        if (idx == unique.length()) {
            int s1Crypt = findCRYPT(s1, charIntMap);
            int s2Crypt = findCRYPT(s2, charIntMap);
            int s3Crypt = findCRYPT(s3, charIntMap);
            if (s1Crypt + s2Crypt == s3Crypt) {
            // sorted order 0 to 26 for every character
                for (int i = 0; i < 26; i++) {
                    char ch = (char) ('a' + i);
                    if (charIntMap.containsKey(ch) == true) {
                        System.out.print(ch + "-" + charIntMap.get(ch) + " ");
                    }
                }
                System.out.println();
            }
            return;
        }
        char ch = unique.charAt(idx);
        for (int i = 0; i < 10; i++) {
            if (usedNumbers[i] == false) {
                usedNumbers[i] = true;
                charIntMap.put(ch, i);
                solution(unique, idx + 1, charIntMap, usedNumbers, s1, s2, s3);
                charIntMap.remove(ch);
                usedNumbers[i] = false;
            }
        }
    }
    
    // Crossword Puzzle
    // Horizontally
    public static boolean canPlaceHorizontally(char[][] grid,int r,int c,String word){
        // left check
        if(c > 0 && grid[r][c-1] != '+'){ 
            // means it shouldn't be like this (- - d e l h i)
            // because prefectly fit because there is space before d. 
            return false;
        }
        // right check
        // the first check in OR tell that there is space to fit word
        // if there is no space return false if there is space then lets check if after filling the word->
        // ->there is - or not if there is - remaining we cant fit the word->
        // there as in one ROW/COL only one word will be there->
        // like acc to second check it shouldn't be (d e l h i - - p q)
        if(c-1 + word.length() >= grid[0].length){
            return false;
        }
        if((c-1+word.length() + 1 < grid[0].length - 1) && (grid[r][c+word.length()] != '+')){
            return false;
        }
        // check if there is - to fill the word or the character are same
        for(int j=0;j<word.length();j++){
            if(grid[r][j+c] != '-' && grid[r][j+c] != word.charAt(j)){
                return false;
            }
        }
        return true;
    }
    
    public static boolean[] placeHorizontally(char[][] grid,int r,int c,String word){
        boolean[] status = new boolean[word.length()];
        for(int j=0;j<word.length();j++){
            if(grid[r][c+j] == '-'){
                grid[r][c+j] = word.charAt(j);
                status[j] = true;
            }
        }
        return status;
    }
    
    public static void unplaceHorizontally(char[][] grid,int r,int c,boolean[] status){
        for(int i=0;i<status.length;i++){
            if(status[i] == true){
                grid[r][c+i] = '-';
            }
        }
    }
    
    // vertically 
    public static boolean canPlaceVertically(char[][] grid,int r,int c,String word){
        // top check
        if(r > 0 && grid[r-1][c] != '+'){ 
            return false;
        }
        // bottom
        if(r-1 + word.length() >= grid.length){
            return false;
        }
        if((r-1+word.length() < grid.length - 1) && (grid[r+word.length()][c] != '+')){
            return false;
        }
        for(int i=0;i<word.length();i++){
            if(grid[i+r][c] != '-' && grid[i+r][c] != word.charAt(i)){
                return false;
            }
        }
        return true;
    }
    
    public static boolean[] placeVertically(char[][] grid,int r,int c,String word){
        boolean[] status = new boolean[word.length()];
        for(int i=0;i<word.length();i++){
            if(grid[r+i][c] == '-'){
                grid[r+i][c] = word.charAt(i);
                status[i] = true;
            }
        }
        return status;
        
    }
    
    public static void unplaceVertically(char[][] grid,int r,int c,boolean[] status){
        for(int i=0;i<status.length;i++){
            if(status[i] == true){
                grid[r+i][c] = '-';
            }
        }
    }
    
    public static void displayPuzzle(char[][] grid){
        for(int i = 0; i < grid.length; i++){
          for(int j = 0; j < grid[0].length; j++){
            System.out.print(grid[i][j]);
          }
          System.out.println();
        }
    }
    
    public static void solution(char[][] grid, String[] words, int vidx){
        if(vidx == words.length){
            displayPuzzle(grid);
            return;
        }

        String str = words[vidx];
        char ch = str.charAt(0);
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j] == '-' || grid[i][j] == ch){
                    // 1. try placing horizontally
                    if(canPlaceHorizontally(grid,i,j,str) == true){
                        // place 
                        boolean[] status = placeHorizontally(grid,i,j,str);
                        // call
                        solution(grid, words, vidx+1);
                        // unplace
                        unplaceHorizontally(grid,i,j,status);
                    }
                    if(canPlaceVertically(grid,i,j,str) == true){
                        // place 
                        boolean[] status = placeVertically(grid,i,j,str);
                        // call
                        solution(grid, words, vidx+1);
                        // unplace
                        unplaceVertically(grid,i,j,status);
                    }
                }
            }
        }
	}
    
    // k-partition
    static int counter = 1;
	public static void solution(int i, int n, int k, int rssf, ArrayList<ArrayList<Integer>> ans) {
	    if(i > n){
	        if(ans.size() == k){
	            System.out.print(counter+". ");
	            for(int j = 0; j < ans.size(); j++) {
                    ArrayList<Integer> list = ans.get(j);
                    System.out.print(list + " ");
                }
	            System.out.println();
	            counter++;
	        }
	        return;
	    }
	   // add to prev
	   for(int j=0;j<ans.size();j++){
	       ArrayList<Integer> li = ans.get(j);
	       li.add(i);
	       solution(i+1,n,k,rssf,ans);
	       li.remove(li.size()-1);
	   }
	   // start from self
	   if(ans.size()+1 <= k){
	       ArrayList<Integer> myres = new ArrayList<>();
	       myres.add(i);
	       ans.add(myres);
	       solution(i+1,n,k,rssf,ans);
	       ans.remove(ans.size()-1);
	   }
	}
    
    // Magnets 
    public static int signCountInRow(char[][] ans, int row, char sign) {
        int count = 0;
        for (int c = 0; c < ans[0].length; c++) {
            if (ans[row][c] == sign) {
                count++;
            }
        }
        return count;
    }

    public static int signCountInCol(char[][] ans, int col, char sign) {
        int count = 0;
        for (int r = 0; r < ans.length; r++) {
            if (ans[r][col] == sign) {
                count++;
            }
        }
        return count;
    }

    public static boolean isValid(char[][] ans, int[] top, int[] left, int[] right, int[] bottom, int r, int c,
            char sign) {
        // make a check for valid polarity
        int[] xdir = { -1, 0, 0 };
        int[] ydir = { 0, 1, -1 };
        for (int d = 0; d < 3; d++) {
            int rr = r + xdir[d];
            int cc = c + ydir[d];
            if (rr >= 0 && rr < ans.length && cc >= 0 && cc < ans[0].length && ans[rr][cc] == sign) {
                return false;
            }
        }
        // make a check for valid sign count in row and col
        int cir = signCountInRow(ans, r, sign); // cir -> count in row
        int cic = signCountInCol(ans, c, sign); // cic -> count in column

        // top and left -> +ve sign, bottom ans right -> -ve sign
        if (sign == '+') {
            if ((top[c] != -1 && cic >= top[c]) || (left[r] != -1 && cir >= left[r])) {
                return false;
            }
        } else {
            if ((bottom[c] != -1 && cic >= bottom[c]) || (right[r] != -1 && cir >= right[r])) {
                return false;
            }
        }
        return true;
    }

    public static boolean isCorrectResult(char[][] ans, int[] top, int[] left, int[] bottom, int[] right) {
        // check for row
        for (int r = 0; r < ans.length; r++) {
            int pcount = 0; // positive count
            int ncount = 0; // negative count
            for (int c = 0; c < ans[0].length; c++) {
                if (ans[r][c] == '+')
                    pcount++;
                else if (ans[r][c] == '-')
                    ncount++;
            }
            if (left[r] != -1 && left[r] != pcount)
                return false;
            if (right[r] != -1 && right[r] != ncount)
                return false;
        }
        // check for col
        for (int c = 0; c < ans[0].length; c++) {
            int pcount = 0; // positive count
            int ncount = 0; // negative count
            for (int r = 0; r < ans.length; r++) {
                if (ans[r][c] == '+')
                    pcount++;
                else if (ans[r][c] == '-')
                    ncount++;
            }
            if (top[c] != -1 && top[c] != pcount)
                return false;
            if (bottom[c] != -1 && bottom[c] != ncount)
                return false;
        }
        return true;
    }

    public static boolean solution(char[][] arr, int[] top, int[] left, int[] right, int[] bottom, char[][] ans,
            int row, int col) {
        if (col == arr[0].length) {
            col = 0;
            row++;
        }
        if (row == ans.length) {
            if (isCorrectResult(ans, top, left, bottom, right))
                return true;
            else
                return false;
        }
        // yes call
        if (arr[row][col] == 'L') {
            // [L | R] -> + -
            if (isValid(ans, top, left, right, bottom, row, col, '+')
                    && isValid(ans, top, left, right, bottom, row, col + 1, '-')) {
                // place + -
                ans[row][col] = '+';
                ans[row][col + 1] = '-';
                if (solution(arr, top, left, right, bottom, ans, row, col + 2) == true) {
                    return true;
                }
                // unplace + -
                ans[row][col] = 'X';
                ans[row][col + 1] = 'X';
            }
            // [L | R] -> - +
            if (isValid(ans, top, left, right, bottom, row, col, '-')
                    && isValid(ans, top, left, right, bottom, row, col + 1, '+')) {
                // place - +
                ans[row][col] = '-';
                ans[row][col + 1] = '+';
                if (solution(arr, top, left, right, bottom, ans, row, col + 2) == true) {
                    return true;
                }
                // unplace - +
                ans[row][col] = 'X';
                ans[row][col + 1] = 'X';
            }
        } else if (arr[row][col] == 'T') {
            // [T | B] -> + -
            if (isValid(ans, top, left, right, bottom, row, col, '+')
                    && isValid(ans, top, left, right, bottom, row + 1, col, '-')) {
                // place + -
                ans[row][col] = '+';
                ans[row + 1][col] = '-';
                if (solution(arr, top, left, right, bottom, ans, row, col + 1) == true) {
                    return true;
                }
                // unplace + -
                ans[row][col] = 'X';
                ans[row + 1][col] = 'X';
            }
            // [T | B] -> - +
            if (isValid(ans, top, left, right, bottom, row, col, '-')
                    && isValid(ans, top, left, right, bottom, row + 1, col, '+')) {
                // place - +
                ans[row][col] = '-';
                ans[row + 1][col] = '+';
                if (solution(arr, top, left, right, bottom, ans, row, col + 1) == true) {
                    return true;
                }
                // unplace - +
                ans[row][col] = 'X';
                ans[row + 1][col] = 'X';
            }
        }
        // no call
        if (solution(arr, top, left, right, bottom, ans, row, col + 1)) {
            return true;
        }
        return false;
    }
    // char[][] ans = new char[m][n];
    // for(int i=0;i<ans.length;i++){
    //     for(int j=0;j<ans[0].length;j++){
    //         ans[i][j] = 'X';
    //     }
    // }
    // boolean res = solution(arr,top,left,right,bottom,ans,0,0);
    // print(ans);


    // Friends Pairing - 2
    static int count = 1;
    public static void solution(int i, int n, boolean[] used, String asf) {
        if(i > n){
            System.out.println(count+"."+asf);
            count+=1;
            return;
        }

        // no call
        if(used[i] == true){
            // already included in lower level so no options
            solution(i+1,n,used,asf);
            
        }else{
            // yes call
            // single call
            used[i] = true;
            solution(i+1,n,used,asf+"("+i+") ");
            
            // pairup call
            for(int k=i+1;k<=n;k++){
                if(used[k] == false){
                    used[k] = true;
                    solution(i+1, n, used, asf+"("+i+","+k+") ");
                    used[k] = false;
                }
            }
            used[i] = false;
     
        }
    }
    // All Palindromic Permutations
    public static String reverseString(String str){
        String res = "";
        for(int i=str.length()-1;i>=0;i--){
            res += str.charAt(i);
        }
        return res;
    }
    public static void generatepw(int cs, int ts, HashMap<Character, Integer> fmap, Character oddc, String asf) {
		if(cs == ts){
            String rev = reverseString(asf);
            if(oddc == null){
                System.out.println(asf+rev);
            }else{
                System.out.println(asf+oddc+rev);
            }
            return;
        }
        for(char ch:fmap.keySet()){
            if(fmap.get(ch) > 0){
                int oldFreq = fmap.get(ch);
                fmap.put(ch, oldFreq-1); //mark
                generatepw(cs+1, ts, fmap,oddc, asf+ch);
                fmap.put(ch, oldFreq); //unmark
            }
        }
	}
    public static void generatepwMain(HashMap<Character,Integer> fmap){
        int oddCount = 0;
        Character ch = null;
        int ts = 0;
        for(char key:fmap.keySet()){
            ts += fmap.get(key);
            if(fmap.get(key) % 2 == 1){
                oddCount++;
                ch = key;
            }
            fmap.put(key, fmap.get(key)/2);
        }
        if(oddCount > 1){
            System.out.println("-1");
            return;
        }
        generatepw(0, ts/2, fmap, ch, "");
    }
    // All Palindromic Partitions
    public static boolean isStringPallindrome(String str){
        int left = 0;
        int right = str.length()-1;
        while(left < right){
            if(str.charAt(left) != str.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
    
    public static void solution(String str, String asf) {
        if(str.length() == 0){
            System.out.println(asf);
            return;
        }
        for(int i=0;i<str.length();i++){
            String options = str.substring(0, i+1);
            String ros = str.substring(i+1);
            if(isStringPallindrome(options) == true){
                solution(ros, asf+"("+options+") ");
            }
        }
		
	}
    // K Subsets With Equal Sum
    public static  boolean equalSum(int[] subsetSum){
        int val = subsetSum[0];
        for(int el:subsetSum){
            if(el != val) return false;
        }
        return true;
    }
    
    public static void solution(int[] arr, int indx,int sum , int k,int[] subsetSum,int ssf, ArrayList<ArrayList<Integer>> ans) {
        if(indx == arr.length){
            if(ssf == k){
                if(equalSum(subsetSum) == true){
                    for(ArrayList<Integer> list : ans){
                        System.out.print(list+" ");
                    }
                    System.out.println();
                }
            }
            return;
        }
        
        int val = arr[indx];
        // existing me add
        int i = 0;
        while(i<ans.size() && ans.get(i).size() > 0){
            if(subsetSum[i] + val <= (sum/k)){
                // increase in subset sum
                subsetSum[i] += val;
                // add in ans
                ans.get(i).add(val);
                
                // call
                solution(arr, indx + 1, sum, k, subsetSum, ssf, ans);
                
                // decrease subset sum
                subsetSum[i] -= val;
                // remove from ans
                ans.get(i).remove(ans.get(i).size()-1);
            }
            i++;
        }
        // nayi shurvaat (single call with new set)
        if(i < k){
            // increase in subset sum
            subsetSum[i] += val;
            // add in ans
            ans.get(i).add(val);
            
            // call
            solution(arr, indx + 1, sum, k, subsetSum, ssf + 1, ans);
            
            // decrease subset sum
            subsetSum[i] -= val;
            // remove from ans
            ans.get(i).remove(ans.get(i).size()-1);

        }
	}
    // solution(arr,0,sum,k,subsetSum,0,ans);
    
    // Tug Of War
    // soset1,soset2=>sum of set 1,2
    static int mindiff = Integer.MAX_VALUE;
	static String ans = "";
	public static void solve(int[] arr, int indx, ArrayList<Integer> s1, ArrayList<Integer> s2, int sum1,int sum2) {
        if(indx == arr.length){
            int diff = Math.abs(sum1-sum2);
            if(diff<mindiff){
                mindiff = diff;
                ans = ""+s1+" "+s2;
            }
            return;
        }

        int val = arr[indx];
        // val in set1 
        if(s1.size() < ((arr.length+1)/2)){
            s1.add(val);
            solve(arr, indx+1, s1, s2, sum1+val, sum2);
            s1.remove(s1.size()-1);
        }
        // val in set2
        if(s1.size() > 0 && s2.size() < ((arr.length+1)/2)){
            s2.add(val);
            solve(arr, indx+1, s1, s2, sum1, sum2+val);
            s2.remove(s2.size()-1);
        }
	}
    // /Pattern Matching
    public static void solution(String str, String pattern, HashMap<Character,String> map, String asf,int indx){
        if(indx == pattern.length()){
            if(str.length() == 0){
                System.out.println(asf+".");
            }
            return;
        }

        char ch = pattern.charAt(indx);
        String mapping = map.get(ch);
        for(int i=0;i<str.length();i++){
            String substr = str.substring(0, i+1);
            String ros = str.substring(i+1);
            
            // mapping
            map.put(ch, substr);
            if(mapping.length() > 0){
                if(substr.equals(mapping) == true){
                    solution(ros, pattern, map, asf, indx+1);
                }
            }else{
                solution(ros, pattern, map, asf+ch+" -> "+substr+", ", indx+1);
            }
            // reset mapping
            map.put(ch, mapping);
        }
	}
    // for(int i=0;i<pattern.length();i++){
    //     map.put(pattern.charAt(i),"");
    // }
    // solution(str,pattern,map,"",0);

    // Word Break - I
    public static void wordBreak(String str, String ans, HashSet<String> dict){ 
        if(str.length() == 0){
            System.out.println(ans);
            return;
        }

        for(int i=0;i<str.length();i++){
            String subStr = str.substring(0, i+1);
            String ros = str.substring(i+1);
            if(dict.contains(subStr) == true){
                wordBreak(ros, ans+subStr+" ", dict);
            }
        }
	}
    // /Remove Invalid Parenthesis
    public static void solution(String str, int minRemoval, HashSet<String> ans) {
        if(minRemoval == 0){
            if(getMin(str) == 0 && ans.contains(str) == false){
                ans.add(str);
                System.out.println(str);
            }
            return;
        }

        for(int i=0;i<str.length();i++){
            String left = str.substring(0, i);
            String right = str.substring(i+1);

            solution(left+right,minRemoval-1,ans);
        }
	}

	public static int getMin(String str){
        Stack<Character> st = new Stack<>();
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if(ch == '('){
                st.push(ch);
            }else if(ch == ')'){
                if(st.size() > 0 && st.peek() == '('){
                    st.pop();
                }else{
                    st.push(ch);
                } 
            }
        
        }
        return st.size();
	}

    // REmove

    
    public static void main(String[] args) {
    }
}