import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/*
 * a + b + c = x
 *  => a + b = x - c
 *  a + b 로 나올 수 있는 모든 값을 구해 정렬한 후(이분 탐색을 위해 정렬)
 *  x - c 로 나올 수 있는 모든 값이 배열에서 존재하는지 탐색 
 */
public class Main {
	static int N;
	static int[] num;
	static ArrayList<Integer> sum = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		for(int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sum.add(num[i] + num[j]);
			}
		}
		Collections.sort(sum);
		int ans = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(func(num[i] - num[j])) ans = Math.max(ans, num[i]);
			}
		}
		System.out.println(ans);
	}
	static boolean func(int num) {
		int l = 0;
		int r = sum.size() - 1;
		while(l <= r) {
			int m = (l + r) / 2;
			if(sum.get(m) > num) r = m - 1;
			else if(sum.get(m) < num) l = m + 1;
			else return true;
		}
		return false;
	}
}
