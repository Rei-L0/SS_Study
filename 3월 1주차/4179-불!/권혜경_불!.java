import java.io.*;
import java.util.*;

public class B_불_4179 {
    static int R,C,answer = 0;
    static char map[][];
    static boolean visit[][];
    static Node start;

    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};
    static Queue<Node> fire = new ArrayDeque<>();
    static Queue<Node> go = new ArrayDeque<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][];
        visit = new boolean[R][C];

        for(int i=0;i<R;i++){
            map[i] = br.readLine().toCharArray();
            for(int j=0;j<C;j++){
                if(map[i][j]=='J') start = new Node(i, j);
                else if(map[i][j]=='F') fire.offer(new Node(i, j));
            }
        }
        bfs();
        if(answer == -1) System.out.print("IMPOSSIBLE");
        else System.out.print(answer);
    }
    static void bfs(){
        go.offer(start);
        visit[start.y][start.x] = true;
        int size, ny, nx;
        while(!go.isEmpty()){
            answer++;
            size = fire.size();
            for(int i=0;i<size;i++){
                Node temp = fire.poll();
                for(int j=0;j<4;j++){
                    ny = temp.y+dy[j];
                    nx = temp.x+dx[j];
                    if(ny>=R || ny<0 || nx>=C || nx<0) continue;
                    if(map[ny][nx]!='.') continue;
                    visit[ny][nx] = true;
                    map[ny][nx] ='F';
                    fire.offer(new Node(ny, nx));
                }

            }
            
            size = go.size();
            for(int i=0;i<size;i++){
                Node temp = go.poll();
                for(int j=0;j<4;j++){
                    ny = temp.y+dy[j];
                    nx = temp.x+dx[j];
                    if(ny>=R || ny<0 || nx>=C || nx<0) return;
                    if(visit[ny][nx] || map[ny][nx]!='.') continue;
                    visit[ny][nx] = true;
                    go.offer(new Node(ny, nx));
                }

            }
        }
        answer = -1;

    }
    static class Node{
        int y,x;
        Node(int y, int x){
            this.y=y;
            this.x=x;
        }
    }
}
