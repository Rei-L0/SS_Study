import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bj_1325_효율적인해킹 {
	static class Node{
		int end;
		
		Node(int end){
			this.end=end;
		}
	}
	static List<List<Node>> list = new ArrayList<>();
	static boolean[] visited;
	static int max;
	static List<Integer> result = new ArrayList<>();
	static int[] hackCounts; // 해킹 가능 컴퓨터 수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		int n= Integer.parseInt(st.nextToken()); //n개의 컴퓨터(노드)
		int m = Integer.parseInt(st.nextToken()); //간선
		hackCounts = new int[n + 1];
		for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
		
		 for (int i = 0; i < m; i++) { //입력
	            st = new StringTokenizer(br.readLine());
	            int end = Integer.parseInt(st.nextToken());
	            int start = Integer.parseInt(st.nextToken());
	            list.get(end).add(new Node(start)); //a가 b신뢰시 b 해킹하면 a 해킹. 역으로 생각하기
	     }
		 for (int i = 1; i <= n; i++) {
	            visited = new boolean[n + 1];
	            
	            visited[i] = true;
	            hackCounts[i]++;
	            dfs(i);  
	     }
		 
		 int maxhack=0;
		 for(int i=1; i<=n; i++) {
			 maxhack = Math.max(maxhack, hackCounts[i]); //가장 많이 해킹할수 있는 컴퓨터 구하고
		 }
		 
		 StringBuilder sb = new StringBuilder();
			for (int i = 1; i <= n; i++) {
				if (hackCounts[i] == maxhack) {
					sb.append(i).append(" ");
				}
			}
		System.out.println(sb);
	}
	
	static void dfs(int idx) {
        for (Node n : list.get(idx)) {
            if (!visited[n.end]) {
	            visited[n.end] = true;
	            hackCounts[n.end]++;
                dfs(n.end);
            }
        }
    }
}
