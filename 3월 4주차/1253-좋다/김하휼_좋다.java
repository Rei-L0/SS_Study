import java.io.*;
import java.util.*;

/*
- 반복문을 돌면서 특정 값을 기준으로 잡고, 왼쪽 값들, 오른쪽 값들로 각각 리스트를 만듦. 
- 만든 리스트를 하나로 합치고 (그럼 특정 값은 배열에 들어가있지 않음)
- 투포인터로 찾아주기. 
*/

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		int cnt = 0;
		int num = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); 
		}
		Arrays.sort(arr);
		if (n <= 2) {
			System.out.println(0);
		}
		else {			
			for (int i=0; i<n; i++) {
				ArrayList<Integer> tmp1 = new ArrayList<>();
				ArrayList<Integer> tmp2 = new ArrayList<>();
				int position = i;
				for (int j=0; j<n; j++) {
					if (j < position) {
						tmp1.add(arr[j]);
					}
					else if (j == position) continue;
					else {
						tmp2.add(arr[j]);
					}
				}
				
				tmp1.addAll(tmp2);
				int lt = 0;
				int rt = tmp1.size()-1;
				
				while (lt < rt) {
					int mid = tmp1.get(lt)+ tmp1.get(rt);
					
					if (mid == arr[i]) {
						cnt++;
						break;
					}
					else if (mid > arr[i]) {
						rt -= 1;
						
					}
					else {
						lt += 1;
					}
				}
			}

		}
		System.out.println(cnt);
	}
}
