import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 중복 순열
 답이 세 개 이상 연속이면 X 
 처음엔 순열이 다 만들어지면 점수 계산했는데
 순열 만들 때 점수도 같이 넘겨주면서 하면 시간 더 빨라짐!
 312ms -> 244ms
*/
public class Main {

	static int[] src = {1, 2, 3, 4, 5};
	static int[] tgt, answer;
	static int cnt;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		answer = new int[10];
		tgt = new int[10];
		for(int i = 0; i < 10; i++) {
			answer[i] = Integer.parseInt(st.nextToken());
		}
		perm(0, 0);
		System.out.println(cnt);
	}
	static void perm(int tgtIdx, int score) {
		if(tgtIdx == 10) {
			if(score >= 5) cnt++;
			return;
		}
		for(int i = 0; i < 5; i++) {
			if(tgtIdx >= 2 && src[i] == tgt[tgtIdx-1] && src[i] == tgt[tgtIdx-2]) continue;
			tgt[tgtIdx] = src[i];
			if(answer[tgtIdx] == tgt[tgtIdx]) perm(tgtIdx + 1, score + 1);
			else perm(tgtIdx + 1, score);
		}
	}
}
