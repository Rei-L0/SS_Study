package live14;
import java.util.*;
import java.io.*;

/*
- Checkpoints

1. 빡구현문제였는데, 첫 구조를 잘짜는 게 진짜 중요한 거 같음. 하면서 문제 계속 읽고, 조건 계속 수정헀음.

2. 상어가 이동하는 걸 반복문으로 구현했는데, s를 입력 값 그대로 쓰면 시간초과 발생 =>  나머지 연산으로 줄여야 함 

3. 체감난이도: 상. 그냥 어려웠습니다. 
*/

class stress {
    static int R, C, M;
    static Shark[][] sharks;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args)  throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sharks = new Shark[R][C]; // 베열은 0부터 시작 

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1; // 0부터 시작하니까 1을 빼줌 
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1; // 방향도 1부터 시작하는데, 0부터 보기 위해서 1을 빼줌 
            int z = Integer.parseInt(st.nextToken());
            
            if((d) <= 1){  // 방향 인덱스가 0부터 시작하니까 d 기준도 0,1 and 2,3으로 해줘야 함 ! 
                s = s%((R-1)*2);
            }else{
                s = s%((C-1)*2);
            }

            sharks[r][c] = new Shark(r, c, s, d, z); // 배열에 상어 정보 넣어주기 
        }

        int ans = solve();
        System.out.println(ans);
    }

    public static int solve() { // 1,2 낚시왕 이동, 상어 잡기
        int totalCaught = 0;
        for (int col = 0; col < C; col++) { // 배열 돌면서 낚시왕이랑 가장 가까운 상어 잡기 
            Shark caughtShark = null;
            for (int row = 0; row < R; row++) {
                if (sharks[row][col] != null) { // 해당 칸이  비어있지 않다면 == 상어 존재
                    caughtShark = sharks[row][col]; // 상어 바로 잡아버리고
                    sharks[row][col] = null;  // 해당 칸 빈칸으로
                    totalCaught += caughtShark.z; // 결과값에 더해줌 
                    break;
                }
            }
            moveSharks(); // 3. 상어 이동 
        }
        return totalCaught;
    }

    public static void moveSharks() {
        Shark[][] newMap = new Shark[R][C]; // 격차 초기화를 위한 배열 
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (sharks[i][j] != null) { // 이동할 때마다 상어 겹치는 경우 확인해주기
                    Shark shark = sharks[i][j];
                    int speed = shark.s;
                    int direction = shark.d;
                    int row = shark.r;
                    int col = shark.c;

                    // 상어의 속력에 따라 이동
                    for (int k = 0; k < speed; k++) {
                        row += dx[direction];
                        col += dy[direction];

                        // 범위를 벗어나면 방향을 조정하여 위치 조정
                        if (row < 0) {
                            row = 1;
                            direction = 1;
                        }
                        if (row >= R) {
                            row = R - 2;
                            direction = 0;
                        }
                        if (col < 0) {
                            col = 1;
                            direction = 2;
                        }
                        if (col >= C) {
                            col = C - 2;
                            direction = 3;
                        }
                    }

                    // 상어가 이동한 위치에 새로운 상어가 없거나 기존의 상어보다 크기가 큰 경우에만 업데이트
                    if (newMap[row][col] == null || newMap[row][col].z < shark.z) {
                        newMap[row][col] = new Shark(row, col, shark.s, direction, shark.z);
                    }
                } 
            }
        }
        sharks = newMap; // 초기화해줌 
    }
}

class Shark {
    int r, c, s, d, z;

    public Shark(int r, int c, int s, int d, int z) {
        this.r = r;
        this.c = c;
        this.s = s;
        this.d = d;
        this.z = z;
    }
}
