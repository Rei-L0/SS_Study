package Baekjoon.최소신장트리;
import java.util.*;
import java.io.*;

/*
- Checkpoints

1. 정복 횟수에 따라 도로 비용이 증가하는데, 이 경우를 잘 고려해줘야 할 듯 !

2. 크루스칼보다는 프림이 훨 간단한 거 같다.

3. 체감난이도: 중하. 최소신장트리에 관한 알고리즘을 알고만 있으면 어렵지 않게 풀 수 있었던 문제 !

*/

class P14950 {
    static int N,M,T,ans;
    static boolean[] visited;
    static ArrayList<ArrayList<Edge>> graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList<ArrayList<Edge>>();
        ans = 0;
        visited = new boolean[N+1];

        for (int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Edge(B, C));
            graph.get(B).add(new Edge(A, C));
        }

        prim(1);
        System.out.println(ans);

     }

     public static void prim(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        pq.offer(new Edge(start, 0));
        int idx = 1; // 정복할 때마다 t씩 증가하는데, t*idx만큼 증가시키기 위한 변수 
        int cnt = 0; // 정복한 횟수 

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (visited[cur.end]) continue;

            visited[cur.end] = true;

            if (cnt >= 2){ // 정복 횟수가 2 이상이면, 도로의 비용을 t*idx만큼 증가한 값을 총 비용에 +
                ans += cur.cost + (T*idx);
                idx++;
            }
            else { // 아니면 기존 도로의 비용만큼만 더해줌 
                ans += cur.cost;
            }

            for (Edge next: graph.get(cur.end)) {
                if (!visited[next.end]) {
                    pq.offer(new Edge(next.end, next.cost));
                }    
            }
            cnt++;
        }
     }
}

class Edge implements Comparable<Edge>{
    int end;
    int cost;

    public Edge(int end, int cost) {
        this.end = end;
        this.cost = cost;
    }
    @Override
    public int compareTo(Edge o){
        return this.cost - o.cost;
    }
}
