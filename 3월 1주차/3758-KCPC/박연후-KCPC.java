import java.io.*;
import java.util.*;

public class Main {

    static int t, n, k, id, m;

    static ArrayList<Team> list;

    // 팀 정보 클래스
    static class Team implements Comparable<Team> {

        int num;
        int score;
        int count;
        int time;
        int[] problem;

        public Team(int num, int score, int count, int time) {
            this.num = num;
            this.score = score;
            this.count = count;
            this.time = time;
            problem = new int[k + 1];
            Arrays.fill(problem, -1);
        }

        // 최종 점수 내림차순 정렬
        // 최종 점수 같을 경우 풀이 제출 횟수 오름차순 정렬
        // 제출 횟수 같을 경우 마지막 제출 시간 오름차순 정렬
        @Override
        public int compareTo(Team o) {
            if (this.score == o.score) {
                if (this.count == o.count) {
                    return Integer.compare(this.time, o.time);
                }
                return Integer.compare(this.count, o.count);
            }
            return Integer.compare(o.score, this.score);
        }
    }

    static void solve(int x, int y, int score, int time) {
        Team now = list.get(x);
        // 이미 해당 문제를 풀었을 때
        if (now.problem[y] != -1) {
        	// 현재 문제가 기존 문제보다 점수가 높을 경우
        	// 최종 점수에서 기존 문제의 점수 깎은 후 현재 점수 더하기
        	// 기존 점수에서 현재 점수로 변경
            if (score > now.problem[y]) {
                now.score -= now.problem[y];
                now.problem[y] = score;
                now.score += score;
            }
        }
        // 풀지 않았을 때
        else {
            now.problem[y] = score;
            now.score += score;
        }
        now.count++;
        now.time = time;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            id = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                list.add(new Team(j, 0, 0, 0));
            }

            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());
                int tId = Integer.parseInt(st.nextToken());
                int pNum = Integer.parseInt(st.nextToken());
                int score = Integer.parseInt(st.nextToken());
                // 팀 정보 업데이트
                solve(tId - 1, pNum, score, j + 1);
            }

            // 우선순위로 정렬
            Collections.sort(list);

            for (int x = 0; x < list.size(); x++) {
            	// 팀 id가 같을 경우 순위 출력
                if (list.get(x).num == id - 1) {
                    System.out.println(x + 1);
                    break;
                }
            }
        }
    }
}