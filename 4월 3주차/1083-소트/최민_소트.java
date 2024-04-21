import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	//1번째 방법 앞에서부터 큰 숫자를 앞에 끌고옴 (1,2,3,4,5) 같은 경우는 5가 맨앞으로 안오게 된다..!
	//2번째 방법 범위 값 내에서 Min(Start+count, N-1)가장 큰 수를 가장 앞으로 땡겨온다.
	static int count, N;
	static int list[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		list=new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		count = Integer.parseInt(br.readLine());
		
		
		//가장 큰 숫자를 앞으로 끌고 오자!
//		while(count!=0) {
//			if (index==N-1) break; //더 이상 할게 없음!
//			if(list[index]<list[index+1]) { //앞 부분이 더 크다면 swap 해준다.
//				int temp= list[index+1];
//				list[index+1]= list[index];
//				list[index] = temp;
//				
//				if(index!=0) index--; //앞에 가서 다시 swap
//				else index++;
//				count--;
//			}
//			else index++;
//		}
		
		// 1 2 3 4 5 / 3
		// 0 1 2 3 4 /4-3
		int index=0;

		swap(0);
		
		for(int i=0; i<N; i++) {
			sb.append(list[i]).append(" ");
		}
		
		System.out.println(sb);
	}
	static void swap(int start) { //start~ N까지의 최대값 구하기
		if(count==0) return;
		if(start>=N) return;
		int max=-1;
		int maxIndex=-1;
		
		for(int i=start; i<=Math.min((N-1), start+count); i++) {
			if(list[i]>max) { //max값 찾기
				max=list[i];
				maxIndex=i;
			}
		}
		//System.out.println(max+" "+maxIndex);
		for(int i=maxIndex; i>start; i--) {
			int temp = list[i-1];
			list[i-1] = list[i];
			list[i]=  temp;
			count--;
		}
		
		swap(start+1);
		
		
	}
}
