import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// 이분탐색
// 나올 수 있는 블루레이 크기의 최소: 주어진 강의의 길이 중 최댓값 -> left
// 나올 수 있는 블루레이 크기의 최대: 주어진 강의 길이의 총합 -> right
// 범위를 줄여나가며 이분탐색을 해 가능한 값이 있는지 찾는다.
public class Main {

	static int n, m;
	static int[] lesson;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		lesson = new int[n];

		int left = 0, right = 0;
		for(int i = 0; i < n; i++) {
			lesson[i] = Integer.parseInt(st.nextToken());
			right += lesson[i];
			left = Math.max(left, lesson[i]);
		}
		while(left <= right) {
			int mid = (left + right) / 2;
			
			int count = getCount(mid);
			if(count > m) left = mid + 1;
			else right = mid - 1;
		}
		System.out.println(left);
	}

	static int getCount(int mid) {
		int sum = 0;
		int count = 0;
		for(int i = 0; i < n; i++) {
			if(sum + lesson[i] > mid) {
				sum = 0;
				count++;
			}
			sum += lesson[i];
		}
		if(sum != 0) count++;
		return count;
	}
}
