import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[] parent;
    static Edge[] edges;

    static class Edge{
		int v1, v2, c;
		Edge(int v1, int v2, int c){
			this.v1 = v1; this.v2=v2; this.c= c;
		}
		@Override
		public String toString() {
			return "Edge [v1=" + v1 + ", v2=" + v2 + ", c=" + c + "]";
		}
	}


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        parent = new int[n + 1]; //0은 dummy
        edges = new Edge[m];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        int cnt=0;
        int sum=0;
        int totalSum = 0; //다더하는거
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if (d == 1) {
                union(a, b);
                cnt++;
                sum+=c;
            }
            edges[i] = new Edge(a, b, c);
            totalSum += c;
        }
        
        

        Arrays.sort(edges,(e1,e2)->e2.c -e1.c);
        for (int i = 0; i < m; i++) {
			Edge edge = edges[i];
			//간선 선택 후 간선이 잇는 두 정점에 대해 서로소 체크

			if(union(edge.v1, edge.v2)) {
				//선택 
				cnt++;
				sum+=edge.c;
				if(cnt==n-1)break;
				
			}
			
		}

        System.out.println(totalSum-sum);

    }
    
    static void makeSet() {
		for(int i=1; i<=n; i++) {
			parent[i] =i;
		}
	}


    static int findSet(int x) {
		if(parent[x]==x) return x;
		else return parent[x] = findSet(parent[x]);
	}


    static boolean union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		
		if(px==py) return false; //cycle 발생
		if(px < py) parent[py] = px;
		else parent[px] = py;
		
		return true; // cycle이 발생하지 않으면 합치고 true 반환
	}

}
