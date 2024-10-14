import java.io.*;
import java.util.*;
public class Main {
    static int n,m,h;
    static int map[][];
    static int min = 4;
    static boolean end = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String [] t = br.readLine().split(" ");
        n = Integer.parseInt(t[0]);
        m = Integer.parseInt(t[1]);
        h = Integer.parseInt(t[2]);
        
        map = new int[h+2][n+2];
        
        for(int i=0; i<m; i++) {
            String [] tt = br.readLine().split(" ");
            int a = Integer.parseInt(tt[0]);
            int b = Integer.parseInt(tt[1]);
            map[a][b]=1;
        }
        
        for(int i=0; i<=3; i++) {
            dfs(0,i,1);
        }
        System.out.println(-1);
    }
    
    //완전탐색 최대 NUM(3)까지만 계산하도록 제한
    public static void dfs(int level, int num, int col) {
        if(level>num || level >min) {
            return ;
        }
        if(isPossible()) {
            min = Math.min(min,level);
            System.out.println(min);
            System.exit(0);
            return ;
        }
        
        //모든 높이에서 n만큼 놓은 것을 완전 탐색
        for(int i=col; i<=h; i++) {
            for(int j=1; j<=n; j++) {
            	//앞이랑 뒤랑 전부 연결이 안되있을 경우
                if(map[i][j]==0 && map[i][j-1]==0 && map[i][j+1]==0) {
                	//현재 위치를 연결해줌
                    map[i][j]=1;
                    //깊이 추가
                    dfs(level+1,num,i);
                    //탐색끝났으면 다음 탐색을 위해 초기화
                    map[i][j]=0;
                }
            }
        }
    }
    
    //사용 가능한지 확인
    public static boolean isPossible() {
    	
    	//모든 세로선 체크
        for(int i=1; i<=n; i++) {
            int line = i;
            int col = 1;
            while(true) {
            	//모든 연결선 통과했으면 현재 위치가 마지막 위치임
                if(col == h+1) {
                    break;
                }
                //오른쪽이랑 연결이 되어 있다면 오른쪽 세로칸으로 이동
                if(map[col][line]==1) { 
                    line+=1;
                }
                //왼쪽이랑 연결되 있다면 왼쪽 세로칸으로 이동
                else if(map[col][line-1] ==1) {
                    line -=1;
                }
                
                // 다음 가로선으로 이동
                col++;
            }
            
            
            //현재 위치가 처음위치랑 같은지 확인
            //즉 통과인지 확인
            if(line != i) {
                return false;
            }
        }
        return true;
    }
}