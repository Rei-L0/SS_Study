import java.io.*;
import java.util.*;

//시간초과

public class B_그래프의줄기_24461 {
    static int N, count = 0; //count 가장자리 노드가 2 초과인 노드
    static int node[];
    static boolean visit[];
    static List<List<Integer>> link = new ArrayList<>();
    static Queue<Integer> queue = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        node = new int[N];
        visit = new boolean[N];
        for(int i=0;i<N;i++){
            link.add(new ArrayList<>());
        }
        
        int A, B;
        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            node[A]++;
            node[B]++;
            link.get(A).add(B);
            link.get(B).add(A);
        }

        for(int i=0;i<N;i++){
            if(node[i]>2) count++;
            if(node[i]!=1) continue;
            
            node[i]--;
            queue.offer(i);
        }

        TopologicalSort();

        System.out.println(sb.toString());
    }
    static void TopologicalSort(){
        int size;
        while(count>0){
            size = queue.size();
            for(int i=0;i<size;i++){
                int temp = queue.poll();
                if(visit[temp]) continue;

                int zero = 0;
                for(int j : link.get(temp)){
                    if(visit[j]) {
                        zero++;
                        continue;
                    }
                    node[j]--;
                    if(node[j]==2) count--;
                    else if(node[j]==0){
                        zero++;
                        queue.offer(j);
                    }
                }
                if(zero == link.get(temp).size()) visit[temp] = true;
            }
        }
        for(int i=0;i<N;i++){
            if(node[i]!=0) sb.append(i).append(" ");
        }
    }
}
