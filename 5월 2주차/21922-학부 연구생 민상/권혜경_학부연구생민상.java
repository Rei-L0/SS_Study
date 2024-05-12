import java.io.*;
import java.util.*;
public class Main {
    static int N, M, answer = 0;
    static int map[][];
    static boolean cool[][];

    static Queue<Node> air = new ArrayDeque<>();

    static int dy[] = {-1,0,1,0};
    static int dx[] = {0,1,0,-1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cool = new boolean[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) air.offer(new Node(i,j));
            }
        }

        while(!air.isEmpty()){
            Node temp = air.poll();
            int ny, nx, dir;
            cool[temp.y][temp.x] = true;
            for(int i=0;i<4;i++){
                ny = temp.y; nx = temp.x;
                dir = i;
                while(true){
                    ny += dy[dir];
                    nx += dx[dir];
                    if(ny>=N||ny<0||nx>=M||nx<0||map[ny][nx]==9||(map[ny][nx]<0 && ((-map[ny][nx])|1<<dir) == -map[ny][nx])) break;
                    cool[ny][nx] = true;
                    if(map[ny][nx]<0) continue;
                    else if(map[ny][nx]==0) map[ny][nx] = -(map[ny][nx]|1<<dir);
                    else if(map[ny][nx]==1 || map[ny][nx]==2) {
                        if(map[ny][nx]%2 != dir%2) continue;
                        dir+=2;
                        dir%=4;
                    }else if(map[ny][nx]==3){
                        dir = dir/2*2 + (dir+1)%2;

                    }else if(map[ny][nx]==4){
                        dir = 3-dir;
                    }
                }
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(cool[i][j]) answer++;
            }
        }
        System.out.println(answer);
    }
    static class Node{
        int y,x;
        Node(int y, int x){
            this.y=y;
            this.x=x;
        }
    }
}
