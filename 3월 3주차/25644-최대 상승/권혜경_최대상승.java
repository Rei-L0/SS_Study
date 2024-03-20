import java.io.*;
import java.util.*;

public class B_최대상승_25644 {
    static int N, min, max, now, answer=0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        min = max = Integer.parseInt(st.nextToken());
        for(int i=1;i<N;i++){
            now = Integer.parseInt(st.nextToken());
            if(now>max) max = now;
            else if(now<min){
                answer = Math.max(max-min, answer);
                min = max = now;
            }
        }
        answer = Math.max(max-min, answer);
        System.out.println(answer);
    }
}
