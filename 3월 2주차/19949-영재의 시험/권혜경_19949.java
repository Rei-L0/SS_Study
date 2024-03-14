import java.io.*;
import java.util.StringTokenizer;

// 완탐말고 다른 방법이 있는지 알고 고민하다가 시간 허비 1
// 밑에 주석으로 구현했는데 계속 안나돠서 알고 봤더니 카운트가 2여야 한다는 사실을 알아씀,, 시간낭비 2

public class Main {
    static int answer[] = new int [10];
    static int choose[] = new int [10];
    static int total=0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0;i<10;i++){
            answer[i] = Integer.parseInt(st.nextToken());
        }
        check(0);
        System.out.println(total);
    }
    static void check(int idx){
        if(idx==10){
            int before = 0, count = 0, score = 0;
            for(int i=0;i<10;i++){
                // if(choose[i]==before){
                //     count++;
                //     if(count==3) return;
                // }
                // else count = 0;

                // before = choose[i];
                if(choose[i]==answer[i]) score++;
            }
            if(score>=5) total++;
            return;
        }
        for(int i=1;i<=5;i++){
            if(idx>=2 && choose[idx-2]==i && choose[idx-1]==i) continue;
            choose[idx] = i;
            check(idx+1); 
        }
    }
}
