import java.io.*;
import java.util.*;

// 정렬해서 target을 정하고 투포인터로 돌면서 체크하기

public class B_좋다_1253 {
    static int N, answer = 0;
    static int input[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        input = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            input[i] = Integer.parseInt(st.nextToken());
        }

        if(N==1||N==2) {System.out.println(0); return;}

        Arrays.sort(input);

        int target = 1_000_000_001; 
        for(int k=N-1;k>-1;k--){
            int front = 0, back = N-1;
            target = input[k];
            while (front<back) {
                if(front==k) front++;
                if(back==k) back--;
                else {
                    if(input[front]+input[back] < target) front++;
                    else if (input[front]+input[back] > target) back--;
                    else if(input[front]+input[back] == target){ 
                        answer++;
                        for(;k>0;k--){
                            if(input[k-1]!=target) break;
                            answer++;
                        }
                        break;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
