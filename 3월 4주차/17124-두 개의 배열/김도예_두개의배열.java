import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 수업 시간에 배운 거 써먹고 싶어서 Arrays.binarySearch() 사용함
 * 해당 값이 0 이상이면 같은 값이 있는 거니까 그 값을 바로 넣어주고,
 * 음수이면 양수로 바꿔서 양 옆 값까지 비교해 차가 적고 값이 작은 것으로 넣음
 * 
 * !sum long으로 안 해놔서 틀림
 */
public class Main {

	static int n, m;
	static long sum;
	static int[] a, b, c;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			a = new int[n];
			b = new int[m];
			c = new int[n];
			sum = 0;
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < m; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(b);
			for(int i = 0; i < n; i++) {
				int tmp = Arrays.binarySearch(b, a[i]);
				if(tmp >= 0) {
					c[i] = b[tmp];
					sum += c[i];
					continue;
				}
				tmp = Math.abs(tmp) - 1;
				if(tmp == 0) c[i] = b[tmp];
				else if(tmp >= m) c[i] = b[m-1];
				else {
					int sub = Integer.MAX_VALUE;
					for(int s = tmp-1; s <= tmp+1; s++) {
						if(s < 0 || s >= m) continue;
						int ns = Math.abs(b[s] - a[i]);
						if(sub > ns) {
							sub = ns;
							c[i] = b[s];
						}
						else if(sub == ns) {
							c[i] = Math.min(c[i], b[s]);
						}
					}
				}
				sum += c[i];
			}
			sb.append(sum).append("\n");
		}
		System.out.println(sb);
	}
}
