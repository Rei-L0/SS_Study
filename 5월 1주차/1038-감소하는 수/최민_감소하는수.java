import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	// 0~9로 만들수 있는 감소하는 수의 개수는 2^10-1인 1023개
	// 앞자리수가 3이면 마지막에 올수 있는 수는 0, 1, 2,3
	// 32 -> 321 , 320
	// 33 -> 332, 331 은 안됨 -> 감소수에서 감소수가 생긴다.
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	
        solution(n);

    }
        public static void solution(int n) {
            if (n < 10) { //일의 자리수는 값과 동일
                System.out.println(n);
                return;
            }
            if (n >= 1023) {
                System.out.println(-1);
                return;
            }

            List<Long> nums = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                nums.add((long) i);
            }

            int target = 0;

            while (true) {
                long before = nums.get(target) % 10; //ex 42 => 2
                for (int num = 0; num < before; num++) {
                    nums.add(nums.get(target) * 10 + num); //420, 421

                    if (n + 1 == nums.size()) { //idx가 0부터 시작하므로 n-1
                        System.out.println(nums.get(n));
                        return;
                    }
                }
                target++;
            }
        }
    }
