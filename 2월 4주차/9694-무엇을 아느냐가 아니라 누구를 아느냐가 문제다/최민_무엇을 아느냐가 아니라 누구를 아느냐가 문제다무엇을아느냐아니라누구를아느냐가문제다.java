import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static List<List<Edge>> list = new ArrayList<>();
    static int[] cost;
    static int[] result;
    static int m;
    static StringBuilder sb = new StringBuilder();

    static class Edge {
        int end;
        int weight;

        public Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            list.clear();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            cost = new int[m];
            result = new int[m];
            for (int i = 0; i < m; i++) {
                list.add(new ArrayList<>());
                cost[i] = Integer.MAX_VALUE;
            }

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                list.get(v1).add(new Edge(v2, weight));
                list.get(v2).add(new Edge(v1, weight));
            }

            dijkstra();

            sb.append("Case #").append(t).append(": ");
            if (cost[m - 1] == Integer.MAX_VALUE) {
                sb.append("-1").append("\n");
            } else {
                printPath();
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((n1, n2) -> n1.weight - n2.weight);
        pq.offer(new Edge(0, 0));
        cost[0] = 0;

        while (!pq.isEmpty()) {
            Edge p = pq.poll();

            List<Edge> li = list.get(p.end);
            for (Edge edge : li) {
                if (cost[edge.end] > cost[p.end] + edge.weight) {
                    cost[edge.end] = cost[p.end] + edge.weight;
                    pq.offer(new Edge(edge.end, cost[edge.end]));
                    result[edge.end] = p.end;
                }
            }
        }
    }

    static void printPath() {
        List<Integer> path = new ArrayList<>();
        int node = m - 1;
        while (node != 0) { //직전노드가 0이 될때까지
            path.add(node);
            node = result[node];
        }
        path.add(0);

        for (int i = path.size() - 1; i >= 0; i--) {
            sb.append(path.get(i)).append(" ");
        }
    }
}
