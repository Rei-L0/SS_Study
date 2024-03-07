import java.io.*;
import java.util.*;
public class B_KCPC_3758 {
    static int T, N, K, ID, M; 
    static StringBuilder sb = new StringBuilder();
    static Map<Integer, int[]> map;
    static PriorityQueue<Team> pqueue;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++){
            map = new HashMap<>();
            pqueue = new PriorityQueue<>((o1,o2)->o2.score==o1.score?(o1.submit==o2.submit? o1.recently-o2.recently: o1.submit-o2.submit ): o2.score-o1.score );
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            ID = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            // 0 아이디별 제줄횟수, 1~ID 번호별 점수, ID+1 아이디별 최종 제출번
            map.put(0, new int[N+1]);
            map.put(N+1, new int[N+1]);

            for(int i=1;i<=N;i++){
                // 0 총 점수 1~K 번호별 최고점
                map.put(i, new int[K+1]);
            }
            for(int i=0;i<M;i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                map.get(a)[b] = Math.max(map.get(a)[b], c);
                map.get(N+1)[a] = i;
                map.get(0)[a]++; 
            }

            for(int i=1;i<=N;i++){
                int score = 0;
                for(int j=1;j<=K;j++){
                    score += map.get(i)[j];
                }
                map.get(i)[0] = score; 
                pqueue.offer(new Team(i, score, map.get(0)[i], map.get(N+1)[i]));
            }
            for(int i=1;i<=N;i++) {
                Team temp = pqueue.poll(); 
                if(temp.idx != ID) continue;
                sb.append(i).append("\n");
                break;
            }
        }
        System.out.println(sb.toString());
    }
    static class Team{
        int idx, score, submit, recently;
        Team(int idx, int score, int submit, int recently){
            this.idx = idx;
            this.score = score;
            this.submit = submit;
            this.recently = recently;
        }
    }
}
