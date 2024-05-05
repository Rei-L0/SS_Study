import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static String ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dfs("0", 0);

		System.out.println(ans);
	}
	
	// 일의 자리부터 앞 자리와 차가 2이상이면 ++
	// 다 돌았는데 더이상 바꿀 게 없으면 
	// 1) 가장 큰 자릿수가 9가 아닌 경우, +1 해주고 일의 자리부터 0, 1, 2... 로 채움
	// 2) 9인 경우(이미 위에서 차가 2 이상인 경우를 체크해줬으므로 해당 자릿수의 마지막 값임 9876 같은) 자릿수 하나 추가
	//		- 해당 자릿수 중 감소하는 가장 작은 수
	static void dfs(String now, int cnt) {

		if(cnt == N) {
			ans = now;
			return;
		}
		if(now.equals("9876543210")) {
			ans = "-1";
			return;
		}
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		for(int i = now.length()-1; i > 0; i--) {
			int n1 = now.charAt(i) - '0';
			int n2 = now.charAt(i-1) - '0';
			
			if(n2-n1 > 1) {
				sb.append(now.substring(0, i)).append((n1+1));
				for(int j = now.length() - (i+1) - 1; j >= 0; j--) {
					sb.append(j);
				}
				flag = true;
				dfs(sb.toString(), cnt+1);
				return;
			}
		}
		if(!flag) {
			// 첫 자리가 9가 아닌 경우
			if(now.charAt(0) - '0' != 9) {
				sb.append(now.charAt(0) - '0' + 1);
				for(int i = now.length()-2; i >= 0; i--) {
					sb.append(i);
				}
				dfs(sb.toString(), cnt+1);
				return;
			}
			// 9인 경우 자릿수 ++
			else {
				sb.append(now.length());
				for(int i = now.length()-1; i >= 0; i--) {
					sb.append(i);
				}
				dfs(sb.toString(), cnt+1);
				return;
			}
		}
	}

}
