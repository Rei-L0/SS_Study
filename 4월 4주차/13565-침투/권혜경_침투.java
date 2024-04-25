// 완탐으로, 들어갈 수 있는 모든 위에서 시작해서 밑에 도달하면 종료합니다

import java.io.*;
import java.util.*;

public class Main {
    static int M, N;
    static boolean answer = false;
    static char map[][];
    static boolean visit[][];

    static Queue<Node> queue = new ArrayDeque<>();

    static int dy[] = {1,0,-1,0};
    static int dx[] = {0,1,0,-1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][];
        visit = new boolean[N][M];

        for(int i=0;i<N;i++){
            map[i] = br.readLine().toCharArray();
        }

        for(int i=0;i<M;i++){
            if(answer) break;
            if(map[0][i]=='0' && !visit[0][i]) dfs(0,i);
        }

        if(answer) System.out.println("YES");
        else System.out.println("NO");
    }
    static void dfs(int y, int x){
        visit[y][x] = true;
        queue.offer(new Node(y, x));

        while(!queue.isEmpty()){
            Node temp = queue.poll();

            if(temp.y==N-1) {
                answer = true;
                return;
            }

            int ny, nx;
            for(int i=0;i<4;i++){
                ny = temp.y+dy[i];
                nx = temp.x+dx[i];
                if( ny>=N || ny<0 || nx>=M || nx<0 || map[ny][nx]!='0' || visit[ny][nx]) continue;
                
                visit[ny][nx] = true;
                queue.offer(new Node(ny, nx));
            }
        }
    }
    static class Node{
        int y, x;
        Node(int y, int x){
            this.y=y;
            this.x=x;
        }
    }
}
