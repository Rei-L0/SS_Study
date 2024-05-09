//queue로 접근시 1% 틀렸습니다. -> arrayDequeue로 접근해야하는걸 안후에 든 궁금증은 왜 시간초과가 아닌 틀렸습니다가 떳는가..?
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static int[][] map = new int [501][501];
	static int dx[] = {-1,1,0,0}; //상하좌우
	static int dy[] = {0,0,-1,1};
	static boolean[][] visit = new boolean[501][501];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) { //위험 : 1
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int maxX=Math.max(x1, x2);
			int minX=Math.min(x1, x2);
			int maxY=Math.max(y1, y2);
			int minY=Math.min(y1, y2);
			
			for(int j=minX; j<=maxX; j++) {
				for(int k=minY; k<=maxY; k++) {
					map[j][k] = 1;
				}
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int maxX=Math.max(x1, x2);
			int minX=Math.min(x1, x2);
			int maxY=Math.max(y1, y2);
			int minY=Math.min(y1, y2);
			
			for(int j=minX; j<=maxX; j++) {
				for(int k=minY; k<=maxY; k++) {
					visit[j][k]=true;
				}
			}
		}
		
		bfs();
	}
	
	static void bfs() {
		ArrayDeque<Point> queue = new ArrayDeque<>();
		boolean flag=false;
		
		queue.add(new Point(0, 0, 0));
		visit[0][0]=true;
		
		while(!queue.isEmpty()) {
			Point a = queue.poll();
			if(a.x==500 && a.y==500) {
				System.out.println(a.count);
				flag=true;
				break;
			}
			
			for(int i=0;i<4; i++) {
				int nx = a.x+dx[i];
				int ny = a.y+dy[i];
				
				if(nx<0 || ny<0 || nx>500 || ny>500 || visit[nx][ny]) continue;
				visit[nx][ny]=true;
				if(map[nx][ny]==1) {
					queue.addLast(new Point(nx, ny, a.count+1));
				}else {
					queue.addFirst(new Point(nx, ny, a.count));
				}
			}
			
		}
		if(!flag) System.out.println("-1");	
		
	}
	
	static class Point{
		int x;
		int y;
		int count;
		public Point(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count=count;
		}
	}
}
