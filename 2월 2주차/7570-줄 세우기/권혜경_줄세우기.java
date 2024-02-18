import java.io.*;
import java.util.*;

public class 줄세우기_7570 {
    static int children[];
    static int move[]; //np

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        children = new int [N];
        move = new int [N+1];
        for(int i = 0; i < N; i++){
            children[i] = Integer.parseInt(st.nextToken());
            move[children[i]] = move[children[i]-1]+1;
        }
        Arrays.sort(move);
        System.out.println(N-move[N]);
    }
}
