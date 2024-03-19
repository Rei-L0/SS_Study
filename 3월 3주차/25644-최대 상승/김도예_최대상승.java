import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 주가가 (최대인 날 - 최소인 날)을 계산할 건데
 * 주가가 최대인 날이 최소인 날 이후여야 함
 * max와 min은 최대, 최소의 인덱스값
 * now는 위 조건에 해당하는 최댓값
 */


public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int max = 0;
		int min = 0;
		int now = 0;
		int[] ana = new int[N];
		for(int i = 0; i < N; i++) {
			ana[i] = Integer.parseInt(st.nextToken());
			min = ana[min] < ana[i] ? min : i;	// 최솟값은 항상 갱신
			if(i >= min && (ana[i] - ana[min]) > now) {
				max = i;
				now = ana[max] - ana[min]; // 조건에 맞는 경우만 최댓값 갱신
			}
		}
		System.out.println(now);
	}

}
