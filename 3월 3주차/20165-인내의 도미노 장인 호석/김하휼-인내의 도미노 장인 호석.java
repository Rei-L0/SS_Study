import java.io.*;
import java.util.*;


// 내가 한 구현이 틀린줄 알고 방식 계속 바꿔서 했었음. 첨엔 while true로 무한반복 돌리면서 break 조건으로 구현했고 두번째는 자료구조 썼음. 
// 아 진짜 동서남북 방향 설정 잘못해줘서 계속 2개 맞고 계속 틀렸음. 문제 그대로 인내심이 필요했던 거 같음. 
// 방식은 큐 만들어서 연쇄적으로 넘길 수 있는 값들을 계산해줬음 

class Main { 
	static int N,M,R;
	static int[][] arr;
	static long score;
	static boolean[][] ans;
	static int[] dx = {0,0,1,-1}; // 동서남북 순 
	static int[] dy = {1,-1,0,0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		ans = new boolean[N][M];
		score = 0;
		for (boolean a[]:ans) {
			Arrays.fill(a,true);
		}

		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i=0; i<R*2; i++) {
			if (i% 2 ==0) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				String direction = st.nextToken();
				attack(x-1,y-1,direction);
			}
			else {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				defense(x-1,y-1);
			}

		}

		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (ans[i][j] == true){
					sb.append("S").append(" ");
				}
				else {
					sb.append("F").append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(score);
		System.out.println(sb);
	}

	public static void attack(int x, int y, String direction) {
		if (!ans[x][y]) return;
		
		int d = 0;
    // 이부분도 메서드로 빼도 됨. 
		if (direction.equals("E")) {
			d = 0;
		} else if (direction.equals("W")){
			d = 1;
		} else if (direction.equals("S")) {
			d = 2;
		} else if (direction.equals("N")){
			d = 3;
		}
		
		int xx = x;
		int yy = y;
		int cnt = 1;
		ans[xx][yy] = false;

		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(xx,yy,arr[xx][yy]));

		while (!q.isEmpty()) {
			Node cur = q.poll();
			for (int i=0; i<cur.val-1;i++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				if (check(nx,ny)) {
					if (ans[nx][ny] == true) {
						ans[nx][ny] = false;
						q.offer(new Node(nx,ny,arr[nx][ny]));
						cnt++;
					}
				}
				cur.x = cur.x + dx[d];
				cur.y = cur.y + dy[d];
			}
		}
		score += cnt;
		return;
	}

	public static void defense(int x, int y) {
		ans[x][y] = true;
	}
	public static boolean check(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < M) return true;
		else return false;
	}
}
class Node{
	int x,y,val;
	public Node(int x, int y, int val) {
		this.x = x;
		this.y = y;
		this.val = val;
	}
}
