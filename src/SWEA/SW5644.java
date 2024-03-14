package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * SWEA 5644 무선 충전
 *
 * 조건
 * 지도의 크기는 10 x 10
 * 사용자는 총 2명의 시작점 : A(1,1), B(10,10)
 * 총 이동 시간 M : 20 ~ 100
 * BC의 개수 1 ~ 8
 * 초기 위치부터 충전 가능
 * 한 위치에 BC는 최대 1개만 설치 가능
 * 사용자가 지도 이탈하는 경우는 없다
 * 위치 : X, Y
 * 충전 범위 C : 1 ~ 4
 * 성능 P : 10 ~ 500 (짝수)
 * BC 정보(위 3가지 정보)
 * 두 지점의 거리 : x좌표의 차 + y좌표의 차 (절댓값) -> C 이하면 BC 접속 가능
 * 서로 다른 BC가 충전 범위에 겹친다면 하나를 선택해서 접속 가능
 * 만약 한 BC에 2명의 사용자가 접속하면 충천양 / 2로 분배한다
 * 이동 경로
 * 0 : 이동x
 * 1 : 상
 * 2 : 우
 * 3 : 하
 * 4 : 좌
 * 시간 T : 1초 단위
 *
 * 문제에서 구하고자 하는 것
 * 모든 사용자가 충전한 양의 합의 최대값값 * 문제 해결 프로세스
 *
 * 문제 해결 프로세스
 * BC 개수 만큼 map 크기와 같은 boolean 배열 생성
 *  1. BC의 정보를 통해 BC의 충전 범위를 충전성능으로 만들어준다
 *  2. 완성된 배열을 List<int[][]>에 담아준다
 *  해당
 * 초기 위치의 좌표값을 List i번의 좌표값으로 확인한다
 * i의 좌표값이 0이 아닌 경우는 충전을 할 수 있는 것 ->
 * -> 새로운 임시 배열 하나 만들어 정수값 저장
 * 내림차순 정렬, 1번부터 사용, 만약 2명인데 BC가 1개인 경우만 배분 처리
 * 사용자 A의 cmd, B의 cmd를 하나씩 보면서 끝까지 반복
 *
 * 고려한 시간 복잡도
 * 100 * 10 * 10 + 4 * 4 * 8 = 10128 충분
 * */

