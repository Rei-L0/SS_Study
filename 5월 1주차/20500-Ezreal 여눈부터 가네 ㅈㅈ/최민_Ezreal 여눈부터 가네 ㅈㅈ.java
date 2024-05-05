import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//3의 배수는 각 자릿수 더해도 3으로 나눠짐. ex)21 -> 2 + 1 은 3으로 나눠진다.
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long MOD = 1000000007L;
		
		int N = Integer.parseInt(br.readLine());
		
		long arr[][]= new long[1516][3];
		arr[2][0] = 1; //15
		arr[2][1] = 1; //55
		
		for(int i=3; i<1516; i++) {
			arr[i][0] =( arr[i-1][1] + arr[i-1][2] )% MOD; // arr[i][1]에 앞자리 5를 더한 경우 + arr[i][2]에 앞자리 1을 더한 경우
			arr[i][1] =( arr[i-1][0] + arr[i-1][2] )% MOD;// arr[i][0]에 앞자리 1을 더한 경우 + arr[i][2]에 앞자리 5를 더한 경우
			arr[i][2] =( arr[i-1][0] + arr[i-1][1] )% MOD; // arr[i][0]에 앞자리 5를 더한 경우 + arr[i][1]에 앞자리 1을 더한 경우
		}
		
		System.out.println(arr[N][0]);
		
		
	}
}
