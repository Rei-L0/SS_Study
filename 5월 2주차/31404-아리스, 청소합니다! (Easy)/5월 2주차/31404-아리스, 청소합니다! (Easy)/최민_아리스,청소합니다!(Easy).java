//안풀려서 도예언니 코드 보고 품... 나중에 다시 풀어보기
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 아리스청소EASY_31404 {

    static int H, W, R, C, D, cycle, cnt = 0, ans = 0;
    static int[][] ruleA, ruleB;
    static boolean[][][][] visit;
    static int dust[][];
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        ruleA = new int[H][W];
        ruleB = new int[H][W];
        dust = new int[H][W]; //먼지 상태
        visit = new boolean[H][W][4][H * W + 1];
        for (int i = 0; i < H; i++) {
            String str = br.readLine();
            for (int j = 0; j < W; j++) {
                ruleA[i][j] = str.charAt(j) - '0';
            }
        }
        for (int i = 0; i < H; i++) {
            String str = br.readLine();
            for (int j = 0; j < W; j++) {
                ruleB[i][j] = str.charAt(j) - '0';
            }
        }
        move(R, C, D);
        System.out.println(ans - cycle);
    }

    static void move(int y, int x, int d) {
        while (true) {
            if (dust[y][x] == 0) { //현재위치에 먼지가 있으면
                dust[y][x] = 1; //청소
                d = (d + ruleA[y][x]) % 4; //방향 변경
                cnt++; //청소칸 수 증가
                cycle = 0; //사이클 초기화
            } else { //현재 위치에 먼지가 없으면
                d = (d + ruleB[y][x]) % 4; 
                if (visit[y][x][d][cnt]) return; //이미 방문했으면 그만
                visit[y][x][d][cnt] = true; //방문 처리
                cycle++; //사이클 증가
            }
            y = y + dy[d];
            x = x + dx[d];
            ans++;
            if (y < 0 || x < 0 || y >= H || x >= W) return; //방밖으로 나가면 그만
        }
    }
}
