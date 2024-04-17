import java.io.*;
import java.util.*;
/*
1. 갈 수 있는 방향이 적은 놈이 더 멀리 갈 수 있을 것이다
2. 갈 수 있는 방향이 같다면 숫자가 더 작을수록 멀리 갈 수 있을 것이다 

라고 생각하고 코드를 짰는데, 생각보다 queue의 in, out 이 시간이 많이 걸려서 속상,,

글고 dp 첨에는 생각못했는데, 좀 더 유연한 사고가 필요한듯
*/
public class B_욕심쟁이판다_1937 {
    static int N, answer = 0;
    static int map[][], dp[][];
    static int dy[] = {-1,0,1,0};
    static int dx[] = {0,1,0,-1};
    static PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->o1.c==o2.c ? o1.v-o2.v : o1.c-o2.c);
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        dp = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                pq.offer(new Node(i, j, check(i, j), map[i][j]));
            }
        }

        while(!pq.isEmpty()){
            Node temp = pq.poll();
            if(dp[temp.y][temp.x]!=0) continue;
            answer = Math.max(dfs(temp.y, temp.x), answer);
        }

        // for(int i=0;i<N;i++){
        //     for(int j=0;j<N;j++){
        //         System.out.print(dp[i][j]+" ");
        //     }
        //     System.out.println("");
        // }
        System.out.println(answer);
    }
    static class Node{
        int y, x, c, v;
        Node(int y, int x, int c, int v){
            this.y=y;
            this.x=x;
            this.c=c;
            this.v=v;
        }
    }
    static int dfs(int y, int x){
        int ny, nx;

        if(dp[y][x]!=0) return dp[y][x];
        dp[y][x]=1;

        for(int i=0;i<4;i++){
            ny = y+dy[i];
            nx = x+dx[i];
            if(ny>=N||ny<0||nx>=N||nx<0||map[y][x]>=map[ny][nx]) continue;
            dp[y][x] = Math.max(dfs(ny, nx)+1, dp[y][x]);
        }
        return dp[y][x];
    }
    static int check(int y, int x){
        int ny, nx, count = 0;
        for(int i=0;i<4;i++){
            ny = y+dy[i];
            nx = x+dx[i];
            if(ny>=N||ny<0||nx>=N||nx<0) continue;
            if(map[ny][nx]<=map[y][x]) count++;
        }
        return count;
    }
}
