import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
 * 맨 처음을 기준으로, 화살의 높이를 HashMap<높이, 개수> 에 추가함
 * 현재 풍선 높이의 화살이 있고, 개수가 1개 이상이면 터트릴 수 있으므로 터트림
 *  => 해당 높이 화살의 개수 -= 1
 * 현재 풍선 높이의 화살이 없을 경우 화살을 추가해 터트리고 cnt++
 * 
 * => 어떤 경우든 터트렸다면 (해당 높이 - 1) 화살의 개수 += 1
 * HashSet으로 했다가 같은 높이에서 날아가는 화살이 여러 개인 경우를 고려하지 못해 틀림
 */
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] balloon = new int[N];
		for(int i = 0; i < N; i++) {
			balloon[i] = Integer.parseInt(st.nextToken());
		}
		HashMap<Integer, Integer> arrow = new HashMap<>();
		arrow.put(balloon[0], 1);
		int cnt = 1;
		for(int i = 0; i < N; i++) {
			int now = balloon[i];
			if(arrow.containsKey(now) && arrow.get(now) > 0) arrow.put(now, arrow.get(now) - 1);
			else cnt++;
			
			arrow.put(now - 1, arrow.getOrDefault(now-1, 0) + 1);
		}
		System.out.println(cnt);
	}
}
