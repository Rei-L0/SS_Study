import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Main{
    static int A, B, N, M;
    static int map[][];
    static Map<Integer, Node> robot = new HashMap<>();
    static int dy[] = {1,0,-1,0};
    static int dx[] = {0,1,0,-1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        map = new int[B][A];

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int d = -1;
            map[y][x] = i+1;
            switch (st.nextToken().charAt(0)){
                case 'N' : d=0; break;
                case 'W' : d=3; break;
                case 'E' : d=1; break;
                case 'S' : d=2; break;
            }
            robot.put(i+1, new Node(y, x, d));
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            char b = st.nextToken().charAt(0);
            int c = Integer.parseInt(st.nextToken());
            Node R = null;
            if(b=='F'){
                R = robot.get(a);
                map[R.y][R.x] = 0;
                for(int j=0;j<c;j++) {
                    R.y+=dy[R.d];
                    R.x+=dx[R.d];
                    if(R.y<0 || R.y>=B || R.x<0 || R.x>=A) {
                        System.out.println("Robot "+a+" crashes into the wall");
                        return;
                    }
                    else if(map[R.y][R.x]!=0){
                        System.out.println("Robot "+a+" crashes into robot "+map[R.y][R.x]);
                        return;
                    }
                }
                map[R.y][R.x] = a;
            }else if(b=='R'){
                R = robot.get(a);
                R.d = (R.d+c)%4;
            }else if(b=='L'){
                R = robot.get(a);
                R.d = (R.d+4-(c%4))%4;
            }
            //System.out.println(i + " "+robot.get(a).toString());
        }
        System.out.println("OK");
    }
    static class Node{
        int y, x, d;
        Node(int y, int x, int d){
            this.y=y;
            this.x=x;
            this.d=d;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "y=" + y +
                    ", x=" + x +
                    ", d=" + d +
                    '}';
        }
    }
}
