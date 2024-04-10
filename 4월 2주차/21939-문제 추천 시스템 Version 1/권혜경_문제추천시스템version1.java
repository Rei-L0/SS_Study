// queue를 활용하되, queue에 있다가 삭제된 녀석도 고려해줘야함

import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static PriorityQueue<Problem> f_queue = new PriorityQueue<>((o1,o2)->o1.level==o2.level?o1.idx-o2.idx:o1.level-o2.level);
    static PriorityQueue<Problem> b_queue = new PriorityQueue<>((o1,o2)->o1.level==o2.level?o2.idx-o1.idx:o2.level-o1.level);
    static Map<Integer, Integer> map = new HashMap<>();

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());

            map.put(idx,level);

            f_queue.offer(new Problem(idx, level));
            b_queue.offer(new Problem(idx, level));
        }

        M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            int O = Integer.parseInt(st.nextToken());
            switch (type) {
                case "recommend":
                    recommend(O);
                    break;
                case "solved":
                    solved(O);
                    break;
                case "add":
                    int L = Integer.parseInt(st.nextToken());
                    add(O, L);
                    break;
            }
        }
        System.out.println(sb.toString());
    }
    static void add(int idx, int level) {
        map.put(idx,level);

        f_queue.offer(new Problem(idx, level));
        b_queue.offer(new Problem(idx, level));
    }
    static void solved(int P){
        map.remove(P);
    }
    static void recommend(int type) {
        if(type>0) {

            if(b_queue.isEmpty()) return;
            if(!map.containsKey(b_queue.peek().idx) || map.get(b_queue.peek().idx)!=b_queue.peek().level) {
                b_queue.poll();
                recommend(type);
            }
            else sb.append(b_queue.peek().idx).append("\n");
        }
        else {
            if(f_queue.isEmpty()) return;
            if(!map.containsKey(f_queue.peek().idx) || map.get(f_queue.peek().idx)!=f_queue.peek().level) {
                f_queue.poll();
                recommend(type);
            }
            else sb.append(f_queue.peek().idx).append("\n");
        }
    }
    static class Problem{
        int idx, level;
        Problem(int idx, int level){
            this.idx = idx;
            this.level = level;
        }
    }
}
