import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N= Integer.parseInt(br.readLine());
		
		int [] list = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			list[i]= Integer.parseInt(st.nextToken());
		}
		int max=0;
		int min=list[0];
		int result=0;
		//제일 작은 값의 인덱스를 구해서 아래와 같이 수정해보기
		for(int i=1; i<N; i++) {
			if(min>list[i])min=list[i];
			if(list[i]>min) {
				result=Math.max(result,list[i]-min);
				max=list[i];
			}
		}
		System.out.println(result);
		

	}

}
