import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 35% 틀림 - cnt 세는게 이상햇고, 얼마만큼 방문을 할 수 있는지에 대한 cnt의 범위를 정하지 못함.
public class B_31404 {
	 static int[] dy = { -1, 0, 1, 0 };
	    static int[] dx = { 0, 1, 0, -1 };

	    public static void main(String[] args) throws Exception {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        int H = Integer.parseInt(st.nextToken());
	        int W = Integer.parseInt(st.nextToken());

	        st = new StringTokenizer(br.readLine());
	        int ArisY = Integer.parseInt(st.nextToken());
	        int ArisX = Integer.parseInt(st.nextToken());
	        int ArisD = Integer.parseInt(st.nextToken());

	        int[][] A = new int[H][W];
	        int[][] B = new int[H][W];

	        for (int i = 0; i < H; i++) {
	            String[] input = br.readLine().split("");
	            for (int j = 0; j < W; j++) {
	                A[i][j] = Integer.parseInt(input[j]);
	            }
	        }

	        for (int i = 0; i < H; i++) {
	            String[] input = br.readLine().split("");
	            for (int j = 0; j < W; j++) {
	                B[i][j] = Integer.parseInt(input[j]);
	            }
	        }

	        int[][] visit = new int[H][W];
	        for (int i = 0; i < H; i++) {
	            Arrays.fill(visit[i], -1);
	        }
	        int time = 0;
	        int cntB = 0;
	        while (true) {

	            if (visit[ArisY][ArisX] != -1) { // 이미 먼지를 제거했을 경우 -> B
	                visit[ArisY][ArisX] = ArisD;
	                ArisD = (ArisD + B[ArisY][ArisX]) % 4;
	                ArisY += dy[ArisD];
	                ArisX += dx[ArisD];
	                cntB++;
	            } else { // A
	                visit[ArisY][ArisX] = ArisD;
	                ArisD = (ArisD + A[ArisY][ArisX]) % 4;
	                ArisY += dy[ArisD];
	                ArisX += dx[ArisD];
	                cntB = 0;
	            }
	            
	            time += 1;
	            
	            if (ArisY < 0 || ArisY > H - 1 || ArisX < 0 || ArisX > W - 1) {
	                break;
	            }
	                
	      
	            if(cntB >= 100000) {
	                break;
	            }
	            
	        }
            System.out.println(time-cntB);

	    }

	}
