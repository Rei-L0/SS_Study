import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 중앙값제거_23758 {
    public static void main(String[] args) throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_2[] = new int [31];
        int two_N[] = new int [31];
        two_N[0] = 1;
        for(int i = 1; i<=30; i++){
            two_N[i] = two_N[i-1]*2;
        }
        for(int i=0;i<N;i++){
            int temp = Integer.parseInt(st.nextToken());
            if(temp<two_N[0]) num_2[0]++;
            else if(temp<two_N[1]) num_2[1]++;
            else if(temp<two_N[2]) num_2[2]++;
            else if(temp<two_N[3]) num_2[3]++;
            else if(temp<two_N[4]) num_2[4]++;
            else if(temp<two_N[5]) num_2[5]++;
            else if(temp<two_N[6]) num_2[6]++;
            else if(temp<two_N[7]) num_2[7]++;
            else if(temp<two_N[8]) num_2[8]++;
            else if(temp<two_N[9]) num_2[9]++;
            else if(temp<two_N[10]) num_2[10]++;
            else if(temp<two_N[11]) num_2[11]++;
            else if(temp<two_N[12]) num_2[12]++;
            else if(temp<two_N[13]) num_2[13]++;
            else if(temp<two_N[14]) num_2[14]++;
            else if(temp<two_N[15]) num_2[15]++;
            else if(temp<two_N[16]) num_2[16]++;
            else if(temp<two_N[17]) num_2[17]++;
            else if(temp<two_N[18]) num_2[18]++;
            else if(temp<two_N[19]) num_2[19]++;
            else if(temp<two_N[20]) num_2[20]++;
            else if(temp<two_N[21]) num_2[21]++;
            else if(temp<two_N[22]) num_2[22]++;
            else if(temp<two_N[23]) num_2[23]++;
            else if(temp<two_N[24]) num_2[24]++;
            else if(temp<two_N[25]) num_2[25]++;
            else if(temp<two_N[26]) num_2[26]++;
            else if(temp<two_N[27]) num_2[27]++;
            else if(temp<two_N[28]) num_2[28]++;
            else if(temp<two_N[29]) num_2[29]++;
            else num_2[30]++;
        }
        //System.out.println(Arrays.toString(num_2));
        int count=0, answer = 0;
        for(int i=0;i<num_2.length;i++){
            if(count+num_2[i]<=(N+1)/2){
                count+=num_2[i];
                answer+=num_2[i]*(i-1);
                continue;
            }
            for(int j=0;j<num_2[i];j++){
                if(count>=(N+1)/2) break;
                count+=1;
                answer+=i-1;
            }
        }
        System.out.println(answer+1);
    }
}
