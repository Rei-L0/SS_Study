import java.io.*;
import java.util.*;

// ���������� 0�̰� ���������� 1�̹Ƿ� ����ġ ������ �����ؾ� �Ѵ�.
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

	// find �޼���
	static int find(int x) {
		if (x == p[x])
			return x;
		return p[x] = find(p[x]);
	}

	// union �޼���
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
		
		// XOR ��Ʈ �����ڸ� ���
		// 0 -> 1 , 1 -> 0 ���� �����Ѵ�.
		sw^=1;

		// � ���� 0-1�� ������ �Ѵ�.
		max = sw;
		min = sw;

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			// XOR ��Ʈ �����ڸ� ���
			// 0 -> 1 , 1 -> 0 ���� �����Ѵ�.
			w^=1;
			// ���� ����Ʈ�� ���� ���� ����
			edges.add(new Node(s, e, w));
		}

		// ���� ����Ʈ �������� ����
		edges.sort(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return Integer.compare(o1.w, o2.w);
			}
		});

		// p �迭 �ʱ�ȭ
		p = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			p[i] = i;
		}

		// ���ĵ� ���� ����Ʈ�� kruskal �˰��� ���
		// �������� �����̹Ƿ� �ּҰ��� �ȴ�.
		for (int i = 0; i < m; i++) {
			Node now = edges.get(i);
			if (union(now.e, now.s)) {
				min += now.w;
			}
		}

		// ���� ����Ʈ �������� ����
		edges.sort(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return Integer.compare(o2.w, o1.w);
			}
		});

		// p �迭 �ʱ�ȭ
		for (int i = 1; i < n + 1; i++) {
			p[i] = i;
		}

		// ���ĵ� ���� ����Ʈ�� kruskal �˰��� ���
		// �������� �����̹Ƿ� �ִ��� �ȴ�.
		for (int i = 0; i < m; i++) {
			Node now = edges.get(i);
			if (union(now.e, now.s)) {
				max += now.w;
			}
		}

		System.out.println((max * max) - (min * min));
	}
}