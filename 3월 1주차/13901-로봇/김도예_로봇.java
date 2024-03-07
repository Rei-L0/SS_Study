import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ay, ax: 로봇의 마지막 위치로 로봇 위치가 변할 때마다 갱신
// fc: 방향 전환 횟수, 같은 자리에서 4번 이상 돌면 더이상 갈 곳이 없는 것이므로 return
// 주어지는 방향이 상하좌우 순서대로 1, 2, 3, 4 이기 때문에 dy, dx의 0번째 값은 dummy로 줌
// cnt는 사실 필요 없음
public class Main {

	static int R, C, nd, cnt, ay, ax, fc;
	static int[][] map;
	static int[] dy = {0, -1, 1, 0, 0};
	static int[] dx = {0, 0, 0, -1, 1};
	static int[] d = new int[4];
	public static void main(String[] args) throws Exception{
    // 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		int n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			map[y][x] = -1;
		}

		st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		map[y][x] = 1;
		Node start = new Node(y, x);
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			d[i] = Integer.parseInt(st.nextToken());
		}

    // 풀이
		nd = d[0];
		cnt = 1;
		ay = start.y;
		ax = start.x;
		move(start, 0);
		System.out.println(ay + " " + ax);
	}
  
	static void move(Node start, int dIdx) {
			if(dIdx == 4) dIdx = 0;
			nd = d[dIdx];
			int ny = start.y + dy[nd];
			int nx = start.x + dx[nd];
			if(fc == 4) return;
      // 해당 방향으로 갈 수 없는 경우
			if(ny < 0 || nx < 0 || ny >= R || nx >= C || map[ny][nx] > 0 || map[ny][nx] == -1) {
				fc++;
				move(start, dIdx + 1);
			}
      // 갈 수 있는 경우
			else{
				fc = 0;
				map[ny][nx] = ++cnt;
				ay = ny;
				ax = nx;
				move(new Node(ny, nx), dIdx);
			}
	}
	static class Node{
		int y, x;
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
