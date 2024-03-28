import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken()); //최대 10
		int c = Integer.parseInt(st.nextToken());
		
		char [][] map = new char[r+2][c+2]; //dummy 바다 생성
		char [][] map2 = new char[r+2][c+2]; //dummy 바다 생성
		for(int i=0; i<=r+1; i++) {
			Arrays.fill(map[i], '.'); //겉 부분 바다로 채워주기
			Arrays.fill(map2[i], '.'); //겉 부분 바다로 채워주기
		}
		
		for(int i=1; i<=r; i++) {
			String []arr = br.readLine().split("");
			for(int j=1; j<=c; j++) {
				map[i][j]=arr[j-1].charAt(0);
				map2[i][j]=arr[j-1].charAt(0);
			}
		}
		
		int[] dx = {-1, 1, 0, 0}; //상하좌우
		int[] dy = {0, 0, -1, 1}; 

		for(int i=1; i<=r; i++) {
			for(int j=1; j<=c; j++) {
				if(map[i][j]=='X') {
					int count=0;
					for(int k=0; k<=3; k++) {
						int x= i+dx[k];
						int y= j+dy[k];
						
						if(map[x][y] == '.')  count++;
						
					}
					if(count>=3) map2[i][j]='.';
				}
				
			}
		}
		
//		for(int i=0; i<=r+1; i++) {
//			System.out.println(Arrays.toString(map2[i]));
//		}
		
		int rMin=Integer.MAX_VALUE;
		int rMax=0;
		int cMin=Integer.MAX_VALUE;
		int cMax=0;
		
		for(int i=1; i<=r; i++) {
			for(int j=1; j<=c; j++) {

				if(map2[i][j] == 'X') {
					if(i<rMin) rMin=i;
				}
				if(map2[i][j] == 'X') {
					if(i>rMax) rMax=i;
				}
				if(map2[i][j] == 'X') {
					if(j<cMin) cMin=j;
				}
				if(map2[i][j] == 'X') {
					if(j>cMax) cMax=j;
				}
			}
		}
		
		for(int i=rMin; i<=rMax; i++) {
			for(int j=cMin; j<=cMax; j++) {
				
				System.out.print(map2[i][j]);
			}
			System.out.println();
		}
		
	}
}
