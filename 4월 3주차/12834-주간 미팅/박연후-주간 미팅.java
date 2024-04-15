import java.io.*;
import java.util.*;

public class Main {

    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, v, e, a, b;
    static long ans;

    static int[] h;

    static List<List<Edge>> graph = new ArrayList<>();

    static class Edge {

        int to;
        int w;

        public Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < v + 1; i++) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        h = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(s).add(new Edge(e, w));
            graph.get(e).add(new Edge(s, w));
        }

        int[] kist = dijk(a);
        int[] crFood = dijk(b);

        for (int i = 0; i < n; i++) {
            ans += ((kist[h[i]] == Integer.MAX_VALUE) ? -1
                : kist[h[i]]) + ((crFood[h[i]] == Integer.MAX_VALUE) ? -1 : crFood[h[i]]);
        }
        System.out.println(ans);
    }

    static int[] dijk(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.w));
        pq.add(new Edge(start, 0));

        int[] dis = new int[v + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[start] = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (now.w > dis[now.to]) {
                continue;
            }
            for (Edge next : graph.get(now.to)) {
                if (dis[next.to] > now.w + next.w) {
                    dis[next.to] = now.w + next.w;
                    pq.add(new Edge(next.to, dis[next.to]));
                }
            }
        }
        return dis;
    }
}