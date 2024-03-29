import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 투포인터 알고리즘 찾아보고 풀었음
 */
public class Main2 {

	static int N, cnt;
	static int[] num;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		num = new int[N];
		cnt = 0;
		for(int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(num);
		for(int i = 0; i < N; i++) {
			int start = 0;
			int end = num.length - 1;
			int key = num[i];
			while(start < end) {
				if(num[start] + num[end] > key) end--;
				else if(num[start] + num[end] < key) start++;
				else {
					if(start != i && end != i) {
						cnt++;
						break;
					}
					else if(start == i) start++;
					else if(end == i) end--;
				}	
			}
		}
		System.out.println(cnt);
	}
}
