import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(list);
		int m = (n + 1) / 2;

		long sum = 0;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < 31; j++) {
				if(list.get(i) < Math.pow(2, j)) {
					sum += j - 1;
					break;
				}
			}
		}
		System.out.println(sum + 1);
	}

}
