// 시간 짱 많이 걸리는데 어케 해야하는지,,

import java.io.*;
import java.util.*;

public class Main {
    static int H, W, move = 0, count = 0, clean = 0; 
    static char A[][], B[][];
    static boolean visit[][];
    static Map<Integer, Integer> turn = new HashMap<>();

    static int dy[] = {-1,0,1,0};
    static int dx[] = {0,1,0,-1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        A = new char[H][W];
        B = new char[H][W];
        visit = new boolean[H][W];

        st = new StringTokenizer(br.readLine());
        int ny = Integer.parseInt(st.nextToken()), nx =Integer.parseInt(st.nextToken()),
            dir = Integer.parseInt(st.nextToken());
    
        for(int i=0;i<H;i++){
            A[i] = br.readLine().toCharArray();
        }
        for(int i=0;i<H;i++){
            B[i] = br.readLine().toCharArray();
        }
        // move : 총움직인횟수
        // count : 먼지없이 방황한 횟수
        // clean : 치운 먼지 수 
        for(;;move++){
            if(ny>=H || ny<0 || nx>=W || nx<0 || clean == H*W) break;
            
            if(!visit[ny][nx]){ // 청소전
                visit[ny][nx] = true;
                count = 0;
                clean++;
                turn.clear();
                dir += A[ny][nx]-'0';
            }else{ // 청소후
                if(turn.containsKey(ny*H+nx)) {
                    int before = turn.get(ny*H+nx); 
                    if(before == (before|1<<dir)) break;    
                }
                count++;
                turn.put(ny*H+nx, turn.getOrDefault(ny*H+nx, 0)|1<<dir);
                dir += B[ny][nx]-'0';
            }
            dir %= 4;
            ny += dy[dir];
            nx += dx[dir];
            // System.out.println(ny+" "+nx);
        }

        System.out.println(move-count);
    }
}