public class SW5644 { // 메모리 : 24748kb, 시간 : 127ms
    static int a;
    static int x;
    static int y;
    static int[][] map;
    static int rowA;
    static int colA;
    static int rowB;
    static int colB;
    static int sumA;
    static int sumB;
    static int[][] delta = new int[][]{{0, 0}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 정지 상 우 하 좌
    static Queue<Integer> userA;
    static Queue<Integer> userB;
    static List<int[][]> charge;
    static int[][] BC;
    static boolean[] isCheckedA;
    static boolean[] isCheckedB;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
            rowA = 1;
            colA = 1;
            rowB = 10;
            colB = 10;

            map = new int[11][11];
            int m = Integer.parseInt(st.nextToken()); // 총 이동 시간
            a = Integer.parseInt(st.nextToken()); // 총 BC 개수

            st = new StringTokenizer(br.readLine());
            userA = new ArrayDeque<>();
            for (int i = 0; i < m; i++) {
                userA.offer(Integer.parseInt(st.nextToken()));
            }

            st = new StringTokenizer(br.readLine());
            userB = new ArrayDeque<>();
            for (int i = 0; i < m; i++) {
                userB.offer(Integer.parseInt(st.nextToken()));
            }
            charge = new ArrayList<>();

            // 각 충전기의 충전 범위 구하기
            for (int i = 0; i < a; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                BC = new int[11][11];
                chargeRange(0, x, y, c, p);

                charge.add(BC);
            }

            // 사용자 좌표를 탐색하며 충전 최대값 구하기
            sumA = 0;
            sumB = 0;

            int maxA = 0;
            int maxIdxA = 0;

            isCheckedA = new boolean[a];
            for (int i = 0; i < charge.size(); i++) {
                if (charge.get(i)[rowA][colA] > 0) {
                    if (maxA <= charge.get(i)[rowA][colA]) {
                        maxA = charge.get(i)[rowA][colA];
                        isCheckedA[i] = true;
                        if (i != 0)
                            isCheckedA[maxIdxA] = false;
                        maxIdxA = i;
                    }
                }
            }

            int maxB = 0;
            int maxIdxB = 0;

            isCheckedB = new boolean[a];
            for (int i = 0; i < charge.size(); i++) {
                if (charge.get(i)[rowB][colB] > 0) {
                    if (maxB < charge.get(i)[rowB][colB]) {
                        maxB = charge.get(i)[rowB][colB];
                        isCheckedB[i] = true;
                        if (i != 0)
                            isCheckedB[maxIdxB] = false;
                        maxIdxB = i;
                    }
                }
            }

            if (maxIdxA == maxIdxB && maxA != 0 && maxB != 0) {
                int secondA = 0;
                int secondB = 0;
                for (int i = 0; i < charge.size(); i++) {
                    if (i == maxIdxA) continue;
                    secondA = Math.max(secondA, charge.get(i)[rowA][colA]);
                    secondB = Math.max(secondB, charge.get(i)[rowB][colB]);

                }

                if (secondA > secondB) maxA = secondA;

                else maxB = secondB;

            }

            sumA += maxA;
            sumB += maxB;

            for (int i = 0; i < m; i++) {
                moveUser();
            }

            int answer = sumA + sumB;
            System.out.println("#" + t + " " + answer);
        }
    }

    private static void chargeRange(int index, int row, int col, int c, int p) {
        if (index == c) return;

        for (int d = 0; d < 5; d++) {
            int nr = row + delta[d][0];
            int nc = col + delta[d][1];
            if (nr > 0 && nr < 11 && nc > 0 && nc < 11) {
                int distance = Math.abs(x - nr) + Math.abs(y - nc);
                if (distance <= c) {
                    BC[nc][nr] = p;
                    chargeRange(index + 1, nr, nc, c, p);
                }
            }
        }

    }

    private static void moveUser() {

        switch (userA.poll()) {
            case 0:
                rowA += delta[0][0];
                colA += delta[0][1];
                break;
            case 1:
                rowA += delta[1][0];
                colA += delta[1][1];
                break;
            case 2:
                rowA += delta[2][0];
                colA += delta[2][1];
                break;
            case 3:
                rowA += delta[3][0];
                colA += delta[3][1];
                break;
            case 4:
                rowA += delta[4][0];
                colA += delta[4][1];
                break;
        }

        int maxA = 0;
        int maxIdxA = 0;
        isCheckedA = new boolean[a];
        for (int i = 0; i < charge.size(); i++) {
            if (charge.get(i)[rowA][colA] > 0) {
                if (maxA <= charge.get(i)[rowA][colA]) {
                    maxA = charge.get(i)[rowA][colA];
                    isCheckedA[i] = true;
                    if (i != 0)
                        isCheckedA[maxIdxA] = false;
                    maxIdxA = i;
                }
            }
        }

        switch (userB.poll()) {
            case 0:
                rowB += delta[0][0];
                colB += delta[0][1];
                break;
            case 1:
                rowB += delta[1][0];
                colB += delta[1][1];
                break;
            case 2:
                rowB += delta[2][0];
                colB += delta[2][1];
                break;
            case 3:
                rowB += delta[3][0];
                colB += delta[3][1];
                break;
            case 4:
                rowB += delta[4][0];
                colB += delta[4][1];
                break;
        }

        int maxB = 0;
        int maxIdxB = 0;

        isCheckedB = new boolean[a];
        for (int i = 0; i < charge.size(); i++) {
            if (charge.get(i)[rowB][colB] > 0) {
                if (maxB < charge.get(i)[rowB][colB]) {
                    maxB = charge.get(i)[rowB][colB];
                    isCheckedB[i] = true;
                    if (i != 0)
                        isCheckedB[maxIdxB] = false;
                    maxIdxB = i;
                }
            }
        }


        if (maxIdxA == maxIdxB && maxA != 0 && maxB != 0) {
            int secondA = 0;
            int secondB = 0;
            for (int i = 0; i < charge.size(); i++) {
                if (i == maxIdxA) continue;
                secondA = Math.max(secondA, charge.get(i)[rowA][colA]);
                secondB = Math.max(secondB, charge.get(i)[rowB][colB]);

            }

            if (secondA > secondB) maxA = secondA;

            else maxB = secondB;
        }

        sumA += maxA;
        sumB += maxB;
    }
}