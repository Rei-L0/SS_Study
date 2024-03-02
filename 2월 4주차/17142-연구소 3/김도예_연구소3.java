import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// comb + bfs
public class Main {

    static int N, M, min, virusSize;
    static int[][] map, copyMap;

    static List<Node> virus = new ArrayList<>();
    static Node[] origin; // 활성화 되는 바이러스 조합 tgt

    static int[] dy = { 1, -1, 0, 0 };
    static int[] dx = { 0,  0,-1, 1 };
    static Queue<Node> queue = new ArrayDeque<>();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][N];
        origin = new Node[M];
        
        boolean f = false;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if( n == 2 ) {
                    virus.add(new Node(i, j));
                }
                if(n == 0) f = true;
                map[i][j] = n;
            }
        }
        min = Integer.MAX_VALUE;
        virusSize = virus.size();
        if(f) comb(0, 0);
        else min = 0;
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void comb(int srcIdx, int tgtIdx) {
        if(tgtIdx == M) {
            check();
            return;
        }
        
        if( srcIdx == virusSize ) return;
        
        origin[tgtIdx] = virus.get(srcIdx);
        
        comb(srcIdx + 1, tgtIdx + 1);
        comb(srcIdx + 1, tgtIdx);
    }
    
    static void check() {
        copyMap = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        // 바이러스 활성화 
        for (int i = 0; i < M; i++) {
            int y = origin[i].y;
            int x = origin[i].x;
            copyMap[y][x] = 3;
            queue.offer(new Node(y, x, 0));
        }
        // 바이러스 퍼뜨리기 
        int c = 0;
        while(! queue.isEmpty() ) {
            Node n = queue.poll();
            if(n.cnt > c && copyMap[n.y][n.x] == 3) c = n.cnt;
            for (int i = 0; i < 4; i++) {
                int ny = n.y + dy[i];
                int nx = n.x + dx[i];
                if( ny < 0 || nx < 0 || ny >= N || nx >= N ) continue;
                if( copyMap[ny][nx] == 0) {
                    copyMap[ny][nx] = 3; 
                    queue.offer(new Node(ny, nx, n.cnt + 1));
                }              
                if(copyMap[ny][nx] == 2) {
                	copyMap[ny][nx] = 4;
                	queue.offer(new Node(ny, nx, n.cnt + 1));
                }
            }
        }
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < N; j++) {
        		if(copyMap[i][j] == 0) c = Integer.MAX_VALUE;
        	}
        }
        min = Math.min(min, c);
    }
    
    static class Node{
        int y, x, cnt;
        Node(int y, int x){
            this.y = y;
            this.x = x;
        }
        Node(int y, int x, int cnt){
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
        
    }
}
