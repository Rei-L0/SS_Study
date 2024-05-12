import java.util.*;
import java.io.*;

class Main {
	static int A,B,N,M;
	static int[][] graph;
	static boolean flag = true;
	static ArrayList<Robot> arr;
	static int[] dx = {0,-1,0,1};
	static int[] dy = {1,0,-1,0};
	public static void main(String[] args) throws Exception {
	  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	  StringTokenizer st;
	  StringBuilder sb = new StringBuilder();

	  st = new StringTokenizer(br.readLine());
	  A = Integer.parseInt(st.nextToken());
	  B = Integer.parseInt(st.nextToken());

	  graph = new int[B][A];
	  arr = new ArrayList<>();
	  
	  st  = new StringTokenizer(br.readLine());
	  N = Integer.parseInt(st.nextToken());
	  M = Integer.parseInt(st.nextToken());
	  int cnt = 1;
	  
	  for (int i=0; i<N; i++) {
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		String direction = st.nextToken();
		arr.add(new Robot(B-y, x-1, direction));
		graph[B-y][x-1] = cnt;
		cnt++;
	  }

	  for (int i=0; i<M; i++) {
		st = new StringTokenizer(br.readLine());
		int robotNum = Integer.parseInt(st.nextToken());
		String cmd = st.nextToken();
		int repeat = Integer.parseInt(st.nextToken());

		solve(robotNum,cmd,repeat);
		if (!flag) break;
		
	  }
	  if (flag) {
		System.out.println("OK");
	  }
	}

	static void solve(int n, String r, int c) {
		Robot cur = arr.get(n-1);
		for (int i=0; i<c; i++) {
			if (r.equals("F")) {
				int nx = 0;
				int ny = 0;
				if (cur.d.equals("N")){
					nx = cur.x + dx[1];
					ny = cur.y + dy[1];
				}
				else if (cur.d.equals("W")) {
					nx = cur.x + dx[2];
					ny = cur.y + dy[2];
				}
				else if (cur.d.equals("S")) {
					nx = cur.x + dx[3];
					ny = cur.y + dy[3];
				}
				else if (cur.d.equals("E")) {
					nx = cur.x + dx[0];
					ny = cur.y + dy[0];
				}

				if (!check(nx,ny)) {
					System.out.println(("Robot "+ n +" crashes into the wall"));
					flag = false;
					break;
				}
				if (graph[nx][ny] != 0) {
					System.out.println("Robot " + n + " crashes into robot " + graph[nx][ny]);
					flag = false;
					break;
				}

				graph[cur.x][cur.y] = 0;
				graph[nx][ny] = n;
				cur.x = nx;
				cur.y = ny;

			}
			else {
				String changeD = changeDirection(r, cur.d);
				cur.d = changeD;
			}
		}
	}

	static boolean check(int x, int y) {
		if (0 <= x && x < B && 0 <= y && y < A) return true;
		else return false;
	}

	static String changeDirection(String cmd, String curDirection) {
		String  d = "";
		if (cmd.equals("L")) {
			if (curDirection.equals("N")) {
				d = "W";
			}
			else if (curDirection.equals("W")) {
				d = "S";
			}
			else if (curDirection.equals("S")) {
				d = "E";
			}
			else if (curDirection.equals("E")) {
				d = "N";
			}
		}
		
		else if (cmd.equals("R")) {
			if (curDirection.equals("N")) {
				d = "E";
			}
			else if (curDirection.equals("W")) {
				d = "N";
			}
			else if (curDirection.equals("S")) {
				d = "W";
			}
			else if (curDirection.equals("E")) {
				d = "S";
			}
		}
		return d;
	}

	static class Robot{
		int x,y;
		String d;
		public Robot(int x, int y, String d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}	
	}
}
