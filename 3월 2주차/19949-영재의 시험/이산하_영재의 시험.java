import java.io.BufferedReader;
import java.io.InputStreamReader; 
import java.util.StringTokenizer;

public class B_19949 {
  static int[] answers;
  static int answer;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    answers = new int[10];
    answer = 0;

    for (int i = 0; i < 10; i++) {
      answers[i] = Integer.parseInt(st.nextToken());
    }
    dfs(0, 0, 0, 0);
    System.out.println(answer);
  }

  // 푼 문제 수, 바로 전에 골랐던 문제 번호,
  // 바로 전에 골랐던 문제 번호를 연속적으로 사용했는지에 대한 cnt변수, 맞은 문제 수
  static void dfs(int problemN, int beforeN, int conseCnt, int correctCnt) {
    if (problemN == 10) {
      if (correctCnt >= 5) {
        answer += 1;
        return;
      }
    }

    if ((10 - problemN) + correctCnt < 5)
      return;

    boolean check = false;
    for (int i = 1; i < 6; i++) {
      if (i == beforeN) {
        if (conseCnt >= 2)
          continue; // 연속된 수 3회 이상은 불가

        if (i == answers[problemN])
          dfs(problemN + 1, i, conseCnt + 1, correctCnt + 1); // 정답과 고른 답이 같으면
        else
          dfs(problemN + 1, i, conseCnt + 1, correctCnt); //
        continue;
      }
      if (i == answers[problemN])
        dfs(problemN + 1, i, 1, correctCnt + 1); // 정답과 고른 답이 가트면
      else
        dfs(problemN + 1, i, 1, correctCnt);
    }
  }
}
