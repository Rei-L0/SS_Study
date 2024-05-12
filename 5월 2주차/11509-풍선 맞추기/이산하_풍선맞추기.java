import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
/* 
2% 틀림 반례
6
5 4 4 3 3 2

동시에 같은 높이로 날라가는 화살이 있을 수 있음.

각 높이에 대한 화살 수를 map으로 저장하고, 
화살의 수를 (0- 화살 없을 때, 1- 화살 있을 때) 카운트가 아닌
화살의 수가 1 이상개 있을 수 있으므로, 현재 값에서  +-1 을 해주는 방식 

*/
public class B_11509 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int result = 0;
		HashMap<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < N; i++) {
			int nowBalloon = Integer.parseInt(st.nextToken());
			if (map.containsKey(nowBalloon) && map.get(nowBalloon) > 0) {
				map.put(nowBalloon, map.get(nowBalloon) - 1);

				if (nowBalloon - 1 > 0) {
					if (map.containsKey(nowBalloon - 1)) {
						map.put(nowBalloon - 1, map.get(nowBalloon - 1) + 1);
					} else {
						map.put(nowBalloon - 1, 1);
					}
				}

			} else {
				if (nowBalloon - 1 > 0) {
					if (map.containsKey(nowBalloon - 1)) {
						map.put(nowBalloon - 1, map.get(nowBalloon - 1) + 1);
					} else {
						map.put(nowBalloon - 1, 1);
					}
				}
				result += 1;
			}
		}

		System.out.println(result);
	}

}
