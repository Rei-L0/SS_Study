package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class bj_1504_특정한최단경로 {
	static List<List<Node>> graph;
	static int N,E;
	
	static class Node implements Comparable<Node>{
		int end;
		int weight;
		public Node(int end, int weight) {
			super();
			this.end = end;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.weight-o.weight;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<E; i++) {
			st=new StringTokenizer(br.readLine());
			int a= Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());
			int c= Integer.parseInt(st.nextToken());
			
			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}
		
		st= new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken()); //입력 받기 끝
		
		//1번 정점 -> v1정점 -> v2정점 -> N번 정점 또는
		//1번 정점 -> v2정점 -> v1정점 -> N번 정점
		int result1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
        int result2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);
        
        
        if(dijkstra(1, v1) == Integer.MAX_VALUE|| dijkstra(v1, v2) ==Integer.MAX_VALUE || dijkstra(v2, v1)==Integer.MAX_VALUE) { //경로가 없는 경우이니 -1 출력
        	System.out.println(-1);
        }
        else {
        	int answer = Math.min(result1, result2);
        	if (answer >= Integer.MAX_VALUE || answer < 0) {
                System.out.println(-1);
            } else {
                System.out.println(answer);
            }
        }
        
		
	}
	static int dijkstra(int start, int end) {
		if(start==end) return 0;
		else {
			PriorityQueue<Node> pq = new PriorityQueue<>();
			int[] distance = new int[N+1];
			Arrays.fill(distance, Integer.MAX_VALUE);
			distance[start]=0;
			
			pq.offer(new Node(start,0));
			
			while(!pq.isEmpty()) {
				Node current = pq.poll();
				
				if(current.end == end) break;
				
				for(Node next : graph.get(current.end)) {
					if (distance[next.end] > distance[current.end] + next.weight) {
						distance[next.end] = distance[current.end] + next.weight;
						pq.offer(new Node(next.end, distance[next.end]));
					}
				}
			}
			return distance[end];
		}
		
		
	}
}
