package Week02_03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 [부족했던 점 CHECK !]
 
 0. 자바로 다익스트라가 익숙하지 못함. -> 자바 다익스트라 코드 참조 
 1. 지나야하는 정점을 구현하기 위하여 다익스트라를 3번 해야한다는 생각을 못함.
	  dijkstra(1, n1), dijkstra(n1, n2), dijkstra(n2, N) 
 2. 3% 틀림 - cost를 더 작게 만들 수 있는 nexVertex를 queue에 넣지 않음.
 	  pq.offer(next); 을 빼먹음
 3. 지나야 하는 정점의 순서를 바꾸어 구한 최솟값을 구할 생각을 못함.
	  dijkstra(1, n1), dijkstra(n1, n2), dijkstra(n2, N) 
	  dijkstra(1, n2), dijkstra(n2, n1), dijkstra(n1, N) 
 */


class Node implements Comparable<Node> {
	int index;
	int cost;

	public Node(int index, int cost) {
		this.index = index;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.cost, o.cost);
	}

}

public class B_1504 {

	static ArrayList<Node>[] graph;
	static int N, E;
	static int[] checkpointsList = new int[4];
	static int INF = 200000000; // cost 의 max 값 1000

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N + 1];
		int answer = Integer.MAX_VALUE;

		// 초기화
		for (int i = 0; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		// 입력
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			// 양방향
			graph[v1].add(new Node(v2, c));
			graph[v2].add(new Node(v1, c));
		}

		// 정점 1 - 지나야하는 정점 1 - 지나야하는 정점 2 - 정점 N 값을 담을 checkpointsList
		st = new StringTokenizer(br.readLine());
		checkpointsList[0] = 1;
		checkpointsList[3] = N;
		for (int i = 1; i < 3; i++) {
			checkpointsList[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < 2; i++) {
			int a = 0;
			for (int j = 0; j < 3; j++) {
				int temp = Dijkstra(checkpointsList[j], checkpointsList[j + 1]);
				if (temp == INF) {
					System.out.println(-1);
					return;
				}
				a += temp;
			}
			answer = Math.min(answer, a);

			// 지나야하는 정점의 순서를 다르게 하여 얻은 두 개의 값 중 최솟 값을 구하기 위한 작업
			int t = checkpointsList[1];
			checkpointsList[1] = checkpointsList[2];
			checkpointsList[2] = t;
		}

		System.out.println(answer);

	}

	public static int Dijkstra(int start, int end) {

		int[] dist = new int[N + 1];

		Arrays.fill(dist, INF);
		dist[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node nowVertex = pq.poll();
			
			for (Node next : graph[nowVertex.index]) {
				if (dist[next.index] > dist[nowVertex.index] + next.cost) {
					dist[next.index] = dist[nowVertex.index] + next.cost;
					pq.offer(next);
				}
			}
		}
		return dist[end];
	}

}
