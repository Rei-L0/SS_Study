import java.io.*;
import java.util.*;

// 오르막길이 0이고 내리막길이 1이므로 가중치 설정에 주의해야 한다.
public class Main {

	static int n, m, k, ans, max, min;

	static int[] p;

	static ArrayList<Node> edges;

	static class Node {
		int s;
		int e;
		int w;

		public Node(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

	}

	// find 메서드
	static int find(int x) {
		if (x == p[x])
			return x;
		return p[x] = find(p[x]);
	}

	// union 메서드
	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y)
			return false;
		p[x] = p[y];
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		edges = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		int ss = Integer.parseInt(st.nextToken());
		int se = Integer.parseInt(st.nextToken());
		int sw = Integer.parseInt(st.nextToken());
		
		// XOR 비트 연산자를 사용
		// 0 -> 1 , 1 -> 0 으로 변경한다.
		sw^=1;

		// 어떤 경우든 0-1은 지나야 한다.
		max = sw;
		min = sw;

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			// XOR 비트 연산자를 사용
			// 0 -> 1 , 1 -> 0 으로 변경한다.
			w^=1;
			// 간선 리스트에 간선 정보 저장
			edges.add(new Node(s, e, w));
		}

		// 간선 리스트 오름차순 정렬
		edges.sort(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return Integer.compare(o1.w, o2.w);
			}
		});

		// p 배열 초기화
		p = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			p[i] = i;
		}

		// 정렬된 간선 리스트로 kruskal 알고리즘 사용
		// 오름차순 정렬이므로 최소값이 된다.
		for (int i = 0; i < m; i++) {
			Node now = edges.get(i);
			if (union(now.e, now.s)) {
				min += now.w;
			}
		}

		// 간선 리스트 내림차순 정렬
		edges.sort(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return Integer.compare(o2.w, o1.w);
			}
		});

		// p 배열 초기화
		for (int i = 1; i < n + 1; i++) {
			p[i] = i;
		}

		// 정렬된 간선 리스트로 kruskal 알고리즘 사용
		// 내림차순 정렬이므로 최댓값이 된다.
		for (int i = 0; i < m; i++) {
			Node now = edges.get(i);
			if (union(now.e, now.s)) {
				max += now.w;
			}
		}

		System.out.println((max * max) - (min * min));
	}
}