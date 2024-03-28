import java.io.*;
import java.util.*;
/*
- b -> a로 가는데, 전체를 한번에 계산하는 것이 아니라, 값들 하나씩 봐줌.
- 2로 나눠질 때까지 나누고, 안나눠지면 1씩 빼줌. 
- 나눠지는 횟수를 모든 값을 대상으로로 최댓값만 ans에 더해줌 ! ( 1을 빼는 것은 한번씩 할 수 있지만, 나누는 것은 모든 수를 나눠야하기 때문에) 
*/

class Main { 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int ans = 0;
		int max_v = Integer.MIN_VALUE;
		for (int i=0; i<n; i++) {
			int cnt =0;
			while (arr[i] != 0 ) {
				if (arr[i]  % 2 == 0 ){
					arr[i] = arr[i]/2;
					cnt++;
				}
				else {
					arr[i]-= 1;
					ans++;
				}
			}
			max_v= Math.max(max_v, cnt);
		}
		ans += max_v;
		System.out.println(ans);	
	}	
}
