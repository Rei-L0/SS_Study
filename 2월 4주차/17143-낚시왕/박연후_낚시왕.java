import  java.util.*;
import  java.io.*;
public class 박연후_낚시왕 {
    static int r, c, m, ans;

    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 1, -1};

    static LinkedHashMap<Integer, Shark> map = new LinkedHashMap<>();
    static ArrayDeque<Integer> remove = new ArrayDeque<>();
    static int[][] board;

    static class Shark {

        int num;
        int x;
        int y;
        int s;
        int d;
        int size;

        public Shark(int num, int x, int y, int s, int d, int size) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.s = s;
            this.d = d;
            this.size = size;
        }
    }

    // map 순회 후 땅과 가장 가까운 상어의 index 찾기
    // 해당 index 상어 크기 return
    // 해당 index 상어 map 에서 삭제
    static int solve(int y) {
        int index = -1;
        for (int i = 1; i <= r; i++) {
            if (board[i][y] != 0) {
                index = board[i][y];
                break;
            }
        }
        if (index == -1) {
            return 0;
        }
        int count = map.get(index).size;
        map.remove(index);
        return count;
    }

    static void move() {
        board = new int[r + 1][c + 1];
        // 상어 전부를 움직인다.
        for (Integer key : map.keySet()) {
            Shark now = map.get(key);
            int x = now.x;
            int y = now.y;
            int d = now.d;
            for (int i = 0; i < now.s; i++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (isOut(nx, ny)) {
                    d = change(d);
                    nx = x + dx[d];
                    ny = y + dy[d];
                }
                x = nx;
                y = ny;
            }
            Shark newShark = new Shark(now.num, x, y, now.s, d, now.size);
            map.put(key, newShark);
            // 상어 크기 비교 후 작은 상어 제거
            if (board[x][y] != 0) {
                Shark oldShark = map.get(board[x][y]);
                if (oldShark.size > newShark.size) {
                    remove.add(newShark.num);
                } else {
                    remove.add(oldShark.num);
                    board[x][y] = newShark.num;
                }
            } else {
                board[x][y] = now.num;
            }
        }
        while (!remove.isEmpty()) {
            map.remove(remove.poll());
        }
    }

    // 벽으로 나가는지 확인하는 메서드
    static boolean isOut(int x, int y) {
        return (x <= 0 || y <= 0 || x > r || y > c);
    }

    // 방향 바꿔주는 메서드
    static int change(int x) {
        if (x == 1) {
            return 2;
        }
        if (x == 2) {
            return 1;
        }
        if (x == 3) {
            return 4;
        }
        return 3;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[r + 1][c + 1];
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            // 상어의 방향에 따라 mod 연산으로 속도를 줄여서 계산한다.
            if (d == 1 || d == 2) {
                map.put(i, new Shark(i, x, y, s % ((r - 1) * 2), d, size));
            } else {
                map.put(i, new Shark(i, x, y, s % ((c - 1) * 2), d, size));
            }
            board[x][y] = i;
        }

        for (int i = 1; i <= c; i++) {
            ans += solve(i);
            move();
        }
        System.out.println(ans);
    }
}
