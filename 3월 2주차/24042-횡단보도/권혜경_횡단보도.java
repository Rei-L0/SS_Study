import java.io.*;
import java.util.*;

public class B_횡단보도_24042 {
    // 기본풀이 다익스트라와 동일
    // 단 현재 내가 있는 시간을 M으로 나눴을때의 나머지가 내 주기보다 작거나 같으면 그때부터 시작하면 되니까 넘어감
    // 근데 내가 있는 시간이 이 시간대의 주기를 넘어갔으면 다음 시간대의 주기를 기다려야해서 M을 더해줌
    
    static int N,M;
    static long answer[];
    static boolean visit[];
    static List<List<Node>> link = new ArrayList<>();
    static PriorityQueue<Node> pqueue = new PriorityQueue<>((o1,o2)-> o1.t-o2.t<0 ? -1 :1) ;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = new long[N+1];
        visit = new boolean[N+1];
        Arrays.fill(answer, Long.MAX_VALUE);

        for(int i=0;i<=N;i++){
            link.add(new ArrayList<>());
        }
        int A,B;
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            link.get(A).add(new Node(B, i));
            link.get(B).add(new Node(A, i));
        }
        dijkstra();
        System.out.println(answer[N]);
    }
    static void dijkstra(){
        pqueue.offer(new Node(1, 0));
        while(!pqueue.isEmpty()){
            Node temp = pqueue.poll();
            if(answer[temp.v]<=temp.t || visit[temp.v]) continue; 
            answer[temp.v] = temp.t;
            visit[temp.v] = true;
            for(Node l : link.get(temp.v)){
                if(temp.t%M<=l.t){
                    pqueue.offer(new Node(l.v, temp.t/M*M+l.t+1));
                }else{
                    pqueue.offer(new Node(l.v, (temp.t/M+1)*M+l.t+1));
                }
            }
        }

    }
    static class Node{
        int v;
        long t;
        Node(int v, long t){
            this.v=v;
            this.t=t;
        }
    }
}
