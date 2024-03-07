import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_18429 {
	static int N, K, answer = 0;
	static int[] kit;
	static int[] tgt;
	static boolean[] select;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		kit = new int[N];
		tgt = new int[N];
		select = new boolean[N];
		for (int i = 0; i < N; i++) {
			kit[i] = Integer.parseInt(st.nextToken());
		}

		perm(0);
		System.out.println(answer);
	}

	static void perm(int depth) {
		if (depth == N) {
			cal();
			return;
		}

		for (int i = 0; i < N; i++) {
			if (select[i])
				continue;
			select[i] = true;
			tgt[depth] = kit[i];
			perm(depth + 1);
			select[i] = false;
		}

	}

	static void cal() {
		int muscle = 500;
		for (int i = 0; i < N; i++) {
			muscle -= K;
			muscle += tgt[i];
			
			if(muscle < 500) {
				return;
			}
		}
		answer += 1;
		return;
	}
}
