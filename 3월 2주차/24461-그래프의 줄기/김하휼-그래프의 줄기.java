package Baekjoon.위상정렬;
import java.io.*;
import java.util.*;

/* 
첨에 문제 제대로 안읽으면 뭐지? 하는 거 같은데, 좀만 생각해보면 어렵지 않게 풀 수 있었던 문제 ! 
위상정렬 기본 코드에 일직선이 되는 경우, 안되는 경우만 확인해주면 됨 
*/

class P24461 {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static int N;
	static int[] inDegrees; 
	static ArrayList<Integer> ans = new ArrayList<>();
	static ArrayList<Integer> tmp = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		for (int i=0; i<N; i++) {
			graph.add(new ArrayList<>());
		}

		inDegrees = new int[N];
		

		for (int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
			inDegrees[b]++;
			inDegrees[a]++;
		}

		ts();
		Collections.sort(tmp);
		for (int v:tmp) {
			sb.append(v).append(" ");
		}
		System.out.println(sb);

	}

	public static void ts() {
		Queue<Integer> q = new ArrayDeque<>();
		for (int i=0; i<inDegrees.length; i++) {
			if (inDegrees[i] == 1) {
				q.offer(i);
			}
		}

		while (!q.isEmpty()) {

			int t = q.size();

			if (t <= 2) { // 일직선이 될 수 있는 경우. 
				for (int i=0; i<t; i++) {
					int cur = q.poll();
					tmp.add(cur);
	
					for (int next: graph.get(cur)) {
						inDegrees[next]--;
						if (inDegrees[next] == 1) {
							q.offer(next);
						}
					}
				}
			}
			else { // 일직선이 안되면, 거장자리 정점 계속 없애기 
				for (int i=0; i<t; i++) {
					int cur = q.poll();
	
					for (int next: graph.get(cur)) {
						inDegrees[next]--;
						if (inDegrees[next] == 1) {
							q.offer(next);
						}
					}
				}
			}
		}
	}
}
