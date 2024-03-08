import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int arr[][];
	static boolean visit[][];
	static int direction[]= new int[4];
	static int[] dx = {0,-1, 1, 0, 0}; //상하좌우
	static int[] dy = {0, 0, 0, -1, 1};
	static int r,c;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken()); //방의 크기
		c = Integer.parseInt(st.nextToken());
		
		arr = new int[r][c];
		visit = new boolean[r][c];
		
		int k = Integer.parseInt(br.readLine()); //장애물의 개수
		
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int kx= Integer.parseInt(st.nextToken());
			int ky= Integer.parseInt(st.nextToken());
			//arr[kx][ky]=-1; //-1은 장애물
			visit[kx][ky]=true;
		}
		
		st = new StringTokenizer(br.readLine()); //스타트 위치
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			direction[i]= Integer.parseInt(st.nextToken());
		}
		
		go(x,y);
		
	}
	
	static void go(int x, int y) {
		int dir = direction[0];
		int count=0;
		int check=0;
		visit[x][y]=true;
		
		while(true) {
			if(check==4) break;
			
			x+=dx[dir];
			y+=dy[dir];
			
			if(x>=r || y>=c || x<0 || y<0 || arr[x][y]==-1 || visit[x][y] ) {//상하좌우가 벽 또는 장애물 또는 방문한 곳이면 끝
//				System.out.println(x+" "+y);
//				System.out.println(dir);
				x-=dx[dir];
				y-=dy[dir]; //제자리로 돌아가자
				
				count++; //방향 바꿔주기
				if(count>3) {
					count=0;
				}
				dir=direction[count];  
				check++;
			}
				
			else {
				visit[x][y]=true;
				check=0;
				arr[x][y]=-5;
			}
			
//			System.out.println(x+" "+y);
//			
//			System.out.println(check);
//			for(int i=0; i<5; i++) {
//				System.out.println(Arrays.toString(arr[i]));
//			}
//			System.out.println();
			
			
		}
		
		System.out.println(x+" "+y);
	}

}
