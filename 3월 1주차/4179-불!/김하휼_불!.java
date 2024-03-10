import java.io.*;
import java.util.*;

// 61%에서 틀림. why ?? 고민해볼 것 ! 

public class prac { 
	static int R,C, start_x,start_y;
	static int[][] graph;
	static boolean[][] visited;
	static boolean[][] visited2;
    static StringBuilder sb = new StringBuilder();
	static ArrayList<int[]> fire = new ArrayList<>();

	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		graph = new int[R][C];
		visited = new boolean[R][C];
		visited2 = new boolean[R][C];

		for (int i=0; i<R; i++) {
			String s = br.readLine();
			for (int j=0; j<C; j++) {
				if (s.charAt(j) == '#') {
					graph[i][j] = -1;
				}
				else if (s.charAt(j) == '.') {
					graph[i][j] = 0;
				} 
				
				else if (s.charAt(j) == 'J') {
					start_x = i;
					start_y = j;
					graph[i][j] = 0;
				}
				else if (s.charAt(j)== 'F') {
					int[] f = new int[2];
					f[0] = i;
					f[1] = j;
					fire.add(f);
					graph[i][j] = 0;
				}
			}
		}


		fire();
		int ans = go();
		System.out.println(ans != -1 ? ans : "IMPOSSIBLE");
    }

	public static void fire() {
		Queue<Node> q = new ArrayDeque<>();
		for (int[] next: fire) {
			q.offer(new Node(next[0],next[1],0));
			visited[next[0]][next[1]] = true;
		}

		while (!q.isEmpty()) {
			Node cur = q.poll();

			for (int i=0; i<4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (check(nx,ny) && !visited[nx][ny] && graph[nx][ny] != -1) {
					graph[nx][ny] = cur.time + 1;
					q.offer(new Node(nx,ny,cur.time+1));
					visited[nx][ny] = true;
				}
			}
		}
	}

	public static int go() {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(start_x,start_y,0));
		visited2[start_x][start_y] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			if (!done(cur.x,cur.y)) {
				return cur.time+1;
			}

			for (int i=0; i<4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (check(nx, ny) && !visited2[nx][ny] && graph[nx][ny] != -1) {
					if (graph[nx][ny] > cur.time+1) {
						graph[nx][ny] = cur.time+1;
						visited2[nx][ny] = true;
						q.offer(new Node(nx,ny,cur.time+1));
					}
				}
			}
		}
		return -1;

	}

	public static boolean check(int x,int y) {
		if ( 0 <= x && x < R && 0 <= y && y < C) return true;
		else return false;
	}

	public static boolean done(int x, int y){
		if (x == 0 || y == 0 || x == R-1 || y == C-1) return false;
		else return true;
	}
}

class Node {
	int x,y,time;

	public Node(int x, int y,int time) {
		this.x = x;
		this.y = y;
		this.time = time;
	}
}
