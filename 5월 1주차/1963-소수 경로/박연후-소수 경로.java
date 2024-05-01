import java.io.*;
import java.util.*;

public class Main {

    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static boolean[] prime;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder stringBuilder = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        prime = makePrimeList();

        for (int z = 0; z < t; z++) {
            st = new StringTokenizer(br.readLine());
            String start = st.nextToken();
            String end = st.nextToken();

            stringBuilder.append(bfs(start, end)).append("\n");
        }
        System.out.print(stringBuilder);
    }

    static String bfs(String s, String e) {
        int[] visit = new int[10000];
        Queue<String> q = new ArrayDeque<>();

        q.offer(s);
        visit[Integer.parseInt(s)] = 1;

        while (!q.isEmpty()) {
            String now = q.poll();
            int nowNum = Integer.parseInt(now);
            if (now.equals(e)) {
                return String.valueOf(visit[nowNum] - 1);
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 10; j++) {
                    sb = new StringBuilder();
                    for (int k = 0; k < 4; k++) {
                        if (i == k) {
                            sb.append(j);
                        } else {
                            sb.append(now.charAt(k));
                        }
                    }
                    String next = sb.toString();
                    int nextNum = Integer.parseInt(next);
                    if (nextNum > 1000 && nextNum < 10000) {
                        if (visit[nextNum] == 0 && prime[nextNum]) {
                            q.offer(next);
                            visit[nextNum] = visit[nowNum] + 1;
                        }
                    }
                }
            }

        }

        return "Impossible";
    }


    static boolean[] makePrimeList() {
        boolean[] check = new boolean[10000];
        Arrays.fill(check, true);
        for (int i = 2; i < 10000; i++) {
            if (check[i]) {
                for (int j = i + i; j < 10000; j = j + i) {
                    check[j] = false;
                }
            }
        }
        return check;
    }


}