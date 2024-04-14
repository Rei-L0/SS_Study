import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PriorityQueue<MaxHeap> maxHeap = new PriorityQueue<>();
        PriorityQueue<MinHeap> minHeap = new PriorityQueue<>();
        HashMap<Integer, Integer> solve = new HashMap<>();
        StringBuilder ans = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            solve.put(P, L);
            maxHeap.add(new MaxHeap(P, L));
            minHeap.add(new MinHeap(P, L));

        }

        int M = Integer.parseInt(br.readLine());

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            String commend = st.nextToken();

            if(commend.equals("add")) {
                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                solve.put(P, L);
                maxHeap.add(new MaxHeap(P, L));
                minHeap.add(new MinHeap(P, L));
            } else if(commend.equals("recommend")) {
                int n = Integer.parseInt(st.nextToken());

                if(n==1) {
                    while(true) {
                        if(solve.containsKey(maxHeap.peek().P) && solve.get(maxHeap.peek().P) == maxHeap.peek().L) {
                            ans.append(maxHeap.peek().P + "\n");
                            break;
                        }
                        maxHeap.poll();
                    }
                } else {
                    while(true) {
                        if(solve.containsKey(minHeap.peek().P) && solve.get(minHeap.peek().P) == minHeap.peek().L) {
                            ans.append(minHeap.peek().P + "\n");
                            break;
                        }
                        minHeap.poll();
                    }
                }
            } else if(commend.equals("solved")) {
                int P =  Integer.parseInt(st.nextToken());
                solve.remove(P);
            }
        }
        System.out.printf(ans.toString());
    }
      static class MaxHeap implements Comparable<MaxHeap>{
        int P,L;

        MaxHeap(int P, int L) {
            this.P = P;
            this.L = L;
        }

        public int compareTo(MaxHeap o) {
            if(this.L == o.L) {
                return o.P - this.P;
            }
            return o.L - this.L;
        }
    }

    static class MinHeap implements Comparable<MinHeap>{
        int P,L;

        MinHeap(int P, int L) {
            this.P = P;
            this.L = L;
        }

        public int compareTo(MinHeap o) {
            if(this.L == o.L) {
                return this.P - o.P;
            }
            return this.L - o.L;
        }
    }
}
