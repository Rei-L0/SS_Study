import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	static int n ,k, t, m;
	static Info[] info; 
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tt = 0; tt < T; tt++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			info = new Info[n];

			for(int x = 0; x < m; x++) {
				st = new StringTokenizer(br.readLine());
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				if(info[i - 1] == null) info[i - 1] = new Info(i, j, x, s);
				else {
					info[i - 1].time = x;
					info[i - 1].add(j, s);
				}
			}

			Arrays.sort(info);
			for(int i = 0; i <= m; i++) {
				if(info[i].id == t) {
					sb.append(i+1).append("\n");
					break;
				}
			}
		}
		System.out.println(sb);
	}
	static class Info implements Comparable<Info>{
		int id, time, cnt, sum;
		HashMap<Integer, Integer> map = new HashMap<>();


		public Info(int id, int j, int time, int s) {
			this.id = id;
			map.put(j, s);
			this.time = time;
			this.cnt = 1;
			this.sum = s;
		}
		public void add(int j, int s) {
			if(map.containsKey(j)) {
				if(map.get(j) < s) {
					sum = sum - map.get(j) + s;
					map.put(j, s);
				}
			}
			else {
				map.put(j, s);
				sum += s;
			}
			cnt++;
		}
		
		@Override
		public int compareTo(Info o) {
			return o.sum == sum ? (cnt == o.cnt ? time - o.time : cnt - o.cnt) : o.sum - sum;
		}
		
	}
}
