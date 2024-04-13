package baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bj_19942_다이어트 {
	static int goal[] = new int [4];
	static int list[][];
	static List<Integer> set = new ArrayList<>();
	static List<Integer> set2 = new ArrayList<>();
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		list = new int [N][6];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			goal[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine());
			list[i][0] = i+1;
			 for (int j = 1; j <= 5; j++) {
	         	list[i][j] = Integer.parseInt(st.nextToken());
	         }
		}
		
		find(0,0,0,0,0,0);
		
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else {
			System.out.println(min);
			for(int i : set2) {
				System.out.print(i + " ");
			}
		}
	}
	//완탐, 모든 조합
	static void find(int index, int sumP, int sumF, int sumS, int sumV, int cost) {
		if(index == list.length) {
			if (sumP >= goal[0] && sumF >= goal[1] && sumS >= goal[2] && sumV >= goal[3] && cost < min) {
               
				set2.clear();
                set2.addAll(set);
                min = cost;
               
            }
            return;
        }

		//System.out.println(list[index][1]+" "+list[index][2]+" "+list[index][3]+" "+list[index][4]+" "+list[index][5]);
        // 현재 식재료를 선택하는 경우
		if(!(list[index][1]==0 && list[index][2]==0&&list[index][3]==0&&list[index][4]==0&&list[index][5]==0)) {
	        set.add(list[index][0]);
		}

        find(index + 1, sumP + list[index][1], sumF + list[index][2], sumS + list[index][3], sumV + list[index][4], cost + list[index][5]);
        set.remove(Integer.valueOf(list[index][0]));
        
		// 현재 식재료를 선택하지 않는 경우
        find(index + 1, sumP, sumF, sumS, sumV, cost);
	}
	
	
}
