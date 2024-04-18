import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * ! 처음에 버블 정렬 쓰다가 틀림
 * ! 수정 후, 교환이 S번 일어나기 이전에 내림차순으로 정렬된 경우를 고려하지 않아서 시간 초과
 * 
 * S번 이내로 리스트를 돌며 최댓값을 찾아 0번째에 넣고, 그 다음 찾아 1번째에, ...
 * 이때 최댓값을 찾아 해당 인덱스에 넣는 과정은
 * 	1) ArrayList 써서 최댓값 삭제 후 해당 인덱스에 삽입
 * 	2) 배열에서 최댓값 인덱스 -> 삽입 인덱스까지 스왑
 * 두 방법으로 해봤는데 시간 같음
 */
public class Main {

	static int N, S;
	static int[] list;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		list = new int[N];
		for(int i= 0; i < N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		S = Integer.parseInt(br.readLine());
		int idx = 0;
		while(true) {
			int max = 0;
			int mi = 0;
			for(int i = idx; i < idx + S + 1; i++) {
				if(i >= N) break;
				if(list[i] > max) {
					max = list[i];
					mi = i;
				}
			}
			if(max > 0) {
				swap(mi, idx);
				S -= (mi - idx);
				idx++;
			}
			else break;
			if(S < 0) break;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sb.append(list[i]).append(" ");
		}
		System.out.println(sb);
	}
	static void swap(int mi, int idx) {
		for(; mi > idx; mi--) {
			int tmp = list[mi];
			list[mi] = list[mi-1];
			list[mi-1] = tmp;
		}
	}
}
