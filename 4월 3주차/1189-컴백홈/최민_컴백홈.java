import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//거리가 K인 가짓수 구하기
    //dfs
	static int R,C,K;
	static Character map[][];
	static boolean visit[][];
	static int[] dx= {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int result=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new Character[R][C];
		visit = new boolean[R][C];
		
		for(int i=0; i<R; i++) {
			String[] a = br.readLine().split("");
			for(int j=0; j<C; j++) {
				map[i][j] = a[j].charAt(0);
			}
		}

		visit[R-1][0] = true;
		dfs(R-1, 0, 1);
		
		System.out.println(result);
	}
	
	static void dfs(int x, int y, int count) {
		if(count==K && x==0 && y==C-1) { //K값과 이동한 수가 같을때 집에 도착했다면 return
			result++;
			//System.out.println(x+" "+y + "도착 확인");
			return;
		}
		
		else if(count>K) { //K값보다 count가 크다면 답이 아니므로 그만
			return;
		}
		
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(nx<0 || ny<0 || nx>=R || ny>=C ) continue;
			if(visit[nx][ny] || map[nx][ny].equals('T')) continue;
			visit[nx][ny]=true;
			count++;
			//System.out.println(nx+" "+ny);
			
			dfs(nx,ny,count);
			
			visit[nx][ny]=false;
			count--;	
		}
	}
}
