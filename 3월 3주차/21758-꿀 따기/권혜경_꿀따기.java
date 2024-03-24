// 무조건 클려면 좌 우로 하나는 고정하고 움직여야한다고 생각했고 그렇게 풀었습니다

import java.io.*;
import java.util.*;

public class Main {
    static int N, total = 0, total_r = 0, total_l = 0, answer = 0;
    static int input[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            input[i] = Integer.parseInt(st.nextToken());
            total += input[i];
        }

        total_r=total;
        total_l=total;

        for(int i=1;i<N-1;i++){
            answer = Math.max(answer, total-input[0]-input[N-1]+input[i]);

            answer = Math.max(answer, total+total_l-input[0]*2-input[i]*2);
            total_l-=input[i];

            answer = Math.max(answer, total+total_r-input[N-1]*2-input[N-1-i]*2);
            total_r-=input[N-1-i];
        }

        System.out.println(answer);
    }
}
