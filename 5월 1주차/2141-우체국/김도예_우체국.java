import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 가장 작은 위치를 출력해야 하기 때문에 위치 기반 정렬 후
 * (모든 사람 수의 합 + 1) / 2보다 크거나 같은 위치가 답! 
 * => +1을 해주는 이유는 홀수일 때의 답을 똑바로 처리해주기 위해서임
 */
public class Main {

	static int N;
	static Node[] list;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new Node[N];
		long sum = 0, tmp = 0;
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			list[i] = new Node(x, a);
			sum += a;
		}
		sum = (sum + 1) / 2;
		Arrays.sort(list);
		for(int i = 0; i < N; i++) {
			tmp += list[i].a;
			if(tmp >= sum) {
				System.out.println(list[i].x);
				break;
			}
		}
	}

	static class Node implements Comparable<Node>{
		int x, a;

		public Node(int x, int a) {
			super();
			this.x = x;
			this.a = a;
		}

		@Override
		public int compareTo(Node o) {
			return x - o.x;
		}
	}
}
