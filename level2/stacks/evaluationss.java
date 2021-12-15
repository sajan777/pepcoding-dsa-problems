import java.util.*;

public class evaluationss {

    private static int evaluate(int val1,int val2,char ch){
        if(ch == '*'){  
            return val1*val2;
        }else if(ch == '/'){
            return val1/val2;
        }else if(ch == '+'){
            return val1+val2;
        }else if(ch == '-'){    
            return val1-val2;
        }
        return 0;
    }
    
    private static int priority(char op){
        if(op == '/' || op == '*'){
            return 2;
        }else if(op == '+' || op == '-'){
            return 0;
        }else{
            return 0;
        }
    }

    // Infix Evaluation
    public static int infixEvaluation(String exp){
        Stack<Integer> vstack = new Stack<>(); // value stack
        Stack<Character> ostack = new Stack<>(); //operator stack
        for(int i=0;i<exp.length();i++){
            char ch = exp.charAt(i);
            if(ch == ' '){
                continue;
            }else if(ch >= '0' && ch <= '9'){
                vstack.push((int)ch-'0');
            }else if(ch == '('){
                ostack.push(ch);
            }else if(ch == ')'){
                while(ostack.peek() != '('){
                    char op = ostack.pop();
                    int v2 = vstack.pop();
                    int v1 = vstack.pop();
                    int res = evaluate(v1,v2,op);
                    vstack.push(res);
                }
                ostack.pop(); // pop the opening bracket
            }else{
                //ch =  operators
                while( ostack.size() > 0 && ostack.peek() != '(' && priority(ostack.peek()) >= priority(ch) ){
                    char op = ostack.pop();
                    int v2 = vstack.pop();
                    int v1 = vstack.pop();
                    int res = evaluate(v1,v2,op);
                    vstack.push(res);
                } 
                ostack.push(ch);
            }
        }
        while(ostack.size() > 0){
            char op = ostack.pop();
            int v2 = vstack.pop();
            int v1 = vstack.pop();
            int res = evaluate(v1,v2,op);
            vstack.push(res);
        }
        return vstack.pop();
    }

    // infix to prefix
    public static String infixToPrefix(String exp){
        Stack<String> vstack = new Stack<>(); // value stack
        Stack<Character> ostack = new Stack<>(); //operator stack
        for(int i=0;i<exp.length();i++){
            char ch = exp.charAt(i);
            if(ch == ' '){
                continue;
            }else if(ch >= 'a' && ch <= 'z'){
                vstack.push(ch+"");
            }else if(ch == '('){
                ostack.push(ch);
            }else if(ch == ')'){
                while(ostack.peek() != '('){
                    char op = ostack.pop();
                    String v2 = vstack.pop();
                    String v1 = vstack.pop();
                    String res = op + v1 + v2;
                    vstack.push(res);
                }
                ostack.pop(); // pop the opening bracket
            }else{
                //ch =  operators
                while( ostack.size() > 0 && ostack.peek() != '(' && priority(ostack.peek()) >= priority(ch) ){
                    char op = ostack.pop();
                    String v2 = vstack.pop();
                    String v1 = vstack.pop();
                    String res = op + v1 + v2;
                    vstack.push(res);
                } 
                ostack.push(ch);
            }
        }
        while(ostack.size() > 0){
            char op = ostack.pop();
            String v2 = vstack.pop();
            String v1 = vstack.pop();
            String res = op + v1 + v2;
            vstack.push(res);
        }
        return vstack.pop();
    }

