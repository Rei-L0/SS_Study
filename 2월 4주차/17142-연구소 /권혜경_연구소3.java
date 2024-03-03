import java.io.*;
import java.util.*;



// 연구소에서 연결된 0마다 최소 연결시간을 구해서 총 최소 시간을 구할려고 했는데
// M값을 어떻게 나눠줘야할지 잘 모르겠어서 다른 방법 고안


public class B_연구소3_17142 {
    static int N, M, count_zero = 0, answer = -1;
    static int map[][];
    static boolean visit[][];

    static PriorityQueue<Node> pqueue = new PriorityQueue<>((o1,o2)->o2.time-o1.time);
    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visit = new boolean[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]!=0) visit[i][j] = true;
            }
        }

        // 연결된 0을 찾고 각 묶음마다 걸리는 시간을 구해서 제일 많이 걸리는 시간이 작은 경우 고르기
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!visit[i][j]) check(i, j);
            }
        }
        if(pqueue.size()<M) {
            System.out.println(-1);
            return;
        }
        answer = Integer.MAX_VALUE;
        while(!pqueue.isEmpty()){
            if(answer==pqueue.peek().time) break;
            Node temp = pqueue.poll();


        }
        System.out.println(answer);
    }
    static void check(int y, int x){
        int temp, count = 0;
        Queue<Integer> virus = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        virus.offer(y*100+x);
        while(!virus.isEmpty()){
            temp = virus.poll();
            list.add(temp);
            for(int i=0;i<4;i++){
                if(temp/100+dy[i]>=N || temp/100+dy[i]<0 || temp%100+dx[i]>=N || temp%100+dx[i]<0) continue;
                if(map[temp/100][temp%100]==2) virus.offer(temp+dy[i]*M+dx[i]);
                if(visit[temp/100+dy[i]][temp%100+dx[i]]) continue;
                visit[temp/100+dy[i]][temp%100+dx[i]] = true;
                virus.offer(temp+dy[i]*100+dx[i]);
                count++;
            }
        }
        pqueue.offer(new Node(count/2, list));
    }
    static class Node{
        int time;
        List<Integer> list;
        Node(int time, List<Integer> list){
            this.time = time;
            this.list = list;
        }
    }
}
