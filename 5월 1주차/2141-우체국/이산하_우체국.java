import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 89% 에서 틀림 -> sum 이 짝수 일 때 중앙 값 : sum /2 , 홀수 일 때 중앙 값 : (sum+1) / 2
// 옛날에 푼 방식 보고 풀었는데, 아직도 왜 (마을별 거리 * 인원 수)의 중앙 값이 아닌 그냥 인원수의 합의 중앙 값을 찾는 지 모르겠음.
public class B_2141 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int N = Integer.parseInt(br.readLine());
    Long[] people = new Long[N];
    Map<Long, Long> map = new HashMap<>();
    Long sum = (long) 0;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      Long a = Long.parseLong(st.nextToken());
      Long b = Long.parseLong(st.nextToken());
      people[i] = a;
      map.put(a, b);
      sum += b;
    }
    Arrays.sort(people);
    Long left_sum = (long) 0;
    for (int i = 0; i < N; i++) {
      left_sum += map.get(people[i]);
      if (left_sum >= (sum + 1) / 2) {
        System.out.println(people[i]);
        break;
      }
    }
  }
}
