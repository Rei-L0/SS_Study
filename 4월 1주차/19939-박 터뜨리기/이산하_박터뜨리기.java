import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1. 맨 처음 모든 바구니에 1, 2, 3 .. 으로 1씩 증가 한 볼을 각 각 넣어줌 => 여기서 가지고 있는 공의 합보다 커지면 -1
// 2. 각 한개 씩 차이나는 바구니에 남은 공을 가장 많이 공을 가지고 있는 바구니 부터 차례로 1개씩 넣어줌.
// 한바퀴 다 돌고도 공이 남으면 계속 2.를 반복.

public class B_19939 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] bags = new int[K];

		int ball = 0;
		int sumBall = 0;
		for (int i = 0; i < K; i++) {
			ball += 1;
			bags[i] = ball;
			sumBall += ball;
			if (sumBall > N) {
				System.out.println(-1);
				System.exit(0);
			}
		}

		int idx = K - 1;
		while (true) {
			sumBall += 1;
			if (sumBall > N) {
				break;
			}
			bags[idx] += 1;
			idx -= 1;
			if (idx < 0) {
				idx = K - 1;
			}
		}
		System.out.println(bags[K - 1] - bags[0]);
	}

}
