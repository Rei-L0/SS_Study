import java.io.*;
import java.util.*;

public class prac { 
    static int R,C,K,cnt;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dx = new int[4];
    static int[] dy = new int[4];
    static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        graph = new int[R][C];
        visited = new boolean[R][C];

        K = Integer.parseInt(br.readLine());
        for (int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            graph[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = -1;
        }

        st = new StringTokenizer(br.readLine());
        int start_x = Integer.parseInt(st.nextToken());
        int start_y = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
		for (int i=0; i<4; i++) {
			int n = Integer.parseInt(st.nextToken());
			if (n==1) {
				dx[i] = -1;
				dy[i] =0;
			} else if (n == 2) {
				dx[i] = 1;
				dy[i] = 0;
			} else if (n == 3) {
				dx[i] = 0;
				dy[i] = -1;
			} else {
				dx[i] = 0;
				dy[i] = 1;
			}
		}

        cnt = 0;
        go(start_x,start_y,0);
        System.out.println(sb);

    }

    public static void go(int x, int y, int d) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(x,y,d));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cnt == 4) {
                sb.append(cur.x).append(" ").append(cur.y);
                return;
            }

            int nx = cur.x + dx[d];
            int ny = cur.y + dy[d];

            if (check(nx,ny) && !visited[nx][ny]) {
                q.offer(new Node(nx,ny,d));
                graph[nx][ny] = graph[cur.x][cur.y] + 1;
                visited[nx][ny] = true;
                cnt = 0;
            }
            else {
                d = (d+1)%4;
                cnt++;
                q.offer(new Node(cur.x,cur.y,d));
            }
        }
    }

    public static boolean check(int x, int y) {
        if (0 <= x && x < R && 0 <= y && y < C && graph[x][y] != -1) return true;
        else return false;
    }
}

class Node{
    int x,y,d;
    public Node(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}
