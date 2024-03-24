// 크루스칼 까먹어서 제 맘대로 하다가 다시 했습니다. 안풀려서 도예코드 참고해ㅓㅇ셔..

import java.io.*;
import java.util.*;

public class B_연애혁명_27498 {
    static int N, M, answer = 0;
    static long total = 0;
    static PriorityQueue<Node> pqueue = new PriorityQueue<>((o1,o2)-> o2.t==o1.t ? o2.c-o1.c : o2.t-o1.t);
    //static boolean visit[];
	static int[] parents;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
		parents = new int[N+1];
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            total+=c;
            pqueue.offer(new Node(a, b, c, d));
        }
        /* 크루스칼 왜 써야하지 하면서 뚝딱뚝딱 하다가 깨달았숮니다
        visit = new boolean[N+1];
        while(count!=N-1 && !pqueue.isEmpty()){
            Node temp = pqueue.poll();
            if(visit[temp.v1] && visit[temp.v2]) continue;
            System.out.println(temp.toString());
            count++;
            answer+=temp.c;
            visit[temp.v1] = true;
            visit[temp.v2] = true;
        }*/
        
		makeSet();
        while(!pqueue.isEmpty()){
            Node temp = pqueue.poll();
            if(union(temp.v1, temp.v2)){
				answer += temp.c;
			}
        }
        System.out.println(total-answer);
    }
    static void makeSet() {
		for(int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	static int find(int x) {
		if(parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}
	static boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if(px != py) {
			parents[px] = py;
			return true;
		}
		return false;
	}
    static class Node{
        int v1, v2, c, t;
        Node(int v1, int v2, int c, int type){
            this.v1=v1;
            this.v2=v2;
            this.c=c;
            this.t=type;
        }        
    }
}
