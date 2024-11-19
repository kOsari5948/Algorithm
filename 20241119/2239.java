import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static class Node{
		int x,y;
		
		public Node(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
    static final int N = 9;
    static int[][] map;
    public static ArrayList<Node> arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[N][N];
        arr = new ArrayList<Main.Node>();
        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<N; j++) {
            	//char는 숫자로 저장됨 즉 1~9 까지의 아스키 와 0의 아스키 코드 숫자를 빼야 진짜 숫자가 나옴
                map[i][j] = str.charAt(j)-'0';
                if(map[i][j]==0) {
                	arr.add(new Node(i, j));
                }
            }
            
        }

        playSdoku(0);
    }

    private static void playSdoku(int count) {

        if(count == arr.size()) {
            printSdoku();
            System.exit(0);
        }
        
        //1 부터 시작해서 9 까지 숫자 넣기
        for(int i=1; i<=9; i++) {
        	// 넣을 수 있는 숫자면 넣어서 다음 스도쿠 진행
            if(isValidPosition(arr.get(count), i)) {
                map[arr.get(count).x][arr.get(count).y] = i;
                playSdoku(count+1);
                //만약 어림 없다면 뒤로 돌아서 반복
                map[arr.get(count).x][arr.get(count).y] = 0;
            }
        }
    }


    private static boolean isValidPosition(Node idx, int num) {
        int r = idx.x;
        int c = idx.y;
        // 가로 || 세로 체크
        // 가로세로 에서 현재 들어갈려는 숫자와 동일하다면 실패
        for(int i=0; i<N; i++) {
            if(map[r][i] == num || map[i][c] == num){
            	return false;
            }
        }
        //9개의 블록 중에 어디에 속해 있냐
        int sr = (r/3)*3;
        int sc = (c/3)*3;

        //속해 있는 블록에서 같은 숫자 있는지 찾기 
        for(int i=sr; i<sr+3; i++) {
            for(int j=sc; j<sc+3; j++) {
                if(map[i][j] == num) return false;
            }
        }
        
        //여기 까지 없다면 넣을 수 있는 숫자
        return true;
    }

    private static void printSdoku() {
        StringBuffer ans = new StringBuffer();
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                ans.append(map[i][j]);
            }
            ans.append("\n");
        }
        System.out.print(ans.toString());
    }
}