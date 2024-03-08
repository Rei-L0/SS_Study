import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static boolean visit[];
	static PriorityQueue<Edge> pqueue = new PriorityQueue<>((e1, e2)->e2.c - e1.c);
	static PriorityQueue<Edge> pqueue_bad = new PriorityQueue<>((e1, e2)->e1.c - e2.c);
	static int n;
	static class Edge{
		int end;
		int c; //0 오르막길, 1 내리막길
		
		public Edge(int start, int end, int c) {
			super();
			this.end = end;
			this.c = c;
		}
		
	}
	
	static List<List<Edge>> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n= Integer.parseInt(st.nextToken()); //건물의 개수
		int m= Integer.parseInt(st.nextToken());//도로의 개수
		
		for(int i=0; i<=n; i++) {
			list.add(new ArrayList<>());
		}
		visit= new boolean[n+1];
		
		for(int i=0; i<=m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c =Integer.parseInt(st.nextToken());
			
			list.get(a).add(new Edge(a, b, c));
			list.get(b).add(new Edge(b, a, c));
		}
		

		
		int bad= prim(pqueue_bad);
		
		Arrays.fill(visit, false);
		int good= prim(pqueue);
		System.out.println(bad-good);
		
	}
	
	//Edge 중심의 프림 알고리즘 사용하기 -> 건물수가 1000개일때 간선수가 999*500이 최대치
	static int prim(PriorityQueue<Edge> queue) {
		int k=0;
		visit[0]= true;
		int cnt=0;
		
		queue.addAll(list.get(0));
		
		while(!queue.isEmpty()) {
			Edge edge = queue.poll(); 
			if(visit[edge.end]) continue;
			
			visit[edge.end]=true;
			if(edge.c==0) k++;
			cnt++;
		
			if(cnt==(n+1)) break;
			List<Edge> temp = list.get(edge.end);
			int size=temp.size();
			for (int i = 0; i < size; i++) {
				Edge e = temp.get(i);
				if(!visit[e.end]) queue.add(e);
			}

		}
		
		//선택된 오르막길의 갯수를 세서 제곱해준다.
		return k*k;
	}
}
