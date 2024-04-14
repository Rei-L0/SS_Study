import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for(int t = 0; t < T; t++) {
			int N = sc.nextInt();
			ArrayList<Grade> list = new ArrayList<>();

			for(int i = 0; i < N; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();

				list.add(new Grade(a, b));
			}

			Collections.sort(list);

			int ans = 1;  
			int min = list.get(0).b; 
			for(int i = 1; i < N; i++) {
				if(list.get(i).b < min) { 
					ans++;
					min = list.get(i).b; 
				}
			}
			System.out.println(ans);
		}	
	}
  class Grade implements Comparable<Grade> {
	int a,b;

	Grade(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public int compareTo(Grade o) {
		if(this.a > o.a) {
			return 1;
		} else {
			return -1;
		}
	}
}
}
