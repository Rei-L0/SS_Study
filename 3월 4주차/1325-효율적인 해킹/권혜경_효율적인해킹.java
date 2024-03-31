import java.io.*;
import java.util.*;

public class Main {
    static int N, M, count=0;
    static List<Integer> answer = new ArrayList<>();
    static List<List<Integer>> list = new ArrayList<>();
    static boolean num[];
    static int trust[];
    
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        num = new boolean [N+1];
        trust = new int [N+1];
        for(int i=0;i<=N;i++){
            list.add(new ArrayList<>());
        }
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            if(A==B || list.get(A).contains(B)) continue;
            trust[B]++;
            list.get(A).add(B);
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=1;i<=N;i++){
            if(trust[i]==0) queue.offer(i);
            else count++;
        }
        while(!queue.isEmpty()){
            answer.clear();
            int size = queue.size();
            for(int i=0;i<size;i++){
                int temp = queue.poll();
                if(num[temp]) continue;
                num[temp] = true;
                answer.add(temp);
                for(int j : list.get(temp)){
                    if(num[j]) continue;
                    trust[j]--;
                    if(trust[j]==0) {
                        queue.add(j);
                        count--;
                    }
                }
            }
        }
        
        if(count!=0){
            for(int i=1;i<=N;i++){
                if(trust[i]!=0) sb.append(i).append(" ");
            }
        }else{
            Collections.sort(answer);
            for(int a : answer)
                sb.append(a).append(" ");
        }

        System.out.println(sb.toString());
    }
}
