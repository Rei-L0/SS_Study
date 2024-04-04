import java.io.*;
import java.util.*;
/*
 오른쪽부터 돌면서 
 본인의 뒤에 있는 값이 들어있는 pqueue에서 제일 큰 값과 내 값을 비교
 본인보다 작거나 같으면 현재값 -1
 본인보다 크면 나보다 큰 값이 뒤에 존재한다는 말
 고로 뒤에 있는 수를 -> 이렇게 탐색하면서 나보다 큰 값을 가진 녀석을 찾아 헤맴
*/
public class Main {
    static int N;
    static int input[], answer[];
    static int num[] = new int[1_000_001];

    static PriorityQueue<Node> pqueue = new PriorityQueue<>((o1,o2)->o2.c-o1.c);

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        input = new int[N];
        answer = new int[N];
        for(int i=0;i<N;i++){
            input[i] = Integer.parseInt(st.nextToken());
            num[input[i]]++;
        }
        check();
        for(int i : answer){
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
    static void check(){
        answer[N-1] = -1;
        pqueue.offer(new Node(input[N-1]));
        for(int i=N-2;i>-1;i--){
            int ans = -1;
            Node temp = pqueue.peek(); 
            int count = num[input[i]];
            if(temp.c>count){
                if(num[input[i+1]]>count) {
                    ans = input[i+1];
                }
                if(ans==-1){
                    for(int j=i+1;j<N;j++){
                        if(answer[j]==-1 || count>=num[answer[j]] ) continue;
                        ans = answer[j];
                        break;
                    }
                }
            }
            answer[i] = ans;
            pqueue.offer(new Node(input[i]));
        }
    }
    static class Node{
        int v, c;
        Node(int value){
            this.v = value;
            this.c = num[v];
        }
    }
}
