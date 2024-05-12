import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prac {
    private static int N,result;
    private static int[] arr = new int[1_000_001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(st.nextToken());
            if (arr[h] > 0) {
                arr[h]--;
            }
            arr[h - 1]++;
        }
        
        for (int i : arr) {
            result += i;
        }
        System.out.println(result);
    }
}
