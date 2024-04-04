import java.util.*;
import java.io.*;

public class boj_16234 {
	
	static int N,L,R,res,cnt;
	static ArrayList<Point> arr;
	static int[][] graph;
	static boolean[][] visited;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		graph = new int[N][N];
		cnt = 0;
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while (true) {
			boolean flag = false;
			visited = new boolean[N][N];
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (!visited[i][j]) {
						bfs(i,j);
						if (arr.size() > 1) { // 이동할 수 있는 나라가 2 이상이어야만 이동 !! 1일 경우는 pass ~ 
							flag = true;
							
							int people = res / arr.size();
							
							for (Point country : arr) {
								graph[country.x][country.y] = people;
							}
						}
					}
				}
			}
			if (!flag) {
				System.out.println(cnt);
				break;
			}
			cnt++;
		}
	}
	
	static void bfs(int x, int y) { // 인구가 이동할 수 있는 나라들을 찾아주기 
		arr = new ArrayList<Point>();
		Queue<Point> q = new ArrayDeque<Point>();
		res = 0;
		visited[x][y] = true;
		q.offer(new Point(x,y));
		arr.add(new Point(x,y));
		res += graph[x][y];
		
		while (!q.isEmpty()) {
			Point cur = q.poll();
			
			for (int i=0; i<4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (!check(nx,ny)) continue;
				
				if (visited[nx][ny]) continue;
				
				int tmp = Math.abs(graph[cur.x][cur.y] - graph[nx][ny]);
				
				if (tmp < L || tmp > R) continue;
				
				arr.add(new Point(nx,ny));
				q.offer(new Point(nx,ny));
				visited[nx][ny] = true;
				res += graph[nx][ny];	
			}
		}
	}
	
	static boolean check(int x, int y) {
		if (0<= x && x < N && 0 <= y && y < N) return true;
		else return false;
	}
	
	static class Point{
		int x,y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
  
}
