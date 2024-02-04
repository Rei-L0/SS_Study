package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class B_2338 {
   public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BigInteger A = new BigInteger (br.readLine());
    BigInteger B = new BigInteger (br.readLine());
    System.out.println(A.add(B)); 
    System.out.println(A.subtract(B)); 
    System.out.println(A.multiply(B));    

  }
}
