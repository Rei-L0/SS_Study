// 자꾸 시초나서 민이의 조언을 참고하였습니다

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    static int N, answer = 0;
    static int num[] = new int[1_000_002];
    static int input[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            input[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N;i++){
            if(num[input[i]]>0) num[input[i]]--;
            num[input[i]-1]++;
        }

        for(int i=0;i<1_000_002;i++){
            answer += num[i];
        }

        System.out.println(answer);
    }
}
