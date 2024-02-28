package 완료;
import java.io.*;
import java.util.*;

/**
 * 다익스트라로 풀었음
 연결된 친구들을 어떻게 표현하지 고민하다가 직전에 연결했던 애들을 저장해주면서 끝까지 갔다가
 끝에 도달하면 역순으로 돌면서 저장
 */
public class B_무엇을아느냐아니라누구를아느냐가문제다_9694 {
    static int T, N, M;
    static int cost[];
    static int before[];
    static boolean visit[];
    static List<List<Node>> list ;
    static StringBuilder sb = new StringBuilder();
    static StringBuilder answer = new StringBuilder();
    static PriorityQueue<Node> pqueue = new PriorityQueue<>((o1,o2)->o1.c==o2.c ? o1.how-o2.how : o1.c-o2.c);
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine()); 
        for(int t=1;t<=T;t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            answer.setLength(0);
            pqueue.clear();
            list = new ArrayList<>();
            cost = new int[M];
            before = new int[M];
            visit = new boolean[M];
            Arrays.fill (cost, Integer.MAX_VALUE);

            for(int i=0;i<M;i++){
                list.add(new ArrayList<>());
            }
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());

                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());
                list.get(B).add(new Node(A, C, 1));
                list.get(A).add(new Node(B, C, 1));
            }

            pqueue.offer(new Node(0, 0, 0));
            while(!pqueue.isEmpty()){
                Node temp = pqueue.poll();
                if(visit[temp.v]) continue;
                visit[temp.v] = true;
                for(Node n : list.get(temp.v)){
                    if(temp.c+n.c < cost[n.v]){
                        before[n.v] = temp.v;
                        cost[n.v] = temp.c+n.c;
                        pqueue.offer(new Node(n.v, temp.c+n.c, n.how+temp.how));
                    }
                }
            }
            
            int turn = M-1;
            if(cost[M-1]!=Integer.MAX_VALUE){
                while(turn!=0){
                    answer.insert(0, " ").insert(0, turn);
                    turn = before[turn];
                }
                answer.insert(0,"0 ");
            }
            else answer.append("-1");

            sb.append("Case #").append(t).append(": ").append(answer).append("\n");
        }
        System.out.println(sb.toString());
    }
    static class Node{
        int v,c,how;
        Node(int v, int c, int how){
            this.v=v;
            this.c=c;
            this.how=how;
        }
    }
}
