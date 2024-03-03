// 제출 X
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static int R, C, M, sum;
	static Shark[][] map;
	static int[] dy = {0, -1, 1, 0, 0}; // 상하우좌
	static int[] dx = {0, 0, 0, 1, -1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Shark[R+1][C+1];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			map[r][c] = new Shark(r, c, s, d, z);
		}
		for(int i = 1; i <= C; i++) { // i가 낚시왕의 위치
			fishing(i);
			move();
		}
		System.out.println(sum);
	}
	// 상어 이동 함수 
	static void move() {
		Deque<Shark> q = new ArrayDeque<>();
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				if(map[i][j] != null) q.offer(new Shark(i, j, map[i][j].s, map[i][j].d, map[i][j].z));
			}
		}
		map = new Shark[R+1][C+1];

		while(!q.isEmpty()) {
			Shark s = q.poll();
			int speed = s.s;
			if(s.d == 1 || s.d == 2) //상 하
				speed %= (R-1) * 2; 
			else if(s.d == 3 || s.d == 4) //좌 우
				speed %= (C-1) * 2;

			for(int sp = 0; sp < speed; sp++) {
				// 현재 r, c에 방향에 맞게 1칸씩 추가하며 위치 이동 
				int newR = s.r + dy[s.d]; 
				int newC = s.c + dx[s.d];

				// 이동할 새로운 위치가 범위를 벗어나 벽에 부딪히면 
				if(newR <= 0 || newR > R || newC <= 0 || newC > C) { 
					s.r -= dy[s.d]; // 다시 값 돌려주고 
					s.c -= dx[s.d];
					// 방향 바꾸기
					if(s.d == 1 || s.d == 3) s.d += 1;
					else if(s.d == 2 || s.d == 4) s.d -= 1;
					continue;
				}

				// 위치 벗어나지 않을때는 새로운 위치로 이동 
				s.r = newR; 
				s.c = newC;
			}
			if(map[s.r][s.c] != null) {
				if(map[s.r][s.c].z < s.z) map[s.r][s.c] = new Shark(s.r, s.c, s.s, s.d, s.z); 
			}
			else map[s.r][s.c] = new Shark(s.r, s.c, s.s, s.d, s.z); 
		}
	}
	static void fishing(int x) {
		for(int y = 1; y <= R; y++) {
			if(map[y][x] != null) {
				sum += map[y][x].z;
				map[y][x] = null;
				break;
			}
		}
	}
	static class Shark{
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
}
