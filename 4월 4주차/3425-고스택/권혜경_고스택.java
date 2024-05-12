// 나누기, 나머지 연산을 경우에 따라서 계산하려고 하니 틀린거더군요,, 
// 어이없습니다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

class Main{
    static Stack<Long> stack = new Stack<>();
    static List<String> queue = new ArrayList<>();
    static int N;
    static String input;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer order;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        while(true){
            inputorder();
            if(queue.get(queue.size()-1).equals("QUIT")) break;
        }
        System.out.println(sb.toString());
    }
    static void inputorder() throws IOException {
        boolean go = true;

        while(go){
            input = br.readLine();
            switch(input){
                case "QUIT" :
                    go = false;
                    queue.clear();
                    queue.add(input);
                    break;
                case "END" :
                    N = Integer.parseInt(br.readLine());
                    for(int i = 0; i < N; i++){
                        stack.push(Long.parseLong(br.readLine()));
                        doit();
                        stack.clear();
                    }

                    queue.clear();
                    br.readLine();
                    sb.append("\n");
                    break;

                default:
                    queue.add(input);
                    break;
            }
        }


    }
    static void doit(){
        long temp_1, temp_2;
        boolean go = true;
        for(String input : queue){
//            System.out.println(input);
            if(!stack.isEmpty() && Math.abs(stack.peek())>1_000_000_000) go = false;
            if(!go) break;
            order = new StringTokenizer(input);
            switch(order.nextToken()){
                case "NUM": stack.push(Long.parseLong(order.nextToken())); break;
                case "POP":
                    if(!stack.isEmpty()) stack.pop();
                    else go = false;
                    break;
                case "INV":
                    if(!stack.isEmpty()) stack.push(-stack.pop());
                    else go = false;
                    break;
                case "DUP":
                    if(!stack.isEmpty()) stack.push(stack.peek());
                    else go = false;
                    break;
                case "SWP":
                    if(stack.size()>=2){
                        temp_1 = stack.pop();
                        temp_2 = stack.pop();
                        stack.push(temp_1);
                        stack.push(temp_2);
                    }
                    else go = false;
                    break;
                case "ADD":
                    if(stack.size()>=2){
                        temp_1 = stack.pop();
                        temp_2 = stack.pop();
                        stack.push(temp_1+temp_2);
                    }else go = false;
                    break;
                case "SUB":
                    if(stack.size()>=2){
                        temp_1 = stack.pop();
                        temp_2 = stack.pop();
                        stack.push(temp_2-temp_1);
                    }else go = false;
                    break;
                case "MUL":
                    if(stack.size()>=2){
                        temp_1 = stack.pop();
                        temp_2 = stack.pop();
                        stack.push(temp_1*temp_2);
                    }else go = false;
                    break;
                case "DIV":
                    if(stack.size()>=2){
                        temp_1 = stack.pop();
                        temp_2 = stack.pop();
                        if(temp_1 != 0) {
                            if (temp_1 * temp_2 > 0) stack.push(Math.abs(temp_2) / Math.abs(temp_1));
                            else stack.push(-Math.abs(temp_2) / Math.abs(temp_1));
                        }
                        else go = false;
                    }else go = false;
                    break;
                case "MOD":
                    if(stack.size()>=2) {
                        temp_1 = stack.pop();
                        temp_2 = stack.pop();
                        if (temp_1 != 0)  stack.push(temp_2%temp_1);
                        else go = false;
                    } else go = false;
                    break;
            }

        }
        if(stack.size()!=1 || Math.abs(stack.peek())>1_000_000_000) go = false;
        if(!go) sb.append("ERROR\n");
        else sb.append(stack.pop()).append("\n");
    }
}
