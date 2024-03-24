import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int [] sum;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n= Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		sum= new int[n+1]; //0은 dummy
		int [] honeys = new int[n+1];
		int max=0;
		
		for(int i=1; i<=n; i++) {
			int honey = Integer.parseInt(st.nextToken());
			
			sum[i]=sum[i-1]+honey; //누적합 구하기
			honeys[i]=honey;
		}
		
		//벌을 가장 왼쪽에 꿀통을 가장 오른쪽에 나머지 벌은 다 배치해보기
		for(int i=2; i<n; i++) {
			max = Math.max(max, sum[n]-sum[1]-honeys[i]+sum[n]-sum[i]);
		}
		//벌을 가장 오른쪽에 꿀통을 가장 왼쪽에 나머지 벌은 다 배치해보기
		for(int i=2; i<n; i++) {
			max = Math.max(max, sum[n]-honeys[n]-honeys[i]+sum[i-1]);
		}
		//벌을 가장 왼쪽과 오른쪽에, 꿀통은 다 배치해보기
		for(int i=2; i<n; i++) {
			max = Math.max(max, sum[i]-honeys[1]+sum[n]-sum[i-1]-honeys[n]);
		}
		
		System.out.println(max);
		
	}
}
