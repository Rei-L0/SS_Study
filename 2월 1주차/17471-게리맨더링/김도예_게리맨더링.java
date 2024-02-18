import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static boolean[] isVisited;
	static ArrayList<Integer> list; // 부분집합
	static ArrayList<Integer> list2;
	static int n; // 구역의 개수
	static int sum; // 전체 인구 수
	static int[] peoples; // 각 구역의 인구 수
	static int[][] con; // 인접 행렬
	static int min; // 답
	static int COUNT;
	static ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		peoples = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			peoples[i] = Integer.parseInt(st.nextToken());
			sum += peoples[i];
		}
		con = new int[n + 1][n + 1];
		COUNT = 1 << n;
		boolean isConnect = false;
		for(int i = 1; i <= n; i++) {
			String[] s = br.readLine().split(" ");
			int cnt = Integer.parseInt(s[0]);
			if(cnt != 0) isConnect = true;
			for(int j = 1; j <= cnt; j++) {
				con[i][Integer.parseInt(s[j])] = 1;
			}
		}
		if(!isConnect && n != 2) min = -1;
		else if(!isConnect && n == 2) min = Math.abs(peoples[1] - peoples[2]);
		else {
			min = Integer.MAX_VALUE;
			findPart();
		}
		if(min == Integer.MAX_VALUE) min = -1;
		System.out.println(min);
	}
	
	static void findPart() {
		for(int i = 0; i < COUNT; i++) {
			// src 를 순회하는 index 와 i(bitmask) 를 비교
			list = new ArrayList<>();
			list2 = new ArrayList<>();
			isVisited = new boolean[n+1];
			for(int j = 0; j < n; j++) {
				if((i & 1 << j) != 0) list.add(j + 1);
				else list2.add(j + 1);
			}
			if(list.size() == 0 || list.size() == n) continue;
			if(arr.contains(list)) continue;
			arr.add(list);
			arr.add(list2);
			int s = 0;
			if(bfs(list.get(0), list) &&  bfs(list2.get(0), list2)) {
				for(int num : list) {
					s += peoples[num];
				}
				if(Math.abs((sum - s) - s) < min) min = Math.abs((sum - s) - s);
			};
		}
	}
	static boolean bfs(int x, ArrayList<Integer> list) {
		Deque<Integer> q = new ArrayDeque<>();
		Set<Integer> set = new HashSet<>();
		q.offer(x);
		set.add(x);
		while(!q.isEmpty()) {
			int now = q.poll();
			for(int i = 1; i <= n; i++ ) {
				if(isVisited[i] || con[now][i] == 0) continue;
				
				if(list.contains(i)) {
					q.offer(i);
					set.add(i);
					isVisited[now] = true;
				}
			}
		}
		if(set.size() == list.size()) return true;
		else return false;
	}
}
