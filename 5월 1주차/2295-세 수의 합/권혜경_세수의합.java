// //시간초과 극복못함,,
// // 극복! fisrt에서도 가지치기 필수,,


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main{
    static int N;
    static List<Integer> input = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++){
            input.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(input);

        int sum, first, second, third;
        for(int i = N-1; i >= 1; i--){ // 최종합
            sum = input.get(i);
            check : for(int j = i-1; j >= 0; j--){ // 제일 큰 수
                first = input.get(j);
                if(sum>first*3) break;

                for(int k = j; k >= 0; k--){ // 두 번째로 큰 수
                    second = input.get(k);

                    if(sum<=first+second) continue;
                    else if(sum>first+second*2) break;
                    else {
                        if(input.indexOf(sum-first-second)!=-1) {
                            System.out.println(sum);
                            return;
                        }
                    }

                }
            }
        }

    }
}
// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.List;

// class Main{
//     static int N;
//     static List<Integer> input = new ArrayList<>();
//     public static void main(String[] args) throws Exception {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         N = Integer.parseInt(br.readLine());
//         for(int i = 0; i < N; i++){
//             input.add(Integer.parseInt(br.readLine()));
//         }
//         Collections.sort(input);


//         for(int i = N-1; i >= 1; i--){
//             check : for(int j = i-1; j >= 0; j--){
//                 for(int k = j; k >= 0; k--){
//                     if(input.get(i) > input.get(j) + input.get(k)*2) {
//                         if(input.get(i)*2 > (input.get(j)+input.get(k))*3) break check;
//                         break;
//                     }
//                     if(input.contains(Integer.valueOf(input.get(i)-input.get(j)-input.get(k)))){
//                         System.out.println(input.get(i));
//                         return;
//                     }
//                 }
//             }
//         }

//     }
// }
