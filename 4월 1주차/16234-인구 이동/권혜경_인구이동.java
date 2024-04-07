import java.io.*;
import java.util.*;

public class B_인구이동_16234 {
    static int N, L, R, answer = 0;
    static int map[][];
    static boolean visit[][];
    static int dy[] = {1,0};
    static int dx[] = {0,1};
    static Queue<Node> queue = new ArrayDeque<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visit = new boolean[N][N];
        System.out.println("anjdu");
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        check();
        //System.out.println(answer);
    }
    static void check(){
        int count = -1;
        boolean changemap = false;
        while(true){
            count=0;
            changemap = false;
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    System.out.println(count+" 0");
                    if(visit[i][j]) continue;
                    
                    visit[i][j] = true;
                    queue.offer(new Node(i, j));
                    count = map[i][j];
                    System.out.println(count+" 1");
                    count += near(i,j);
                    System.out.println(count+" 2");
                    change(count);
                    System.out.println(count+" 3");
                    if(count != map[i][j]) changemap = true;
                }   
            }
            if(!changemap) break;
            answer++;
        }

    }
    static int near(int i, int j){
        int count = 0;
        for(int a=0;a<2;a++){
            int ny = dy[a]+i;
            int nx = dy[a]+j;
            if(ny>=N||ny<0||nx>=N||nx<0||!visit[ny][nx]||
            Math.abs(map[i][j]-map[ny][nx])<L||R<Math.abs(map[i][j]-map[ny][nx])) continue;
            visit[ny][nx] = true;
            queue.offer(new Node(ny,nx));
            count+=near(ny, nx);
        }
        return count;
    }
    static void change(int count){
        int size = queue.size();
        int people = count/size;
        for(int i=0;i<size;i++){
            Node temp = queue.poll();
            map[temp.y][temp.x] = people;
        }
        for(int i=0;i<N;i++){
             Arrays.fill(visit[i], false);
        }
    }
    static class Node{
        int y,x;
        Node(int y, int x){
            this.y=y;
            this.x=x;
        }
    }
}
