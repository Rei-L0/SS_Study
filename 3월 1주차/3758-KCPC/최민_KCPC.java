import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static class Team  {
		int TeamID;
		int arr[]; //문제 점수
		int lastSubmit; //마지막 제출 시간
		int countSubmit; //제출 횟수
		int total; //최종 점수
	
		public Team() {
			super();
		}

		public Team(int TeamId, int[] arr, int lastSubmit, int countSubmit, int total) {
			super();
			this.TeamID = TeamId;
			this.arr = arr;
			this.lastSubmit = lastSubmit;
			this.countSubmit = countSubmit;
			this.total = total;
		}	
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int c=0; c<T; c++) {
			StringTokenizer st  = new StringTokenizer(br.readLine());
			
			int n=Integer.parseInt(st.nextToken()); //팀의 개수
			int k=Integer.parseInt(st.nextToken()); //문제의 개수
			int t=Integer.parseInt(st.nextToken()); //당신 팀의 ID
			int m=Integer.parseInt(st.nextToken()); //로그 엔트리의 개수
			
			Team[] team = new Team[n+1]; //0은 dummy
			
			for(int i=0; i<=n; i++) {
				team[i]=new Team();
				team[i].arr=new int[k+1]; //0은 dummy
			}
			
			for(int j=0; j<m; j++) {
				st= new StringTokenizer(br.readLine());
				int id = Integer.parseInt(st.nextToken()); //팀 ID
				int q = Integer.parseInt(st.nextToken()); //문제 번호
				int score = Integer.parseInt(st.nextToken()); //획득 점수
				team[id].total+=score;
				team[id].TeamID=id;
				if(team[id].arr[q]<score) { //저장된거보다 크면은 바꾸기
					team[id].total-=team[id].arr[q];
					team[id].arr[q]= score;
				}else {
					team[id].total-=score;
				}
				
				team[id].countSubmit ++;
				team[id].lastSubmit=j;
			}
			
			Arrays.sort(team, new Comparator<Team>() {
				@Override
				public int compare(Team o1, Team o2) {

						if(o1.total==o2.total) { //점수 같으면 풀이 제출 횟수 적은 팀
							if(o1.countSubmit == o2.countSubmit) {
								return o1.lastSubmit-o2.lastSubmit;
							}
							//제출횟수도 같으면 마지막 제출시간이 더 빠른 팀
							return o1.countSubmit-o2.countSubmit;
						}
						return o2.total-o1.total; //이 친구는 내림차 순
					}
			});
			

			for(int i=0; i<=n; i++) {
				if(t==team[i].TeamID) {
					System.out.println(i+1);
					break;
				}
			}
		}
	}
}
