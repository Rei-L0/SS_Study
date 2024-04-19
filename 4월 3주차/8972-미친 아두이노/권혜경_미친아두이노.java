// 뭔가 풀이가 더러워서 수정해볼려고 했는데 쉽지 않네요,, 속도도 생각만큼 빠르지 않고,, 속상합니다,,
// 문제는 구현 그 잡채인 느낌인데, 꼼꼼함이 필요했어요

import java.util.*;
import java.io.*;
public class B_미친아두이노_8972 {
    static int R, C, count = 0;
    static Node J;
    static List<Node> Adu = new ArrayList<>();
    static int map[][];
    static char move[];

    static int dy[] = {1,1,1,0,0,0,-1,-1,-1};
    static int dx[] = {-1,0,1,-1,0,1,-1,0,1};

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String s;

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        Adu.add(new Node(0, -1, -1));

        for(int i=0;i<R;i++){
            s = br.readLine();
            for(int j=0;j<C;j++){
                switch(s.charAt(j)){
                    case 'I': map[i][j] = 1; J = new Node(i, j); break;
                    case 'R': map[i][j] = -Adu.size(); Adu.add(new Node(Adu.size(), i, j)); break;
                    
                    default : map[i][j] = 0;
                }
            }
        }

        move = br.readLine().toCharArray();
        move_step : for(;count<move.length;count++){
            // 종수 움직이기
            if(move_J(move[count]-'0'-1)) break;
            
            map = new int[R][C];
            map[J.y][J.x] = 1;

            // 아두이노 움직이기
            for(Node A : Adu){
                if(move_A(A)) break move_step;
            }

            // 터질녀석들 정리
            boom();
        }

        if(sb.length()==0) printmap();
        System.out.print(sb.toString());
    }
    
    static void boom(){
        for(Node a : Adu){
            if( (a.y==-1 && a.x==-1) || !a.boom) continue;
            map[a.y][a.x]=0;
            a.y=-1;
            a.x=-1;
            a.boom=false;
        }
    }
    static boolean move_A(Node A){
        if(A.y==-1 && A.x==-1) return false;

        // 가장 가까운 위치 찾기
        int diff = 0, diff_value = 100000;
        for(int i=0;i<9;i++){
            diff = Math.abs(A.y+dy[i]-J.y)+Math.abs(A.x+dx[i]-J.x);
            if(diff<diff_value) {
                diff_value = diff ;
                A.idx = i;
            }
        }
        
        // 움직였다가 종수 잡은 경우 종료
        if(map[A.y+dy[A.idx]][A.x+dx[A.idx]]==1) {
            sb.append("kraj ").append(count+1);
            return true;
        }

        // 움직였다가 기존의 아두이노가 있는 경우 터질녀석으로 체크
        if(map[A.y+dy[A.idx]][A.x+dx[A.idx]] < 0) {
            A.boom = true;
            Adu.get(-map[A.y+dy[A.idx]][A.x+dx[A.idx]]).boom = true;
        }

        A.y += dy[A.idx];
        A.x += dx[A.idx];
        map[A.y][A.x] = -A.i;
        return false;
    }
    static boolean move_J(int m){
        J.y += dy[m];
        J.x += dx[m];
        if(map[J.y][J.x] < 0) {
            sb.append("kraj ").append(count+1);
            return true;
        }
        return false;
    }
    static void printmap(){
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j]<0) sb.append('R');
                else if(map[i][j]==1) sb.append('I');
                else sb.append('.');
            }
            sb.append("\n");
        }
    }
    static class Node{
        int i, y, x, idx;
        boolean boom;
        Node(int y, int x){ 
            this.y=y;
            this.x=x;
        }
        Node(int i, int y, int x){ 
            this.i=i;
            this.y=y;
            this.x=x;
            idx = -1;
            boom = false;
        }
        @Override
        public String toString() {
            return "Node [i=" + i + ", y=" + y + ", x=" + x + ", idx=" + idx + "]";
        }
    }

}
