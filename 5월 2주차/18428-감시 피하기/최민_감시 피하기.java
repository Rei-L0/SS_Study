import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node{
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	static boolean flag= false;
	static char map[][];
	static char copy[][];
	static int[]dx= {0,0,-1,1};
	static int[]dy= {1,-1,0,0};
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]= st.nextToken().charAt(0);
			}
		}
		
		go(0);
		
		if(!flag) System.out.println("NO");
		else System.out.println("YES");
	}
	
	public static void go(int obstacle) { //장애물 배치
		if(obstacle==3) {
			if(bfs()) {
				flag=true;
				return;
			}
			return;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]=='X') {
					map[i][j]='O';
					go(obstacle+1);
					map[i][j]='X';
				}
			}
		}
	}
	
	public static boolean bfs() {
		Queue<Node> queue = new ArrayDeque<>();
		
		copy = new char[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				copy[i][j] = map[i][j];
				if(copy[i][j]=='T') {
					queue.add(new Node(i, j));
				}
			}
		}
		
		while(!queue.isEmpty()) {
			Node a = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nx = a.x;
				int ny = a.y;
				
				while(true) {
					nx+=dx[i];
					ny+=dy[i];
					if(nx<0 || ny<0 || nx>=N || ny>=N) break;
					if(copy[nx][ny] == 'O') break; //장애물 뒤의 학생은 볼수 없음
					if(copy[nx][ny]=='S') return false; //학생 만나면 false
				}
			}
		}
		return true;
	}
}
