/*
구간 내 위치마다 매번 합을 구해 최댓값을 구하면 시간 초과 -> 누적합 써야 됨

왼쪽으로 K만큼, 오른쪽으로 K만큼 더해주면 됨 -> 범위: 2k+1
범위를 벗어난 앞의 값을 빼서 합 구간 변경
 -> 오른쪽 끝 값이 7이고, k 가 3일 때, index 0의 값 빼기

? 범위 설정 똑바로 안 해서 런타임에러 남
얼음 양동이 개수가 100,000이고 좌표는 0 ~ 1,000,000
 */
 
public class Main {

	static int N, K, ans;
	static int[] ice;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ice = new int[1000001];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int g = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			ice[x] = g;
		}
		int sum = 0;
		int k = 2 * K + 1;
		
		for(int i = 0; i <= 1000000; i++) {
			if(i-k >= 0) sum -= ice[i - k];
			sum += ice[i];
		
			ans = Math.max(ans, sum);
		}
		System.out.println(ans);
	}
}
