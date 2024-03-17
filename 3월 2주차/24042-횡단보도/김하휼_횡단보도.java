import java.util.*;
import java.io.*;

public class Main {
	static int N,M;
	static long INF;
	static long[] min_dis;
	static ArrayList<ArrayList<Pos>> arr = new ArrayList<ArrayList<Pos>>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		INF = Long.MAX_VALUE;
		min_dis = new long[N+1];
		
		for (int i=0; i<=N; i++) {
			arr.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr.get(a).add(new Pos(b,i)); 
			arr.get(b).add(new Pos(a,i));
		}
		dijkstra();
		System.out.println(min_dis[N]);
	}
	
	public static void dijkstra() {
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		pq.offer(new Pos(1,0)); 
		Arrays.fill(min_dis, INF);
		min_dis[1] = 0;
		
		while (!pq.isEmpty()) {
			Pos cur = pq.poll();
			
			if (min_dis[cur.end] < cur.weight) continue;
			
			for (Pos next: arr.get(cur.end)) {
				long nxCost = cur.weight + ((next.weight - cur.weight) % M + M)%M; // 주기 구할 때 계속 WA 떠서 다른 분꺼 참고함 ! 
				if (min_dis[next.end] > nxCost+1) {
					min_dis[next.end] = nxCost+1;
					pq.offer(new Pos(next.end, nxCost+1));
				}
			}
		}
	}
}

class Pos implements Comparable<Pos>{
	int end;
	long weight;
	public Pos(int end, long weight) {
		this.end = end;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Pos o) {
		return (int)this.weight- (int)o.weight;
	}
}
