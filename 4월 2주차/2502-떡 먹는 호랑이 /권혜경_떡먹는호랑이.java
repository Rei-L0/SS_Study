// 수학적으로 접근해서 완탐으로 풉니다

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int D, K, A, B;
    static int day[][]; //0:A, 1:B
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        day = new int[D][2];
        day[0][0] = 1;
        day[0][1] = 0;
        day[1][0] = 0;
        day[1][1] = 1;

        for(int i=2;i<D;i++){
            day[i][0] = day[i-1][0]+day[i-2][0];
            day[i][1] = day[i-1][1]+day[i-2][1];
        }
        // K = day[D-1][0]*A + day[D-1][1]*B;
        for(int i=1;i<K;i++){
            if((K-day[D-1][0]*i)%day[D-1][1]==0){
                A = i;
                B = (K-day[D-1][0]*i)/day[D-1][1];
                if(day[D-1][0]*A + day[D-1][1]*B == K) break;
            }
        }
        // A B A+B A+2B 2A+3B 3A+5B
        // 2 26 28 54 82 136 218

        System.out.println(A+"\n"+B);
    }
}
