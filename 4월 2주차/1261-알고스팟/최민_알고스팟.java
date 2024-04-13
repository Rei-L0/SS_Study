import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int M, N;
	static int[][] maze;
	static int[][] arr;
	static int[] dx = {-1,1,0,0}; //상하좌우
	static int[] dy = {0,0,-1,1};
	
	static class Point implements Comparable<Point>{
		int x;
		int y;
		int broken;
		public Point(int x, int y, int broken) {
			super();
			this.x = x;
			this.y = y;
			this.broken = broken;
		}
		@Override
		public int compareTo(Point o) {
			return this.broken-o.broken;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		maze = new int[N][M];
		arr = new int[N][M];
		
		for (int i=0; i<N; i++) {
			String[] a = br.readLine().split("");
			for(int j=0; j<M; j++) {
				maze[i][j] = Integer.parseInt(a[j]);
			}
		}
		for (int i = 0; i < N; i++) {
			Arrays.fill(arr[i], Integer.MAX_VALUE);
		}
        arr[0][0]=0;
		dijkstra();
		System.out.println(arr[N-1][M-1]);
		
	}
	public static void dijkstra() {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(0, 0, 0)); // 0,0에서 부터 시작
		while(!pq.isEmpty()) {
			Point p = pq.poll();
			
			for(int i=0; i<4; i++) {
				int x = p.x + dx[i];
				int y = p.y + dy[i];
				int broken = p.broken;
				
				if(x<0 || y<0 || x>=N || y>=M ) continue;
				if(maze[x][y] == 1) broken++;
				if(arr[x][y] > broken) {
					arr[x][y] = broken;
					pq.add(new Point(x,y,broken));
				}
			}
		}
	}
}
