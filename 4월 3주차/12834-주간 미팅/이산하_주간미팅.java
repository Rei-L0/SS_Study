import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.MAX_VALUE;

public class Main {
    static int[] dist;
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int V = parseInt(st.nextToken());
        int E = parseInt(st.nextToken());

        int[] starts = new int[N];
        dist = new int[V + 1];
        for (int i = 0; i <= V; i++)
            graph.add(new ArrayList<>());

        int A, B;
        st = new StringTokenizer(br.readLine());
        A = parseInt(st.nextToken());
        B = parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < starts.length; i++)
            starts[i] = parseInt(st.nextToken());

        int a, b, l;
        while (E-- > 0) {
            st = new StringTokenizer(br.readLine());
            a = parseInt(st.nextToken());
            b = parseInt(st.nextToken());
            l = parseInt(st.nextToken());

            graph.get(a).add(new Node(b, l));
            graph.get(b).add(new Node(a, l));
        }

        int result = 0;
        for (int start : starts) {
            dijkstra(start);
            result += dist[A] == MAX_VALUE ? -1 : dist[A];
            result += dist[B] == MAX_VALUE ? -1 : dist[B];
        }
        System.out.println(result);
        br.close();
    }

    static void dijkstra(int start) {
        Arrays.fill(dist, MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.w));
        dist[start] = 0;
        pq.offer(new Node(start, dist[start]));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.v] < cur.w)
                continue;

            for (Node next : graph.get(cur.v)) {
                if (dist[next.v] < dist[cur.v] + next.w)
                    continue;

                dist[next.v] = dist[cur.v] + next.w;
                pq.offer(new Node(next.v, dist[next.v]));
            }
        }
    }

    static class Node {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
