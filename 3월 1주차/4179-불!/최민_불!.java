import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	//미로의 가장자리에 접한 공간에서 탈주 가능
	//불은 상하좌우로 퍼진다.
	static boolean[][] visit;
	static char[][] maze;
	static int r, c;
	static Queue<int[]> fire = new ArrayDeque<>();
	static Queue<int[]> jihun = new ArrayDeque<>();
	static int dx[] = {-1,1,0,0}; //상하좌우
	static int dy[] = {0,0,-1,1};
	static int J[] = new int[2];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		visit = new boolean[r][c];
		maze = new char[r][c];
		
		for (int i =0; i<r; i++) {
			String a = br.readLine();
			for(int j=0; j<c; j++) {
				maze[i][j]= a.charAt(j);
				if(maze[i][j] == 'F') fire.add(new int[] {i,j});
				else if(maze[i][j]=='J') jihun.add(new int[] {i,j});
			}
		}
		
		bfs();
	}
	static void bfs() {
		int time=0;
		while(!jihun.isEmpty()) {
			time++;
			
			//불 확산
			int fSize = fire.size();
			for(int i=0; i<fSize; i++) {
				int[] fi = fire.poll();
				for(int j=0; j<4; j++) {
					int nx=fi[0] +dx[j];
					int ny=fi[1] +dy[j];
					if (nx >= 0 && nx < r && ny >= 0 && ny < c && (maze[nx][ny] == '.' || maze[nx][ny] == 'J')) {
						//이동 범위 내라면 불 확산
						fire.add(new int[] {nx,ny});
						maze[nx][ny] = 'F';
					}
				}
			}
			
			//지훈 이동
			int jSize= jihun.size();
			for(int i=0; i<jSize; i++) {
				int[] ji = jihun.poll();
				for(int j=0; j<4; j++) {
					int nx=ji[0] +dx[j];
					int ny=ji[1] +dy[j];
					if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
						//가장 자리에 닿으면 끝
						System.out.println(time);
						return;
					}
					else if(!visit[nx][ny] && maze[nx][ny] == '.') {
						visit[nx][ny]=true;
						jihun.add(new int[] {nx,ny});
					}
				}
			}
			
			
		}
		System.out.println("IMPOSSIBLE");
		return;
	}
}
