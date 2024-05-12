import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// ** 조합, 브루트포스, 백트래킹 
// 일일이 조합으로 3개의 장애물 위치 구하여 조건에 맞는 경우가 있는지 탐색  
public class B_18428 {

	static int N;
	static String[][] map;
	static ArrayList<int[]> students, teachers, xs;
	static int studentCnt, teacherCnt, xCnt;
	static int[][] tgt = new int[3][2];
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		StringTokenizer st;
		map = new String[N][N];
		students = new ArrayList<>();
		teachers = new ArrayList<>();
		xs = new ArrayList<>();
		xCnt = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = st.nextToken();
				if (map[i][j].equals("S")) {
					students.add(new int[] { i, j });
					studentCnt += 1;
				} else if (map[i][j].equals("T")) {
					teachers.add(new int[] { i, j });
					teacherCnt += 1;
				} else {
					xs.add(new int[] { i, j });
					xCnt += 1;
				}
			}
		}

		comb(0, 0); // tgtCnt, srcCnt
		System.out.println("NO");

	}

	static void comb(int tgtCnt, int srcCnt) {
		if (tgtCnt == 3) {
			if (check()) {
				System.out.println("YES");
				System.exit(0); // ZERO 로 종료 !!
			}
			return;
		}
		if (srcCnt == xCnt)
			return;

		tgt[tgtCnt][0] = xs.get(srcCnt)[0];
		tgt[tgtCnt][1] = xs.get(srcCnt)[1];
		comb(tgtCnt + 1, srcCnt + 1);
		comb(tgtCnt, srcCnt + 1);
	}

	static boolean check() {
		for (int i = 0; i < 3; i++) {
			int y = tgt[i][0];
			int x = tgt[i][1];
			map[y][x] = "O";
		}

		for (int i = 0; i < studentCnt; i++) {
			int sy = students.get(i)[0];
			int sx = students.get(i)[1];

			for (int j = 0; j < 4; j++) {
				int yy = sy;
				int xx = sx;

				while (true) {
					yy += dy[j];
					xx += dx[j];
					if (yy < 0 || xx < 0 || yy > N - 1 || xx > N - 1)
						break;
					if (map[yy][xx].equals("O"))
						break;
					else if (map[yy][xx].equals("X"))
						continue;
					else if (map[yy][xx].equals("T")) {
						for (int k = 0; k < 3; k++) {	// 조건에 맞지 않는다면, 다시 장애물을 빈칸으로 바꿔줘야 함. 
							int y = tgt[k][0];
							int x = tgt[k][1];
							map[y][x] = "X";
						}
						return false;
					}
				}

			}
		}

		for (int i = 0; i < 3; i++) {
			int y = tgt[i][0];
			int x = tgt[i][1];
			map[y][x] = "X";
		}
		return true;
	}

}
