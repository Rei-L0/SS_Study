import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static Ant[] ants;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		ants = new Ant[N + M];

		String[] input = br.readLine().split("");
		for (int i = 0; i < N; i++) {
			ants[N - (i + 1)] = new Ant(input[i], 0);
		}

		input = br.readLine().split("");
		for (int i = 0; i < M; i++) {
			ants[i + N] = new Ant(input[i], 1);
		}

		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			go();
		}
		for (int i = 0; i <N+M; i++) {
			System.out.print(ants[i].name);
		}
		
	}

	static void go() throws CloneNotSupportedException {
		int now = 0;
		while (true) {
			if (now >= (N + M) || now + 1 >= (N + M))
				break;
			if (ants[now].teamN == 0 && ants[now+1].teamN == 1) {
				String tName = ants[now].name;
				ants[now].teamN = 1;
				ants[now].name = ants[now + 1].name;
				ants[now + 1].teamN = 0;
				ants[now + 1].name = tName;
				now += 2;
				continue;
			}
			now += 1;

		}
	}

	static class Ant {
		String name;
		int teamN;

		Ant(String name, int teamN) {
			this.name = name;
			this.teamN = teamN;
		}
	}
}
