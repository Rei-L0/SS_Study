import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
 * 개미를 순서대로 배열에 저장, 방향별로 set에 저장
 * 반복문 내에서 반대 방향으로 움직이는 개미가 있으면 둘이 자리 바꿔줌
 * 오른쪽으로 가는 개미 앞에 왼쪽으로 가는 개미가 있는 경우만 고려함
 */
public class Main {

	static int N1, N2, T;
	static HashSet<Character> a1 = new HashSet<>();
	static HashSet<Character> a2 = new HashSet<>();
	static char[] ant;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N1 = Integer.parseInt(st.nextToken());
		N2 = Integer.parseInt(st.nextToken());
		ant = new char[N1 + N2];
		String str = br.readLine();
		for(int i = 0, j = N1-1; i < N1; i++, j--) {
			ant[j] = str.charAt(i);
			a1.add(ant[j]);
		}
		str = br.readLine();
		for(int i = 0, j = N1; i < N2; i++, j++) {
			ant[j] = str.charAt(i);
			a2.add(ant[j]);
		}
		T = Integer.parseInt(br.readLine());

		for(int t = 0; t < T; t++) {
			for(int i = 0; i < N1+N2 - 1; i++) {
				if(a1.contains(ant[i]) && a2.contains(ant[i+1])) {
					swap(i, ++i);
				}
			}
		}
		for(int i = 0; i < N1+N2; i++) {
			sb.append(ant[i]);
		}
		System.out.println(sb);
		
	}
	static void swap(int i, int j) {
		char tmp = ant[i];
		ant[i] = ant[j];
		ant[j] = tmp;
	}
}
