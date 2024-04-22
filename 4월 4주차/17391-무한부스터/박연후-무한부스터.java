import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, m;

    static int[] dx = {1, 0};
    static int[] dy = {0, 1};

    static class Pos {

        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve(board));

    }

    static int solve(int[][] board) {
        Queue<Pos> q = new ArrayDeque<>();
        int[][] dis = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }
        dis[0][0] = 0;
        q.add(new Pos(0, 0));

        while (!q.isEmpty()) {
            Pos now = q.poll();
            for (int i = 0; i < 2; i++) {
                int x = now.x;
                int y = now.y;
                int cnt = board[x][y];
                while (cnt-- > 0) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                        break;
                    }
                    x = nx;
                    y = ny;
                    if (dis[nx][ny] > dis[now.x][now.y] + 1) {
                        dis[nx][ny] = dis[now.x][now.y] + 1;
                        q.add(new Pos(nx, ny));
                    }
                }
            }
        }
        return dis[n - 1][m - 1];
    }

}