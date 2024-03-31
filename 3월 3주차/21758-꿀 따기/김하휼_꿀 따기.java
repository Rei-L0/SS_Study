import java.io.*;
import java.util.*;
 
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
 
        long[] honeySum = new long[N+1];
        long[] honeys = new long[N+1];
        long[] input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
	
    	//누적합 구하기
        for(int i=0;i<input.length;i++){
            honeys[i+1] = input[i];
            honeySum[i+1] = honeySum[i] + input[i];
        }
 
        long max = 0L; //최대 꿀양
 
        //벌통이 오른쪽 끝에 있는 경우
        long leftHoneySum = honeySum[N] - honeys[1];
 
        for(int i=2;i<honeys.length-1;i++){
            long midHoneySum = honeySum[N] - honeySum[i];
            long sum = leftHoneySum - honeys[i] + midHoneySum;
            max = Math.max(sum,max);
        }
 
        //벌통이 왼쪽 끝에 있는 경우
        long rightHoneySum = honeySum[N-1];
 
        for(int i=honeys.length-2;i>0;i--){
            long midHoneySum = honeySum[i-1];
            long sum = rightHoneySum - honeys[i] + midHoneySum;
            max = Math.max(sum,max);
        }
 
 
        //벌통이 가운데 있는 경우
        for(int i=1;i<honeys.length-1;i++){
            long sum = honeySum[i] - honeySum[1] + honeySum[N-1] - honeySum[i-1];
            max = Math.max(sum,max);
        }
 
        System.out.println(max);
    }
}
