import java.io.*;
import java.util.*;

class Main {
	static int R, C, start_x, start_y, jong_x, jong_y;
	static char[][] graph;
	static int[][] countCheck;
	static ArrayList<Node> arr;
	static int[] dx = { 0, 1, 1, 1, 0, 0, 0, -1, -1, -1 };
	static int[] dy = { 0, -1, 0, 1, -1, 0, 1, -1, 0, 1 };
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		graph = new char[R][C];
		arr = new ArrayList<>();

		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				graph[i][j] = s.charAt(j);
				if (graph[i][j] == 'R') {
					arr.add(new Node(i, j));
				}
				if (graph[i][j] == 'I') {
					start_x = i;
					start_y = j;
				}
			}
		}
		boolean flag = true;
		int cnt = 0;
		String s = br.readLine();
		for (int i = 0; i < s.length(); i++) {
			int cmd = s.charAt(i) - '0';
			if (!solve(cmd)) {
				flag = false;
				cnt = i + 1;
				break;
			}
		}
		if (!flag) {
			System.out.println("kraj" + " " + cnt);
		} else {
			for (char[] x : graph) {
				for (char val : x) {
					sb.append(val);
				}
				sb.append("\n");
			}
			System.out.println(sb);
		}

	}

	static boolean solve(int d) {
		jong_x = start_x + dx[d];
		jong_y = start_y + dy[d];

		if (graph[jong_x][jong_y] == 'R') {
			return false;
		}

		graph[start_x][start_y] = '.';
		graph[jong_x][jong_y] = 'I';

		if (!robotMove(jong_x, jong_y))
			return false;

		start_x = jong_x;
		start_y = jong_y;
		return true;

	}

	static boolean robotMove(int x, int y) {
		countCheck = new int[R][C];
		ArrayList<Node> toWhere = new ArrayList<>();
		for (int i = 0; i < arr.size(); i++) {
			int tmp_x = 0;
			int tmp_y = 0;
			int min_v = Integer.MAX_VALUE;
			for (int j = 1; j < 10; j++) {
				int nx = arr.get(i).x + dx[j];
				int ny = arr.get(i).y + dy[j];

				int dis = Math.abs(x - nx) + Math.abs(y - ny);

				if (dis < min_v) {
					min_v = dis;
					tmp_x = nx;
					tmp_y = ny;
				}
			}
			graph[arr.get(i).x][arr.get(i).y] = '.';
			toWhere.add(new Node(tmp_x, tmp_y));
			countCheck[tmp_x][tmp_y] += 1;
		}
    
		arr.clear();

		for (int i = 0; i < toWhere.size(); i++) {
			if (graph[toWhere.get(i).x][toWhere.get(i).y] == 'I')
				return false;

			if (countCheck[toWhere.get(i).x][toWhere.get(i).y] > 1) {
				continue;
			} else {
				graph[toWhere.get(i).x][toWhere.get(i).y] = 'R';
				arr.add(new Node(toWhere.get(i).x, toWhere.get(i).y));
			}
		}
		return true;

	}

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
