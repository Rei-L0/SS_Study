import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	//int 범위 21억
	//a[x]+a[y]+a[y]= a[c]
	//a[x]+a[y]= a[c]-a[y]
	//위의 공식과 일치하는 것을 찾는다.
	static Integer list[];
	static Set<Integer>set = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		list = new Integer[N];
		for(int i=0; i<N; i++) {
			list[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(list, Collections.reverseOrder());
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				set.add(list[i]+list[j]); //a[x]+a[y]
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(set.contains(list[i]-list[j])) {
					System.out.println(list[i]);
					return;
				}
			}
		}
		
		
		
	}
}
