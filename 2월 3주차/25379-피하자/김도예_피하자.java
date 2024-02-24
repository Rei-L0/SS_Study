import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		boolean prev = isEO(arr[0]);
		boolean prev2 = !prev;
		long min = 0;
		long cnt = 0;
		long sc = 0;
		
		long cnt2 = 0;
		long sc2 = 0;
		for(int i = 0; i < n; i++) {
			if(prev == isEO(arr[i])) sc++;
			else cnt += sc;
			
			if(prev2 == isEO(arr[i])) sc2++;
			else cnt2 += sc2;
		}
		min = Math.min(cnt2, cnt);
		System.out.println(min);
	}
	static boolean isEO(int n) {  // true면 홀수
		if(n%2 == 0) return false;
		else return true;
	}
}
