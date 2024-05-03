import java.io.*;
import java.util.*;

public class boj {
	static int N;
	static ArrayList<Node> village;
	static long sum = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		village = new ArrayList<Node>();
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int area = Integer.parseInt(st.nextToken());
			int people = Integer.parseInt(st.nextToken());
			village.add(new Node(area,people));
			sum += people;
		}
		
		Collections.sort(village); // 가능한 경우가 여러 가지인 경우 더 작은 위치 출력을 위한 오름차순 정렬.
		long ans = 0;
		
		for (int i=0; i<N; i++) {
			ans += village.get(i).p; // 중간값을 포함하면서 중간값에 가장 가까운 마을이 답임 !! 
			if (ans >= (sum+1)/2) {
				System.out.println(village.get(i).n);
				break;
			}
		}
	}
	
	static class Node implements Comparable<Node>{
		int n,p;
		public Node(int n, int p) {
			this.n = n;
			this.p = p;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.n - o.n;
		}
	}
}
