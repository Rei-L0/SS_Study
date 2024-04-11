import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n, m;

	static int[] diff;

	static PriorityQueue<Problem> maxQ, minQ;

	static class Problem implements Comparable<Problem> {
		int num;
		int difficult;

		public Problem(int num, int difficult) {
			super();
			this.num = num;
			this.difficult = difficult;
		}

		@Override
		public int compareTo(Problem o) {
			if (this.difficult == o.difficult) {
				return Integer.compare(this.num, o.num);
			}
			return Integer.compare(this.difficult, o.difficult);
		}
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());

		maxQ = new PriorityQueue<>(Collections.reverseOrder());
		minQ = new PriorityQueue<>();

		diff = new int[100_001];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			addQ();
		}

		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			String oper = st.nextToken();
			if (oper.equals("add")) {
				addQ();
			} else if (oper.equals("solved")) {
				int num = Integer.parseInt(st.nextToken());
				diff[num] = 0;
			} else {
				int num = Integer.parseInt(st.nextToken());
				if (num == -1) {
					sb.append(solve(minQ)).append("\n");
				} else {
					sb.append(solve(maxQ)).append("\n");
				}
			}
		}
		System.out.println(sb);
	}

	static void addQ() throws Exception {
		int num = Integer.parseInt(st.nextToken());
		int difficult = Integer.parseInt(st.nextToken());

		diff[num] = difficult;
		minQ.offer(new Problem(num, difficult));
		maxQ.offer(new Problem(num, difficult));
	}

	static int solve(PriorityQueue<Problem> pq) {
		while (diff[pq.peek().num] != pq.peek().difficult) {
			pq.poll();
		}
		return pq.peek().num;
	}
}