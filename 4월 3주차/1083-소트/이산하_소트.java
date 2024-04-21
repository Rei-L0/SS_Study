import java.util.*;
import java.io.*;

public class B_1083 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    ArrayList<Integer> list = new ArrayList<>();

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      list.add(Integer.parseInt(st.nextToken()));
    }

    int S = Integer.parseInt(br.readLine());

    for (int i = 0; i < N && 0 < S; i++) {
      int max = 0, maxIdx = 0;

      for (int j = i; j < N && j <= S + i; j++) {
        if (max <= list.get(j)) {
          max = list.get(j);
          maxIdx = j;
        }
      }

      list.remove(maxIdx);
      list.add(i, max);

      S -= (maxIdx - i);
    }
    StringBuilder sb = new StringBuilder();
    for (int num : list) {
      sb.append(num).append(" ");
    }

    System.out.println(sb.toString());
  }
}
