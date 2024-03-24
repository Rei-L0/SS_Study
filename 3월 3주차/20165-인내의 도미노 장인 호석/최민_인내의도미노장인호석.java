import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	static int[][] list;
	static int[][] uplist;
	static char[][] copylist;
	static int n, m, r;
	static int total=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st= new StringTokenizer(br.readLine());
		n= Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r= Integer.parseInt(st.nextToken());
				
		list = new int[n][m];
		copylist= new char[n][m];
		uplist = new int[n][m];
		for(int i=0;i<n;i++) {
			Arrays.fill(copylist[i], 'S');
		}

		for(int i=0; i<n; i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				int a= Integer.parseInt(st.nextToken());
				list[i][j] = a;
				uplist[i][j] = a;
			}
		}
		
		for(int i=0; i<r; i++) {
			st= new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
		
			down(x, y, d);
			
			st= new StringTokenizer(br.readLine());
			int upx = Integer.parseInt(st.nextToken());
			int upy = Integer.parseInt(st.nextToken());
			up(upx, upy);
		}
		System.out.println(total);
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(copylist[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	static void down(int x, int y, char d) {
		int []dx = {0, 0, 1, -1};
		int []dy = {1, -1, 0, 0};
		int count =-1;
		if(d=='E') {//동
			count=0;
		}else if(d=='W') {//서
			count=1;
		}else if(d=='S') {//남
			count=2;
		}else if(d=='N') {//북
			count=3;
		}
		//3,1 ->2,0
		//5,3 ->
		//5,2 -> 0,1
		// 3,5-> 2, 4
		
		int rx=x-1;
		int ry=y-1;
		
		int max = list[rx][ry]-1;
		
		copylist[rx][ry]='F';
		total++;
		
		while(max!=0) {
			rx += dx[count];
			ry += dy[count];
			
			max --;
			
			if(rx<0 || ry<0 || rx>=n || ry>=m) break;
			if(max<list[rx][ry] && copylist[rx][ry]!='F') max=list[rx][ry]-1;
			if(copylist[rx][ry]!='F') total++;
			copylist[rx][ry]='F';
		}
		
//		for(int i=0; i<n; i++) {
//			System.out.println(Arrays.toString(copylist[i]));
//		}
//		System.out.println();
	}
	
	static void up(int x, int y) {
		list[x-1][y-1]=uplist[x-1][y-1];
		copylist[x-1][y-1]='S';
	}
}
