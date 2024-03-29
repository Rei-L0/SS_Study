import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int T = 1;
    while (true) {
      int caseN = Integer.parseInt(br.readLine());
      if (caseN == 0) {
        break;
      }
      String[] names = new String[caseN];
      int[] cnt = new int[caseN];
      for (int i = 0; i < caseN; i++) {
        names[i] = br.readLine();
      }
      for (int i = 0; i < caseN * 2 - 1; i++) {
        st = new StringTokenizer(br.readLine());
        cnt[Integer.parseInt(st.nextToken()) - 1] += 1;
      }
      for (int i = 0; i < caseN; i++) {
        if (cnt[i] == 1) {
          System.out.println(T + " " + names[i]);
        }
      }
      T += 1;
    }
  }
}
