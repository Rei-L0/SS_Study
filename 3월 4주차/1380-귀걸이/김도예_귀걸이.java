import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 학생들의 이름을 배열에 저장해놓음
 * 입력 중 A, B는 무시
 * 학생들 번호를 boolean 배열에서 true로 바꾸고, 이미 true이면 false로 바꿔줌
 * true로 남아있는 번호를 찾아 해당 학생 이름 출력
 */
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int num = 1;
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			String[] students = new String[N+1];
			boolean[] ring = new boolean[N+1];
			for(int i = 1; i <= N; i++) {
				students[i] = br.readLine();
			}
			for(int i = 1; i <= N*2-1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int index = Integer.parseInt(st.nextToken());
				st.nextToken();
				if(ring[index]) ring[index] = false;
				else ring[index] = true;
			}

			for(int i = 1; i <= N; i++) {
				if(ring[i]) {
					sb.append(num).append(" ").append(students[i]).append("\n");
					break;
				}
			}
			num++;
		}
		System.out.println(sb);
	}
}
