import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bReader.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int start = 0;
        int end = 0;
        arr = new int[n];

        st = new StringTokenizer(bReader.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] > start)
                start = arr[i];
        }
        end = Arrays.stream(arr).sum();

        while (start <= end) {
            int mid = (start + end) / 2;

            int count = 1;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (sum + arr[i] <= mid) {
                    sum += arr[i];
                } else {
                    sum = arr[i];
                    count++;
                }
            }
            if (count <= m) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(start);

    }
}