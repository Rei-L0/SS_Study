import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        int[][] arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

        int midX = arr[N / 2][0];

        Arrays.sort(arr, Comparator.comparingInt(a -> a[1]));

        int midY = arr[N / 2][1];

        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += (Math.abs(arr[i][0] - midX) + Math.abs(arr[i][1] - midY));
        }

        System.out.print(sum);
    }
}
