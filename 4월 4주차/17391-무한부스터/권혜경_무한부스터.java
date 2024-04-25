// bfs로 풀엇는데, 방향이 2개인걸 못봐서 한 번 틀려서 제법 속상합니다

import java.util.*;
import java.io.*;
public class Main {
    static int N, M, answer = 0;
    static int map[][];
    static boolean visit[][];
    static int dy[] = {1,0};
    static int dx[] = {0,1};

    static Queue<Node> queue = new ArrayDeque<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visit = new boolean[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        queue.offer(new Node(0, 0, map[0][0]));
        
        go();

        System.out.println(answer);
    }
    static void go(){
        int ny, nx;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int j=0;j<size;j++){
                Node temp = queue.poll();
                if(temp.y==N-1 && temp.x==M-1) return;
                
                for(int i=0;i<2;i++){
                    for(int k=1;k<=temp.cost;k++){
                        ny = temp.y+k*dy[i];
                        nx = temp.x+k*dx[i];

                        if(ny>=N || ny<0 || nx>=M || nx<0 || visit[ny][nx]) continue;
                        visit[ny][nx] = true;
                        queue.offer(new Node(ny, nx, map[ny][nx]));
                    }
                }
            }
            answer++;
        }
    }
    static class Node{
        int y, x, cost;
        Node(int y, int x, int cost){
            this.y=y;
            this.x=x;
            this.cost=cost;
        }
    }
}
