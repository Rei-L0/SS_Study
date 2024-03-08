import java.io.*;
import java.util.*;

public class prac {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc=0; tc<t; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int my = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] cnt = new int[N+1];
            value[][] arr = new value[N+1][K+1];
            int[] total_score = new int[N+1];
            int[] submit = new int[N+1];
            ArrayList<Node> ans = new ArrayList<>();

            for (int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int ID = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                if (arr[ID][j] == null) {
                    arr[ID][j] = new value(s,i);
                }
                else {
                    if (arr[ID][j].score < s) {
                        arr[ID][j].score = s;
                    }
                    else {
                        arr[ID][j].time = i;
                    }
                }
                cnt[ID]++;
            }

            for (int i=1; i<=N; i++) {
                int tmp = 0;
                int s_t = 0;
                for (int j=1; j<=K;j++) { 
                    if (arr[i][j] != null) {
                        tmp += arr[i][j].score;
                        if (s_t < arr[i][j].time) {
                            s_t = arr[i][j].time;
                        }
                    }
                }
                submit[i] = s_t;
                total_score[i] = tmp;
            }

            for (int i=1; i<=N; i++) {
                ans.add(new Node(total_score[i],cnt[i],submit[i],i));
            }

            Collections.sort(ans);
            
            for (int i=0; i<N; i++) {
                if (ans.get(i).n == my) {
                    sb.append(i+1).append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}

class value {
    int score,time;

    public value(int score,int time) {
        this.score = score;
        this.time = time;
    }
}

class Node implements Comparable<Node>{
    int s,c,t,n;
    public Node(int s, int c, int t, int n)  {
        this.s = s;
        this.c = c;
        this.t = t;
        this.n = n;
    }   
    @Override
    public int compareTo(Node o) {
        if (this.s == o.s && this.c == o.c) {
            return this.t - o.t;
        } else if (this.s == o.s) {
            return this.c - o.c;
        }
        return o.s - this.s;
    }   
}
