import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); //팀의 수
		int s = Integer.parseInt(st.nextToken()); //손상된 팀의 수
		int r = Integer.parseInt(st.nextToken()); //카약을 하나 더 가져온 팀의 수
		
		int []arr = new int[n+1]; //0은 dummy
		
		st = new StringTokenizer(br.readLine()); //카약이 손상된 팀의 번호
		for(int i=0; i<s; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[num]=-1;
		}
		//0은 내 카약 존재, 1은 여유분 존재, -1은 카약 손상
		st = new StringTokenizer(br.readLine()); //카약을 하나 더 가져온 팀 + 자기팀 카약이 손상되었다면 자기팀 먼저 수리해야한다.
		for(int i=0; i<r; i++) {
			int num=Integer.parseInt(st.nextToken());
			arr[num]+=1; //
		}
		
		for(int i=1; i<=n; i++) {
			if(arr[i]==1) { // 여유분이 존재한다면
				if(i-1>=0) {
					if(arr[i-1]==-1) {
						arr[i-1]++;
						arr[i]--;
						continue;//남 줬으니 다음거
					}
				}
				if(i<=n-1) {
					if(arr[i+1]==-1) {
						arr[i+1]++;
						arr[i]--;
					}
				}
			}
		}
		
		int count=0;
		for(int i=1; i<=n; i++) {
			if(arr[i]==-1) count++;
		}
		System.out.println(count);
	}
}
