import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int [] x = new int[N];
    int [] y = new int[N];
    long result = 0;

    for(int i = 0; i < N; i++){
      st = new StringTokenizer(br.readLine().trim());
      x[i] = Integer.parseInt(st.nextToken().trim());
      y[i] = Integer.parseInt(st.nextToken().trim());
    }
    Arrays.sort(x);
    Arrays.sort(y);

    for(int i = 0; i < N; i++){
      result += Math.abs(x[i]-x[N/2]);
      result += Math.abs(y[i]-y[N/2]);
    }
    System.out.println(result);
  
  }
}
