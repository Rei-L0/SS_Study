package Baekjoon.문자열;
import java.io.*;
import java.util.*;

class P3048 { 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Character[] ants = new Character[N+M];
		int[] check = new int[N+M];

		String s1 = br.readLine();
		for (int i=0; i<N; i++) {
			ants[N-1-i] = s1.charAt(i);
			check[N-1-i] = 0;
		}
		String s2 = br.readLine();
		for (int i=0; i<M; i++) {
			ants[N+i] = s2.charAt(i);
			check[N+i] = 1;
		}

		int t = Integer.parseInt(br.readLine());

		for (int i=0; i<t; i++) {
			int idx = 0;
			while (true) {
				if (idx >= ants.length-1) break;
				else {
					if (check[idx] == 0 &&  check[idx+1] == 1) {
						Character tmp = ants[idx];
						ants[idx] = ants[idx+1];
						ants[idx+1] = tmp;
						check[idx] = 1;
						check[idx+1] = 0;
						idx+=2;
					}
					else {
						idx++;
					}
				}
      }
			}
			for (char next: ants) {
				sb.append(next);
		}
		System.out.println(sb);
	}
}
