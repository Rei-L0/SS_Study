import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


// MST - kruskal
// 정점의 개수 = R*C
public class Main {

	static int R, C;
	static int[] parent;
	static ArrayList<Node> list;
	static StringBuilder sb = new StringBuilder();
 	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			for(int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1; j < C; j++) {
					int now = Integer.parseInt(st.nextToken());
					list.add(new Node(C*i+j, C*i + j + 1, now));
				}
			}
			for(int i = 0; i < R-1; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1; j <= C; j++) {
					int now = Integer.parseInt(st.nextToken());
					list.add(new Node(C*(i+1) + j,C*i + j, now));
				}
			}
			Collections.sort(list);
			makeSet();
			int ans = 0, cnt = 0;
			for(int i = 0; i < list.size(); i++) {
				if(cnt == R*C) break;
				if(union(list.get(i).v1, list.get(i).v2)) {
					ans += list.get(i).c;
					cnt++;
				}
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

 	static void makeSet() {
 		parent = new int[R*C+1];
 		for(int i = 1; i <= R*C; i++) {
 			parent[i] = i;
 		}
 	}
 	static boolean union(int v1, int v2) {
 		int p1 = find(v1);
 		int p2 = find(v2);
 		
 		if(p1 != p2) {
 			parent[p2] = p1;
 			return true;
 		}
 		return false;
 	}
 	static int find(int x) {
 		if(parent[x] == x) return x;
 		return parent[x] = find(parent[x]);
 	}
 	
 	static class Node implements Comparable<Node>{
 		int v1, v2, c;

		public Node(int v1, int v2, int c) {
			this.v1 = v1;
			this.v2 = v2;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			return c - o.c;
		}
 		
 	}
}
