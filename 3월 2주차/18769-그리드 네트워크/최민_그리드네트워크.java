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
	static int r,c;
	static class Edge{
		int end;
		int c;
		public Edge(int end, int c) {
			super();
			this.end = end;
			this.c = c;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			r= Integer.parseInt(st.nextToken());
			c= Integer.parseInt(st.nextToken());
			
			visit = new boolean[r*c];
			Arrays.fill(visit, false);
			list.clear(); 
			
			
			for(int i=0; i<r*c; i++) {
				list.add(new ArrayList<>());
			}
			
			for(int i=0; i<r; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<c-1; j++) {
					int cost = Integer.parseInt(st.nextToken());
					int index= i*c+j;
					list.get(index).add(new Edge(index+1, cost));
					list.get(index+1).add(new Edge(index, cost));
				}
			}
			
			for(int i=0; i<r-1; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<c; j++) {
					int cost = Integer.parseInt(st.nextToken());
					int index= i*c+j;
					list.get(index).add(new Edge(index+c, cost));
					list.get(index+c).add(new Edge(index, cost));
				}
			}
			prim();
		}
		
	}
	
	static void prim() {
		int count=0;
		int total=0;
		visit[0]=true;
		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2)->e1.c - e2.c);
		
		pq.addAll(list.get(0));
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			if(visit[edge.end]) continue;
			
			visit[edge.end]=true;
			total+=edge.c;
			count++;
			if(count==(r*c)) break;
			
			List<Edge> temp = list.get(edge.end);
			int size = temp.size();
			
			for(int i=0; i<size; i++) {
				Edge e = temp.get(i);
				if(!visit[e.end]) pq.add(e);
			}
		}
		
		System.out.println(total);
	}
}
