// 처음에 queue에 넣어주기 직전에 바이러스의 인덱스별로 정렬 후 queue 에 넣어 BFS

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N, K, S, Y, X;
    static List<Node> list = new ArrayList<>();
    static Queue<Node> queue = new ArrayDeque<>();
    static int map[][];
    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]!=0) list.add(new Node(map[i][j],i,j));
            }
        }
        list.sort(Comparator.comparingInt(o -> o.idx));
        for(Node n : list) queue.offer(n);

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken())-1;
        Y = Integer.parseInt(st.nextToken())-1;

        for(int i=0;i<S;i++) {
            int size = queue.size();
            for(int j=0;j<size;j++) {
                Node temp = queue.poll();
                for(int k=0;k<4;k++) {
                    int ny = temp.y+dy[k];
                    int nx = temp.x+dx[k];
                    if(ny<0 || ny>=N || nx<0 || nx>=N ||map[ny][nx]!=0) continue;
                    map[ny][nx] = temp.idx;
                    queue.offer(new Node(temp.idx, ny,nx));
                }
            }
//            for(int a=0;a<N;a++){
//                for(int b=0;b<N;b++){
//                    System.out.print(map[a][b]+" ");
//                }
//                System.out.println("");
//            }
        }
        System.out.println(map[X][Y]);
    }
    static class Node{
        int idx, y, x;
        Node(int idx, int y, int x){
            this.idx = idx;
            this.y = y;
            this.x = x;
        }
    }
}
