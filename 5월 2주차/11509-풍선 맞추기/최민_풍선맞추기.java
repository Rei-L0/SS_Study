import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		//List<Integer> list = new ArrayList<>();
		int list[]= new int[1000002];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int bollon = Integer.parseInt(st.nextToken());
			
			if(list[bollon+1]>=1) {
				list[bollon+1]--;
				list[bollon]++;
			}else {
				list[bollon]++;
			}
		}
		
		int count=0;
		for(int i=1;i<=1000000;i++) {
			if(list[i]>=1) count+=list[i];
		}
		
		System.out.println(count);
	}
}
