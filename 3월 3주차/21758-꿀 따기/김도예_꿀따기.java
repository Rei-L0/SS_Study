import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 오른쪽 끝에 벌통이 있는 경우
 * -> 첫 번째 벌 위치 맨 왼쪽으로 고정
 * -> 두 번째 벌 위치 옮겨가며 최댓값 계산
 * 
 * 왼쪽 끝에 벌통이 있는 경우
 * -> 첫 번째 벌 위치 맨 오른쪽으로 고정
 * -> 두 번째 벌 위치 옮겨가며 최댓값 계산
 * 
 * 중간에 벌통이 있는 경우
 * -> 첫 번째 벌은 맨 왼쪽에 고정, 두 번째 벌은 맨 오른쪽에 고정
 * -> 벌통을 옮겨가며 최댓값 계산
 */
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] honey = new int[N];
		int[] leftsum = new int[N];
		int[] rightsum = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			honey[i] = Integer.parseInt(st.nextToken());
			if(i == 0) continue; 
			leftsum[i] = leftsum[i-1] + honey[i];
		}
		for(int i = N-2; i >= 0; i--) {
			rightsum[i] = rightsum[i+1] + honey[i];
		}
		int max = 0;
		for(int i = 1; i < N; i++) {
			int bee1 = leftsum[N-1] - honey[i];
			int bee2 = leftsum[N-1] - leftsum[i];
			max = Math.max(bee1+bee2, max);
		}
		for(int i = N-2; i >= 0; i--) {
			int bee1 = rightsum[0] - honey[i];
			int bee2 = rightsum[0] - rightsum[i];
			max = Math.max(bee1+bee2, max);
		}
		for(int i = 1; i < N; i++) {
			max = Math.max(leftsum[i] + rightsum[i], max);
		}
		System.out.println(max);
	}
}
