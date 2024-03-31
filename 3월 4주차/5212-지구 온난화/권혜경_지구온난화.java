import java.io.*;
import java.util.*;

public class B_지구온난화_5212 {
    static int R, C, fr, fc, lr, lc;
    static char map[][];
    static boolean visit[][];
    static Queue<Integer> queue = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();

    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][];
        visit = new boolean[R][C]; //땅이 true, 땅기준으로
        fr=R; fc=C; lr=0; lc=0;
        for(int i=0;i<R;i++){
            map[i] = br.readLine().toCharArray();
            for(int j=0;j<C;j++){
                if(map[i][j]=='X') {
                    queue.offer(i*C+j);
                    visit[i][j] = true;
                }
            }
        }
        check();
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(visit[i][j]){
                    fr = Math.min(fr, i);
                    lr = Math.max(i, lr);
                    fc = Math.min(fc, j);
                    lc = Math.max(j, lc);
                }
            }
        }
        for(int i=fr;i<=lr;i++){
            for(int j=fc;j<=lc;j++){
                if(visit[i][j]) sb.append("X");
                else sb.append(".");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
    static void check(){
        int size = queue.size();
        for(int j=0;j<size;j++){
            int temp = queue.poll();
            int y = temp/C, x = temp%C, count = 0;
            
            for(int i=0;i<4;i++){
                int ny = y+dy[i], nx = x+dx[i];
                if(ny<0 || ny>=R || nx<0 || nx>=C) count++;
                else if (map[ny][nx]=='X') continue;
                else count++;
            }
            if(count>=3) visit[y][x] = false;
        }
    }
}
