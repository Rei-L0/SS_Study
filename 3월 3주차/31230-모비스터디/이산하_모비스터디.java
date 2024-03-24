import java.io.*;
import java.util.*;

class Main { 
   static int N,M,A,B;
   static long INF;
   static ArrayList<ArrayList<Pos>> arr = new ArrayList<ArrayList<Pos>>();
   public static void main(String[] args) throws Exception{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      StringBuilder sb = new StringBuilder();

      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      A = Integer.parseInt(st.nextToken());
      B = Integer.parseInt(st.nextToken());


      INF = Long.MAX_VALUE;
      for (int i=0; i<=N; i++) {
         arr.add(new ArrayList<>());
      }

      for (int i=0; i<M; i++) {
         st = new StringTokenizer(br.readLine());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int c = Integer.parseInt(st.nextToken());
         arr.get(a).add(new Pos(b,c));
         arr.get(b).add(new Pos(a,c));                              
      }

      long[] fromA = new long[N+1];
      long[] fromB = new long[N+1];
      dijkstra(fromA,A);
      dijkstra(fromB,B);
      long shortest = fromA[B]; 

      int cnt = 0;
      for (int i=0; i<=N; i++) {
         if (i == A || i ==B) {
            sb.append(i).append(" ");
            cnt++;
         }
         else {
            if (fromA[i] + fromB[i] == shortest) {
               sb.append(i).append(" ");
               cnt++;
            }
         }
      }

      System.out.println(cnt);
      System.out.println(sb);
   }

   public static void dijkstra(long[] min_dis, int start){
      PriorityQueue<Pos> pq = new PriorityQueue<>();
      Arrays.fill(min_dis, INF);
      pq.offer(new Pos(start,0));

      while (!pq.isEmpty()){
         Pos cur = pq.poll();

         if (min_dis[cur.node] < cur.cost) continue;

         for(Pos next: arr.get(cur.node)) {
            if (min_dis[next.node] > cur.cost + next.cost) {
               min_dis[next.node] = cur.cost + next.cost;
               pq.offer(new Pos(next.node,cur.cost+next.cost));
            }
         }
      }
   }
}

class Pos implements Comparable<Pos>{
   int node;
   long cost;
   public Pos(int node, long cost) {
      this.node = node;
      this.cost = cost;
   }
   @Override
   public int compareTo(Pos o) {
      return (int) ( (int) this.cost - o.cost);
   }
}
