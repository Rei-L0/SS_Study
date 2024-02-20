import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_25379 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int even_idx = 0; // 짝수를 왼쪽부터 채워나가기 위한 idx
		int odd_idx = 0; // 홀수를 왼쪽부터 채워나가기 위한 idx
		long result1 = 0;
		long result2 = 0;
		int n;

		for (int i = 0; i < N; i++) {
			
			n = Integer.parseInt(st.nextToken());
			if (n % 2 == 0) {
				result1 += i - (even_idx++);
			} else {
				result2 += i - (odd_idx++);
			}
		}

		System.out.println(Math.min(result1, result2));

	}

}
