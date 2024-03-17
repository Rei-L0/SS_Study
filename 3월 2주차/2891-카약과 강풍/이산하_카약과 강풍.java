import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*

    #1
    10 1 1
    1 
    3

    정답: 1
    고칠 배가 처음부터 없는 경우 

    #2
    10 5 2
    1 2 3 6 7 
    7 8
    정답: 4
    7은 자기자신부터 없앤다. 남은 고장난 배는 1,2,3,6. 

    7이 6 고치고, 8이 7 고쳐서 고장난 배가 1,2,3 인 경우는 안되는걸 인지해야한다. => 된다는 경우라고 잘못 읽음

    => 무조건 2(카약이 여분이 있는)와 0(카약이 부서진) 팀이 서로 나란히 있을 때만 판단하면 되는 것. 
    1(자신의 것을 써야하는 팀)인 경우 다른 팀에게 줄 수 없음 => 카약이 부서졌다가 여분도 있었던 팀도 포함.

 */
public class B_2891 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int S = Integer.parseInt(st.nextToken());
    int R = Integer.parseInt(st.nextToken());
    int[] team = new int[N + 1]; // 0 dummy

    int answer = 0;
    Arrays.fill(team, 1);
    // 카약이 손상된 팀
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < S; i++) {
      team[Integer.parseInt(st.nextToken())] -= 1;
    }

    // 카약을 하나 더 가져온 팀
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < R; i++) {
      team[Integer.parseInt(st.nextToken())] += 1;
    }

    for (int i = 1; i < N; i++) {
      if (team[i] == 0 & team[i + 1] == 2) {
        team[i] += 1;
        team[i + 1] -= 1;

      } else if (team[i + 1] == 0 && team[i] == 2) {
        team[i] -= 1;
        team[i + 1] -= 1;
      }
    }
    for (int i = 1; i <= N; i++) {
      if (team[i] == 0)
        answer += 1;

    }
    System.out.println(answer);
  }
}
