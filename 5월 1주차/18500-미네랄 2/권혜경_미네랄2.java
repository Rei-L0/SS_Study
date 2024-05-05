// 이사람 풀이를 참조해서 다시 풀 예정,,

import java.io.*;
import java.util.*;

public class Main_B18500 {
	static char[][]map;
	static int R,C;
	static boolean[][] vistied;
	static int[]dx= {1,-1,0,0};
	static int[]dy= {0,0,1,-1};
	static Queue<int[]>cluster=new LinkedList<>();;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		StringBuilder sb=new StringBuilder();
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		map=new char[R][C];
		for(int i=0;i<R;i++)map[i]=br.readLine().toCharArray();
		
		int cnt=Integer.parseInt(br.readLine());
		st=new StringTokenizer(br.readLine());
		
		for(int t=0;t<cnt;t++){
			int height=R-Integer.parseInt(st.nextToken());
			remove(t%2,height);
			vistied=new boolean[R][C];
			//바닥에 있는 미네랄 모두 방문 체크
			for(int i=0;i<C;i++) {
				if(map[R-1][i]=='x'&&!vistied[R-1][i])
					bfs(R-1,i);
			}
			boolean downCheck=false;
			//공중에 있는 클러스터를 찾아 아래로
			//문제에서 1개이하의 클러스터만 떨어진다고 했으므로 하나가 떨어지면 반복문 종료 
			for(int i=0;i<R;i++) {
				if(downCheck)break;
				for(int j=0;j<C;j++) {
					if(map[i][j]=='x'&&!vistied[i][j]) {
						bfs(i,j);
						down();
						downCheck=true;
						break;
					}
				}
			}
		}
		for(char[]a:map) {
			for(char c:a) {
				sb.append(c);
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	//높이와 방향에 따라 미네랄 파괴
	static void remove(int d,int height) {
		int i=0;
		if(d==0) {
			for(i=0;i<C;i++) {
				if(map[height][i]=='x') {
					map[height][i]='.';
					break;
				}
			}
		}
		else {
			for(i=C-1;i>=0;i--) {
				if(map[height][i]=='x') {
					map[height][i]='.';
					break;
				}
			}
		}
	}
	//클러스터 찾기
	static void bfs(int x,int y) {
		Queue<int[]>q=new LinkedList<>();
		cluster.clear();
		cluster.add(new int[] {x,y});
		q.add(new int[] {x,y});
		vistied[x][y]=true;
		while(!q.isEmpty()) {
			int[]temp=q.poll();
			for(int i=0;i<4;i++) {
				int nx=temp[0]+dx[i];
				int ny=temp[1]+dy[i];
				if(nx>=0&&ny>=0&&nx<R&&ny<C&&!vistied[nx][ny]&&map[nx][ny]=='x') {
					vistied[nx][ny]=true;
					cluster.add(new int[] {nx,ny});
					q.add(new int[] {nx,ny});
				}
			}
		}
	}
	//분리되어 있는 클러스터를 아래로 이동
	static void down() {
		int[][]temp=new int[cluster.size()][2];
		int i=0,minus=0,size=temp.length;
		while(!cluster.isEmpty()) {
			temp[i++]=cluster.poll();
		}
		change(0, size, temp, '.');
		while(true) {
			if(check(minus+1, size, temp))minus++;
			else break;
		}
		change(minus, size, temp, 'x');
	}
	//map 상태 변경
	static void change(int minus,int size,int[][] temp,char c) {
		for(int i=0;i<size;i++) {
			int nx = temp[i][0]+minus;
			int ny = temp[i][1];
			map[nx][ny]=c;
		}
	}
	//내려갈 수 있는지 확인
	static boolean check(int minus,int size,int[][] temp) {
		for(int i=0;i<size;i++) {
			int nx = temp[i][0]+minus;
			int ny = temp[i][1];
			if(nx>=R||map[nx][ny]=='x') return false;
		}
		return true;
	}
}
