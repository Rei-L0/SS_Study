import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 방문 체크 중요! 같은 곳에 같은 방향으로 방문했어도 여러번 방문 가능
// visit[y][x][d][c]  
// => y, x : 좌표, d: 방향, c: 여태 제거한 먼지 개수 
public class Main {
	
	static int H, W, R, C, D, cycle, cnt = 0, ans = 0;
	static int[][] map, ruleA, ruleB;
	static boolean[][][][] visit;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		ruleA = new int[H][W];
		ruleB = new int[H][W];
		visit = new boolean[H][W][4][H*W+1];
		for(int i = 0; i < H; i++) {
			String str = br.readLine();
			for(int j = 0; j < W; j++) {
				ruleA[i][j] = str.charAt(j);
			}
		}
		for(int i = 0; i < H; i++) {
			String str = br.readLine();
			for(int j = 0; j < W; j++) {
				ruleB[i][j] = str.charAt(j);
			}
		}
		move(R, C, D);
		System.out.println(ans - cycle);
	}
	static void move(int y, int x, int d) {
		while(true) {
			if(map[y][x] == 0) {
				map[y][x] = 1;
				d = (d + ruleA[y][x]) % 4;
				cnt++;
				cycle = 0;
			}
			else {
				d = (d + ruleB[y][x]) % 4;
				if(visit[y][x][d][cnt]) return;
				visit[y][x][d][cnt] = true;
				cycle++;
			}
			y = y + dy[d];
			x = x + dx[d];
			ans++;
			if(y < 0 || x < 0 || y >= H || x >= W) return;
		}
	}
}
