import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			long[] A = new long[N];
			long[] B = new long[M];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Long.parseLong(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				B[i] = Long.parseLong(st.nextToken());
			}

			Arrays.sort(B);

			long result = 0;
			for (int i = 0; i < N; i++) {
				long now = A[i];

				int left = 0;
				int right = M - 1;
				int mid;

				long gap = Long.MAX_VALUE; // 절댓값의 차의 최솟값을 저장할 변수
				long insert = Long.MAX_VALUE; // result 값에 더해줄 선택된 수

				// 이분 탐색
				while (left <= right) {
					mid = (left + right) / 2;
					long tempGap = Math.abs(now - B[mid]); // 절댓값의 차를 비교하기 위한 임시 변수

					if (tempGap < gap) { // 지금까지 저장된 절댓값의 차의 최솟값보다 현재의 절댓값의 차가 더 작을 때
						insert = B[mid];
						gap = tempGap;
					} else if (tempGap == gap && B[mid] < insert) {
						insert = B[mid];
					}

					if (now < B[mid]) {
						right = mid - 1;
					} else {
						left = mid + 1;
					}
				}
				result += insert;
			}
			System.out.println(result);
		}
	}
}
