// 다익스트라로 접근해서 최소값 또는 동일한 값일때 before이라는 리스트에 직전 노드를 저장해주는 방법으로 접근했는데 왜 틀렷는지 모르겠씁니다
// 1퍼부터 틀려서 제법 속상,,

import java.io.*;
import java.util.*;

public class B_모비스터디_31230 {
    static int N, M, A, B, answer = 0;
    static int cost[];
    static boolean visit[];
    static List<List<Node>> link = new ArrayList<>();
    static List<List<Integer>> before = new ArrayList<>();
    static PriorityQueue<Node> pqueue = new PriorityQueue<>((o1,o2)->o1.c-o2.c);
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        cost = new int[N+1];
        visit = new boolean[N+1];

        for(int i=0;i<=N;i++){
            link.add(new ArrayList<>());
            before.add(new ArrayList<>());
        }
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            link.get(a).add(new Node(b, c));
            link.get(b).add(new Node(a, c));
        }

        dijkstra();

        check();

        System.out.println(answer);
        System.out.println(sb.toString());
    }
    static void check(){
        Arrays.fill(visit, false);
        pqueue.offer(new Node(B, 0));
        while(!pqueue.isEmpty()){
            Node temp = pqueue.poll();
            if(visit[temp.v]) continue;
            visit[temp.v] = true;
            
            for(int i:before.get(temp.v)){
                if(visit[i]) continue;
                pqueue.offer(new Node(i, 0));
            }
        }

        for(int i=1;i<=N;i++){
            if(!visit[i]) continue;
            sb.append(i).append(" ");
            answer++;
        }
    }
    static void dijkstra(){
        pqueue.offer(new Node(A, 0));
        while(!pqueue.isEmpty()){
            Node temp = pqueue.poll();
            if(visit[temp.v]) continue;
            visit[temp.v] = true; 
            for(Node n : link.get(temp.v)){
            
                if(cost[n.v]==cost[temp.v]+n.c){
                    before.get(n.v).add(temp.v);
                }
                else if(cost[n.v]==0||(cost[n.v]!=0&&cost[n.v]>cost[temp.v]+n.c)) {
                    cost[n.v]=cost[temp.v]+n.c;
                    before.get(n.v).clear();
                    before.get(n.v).add(temp.v);
                    pqueue.add(new Node(n.v, cost[n.v]));
                }
            }
        }

    }
    static class Node{
        int v, c;
        Node(int v, int c){
            this.v=v;
            this.c=c;
        }
    }
}
