import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int r, c, k, ans;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static char[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new char[r][c];
        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        boolean[][] visit = new boolean[r][c];
        visit[r - 1][0] = true;
        dfs(r - 1, 0, 1, visit);

        System.out.println(ans);
    }

    static void dfs(int x, int y, int d, boolean[][] visit) {
        if (x == 0 && y == (c - 1)) {
            if (d == k) {
                ans++;
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= r || ny < 0 || ny >= c || visit[nx][ny] || board[nx][ny] == 'T') {
                continue;
            }
            visit[nx][ny] = true;
            dfs(nx, ny, d + 1, visit);
            visit[nx][ny] = false;
        }
    }
}