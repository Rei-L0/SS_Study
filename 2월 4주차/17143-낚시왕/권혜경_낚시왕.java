import java.io.*;
import java.util.*;

public class B_낚시왕_17143 {
    static int R, C, M, answer = 0;
    static int check[][];
    static boolean grap;
    static Map<Integer, Shark> map = new HashMap<>();
    static List<Integer> die = new ArrayList<>();

    static int dy[] = {-1, 1, 0, 0}; // 상 하 우 좌
    static int dx[] = {0, 0, 1, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        check = new int[R][C];
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken())-1;
            int B = Integer.parseInt(st.nextToken())-1;
            check[A][B] = i+1;
            map.put(i+1, new Shark(A,B,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())));
        }
        for(int i=0;i<C;i++){
            
            if(map.size()==0) break;
            
            // System.out.println("-------------------"+answer);
            // for(int a=0;a<R;a++){
            //     System.out.println(Arrays.toString(check[a]));
            // }

            // 제일 가까운 놈 잡으면 끝나야하니까 그거 표시
            grap = false;
            // 움직인 후 상어포획
            for(int j=0;j<R;j++){
                if(check[j][i]!=0 && !grap) {
                    answer+=map.get(check[j][i]).z;
                    map.remove(check[j][i]);
                    grap = true;
                }
                Arrays.fill(check[j],0);
            } 
            
            // 상어움직이기
            for(int j : map.keySet()){
                Shark s = map.get(j);
                
                int ny=s.y+dy[s.d]*s.s, nx=s.x+dx[s.d]*s.s;
                ny%=2*(R-1);
                nx%=2*(C-1);
                
                if(ny<0){
                    ny = -ny;
                    s.d = 1;
                }
                if(ny>=R){ 
                    ny = R-1-(ny%(R-1)); 
                    s.d = 0;
                }
                if(nx<0){
                    nx = -nx; 
                    s.d = 2;
                }
                if(nx>=C){ 
                    nx = C-1-(nx%(C-1)); 
                    s.d = 3;
                }
                s.y=ny;
                s.x=nx;

                if(check[ny][nx]==0) check[ny][nx] = j;
                //곂치는 경우에는 큰 놈을 두고 작은 놈은 나중에 죽이는 리스트에 추가
                else {
                    if(map.get(check[ny][nx]).z < s.z){
                        die.add(check[ny][nx]);
                        check[ny][nx] = j;
                    }
                    else
                        die.add(j);
                    
                }
            }
            
            //곂쳐서 먹은 상어 제거
            for(int a:die){
                map.remove(a);
            }
            die.clear();
        }
        System.out.println(answer);
    }
    static class Shark{
        int y, x, s, d, z;
        Shark(int y, int x, int s, int d, int z){
            this.y=y;
            this.x=x;
            this.s=s;
            this.d=d;
            this.z=z;
        }
    }
}
