package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

// add 할때마다 정렬을 하기 때문에 시간초과가 난 것 같음 (list 사용)
// 처음에 removeif를 통해 해당 문제 번호를 찾아서 삭제해줬더니 시간 초과 (treeSet 사용)
// -> hashmap을 사용하여 key value 저장하고 이를 활용하여 removeif(O(n*log n))-> remove(O(log n))사용 -> 해결

public class bj_21939_문제추천시스템version1 {
	static TreeSet<Question> list = new TreeSet<>();
	static Map<Integer, Integer> map = new HashMap<>();
	static class Question implements Comparable<Question>{
		int num;
		int difficulty;
		public Question(int num, int difficulty) {
			super();
			this.num = num;
			this.difficulty = difficulty;
		}
		@Override
		public int compareTo(Question o) {
			// TODO Auto-generated method stub
			if(this.difficulty == o.difficulty) {
				return o.num-this.num;
			}
			return o.difficulty-this.difficulty; //내림차순
		}
		@Override
		public String toString() {
			return "Question [num=" + num + ", difficulty=" + difficulty + "]";
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n= Integer.parseInt(br.readLine()); // 리스트에 존재하는 문제의 개수
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int diff = Integer.parseInt(st.nextToken());
			list.add(new Question(num, diff));
			map.put(num, diff);
		}
		
		
		n= Integer.parseInt(br.readLine()); //명령어 입력
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String c = st.nextToken();
			if(c.equals("add")) {
				int num = Integer.parseInt(st.nextToken());
				int diff = Integer.parseInt(st.nextToken());
				list.add(new Question(num, diff));
				map.put(num, diff);
			}
			else if(c.equals("recommend")) {
				int num = Integer.parseInt(st.nextToken());
				if(num==1) {
					sb.append(list.first().num).append("\n");
				}
				else {
					sb.append(list.last().num).append("\n");
				}
			}
			else if(c.equals("solved")) {
				int num = Integer.parseInt(st.nextToken());
				list.remove(new Question(num, map.get(num)));
				map.remove(num);
			}
//			System.out.println(list);
//			System.out.println();
		}
		
		System.out.println(sb);
	}
	
}
