import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 한 번에 한자리만 바꿀 수 있다.
	static class password{
		int num, count;

		public password(int num, int count) {
			super();
			this.num = num;
			this.count = count;
		}
		
	}
	static boolean check[]= new boolean [10000]; //0~9999
	public static void main(String[] args) throws NumberFormatException, IOException {
		//소수를 먼저 찾는다. (에라토스테네스의 체)
		for(int i=2; i<10000; i++) {
			if(!check[i]) {
				for(int j=i*2; j<10000; j+=i) {
					check[j]=true;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(bfs(a,b)==-1) sb.append("Impossible").append("\n");
			else sb.append(bfs(a,b)).append("\n");
			
		}
		System.out.println(sb);
	}
	
	static int bfs(int start, int end) {
		Queue<password> queue = new ArrayDeque<>();
		boolean[]visit = new boolean[10000];
		visit[start]= true;
		queue.add(new password(start, 0));
		while(!queue.isEmpty()) {
			password p = queue.poll();
			int num = p.num;
			int count = p.count;
			if(num==end) return count;
			
			//1의 자리 바꾸기
			int n = num/10 * 10; //각 자리의 숫자 0으로 바꾸기
			for(int i=0; i<10; i++) {
				int temp = n+i;
				if(!visit[temp] && !check[temp]) { //바뀐 숫자가 소수이고 방문 x
					visit[temp] = true;
					queue.add(new password(temp, count+1));
				}
			}
			
			//10의 자리 바꾸기
			n = (num/100 * 100) + (num%10);
			for(int i=0; i<10; i++) {
				int temp = n+i*10;
				if(!visit[temp] && !check[temp]) { //바뀐 숫자가 소수이고 방문 x
					visit[temp] = true;
					queue.add(new password(temp, count+1));
				}
			}
			
			//100의 자리 바꾸기
			n = (num/1000 * 1000) + (num%100);
			for(int i=0; i<10; i++) {
				int temp = n+i*100;
				if(!visit[temp] && !check[temp]) { //바뀐 숫자가 소수이고 방문 x
					visit[temp] = true;
					queue.add(new password(temp, count+1));
				}
			}
			
			//1000의 자리 바꾸기
			n=num%1000;
			for(int i=1; i<10; i++) { // 0이 올 수 없다.
				int temp = n+i*1000;
				if(!visit[temp] && !check[temp]) { //바뀐 숫자가 소수이고 방문 x
					visit[temp] = true;
					queue.add(new password(temp, count+1));
				}
			}
		}
		return -1;
	}
}
