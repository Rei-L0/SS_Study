import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	//순열구하기~!
	static int [] list;
	static int [] tgt ;
	static int n,k;
	static boolean visit [];
	static int count = 500; 
	static int result=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		visit = new boolean [n];
		tgt = new int[n];
		
		//하루마다 k
		list = new int[n];
		st= new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		choose(0);
		System.out.println(result);
	}
	
	static void choose(int tgtIdx) {
		if(tgtIdx==n) {
			count=500;
			cel();
		}
		
		for(int i=0; i<n; i++) {
			if(visit[i]) continue;
			tgt[tgtIdx]=list[i];
			visit[i]=true;
			choose(tgtIdx+1);
			visit[i]=false;
		}
	}
	
	static void cel() {
		for(int i=0; i<n; i++) {
			count-=k;
			count+=tgt[i];
			if(count<500) return;
		}
		result++;
	}
	
	
}
