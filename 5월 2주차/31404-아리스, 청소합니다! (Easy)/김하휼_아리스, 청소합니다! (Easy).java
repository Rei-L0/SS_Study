import java.io.*;
import java.util.*;

public class Main {
	static int N,M,aris_x,aris_y,direction,cnt, limit;
	static int[][] graph,ruleA,ruleB;
	static int[] dx = {-1,0,1,0}; 
	static int[] dy = {0,1,0,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new int[N][M];
		ruleA = new int[N][M];
		ruleB = new int[N][M];
		cnt = 0;
		limit = 0;
		
		st = new StringTokenizer(br.readLine());
		aris_x = Integer.parseInt(st.nextToken());
		aris_y = Integer.parseInt(st.nextToken());
		direction = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				graph[i][j] = 1;
			}
		}
		
		for (int i=0; i<N; i++) {
			String s = br.readLine();
			for (int j=0; j<M; j++) {
				ruleA[i][j] = s.charAt(j)-'0';
			}
		}
		
		for (int i=0; i<N; i++) {
			String s = br.readLine();
			for (int j=0; j<M; j++) {
				ruleB[i][j] = s.charAt(j)-'0';
			}
		}
		solve();

	}
	
	static void solve() {
		
		while (true) {
			
			if (cleanDust(aris_x,aris_y)) {
				direction = goRuleA(aris_x,aris_y,direction);
				limit = 0;
			}
			else {
				direction = goRuleB(aris_x,aris_y,direction);
				limit++;
			}
			
			aris_x = aris_x + dx[direction];
			aris_y = aris_y + dy[direction];
			
			cnt++;
			
			if (!check(aris_x,aris_y)) {
				System.out.println(cnt-limit);
				break;
			}
			
			if (limit >= 100000) {
				System.out.println(cnt-100000);
				break;
			}
					
		}
	}
	
	static boolean cleanDust(int x, int y) {
		if (graph[x][y] == 1) {
			graph[x][y] = 0;
			return true;
		}
		return false;
	}
	
	static int goRuleA(int x, int y,int d) {
		int tmp = (d+ruleA[x][y]) % 4;
		return tmp;	
	}
	
	static int goRuleB(int x, int y,int d) {
		int tmp = (d+ruleB[x][y]) % 4;
		return tmp;	
	}
	
	static boolean check(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < M) return true;
		else return false;
	}
}
