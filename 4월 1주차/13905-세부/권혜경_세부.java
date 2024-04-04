import java.io.*;
import java.util.*;
/*
 * 다익스트라의 응용이라고 생각하고 
 * 우선순위의 큐를 내림차순 정렬, 
 * min(현재까지 오는데 걸린 w , 새로운 다리가 감당할 수 있는 w)의 값이 
 * 작으면 기존 w 유지, 크면 갱신 후 pqueue에 넣어줌
 */
public class Main {
    static int N, M, start, end;
    static boolean visit[];
    static int weight[]; 
    static List<List<Node>> list = new ArrayList<>();
    static PriorityQueue<Node> pqueue = new PriorityQueue<Node>((o1,o2)-> o2.w-o1.w);
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visit = new boolean[N+1];
        weight = new int[N+1];

        for(int i=0;i<=N;i++){
            list.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            list.get(A).add(new Node(B, C));
            list.get(B).add(new Node(A, C));
        }
        dijkstra();
        // for(int w :weight){
        //     System.out.print(w +" ");
        // }
        System.out.println(weight[end]);
    }
    static void dijkstra(){
        weight[start] = 1_000_001;
        pqueue.offer(new Node(start, 1_000_001));
        while(!pqueue.isEmpty()){
            Node temp = pqueue.poll();
            if(visit[temp.h]) continue;
            visit[temp.h] = true;
            for(Node n : list.get(temp.h)){ 
                if(weight[n.h] < Math.min(temp.w,n.w)) {
                    weight[n.h] = Math.min(temp.w,n.w);
                    pqueue.offer(new Node(n.h, weight[n.h]));
                }
            }
        }
    }
    static class Node{
        int h, w;
        Node(int h, int w){
            this.h=h;
            this.w=w;
        }
    }
}
