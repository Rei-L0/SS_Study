import java.io.*;
import java.util.*;
/*

f, b은 인덱스
front, back은 인덱스 값

경우를 크게는 양수값만 나오는 경우, 음수값만 나오는 경우, 양수+음수 나오는 경우로 나눈 후
양+음 의 경우에도 양양, 양음 음음 의 합 별로 비교함

*/
public class Main {
    static int N, f, b, front, back, answer, zero; //zero 첫번쩨 양수 인덱스
    static int ans[] = new int[2];
    static int input[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        input = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            input[i] = Integer.parseInt(st.nextToken());
        }
        
        f = 0;
        b = N-1;

        if(input[f] > 0) {
            answer = input[f]+input[f+1];
            ans[0] = input[f];
            ans[1] = input[f+1];
            
            zero = 0;
        }
        else if(input[b] < 0) {
            answer = input[b-1]+input[b];
            ans[0] = input[b-1];
            ans[1] = input[b];
            
            zero = N;
        }
        else{
            answer = 2_000_000_001;
            
            // 음 양의 최소값구하기
            while(input[f]<0 && input[b]>0){
                check();
                if(-front<back) b--;
                else if(-front>back) f++;
                else break;
            }
            if(answer!=0){
                frontback(); 
                check();
                frontfront();
                check();
                backback();
                check();
                //System.out.println(answer+" "+ans[0]+" "+ans[1]);
            }

        }
        System.out.println(ans[0]+" "+ans[1]);
    }
    static void frontback(){
        if(input[f]>0) {
            zero = f;
            b = f;
            f = f-1;
        }
        else if(input[b]<0) {
            zero = b+1;
            f = b;
            b = b+1; 
        }
    }
    static void frontfront(){
        if(zero>=2){
            f = zero-2;
            b = zero-1;
        }
    }
    static void backback(){
        if(zero<=N-2){
            f = zero;
            b = zero+1;
        }
    }
    static void check(){
        front = input[f];
        back = input[b];
        if(answer>Math.abs(front+back)){
            answer = Math.abs(front+back);
            ans[0] = front;
            ans[1] = back;
        }
    }
}
