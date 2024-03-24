import java.io.*;
import java.util.*;


// 사이즈가 1개일때, 2개일때 등 길이가 헷갈렸지만 ㄱㅊ았던것 같습니다

public class Main {
    static int N,M,R,score = 0;
    static int map[][];
    static boolean stand[][];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        stand = new boolean[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken())-1;
            }
            Arrays.fill(stand[i], true);
        }

        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            char C = st.nextToken().charAt(0);
            int end;
            if(stand[y][x]){            
                stand[y][x] = false;
                score++;
                switch (C) {
                    case 'E': end=x+map[y][x]; 
                    for(int j=x+1;j<=end;j++){
                        if(j==M) break;
                        if(!stand[y][j]) continue;
                        stand[y][j] = false;
                        end = Math.max(end, j+map[y][j]);
                        score++;
                    }
                    break;
    
                    case 'W': end=x-map[y][x]; 
                    for(int j=x-1;j>=end;j--){
                        if(j==-1) break;
                        if(!stand[y][j]) continue;
                        stand[y][j] = false;
                        end = Math.min(end, j-map[y][j]);
                        score++;
                    }
                    break;
    
                    case 'S': end=y+map[y][x]; 
                    for(int j=y+1;j<=end;j++){
                        if(j==N) break;
                        if(!stand[j][x]) continue;
                        stand[j][x] = false;
                        end = Math.max(end, j+map[j][x]);
                        score++;
                    }
                    break;
    
                    case 'N': end=y-map[y][x]; 
                    for(int j=y-1;j>=end;j--){
                        if(j==-1) break;
                        if(!stand[j][x]) continue;
                        stand[j][x] = false;
                        end = Math.min(end, j-map[j][x]);
                        score++;
                    }
                    break;            
                }
            }
            st = new StringTokenizer(br.readLine());
            stand[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = true;
        }

        sb.append(score).append("\n");
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(stand[i][j]) sb.append("S ");
                else sb.append("F ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
