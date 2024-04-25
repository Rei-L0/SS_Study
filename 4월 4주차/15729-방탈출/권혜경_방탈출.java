// 걍 나올때마다 바꿔주면서 했습니다,,

import java.io.*;
import java.util.*;

public class Main {
    static int N, answer = 0;
    static boolean input[], output[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        input = new boolean[N];
        output = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            input[i] = st.nextToken().equals("1");
        }
        for(int i=0;i<N-2;i++){
            if(input[i]!=output[i]) turn(i);
        }
        if(N>=2 && input[N-2]!=output[N-2]) {
            answer++;
            output[N-1] = !output[N-1];
        }
        if(input[N-1]!=output[N-1]) answer++;
        System.out.println(answer);
    }
    static void turn(int i){
        output[i+1] = !output[i+1];
        output[i+2] = !output[i+2];
        answer++;
    }
}
