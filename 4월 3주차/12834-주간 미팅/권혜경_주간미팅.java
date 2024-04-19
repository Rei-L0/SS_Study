// A와 B 기준으로 다익스트라로 가는데 걸리는 cost 구하고, 방문하지 못하는 경우에는 -1을 넣어주기


import java.util.*;
import java.io.*;

public class Main {
    static int N, V, E, A, B;
    static int answer = 0;
    static int cost[], home[];
    static boolean visit[];
    static List<List<Node>> link = new ArrayList<>();
    static PriorityQueue<Node> pqueue = new PriorityQueue<>((o1,o2)->o1.c-o2.c);
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        home = new int[N];
        for(int i=0;i<N;i++){
            home[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<=V;i++){
            link.add(new ArrayList<>());
        }
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            link.get(v1).add(new Node(v2, l));
            link.get(v2).add(new Node(v1, l));
        }

        dijkstra(A);
        dijkstra(B);

        System.out.println(answer);
    }
    static void dijkstra(int now){
        visit = new boolean[V+1];
        cost = new int[V+1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        pqueue.offer(new Node(now, 0));
        cost[now] = 0;

        while(!pqueue.isEmpty()){
            Node temp = pqueue.poll();
            if(visit[temp.v]) continue;
            visit[temp.v] = true;

            for(Node n : link.get(temp.v)){
                if(cost[n.v] > cost[temp.v]+n.c){
                    cost[n.v] = cost[temp.v]+n.c;
                    pqueue.offer(new Node(n.v, cost[n.v]));
                }
            }
        }
        for(int h : home){
            //System.out.print(cost[h]+" ");
            if(cost[h] == Integer.MAX_VALUE) cost[h] = -1;
            answer += cost[h];
        }
    }
    static class Node{
        int v,c;
        Node(int v, int c){
            this.v=v;
            this.c=c;
        }
    }
}
