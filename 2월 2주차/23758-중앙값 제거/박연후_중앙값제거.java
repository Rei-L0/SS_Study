import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bReader.readLine());

        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bReader.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int mid = (n + 1) / 2;

        int count = 0;
        for (int i = 0; i < mid; i++) {
            int num = arr[i];
            while (num > 1) {
                num = num / 2;
                count++;
            }
        }
        System.out.println(count + 1);
    }
}