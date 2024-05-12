import java.util.*;
import java.io.*;

class Main {
	static int N;
	static char[][] graph;
	static char[][] newMap;
	static ArrayList<Node> teachers;
	static ArrayList<Node> canInstall;
	static int[] selected;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static boolean flag = false;
	public static void main(String[] args) throws Exception {
	  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	  StringTokenizer st;

	  N = Integer.parseInt(br.readLine());

	  graph = new char[N][N];
	  teachers = new ArrayList<>();
	  canInstall = new ArrayList<>();
	  selected = new int[3];
	  newMap = new char[N][N];
	
	  for (int i=0; i<N; i++) {
		st = new StringTokenizer(br.readLine());
		for (int j=0; j<N; j++) {
			graph[i][j] = st.nextToken().charAt(0);
			if  (graph[i][j] == 'T') {
				teachers.add(new Node(i,j));
			}
			if (graph[i][j] == 'X') {
				canInstall.add(new Node(i,j));
			}
			newMap[i][j] = graph[i][j];
		}
	  }


	obstacle(0, 0);
	System.out.println(flag ? "YES" : "NO");
	}

	static void renew() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				graph[i][j] = newMap[i][j];
			}
		}
	}

	static void obstacle(int start, int cnt) { // 장애물 설치할 위치 조합으로 찾기
		if (cnt == 3) {
			buildObstcle();

			int tmp = solve(selected);
			if (tmp == 1) {
				flag = true;
				return;
			}
			renew();
			return;
		}

		for (int i=start; i<canInstall.size(); i++) {
			selected[cnt] = i;
			obstacle(i+1, cnt+1);
		}
	}

	static void buildObstcle() { // 장애물 만들 수 있는 곳에 장애물 설치하기
		for (int i=0; i<3; i++) {
			Node now = canInstall.get(selected[i]);
			graph[now.x][now.y] = 'O';
		}
	}

	static int solve(int[] ob) { // 감시 피할 수 있는지 확인 

		for (int t=0; t<teachers.size(); t++) {
			Node cur = teachers.get(t);

			for (int i = 0; i<4; i++) {
				int tmpx = cur.x;
				int tmpy = cur.y;
				while (true) {
					int nx = tmpx + dx[i];
					int ny = tmpy + dy[i];

					if (!check(nx, ny)) break;
					if (graph[nx][ny] == 'O') break;
					if (graph[nx][ny] == 'T') break;

					if (graph[nx][ny] == 'S') {
						return -1;
					}

					tmpx = nx;
					tmpy = ny;
				}

			}
		}
		return 1;
	}

	static boolean check(int x,int y) {
		if (0 <= x && x < N && 0 <= y && y < N) return true;
		else return false;
	}

	static class Node{
		int x,y;
		public Node(int x, int y) {
			this.x = x;
			this.y =y ;
		}
		
	}
}
