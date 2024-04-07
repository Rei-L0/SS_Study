import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//MST만 할게 아니었음
//모든 정점들을 연결할 수 있는 경우 중, 가장 높은 가중치의 합을 크루스칼로 구한 후, 출발노드에서 하여금 다시 한번 BFS나 DFS를 통해 가중치의 최솟값을 구해야한다.
//다른 스터디원들은 다익스트라 사용한듯 나중에 이것도 풀이 참고해보기
public class bj_13905_세부 {
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 섬에 존재하는 집의 수
        int M = Integer.parseInt(st.nextToken()); // 다리의 수

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()); // 숭이의 출발 위치
        int end = Integer.parseInt(st.nextToken()); // 혜빈이의 위치

        Edge[] edges = new Edge[M]; // 다리 정보 배열

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int h1 = Integer.parseInt(st.nextToken());
            int h2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(h1, h2, weight);
        }

        //내림차순 정렬
        Arrays.sort(edges, (e1, e2) -> e2.weight - e1.weight);

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        int maxWeight = Integer.MAX_VALUE; 
        for (Edge edge : edges) {
            int rootA = find(edge.from);
            int rootB = find(edge.to);

            if (rootA != rootB) {
                union(rootA, rootB);
            }

            if (find(start) == find(end)) {
                maxWeight = edge.weight;
                break;
            }
        }
        
        int count = Math.min(maxWeight, Math.abs(start - end) - 1);
        System.out.println(count);
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }

    static class Edge {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
