import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 	[부족했던 점 CHECK !]
 	
	1. 25 % 틀림 -> 아래의 반례를 사용하여 해결 ! 
	route라는 ArrayList 배열을 사용하여 지금 계산된 거리 값이 현재까지 계산된 거리 값보다 작을 때,
	지금 계산되기까지의 친구 목록으로 갱신해 줌. 
	
	=> 더 작은 거리 값만 갱신되고, 그때의 친구 목록이 갱신되지 않았던 문제 해결 
	
	1-1. ArrayList 깊은 복사
	route[i] = (ArrayList<Integer>) route[j].clone();
	
	
 */

/*
1
6 5
0 1 1
1 2 1
2 3 1
3 4 1
0 3 2
3 4 1

print : 0 1 2 3 4
answer : 0 3 4
 */

public class B_9694 {

	static int N, M;
	static List<List<int[]>> info;
	static int[] distance;
	static ArrayList[] route;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 관계 수
			M = Integer.parseInt(st.nextToken()); // 정치인의 수

			info = new ArrayList<>();
			for (int i = 0; i < M; i++) {
				info.add(new ArrayList<>());
			}
			for (int i = 0; i < N; i++) {

				st = new StringTokenizer(br.readLine());
				int f1 = Integer.parseInt(st.nextToken());
				int f2 = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				info.get(f1).add(new int[] { f2, c });
				info.get(f2).add(new int[] { f1, c });

			}

			System.out.print("Case #" + t + ": ");
			dijkstra();
			if (route[M - 1].size() == 1) {
				System.out.println(-1);
			} else {
				for (int i = 0; i < route[M - 1].size(); i++) {
					System.out.print(route[M - 1].get(i) + " ");
				}
			}
			System.out.println();
		}

	}

	static void dijkstra() {

		PriorityQueue<int[]> pqueue = new PriorityQueue<>((f1, f2) -> f1[1] - f2[1]);
		pqueue.offer(new int[] { 0, 0 }); // friend N, cost(친밀도)

		distance = new int[M];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[0] = 0;

		route = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			route[i] = new ArrayList<>();
			route[i].add(0);
		}

		while (!pqueue.isEmpty()) {
			int[] nowFriend = pqueue.poll();

			for (int[] nextFriend : info.get(nowFriend[0])) {
				if (distance[nextFriend[0]] >= distance[nowFriend[0]] + nextFriend[1]) {
					distance[nextFriend[0]] = distance[nowFriend[0]] + nextFriend[1];
					route[nextFriend[0]] = (ArrayList<Integer>) route[nowFriend[0]].clone();
					route[nextFriend[0]].add(nextFriend[0]);
					pqueue.offer(nextFriend);

				}

			}

		}

	}

}
