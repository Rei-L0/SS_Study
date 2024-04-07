//답이 안나와서 다른 사람 코드 참고함
//후에 다시 혼자 풀어서 올려볼 것 
//3개의 중간 정점을 거쳐야 함. 중간 정점은 순열을 이용하여 모든 경우 고려할 것
import java.util.*;
import java.io.*;

class Vertex implements Comparable<Vertex> {
	int no;
	long dis;

	public Vertex(int no, long dis) {
		this.no = no;
		this.dis = dis;
	}

	@Override
	public int compareTo(Vertex o) {
		// TODO Auto-generated method stub
		if(this.dis < o.dis) return -1;
		else if(this.dis > o.dis) return 1;
		else return 0;
	}
}

public class Main {
	static int N, M, p, last;
	static long distance[][];
	static boolean visit[];
	static int num[];
	static long min = 999876454321L;
	public static void permu(int arr[], int cnt) {
		if(cnt == 3) {
			long temp = distance[0][arr[num[0]]] + distance[num[0]][arr[num[1]]] + distance[num[1]][arr[num[2]]] + distance[num[2]][last];
			min = Math.min(min, temp);
			return;
		}
		
		for(int i=1; i<=p; i++) {
			if(!visit[i]) {
				visit[i] = true;
				num[cnt] = i;
				permu(arr,cnt+1);
				visit[i] = false;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ArrayList<Vertex> list[] = new ArrayList[N];
		//연결관계 정리
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			int value = Integer.parseInt(st.nextToken());
			list[start].add(new Vertex(end, value));
			list[end].add(new Vertex(start, value));
		}
		//시작 끝 지점 입력
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken())-1;
		last = Integer.parseInt(st.nextToken())-1;
		p = Integer.parseInt(br.readLine());
		
		//순열을 위한
		visit = new boolean[p+1];
		num = new int[3];
		
		//거치는 점들
		int arr[] = new int[p + 1];
		st = new StringTokenizer(br.readLine());
		//시작지점은 0
		arr[0] = start;
		for (int i = 1; i < p+1; i++) {
			arr[i] = Integer.parseInt(st.nextToken())-1;
		}
		//다익스트라 p+1개
		distance = new long[p+1][N];
		for (int i = 0; i <p+1; i++) {
			Arrays.fill(distance[i], 999876454321L);
			distance[i][arr[i]]= 0;
		}
		//다익스트라 돌기
		for (int i = 0; i < p + 1; i++) {
			PriorityQueue<Vertex> pq = new PriorityQueue<>();
			pq.add(new Vertex(arr[i], 0));
			while (!pq.isEmpty()) {
				Vertex cur = pq.poll();
				if (distance[i][cur.no] < cur.dis)
					continue;
				for (int j = 0; j < list[cur.no].size(); j++) {
					Vertex next = list[cur.no].get(j);
					if (distance[i][next.no] > cur.dis + next.dis) {
						distance[i][next.no] = cur.dis + next.dis;
						pq.add(new Vertex(next.no, distance[i][next.no]));
					}
				}
			}
		}
		//순열
		permu(arr,0);
		
		if(min == 999876454321L) min = -1;
		System.out.println(min);
		br.close();
	}
}
