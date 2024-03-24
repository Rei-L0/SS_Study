// 지난번에 풀었던거 알겠는데 설명을 봐도 이해가 안됩니다...
// 공부 하겠습니다...

import java.io.*;
import java.util.*;

public class B_개미_3048 {
    static char front[], back[], answer[];
    static boolean visit[];
    static int T, f, b, start;
    static StringBuilder sb= new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        answer = new char[Integer.parseInt(st.nextToken())+Integer.parseInt(st.nextToken())];
        visit = new boolean[answer.length];
        front = br.readLine().toCharArray();
        back = br.readLine().toCharArray();
        T = Integer.parseInt(br.readLine());
        b = front.length-1+T;
        f = front.length-T;
        if(f<0) f=0;
        if(b>=answer.length) b=answer.length-1;

        for(int i=0;i<T;i++){
            if(f+i*2>=answer.length||b-i*2<0||i>=front.length||i>=back.length) break;
            answer[f+i*2] = back[i];
            answer[b-i*2] = front[i];
            visit[i] = true;
            visit[front.length+i] = true;

            System.out.println(answer[f+i]+" "+answer[b-i]);
        }

        System.out.println(Arrays.toString(answer));
        start = front.length-1;
        for(int i=0;i<front.length;i++){
            if(answer[i]!='\u0000') continue;
            if(visit[start]) {
                start--;
                continue;
            }
            answer[i] = front[start--];
        }
        start = back.length-1;
        for(int i=back.length-1;i>-1;i--){
            if(answer[front.length+i]!='\u0000') continue;
            if(visit[start]) {
                start--;
                continue;
            }
            answer[front.length+i] = back[start--];
        }

        for(int i=0;i<answer.length;i++){
            sb.append(answer[i]);
        }
        System.out.println(sb.toString());
    }
}
