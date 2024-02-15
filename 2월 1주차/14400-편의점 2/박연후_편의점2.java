import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i][0] = x;
            arr[i][1] = y;
        }

        Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));
        int xa = arr[n / 2][0];
        Arrays.sort(arr, Comparator.comparingInt(o -> o[1]));
        int ya = arr[n / 2][1];

        long ans = 0;
        for (int[] pos : arr) {
            ans += Math.abs(pos[0] - xa) + Math.abs(pos[1] - ya);
        }

        System.out.println(ans);

    }
}