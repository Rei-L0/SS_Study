package Baekjoon.구현;

import java.io.*;
import java.util.*;

class P13901 { 
    static int R,C,K,cnt, start_x,start_y;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dx = new int[4];
    static int[] dy = new int[4];
    static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        graph = new int[R][C];
        visited = new boolean[R][C];

        K = Integer.parseInt(br.readLine());

        for (int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            graph[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = -1;
        }

        st = new StringTokenizer(br.readLine());
        start_x = Integer.parseInt(st.nextToken());
        start_y = Integer.parseInt(st.nextToken());
        graph[start_x][start_y] = 1;

        st = new StringTokenizer(br.readLine());
		for (int i=0; i<4; i++) {
			int n = Integer.parseInt(st.nextToken());
			if (n==1) {
				dx[i] = -1;
				dy[i] =0;
			} else if (n == 2) {
				dx[i] = 1;
				dy[i] = 0;
			} else if (n == 3) {
				dx[i] = 0;
				dy[i] = -1;
			} else {
				dx[i] = 0;
				dy[i] = 1;
			}
		}
        cnt = 0;
        go(0);
        System.out.println(start_x+" "+start_y);
    }

    public static void go(int d) {
        while (true) {
            int nx = start_x + dx[d];
            int ny = start_y + dy[d];

            if (check(nx,ny)) {
                graph[nx][ny] = 1;
                start_x = nx;
                start_y = ny;
                cnt = 0;
            }
            else {
                d = (d+1) % 4;
                cnt++;
            }
            if (cnt == 4) break;
        }
    }

    public static boolean check(int x, int y) {
        if (0 <= x && x < R && 0 <= y && y < C && graph[x][y] == 0) return true;
        else return false;
    }
}
