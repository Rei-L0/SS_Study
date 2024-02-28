import java.io.*;
import java.util.*;

/*
- Checkpoints

1. 처음엔 입력받은 그래프 그대로 복사해서 시간을 갱신해주는 방식으로 했는데, 
   Node 클래스에 시간을 체크하는 변수를 만들어서 체크하고 visited배열로 방문 체크만 하면 배열 복사 필요 X.

2. 그래프를 그릴 때 빈칸의 수를 체크하고, bfs 돌면서 해당 값이 빈칸이면 그 갯수를 1씩 줄여 나감.
   그러다가 빈칸의 개수가 0이 되면 결과 값 갱신. 0이 아니더라도 bfs 다 돌면 함수는 종료. ans에는 초기값이 남아있겠죠
     
3. 체감 난이도: 꽤 난해했습니다. 일단 문제 이해 잘 안되고, 구현할 때 코드가 너무 더러워져서 디버깅이 힘들었음 !! 
 */

public class prac {
	static int N,M,ans,emptySpace;
	static int[][] graph;
	static boolean[][] visited;
	static Node[] active;
	static ArrayList<Node> virus;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new int[N][N];
		active = new Node[M];
		virus = new ArrayList<Node>();
		ans = Integer.MAX_VALUE;

		for (int i =0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if (graph[i][j] == 2) { 
					virus.add(new Node(i,j,0));
				}
				if (graph[i][j] == 0) {
					emptySpace++;
				}
			}
		}
		
		if (emptySpace == 0) {
			System.out.println(0);
		}
		else {
			solve(0,0);
			System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
		}
	}
	
	
	public static void solve(int cnt, int start) { // 조합 

		if (cnt == M) { // 기저조건					
			BFS(emptySpace);
			return;
		}
		
		for (int i=start; i<virus.size(); i++) { // 유도조건
			active[cnt] = virus.get(i);
			solve(cnt+1,i+1);
		}
	}
	
	public static void BFS(int space) {
		Queue<Node> q = new ArrayDeque<Node>();
		visited = new boolean[N][N];
		
		for (int i=0; i<M; i++) {
			q.offer(new Node(active[i].x,active[i].y,0));
			visited[active[i].x][active[i].y] = true;
		}
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			for (int i=0; i<4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (check(nx,ny) && !visited[nx][ny] && graph[nx][ny] != 1) {
					if (graph[nx][ny] == 0) {
						space--;
					}
					if (space == 0) {
						ans = Math.min(ans,cur.time+1);
						return;
					}
					visited[nx][ny] = true;
					q.offer(new Node(nx,ny,cur.time+1));
				}
			}
		}
	}
	
	public static boolean check(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < N) return true;
		else return false;
	}
}

class Node{ 
	int x;
	int y;
	int time;
	public Node(int x, int y, int time) {
		this.x = x;
		this.y = y;
		this.time = time;
	}
}

