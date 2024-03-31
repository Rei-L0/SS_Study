import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m, r, ans;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int[][] map;
    static boolean[][] check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        check = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < r; i++) {
            // 공격
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            char c = st.nextToken().charAt(0);

            if (!check[x][y]) {
                check[x][y] = true;
                ans++;
                attack(x, y, dir(c), map[x][y]);
            }

            // 수비
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken()) - 1;
            y = Integer.parseInt(st.nextToken()) - 1;
            check[x][y] = false;
        }

        System.out.println(ans);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (check[i][j]) {
                    sb.append('F').append(' ');
                } else {
                    sb.append('S').append(' ');
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);

    }

    static void attack(int x, int y, int d, int cnt) {
        while (cnt-- > 1) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                return;
            }
            x = nx;
            y = ny;
            if (check[nx][ny]) {
                continue;
            }
            check[nx][ny] = true;
            attack(nx, ny, d, map[nx][ny]);
            ans++;
        }
    }

    static int dir(char c) {
        if (c == 'E') {
            return 0;
        }
        if (c == 'W') {
            return 1;
        }
        if (c == 'S') {
            return 2;
        }
        return 3;
    }
}