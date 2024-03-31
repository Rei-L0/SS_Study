import java.io.*;
import java.util.*;
 
public class prac {
    static List<ArrayList<Integer>> list;
    static int ans[];
    static int n, m;
    static boolean visit[];
    public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ans = new int[n+1];
        
        
        list = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<Integer>());
        }
        
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
        }
        
        for(int i=1; i<=n;i++) {
            visit = new boolean[n+1];
            BFS(i);
        }
        
        int max = Integer.MIN_VALUE;
        
        for(int i=1; i<=n;i++) {
            max = Math.max(max, ans[i]);
        }
        
        for(int i=1; i <=n ; i++) {
            if(ans[i]==max) {
                System.out.print(i + " ");
            }
        }
        
    }
    private static void BFS(int i) {
        Queue<Integer> q = new LinkedList<Integer>();
        visit[i] = true;
        q.add(i);
        
        while(!q.isEmpty()) {
            int now = q.poll();
            
            for(int a : list.get(now)) {
                if(!visit[a]) {
                    visit[a] =true;

                    ans[a]++;
                    q.add(a);
                }
            }
        }
	}
}

