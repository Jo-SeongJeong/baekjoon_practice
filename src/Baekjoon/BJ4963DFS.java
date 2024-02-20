package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 4963 섬의 개수
 * 
 * 조건
 * 지도의 너비 w * h : 각 1 ~ 50
 * 1 : 땅
 * 0 : 바다
 * 입력의 마지막 줄 : 0 0
 * 지도 밖으로 나갈 수 없음
 * 가로, 세로, 또는 대각선으로 연결 되어 있는 사각형은 걸어갈 수 있는 사각형 -> 한 개의 섬
 * 
 * 문제에서 구하고자 하는 것
 * 섬의 개수
 * 
 * 문제 해결 프로세스
 * DFS, BFS 가능
 * DFS
 * 탐색 중, 1을 발견하면 8방 탐색하며 깊이 우선 탐색
 * 좌표 0, 0 ~ w-1, h-1 까지 탐색
 * 만약 8방향에 1이 없다면 섬 개수 ++, return
 * 1이 있다면 해당 좌표 0으로 변경, 반복
 * 
 * 고려한 시간 복잡도
 * 8 * 50 * 50 = 20000
 * */

public class BJ4963DFS { // 메모리 : 13320kb, 시간 : 116ms
	static int w;
	static int h;
	static int[][] map;
	static int[][] delta = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
	static int count;
	static boolean flag;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			
			if(w == 0 && h == 0) break;
				
			map = new int[w][h];
			
			for(int i = 0; i < w; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j< h; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			count = 0;
			for(int i = 0; i < w; i++) {
				for(int j = 0; j< h; j++) {
					if(map[i][j] == 1) {
						flag = false;
						dfs(i, j);
					}
				}
			}
			sb.append(count).append("\n");
			
		}
		
		System.out.println(sb);
	}

	private static void dfs(int row, int col) {
		map[row][col] = 0;
		for(int d = 0; d < 8; d++) {
			int nr = row + delta[d][0];
			int nc = col + delta[d][1];
			
			if(nr >= 0 && nr < w && nc >= 0 && nc < h && map[nr][nc] == 1)
				dfs(nr, nc);
		}
		
		if(flag) return;
		
		flag = true;
		count += 1;
		return ;
	}

}
