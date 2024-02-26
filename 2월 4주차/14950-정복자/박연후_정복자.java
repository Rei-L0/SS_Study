import java.util.*;
import java.io.*;

public class 박연후_정복자 {
    static int n, m, t, ans;

    static int[] p, size;
    static PriorityQueue<Edge> pq;

    static class Edge implements Comparable<Edge> {

        int s;
        int e;
        int w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static boolean union(int x, int y) {
        int pX = find(x);
        int pY = find(y);
        if (pX == pY) {
            return false;
        }
        if (size[pX] > size[pY]) {
            size[pX] += size[pY];
            p[pY] = p[pX];
        } else {
            size[pY] += size[pX];
            p[pX] = p[pY];
        }
        return true;
    }

    static int find(int x) {
        if (p[x] == x) {
            return x;
        }
        return p[x] = find(p[x]);
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        p = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            p[i] = i;
        }
        size = new int[n + 1];
        Arrays.fill(size, 1);

        pq = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.add(new Edge(s, e, w));
        }

        int count = 0;

        while (count != n - 1) {
            Edge now = pq.poll();
            if (union(now.s, now.e)) {
                ans += now.w + (t * count);
                count++;
            }
        }
        System.out.println(ans);
    }
}
