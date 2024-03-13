import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 team 배열: -1이면 손상, 0이면 손상 x, 1이면 여분 카약 있음
 입력 받을 때 손상 있는 팀이 여분 카약 가지고 있으면 이 값을 0으로 만듦
 
 이후 반복문에서 손상 있는 카약을 발견할 시, 직전 팀이 여분 카약을 가지고 있으면 빌리고
 없으면 직후 팀한테 빌림 -> 빌려준 팀과 빌린 팀 값 0으로 갱신
  -> 그리디
 
 양쪽 다 없으면 갱신 없이 진행
 */
public class Main {

	static int N, S, R, ans;
	static int[] team;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		team = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < S; i++) {
			team[Integer.parseInt(st.nextToken())] = -1;
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < R; i++) {
			team[Integer.parseInt(st.nextToken())]++;
		}

		// 풀이
		for(int i = 1; i <= N; i++) {
			if(team[i] == -1 && team[i - 1] == 1) {
					team[i-1] = 0;
					team[i] = 0;
			}
			else if(team[i] == -1 && i < N && team[i + 1] == 1) {
				team[i+1] = 0;
				team[i] = 0;
			}
		}
		for(int i = 1; i <= N; i++) {
			if(team[i] == -1) ans++;
		}
		System.out.println(ans);
	}
}
