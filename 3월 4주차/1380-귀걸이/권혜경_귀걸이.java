import java.io.*;
import java.util.StringTokenizer;

/**
 * B_귀걸이_1380
 */
public class Main {
    static int N;
    static String name[];
    static boolean ear[];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int t=1;;t++){
            N = Integer.parseInt(br.readLine());
            if(N == 0) break;
            name = new String[N+1];
            ear = new boolean[N+1];
            for(int i=1;i<=N;i++){
                name[i] = br.readLine();
            }
            for(int i=0;i<2*N-1;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int where = Integer.parseInt(st.nextToken());
                ear[where] = !ear[where];
            }
            for(int i=1;i<=N;i++){
                if(ear[i]){sb.append(t).append(" ").append(name[i]).append("\n"); break;}
            }
        }
        System.out.println(sb.toString());
    }
}
