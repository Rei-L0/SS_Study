import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, a, b, c, m, idx;

    static long res;

    static long[] ans;

    static List<List<Edge>> graph;

    static class Edge implements Comparable<Edge> {

        int to;
        long w;

        public Edge(int to, long w) {
            super();
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.w, o.w);
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        ans = new long[n + 1];

        Arrays.fill(ans, Long.MAX_VALUE);

        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(s).add(new Edge(e, w));
            graph.get(e).add(new Edge(s, w));
        }

        dijk(a);
        dijk(b);
        dijk(c);

        for (int i = 1; i < n + 1; i++) {
            if (i == a || i == b || i == c) {
                continue;
            }
            if (res < ans[i]) {
                res = ans[i];
                idx = i;
            }
        }
        System.out.println(idx);
    }

    static void dijk(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        long[] dis = new long[n + 1];
        Arrays.fill(dis, Long.MAX_VALUE);

        dis[start] = 0;

        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (dis[now.to] < now.w) {
                continue;
            }
            for (Edge next : graph.get(now.to)) {
                if (dis[next.to] > now.w + next.w) {
                    dis[next.to] = now.w + next.w;
                    pq.add(new Edge(next.to, dis[next.to]));
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            if (dis[i] == 0) {
                continue;
            }
            ans[i] = Math.min(ans[i], dis[i]);
        }
    }

}