import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 고객들 위치의 x 좌표 중 중앙값과 y 좌표 중 중앙값을 구해,
// 해당 위치에서의 거리합을 출력한다.
public class 김도예_편의점2 {
	static int n, midX, midY;
	static int[] xs, ys;
	public static void main(String[] args) throws Exception{
    // input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().split(" ")[0]); // 공백이 들어오는 경우를 처리해야 함
		xs = new int[n];
		ys = new int[n];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			xs[i] = Integer.parseInt(st.nextToken());
			ys[i] = Integer.parseInt(st.nextToken());
		}
		// 중앙값 설정
		Arrays.sort(xs);
		Arrays.sort(ys);
		
		midX = xs[n / 2];
		midY = ys[n / 2];
		
		// 거리의 합 구하기
		long sum = 0;
		for(int i = 0; i < n; i++) {
			sum += Math.abs(xs[i] - midX) + Math.abs(ys[i] - midY);
		}
		System.out.println(sum);
		
	}

}
