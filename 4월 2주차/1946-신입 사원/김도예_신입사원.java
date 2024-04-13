import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
 * 처음에 로직을 잘못 생각해서 헤맸음
 * 서류 1등부터 for문 돌려야 해서 인덱스에 등수를 저장했고(동석차가 없어서 가능) 따로 정렬하지 않아도 됨 
 * 면접은 몇 번 지원자가 몇 등인지 알아야 해서 지원자 번호를 인덱스로 저장함
 * 서류1등의 면접 등수를 저장해놓고 이보다 더 높으면 뽑고 면접 등수 갱신, 아니면 패스 
 */
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			HashSet<Integer> set = new HashSet<>();
			int[] s = new int[N+1]; // s[i] = j : 서류 i등: j번 
			int[] mr = new int[N+1]; // mr[i] = j: i번의 면접 등수: j
			for(int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				// i번의 등수 a, b
				s[a] = i;
				mr[i] = b;
			}
			int cnt = 1;
			int min = mr[s[1]];
			for(int i = 2; i <= N; i++) {
				if(min > mr[s[i]]) {
					min = mr[s[i]];
					cnt++;
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}
