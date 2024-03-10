import java.io.*;
import java.util.*;

public class B_로봇_13901 {
    // 문제가 모호함
    // 방문한 곳을 갈 수 없는건지, 방향을 4번 돌았을때 끝나는건지 근데 내가 문제 똑바로 안읽은,,,
    static Node start;
    static int R,C,K;
    static int map[][];
    static int move[] = new int[4];

    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); 
        C = Integer.parseInt(st.nextToken()); 
        K = Integer.parseInt(br.readLine());
        map = new int[R][C];

        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = -1;
        }

        st = new StringTokenizer(br.readLine());
        start = new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<4;i++){
            move[i] = Integer.parseInt(st.nextToken())-1;
        }

        go();
        System.out.println(start.y +" "+start.x);
    }
    static void go(){
        int ny, nx, dire = 0, count = 0;
        while(true){
            map[start.y][start.x] = 1;
            ny = start.y+dy[dire];
            nx = start.x+dx[dire];
            if(ny<0 || nx<0 || ny>=R || nx>=C || map[ny][nx]!=0) {
                count++;
                dire++;
                dire=dire%4;
                if(count==4) return;
                continue;
            }
            count=0;
            start.y=ny;
            start.x=nx;
        }
    }
    static class Node {
        int y, x;

        Node(int y, int x){
            this.y=y;
            this.x=x;
        }
    }
}
