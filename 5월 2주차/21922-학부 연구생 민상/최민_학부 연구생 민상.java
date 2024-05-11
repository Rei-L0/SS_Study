// 시간초과 해결기
// 1. boolen visit -> int 실패
// 2. 방향 바꾸는 부분 더 간결해보이는 거 참고해서 바꾸기 X
// 3. 에어컨 만나면 break 해주지 않아서 시간 초과였음!! 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_21922_학부연구생민상 {
    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] graph;
    static int[][] visited ;
    static Queue<Pair> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 9) {
                    queue.add(new Pair(i, j));
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        int[] xd = {-1, 1, 0, 0};
        int[] yd = {0, 0, -1, 1};
        int n = graph.length;
        int m = graph[0].length;
        int result = 0;

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            int x = current.x;
            int y = current.y;

            for (int i = 0; i < 4; i++) {
                int nx = xd[i];
                int ny = yd[i];
                int r = x;
                int c = y;
                int count=0;
                while (0 <= r && r < n && 0 <= c && c < m) {
                	if(count!=0 && graph[r][c]==9) break;
                	count++;
                	visited[r][c] = 1;
                    if ((graph[r][c] == 1 && nx == 0) || (graph[r][c] == 2 && ny == 0)) {
                        break;
                    } else if (graph[r][c] == 3) {
                        int temp = nx;
                        nx = -ny;
                        ny = -temp;
                    } else if (graph[r][c] == 4) {
                        int temp = nx;
                        nx = ny;
                        ny = temp;
                    }
                    r += nx;
                    c += ny;
                    
                }
            }
        }

        for (int[] row : visited) {
            for (int val : row) {
                result += val;
            }
        }

        return result;
    }
}
