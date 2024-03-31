import java.io.*;
import java.util.*;

public class Main {
    static int T, n, m;
    static int A[], B[];
    static List<Node> list;
    static StringBuilder sb = new StringBuilder();
    static Long answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            A = new int [n];
            B = new int[m];
            answer = 0L;

            st = new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++){
                A[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<m;i++){
                B[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(A);
            Arrays.sort(B);
            int r = 0;
            for(int l = 0; l < n; l++){
                while(r < m - 1 && A[l] >= B[r]) r++;
                if(r > 0) {
                    answer += (B[r] - A[l] < A[l] - B[r - 1] ? B[r] : B[r - 1]);
                }
                else answer += B[r];
            }
            sb.append(answer).append('\n');
        }
        System.out.println(sb.toString());
    }

    static class Node {
        int a;
        boolean type;

        Node(int a, boolean type) {
            this.a = a;
            this.type = type;
        }

    }
}
