import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] board;
    static LinkedList<Node> adu;
    static Node arduino;

    static int[] dx = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dy = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        adu = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'R') {
                    adu.add(new Node(i, j));
                } else if (board[i][j] == 'I') {
                    arduino = new Node(i, j);
                }
            }
        }

        String directions = br.readLine();
        int count = 1; // 움직인 횟수
        boolean isLose = false;

        for (char direction : directions.toCharArray()) {
            // 종수 아두이노 이동
            board[arduino.x][arduino.y] = '.';
            int moveDirection = Character.getNumericValue(direction);
            arduino.x += dx[moveDirection];
            arduino.y += dy[moveDirection];

            if (board[arduino.x][arduino.y] == 'R') {
                isLose = true;
                break;
            }
            board[arduino.x][arduino.y] = 'I';

            if (!moveCrazeArduino()) { //더이상 움직이지 않는 경우 그만하고 출력
                isLose = true;
                break;
            }

            count++;
        }

        if (isLose) {
            System.out.println("kraj " + count);
        } else {
            printBoard();
        }
    }

    public static boolean moveCrazeArduino() { //아두이노 이동
        int[][] arduinoCount = new int[R][C];

        int size = adu.size();
        for (int i = 0; i < size; i++) {
            Node current = adu.poll();
            board[current.x][current.y] = '.';

            int direction = find(current);
            int nextX = current.x + dx[direction];
            int nextY = current.y + dy[direction];

            if (board[nextX][nextY] == 'I') {
                return false;
            }
            arduinoCount[nextX][nextY]++;
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arduinoCount[i][j] == 1) {
                    board[i][j] = 'R';
                    adu.add(new Node(i, j));
                }
            }
        }
        return true;
    }

    public static int find(Node current) { //가까운 거리 찾기
        int minDistance = Integer.MAX_VALUE;
        int minDirection = -1;

        for (int i = 1; i <= 9; i++) {

            int nextX = current.x + dx[i];
            int nextY = current.y + dy[i];

            if (nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) {
                continue;
            }

            int distance = Math.abs(nextX - arduino.x) + Math.abs(nextY - arduino.y);
            if (minDistance > distance) {
                minDistance = distance;
                minDirection = i;
            }
        }
        return minDirection;
    }

    public static void printBoard() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
