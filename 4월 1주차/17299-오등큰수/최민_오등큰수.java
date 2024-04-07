import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int [] count = new int[1000001];
		int [] list = new int [N];
		int [] answer = new int[N];
		Stack<Integer> stack = new Stack<>(); //index를 저장한다.
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int a = Integer.parseInt(st.nextToken()); //배열 입력받는다.
			list[i]=a;
			count[a]++;
		}
		
		for(int i=0; i<N; i++) {
				while(!stack.isEmpty()&& count[list[stack.peek()]]<count[list[i]]) { // 스택의 최근 인덱스의 실제 값의 count가 현재 list[i]의 카운터보다 자긍ㄹ때까지
					answer[stack.pop()]=list[i]; //stack에서 인덱스 빼서 그 인덱스에 해당하는 answer에 list[i] 삽입후
				}
				stack.push(i); //stack에 index값 push
		}
		while(!stack.isEmpty()) {
			answer[stack.pop()]= -1;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			sb.append(answer[i]).append(" ");
		}
		System.out.println(sb);
	}
}
