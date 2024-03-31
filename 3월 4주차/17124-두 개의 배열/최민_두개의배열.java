import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] listN;
    static int[] listM;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            listN = new int[n];
            listM = new int[m];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                listN[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                listM[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(listM); // 배열 B 정렬

            long sum = 0; // 정답의 합을 저장할 변수
            for (int i = 0; i < n; i++) {
                int minDiff = Integer.MAX_VALUE;
                int closestValue = Integer.MAX_VALUE;

                // 이분 탐색, 배열 B에서 배열 A의 원소 중 가장 가까운 절대값 찾기
                int low = 0, high = m - 1;
                while (low <= high) {
                    int mid = (low + high) / 2;
                    int diff = Math.abs(listN[i] - listM[mid]);
                    if (diff < minDiff || (diff == minDiff && listM[mid] < closestValue)) {
                        minDiff = diff;
                        closestValue = listM[mid];
                    }
                    if (diff == 0) break;
                    if (listM[mid] < listN[i]) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
                sum += closestValue;
            }

            System.out.println(sum);
        }
    }
}
