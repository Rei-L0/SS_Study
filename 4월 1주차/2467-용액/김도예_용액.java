import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 투포인터
public class Main {

	static int N, min = Integer.MAX_VALUE, ans1, ans2;
	static int[] num;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		int start = 0; 
		int end = N-1;
		while(start < end) {
			int sum = Math.abs(num[start] + num[end]);
			if(min > sum) {
				min = sum;
				ans1 = num[start];
				ans2 = num[end];
			}
			if(min == 0) break;
			if(num[start] + num[end] < 0) start++;
			else if(num[start] + num[end] > 0) end--;
		}

		System.out.println(ans1 + " " + ans2);
	}
}
