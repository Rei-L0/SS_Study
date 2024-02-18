package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj_17471_게리맨더링 {
	//이게 골4?? //나중에 꼭 다시 풀어보기(1트 : 100% 내 힘으로 못품)
	static int N;
    static int[] population;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        population = new int[N + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                graph.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        selected = new boolean[N + 1];
        int result = divideDistricts(1, 0);

        System.out.println(result);
    }

    private static int divideDistricts(int index, int count) {
        if (index > N) {
            if (count == 0 || count == N) {
                return -1;
            }

            // 두 선거구가 모두 연결되어 있는 경우
            if (checkConnected(selected) && checkConnected(notSelected(selected))) {
                int sumDistrict1 = calculatePopulation(selected);
                int sumDistrict2 = calculatePopulation(notSelected(selected));

                return Math.abs(sumDistrict1 - sumDistrict2);
            } else {
                
                return -1;
            }
        }

        // 현재 구역을 포함 O
        selected[index] = true;
        int include = divideDistricts(index + 1, count + 1);

        // 현재 구역을 포함 X
        selected[index] = false;
        int exclude = divideDistricts(index + 1, count);

        // 두 경우 중에서 최솟값 반환
        if (include == -1 || exclude == -1) {
            return Math.max(include, exclude);
        } else {
            return Math.min(include, exclude);
        }
    }

    private static boolean checkConnected(boolean[] selected) {
        boolean[] visited = new boolean[N + 1];
        int start = -1;

        for (int i = 1; i <= N; i++) {
            if (selected[i]) {
                start = i;
                break;
            }
        }

        dfs(start, selected, visited);

        for (int i = 1; i <= N; i++) {
            if (selected[i] && !visited[i]) {
                return false;
            }
        }

        return true;
    }

    private static void dfs(int node, boolean[] selected, boolean[] visited) {
        visited[node] = true;

        for (int next : graph.get(node)) {
            if (selected[next] && !visited[next]) {
                dfs(next, selected, visited);
            }
        }
    }

    private static boolean[] notSelected(boolean[] selected) {
        boolean[] result = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            result[i] = !selected[i];
        }
        return result;
    }

    private static int calculatePopulation(boolean[] selected) {
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            if (selected[i]) {
                sum += population[i];
            }
        }
        return sum;
    }
}
