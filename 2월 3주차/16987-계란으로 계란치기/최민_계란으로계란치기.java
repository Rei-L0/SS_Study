package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_16987_계란으로계란치기 {
	static int N,ans=0;
	static int arr[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N= Integer.parseInt(br.readLine());
		arr= new int[N][2];
		
		for(int i=0; i<N ;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0]=Integer.parseInt(st.nextToken()); //내구도
			arr[i][1]=Integer.parseInt(st.nextToken()); //무게
		}
		
		egg(0);
		System.out.println(ans);
	}
	
	public static void egg(int now ) {
		if(now==N) {// 가장 마지막 달걀
			int count=0;
			for(int i=0; i<N; i++) {
				if(arr[i][0]<=0)count++;
			}
			if(count>ans) {
				ans=count;
			}
			return; 
		}
		if(arr[now][0]<=0) egg(now+1); //이미 부서진 달걀이면 다음으로
		else {// 아니라면
			boolean flag = true;
			for(int i=0; i<N; i++) {
				if(i==now || arr[i][0] <=0) continue; //자기 자신이거나 이미 깨진거면 다음거
				
				flag=false;
				arr[i][0]-=arr[now][1];
				arr[now][0]-=arr[i][1]; // 계란 깨기
				egg(now+1);
				arr[i][0]+=arr[now][1];
				arr[now][0]+=arr[i][1]; // 계란 복구
			}
			if(flag)egg(now+1); //아무 계란을 깨지 못한 경우 다음으로 넘어간다.
			
		}
		
	}
}
