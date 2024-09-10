import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static int n, answer, map[][];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        answer = 0;
        map = new int[n][n];
        StringTokenizer stz;
        for(int i = 0; i < n; i++) {
            stz = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(stz.nextToken());
        }
        
        game(0);
        System.out.println(answer);
    }
    
    public static void game(int count) {
        //5번 움직이면 체크 하고 끝
    	if(count == 5) {
            findMax();
            return;
        }
        int copy[][] = new int[n][n];
        //원본 복사
        for(int i = 0; i < n; i++) {
            copy[i] = map[i].clone();
        }
        
        //상 하 좌우 움직이기
        for(int i = 0; i < 4; i++) {
            move(i);
            game(count+1);
            //게임 한판 끝나면 이동 원상 복구
            for(int a = 0; a < n; a++) {
                map[a] = copy[a].clone();
            }
        }
    }
    
    public static void move(int dir) {
        switch(dir) {
            //위로 몰기
            case 0:
                for(int i = 0; i < n; i++) {
                    int index = 0;
                    int block = 0;
                    for(int j = 0; j < n; j++) {
                    	//숫자가 있는 칸이라면
                        if(map[j][i] != 0) {
                        	//동일한 값이면 즉 이미 올린 값이랑 같은 값이라면 합체
                            if(block == map[j][i]) {
                            	map[index - 1][i] = block * 2;
                            	//올렸으면 다시 초기화
                            	//현재 위치 0
                                block = 0;
                                map[j][i] = 0;
                            }
                            else {
                            	//다르다면 즉 만나지 못한다면 현재 위치 0으로 하고 위로 올려 버리기
                            	//위로 올려야 해서 index는 x값만 바꿈
                                block = map[j][i];
                                map[j][i] = 0;
                                map[index][i] = block;
                                index++;
                            }
                        }
                    }
                }
                break;
            //아래로 몰기 위랑 동일하지만 아래이기 때문에 index는 마지막 부터 시작
            case 1:
                for(int i = 0; i < n; i++) {
                    int index = n - 1;
                    int block = 0;
                    for(int j = n - 1; j >= 0; j--) {
                        if(map[j][i] != 0) {
                            if(block == map[j][i]) {
                                map[index + 1][i] = block * 2;
                                block = 0;
                                map[j][i] = 0;
                            }
                            else {
                                block = map[j][i];
                                map[j][i] = 0;
                                map[index][i] = block;
                                index--;
                            }
                        }
                    }
                }
                break;
            //왼쪽으로 몰기 이것도 동일하지만 좌우이기에 index는 y만 변경
            case 2:
                for(int i = 0; i < n; i++) {
                    int index = 0;
                    int block = 0;
                    for(int j = 0; j < n; j++) {
                        if(map[i][j] != 0) {
                            if(block == map[i][j]) {
                                map[i][index - 1] = block * 2;
                                block = 0;
                                map[i][j] = 0;
                            }
                            else {
                                block = map[i][j];
                                map[i][j] = 0;
                                map[i][index] = block;
                                index++;
                            }
                        }
                    }
                }
                break;
            //오른쪽으로 몰기 왼쪽이랑 동일하지만 오른쪽으로 밀기때문에 index 마지막 부터 시작
            case 3:
                for(int i = 0; i < n; i++) {
                    int index = n - 1;
                    int block = 0;
                    for(int j = n - 1; j >= 0; j--) {
                        if(map[i][j] != 0) {
                            if(block == map[i][j]) {
                                map[i][index + 1] = block * 2;
                                block = 0;
                                map[i][j] = 0;
                            }
                            else {
                                block = map[i][j];
                                map[i][j] = 0;
                                map[i][index] = block;
                                index--;
                            }
                        }
                    }
                }
                break;
        }
    }
    
    public static void findMax() {
    	//전부 다 돌면서 최대값 answer에 저장
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                answer = Math.max(answer, map[i][j]);
    }
}