package Programmers;

/**
 * 프로그래머스 동영상 재생기
 *
 * 조건
 * 동영상 길이 : video_len (00:00 ~ 59:59)
 * 현재 위치 : pos
 * 시작 위치 : 00:00
 * 오프닝 시작 시간 : op_start
 * 오프닝 종료 시작 : op_end
 * 사용자 입력 : command (1 ~ 100)
 * 종류
 * 10초 전 : prev
 * 10초 후 : next
 * 오프닝 건너뛰기 : 현재 재생 위치가 오프닝 구간인 경우, 자동으로 끝 위치로 이동
 *
 * 문제에서 구하고자 하는 것
 * 커멘드 입력 완료 후 위치
 *
 * 문제 해결 프로세스
 * 구현
 * 문제 조건에 따라 수행하기
 * 오프닝인 경우를 항상 확인하자
 *
 * 고려한 시간 복잡도
 * 100
 */

public class ProVideoPlayer {
    public static void main(String[] args) {
        String answer = solution("34:33", "13:00", "00:55", "02:55", new String[]{"next", "prev"});
        System.out.println(answer);
    }

    public static String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        int endMin = Integer.parseInt(video_len.substring(0, video_len.indexOf(":")));
        int endSec = Integer.parseInt(video_len.substring(video_len.indexOf(":") + 1));
        int opMin = Integer.parseInt(op_start.substring(0, op_start.indexOf(":")));
        int opSec = Integer.parseInt(op_start.substring(op_start.indexOf(":") + 1));
        int opEndMin = Integer.parseInt(op_end.substring(0, op_end.indexOf(":")));
        int opEndSec = Integer.parseInt(op_end.substring(op_end.indexOf(":") + 1));


        for(int i = 0; i <= commands.length; i++) {
            int startMin = Integer.parseInt(pos.substring(0, pos.indexOf(":")));
            int startSec = Integer.parseInt(pos.substring(pos.indexOf(":") + 1));

            // 오프닝 건너뛰기
            if(startMin >= opMin && startMin <= opEndMin) {
                if(startMin > opMin && startMin < opEndMin)  {
                    startMin = opEndMin;
                    startSec = opEndSec;
                    pos = op_end;
                }
                else if(startMin == opMin && startMin == opEndMin) {
                    if(startSec >= opSec && startSec < opEndSec) {
                        startMin = opEndMin;
                        startSec = opEndSec;
                        pos = op_end;
                    }
                }
                else if(startMin == opMin) {
                    if(startSec >= opSec) {
                        startMin = opEndMin;
                        startSec = opEndSec;
                        pos = op_end;
                    }
                }
                else if(startMin == opEndMin) {
                    if(startSec < opEndSec) {
                        startMin = opEndMin;
                        startSec = opEndSec;
                        pos = op_end;
                    }
                }
            }

            if(i == commands.length) break;

            String command = commands[i];

            if(command.equals("prev")) {
                if(startMin == 0 && startSec <= 10) {
                    pos = "00:00";
                }
                else {
                    startSec -= 10;

                    if(startSec < 0) {
                        startSec = 60 + startSec;
                        startMin -= 1;
                    }

                    pos = (startMin >= 10 ? startMin : "0"+startMin)+ ":"+(startSec >= 10 ? startSec : "0"+startSec);
                }
            }
            else {
                if(startMin == endMin && endSec - startSec <= 10) {
                    pos = video_len;
                }
                else if((startMin == endMin -1) && (startSec - 50 >= endSec)) {
                    pos = video_len;
                }
                else {
                    startSec += 10;

                    if(startSec >= 60) {
                        startSec = startSec - 60;
                        startMin += 1;
                    }

                    pos = (startMin >= 10 ? startMin : "0"+startMin)+ ":"+(startSec >= 10 ? startSec : "0"+startSec);
                }
            }
        }
        answer = pos;
        return answer;
    }
}
