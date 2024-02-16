import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int mid = (N+1)/2;
        int sum = 0;
        for (int i=0; i< mid; i++) {
            sum += check(arr[i]);
        }

        sb.append(sum+1);
        System.out.println(sb);
        
    }

    public static int check(int k) {
        int cnt = 0;

        while (k > 1) {
            k /= 2;
            cnt++;
        }
        return cnt;
    }
}
