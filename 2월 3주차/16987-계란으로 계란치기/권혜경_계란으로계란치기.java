import java.io.*;
import java.util.*;

public class B_계란으로계란치기_16987 {
    static int N, answer = 0;
    static List<Egg> list = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            list.add(new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        
        dfs(0,0);
        System.out.println(answer);
    }
    static void dfs(int idx, int mask){
        if(idx == N || mask==Math.pow(N,2)-1 || mask==(mask&1<<idx)) {
            dfs(idx+1, mask);
            return;
        }
        Egg now = list.get(idx);
        boolean check = true;
        for(int t=0;t<=N;t++){
            if(t==idx || mask==(mask&1<<idx)) continue;
            now.S -= list.get(t).W;
            list.get(t).S -= now.W;
            dfs(idx+1);
            now.S += list.get(t).W;
            list.get(t).S += now.W;
        }
        if(check) dfs(idx+1);
    }    
    static class Egg{
        int S, W;
        Egg(int S, int W){
            this.S=S;
            this.W=W;
        }
    }
}