    // infix to postfix
    public static String infixToPostfix(String exp){
        Stack<String> vstack = new Stack<>(); // value stack
        Stack<Character> ostack = new Stack<>(); //operator stack
        for(int i=0;i<exp.length();i++){
            char ch = exp.charAt(i);
            if(ch == ' '){
                continue;
            }else if(ch >= 'a' && ch <= 'z'){
                vstack.push(ch+"");
            }else if(ch == '('){
                ostack.push(ch);
            }else if(ch == ')'){
                while(ostack.peek() != '('){
                    char op = ostack.pop();
                    String v2 = vstack.pop();
                    String v1 = vstack.pop();
                    String res = v1 + v2 + op;
                    vstack.push(res);
                }
                ostack.pop(); // pop the opening bracket
            }else{
                //ch =  operators
                while( ostack.size() > 0 && ostack.peek() != '(' && priority(ostack.peek()) >= priority(ch) ){
                    char op = ostack.pop();
                    String v2 = vstack.pop();
                    String v1 = vstack.pop();
                    String res = v1 + v2 + op;
                    vstack.push(res);
                } 
                ostack.push(ch);
            }
        }
        while(ostack.size() > 0){
            char op = ostack.pop();
            String v2 = vstack.pop();
            String v1 = vstack.pop();
            String res = v1 + v2 + op;
            vstack.push(res);
        }
        return vstack.pop();
    }

    // Postfix Evaluation
    public static int postfixEvaluation(String exp){
        Stack<Integer> vstack = new Stack<>(); // value stack
        for(int i=0;i<exp.length();i++){
            char ch = exp.charAt(i);
            if(ch == ' '){
                continue;
            }else if(ch >= '1' && ch <= '9'){
                vstack.push((int)ch-'0');
            }else{
                //ch =  operators
                int v2 = vstack.pop();
                int v1 = vstack.pop();
                int res = evaluate(v1,v2,ch);
                vstack.push(res);
            }
        }
        return vstack.pop();
    }

    // Postfix to Infix
    public static String postfixToInfix(String exp){
        Stack<String> vstack = new Stack<>(); // value stack
        for(int i=0;i<exp.length();i++){
            char ch = exp.charAt(i);
            if(ch == ' '){
                continue;
            }else if(ch >= '0' && ch <= '9'){
                vstack.push(ch+"");
            }else{
                //ch =  operators
                String v2 = vstack.pop();
                String v1 = vstack.pop();
                String res = "("+v1+ch+v2+")";
                vstack.push(res);
            }
        }
        return vstack.pop();
    }

    // Postfix to Prefix
    public static String postfixToPrefix(String exp){
        Stack<String> vstack = new Stack<>(); // value stack
        for(int i=0;i<exp.length();i++){
            char ch = exp.charAt(i);
            if(ch == ' '){
                continue;
            }else if(ch >= '0' && ch <= '9'){
                vstack.push(ch+"");
            }else{
                //ch =  operators
                String v2 = vstack.pop();
                String v1 = vstack.pop();
                String res = ch+v1+v2;
                vstack.push(res);
            }
        }
        return vstack.pop();
    }

    // Prefix Evaluation
    public static int prefixEvaluation(String exp){
        Stack<Integer> vstack = new Stack<>();
        for(int i=exp.length()-1;i>=0;i--){
            char ch = exp.charAt(i);
            if(ch >= '1'  && ch <= '9'){
                vstack.push((int)ch-'0');
            }else {
                // ch = operand
                int val1 = vstack.pop();
                int val2 = vstack.pop();
                int res = evaluate(val1, val2, ch);
                vstack.push(res);
            }
        }
        return vstack.pop();
    }

    // Prefix to Infix
    public static String prefixToInfix(String exp){
        Stack<String> vstack = new Stack<>();
        for(int i=exp.length()-1;i>=0;i--){
            char ch = exp.charAt(i);
            if(ch >= '1'  && ch <= '9'){
                vstack.push(ch+"");
            }else {
                // ch = operand
                String val1 = vstack.pop();
                String val2 = vstack.pop();
                String res = "("+val1+ch+val2+")";
                vstack.push(res);
            }
        }
        return vstack.pop();
    }

    // Prefix to Prefix
    public static String prefixToPostfix(String exp){
        Stack<String> vstack = new Stack<>();
        for(int i=exp.length()-1;i>=0;i--){
            char ch = exp.charAt(i);
            if(ch >= '1'  && ch <= '9'){
                vstack.push(ch+"");
            }else {
                // ch = operand
                String val1 = vstack.pop();
                String val2 = vstack.pop();
                String res = val1+val2+ch;
                vstack.push(res);
            }
        }
        return vstack.pop();
    }


    public static void main(String[] args) {
        
    }
}
