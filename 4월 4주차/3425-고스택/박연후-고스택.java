import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
	16%에서 계속 틀렸습니다..
	아직 문제를 못 찾아서 조금 더 찾아서 맞춰보겠습니다.
 */

public class Main {

	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			boolean isFinish = false;
			List<String> oper = new ArrayList<>();
			List<Integer> add = new ArrayList<>();
			while (true) {
				String s = br.readLine();
				if ("QUIT".equals(s)) {
					isFinish = true;
					break;
				} else if ("END".equals(s)) {
					break;
				} else if (s.contains("NUM")) {
					oper.add("NUM");
					add.add(Integer.parseInt(s.split(" ")[1]));
				} else {
					oper.add(s);
				}
			}
			if (isFinish)
				break;
			int n = Integer.parseInt(br.readLine());
			int a = 0;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				solve(a, add, oper);
			}
			br.readLine();
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void solve(int a, List<Integer> list, List<String> oper) {
		ArrayDeque<Long> q = new ArrayDeque<>();
		q.add((long) a);
		int idx = 0;
		for (int i = 0; i < oper.size(); i++) {
			String op = oper.get(i);
			if (op.equals("NUM")) {
				q.add((long) list.get(idx));
				idx++;
			} else if (op.equals("POP")) {
				if (q.isEmpty()) {
					sb.append("ERROR").append("\n");
					return;
				}
				q.pollLast();
			} else if (op.equals("INV")) {
				if (q.isEmpty()) {
					sb.append("ERROR").append("\n");
					return;
				}
				q.offer(-q.pollLast());
			} else if (op.equals("DUP")) {
				if (q.isEmpty()) {
					sb.append("ERROR").append("\n");
					return;
				}
				q.offer(q.peek());
			} else if (op.equals("SWP")) {
				if (q.size() < 2) {
					sb.append("ERROR").append("\n");
					return;
				}
				long x = q.pollLast();
				long y = q.pollLast();
				q.offer(x);
				q.offer(y);
			} else {
				if (q.size() < 2) {
					sb.append("ERROR");
					return;
				}
				long x = q.pollLast();
				long y = q.pollLast();
				long res = 0;
				if (op.equals("ADD")) {
					res = x + y;
				} else if (op.equals("SUB")) {
					res = y - x;
				} else if (op.equals("MUL")) {
					res = y * x;
				} else if (op.equals("DIV")) {
					if (x == 0) {
						sb.append("ERROR").append("\n");
						return;
					}
					res = Math.abs(y) / Math.abs(x);
					if (x < 0)
						res -= res * 2;
					if (y < 0)
						res -= res * 2;
				} else if (op.equals("MOD")) {
					if (x == 0) {
						sb.append("ERROR").append("\n");
						return;
					}
					res = y % Math.abs(x);
					if (y < 0)
						res -= res * 2;
				}
				if (Math.abs(res) > 10_0000_0000) {
					sb.append("ERROR").append("\n");
					return;
				}
				q.offer(res);
			}
		}
		if (q.size() == 1) {
			sb.append(q.poll()).append("\n");
		} else {
			sb.append("ERROR").append("\n");
		}
	}

}