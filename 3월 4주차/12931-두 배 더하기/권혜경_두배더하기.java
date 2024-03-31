import java.io.*;
import java.util.*;

public class B_두배더하기_12931 {
    static int N, answer = 0;
    static PriorityQueue<Node> queue = new PriorityQueue<>((o1,o2)-> o1.t-o2.t);
    static int input[];
    static boolean visit[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        input = new int[N+1];
        visit = new boolean[1001];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            input[i] = Integer.parseInt(st.nextToken());
        }
        input[N] = 0;
        Arrays.sort(input);
        check();
        System.out.println(answer);
    }
    static void check(){
        for(int i=0;i<N;i++){
            queue.offer(new Node(input[i],0));
            while(!queue.isEmpty()){
                Node temp = queue.poll();
                if(visit[temp.v]||temp.v>input[i+1]) continue;
                visit[temp.v] = true;
                if(temp.v==input[i+1]){
                    queue.clear();
                    
                    visit[temp.v] = false;
                    answer+=temp.t;
                    break;
                }
                if(temp.v!=0) queue.offer(new Node(temp.v*2, temp.t+1));
                queue.offer(new Node(temp.v+1, temp.t+i+1));       
            }
        }
    }
    
    static class Node{
        int v, t;
        Node(int v, int t){
            this.v=v;
            this.t=t;
        }
    }
}
