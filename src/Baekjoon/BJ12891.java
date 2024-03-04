package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 12891 DNA 비밀번호
 *
 * 조건
 *
 * 입력된 문자열 중, 특정 길의의 문자열을 만들 때 {'A', 'C', 'G', 'T'}에 해당하는
 * 문자가 최소 요구한 횟수를 만족해야함 1 <= 만든 문자열 길이 <= 원본 문자열 길이 <= 100000
 *
 * 문제에서 구하고자 하는 것
 *
 * 입력된 문자열에서 입력된 길이의 문자열을 뽑았을 때 A, C, G, T로만 구성된 문자열이며, 각 문자가
 * 등장해야 할 최소한의 횟수를 만족하는 문자열의 개수
 *
 * 문제 해결 프로세스
 *
 * 임의로 만든 문자열의 길이를 입력받는다 입력받는다
 * 사용할 문자열의 길이를 입력받는다
 * 임의 문자열을 입력받는다
 * 각 문자가 나와야 할 최소 횟수를 저장할 배열을 생성한다(A, C, G, T 크기)
 * 순서에 맞게 해당 문자가 나와야하는 최소 횟수를 입력받는다
 * 사용할 문자열 후보에서 실제로 나온 문자 횟수 배열을 생성한다
 * 최초 0번째부터 p-1까지의 사용 문자열 후보를 생성하고 문자열에서 실제로 나온 문자 횟수를 배열에 각각 저장한다
 * 추후 사용할 문자열 후보의 첫, 끝 인덱스 값을 설정한다
 * 최소 횟수와 실제횟수를 비교하여 최소 기준을 통과한 경우 사용 가능한 문자열로 cnt한다
 * 끝 인덱스 값을 증가시켜 해당하는 문자 횟수를 증가시키고, 시작 인덱스에 해당하는 문자 횟수를 감소하여 다음 문자열로 넘어간다
 * 반복하여 끝 인덱스가 범위 안의 경우까지 비교하여 횟수를 구한다
 *
 * 고려한 시간 복잡도  p + 1000000 * (2+4)
 *
 */

public class BJ12891 { // 메모리 : 21736kb, 시간 : 220ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        String str = br.readLine();

        int[] minNum = new int[4]; // 각 문자가 나와야 할 최소 횟수
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            minNum[i] = Integer.parseInt(st.nextToken());
        }

        int[] pick = new int[4]; // substring에서 실제로 각 문자가 나온 횟수

        // 최초 부분 문자열 생성
        String subStr = str.substring(0, p);
        for (int i = 0; i < subStr.length(); i++) {
            switch (subStr.charAt(i)) {
                case 'A':
                    pick[0]++;
                    break;
                case 'C':
                    pick[1]++;
                    break;
                case 'G':
                    pick[2]++;
                    break;
                case 'T':
                    pick[3]++;
                    break;
            }
        }

        // 시작 끝 인덱스 설정
        int startIndex = 0;
        int endIndex = p-1;
        int count = 0; // 가능한 문자열 개수

        while (true) {
            int check = 0; // 최소 조건에 맞는지 확인(A, C, G, T 각각)
            for (int i = 0; i < 4; i++) {
                if (minNum[i] <= pick[i]) {
                    check++;
                } else
                    break;
            }

            if (check == 4) { // 모두 통과되면 가능한 문자열
                count++;
            }

            // 끝 문자열 하나 추가
            endIndex++;
            if (endIndex < str.length()) {
                switch (str.charAt(endIndex)) {
                    case 'A':
                        pick[0]++;
                        break;
                    case 'C':
                        pick[1]++;
                        break;
                    case 'G':
                        pick[2]++;
                        break;
                    case 'T':
                        pick[3]++;
                        break;
                }
            }
            else break; // 인덱스 범위 벗어나면 종료

            // 시작 문자열 하나 제거
            switch (str.charAt(startIndex)) {
                case 'A':
                    pick[0]--;
                    break;
                case 'C':
                    pick[1]--;
                    break;
                case 'G':
                    pick[2]--;
                    break;
                case 'T':
                    pick[3]--;
                    break;
            }
            startIndex++;
        }

        System.out.println(count);
    }
}
