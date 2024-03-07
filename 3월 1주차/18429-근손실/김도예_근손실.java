import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 순열
// 순열을 선택할 때 해당 값이 500 미만이면 넘김
public class Main {

	static int N, K, cnt;
	static int[] kit;
	static boolean[] select;
	static int[] days;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		kit = new int[N + 1];
		days = new int[N + 1];
		select = new boolean[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			kit[i] = Integer.parseInt(st.nextToken());
		}
		days[0] = 500;
		perm(1);
		System.out.println(cnt);
	}
	static void perm(int tgtIdx) {
		if(tgtIdx == N + 1) {
			cnt++;
			return;
		}
		for(int i = 1; i <= N; i++) {
			if(select[i] || (days[tgtIdx-1] - K + kit[i]) < 500) continue;
			select[i] = true;
			days[tgtIdx] = days[tgtIdx-1] - K + kit[i];
			perm(tgtIdx + 1);
			select[i] = false;
			days[tgtIdx] = 0;
		}
	}
}
