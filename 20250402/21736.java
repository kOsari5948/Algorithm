import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static class Node{
		int x;
		int y;
		
		public Node(int x , int y) {
			this.x =x;
			this.y= y;
		}
	}
	
	public static int [] movex = {0,0,1,-1};
	public static int [] movey = {1,-1,0,0};
	public static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		
		int i_x = 0;
		int i_y = 0;
		
		ArrayList<Node> p = new ArrayList<>();
		
		for(int i =0; i<n; i++) {
			String str = br.readLine();
			for(int j =0 ;j <m; j++) {
				if(str.charAt(j) == 'X') {
					map[i][j] = -1;
				}
				
				if(str.charAt(j) == 'P') {
					map[i][j] = 1;
					p.add(new Node(i, j));
				}
				
				if(str.charAt(j) == 'I') {
					map[i][j] = 9;
					i_x = i;
					i_y = j;
				}
			}
		}
		int count = 0;
		Queue<Node> q = new LinkedList<Main.Node>();
		int[][] visited = new int[n][m];
		visited[i_x][i_y] = 1;
		q.add(new Node(i_x,i_y));
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			if(map[node.x][node.y] == 1) {
				count++;
			}
			
			for(int i =0; i<4; i++) {
				int mx = node.x+movex[i];
				int my = node.y+movey[i];
				
				if(mx>=0&&mx<n&&my>=0&&my<m) {
					if(map[mx][my] != -1&&visited[mx][my] == 0) {
						q.add(new Node(mx,my));
						visited[mx][my] = 1;
					}
				}
			}
		}		
		
		if(count==0) {
			System.out.println("TT");
		}else {
			System.out.println(count);
		}
	}
	

}
