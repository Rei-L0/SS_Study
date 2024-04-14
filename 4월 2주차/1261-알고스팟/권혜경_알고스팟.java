// 방문 미방문으로 하다보니 다익스트라라는 것을 알았습니다

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
    static int N, M;
    static int map[][], visit[][];
    static int dy[] = {1,0,-1,0};
    static int dx[] = {0,1,0,-1};
    static PriorityQueue<Node> pqueue = new PriorityQueue<>((o1,o2)->o1.c-o2.c);
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new int[N][M];

        for(int i = 0; i < N; i++){
            String s = br.readLine();
            Arrays.fill(visit[i],Integer.MAX_VALUE);
            for(int j = 0; j < M; j++){
                map[i][j] = s.charAt(j)-'0';
            }
        }

        go();
//        for(int i = 0; i < N; i++){
//            for(int j = 0; j < M; j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println("");
//        }
        System.out.println(visit[N-1][M-1]);
    }
    static void go(){

        pqueue.offer(new Node(0,0,0));
        visit[0][0] = 0;
        while(!pqueue.isEmpty()){
            Node temp = pqueue.poll();
            int y = temp.y, x = temp.x, count = temp.c;

            if(y == N-1 && x == M-1){
                visit[y][x] = Math.min(count, visit[y][x]);
                break;
            }
            else if(visit[N-1][M-1] <= count) continue;

            //System.out.println(y+" "+x+" "+count);

            for(int i = 0; i < 4; i++){
                int ny = y + dy[i];
                int nx = x + dx[i];

                if(ny<0 || ny>=N || nx<0 || nx>=M) continue;

                if(map[ny][nx] == 0 && visit[ny][nx]>count) {
                    visit[ny][nx] = count;
                    pqueue.offer(new Node(ny, nx, count));
                }
                else if(map[ny][nx] == 1 && visit[ny][nx]>count+1) {
                    visit[ny][nx] = count+1;
                    pqueue.offer(new Node(ny, nx, count+1));
                }

            }
        }

    }
    static class Node{
        int y, x, c;
        Node(int y, int x, int c){
            this.y = y;
            this.x = x;
            this.c = c;
        }
    }
}
