import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, m;

    static final int U = 0, D = 1, L = 2, R = 3;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int[][] board;
    static boolean[][] visited;

    static List<Pos> airCon;

    static class Pos {

        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];

        airCon = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 9) {
                    airCon.add(new Pos(i, j));
                }
            }
        }
        visited = new boolean[n][m];
        for (int i = 0; i < airCon.size(); i++) {
            spread(airCon.get(i));
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j]) {
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }

    static void spread(Pos start) {
        visited[start.x][start.y] = true;

        for (int k = 0; k < 4; k++) {
            int x = start.x;
            int y = start.y;
            int d = k;
            while (true) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    break;
                }
                if (board[nx][ny] == 1) {
                    if (d == L || d == R) {
                        visited[nx][ny] = true;
                        break;
                    }
                } else if (board[nx][ny] == 2) {
                    if (d == U || d == D) {
                        visited[nx][ny] = true;
                        break;
                    }
                } else if (board[nx][ny] == 3) {
                    if (d == L) {
                        d = D;
                    } else if (d == R) {
                        d = U;
                    } else if (d == U) {
                        d = R;
                    } else {
                        d = L;
                    }
                } else if (board[nx][ny] == 4) {
                    if (d == L) {
                        d = U;
                    } else if (d == R) {
                        d = D;
                    } else if (d == U) {
                        d = L;
                    } else {
                        d = R;
                    }
                } else if (board[nx][ny] == 9) {
                    break;
                }
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                }
                x = nx;
                y = ny;
            }
        }
    }

}