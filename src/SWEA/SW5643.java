package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 5643 키순서
 *
 * 조건
 * testCase 수  : 15
 * 학생 수 N(1부터 N) : 2 ~ 500
 * 키 비교 횟수 : 0 ~ 124,750
 * N명의 학생은 키가 모두 다르다
 * 두 학생끼리 키를 비교한 결과의 일부가 입력
 * 입력 구성
 * a b -> a인 학생이 b인 학생보다 키가 작다
 *
 * 문제에서 구하고자 하는 것
 * 자신의 키가 몇번째인지 알 수 있는 사람인 몇명인지
 *
 * 문제 해결 프로세스
 * 인접행렬을 만들자
 * 입력에 대해 단방향 그래프로 인접행렬 채우기(가중치 없는 그래프)
 * 길이가 n인 visit 배열 생성
 * 정점 기준 더 작은 사람 탐색 -> 가능하면 해당 번호 true
 * 정점 기준 더 큰 사람 탐색 -> 가능하면 해당 번호 true
 * 모두 true가 되는 지 확인하자
 *
 * 고려한 시간 복잡도
 * 500 * 500 * 15 = 3,750,000
 * */

public class SW5643 { // 메모리 : 100860kb, 시간 : 2849ms
    static int n;
    static int m;
    static int[][] tallAdjMatrix;
    static int[][] shortAdjMatrix;
    static boolean[] isChecked;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            sb.append("#" + t +" ");

            n = Integer.parseInt(br.readLine());
            m = Integer.parseInt(br.readLine());

            tallAdjMatrix = new int[n+1][n+1];
            shortAdjMatrix = new int[n+1][n+1];

            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                tallAdjMatrix[from][to] = 1;
                shortAdjMatrix[to][from] = 1;
            }

            int count = 0;
            for(int i = 1; i < n+1; i++) {
                isChecked = new boolean[n+1];

                isChecked[i] = true;
                tallerThanMe(i);
                shorterThanMe(i);

                boolean flag = true;
                for(int j = 1; j < n+1; j++) {
                    if(!isChecked[j]) {
                        flag = false;
                        break;
                    }
                }

                if(flag) count++;
            }
            sb.append(count + "\n");
        }
        System.out.println(sb);
    }

    private static void tallerThanMe(int from) {
        for(int i = 1; i < n+1; i++) {
            if(i == from) continue;
            if(tallAdjMatrix[from][i] == 0) continue;
            if(isChecked[i]) continue;

            isChecked[i] = true;
            tallerThanMe(i);
        }

    }

    private static void shorterThanMe(int from) {

        for(int i = 1; i < n+1; i++) {
            if(i == from) continue;
            if(shortAdjMatrix[from][i] == 0) continue;
            if(isChecked[i]) continue;

            isChecked[i] = true;
            shorterThanMe(i);
        }
    }
}
