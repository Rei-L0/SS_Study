package boj2174;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	static int A, B, N, M;
	static int[][] map;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static Node[] robot;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		map = new int[A+1][B+1];
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		robot = new Node[N+1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int d = -1;
			char tmp = st.nextToken().charAt(0);
			switch(tmp) {
			case 'N': d = 0; break;
			case 'E': d = 1; break;
			case 'S': d = 2; break;
			case 'W': d = 3; break;
			}
			robot[i] = new Node(y, x, d);
			map[y][x] = i;
		}
		boolean flag = true;
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			char o = st.nextToken().charAt(0);
			int c = Integer.parseInt(st.nextToken());
			for(int j = 0; j < c; j++) {
				if(sb.length() > 0) {
					flag = false;
					break;
				}
				switch(o) {
				case 'L': left(n, c); break;
				case 'R': right(n, c); break;
				case 'F': go(n, c); break;
				}
			}
		}
		if(flag) sb.append("OK");
		System.out.println(sb);
	}
	static void left(int n, int c) {
		int nc = c % 4;
		for(int i = 0; i < nc; i++) {
			robot[n].d--;
			if(robot[n].d == -1) robot[n].d = 3;
		}
	}
	static void right(int n, int c) {
		int nc = c % 4;
		for(int i = 0; i < nc; i++) {
			robot[n].d++;
			if(robot[n].d == 4) robot[n].d = 0;
		}
	}
	static void go(int n, int c) {
		int ny = robot[n].y;
		int nx = robot[n].x;
		int d = robot[n].d;
		for(int i = 0; i < c; i++) {
			ny = ny + dy[d];
			nx = nx + dx[d];
			if(ny < 0 || nx < 0 || ny >= N || nx >= M) {
				sb.append("Robot ").append(n).append(" crashes into the wall");
				return;
			}
			if(map[ny][nx] > 0) {
				sb.append("Robot ").append(n).append(" crashes into the ").append(map[ny][nx]);
				return;
			}
		}
		map[robot[n].y][robot[n].x] = 0;
		map[ny][nx] = n;
		robot[n].y = ny;
		robot[n].x = nx;
	}
	static class Node{
		int y, x, d;

		public Node(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}

}
