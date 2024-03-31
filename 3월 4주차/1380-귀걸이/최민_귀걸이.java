import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static String[] arr;
	static int n;
	static int[] arr2;
	static int count=1;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		arr= br.readLine().split("");
		while(true) {
			if(arr[0].equals("0")) break;
			else n=Integer.parseInt(arr[0]);
			//데이터의 길이가 1이면 다시 반복 이때 0이면 종료한다.
			
			//번호가 처음 등장하면 뺏긴거, 다시 나오면 돌려받은거(이때 문자열이 다름)
			String names[] = new String[n];
			arr2 = new int[n];
			for(int i=0; i<n; i++) {
				names[i] = br.readLine();
			}
			
			while(true) {
				arr = br.readLine().split(" ");
				if(arr.length==1) break;
				int a = Integer.parseInt(arr[0])-1;
				arr2[a]++;

			}
			
			for(int i=0; i<n; i++) {
				if(arr2[i]==1) System.out.println(count+" "+names[i]);
			}
			count++;
			
		}
		
	}
}
