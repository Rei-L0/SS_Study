// 완탐으로 접근! 해결~

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static int N;
    static Food answer, com, min, input[];
    static List<Integer> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        min = new Food(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),0);
        answer = new Food(0,0,0,0,987654321);
        input = new Food[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            input[i] = new Food(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i < N; i++) {
            check(i, 1<<i ,input[i]);
        }

        if(list.size() == 0){
            sb.append(-1);
        }else{
            sb.append(answer.c).append("\n");
            for(int l : list){
                sb.append(l).append(" ");
            }
        }
        System.out.println(sb.toString());
    }
    static void check(int idx, int visit, Food f) {
        if(f.mp>=min.mp && f.mf>=min.mf && f.ms>=min.ms && f.mv>=min.mv && answer.c>f.c) {
            answer.mp=f.mp;
            answer.mf=f.mf;
            answer.ms=f.ms;
            answer.mv=f.mv;
            answer.c=f.c;

            list.clear();
            for(int i = 0; i < N; i++) {
                if((visit&1<<i) != 0) list.add(i+1);
            }
            return;
        }
        for(int i = idx+1; i < N; i++) {
            check(i,visit|1<<i, new Food(f.mp+input[i].mp, f.mf+input[i].mf, f.ms+input[i].ms, f.mv+input[i].mv, f.c+input[i].c));
        }
    }
    static class Food{
        int mp, mf, ms, mv, c;
        Food(int mp, int mf, int ms, int mv, int c) {
            this.mp = mp;
            this.mf = mf;
            this.ms = ms;
            this.mv = mv;
            this.c = c;
        }
    }
}
