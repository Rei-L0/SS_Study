//아직못풀었어요ㅜㅜ
import java.io.*;
import java.util.*;
public class B_특정한최단경로_1504 {
    static int N,E,V1,V2, start; 
    static int link[][];
    static long answer, start_middle = 0, cost=0;
    static long node[];
    static Queue<Node> pqueue = new PriorityQueue<>((o1,o2)-> o1.c-o2.c<0 ? -1 : 1);
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        link = new int[N+1][N+1];
        node = new long[N+1];

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            if(link[A][B]!=0 && link[A][B]<C) continue;
            link[A][B] = C;
            link[B][A] = C;
        }
        st = new StringTokenizer(br.readLine());
        V1 = Integer.parseInt(st.nextToken());
        V2 = Integer.parseInt(st.nextToken());

        Arrays.fill(node,-1);
        node[V1] = 0;
        pqueue.clear();
        go(1); 
        long start_V1=-1,start_V2=-1,V1_V2=-1,V1_end=-1,V2_end=-1;
        start_V1 = node[V1];
        start_V2 = node[V2];

        if(V1==1 && V2==N){
            System.out.println(node[N]);
            return;
        }

        Arrays.fill(node,-1);
        node[V1] = 0;
        pqueue.clear();
        go(V1); 
        V1_V2 = node[V2];

        Arrays.fill(node,-1);
        node[N] = 0;
        pqueue.clear();
        go(N); 
        V1_end = node[V1];
        V2_end = node[V2];

        if(start_V1==-1 || start_V2==-1 || V1_V2==-1 || V1_end==-1 || V2_end==-1) answer =-1;
        else answer = Math.min(start_V1+V1_V2+V2_end, start_V2+V1_V2+V1_end);

        System.out.println(answer);
    }
    static void go(int idx){
        for(int i = 1; i <= N; i++){
            if(link[i][idx]==0) continue;
            node[i] = (long)link[idx][i];
            pqueue.offer(new Node(i, node[i]));
        }
        while(!pqueue.isEmpty()){
            Node now = pqueue.poll();
            //System.out.print(now.v+" ");
            for(int i=1;i<=N;i++){
                //방문한 노드라면 node[i] + i -> now까지 가는데 걸린 cost vs node[now]
                //방문하지 않은 노드라면 node[i] + i->now
                if(i==idx || i==now.v || link[i][now.v] ==0) continue;
                if(node[i]==0 || (node[i] > node[now.v] + link[i][now.v]) ) {
                    node[i] = node[now.v] + link[i][now.v];
                    pqueue.add(new Node(i, node[i]));
                }
            }
        }

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

// 2번접근 V1,V2 를 각자 목적지 => V1-V2 연결 => V1, V2에서 N으로 가는 거리 
// 근데 생각해보니 다익스트라를 안쓴것같아 다시
// 시간초과 
/*
public class B_특정한최단경로_1504 {
    static int N,E,V1,V2,start, end; 
    static long V1_1, V2_1, V1_V2, V1_N, V2_N, answer;
    static int link[][];
    static long node[];
    static Queue<Integer> pqueue = new ArrayDeque<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        link = new int[N+1][N+1];

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            link[A][B] = C;
            link[B][A] = C;
        }
        
        st = new StringTokenizer(br.readLine());
        V1 = Integer.parseInt(st.nextToken());
        V2 = Integer.parseInt(st.nextToken());

        V1_1=Integer.MAX_VALUE; 
        V2_1=Integer.MAX_VALUE; 
        V1_V2=Integer.MAX_VALUE;
        V1_N=Integer.MAX_VALUE;
        V2_N=Integer.MAX_VALUE;

        node = new long[N+1];
        start = 1; end = V1;
        go(1, 0,0);
        
        node = new long[N+1];
        start = 1; end = V2;
        go(1, 0,0);

        node = new long[N+1];
        start = V1; end = V2;
        go(V1, 0,0);

        node = new long[N+1];
        start = V1; end = N; 
        go(V1, 0,0);

        node = new long[N+1];
        start = V2; end = N; 
        go(V2, 0,0);

        if(V1_1==Integer.MAX_VALUE||V2_1==Integer.MAX_VALUE||V1_V2==Integer.MAX_VALUE
        ||V1_N==Integer.MAX_VALUE||V2_N==Integer.MAX_VALUE)
            answer = -1;
        else 
            answer = Math.min(V1_1+V1_V2+V2_N, V2_1+V1_V2+V1_N);
        //System.out.println(V1_1+" "+V1_V2+" "+V2_N+" "+V2_1+" "+V1_V2+" "+V1_N);
        System.out.println(answer);
        
   }
   static void go(int idx, long count, int visit){
        if(visit == N) return;
        if(idx == end){
            if(start == 1 && end == V1)
                V1_1 = Math.min(V1_1, count);
            else if(start == 1 && end == V2)
                V2_1 = Math.min(V2_1, count);
            else if(start == V1 && end == V2)
                V1_V2 = Math.min(V1_V2, count);
            else if(start == V1 && end == N)
                V1_N = Math.min(V1_N, count);
            else if(start == V2 && end == N)
                V2_N = Math.min(V2_N, count);
            
        }
        for(int i=1;i<=N;i++){
            if(link[idx][i]==0) continue; 
            if(node[i]!=0 && node[i]<(long)link[idx][i]+count) continue;
            count += (long)link[idx][i];
            if(node[i]!=0){ 
                node[i] = (long)link[idx][i]+count;
                go(i, count, visit+1);
            }else {
                node[i] = (long)link[idx][i]+count;
                go(i, count, visit);
            }
            count -= (long)link[idx][i];
        }
   }
}*/
/*
public class B_특정한최단경로_1504 {
    static int N,E,V1,V2,start, end; 
    static long V1_1, V2_1, V1_V2, V1_N, V2_N, answer;
    static int link[][];
    static long node[][];
    static Queue<Integer> pqueue = new ArrayDeque<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        link = new int[N+1][N+1];
        node = new long[N+1][N+1];

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            link[A][B] = C;
            link[B][A] = C;
            node[A][B] = C;
            node[B][A] = C;
        }
        
        st = new StringTokenizer(br.readLine());
        V1 = Integer.parseInt(st.nextToken());
        V2 = Integer.parseInt(st.nextToken());

        V1_1=Integer.MAX_VALUE; 
        V2_1=Integer.MAX_VALUE; 
        V1_V2=Integer.MAX_VALUE;
        V1_N=Integer.MAX_VALUE;
        V2_N=Integer.MAX_VALUE;

        start = 1; end = V1;
        go(1, 0,0);
        
        start = 1; end = V2;
        go(1, 0,0);

        start = V1; end = V2;
        go(V1, 0,0);

        start = V1; end = N; 
        go(V1, 0,0);

        start = V2; end = N; 
        go(V2, 0,0);

        if(V1_1==Integer.MAX_VALUE||V2_1==Integer.MAX_VALUE||V1_V2==Integer.MAX_VALUE
        ||V1_N==Integer.MAX_VALUE||V2_N==Integer.MAX_VALUE)
            answer=-1;
        else 
            answer = Math.min(V1_1+V1_V2+V2_N, V2_1+V1_V2+V1_N);
        System.out.println(V1_1+" "+V1_V2+" "+V2_N+" "+V2_1+" "+V1_V2+" "+V1_N);
        System.out.println(answer);
        
   }
   static void go(int idx, long count, int visit){
        if(visit == N) return;
        if(idx == end){
            if(start == 1 && end == V1)
                V1_1 = Math.min(V1_1, count);
            else if(start == 1 && end == V2)
                V2_1 = Math.min(V2_1, count);
            else if(start == V1 && end == V2)
                V1_V2 = Math.min(V1_V2, count);
            else if(start == V1 && end == N)
                V1_N = Math.min(V1_N, count);
            else if(start == V2 && end == N)
                V2_N = Math.min(V2_N, count);
            
        }
        for(int i=1;i<=N;i++){
            if(link[idx][i]!=0) {
                if(node[idx][i]!=0 && node[idx][i]<link[idx][i]+count) continue;
                count+=link[idx][i];
                if(node[idx][i]!=0){ 
                    node[idx][i] = link[idx][i]+count;
                    node[i][idx] = link[idx][i]+count;
                    go(i, count, visit+1);
                }else {
                    node[idx][i] = link[idx][i]+count;
                    node[i][idx] = link[idx][i]+count;
                    go(i, count, visit);
                }
                count-=link[idx][i];
            }
        }
   }
}
 */
