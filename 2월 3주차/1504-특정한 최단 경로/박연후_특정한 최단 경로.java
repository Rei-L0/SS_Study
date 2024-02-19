import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 1번 정점 -> 첫 번째 정점 -> 두 번째 정점 -> n번 정점
// 1번 정점 -> 두 번째 정점 -> 첫 번째 정점 -> n번 정점
// 두 가지 경우를 다익스트라 알고리즘으로 탐색 후 나온 최소값이 정답
public class Main {

    static int n, e, v1, v2;
    static long ans = 0;
    static ArrayList<ArrayList<Node>> arrayList;

    static class Node implements Comparable<Node> {

        int to;
        long c;

        public Node(int to, long c) {
            this.to = to;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.c, o.c);
        }
    }

    static long solve() {
        ans = Math.min(dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, n),
            dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, n));
        return ans >= Integer.MAX_VALUE ? -1 : ans;
    }

    static long dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        long[] distance = new long[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (distance[now.to] < now.c) {
                continue;
            }
            for (int i = 0; i < arrayList.get(now.to).size(); i++) {
                Node next = arrayList.get(now.to).get(i);
                if (distance[next.to] > now.c + next.c) {
                    distance[next.to] = now.c + next.c;
                    pq.add(new Node(next.to, distance[next.to]));
                }
            }
        }
        return distance[end];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bReader.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        arrayList = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            arrayList.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(bReader.readLine());
            int s = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            arrayList.get(s).add(new Node(end, w));
            arrayList.get(end).add(new Node(s, w));
        }

        st = new StringTokenizer(bReader.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        System.out.println(solve());

    }
}