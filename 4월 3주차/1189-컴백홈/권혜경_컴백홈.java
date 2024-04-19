// dfs 로 완탐

import java.util.*;
import java.io.*;
public class B_컴백홈_1189 {
    static int R, C, K, answer = 0;
    static char map[][];
    static boolean visit[][];

    static int dy[] = {-1,0,1,0};
    static int dx[] = {0,1,0,-1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[R][];
        visit = new boolean[R][C];

        for(int i=0;i<R;i++){
            map[i] = br.readLine().toCharArray();
        }
        
        visit[R-1][0] = true;
        dfs(R-1,0,1);

        System.out.println(answer);
    }
    static void dfs(int y, int x, int count){
        if(y==0 && x==C-1 ){
            if(count==K) answer++;
            return;
        }
        int ny, nx;
        for(int i=0;i<4;i++){
            ny = y+dy[i];
            nx = x+dx[i];
            
            if(ny>=R || ny<0 || nx>=C || nx<0 || map[ny][nx] == 'T' || visit[ny][nx]) continue;
            
            visit[ny][nx] = true;
            dfs(ny, nx, count+1);
            visit[ny][nx] = false;
        }
        
    }
}
