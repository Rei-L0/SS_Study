// MSI 인건 알앗는데, 어케 풀었는지 가물가물해서 찾아보고 풀었습니다!
// 항상 고민인 최댓값을 어케 계산하는가,,

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer;
    static int friend[] = new int[3];
    static Long dis = 0L;
    static Long cost[][];
    static boolean visit[];
    static List<List<Node>> list = new ArrayList<>();
    
    static PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->o1.c<o2.c ? -1 :1);
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<3;i++){
            friend[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        cost = new Long[3][N+1];
        for(int i=0;i<=N;i++){
            list.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.get(a).add(new Node(b, 0L+c));
            list.get(b).add(new Node(a, 0L+c));
        }

        for(int i=0;i<3;i++){
            dijkstra(i);
        }

        for(int i=1;i<=N;i++){
            Long max = Math.min(Math.min(cost[0][i],cost[1][i]), cost[2][i]);
            if(dis >= max) continue;
            dis = max;
            answer = i; 
        }

        System.out.println(answer);
    }
    static void dijkstra(int idx){
        pq.offer(new Node(friend[idx], 0L));
        Arrays.fill(cost[idx], 100_000*500_000*10_000 + 2L);
        cost[idx][friend[idx]] = 0L;
        visit = new boolean[N+1];

        while(!pq.isEmpty()){
            Node temp = pq.poll();
            if(visit[temp.v]) continue;
            visit[temp.v] = true;
            for(Node n : list.get(temp.v)){
                if(cost[idx][temp.v]+n.c<cost[idx][n.v]){
                    cost[idx][n.v] = cost[idx][temp.v]+n.c;
                    pq.offer(new Node(n.v, cost[idx][n.v]));
                }
            }
        }
    }
    static class Node{
        int v;
        Long c;
        Node(int v, Long c){
            this.v=v;
            this.c=c;
        }
        @Override
        public String toString() {
            return "Node [v=" + v + ", c=" + c + "]";
        }
    }
}
