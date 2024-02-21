package Baekjoon.백트래킹;
import java.util.*;
import java.io.*;

class P16987 {
    static int N;
    static int[][] egg;
    static int max_v;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        egg = new int[N][2];
        max_v = Integer.MIN_VALUE;

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            egg[i][0] = Integer.parseInt(st.nextToken());
            egg[i][1] = Integer.parseInt(st.nextToken());
        }

        DFS(0,0);
        System.out.println(max_v);
    }

    public static void  DFS(int idx, int brokenEgg) {

        max_v = Math.max(max_v,brokenEgg);

        if (idx == N) { 
            return;
        }

        if (egg[idx][0] <= 0) { // 들고 있는 계란이 깨져있을 때
            DFS(idx+1,brokenEgg);
            return;
        }

        for (int i=0; i<N; i++) {

            // 들고있는 계란이거나 칠 계란이 깨져있으면 continue ~ 
            if (idx == i || egg[i][0] <= 0) continue; 

            crash(idx,i); // 계란 부딪쳐주고 

            int tmp = 0; // 깨진 계란이 있으면 카운트 해주고
            if (egg[idx][0] <= 0) tmp++; 
            if (egg[i][0] <= 0 ) tmp++;

            DFS(idx+1, brokenEgg+tmp); // DFS 돌고

            back(idx,i); // 다시 원상 복구 

        }
    }

    public static void crash(int idx, int next) {
        egg[idx][0] -= egg[next][1];
        egg[next][0] -= egg[idx][1];
    }

    public static void back(int idx, int next) {
        egg[idx][0] += egg[next][1];
        egg[next][0] += egg[idx][1];
    }
}
