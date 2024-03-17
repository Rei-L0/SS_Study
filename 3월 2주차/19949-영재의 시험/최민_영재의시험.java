import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] src = {1, 2, 3, 4, 5};
    static int count = 0;
    static int[] answer = new int[10];
    static int[] choose = new int[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            answer[i] = Integer.parseInt(st.nextToken());
        }

        perm(0,0);
        System.out.println(count);
    }

    static void perm(int tgtIdx, int score) {
        if (tgtIdx == 10) {
            if (score >= 5) {
                count++;
            }
            return;
        }

        for(int i=1; i<=5; i++) { //5지선다
	        if (tgtIdx >= 2 && choose[tgtIdx - 1] == i && choose[tgtIdx - 2] == i) {
	        	// 이전 두 문제의 답이 현재 답과 같은 경우
	        	continue;
	        }
	        choose[tgtIdx]=i;
	        if (answer[tgtIdx] == i) {
	            // 현재 답과 정답이 같은 경우
	            perm(tgtIdx + 1, score + 1);
	        } else {
	            // 현재 답과 정답이 다른 경우
	            perm(tgtIdx + 1, score);
	        } 
           
        }
    }
}
