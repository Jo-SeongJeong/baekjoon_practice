package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1244 스위치 켜고 끄기
 * 조건
 * 성별
 * 남자 = 1, 여자 = 1
 * 남자인 경우 받은 번호의 배수인 스위치 상태 변경
 * 여자인 경우 본인 스위치 기준 좌우 대칭인 경우 상태 변경
 * 학생 수 = 100 이하인 양의 정수
 *
 * 문제에서 구하고자 하는 것
 * 모든 조건에 맞게 스위치 변경 후 최종 상태
 *
 * 문제 해결 프로세스
 * 스위치의 개수를 입력 받는다
 * 스위치의 상태를 저장 할 배열을 만들고 상태를 입력 받는다
 * 스위치의 상태를 변경 할 학생 수를 입력 받는다
 * 학생의 성별과 스위치 번호를 입력 받아 조건에 맞게 스위치의 상태를 변경한다
 * 모든 조건에 맞게 스위치 상태를 변경하고 스위치의 상태를 출력한다
 *
 * 고려한 시간 복잡도
 * 100(학생수) * 100(스위치 배열의 길이)
 *
 * */

public class BJ1244 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] array = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i < n+1; i++) {
            array[i] = Integer.parseInt(st.nextToken());

        }

        int student = Integer.parseInt(br.readLine());

        for(int i = 0; i < student; i++) {
            st = new StringTokenizer(br.readLine());

            int gender = Integer.parseInt(st.nextToken());

            int number = Integer.parseInt(st.nextToken());

            // 남자인 경우
            if(gender == 1) {
                int j = 1;
                int mul = number;
                while (mul < n + 1) {
                    if (array[mul] == 1) array[mul] = 0;

                    else if (array[mul] == 0) array[mul] = 1;

                    mul = number;
                    j++;
                    mul *= j;
                }
            }

            // 여자인 경우
            else if(gender == 2) {
                int preIdx = number-1;
                int nextIdx = number+1;

                if (array[number] == 1) array[number] = 0;

                else if (array[number] == 0) array[number] = 1;

                while(preIdx > 0 && nextIdx < n+1) {
                    if(array[preIdx] != array[nextIdx])  break;

                    if(array[preIdx] == 0) {
                        array[preIdx] = 1;
                        array[nextIdx] = 1;
                    }
                    else if(array[preIdx] == 1) {
                        array[preIdx] = 0;
                        array[nextIdx] = 0;
                    }
                    preIdx--;
                    nextIdx++;
                }
            }
        }

        for(int i = 1; i < n+1; i++) {
            System.out.print(array[i] + " ");
            if (i % 20 == 0) {
                System.out.println();
            }
        }
    }
}

// 메모리 : 11612kb 시간 : 80ms