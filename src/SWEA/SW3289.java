package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 3289 서로소 집합
 *
 * 조건
 * 초기 집합 개수 n : 1 ~ 1,000,000
 * 연산 개수 m : 1 ~ 100,000
 * 합집합 입력 형태 : 0 a b (a 포함 집합과 b 포함 집합을 합침)
 * 같은 집합 확인 입력 형태 : 1 a b (두 원소가 같은 집합에 포함되어 있는지 확인)
 * a, b : n이하의 자연수
 *
 * 문제에서 구하고자 하는 것
 * 같은 집합 확인 시, 같은 집합에 속해있다면 1 아니면 0 출력
 * union-find 알고리즘 작성
 *
 * 문제 해결 프로세스
 * find 알고리즘 작성
 *  해당 원소의 루트(부모)를 return 하도록 함
 *  만약 현재 원소 번호가 루트 번호와 같으면 그대로 return
 *  같지 않으면 재귀를 통해 부모로 타고 올라가자
 *
 * union 알고리즘 작성
 * a, b에 대해 각각 find 알고리즘을 호출해 return 값을 받는다
 * 만약 루트(부모)가 같으면 이미 같은 집합이므로 합집합x
 * 다르면 루트를 합치자
 *
 * 이 과정에서 입력이 1로 시작되는 연산의 경우 find 연산 결과(같은 집합이면 1, 다른 집합이면 0)를 출력
 *
 * 고려한 시간 복잡도
 * 1000000log0010000 = 40000000
 * */

public class SW3289 { // 메모리 : 104196kb, 시간 : 438ms
    static int n;
    static int m;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            sb.append("#").append(t).append(" ");

            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            parents = new int[n+1];

            for(int i = 1; i < n; i++) {
                parents[i] = i;
            }

            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int cmd = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(cmd == 0) union(a, b);
                if(cmd == 1) {
                    int resultA = find(a);
                    int resultB = find(b);

                    if(resultA == resultB) sb.append(1);
                    else sb.append(0);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int find(int a) {
        if(a == parents[a]) return a;

        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }
}
