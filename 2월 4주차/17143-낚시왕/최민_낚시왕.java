//흐어.. 미완성 풀이입니다
//문제점 발견하시면.. 말씀주세요.. 많관부...

package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 낚시왕_17143 {
	static int[] dx = {0, -1, 1, 0, 0}; 
	static int[] dy = {0, 0, 0, 1, -1};
	static int total=0;
	static int R,C,M;
	static int arr[][];
	static List<Shark> sharks;
	static class Shark{
		int r; //위치
		int c; 
		int s; //속력
		int d; //방향
		int z; //크기
		
		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); //상어수

		sharks = new ArrayList<>();
		arr = new int[R + 1][C + 1]; //0은 dummy
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            sharks.add(new Shark(r, c, s, d, z));
            arr[r][c] = z;
		}
		
		for (int[] list : arr) {
			System.out.println(Arrays.toString(list));
		}
		System.out.println();
		
		for(int i=1; i<=C; i++) { //낚시왕이 이동
			catchShark(i);
			move();
			
			for (int[] list : arr) {
				System.out.println(Arrays.toString(list));
			}
			System.out.println();
		}
		
		System.out.println(total);
	}
	
	static void catchShark(int y) {//상어 잡기
		for(int x=1; x<=R; x++) {
			if(arr[x][y]!=0) {
				final int currentX=x;
				total +=arr[x][y]; //잡은 합에 더하고
				arr[x][y] =0; //빈 칸으로 만들어준다.
				sharks.removeIf(shark -> shark.r==currentX && shark.c == y); //리스트에서 상어 삭제
				//x로 하니까 Local variable x defined in an enclosing scope must be final or effectively final 오류 발생해서 찾아보니까 final 쓰라함
				break;
			}
		}
		return;
	}
	
	static void move() {
		int [][] newarr = new int[R+1][C+1]; //상어가 중간에 겹쳐서.. 자꾸.. 이상했구나.. 응.. 중복방지를 위해 생성
		List<Shark> newSharks = new ArrayList<>();
		for (Shark shark : sharks) { //각 상어 이동
			int speed = shark.s;
            int direction = shark.d;
            int x = shark.r;
            int y = shark.c;
            
			while(speed > 0) {
				x += dx[direction];
                y += dy[direction];
                
                
                
                if (direction == 1 && x == 1) {
                    direction = 2; // 위-> 아래
                } else if (direction == 2 && x == R) {
                    direction = 1; // 아래->위
                } else if (direction == 3 && y == C) {
                    direction = 4; // 오른쪽 -> 왼쪽
                } else if (direction == 4 && y == 1) {
                    direction = 3; // 왼쪽 -> 오른족
                }
                
                if(speed==1) {
                	 if (newarr[x][y] == 0 || newarr[x][y] < shark.z) { //0이거나 기존보다 크다면 현재 상어의 크기로 저장
                     	if (newarr[x][y] != 0) {
                     		final int currentX=x;
                     		final int currentY=y;
                             newSharks.removeIf(lastshark -> lastshark.r == currentX && lastshark.c == currentY); //잡아먹힌 상어도 삭제
                         }
                     	newarr[x][y] = shark.z;
                         newSharks.add(new Shark(x, y, shark.s, direction, shark.z)); 
                     }
                }
               
                speed--; //스피드만큼 반복하기 위해서
			}
		}
		
		sharks = newSharks;
		 for (int i = 1; i <= R; i++) {//격자판 복사
	            System.arraycopy(newarr[i], 0, arr[i], 0, newarr[i].length);
	            //newarr[i]를 arr[i]로 복사, 복사시작 위치 0, 복사 배열 길이 newarr[i].length
	            //System.arraycopy(원본배열, 원본배열의 시작 인덱스, 대상배열, 대상배열의 시작 인덱스, 복사할 요소의 개수);
	     }
	}
}
