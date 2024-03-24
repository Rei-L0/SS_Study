//슬 다익스트라 공식 외울때 됬다~ 외우자 좀~!
//풀이는 다른 사람 참고함 나중에 혼자 다시 풀어보기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node> {
        int city;
        long distance;

        public Node(int city, long distance) {
            this.city = city;
            this.distance = distance;
        }

        public int compareTo(Node other) {
            return Long.compare(this.distance, other.distance);
        }
    }

    static List<List<Node>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //도시 개수
        int M = Integer.parseInt(st.nextToken()); //도로 개수
        int A = Integer.parseInt(st.nextToken()); //민겸 살고 있는 도시
        int B = Integer.parseInt(st.nextToken()); //시은 살고 있는 도시

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        List<Integer> meetCities = findMeetingCities(N, A, B);

        System.out.println(meetCities.size());
        for (int city : meetCities) {
            System.out.print(city + " ");
        }
    }
    
    static long[] dijkstra(int start, int N) {
        long[] distances = new long[N + 1];
        Arrays.fill(distances, Long.MAX_VALUE);
        distances[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.distance > distances[current.city]) continue;

            for (Node c : graph.get(current.city)) {
                long distance = current.distance + c.distance;

                if (distance < distances[c.city]) {
                    distances[c.city] = distance;
                    pq.offer(new Node(c.city, distance));
                }
            }
        }

        return distances;
    }

    static List<Integer> findMeetingCities(int N, int A, int B) {
        long[] startA = dijkstra(A, N); //a에서 출발하는 최단 거리 배열
        long[] startB = dijkstra(B, N); //b

        long shortd = startA[B];

        List<Integer> meetCities = new ArrayList<>();
        for (int city = 1; city <= N; city++) {
            if (startA[city] + startB[city] == shortd) {
            	meetCities.add(city);
            }
        }

        return meetCities;
    }
}
