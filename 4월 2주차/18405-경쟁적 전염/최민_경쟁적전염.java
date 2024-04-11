import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 입력 -> 리스트에 넣고 정렬 후 큐에 삽입 -> bfs
public class Main {
	static class location implements Comparable<location>{
		int x;
		int y;
		int kind;
		public location(int x, int y, int kind) {
			super();
			this.x = x;
			this.y = y;
			this.kind = kind;
		}
		@Override
		public String toString() {
			return "location [x=" + x + ", y=" + y + ", kind=" + kind + "]";
		}
		@Override
		public int compareTo(location o) {
			// TODO Auto-generated method stub
			return this.kind- o.kind;
		}
		
		
	}
	static int dx[] = {-1,1,0,0}; //상하좌우
	static int dy[] = {0,0,-1,1};
	static boolean visit[][];
	static int X,Y,S, N, K;
	static int list[][];
	static Queue<location> queue = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		list = new int[N][N];
		visit= new boolean[N][N];
		
		List<location> virus = new ArrayList<>();
		int count=0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				list[i][j] = Integer.parseInt(st.nextToken());
				if(list[i][j]!=0) {
					virus.add(new location(i, j, list[i][j]));
					visit[i][j] = true;
					count++;
				}
			}
		}
		
		Collections.sort(virus);

		for(int i=0; i<virus.size(); i++) {
			queue.add(new location(virus.get(i).x, virus.get(i).y, virus.get(i).kind));
		}
		
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken()); //S초 후
		X = Integer.parseInt(st.nextToken()); //좌표
		Y = Integer.parseInt(st.nextToken());

		bfs();
		
		System.out.println(list[X-1][Y-1]);
	}
	static void bfs() {
		while(!queue.isEmpty()) {
			S--;
			if(S<0) return;
			int size = queue.size();
			for(int j=0; j<size; j++) {
				location l = queue.poll();
				for(int i=0; i<4; i++) {
					int rx = l.x+dx[i];
					int ry = l.y+dy[i];
					
					if(rx<0 || ry <0 || rx >=N || ry>=N || visit[rx][ry]) continue;
					queue.add(new location(rx, ry, l.kind));
					visit[rx][ry] = true;
					list[rx][ry] = l.kind;
					
				}
			}

		}
	}
}
