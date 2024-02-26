
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class 박연후_무엇을아느냐가아니라누구를아느냐가문제다 {
    static int t;


    static class Node implements Comparable<Node> {

        int to;
        int w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] distance = new int[m];
            int[] route = new int[m];

            ArrayList<ArrayList<Node>> graph = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                graph.get(s).add(new Node(e, w));
                graph.get(e).add(new Node(s, w));
            }

            Arrays.fill(distance, Integer.MAX_VALUE);
            Arrays.fill(route, -1);

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(0, 0));
            distance[0] = 0;
            route[0] = 0;
            while (!pq.isEmpty()) {
                Node now = pq.poll();
                if (distance[now.to] < now.w) {
                    continue;
                }
                for (Node next : graph.get(now.to)) {
                    if (distance[next.to] > now.w + next.w) {
                        distance[next.to] = now.w + next.w;
                        route[next.to] = now.to;
                        pq.add(new Node(next.to, distance[next.to]));
                    }
                }
            }
            System.out.print("Case #" + tc + ": ");
            if (route[m - 1] == -1) {
                System.out.print(-1 + "\n");
            } else {
                int x = m - 1;
                ArrayList<Integer> arr = new ArrayList<>();
                while (x != 0) {
                    arr.add(x);
                    x = route[x];
                }
                arr.add(0);
                for (int i = arr.size() - 1; i >= 0; i--) {
                    System.out.print(arr.get(i) + " ");
                }
                System.out.println();
            }
        }

    }
}