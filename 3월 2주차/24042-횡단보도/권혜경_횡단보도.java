import java.io.*;
import java.util.*;

public class B_횡단보도_24042 {
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
        dijstra();
        System.out.println(answer[N]);
    }
    static void dijstra(){
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
