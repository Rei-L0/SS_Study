package Baekjoon.다익스트라;
import java.util.*;
import java.io.*;

class P1504 {
    static int N,E,INF;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    static int[] min_dis;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
    
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        INF = 200000000; // INF 값 설정때매 몇번 틀렸음. E가 20만이고. 가중치가 1000이니까 INF는 2억으로 설정 

        for (int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b,c)); // 무방향 그래프 
            graph.get(b).add(new Node(a,c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] first = dijkstra(1);
        int[] second = dijkstra(v1);
        int[] third = dijkstra(v2);

        int from_v1_to_v2 = first[v1] + second[v2] + third[N]; // 1 -> 2 -> 3 -> N
        int from_v2_to_v1 = first[v2] + third[v1] + second[N]; // 1 -> 3 -> 2 -> N

        int ans = Math.min(from_v1_to_v2,from_v2_to_v1);
        if (ans < INF) sb.append(ans);
        else sb.append(-1);

        System.out.println(sb);
        
    }    

    public static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>((o1,o2) -> o1.cost - o2.cost);
        min_dis = new int[N+1];

        for (int i=0; i<=N; i++) {
            min_dis[i] = INF;
        }

        min_dis[start] = 0;
        pq.offer(new Node(start,0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (min_dis[cur.node] < cur.cost) continue;

            for (int i=0; i< graph.get(cur.node).size(); i++) {
                Node next = graph.get(cur.node).get(i);
                if (min_dis[next.node] > cur.cost + next.cost) {
                    min_dis[next.node] = cur.cost + next.cost;
                    pq.offer(new Node(next.node, cur.cost + next.cost));
                }
            }
        }
        return min_dis;
    }
}

class Node{
    int node;
    int cost;
    public Node(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
}
