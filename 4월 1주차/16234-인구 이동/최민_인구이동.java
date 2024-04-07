import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static int[][] area;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};
    static int days = 0; // 인구 이동이 발생한 날짜

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new boolean[N][N];
        area = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 입력 받기

        while (true) {
            boolean moved = false; 
            visited = new boolean[N][N]; 

            // 모든 곳 bfs 
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) { //방문하지 않은 나라에 대해 BFS 실행
                        if (bfs(i, j)) { // 결과값이 true면 인구 이동 발생
                            moved = true;
                        }
                    }
                }
            }

            if (!moved) { // 인구 이동이 발생x
                break;
            }

            days++; // 발생시 날짜 증가
        }

        System.out.println(days);
    }


    static boolean bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        ArrayList<int[]> union = new ArrayList<>(); // 연합에 속한 나라의 좌표 저장
        int total = 0; // 연합의 총 인구 수

        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        union.add(new int[]{x, y});
        total += map[x][y];

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    int diff = Math.abs(map[cx][cy] - map[nx][ny]);
                    if (diff >= L && diff <= R) { 
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                        union.add(new int[]{nx, ny});
                        total += map[nx][ny];
                    }
                }
            }
        }

        if (union.size() > 1) { // 연합에 속한 나라가 2개 이상이면 인구 이동 발생
            int avg = total / union.size();
            for (int[] country : union) {
                int cx = country[0];
                int cy = country[1];
                map[cx][cy] = avg; 
            }
            return true; // 인구 이동 발생
        }

        return false; // 인구 이동이 발생X
    }
}
