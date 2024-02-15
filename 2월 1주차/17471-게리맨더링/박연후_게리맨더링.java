import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 17471
public class Main {

    static final int MAX_VALUE = 10000;
    static int n;
    static int[] num;
    static int ans = MAX_VALUE;
    // 인접한 구역의 정보
    static ArrayList<int[]> arrayList = new ArrayList<>();

    // 선거구 조합 생성
    static void combi(boolean[] visit, int start, int count, int goal) {
        if (count == goal) {
            int idx = 0;
            int[] arr = new int[goal];
            int idx2 = 0;
            int[] arr2 = new int[n - goal];
            for (int i = 0; i < n; i++) {
                if (visit[i]) {
                    arr[idx++] = i;
                } else {
                    arr2[idx2++] = i;
                }
            }
            if (check(arr) && check(arr2)) {
                int sum = 0;
                int sum2 = 0;
                for (int k : arr) {
                    sum += num[k];
                }
                for (int j : arr2) {
                    sum2 += num[j];
                }
                ans = Math.min(ans, Math.abs(sum - sum2));
            }
            return;
        }
        for (int i = start; i < n; i++) {
            visit[i] = true;
            combi(visit, i + 1, count + 1, goal);
            visit[i] = false;
        }
    }

    // 선거구끼리 인접 여부 체크
    static boolean check(int[] arr) {
        boolean[] visit = new boolean[n];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        visit[arr[0]] = true;
        queue.add(arr[0]);
        while (!queue.isEmpty()) {
            int curIdx = queue.poll();
            int[] list = arrayList.get(curIdx);
            for (int j : list) {
                for (int i : arr) {
                    if (j == i && !visit[j]) {
                        visit[j] = true;
                        queue.add(j);
                    }
                }
            }
        }
        int count = arr.length;
        for (int j : arr) {
            if (visit[j]) {
                count--;
            }
        }
        return count == 0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stringTokenizer.nextToken());
        num = new int[n];

        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int[] tmp = new int[m];
            for (int j = 0; j < m; j++) {
                tmp[j] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            }
            arrayList.add(tmp);
        }
        for (int i = 1; i <= n / 2; i++) {
            combi(new boolean[n], 0, 0, i);
        }
        if (ans != MAX_VALUE) {
            System.out.println(ans);
        } else {
            System.out.println(-1);
        }
    }
}