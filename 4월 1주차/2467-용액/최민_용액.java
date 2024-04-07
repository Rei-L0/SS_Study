import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N= Integer.parseInt(br.readLine());
		
		int [] list = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(list);
		
		int start = 0; 
		int end = N-1;
		int x=-1;
		int y=-1;
		int zero = Integer.MAX_VALUE;
		boolean flag=false;
		while(start<end) {
			int sum = list[start]+list[end];
			if(sum == 0) {
				System.out.println(list[start] + " " + list[end]);
				flag=true;
				break;
			}
			else if(Math.abs(sum)<zero) {
				zero = Math.abs(sum);
				x= list[start];
				y= list[end];
			}
			
			if(sum<0) { //합이 0보다 작으면 start 오른쪽 이동(합 증가)
				start++;
			}else {//(합 감소)
				end --;
			}
		}
		if(!flag) System.out.println(x+" "+y);
	}
	
}
