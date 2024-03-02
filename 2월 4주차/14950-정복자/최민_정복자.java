import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static List<List<Edge>> list = new ArrayList<>();
	static boolean[] visit;
	static int sum=0;
	static int n,m,t;
	static PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2)->e1.c - e2.c);
	static class Edge{
		public int v;
		int v1, c;
		public Edge(int v, int v1, int c) {
			this.v = v;
			this.v1 = v1;
			this.c = c;
		}
		public Edge(int v1, int c) {
			this.v1 = v1;
			this.c = c;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		t=Integer.parseInt(st.nextToken());
		
		visit = new boolean[n+1];
		for(int i=0; i<=n; i++) {
			list.add(new ArrayList<>());
		}
		for(int i=0; i<m; i++) {
			st= new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.get(v1).add(new Edge(v2, c));
			list.get(v2).add(new Edge(v1, c));
		}
		
		prim();
		System.out.println(sum);
	}
	
	static void prim() {
		//1번 도시부터 시작
		int cnt=1;
		visit[1] = true;
		pq.addAll(list.get(1));
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			if(visit[edge.v1]) continue;
			
			visit[edge.v1]=true;
			sum+=edge.c+((cnt-1)*t);
			cnt++;
			if(cnt==n) break;
			
			List<Edge> temp = list.get(edge.v1);
			int size=temp.size();
			for(int i=0; i<size; i++) {
				Edge e = temp.get(i);
				if(!visit[e.v1])pq.add(e);
			}
		}
		
	}
}

