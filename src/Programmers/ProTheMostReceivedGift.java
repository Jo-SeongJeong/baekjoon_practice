package Programmers;

import java.util.HashMap;
import java.util.Map;

/**
 * 프로그래머스 가장 많이 받은 선물
 *
 * 조건
 * 친구들 수 : 2 ~ 50
 * 선물 횟수 : 1 ~ 10000
 * 선물 규칙
 * 두 사람이 선물을 주고받은 기록이 있는 경우
 * 서로 덜 준 사람에게 1개 선물
 * 아닌 경우 (선물 교환 수가 같은 경우)
 * 선물지수가 높은 사람에게 1개 선물
 * 선물지수도 같다면 선물 없음
 *
 * 문제에서 구하고자 하는 것
 * 가장 많이 선물을 받은 사람의 선물 개수
 *
 * 문제 해결 프로세스
 * 2차원 배열로 map 만들어서 싹다 돌려보자
 * 그래프 탐색처럼
 *
 * 고려한 시간 복잡도
 * 50 * 50
 */

public class ProTheMostReceivedGift {
    static int[][] adjMatrix;
    static int n;
    static Map<String, Integer> map;
    static Map<Integer, Integer> count;
    static boolean[][] visited;

    public static void main(String[] args) {
        String[] friends = new String[]{"muzi", "ryan", "frodo", "neo"};
        String[] gifts = new String[] {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};

        int answer = solution(friends, gifts);
        System.out.println(answer);
    }

    public static int solution(String[] friends, String[] gifts) {
        int answer = 0;
        n = friends.length;

        adjMatrix = new int[n][n];
        visited = new boolean[n][n];

        map = new HashMap<>();
        count = new HashMap<>();

        int idx = 0;
        for(int i = 0; i < n; i++) {
            map.put(friends[i], idx);
            count.put(idx, 0);
            idx++;
        }

        for(int i = 0; i < gifts.length; i++) {
            String str = gifts[i];
            String a = str.substring(0, str.indexOf(" "));
            String b = str.substring(str.indexOf(" ")+1);

            adjMatrix[map.get(a)][map.get(b)]++;
        }

        for(int i = 0; i < n; i++) {
            give(i);
        }

        for(int key : count.keySet()) answer = Math.max(answer, count.get(key));
        return answer;
    }

    private static void give(int cur) {

        for(int i = 0; i < n; i++) {
            if(cur == i) continue;
            if(visited[cur][i]) continue;

            visited[cur][i] = true;
            visited[i][cur] = true;

            if(adjMatrix[cur][i] > adjMatrix[i][cur]) count.put(cur, count.get(cur)+1);
            else if(adjMatrix[cur][i] < adjMatrix[i][cur]) count.put(i, count.get(i)+1);
            else {
                int score1 = 0;
                int score2 = 0;

                for(int j = 0; j < n; j++) {
                    score1 += adjMatrix[cur][j];
                    score1 -= adjMatrix[j][cur];
                    score2 += adjMatrix[i][j];
                    score2 -= adjMatrix[j][i];
                }
                if(score1 > score2) count.put(cur, count.get(cur)+1);
                else if(score1 < score2) count.put(i, count.get(i)+1);
            }
        }
    }
}
