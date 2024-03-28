import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 그리디
 * B 배열 값들이 모두 2로 나누어지면 2로 나누는 연산, 안 되면 안 되는 원소만 1 빼기
 * 해당 연산을 할 때마다 cnt++
 */
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arrB = new int[N];

		for(int i = 0; i < N; i++) {
			arrB[i] = Integer.parseInt(st.nextToken());
		}
		int cnt = 0;
		while(true) {
			boolean flag = true;
			for(int i = 0; i < arrB.length; i++) {
				if(arrB[i] != 0) flag = false;
			}
			if(flag) break;
			if(check(arrB)) { 
				for(int i = 0; i < arrB.length; i++) {
					arrB[i] /= 2;
				}
			}
			cnt++;
		}
		System.out.println(cnt);
		

	}
	static boolean check(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] % 2 != 0) {
				arr[i]--;
				return false;
			}
		}
		return true;
	}
}
