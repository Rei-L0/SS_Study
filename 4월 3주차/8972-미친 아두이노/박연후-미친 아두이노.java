import java.io.*;
import java.util.*;

public class Main {

    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int r, c;

    static Pos jongsu;

    static String dir;

    static boolean[] remove;

    static int[] dx = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dy = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};

    static char[][] board;

    static Queue<Pos> q;

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
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        q = new ArrayDeque<>();

        board = new char[r][c];
        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = s.charAt(j);
                if (board[i][j] == 'I') {
                    jongsu = new Pos(i, j);
                }
                if (board[i][j] == 'R') {
                    q.offer(new Pos(i, j));
                }
            }
        }

        dir = br.readLine();
        int time = 0;

        while (time != dir.length()) {
            if (!move(dir.charAt(time) - '0')) {
                break;
            }
            time++;
        }

        if (time == dir.length()) {
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    sb.append(board[i][j]);
                }
                sb.append("\n");
            }
        } else {
            sb.append("kraj ").append(time + 1);
        }
        System.out.print(sb);
    }

    static boolean move(int idx) {
        board[jongsu.x][jongsu.y] = '.';
        int nx = jongsu.x + dx[idx];
        int ny = jongsu.y + dy[idx];
        if (board[nx][ny] == 'R') {
            return false;
        }
        board[nx][ny] = 'I';

        jongsu = new Pos(nx, ny);

        int[][] count = new int[r][c];

        while (!q.isEmpty()) {
            Pos adu = q.poll();
            board[adu.x][adu.y] = '.';
            int val = Integer.MAX_VALUE;
            int index = 0;

            for (int i = 0; i < dx.length; i++) {
                nx = adu.x + dx[i];
                ny = adu.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= r || ny >= c) {
                    continue;
                }
                if (dis(new Pos(nx, ny), jongsu) < val) {
                    val = dis(new Pos(nx, ny), jongsu);
                    index = i;
                }
            }

            nx = adu.x + dx[index];
            ny = adu.y + dy[index];
            count[nx][ny]++;
            if (board[nx][ny] == 'I') {
                return false;
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (count[i][j] == 1) {
                    board[i][j] = 'R';
                    q.offer(new Pos(i, j));
                }
            }
        }

        return true;
    }

    static int dis(Pos a, Pos b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}