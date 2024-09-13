import java.beans.Visibility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	
    	int[] dx = {0,0,1,-1};
    	int[] dy = {1,-1,0,0};
    	
    	int[][] box = new int[n][m];
    	boolean[][] visited ;
    	int cheese = 0;
    	for(int i =0; i<n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j =0; j<m; j++) {
    			box[i][j] = Integer.parseInt(st.nextToken());
    			if(box[i][j] == 1) {
    				cheese++;
    			}
    		}
    	}
    	
    	int CCount = 0 ;
    	int time = 0;
    	
    	while(cheese!=0) {
    		CCount = cheese;
    		time++;
    		visited = new boolean[n][m];
    		Queue<int[]>q = new LinkedList<int[]>();
    		
    		q.offer(new int[] {0,0});
    		visited[0][0] = true;
    		
    		while(!q.isEmpty()) {
                int[] current = q.poll();
                
                for(int i = 0; i < 4; i++) {
                    int nx = current[0] + dx[i];
                    int ny = current[1] + dy[i];
                    
                    if(nx >= 0 && ny >= 0 && nx < n && ny < m && visited[nx][ny] == false) {
                        visited[nx][ny] = true;
                        if(box[nx][ny] == 0) {
                            q.offer(new int[] {nx, ny});
                        } else {
                            cheese--;
                            box[nx][ny] = 0;
                        }
                    }
                }
            }
    	}
    	 System.out.println(time);
         System.out.println(CCount);
    	
    }
}