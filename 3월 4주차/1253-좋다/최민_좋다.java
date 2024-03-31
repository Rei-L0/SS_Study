import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int list[] = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			list[i]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(list);
		
		int count=0;
		
		for(int i=0; i<n; i++) {
			int left=0;
			int right=n-1;
			
			while(true) { //투포인터
				//합을 구해야하므로 현재 숫자가 더해서 구해야 할 값이면 포인터 이동
				if(i==left) left++;
				else if(right==i) right--;
				
				if(left>=right) break;
				
				if(list[left]+list[right]> list[i])right--;
				else if (list[left]+list[right]< list[i])left++;
				else { //같다면
					count++;
					break;
				}
			}
		}
		
		System.out.println(count);
	}
}
