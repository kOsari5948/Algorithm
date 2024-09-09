import java.io.*;
import java.util.*;

public class Main {
	public static int n;
	public static int[][] map;
	public static int[] count;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		
		for(int i =0 ; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j =0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		count=new int[2];
		dfs(0,0,n-1,n-1);
		System.out.println(count[0]);
		System.out.println(count[1]);
	}	
	
	public static void dfs(int x1, int y1, int x2, int y2) {
		//처음 xy 마지막 xy
		//이게 전부 일치하는지 확인
		//전부 일치하지 않는다면 x1+x2/2 y1+y2/2
		//해서 다시 구함 만약 모두 일치하면 or 1개라면  +1 한뒤에 리턴
		
		if(x1==x2&&y1==y2) {
			count[map[x1][y1]]++;
			return;
		}
		//모두 일치한다면
		int temp = map[x1][y1];
		boolean flag = false;
		for(int i=x1; i<=x2; i++) {
			for(int j=y1;j<=y2;j++) {
				if(temp!=map[i][j]) {
					flag = true;
					break;
				}
			}
			
			if(flag) {
				break;
			}
		}
		//추가
		if(!flag) {
			count[map[x1][y1]]++;
			return;
		}
		
		//일치 하지 않는다면 4개로 나누기
		int midx = (x1+x2)/2;
		int midy = (y1+y2)/2;
		
		//그후 4개를 각각 진행
		dfs(x1,y1,midx,midy);
		dfs(x1,midy+1,midx,y2);
		dfs(midx+1,y1,x2,midy);
		dfs(midx+1,midy+1,x2,y2);
		
		
	}
}