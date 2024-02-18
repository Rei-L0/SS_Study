package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_23758_중앙값제거 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        int n = Integer.parseInt(br.readLine());
        int[] amel = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            amel[i] = Integer.parseInt(st.nextToken());
        }

        // 배열 정렬
        Arrays.sort(amel);

        int result = 0;
        for (int i = 0; i < (n + 1) / 2; i++) {
            result += (int) (Math.log(amel[i]) / Math.log(2)); //log2... 개념 확실히 알기!
        }

        System.out.println(result + 1);
    }
}
