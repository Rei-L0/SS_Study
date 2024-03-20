import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * MST - 크루스칼
 * 우선순위: 성사된 것 ->  애정도가 높은 것
 * (총 애정도의 합 - 위 우선순위를 따라 고른 애정도의 합)이 답
 */

public class Main {

	static int N, M, sum, max;
	static ArrayList<Node> list = new ArrayList<>();
	static int[] parents;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			list.add(new Node(a, b, c, d));
			sum += c;
		}
		Collections.sort(list);
		makeSet();
		for(int i = 0; i < list.size(); i++) {
			if(union(list.get(i).a, list.get(i).b)){
				max += list.get(i).c;
			}
		}
		System.out.println(sum - max);
	}
	static void makeSet() {
		parents = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	static int find(int x) {
		if(parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}
	static boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if(px != py) {
			parents[px] = py;
			return true;
		}
		return false;
	}

	static class Node implements Comparable<Node>{
		int a, b, c, d;

		public Node(int a, int b, int c, int d) {
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
		}

		@Override
		public int compareTo(Node o) {
			return o.d == d ? o.c - c : o.d - d;
		}
		
	}
}
