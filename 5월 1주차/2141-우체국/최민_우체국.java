import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] towns = new int[N][2];

        long total = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            towns[i][0] = Integer.parseInt(st.nextToken()); // 마을 위치
            towns[i][1] = Integer.parseInt(st.nextToken()); // 사람 수
            total += towns[i][1];

        }
        
        Arrays.sort(towns, (a, b) -> a[0] - b[0]);

        long mid = 0;
        long current = 0;
        for (int i = 0; i < N; i++) {
            current += towns[i][1];
            if (current >= (total + 1) / 2) {
                mid = towns[i][0];
                break;
            }
        }
        System.out.println(mid);
    }
}

