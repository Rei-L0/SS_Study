// Queue로 해서 메모리초과가 나서 PriorityQueue로 다시 함

import java.io.*;
import java.util.*;

class Main {
    static int N, M, answer = 0;
    static int danger[][] = new int[501][501];
    static boolean visit[][] = new boolean[501][501];

    static Queue<Node> queue = new PriorityQueue<>((o1,o2)-> o1.life-o2.life);
    static int dy[]= {-1,0,1,0};
    static int dx[]= {0,-1,0,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int x1,y1,x2,y2;
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            x1= Integer.parseInt(st.nextToken());
            y1= Integer.parseInt(st.nextToken());
            x2= Integer.parseInt(st.nextToken());
            y2= Integer.parseInt(st.nextToken());

            for(int a=Math.min(y1,y2);a<=Math.max(y1,y2);a++){
                for(int b=Math.min(x1,x2);b<=Math.max(x1,x2);b++){
                    danger[a][b] = -501*501+2;
                }
            }
        }

        M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            x1= Integer.parseInt(st.nextToken());
            y1= Integer.parseInt(st.nextToken());
            x2= Integer.parseInt(st.nextToken());
            y2= Integer.parseInt(st.nextToken());

            for(int a=Math.min(y1,y2);a<=Math.max(y1,y2);a++){
                for(int b=Math.min(x1,x2);b<=Math.max(x1,x2);b++){
                    danger[a][b] = 501*501+2;
                }
            }
        }

        bfs();
    }
    static void bfs(){
        int ny, nx, life;
        Node temp;

        queue.offer(new Node(0,0,0));
        visit[0][0] = true;

        total : while(!queue.isEmpty()){
            temp = queue.poll();
//            System.out.println(temp);
            for(int j=0;j<4;j++){
                ny = temp.y+dy[j];
                nx = temp.x+dx[j];
                life = temp.life;

                if(ny>500 || ny<0 || nx>500 || nx<0)  continue;

                if(danger[ny][nx]<0) life++;

                // 갈 수 없는 경우 1. 죽음의 공간인 경우
                // 갈 수 없는 경우 2. 내가 가진 목숨보다 지나오면서 찍은 깎은 목숨이 더 작은 경우
                if(danger[ny][nx] == 501*501+2) continue;
                else if( visit[ny][nx] && Math.abs(danger[ny][nx])<=life) continue;

                // 방문체크
                if(danger[ny][nx]<0) danger[ny][nx]=-life;
                else danger[ny][nx]=life;
                visit[ny][nx]=true;

                if(ny==500 && nx==500) break total;
                queue.offer(new Node(ny,nx,life));
            }
        }

        if(!visit[500][500]) answer = -1;
        else answer = Math.abs(danger[500][500]);

        System.out.println(answer);
    }
    static class Node{
        int y, x, life;
        Node(int y, int x, int life){
            this.y=y;
            this.x=x;
            this.life=life;
        }
    }
}
