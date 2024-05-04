import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
// 9876543210 가 가장 큰 수 이므로, 이보다 값이 크고 감소하는 수는 없음.
// 0부터 1씩 증가해 가며 감소하는 수 찾기 -> 수 조합하여 감소하는 수 만들어서 정렬.
import java.util.Arrays;
import java.util.Collections;

public class B_1038 {
	static int N;
	static ArrayList<Long> descNumbers = new ArrayList<>();
	static int tgtCnt;
	static int[] tgt = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for (int i = 1; i < 11; i++) {
			tgtCnt = i;
			comb(0, 0, (long) 0);
		}
		Collections.sort(descNumbers);
		if (N > descNumbers.size()-1) {
			System.out.println(-1);
		} else {
			System.out.println(descNumbers.get(N));

		}

	}

	static void comb(int tgtIdx, int cnt, Long sum) {
		if (cnt == tgtCnt) {

			descNumbers.add(sum);
			return;
		}
		if (tgtIdx == 10)
			return;

		comb(tgtIdx + 1, cnt + 1, sum * 10 + tgt[tgtIdx]);
		comb(tgtIdx + 1, cnt, sum);

	}

}
