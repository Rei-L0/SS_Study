import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        long count_oe = 0, count_eo = 0;
        boolean num[] = new boolean[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            String s = st.nextToken();
            num[i] = false;
            if((s.charAt(s.length()-1)-'0')%2==0) num[i] = true; // 짝수가 트루
        }

        long r = 0, l = N-1;

        //짝홀로정리하기
        while(r<l){
            if(!num[(int)r]){r++; continue;} //짝수가 아니면 넘어가
            if(num[(int)l]){l--; continue;} //짝수면 넘어가
            count_eo += l-r;
            r++;
            l--;
        }
        r = 0; l = N-1;
        while(r<l){
            if(num[(int)r]){r++; continue;}
            if(!num[(int)l]){l--; continue;}
            count_oe += l-r;
            r++;
            l--;
        }
        System.out.println(Math.min(count_eo, count_oe));
        
    }
}
