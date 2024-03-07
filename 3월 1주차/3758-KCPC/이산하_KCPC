import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 	[부족했던 점 CHECK!]
 	0. 런타임 에러 (ArrayIndexOutOfBounds) : test case 별로 teams arrayList를 재선언 해주지 않았음.
 */

public class B_3758 {

	static int teamCnt, problemCnt, id, logCnt; // 팀의 개수, 문제의 개수, 당신 팀의 ID, 로그 엔트리 개수
	static List<Team> teams;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			teamCnt = Integer.parseInt(st.nextToken());
			problemCnt = Integer.parseInt(st.nextToken());
			id = Integer.parseInt(st.nextToken());
			logCnt = Integer.parseInt(st.nextToken());
			teams = new ArrayList<>();

			for (int i = 0; i < teamCnt + 1; i++) {
				teams.add(new Team(i));
			}

			for (int l = 0; l < logCnt; l++) {
				st = new StringTokenizer(br.readLine());
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());

				cal(i, j, s, l);
			}

			Collections.sort(teams);
			int answer = 1;
			for (int i = 0; i < teamCnt + 1; i++) {
				if (teams.get(i).id == 0)
					continue;

				if (teams.get(i).id == id) {
					break;
				}
				answer += 1;

			}
			System.out.println(answer);

		}

	}

	static class Team implements Comparable<Team> {
		int id;
		int[] problemPoint;
		int submitCnt;
		int totalPoint;
		int submitTime;

		Team(int id) {
			this.id = id;
			this.problemPoint = new int[problemCnt + 1]; // 0 dummy
			this.submitCnt = 0;
			this.totalPoint = 0;
			this.submitTime = Integer.MAX_VALUE;
		}

		@Override
		public int compareTo(Team t) {
			if (this.totalPoint == t.totalPoint) {
				if (this.submitCnt == t.submitCnt) {
					return this.submitTime - t.submitTime;
				}
				return this.submitCnt - t.submitCnt;
			}
			return t.totalPoint - this.totalPoint;
		}

		@Override
		public String toString() {
			return "Team [id=" + id + ", problemPoint=" + Arrays.toString(problemPoint) + ", submitCnt=" + submitCnt
					+ ", totalPoint=" + totalPoint + ", submitTime=" + submitTime + "]";
		}

	}

	static void cal(int teamId, int problemNm, int score, int submitTime) {
		Team nowTeam = teams.get(teamId);
		nowTeam.totalPoint -= nowTeam.problemPoint[problemNm];
		nowTeam.problemPoint[problemNm] = Math.max(nowTeam.problemPoint[problemNm], score);
		nowTeam.totalPoint += nowTeam.problemPoint[problemNm];
		nowTeam.submitCnt += 1;
		nowTeam.submitTime = submitTime;
	}

}
