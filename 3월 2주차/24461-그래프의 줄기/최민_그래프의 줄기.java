//정답 코드 x, 출력 초과, 제공 테케는 정답, 방법이 잘못된건가..음, 오늘까지 못풀면 스터디분들 정답 코드 보고 공부 예정
package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;

public class bj_24461_그래프의줄기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        Set<Integer> result = new HashSet<>();
        for (int i = 0; i < N; i++) {
            if (graph.get(i).size() != 1) { // 진입차수가 1이 아닌 노드를 찾아서 결과에 추가
                result.add(i);
            }
        }

        for (int v : result) {
            System.out.print(v + " ");
        }
    }
}
