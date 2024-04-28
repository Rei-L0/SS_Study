import java.io.*;
import java.util.*;

// 아직 덜품 .. 더 시간투자 해보겟샴
public class B_3425 {

  public static ArrayList<String> list = new ArrayList<>();
  public static long[] stack = new long[1001];
  public static int head = 0;
  public static int MAX = 1000000000;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringBuilder sb = new StringBuilder();

    while (true) {
      list.clear();

      String line = br.readLine();

      // QUIT일 경우 다음 기계 설명이 없음.
      if (line.equals("QUIT")) {
        break;
      }

      // END가 나올때까지 명령 입력
      while (!line.equals("END")) {
        String[] splitLine = line.split(" ");

        // POP, INV, DUP, SWP, ADD, SUB, MUL, DIV, MOD
        if (splitLine.length == 1) {
          list.add(splitLine[0]);
        } else {
          // NUM X
          list.add(splitLine[0]);
          list.add(splitLine[1]);
        }

        line = br.readLine();
      }

      int test_case = Integer.parseInt(br.readLine());

      for (int x = 0; x < test_case; x++) {
        int num = Integer.parseInt(br.readLine());

        if (runProgram(num)) {
          sb.append(stack[0]).append("\n");
        } else {
          sb.append("ERROR\n");
        }
      }

      sb.append("\n");
      br.readLine();

    }
    System.out.println(sb);

  }

  // 입력 값에 대해 프로그램 수행
  public static boolean runProgram(int now) {
    int listSize = list.size();

    // 초기화
    head = 0;
    stack[head++] = now;

    for (int x = 0; x < listSize; x++) {

      if (list.get(x).equals("NUM")) {

        stack[head++] = Long.parseLong(list.get(x + 1));
        x++;
      } else if (list.get(x).equals("POP")) {
        if (head < 1)
          return false;

        head--;

      } else if (list.get(x).equals("INV")) {

        if (head < 1)
          return false;

        stack[head - 1] *= -1;

      } else if (list.get(x).equals("DUP")) {

        if (head < 1) {
          return false;
        }

        stack[head] = stack[head - 1];
        head++;

      } else if (list.get(x).equals("SWP")) {

        if (head < 2)
          return false;

        long temp = stack[head - 1];
        stack[head - 1] = stack[head - 2];
        stack[head - 2] = temp;
      } else if (list.get(x).equals("ADD")) {

        if (head < 2)
          return false;

        if (Math.abs(stack[head - 1] + stack[head - 2]) > MAX) {
          return false;
        }

        stack[head - 2] = stack[head - 1] + stack[head - 2];
        head--;

      } else if (list.get(x).equals("SUB")) {
        if (head < 2)
          return false;

        if (Math.abs(stack[head - 2] - stack[head - 1]) > MAX) {

          return false;
        }

        stack[head - 2] = stack[head - 2] - stack[head - 1];
        head--;

      } else if (list.get(x).equals("MUL")) {
        if (head < 2)
          return false;

        if (Math.abs(stack[head - 2] * stack[head - 1]) > MAX) {
          return false;
        }

        stack[head - 2] = stack[head - 2] * stack[head - 1];
        head--;
      } else if (list.get(x).equals("DIV")) {
        if (head < 2)
          return false;

        if (stack[head - 1] == 0)
          return false;

        stack[head - 2] = stack[head - 2] / stack[head - 1];
        head--;
      } else {
        // MOD

        if (head < 2)
          return false;

        if (stack[head - 1] == 0)
          return false;

        stack[head - 2] = stack[head - 2] % stack[head - 1];
        head--;
      }

    }

    // 스택에 저장되어 있느 숫자가 1개가 아니라면 ERROR
    if (head == 1)
      return true;
    return false;

  }

}
