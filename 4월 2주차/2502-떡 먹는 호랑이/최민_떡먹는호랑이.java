import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int [] dpA;
	static int [] dpB;
	//a는 a, 0, a, a, 2a, 3a  : 3항부터 피보나치 수열 
	//b는 0, b, b, 2b, 3b : 2항부터 피보나치 수열
	
	//dpa[D]*A + dpB[D]*b = k;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		dpA= new int [d+1];
		dpB= new int [d+1];
		dpA[1]= dpB[2] =1;
		dpA[2]= dpB[1] =0;
		
		for (int i = 3; i <= d; i++) {
            dpA[i] = dpA[i - 1] + dpA[i - 2];
            dpB[i] = dpB[i - 1] + dpB[i - 2];
        }
    
		int i= k/dpB[d];
		 while (i > 0) {
	            i--;
	            if ((k - dpB[d] * i) % dpA[d] == 0 && (k - dpB[d] * i) / dpA[d] > 0) {
	                break;
	            }
	        }
	        System.out.println((k - dpB[d] * i) / dpA[d]);
	        System.out.println(i);
	}
}
