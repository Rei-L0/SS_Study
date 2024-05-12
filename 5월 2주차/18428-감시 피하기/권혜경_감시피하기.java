import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean answer = false;
    static char map[][];
    static Node[] teacher;
    static int dy[] = {-1,1,0,0};
    static int dx[] = {0,0,-1,1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        int t = 0, s = 0;
        for(int i =0;i<N;i++){
            String input = br.readLine();
            for(int j=0;j<N;j++){
                map[i][j] = input.charAt(j*2);
                if(map[i][j]=='T') t++;
            }
        }

        teacher = new Node[t];
        
        t = 0; s = 0; 
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]=='T') teacher[t++] = new Node(i,j);
            }
        }

        Node n[] = new Node[3];
        for(int a=0;a<N*N-2;a++){
            n[0] = new Node(a/N, a%N);
            if(map[n[0].y][n[0].x]!='X') continue;
            for(int b=a+1;b<N*N-1;b++){
                n[1] = new Node(b/N, b%N);
                if(map[n[1].y][n[1].x]!='X') continue;
                for(int c=b+1;c<N*N;c++){
                    n[2] = new Node(c/N, c%N);
                    if(map[n[2].y][n[2].x]!='X') continue;

                    if(check(n)) {
                        System.out.println("YES");
                        return;
                    }
                }
            }
        }
        System.out.println("NO");

    }
    static boolean check(Node n[]){
        map[n[0].y][n[0].x] = 'O';
        map[n[1].y][n[1].x] = 'O';
        map[n[2].y][n[2].x] = 'O';

        boolean poss = true;
        int ny, nx;
        sight : for(int i=0;i<teacher.length;i++){ 
            for(int j=0;j<4;j++){
                ny = teacher[i].y; nx = teacher[i].x; 
                while(true){
                    ny+=dy[j]; nx+=dx[j];
                    if(ny<0 || ny>=N || nx<0 || nx>=N ||map[ny][nx]=='O') break;
                    if(map[ny][nx]=='S'){
                        poss = false;
                        break sight;
                    }
                }
            }
        }

        map[n[0].y][n[0].x] = 'X';
        map[n[1].y][n[1].x] = 'X';
        map[n[2].y][n[2].x] = 'X';

        return poss;
    }
    static class Node{
        int y, x;
        Node(int y, int x){
            this.y=y;
            this.x=x;
        }
    }
}
