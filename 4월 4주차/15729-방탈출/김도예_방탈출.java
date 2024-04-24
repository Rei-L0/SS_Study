import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 불 다 꺼진 상태의 배열, 만들어야 하는 배열 만들어서
 * 처음부터 조회하며 두 배열의 값이 다르면 
 * cnt++, 현재 불과 오른쪽 두 개까지 현재 상태와 반대로 만듦
 */
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] light = new boolean[N];
		boolean[] n = new boolean[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			light[i] = (Integer.parseInt(st.nextToken()) == 0) ? false : true;
		}
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			if(n[i] != light[i]) {
				cnt++;
				for(int j = i; j < i + 3 && j < N; j++) {
					n[j] = !n[j];
				}
			}
		}
		System.out.println(cnt);
	}
}
