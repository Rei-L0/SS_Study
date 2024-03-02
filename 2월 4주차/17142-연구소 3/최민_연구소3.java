package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_17142_연구소3 {
	static int [][]arr;
    static List<Virus> viruses = new ArrayList<>();
	static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] selectVirus;
    static int min = Integer.MAX_VALUE;
    static int n,m;
    static boolean[] visit;
	static class Virus {
        int x, y;

        public Virus(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][n];
		selectVirus = new int[m];
		

		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) {
					viruses.add(new Virus(i, j));
                }
			}
		}
		visit = new boolean[viruses.size()];
		
		comb(0,0);
		
		if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
	}
	
	static void comb(int srcIdx, int tgtIdx) { //선택할수 있는 바이러스의 조합
		if(tgtIdx == m) { 
			bfs();
			return;
		}
		
		for (int i = srcIdx; i < viruses.size(); i++) { 
			visit[i] = true;
			comb(i + 1, tgtIdx + 1);
			visit[i] = false;
		}
	}
	
	static void bfs() { //선택된 바이러스 조합 활성화, 가장 작은 시간 찾기
		Queue<Virus> queue = new ArrayDeque<>();
		int[][] visited = new int[n][n];
		for(int i=0; i<n;i++) {
			Arrays.fill(visited[i], -1);
		}
		
		int time = 0;
		for (int i = 0; i < viruses.size(); i++) {
            if (visit[i]) { //선택한 바이러스 큐에 넣고 활성화
                Virus virus = viruses.get(i);
                queue.offer(virus);
                visited[virus.x][virus.y] = 0; //현재 0부터 시작
            }
        }
		
		
		while (!queue.isEmpty()) {
            Virus current = queue.poll();
            int x = current.x;
            int y = current.y;
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (arr[nx][ny] == 0 && visited[nx][ny] == -1) {
                        queue.offer(new Virus(nx, ny));
                        visited[nx][ny] = visited[x][y] + 1;
                        time = Math.max(time, visited[nx][ny]);
                    }else if (arr[nx][ny] == 2 && visited[nx][ny] == -1) { // 비활성 바이러스를 활성 상태로 변환
                        queue.offer(new Virus(nx, ny));
                        visited[nx][ny] = visited[x][y] + 1;
                    }
                }
            }
        }
		 boolean check = true; //전체 감염 여부 확인
		 for (int i = 0; i < n; i++) {
			 for (int j = 0; j < n; j++) {
		            if (arr[i][j] == 0 && visited[i][j] == -1) {
		            	check = false;
		                break;
		            }
		      }
		      if (!check) { //감염 다 안됬음
		    	  break;
		      }
		 }
		 
		 if (check && time < min) {
			 	min = time; 
		    }
	}

}
