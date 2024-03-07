import java.io.*;
import java.util.*;

public class B_근손실_18429 {
    static int N, K, hp, answer = 0;
    static int health[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        health = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            health[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N;i++){
            hp = hp - K + health[i];
            com(1<<i);
            hp = hp + K - health[i];
        }
        System.out.println(answer);
    }
    static void com(int visit){
        if(hp<0) return;
        if(visit == ((1<<N)-1)) answer+=1;
        for(int i=0;i<N;i++){
            if((visit & 1<<i) != 0) continue;
            hp = hp - K + health[i];
            com(visit | 1<<i);
            hp = hp + K - health[i];
        }

    }
}
