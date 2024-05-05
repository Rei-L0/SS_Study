import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    static class Position {
        int x, y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int R, C, N;
    static char[][] cave;
    static int[] H;
    static int[] dx = {0, -1, 1, 0};
    static int[] dy = {-1, 0, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        cave = new char[R][C];
        for (int i = 0; i < R; i++) {
            cave[i] = br.readLine().toCharArray();
        }

        N = Integer.parseInt(br.readLine());
        H = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            H[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            throwStick(H[i], i);
        }

        printCave();
    }

    static void throwStick(int height, int turn) {
        int start, end, direction;
        if (turn % 2 == 0) {
            start = 0;
            end = C;
            direction = 1;
        } else {
            start = C - 1;
            end = -1;
            direction = -1;
        }

        int x = -1;
        for (int j = start; j != end; j += direction) {
            if (cave[height][j] == 'x') {
                x = j;
                break;
            }
        }

        if (x != -1) {
            cave[height][x] = '.';
            for (int j = 0; j < 4; j++) {
                int tempX = x + dx[j];
                int tempY = height + dy[j];
                if (inCaveCheck(tempX, tempY) && cave[tempY][tempX] == 'x') {
                    bfs(tempX, tempY);
                }
            }
        }
    }

    static void bfs(int x, int y) {
        ArrayList<Position> list = new ArrayList<>();
        Queue<Position> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        visited[y][x] = true;
        list.add(new Position(x, y));
        queue.add(new Position(x, y));

        while (!queue.isEmpty()) {
            Position cur = queue.poll();
            if (cur.y == 0) {
                return;
            }
            for (int i = 0; i < 4; i++) {
                int tempX = cur.x + dx[i];
                int tempY = cur.y + dy[i];
                if (inCaveCheck(tempX, tempY) && !visited[tempY][tempX] && cave[tempY][tempX] == 'x') {
                    visited[tempY][tempX] = true;
                    list.add(new Position(tempX, tempY));
                    queue.add(new Position(tempX, tempY));
                }
            }
        }

        for (Position pos : list) {
            int tempX = pos.x;
            int tempY = pos.y;
            cave[tempY][tempX] = '.';
        }

        mineralDown(list);
    }

    static void mineralDown(ArrayList<Position> list) {
        boolean check = true;
        while (check) {
            for (Position pos : list) {
                int x = pos.x;
                int y = pos.y - 1;
                if (y == -1 || cave[y][x] == 'x') {
                    check = false;
                    break;
                }
                pos.y = y;
            }
        }

        for (Position pos : list) {
            cave[pos.y + 1][pos.x] = 'x';
        }
    }

    static boolean inCaveCheck(int x, int y) {
        return x >= 0 && x < C && y >= 0 && y < R;
    }

    static void printCave() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(cave[i][j]);
            }
            System.out.println();
        }
    }
}
