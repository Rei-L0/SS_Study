import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 23807
public class Main {

    static final long MAX = Long.MAX_VALUE;

    static int n, m, x, z, p;

    static long ans = MAX;

    static int[] nodeIdx;

    static List<List<Edge>> graph;
    static Map<Integer, long[]> disMap = new HashMap<>();

    static StringTokenizer st;
    static StringBuilder sb;

    static class Edge implements Comparable<Edge> {

        int e;
        long w;

        public Edge(int e, long w) {
            this.e = e;
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
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(s).add(new Edge(e, w));
            graph.get(e).add(new Edge(s, w));
        }

        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        z = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());

        nodeIdx = new int[p];
        st = new StringTokenizer(br.readLine());

        disMap.put(x, new long[n + 1]);

        dijk(x);
        boolean[] visit = new boolean[n + 1];
        visit[x] = true;

        for (int i = 0; i < p; i++) {
            nodeIdx[i] = Integer.parseInt(st.nextToken());
            disMap.put(nodeIdx[i], new long[n + 1]);
            dijk(nodeIdx[i]);
        }

        for (int i = 0; i < p; i++) {
            int now = nodeIdx[i];
            solve(0, x, 0, visit);
        }
        System.out.println((ans == MAX) ? -1 : ans);
    }

    static void dijk(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        long[] dis = disMap.get(start);

        Arrays.fill(dis, MAX);

        dis[start] = 0;
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (dis[now.e] < now.w) {
                continue;
            }
            for (Edge next : graph.get(now.e)) {
                if (dis[next.e] > now.w + next.w) {
                    dis[next.e] = now.w + next.w;
                    pq.add(new Edge(next.e, dis[next.e]));
                }
            }
        }
        disMap.put(start, dis);
    }

    static void solve(int cnt, int before, long res, boolean[] visit) {
        if (res > ans) {
            return;
        }
        if (cnt == 3) {
            if (disMap.get(before)[z] == MAX) {
                return;
            }
            ans = Math.min(res + disMap.get(before)[z], ans);
            return;
        }
        for (int i = 0; i < p; i++) {
            int now = nodeIdx[i];
            if (visit[now] || disMap.get(before)[now] == MAX) {
                continue;
            }
            visit[now] = true;
            res += disMap.get(before)[now];
            solve(cnt + 1, now, res, visit);
            res -= disMap.get(before)[now];
            visit[now] = false;
        }
    }
}