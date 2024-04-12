import java.io.*;
import java.util.*;
/*
 사전순 정렬을 생각 못해서 틀렸다.
 스트링 사전 순서에 대해 학습 해야겠습니다.
 */
public class Main {

	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n, mp, mf, ms, mv;

	static PriorityQueue<Res> pq = new PriorityQueue<>();

	static int[][] foods;
	static List<Integer> ansList;

	static class Res implements Comparable<Res> {
		int val;
		String s;

		public Res(int val, String s) {
			super();
			this.val = val;
			this.s = s;
		}

		@Override
		public int compareTo(Res o) {
			if (this.val == o.val) {
				return this.s.compareTo(o.s);
			}
			return Integer.compare(this.val, o.val);
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());

		foods = new int[n + 1][5];

		st = new StringTokenizer(br.readLine());
		mp = Integer.parseInt(st.nextToken());
		mf = Integer.parseInt(st.nextToken());
		ms = Integer.parseInt(st.nextToken());
		mv = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int f = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			foods[i + 1][0] = p;
			foods[i + 1][1] = f;
			foods[i + 1][2] = s;
			foods[i + 1][3] = v;
			foods[i + 1][4] = c;
		}

		for (int i = 1; i < n + 1; i++) {
			solve(1, 0, i, new int[i]);
		}

		if (pq.size() == 0) {
			System.out.println(-1);
		} else {
			Res ans = pq.poll();
			System.out.println(ans.val);
			System.out.println(ans.s);
		}
	}

	static void solve(int start, int cnt, int endCnt, int[] idx) {
		if (cnt == endCnt) {
			int cost = 0;
			int check[] = new int[4];
			for (int i = 0; i < endCnt; i++) {
				for (int j = 0; j < 4; j++) {
					check[j] += foods[idx[i]][j];
				}
				cost += foods[idx[i]][4];
			}
			if (!avail(check))
				return;
			ansList = new ArrayList<>();
			sb = new StringBuilder();
			for (int i = 0; i < endCnt; i++) {
				sb.append(idx[i]).append(" ");
			}
			pq.add(new Res(cost, sb.toString()));
			return;
		}
		for (int i = start; i < n + 1; i++) {
			idx[cnt] = i;
			solve(i + 1, cnt + 1, endCnt, idx);
		}
	}

	static boolean avail(int[] arr) {
		return arr[0] >= mp && arr[1] >= mf && arr[2] >= ms && arr[3] >= mv;
	}
}