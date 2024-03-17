import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        int node, time;

        Edge(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int t = 1; t <= M; t++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Edge(b, t));
            graph.get(b).add(new Edge(a, t));
        }

        long INF = Long.MAX_VALUE;
        boolean[] visited = new boolean[N + 1];
        long[] table = new long[N + 1];
        Arrays.fill(table, INF);
        table[1] = 0;

        PriorityQueue<long[]> heap = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        for (Edge edge : graph.get(1)) {
            table[edge.node] = edge.time;
            heap.offer(new long[]{edge.time, edge.node, edge.time});
        }

        if (table[N] <= M) {
            System.out.println(table[N]);
            return;
        }

        while (!heap.isEmpty()) {
            long[] current = heap.poll();
            long cumul_time = current[0];
            int node = (int) current[1];
            long t = current[2];

            if (node == N) { //도착 노드에 도착
                System.out.println(cumul_time);
                break;
            }
            if (visited[node]) { //방문했으면 패스
                continue;
            }
            visited[node] = true;

            for (Edge next : graph.get(node)) {
                int nextNode = next.node;
                long nextTime = next.time;
                if (visited[nextNode]) {
                    continue;
                }
                long time = nextTime - cumul_time % M;
                if (time < 0) {
                    time += M;
                }
                if (time + cumul_time < table[nextNode]) {
                    table[nextNode] = time + cumul_time;
                    heap.offer(new long[]{table[nextNode], nextNode, nextTime});
                }
            }
        }
    }
}
