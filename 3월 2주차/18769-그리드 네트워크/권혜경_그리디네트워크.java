import java.io.*;
import java.util.*;

public class B_그리드네트워크_18769 {
  // 좌표를 숫자로 전환해서 prim
    static int R,C,T;
    static int map[];
    static boolean visit[];
    static List<List<Node>> link;
    static PriorityQueue<Node> pqueue;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++){
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new int[R*C];
            visit = new boolean[R*C];
            link = new ArrayList<>();
            pqueue = new PriorityQueue<>((o1,o2)->o1.c-o2.c);
            for(int i=0;i<R*C;i++){
                link.add(new ArrayList<>());
            }
            
            for(int j=0;j<R;j++){
                st = new StringTokenizer(br.readLine());
                for(int i=0;i<C-1;i++){
                    int cost = Integer.parseInt(st.nextToken());
                    link.get(j*C+i+1).add(new Node(j*C+i, cost));
                    link.get(j*C+i).add(new Node(j*C+i+1, cost));
                }
            }
            
            for(int j=0;j<R-1;j++){
                st = new StringTokenizer(br.readLine());
                for(int i=0;i<C;i++){
                    int cost = Integer.parseInt(st.nextToken());
                    link.get((j+1)*C+i).add(new Node(j*C+i, cost));
                    link.get(j*C+i).add(new Node((j+1)*C+i, cost));
                }
            }

            check();
        }
        System.out.println(sb.toString());
    }
    static void check(){
        int count=0;
        pqueue.offer(new Node(0,0));

        while(!pqueue.isEmpty()){
            Node temp = pqueue.poll();
            if(visit[temp.v]) continue;
            visit[temp.v] = true;
            count+=temp.c;
            for(Node n:link.get(temp.v)){
                pqueue.offer(n);
            }
        } 
        sb.append(count).append("\n");
    }
    static class Node{
        int v,c;
        Node(int v, int c){
            this.c=c;
            this.v=v;
        }
    }
}
