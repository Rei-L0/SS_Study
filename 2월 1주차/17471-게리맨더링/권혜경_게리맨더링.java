import java.io.*;
import java.util.*;
// 비트마스킹으로 다 한 후 마지막에 연결되지 않는 경우는 nocount가 맞을까
// node끼리 연결되지 않을 경우 전진X 로 접근하는게 맞을까?
public class 게리맨더링_17471 {
    static int N, total = 0, answer = 0;
    static int[] people;
    static int link[][];
    static Map<Integer,Integer> map = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        people = new int[N];
        link = new int[N][N];
        for(int i=0;i<N;i++) Arrays.fill(link[i],-1);
        StringTokenizer st  = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            people[i] = Integer.parseInt(st.nextToken());
            total += people[i];
        }
        for(int i=0;i<N;i++){
            st  = new StringTokenizer(br.readLine());
            int K =  Integer.parseInt(st.nextToken());
            if(K==0) answer+=1;
            for(int j=0;j<K;j++){
                int M = Integer.parseInt(st.nextToken())-1;
                link[i][M] = 0;
                link[M][i] = 0;
            }
        } 
        if(answer>1){ 
            if(N==2) System.out.println(Math.abs(people[0]-people[1]));
            else System.out.println(-1);
            return;
        }
        answer=Integer.MAX_VALUE;

        dfs(0,0);
        //System.out.println("-----");
        for(int m : map.keySet()){
            int m_ = (int)Math.pow(2,N)-1-m;
            //System.out.println(m+" "+map.get(m)+" "+m_);
            if(map.containsKey(m_))
                answer = answer>Math.abs(map.get(m)-map.get(m_)) ? Math.abs(map.get(m)-map.get(m_)) :answer;

        }
        System.out.println(answer);
    }
    static void dfs(int idx, int m){
        if(idx==N) {
            if(m==0||m==Math.pow(2,N)-1) return;
            Deque<Integer> queue = new ArrayDeque<>();
            int check = 0, sum=0, mask = m;
            for(int i=0;i<N;i++){
                if((mask & 1 << i)!=0) {
                    mask = mask & ~(1<<i);
                    queue.offer(i); 
                    sum += people[i];
                    break;
                }
            }
            while(!queue.isEmpty()){
                check = queue.poll();
                for(int i=0;i<N;i++) {
                    if((mask & 1 << i)!=0 && link[check][i]!=-1) {
                        mask = mask & ~(1<<i);
                        sum += people[i];
                        queue.offer(i);
                    }
                }
            }
            if (mask==0) map.put(m,sum);
            return;
        }
       
        //비선택
        dfs(idx+1, m);
        //선택
        dfs(idx+1, m|1<<idx);
    }
}
