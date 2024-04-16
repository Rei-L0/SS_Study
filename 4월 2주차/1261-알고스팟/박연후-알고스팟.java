import java.io.*;
import java.util.*;

public class Main {

    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int m, n;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int[][] board, dis;

    static class Pos {

        int x;
        int y;
        int d;

        Pos(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        dis = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            Arrays.fill(dis[i], Integer.MAX_VALUE);
            for (int j = 0; j < m; j++) {
                board[i][j] = s.charAt(j) - '0';
            }
        }

        dijk();

        System.out.println(dis[n - 1][m - 1]);
    }

    static void dijk() {
        PriorityQueue<Pos> pq = new PriorityQueue<Pos>(Comparator.comparing(o -> o.d));
        pq.add(new Pos(0, 0, 0));
        dis[0][0] = 0;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (board[nx][ny] == 1) {
                    if (dis[nx][ny] > dis[now.x][now.y] + 1) {
                        dis[nx][ny] = dis[now.x][now.y] + 1;
                        pq.add(new Pos(nx, ny, dis[nx][ny]));
                    }
                } else {
                    if (dis[nx][ny] > dis[now.x][now.y]) {
                        dis[nx][ny] = dis[now.x][now.y];
                        pq.add(new Pos(nx, ny, dis[nx][ny]));
                    }
                }
            }
        }
    }
}