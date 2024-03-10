import java.io.*;
import java.util.*;

// 프림으로 시도했으나 값이 이상함
// 생각해보니 프림으로는 같은 값은 어케 처리하는지 모름
// 진짜 빠른시일내에 해결하겠습니당
public class B_학교탐방하기_13418 {
    static int N,M,A,B,C,start;
    static int go[], answer[] = new int[2];
    static List<List<Node>> list = new ArrayList<>();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        go = new int[N+1];

        for(int i=0;i<=N;i++){
            list.add(new ArrayList<>());
        }
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            list.get(A).add(new Node(B, C, 0));
            list.get(B).add(new Node(A, (C+1)%2, 0));
        }

        System.out.println("---");
        prim(1);
        
        System.out.println("---");
        prim(0);

        System.out.println(answer[0]*answer[0]-answer[1]*answer[1]);
    }
    static void prim(int type){
        Node temp;
        Arrays.fill(go, -1);
        int turn = 0, visit = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>((o1,o2)->o2.c==o1.c ? o1.t-o2.t : o2.c-o1.c);
        if(type == 0) queue = new PriorityQueue<>((o1,o2)->o2.c==o1.c ? o2.t-o1.t : o1.c-o2.c);

        queue.offer(new Node(1,start,0));
        while(!queue.isEmpty()){
            temp = queue.poll();
            if((visit & 1<<temp.v) != 0) continue;
            visit = visit|1<<temp.v;
            turn++;
            go[temp.v] = (temp.c+1)%2;

            for(Node n : list.get(temp.v)){
                if(go[n.v]==type || (go[n.v]==(type+1)%2&&n.c==(type+1)%2)) continue;
                System.out.print(temp.v+" "+n.v+" "+n.c+" / ");
                queue.offer(new Node(n.v, n.c, turn));
            }
            System.out.println(temp.v+" "+temp.c+" "+visit);
            //System.out.println("");
        }
        for(int i:go){
            System.out.print(i+" ");
            answer[type] += i;}
    }
    // static void prim(){
    //     Node temp;
    //     while(!pqueue.isEmpty() || visit == 1<<(N+1)-1){
    //         temp = pqueue.poll();
    //         if((visit & 1<<temp.v) != 0 && go[temp.v]==0) continue;
    //         visit = visit|1<<temp.v;
    //         go[temp.v] = (temp.c+1)%2;
            

    //         System.out.println(temp.v+" "+temp.c+" "+answer+" "+visit);
    //         for(Node n : list.get(temp.v)){
    //             if((visit & 1<<n.v) == 0 || go[n.v]==0&&n.c==1) pqueue.offer(n);
    //         }
    //     }
    // }
    static class Node {
        int v,c,t;
        Node(int v, int c, int t){
            this.v=v;
            this.c=c;
            this.t=t;
        }
    }
}
