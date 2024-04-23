import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 어려운 건 아닌데 문제 잘 읽고 잘 생각해서 코드 짜야 하는 문제... 
 * 별거 아닌 걸로 여러번 틀림 
 * 자료형 선택/스택 사이즈 체크 중요,,,
 */
public class Main {

	static ArrayDeque<Integer> stack;
	static ArrayList<String> calc;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		calc = new ArrayList<>();
		while(true) {
			String o = br.readLine();
			if(o.equals("QUIT")) break;
			else if(isInteger(o)) {
				int N = Integer.parseInt(o);
				for(int i = 0; i < N; i++) {
					stack = new ArrayDeque<>();
					stack.add(Integer.parseInt(br.readLine()));
					boolean flag = true;
					for(String cal : calc) {

						if(cal.length() > 4) {
							StringTokenizer st = new StringTokenizer(cal);
							if(st.nextToken().equals("NUM")) stack.push(Integer.parseInt(st.nextToken()));
						}
						else if(stack.isEmpty()) {
							flag = false;
							break;
						}
						else if(cal.equals("POP")) stack.pop();
						else if(cal.equals("INV")) inv();
						else if(cal.equals("DUP")) dup();
						else if(cal.equals("SWP")) {
							if(!sizecheck()) break;
							else swp();
						}
						else if(cal.equals("ADD")) {
							if(!sizecheck() || !add()) {
								flag = false;
								break;
							}
						}
						else if(cal.equals("SUB")) {
							if(!sizecheck() || !sub()) {
								flag = false;
								break;
							}
						}
						else if(cal.equals("MUL")) {
							if(!sizecheck() || !mul()) {
								flag = false;
								break;
							}
						}
						else if(cal.equals("DIV")) {
							if(!sizecheck() || !div()) {
								flag = false;
								break;
							}
						}
						else if(cal.equals("MOD")) {
							if(!sizecheck() || !mod()) {
								flag = false;
								break;
							}
						}
						else if(cal.equals("END")) {
							if(flag && stack.size() == 1) sb.append(stack.peek()).append("\n");
							else sb.append("ERROR\n");
						}
					}
					if(!flag) sb.append("ERROR").append("\n");
				}
				sb.append("\n");
				calc = new ArrayList<>();
			}
			else {
				calc.add(o);
			}
		}
		System.out.println(sb);
	}
	static boolean sizecheck() {
		if(stack.size() >= 2) return true;
		return false;
	}
	static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	static void inv() {
		int a = stack.pop();
		stack.push(-a);
	}
	static void dup() {
		stack.push(stack.peek());
	}
	static void swp() {
		int a = stack.pop();
		int b = stack.pop();
		stack.push(a);
		stack.push(b);
	}
	static boolean add() {
		int a = stack.pop();
		int b = stack.pop();
		if(Math.abs(a+b) > 1_000_000_000) return false;
		else {
			stack.push(a+b);
			return true;
		}
	}
	static boolean sub() {
		int a = stack.pop();
		int b = stack.pop();
		if(Math.abs(b-a) > 1_000_000_000) return false;
		else {
			stack.push(b-a);
			return true;
		}
	}
	static boolean mul() {
		long a = (long)stack.pop();
		long b = (long)stack.pop();
		if(a*b > 1_000_000_000) return false;
		else {
			stack.push((int) (a*b));
			return true;
		}
	}
	static boolean div() {
		long a = stack.pop();
		long b = stack.pop();
		if(a == 0) return false;
		if(a * b < 0) stack.push(-(int)(Math.abs(b) / Math.abs(a)));
		else stack.push((int) (Math.abs(b) / Math.abs(a)));
		
		return true;
	}
	static boolean mod() {
		int a = stack.pop();
		int b = stack.pop();
		if(a == 0) return false;
		if(b < 0) stack.push(-(Math.abs(b) % Math.abs(a)));
		else stack.push(Math.abs(b) % Math.abs(a));
		
		return true;
	}
}

