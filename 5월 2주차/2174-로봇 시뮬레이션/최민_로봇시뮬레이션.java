//x,y 축 방향 헷갈려서 틀렸습니다 몇번 떴음. 주의하기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Robot {
    int x, y; // 로봇의 위치
    char direction; // 로봇의 방향

    public Robot(int x, int y, char direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
}

public class Main {
    static int A, B; 
    static ArrayList<Robot> robots; 
    static int[] dx = {0,-1,0,1}; //동북서남
    static int[] dy = {1,0,-1,0};
   

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); 
        int M = Integer.parseInt(st.nextToken()); 

        robots = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            char direction = st.nextToken().charAt(0);
            robots.add(new Robot(x, y, direction));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken()) - 1;
            char command = st.nextToken().charAt(0);
            int repeat = Integer.parseInt(st.nextToken());

            if (!simulate(id, command, repeat)) {
                return;
            }
        }

        System.out.println("OK");
    }

    static boolean simulate(int id, char command, int repeat) {
        Robot robot = robots.get(id);

        for (int i = 0; i < repeat; i++) {
            if (command == 'F') {
            	int dir = -1;
            	if(robot.direction=='E') dir=3;
            	else if (robot.direction=='N') dir=0;
            	else if (robot.direction=='W') dir=1;
            	else if (robot.direction=='S') dir=2;
                int newX = robot.x + dx[dir];
                int newY = robot.y + dy[dir];
                
                if (newX < 1 || newY < 1 || newX > A || newY > B) { //X번 로봇이 벽에 충돌하는 경우
                	System.out.printf("Robot %d crashes into the wall\n", id + 1);
                    return false;
                }

                for (int j = 0; j < robots.size(); j++) { //X번 로봇이 움직이다가 Y번 로봇에 충돌하는 경우
                    if (j != id && robots.get(j).x == newX && robots.get(j).y == newY) {
                        System.out.printf("Robot %d crashes into robot %d\n", id + 1, j + 1);
                        return false;
                    }
                }

                robot.x = newX;
                robot.y = newY;
            } else if (command == 'L') {
                robot.direction = turnLeft(robot.direction);
            } else if (command == 'R') {
                robot.direction = turnRight(robot.direction);
            }
        }

        return true;
    }

   
    static char turnLeft(char direction) {
        switch (direction) {
            case 'E':
                return 'N';
            case 'N':
                return 'W';
            case 'W':
                return 'S';
            case 'S':
                return 'E';
            default:
                return ' ';
        }
    }

    static char turnRight(char direction) {
        switch (direction) {
            case 'E':
                return 'S';
            case 'N':
                return 'E';
            case 'W':
                return 'N';
            case 'S':
                return 'W';
            default:
                return ' ';
        }
    }
}
