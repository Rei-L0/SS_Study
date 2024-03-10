package prim;

import java.io.*;
import java.util.*;

/*
  [부족했던 점 CHECK !]
  0. now가 이미 visit했는지에 대한 체크를 안함.
 */

public class B_13418{

  static int N, M;
    static ArrayList<ArrayList<Node>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 1; i <= N + 1; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.get(a).add(new Node(b, c));
            list.get(b).add(new Node(a, c));
        }

        PriorityQueue<Node> pqMax = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        int max = prim(pqMax);

        PriorityQueue<Node> pqMin = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.cost, o1.cost));
        int min = prim( pqMin);

        System.out.println((int) (Math.pow(max, 2) - Math.pow(min, 2)));
    }

    private static int prim(PriorityQueue<Node> pq) {
        boolean[] visit = new boolean[N + 1];

        pq.add(new Node(0, 0));

        int cnt = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (visit[now.point]) {
                continue;
            }

            visit[now.point] = true;
            if (now.cost == 1) {
                cnt++;
            }

            for (Node next : list.get(now.point)) {
                if (!visit[next.point]) {
                    pq.add(next);
                }
            }
        }

        return N - cnt;
    }
    static class Node {
      int point;
      int cost;

      public Node(int point, int cost) {
          this.point = point;
          this.cost = cost;
      }
  }

}
