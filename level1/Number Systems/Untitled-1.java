import java.util.*;
                            public class Main{
                            public static void main(String[] args) {
                            Scanner scn = new Scanner(System.in);
                            int b = scn.nextInt();
                            int n1 = scn.nextInt();
                            int n2 = scn.nextInt();
                            int d = getProduct(b, n1, n2);
                            System.out.println(d);
                            }
                            // we write a helper function to ease our computation in
                            // int getProduct() method.
                            //this function helps us multiply a single digit of multiplier
                            //with the entire multiplicand
                            public static int getProductWithSingleDigit(int b, int n1, int d2)
                            {
                            int rv = 0;
                            int p =1;
                            int c =0;
                            while(n1>0 || c>0) //we proceed with multiplication till
                            // the multiplier exhausts or carry is not zero
                            {
                            int d1 = n1 % 10;
                            n1 = n1/10;
                            int d = d1*d2 + c;
                            c = d/b; //setting carry
                            d = d%b; //setting digit
                            rv = rv + d*p; // updating return_value
                            p = p*10; //updating power
                            }
                            return rv;
                            }
                            // We dont "Re-write" the any base addition code
                            // and re-use the method we used earlier.
                            // Let us call it magic because we know it will definitely work
                            // upon the inputs we give it.
                            public static int getSum(int b, int n1, int n2){
                            int rv = 0;
                            int p = 1;
                            int c = 0;
                            while( n1 > 0 || n2 > 0 || c > 0) //while numbers are not-zero or carry is non-zero
                            {
                            int d1 = n1 % 10; //extracting digits
                            int d2 = n2 % 10;
                            int d = d1 + d2 + c; //adding up extracted digits
                            c = d/b; //storing carry for next addition
                            d = d%b;
                            rv += d*p;
                            p *= 10;
                            n1 /= 10;
                            n2 /= 10;
                            }
                            return rv;
                            }
                            public static int getProduct(int b, int n1, int n2){
                            int rv = 0;
                            int p =1;
                            while( n2 > 0) // while the multiplicand is not exhausted
                            {
                            int d2 = n2 % 10;
                            n2 = n2/10;
                            int single_product = getProductWithSingleDigit(b, n1, d2);
                            //we extract product of the rightmost digit of n2 with whole of n1
                            single_product *= p;
                            // we set the correct place value for addition of extracted number
                            rv = getSum(b,rv,single_product);
                            // we use any base addition to just simply add the products we get one by one
                            //until the number n2 does not turn zero
                            p = p*10;
                            }
                            return rv;
                            }
                            }