import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n1= Integer.parseInt(st.nextToken());
		int n2 = Integer.parseInt(st.nextToken());
		
		char[] ants1 = br.readLine().toCharArray();
        char[] ants2 = br.readLine().toCharArray();
        
       
        char [] total = new char[n1+n2];
        int [] totalD = new int[n1+n2];
        
        for(int i=0; i<n1; i++) {
        	totalD[i] = -1; //오른쪽으로 가는건 -1, 왼쪽으로 가는건 0
        }
		int count=0;
		for(int i=n1-1; i>=0; i--) {
			total[count] = ants1[i];
			count++;
		}
		
		for(int i=0; i<n2; i++) {
			total[count] = ants2[i];
			count++;
		}
		
		int t = Integer.parseInt(br.readLine());
		
		for(int i=0; i<t; i++) {
			for(int j=0; j<n1+n2-1; j++) {
				if(totalD[j]==-1 && totalD[j+1]==0) { //방향이 다르다면
					
					int temp = totalD[j];
					totalD[j]=totalD[j+1];
					totalD[j+1] = temp;
					
					char tem = total[j];
					total[j] = total[j+1];
					total[j+1] = tem; //스위치
					
					j++; //한번에 한개의 개미쌍들만 점프하므로 이동한 경우 다음 개미는 이미 바꾼 개미이니까 ++;
				}
			}
		}
		
		for (int i=0; i<total.length; i++) {
			System.out.print(total[i]);
		}
	}
}
