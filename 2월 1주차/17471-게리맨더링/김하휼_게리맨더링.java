import java.io.*;
import java.util.*;

class Main {
    static int[] people;
    static ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
    static int N, min_v;
    static boolean[] check;
    static int[] popul;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        min_v = Integer.MAX_VALUE;
        people = new int[N+1];
        arr = new ArrayList<>();
        popul = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        for (int i=0;i<=N;i++) {
            arr.add(new ArrayList<>());
        }

        for (int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            for (int j=0;j<a;j++) {
                arr.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        search(1);
        if (min_v == Integer.MAX_VALUE) sb.append(-1);
        else sb.append(min_v);
        System.out.println(sb);

    }   

    public static void search(int depth) {
        if (depth == N+1) {
            check = new boolean[N+1];
            int a = 0; 
            int b = 0;
            for (int i=0; i<N; i++) {
                if (popul[i+1] == 1) a += people[i+1];
                else b += people[i+1];
            }

            int cnt = 0;

            for (int i=1; i<=N; i++) {
                if (check[i]) continue;
                BFS(i,popul[i]);
                cnt++;
            }

            if (cnt==2) min_v = Math.min(min_v,Math.abs(a-b));
            return;

        }
        popul[depth] = 1;
        search(depth+1);
        popul[depth] = 0;
        search(depth+1);
    }

    public static void BFS(int n, int local) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(n);
        check[n] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next: arr.get(cur)) {
                if(popul[next] == local && !check[next]) {
                    check[next] = true;
                    q.add(next);
                }
            }
        }
    }
}
