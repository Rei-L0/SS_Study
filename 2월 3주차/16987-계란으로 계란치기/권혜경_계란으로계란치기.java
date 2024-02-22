import java.io.*;
import java.util.*;

public class Main {
    static int N, answer = 0;
    static ArrayList<Egg> list = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            list.add(new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        
        dfs(0);
        System.out.println(answer);

    }
    static void dfs(int idx){
        if(idx == N){
            int count = 0;
            for(int i=0;i<N;i++){
                if(list.get(i).S<=0) count++;
            }
            answer = Math.max(answer, count);
            return;
        }
        Egg now = list.get(idx);
        boolean check = false;
        if(now.S<=0) {
            dfs(idx+1);
            return;
        }
        for(int t=0;t<N;t++){
            if(t==idx) continue;
            if(list.get(t).S<=0) continue;
            check = true;
            now.S-=list.get(t).W;
            list.get(t).S -= now.W;
            dfs(idx+1);
            now.S += list.get(t).W;
            list.get(t).S += now.W;
        }
        if(!check) dfs(idx+1);
    }    
    static class Egg{
        int S, W;
        Egg(int S, int W){
            this.S=S;
            this.W=W;
        }
    }
}
