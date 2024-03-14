import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int map[], answer=0, count=0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[1_000_001];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int how=Integer.parseInt(st.nextToken()), where=Integer.parseInt(st.nextToken());
            map[where] += how;
        }
        for(int i=0;i<1_000_001;i++){
            count+=map[i];
            if(i<2*K+1) continue;
            count-=map[i-1-2*K];

            answer = Math.max(answer, count);
        }
        if(answer==0) answer = count;
        System.out.println(answer);
    }
}
