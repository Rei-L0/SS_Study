import java.io.*;
import java.util.*;

public class B_22865 {
  
  static ArrayList<ArrayList<Node>> nodes;
  static long[] min_dis;
  static long INF;
  static int N, M, A, B, C;
  
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());

    st = new StringTokenizer(br.readLine());
    A = Integer.parseInt(st.nextToken());
    B = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    M = Integer.parseInt(br.readLine());

    nodes = new ArrayList<ArrayList<Node>>();
    INF = 10000 * 500000 + 1;
    long max_v = Long.MIN_VALUE;
    int result = 0;

    for (int i = 0; i <= N; i++) {
      nodes.add(new ArrayList<>());
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      nodes.get(s).add(new Node(e, c));
      nodes.get(e).add(new Node(s, c));
    }

    long[] aStart = dijkstra(A);
    long[] bStart = dijkstra(B);
    long[] cStart = dijkstra(C);

    for (int i = 1; i <= N; i++) {
      long tmp = Math.min(aStart[i], Math.min(bStart[i], cStart[i]));
      if (tmp > max_v) {
        max_v = tmp;
        result = i;
      }
    }
    System.out.println(result);

  }

  static long[] dijkstra(int start) {

    min_dis = new long[N + 1];
    Arrays.fill(min_dis, INF);

    min_dis[start] = 0;

    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.offer(new Node(start, 0));

    while (!pq.isEmpty()) {
      Node cur = pq.poll();

      if (min_dis[cur.node] < cur.cost)
        continue;

      for (Node next : nodes.get(cur.node)) {
        if (min_dis[next.node] > cur.cost + next.cost) {
          min_dis[next.node] = cur.cost + next.cost;
          pq.offer(new Node(next.node, cur.cost + next.cost));
        }
      }
    }
    return min_dis;
  }

  static class Node implements Comparable<Node> {
    int node;
    long cost;

    Node(int node, long cost) {
      this.node = node;
      this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
      return (int) ((int) this.cost - o.cost);
    }
  }
}
