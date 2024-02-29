import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { 
	static int result= 0 ;//최대 이익
	static int n;
	static int arr[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		arr = new int [n][2];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0]= Integer.parseInt(st.nextToken());
			arr[i][1]= Integer.parseInt(st.nextToken());
		}
		
		
		go(0,0);
		System.out.println(result);
		
	}
	
	static void go(int index, int max) {//현재 인덱스, 남은 날짜, 돈
		if(index>=n) {
			if(result<max) result=max;
			return; //현재 인덱스가 범위 넘어서면 그만
		}
		if(arr[index][0]>(n-index)) {
			if(result<max) result=max;
			go(index+1, max); //선택 안하고 다음거 이동
			return;
			//남은 날짜가 소요해야할 날짜보다 작다면 다음으로 이동
		}
		
		go(index+1, max); //선택 안하고 다음거 이동
		
		max+=arr[index][1];
		go(index+arr[index][0], max); //선택 하고 n day후로 이동
		
		
		
	}
}
