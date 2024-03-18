import java.io.*;
import java.util.*;

// MST 문제
// 크루스칼 알고리즘 사용

public class Main {

    static int n, m, ans;

    static int[] p;

    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    static class Edge implements Comparable<Edge> {

        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(o.cost, this.cost);
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        p = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            p[i] = i;
        }

        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if (d == 1) {
                union(a, b);
            } else {
                pq.add(new Edge(a, b, c));
            }
        }

        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            if (union(e.from, e.to)) {
                continue;
            }
            ans += e.cost;
        }

        System.out.println(ans);

    }

    static int find(int x) {
        if (p[x] == x) {
            return x;
        }
        return p[x] = find(p[x]);
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return false;
        }
        p[x] = p[y];
        return true;
    }
}