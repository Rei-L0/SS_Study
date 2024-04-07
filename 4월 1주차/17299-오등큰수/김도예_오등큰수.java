import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] nums, cnt, ans;
	static ArrayDeque<Integer> stack = new ArrayDeque<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		ans = new int[N];
		cnt = new int[1_000_001];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			cnt[nums[i]]++;
		}
		for(int i = 0; i < N; i++) {
			while(!stack.isEmpty() && cnt[nums[i]] > cnt[nums[stack.peek()]]) {
				ans[stack.pop()] = nums[i];
			}
			stack.push(i);
		}
		while(!stack.isEmpty()) {
			ans[stack.pop()] = -1;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sb.append(ans[i]).append(" ");
		}
		System.out.println(sb);
	}
}
