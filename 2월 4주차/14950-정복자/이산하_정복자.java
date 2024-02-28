import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
	
	다익스트라 전형적인 문제라고 판단.
	템플릿 외워서 풀어봄 !
	
 */

public class B_14950 {

	static int N, M, t;
	static List<List<CITY>> cityList = new ArrayList<>();
	static boolean[] visited;
	static int answer = 0;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 도시의 개수
		M = Integer.parseInt(st.nextToken()); // 도로의 개수
		t = Integer.parseInt(st.nextToken()); // 한번 정복할 때마다 증가하는 도로의 비용

		visited = new boolean[N + 1]; // 0 dummy
		// 초기화
		for (int i = 0; i < N + 1; i++) {
			cityList.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int c1 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			cityList.get(c1).add(new CITY(c2, cost));
			cityList.get(c2).add(new CITY(c1, cost));
		}

		dijkstra();
		System.out.print(answer);

	}

	static void dijkstra() {
		Queue<CITY> pqueue = new PriorityQueue<>((c1, c2) -> c1.cost - c2.cost);

		pqueue.offer(new CITY(1, 0));

		int addCost = 0;
		int cnt = 0;

		while (!pqueue.isEmpty()) {

			CITY nowCity = pqueue.poll();

			if (visited[nowCity.num])
				continue;
			visited[nowCity.num] = true;

			if (cnt > 1) {
				addCost += t;
			}
			answer += (addCost + nowCity.cost);
			cnt += 1;

			for (CITY nextCity : cityList.get(nowCity.num)) {
				if (visited[nextCity.num])
					continue;
				pqueue.offer(nextCity);
			}
		}
	}

	static class CITY {
		int num;
		int cost;

		CITY(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "CITY [num=" + num + ", cost=" + cost + "]";
		}

	}
}
