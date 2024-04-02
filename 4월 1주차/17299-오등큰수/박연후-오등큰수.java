import java.io.*;
import java.util.*;

/*
    1. 각 숫자별로 cnt값을 배열에 저장한다.
    2. 수열을 돌면서 해당 값을 pq에 삽입한다.
    3. 만약 현재 값의 cnt가 pq.peek()의 cnt보다 클 경우 pq에서 빼고 결과 배열에 값을 저장한다.
    4. 수열을 순회했지만 아직 결과 배열의 값이 0일 경우 오등큰수가 없는 경우이기 때문에 -1을 출력한다.
 */

public class Main {

    static int n;

    static int[] num, ans, cnt;

    static PriorityQueue<Num> pq = new PriorityQueue<>();

    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static class Num implements Comparable<Num> {

        int num;
        int idx;
        int cnt;

        public Num(int num, int idx, int cnt) {
            this.num = num;
            this.idx = idx;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Num o) {
            return Integer.compare(this.cnt, o.cnt);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        num = new int[n + 1];
        ans = new int[n + 1];
        cnt = new int[1_000_001];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            num[i] = Integer.parseInt(st.nextToken());
            cnt[num[i]]++;
        }

        for (int i = 1; i < n + 1; i++) {
            if (!pq.isEmpty()) {
                while (!pq.isEmpty() && pq.peek().cnt < cnt[num[i]]) {
                    Num x = pq.poll();
                    ans[x.idx] = num[i];
                }
            }
            pq.offer(new Num(num[i], i, cnt[num[i]]));
        }

        for (int i = 1; i < n + 1; i++) {
            if (ans[i] == 0) {
                sb.append(-1).append(" ");
            } else {
                sb.append(ans[i]).append(" ");
            }
        }

        System.out.println(sb);
    }
}