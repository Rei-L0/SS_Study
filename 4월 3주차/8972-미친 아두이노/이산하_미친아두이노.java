package implement;

import java.io.*;
import java.util.*;

public class B_8972 {

  static int r, c;
  static char[][] board;
  static int[] dx = { 0, 1, 1, 1, 0, 0, 0, -1, -1, -1 };
  static int[] dy = { 0, -1, 0, 1, -1, 0, 1, -1, 0, 1 };
  static Queue<Node> craze_arduino;
  static Node arduino;

  public static void main(String[] args) throws IOException {

    // 입력
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String str = br.readLine();
    StringTokenizer st = new StringTokenizer(str);
    r = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());

    board = new char[r][c];
    craze_arduino = new ArrayDeque<>(); // 미친 아두이노의 위치 저장
    for (int i = 0; i < r; i++) {
      str = br.readLine();
      for (int j = 0; j < c; j++) {
        board[i][j] = str.charAt(j);

        if (board[i][j] == 'R')
          craze_arduino.add(new Node(i, j));
        else if (board[i][j] == 'I')
          arduino = new Node(i, j);
      }
    }
    String direction = br.readLine();
    // 입력 끝

    int count = 1; // 움직인 횟수
    boolean flag = false;
    for (int k = 0; k < direction.length(); k++) {
      // 종수 아두이노 이동
      board[arduino.x][arduino.y] = '.';
      arduino.x = arduino.x + dx[direction.charAt(count - 1) - '0'];
      arduino.y = arduino.y + dy[direction.charAt(count - 1) - '0'];

      if (board[arduino.x][arduino.y] == 'R') {
        flag = true;
        break;
      }
      board[arduino.x][arduino.y] = 'I';

      // 미친 아두이노 이동
      if (!move_craze_arduino()) {
        flag = true;
        break;
      }
      count++;
    }

    if (flag)
      System.out.println("kraj " + count);
    else
      for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
          System.out.print(board[i][j]);
        }
        System.out.println();
      }
  }

  public static boolean move_craze_arduino() {
    int[][] arduino_count = new int[r][c];

    int craze_arduino_size = craze_arduino.size();
    for (int i = 0; i < craze_arduino_size; i++) {
      Node current = craze_arduino.poll();
      board[current.x][current.y] = '.';

      int dir = find_close_dir(current);
      int nx = current.x + dx[dir];
      int ny = current.y + dy[dir];

      if (board[nx][ny] == 'I')
        return false;
      arduino_count[nx][ny]++;
    }

    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        if (arduino_count[i][j] == 1) {
          board[i][j] = 'R';
          craze_arduino.add(new Node(i, j));
        }
      }
    }
    return true;
  }

  public static int find_close_dir(Node current) {
    int min = Integer.MAX_VALUE;
    int min_dir = -1;
    for (int i = 1; i <= 9; i++) {
      if (i == 5)
        continue; // 미친 아두이노는 가만히 있는 경우가 없다.
      int nx = current.x + dx[i];
      int ny = current.y + dy[i];
      if (nx < 0 || ny < 0 || nx >= r || ny >= c)
        continue;

      int distance = Math.abs(nx - arduino.x) + Math.abs(ny - arduino.y);
      if (min > distance) {
        min = distance;
        min_dir = i;
      }
    }
    return min_dir;
  }

  public static class Node {
    int x, y;

    public Node(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
