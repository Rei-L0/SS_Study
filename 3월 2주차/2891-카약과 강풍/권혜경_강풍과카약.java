import java.io.*;
import java.util.*;

public class Main {
    static int N,S,R,answer=10;
    static int kayak[]; 

    // 상태 정하기
    // 0 : 멀쩡한 카약 / 하나의 부서진 카약 + 예비 카약
    // 1 : 부서진 카약
    // -1 : 예비카약

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        kayak = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<S;i++){
            int now = Integer.parseInt(st.nextToken());
            kayak[now] = 1;
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<R;i++){
            int now = Integer.parseInt(st.nextToken());
            kayak[now] = kayak[now] - 1;
        }

        check(1,0);
        System.out.println(answer);
    }
    static void check(int idx, int visit){
      // 기저조건
        if(idx==N+1){
            int count=0;
            for(int i=1;i<=N;i++){
                if(kayak[i]==1 && (visit&1<<i)==0) count++;
            }
            answer = Math.min(answer,count);
            return;
        }
      // 빌려줄 수 없으면 다음으로 
        if(kayak[idx]!=-1) {
            check(idx+1, visit);
            return;
        }
      // 빌려줄 수 있다면 무조건 앞뒤로 빌려주기
        check(idx+1, visit|1<<(idx-1));
        check(idx+1, visit|1<<(idx+1));
    }
}
