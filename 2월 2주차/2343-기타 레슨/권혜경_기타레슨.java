import java.io.*;
import java.util.*;

public class 기타레슨_2343 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N= Integer.parseInt(st.nextToken());
        int M= Integer.parseInt(st.nextToken());
        int lac[] = new int[N];
        int bluelay[] = new int[M];
        int min=0, max=0, ans=0;

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            lac[i] = Integer.parseInt(st.nextToken());
            max += lac[i];
        }
        min = max/M;

        while(min<=max){
            //System.out.println(min+" "+max);
            int terget = (min+max)/2;
            int i = 0, j = 0, now = 0;
            while(true){
                if(j==M||i==N) break;
                if(now+lac[i]<=terget) now+=lac[i++];
                else {
                    now=0;
                    j++;    
                }
            }
            //다 돌아진다 => 자리를 좀 더 타이트하게 잡아도 된다
            if(i==N){ 
                ans = max;
                max = terget-1;
            }
            //다 못돌았다 => 자리가 좁으니 좀 더 넓게 잡아야 한다
            else {
                ans = min;
                min = terget+1;
            }
        }
        System.out.println(min);
    }
}
