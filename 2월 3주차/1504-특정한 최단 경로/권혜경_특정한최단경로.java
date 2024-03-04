import java.io.*;
import java.util.*;
public class Main {
    static int N,E,V1,V2, start; 
    static long answer, start_middle = 0, cost=0;
    static long node[];
    static boolean visit[];
    static List<List<Node>> link = new ArrayList<>();
    static Queue<Node> pqueue = new PriorityQueue<>((o1,o2)-> o1.c-o2.c<0 ? -1 : 1);
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        node = new long[N+1];
        visit = new boolean[N+1];
        for(int i=0;i<=N;i++){
            link.add(new ArrayList<>());
        }

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            link.get(A).add(new Node(B, C));
            link.get(B).add(new Node(A, C));
        }
        st = new StringTokenizer(br.readLine());
        V1 = Integer.parseInt(st.nextToken());
        V2 = Integer.parseInt(st.nextToken());

        long start_V1=-1,start_V2=-1,V1_V2=-1,V1_end=-1,V2_end=-1;
        // 1에서 V1으로 가기
        go(1); 
        
        start_V1 = node[V1];
        start_V2 = node[V2];

        if(V1==1 && V2==N){
            System.out.println(node[N]);
            return;
        }        

        // V1에서 V2로 가기
        go(V1); 
        V1_V2 = node[V2];

        // N에서부터 V1, V2로 가기
        go(N); 
        V1_end = node[V1];
        V2_end = node[V2];

        if(start_V1==-1 || start_V2==-1 || V1_V2==-1 || V1_end==-1 || V2_end==-1) answer =-1;
        else answer = Math.min(start_V1+V1_V2+V2_end, start_V2+V1_V2+V1_end);
        //System.out.println(start_V1+" "+V1_V2+" "+V2_end);
        //System.out.println(start_V2+" "+V1_V2+" "+V1_end);
        System.out.println(answer);
    }
    static void go(int idx){
        
        Arrays.fill(visit,false);
        Arrays.fill(node,-1);

        node[idx] = 0;
        pqueue.offer(new Node(idx, 0));

        while(!pqueue.isEmpty()){
            Node now = pqueue.poll();
            if(visit[now.v]) continue;
            //System.out.print(now.v+" "+now.c+" / ");
            visit[now.v] = true;
            for(Node n : link.get(now.v)){
                if(visit[n.v] || (node[n.v]!=-1 && node[n.v]<=node[now.v]+n.c)) continue;
                node[n.v] = node[now.v] + n.c;
                pqueue.offer(new Node(n.v, node[n.v])); 
            }
        }
        //System.out.println(" ");

    }
    static class Node{
        int v;
        long c;
        Node(int v, long c){
            this.v=v;
            this.c=c;
        }
    }
}
