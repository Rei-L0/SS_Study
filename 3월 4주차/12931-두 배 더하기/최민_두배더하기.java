import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//배열 A는 0으로 차있다.
//배열에 있는 값 하나를 증가하거나 배열에 있는 모든 값을 두 배 시킬 수 있다.
public class Main {
	public static void main(String[] args) throws IOException {
		//int list[] = new int[n];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int arr[] = new int [n];

		int count=0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int min=Integer.MAX_VALUE;
		for(int i=0; i<n; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		while(true) {
			for(int i=0; i<n; i++) {
				
				if(!((arr[i]%2)==0)) { //홀수이면 -1 해주기
					arr[i]-=1;
					count++;
				}
			}
			if(Arrays.stream(arr).sum()==0) break;
			for(int i=0; i<n; i++) {
				arr[i]/=2;
			}
			count++;
		}
		
		System.out.println(count);
	}
}
