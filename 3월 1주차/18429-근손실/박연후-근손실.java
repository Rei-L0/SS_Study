import java.io.*;
import java.util.*;

public class Main {

	static int n, k, ans;

	static int[] num;

	// ŰƮ ��ȣ�� ������ Ž���Ѵ�.
	// �ش� ŰƮ ����Ͽ� �߷� 500�̸��� �� ��� Ž�� X
	static void solve(int count, int weight, boolean[] visit) {
		if (count == n) {
			ans++;
			return;
		}
		for (int i = 0; i < n; i++) {
			if (visit[i])
				continue;
			if (weight + num[i] - k >= 500) {
				visit[i] = true;
				solve(count + 1, weight + num[i] - k, visit);
				visit[i] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		num = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			num[i] = Integer.parseInt(st.nextToken());

		// �ʱ� �߷� 500���� ����
		solve(0, 500, new boolean[n]);

		System.out.println(ans);
	}
}