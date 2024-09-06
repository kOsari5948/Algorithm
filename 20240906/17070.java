import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	
	public static int n,count;
	public static int[][] arr;
	public static int[] movex = {0,1,1};
	public static int[] movey = {1,0,1};
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	
    	arr = new int [n][n];
    	
    	
    	for(int i =0; i<n; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		for(int j=0; j<n; j++) {
    			arr[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	count = 0;
    	dfs(0,1,0);
    	System.out.println(count);
    }
    
    public static void dfs(int x2,int y2, int rot) {
    	if((x2==n-1&&y2==n-1)) {
    		count++;
    		return;
    	}
    	//좌우옆 이렇게 3개 이동
    	//단 이동중 45도 이상 넘어가면 안됨 즉 현재 각도 확인 필요
    	//rot == 0 이면 가로  이면 가로 대각선으로만 움직임 가능 
    	//rot == 1 이면 세로  이면 세로 대각선으로만 움직임 가능
    	//rot == 2 이면 대각선 이면 가로세로 대각선 움직임 가능
    	
    	// 즉 0= 0,2 / 1 = 1,2 / 2 = 0,1,2 
    	// 이동 가능
    	
    	switch (rot) {
		case 0: {
			//0,2
			int mx = x2+movex[0];
			int my = y2+movey[0];
			if(my>=0&&my<n&&mx>=0&&mx<n&&arr[mx][my]==0) {
				dfs(mx,my,0);
			}
			
			
			mx = x2+movex[2];
			my = y2+movey[2];
			if(my>=0&&my<n&&mx>=0&&mx<n&&arr[mx][my]==0&&arr[mx][y2]==0&&arr[x2][my]==0) {
				dfs(mx,my,2);
			}
			
			
			break;
		}
		case 1: {
			//1,2
			
			int mx = x2+movex[1];
			int my = y2+movey[1];
			if(my>=0&&my<n&&mx>=0&&mx<n&&arr[mx][my]==0) {
				dfs(mx,my,1);
			}
			
			
			mx = x2+movex[2];
			my = y2+movey[2];
			if(my>=0&&my<n&&mx>=0&&mx<n&&arr[mx][my]==0&&arr[mx][y2]==0&&arr[x2][my]==0) {
				dfs(mx,my,2);
			}
			break;
		}
		case 2: {
			//0,1,2
			int mx = x2+movex[0];
			int my = y2+movey[0];
			if(my>=0&&my<n&&mx>=0&&mx<n&&arr[mx][my]==0) {
				dfs(mx,my,0);
			}
			
			mx = x2+movex[1];
			my = y2+movey[1];
			if(my>=0&&my<n&&mx>=0&&mx<n&&arr[mx][my]==0) {
				dfs(mx,my,1);
			}
			
			mx = x2+movex[2];
			my = y2+movey[2];
			if(my>=0&&my<n&&mx>=0&&mx<n&&arr[mx][my]==0&&arr[mx][y2]==0&&arr[x2][my]==0) {
				dfs(mx,my,2);
			}
			break;
		}
		default:
		}
    }
}