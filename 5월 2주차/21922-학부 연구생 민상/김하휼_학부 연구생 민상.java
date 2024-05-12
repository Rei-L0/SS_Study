import java.util.*;
import java.io.*;

class Main {
	static int N,M,cnt;
	static int[][] graph;
	static ArrayList<Node> airConditioner;
	static boolean[][] visited;
	static int[] dx = {-1,0,1,0}; 
	static int[] dy = {0,1,0,-1};
	public static void main(String[] args) throws Exception {
	  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	  StringTokenizer st;

	  st = new StringTokenizer(br.readLine());
	  N = Integer.parseInt(st.nextToken());
	  M = Integer.parseInt(st.nextToken());
	  cnt = 0;

	  graph = new int[N][M];
	  airConditioner = new ArrayList<>();
	  visited = new boolean[N][M];

	  for (int i=0; i<N; i++) {
		st = new StringTokenizer(br.readLine());
		for (int j=0; j<M; j++) {
			graph[i][j] = Integer.parseInt(st.nextToken());
			if (graph[i][j] == 9) {
				airConditioner.add(new Node(i,j));
				visited[i][j] = true;
				cnt++;
			}
		}
	  }
	  solve();
	  System.out.println(cnt);
	}

	static void solve() {
		for (int a=0; a < airConditioner.size(); a++) {
			Node cur = airConditioner.get(a);

			for (int i=0; i<4; i++) {
				int direction = i;
				int tmpx = cur.x;
				int tmpy = cur.y;
				
				while(true) {
					int nx = tmpx + dx[direction];
					int ny = tmpy + dy[direction];

					if (!check(nx, ny)) break;

					if (graph[nx][ny] == 0) {
						if (!visited[nx][ny]) {
							visited[nx][ny] = true;
							cnt++;
						}
					}
				
					else if (graph[nx][ny] == 9) break;
					else {
						if (direction == 0 || direction == 2) {
							if (graph[nx][ny] == 2) {
								if (!visited[nx][ny]) {
									visited[nx][ny] = true;
									cnt++;
								}
								break;
							}
							else if (graph[nx][ny] == 1) {
								if (!visited[nx][ny]) {
									cnt++;
								}
								visited[nx][ny] = true;
							}
							else {
								direction = changeDirection(direction, graph[nx][ny]);
								if (!visited[nx][ny]) {
									cnt++;
								}
								visited[nx][ny] = true;
							}
						}
						else if (direction == 1 || direction == 3) {
							if (graph[nx][ny] == 1) {
								if (!visited[nx][ny]) {
									visited[nx][ny] = true;
									cnt++;
								}
								break;
							}
							else if (graph[nx][ny] ==2 ){
								if (!visited[nx][ny]) {
									cnt++;
								}
								visited[nx][ny] = true;
							}
							else {
								direction = changeDirection(direction, graph[nx][ny]);
								if (!visited[nx][ny]) {
									cnt++;
								}
								visited[nx][ny] = true;
							}
						}					
					}
					tmpx = nx;
					tmpy = ny;
				}
			}
		}
	}

	static int changeDirection(int d, int nextVal) {
		int changeD = 0;
		if (d == 0) {
			if (nextVal == 3) {
				changeD = 1;
			}
			else if (nextVal == 4) {
				changeD = 3;
			}
		}
		else if (d == 1) {
			if (nextVal == 3) {
				changeD = 0;
			}
			else if (nextVal == 4) {
				changeD = 2;
			}
		}
		else if (d == 2) {
			if (nextVal == 3) {
				changeD = 3;
			}
			else if (nextVal == 4) {
				changeD = 1;
			}
		}
		else if (d == 3) {
			if (nextVal == 3) {
				changeD = 2;
			}
			else if (nextVal == 4) {
				changeD = 0;
			}
		}
		return changeD;
	}

	static boolean check(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < M) return true;
		else return false;
	}
	static class Node{
		int x,y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
