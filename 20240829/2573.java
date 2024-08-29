import java.io.*;
import java.util.*;

public class Main {
	
	
	
	
	public static class Node{
		int x,y,h;
		
		public Node(int x, int y, int h) {
			this.x =x;
			this.y =y;
			this.h =h;
		}
	}
	
	public static int[][] map;
	public static int n,m,time;
	public static int[][] arr,visited;
	public static ArrayList<Node> ice;
	public static int[] movex= {0,0,1,-1};
	public static int[] movey= {1,-1,0,0};
	public static Queue<Node> q,tq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		
		ice = new ArrayList<Main.Node>();
		
		for(int i =0;i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] != 0) {
					ice.add(new Node(i, j, arr[i][j]));
				}
			}
		}
		
		time = 0;
		
		//1. 몇개의 묶음인지 확인
		//1-1.만약 묶음이 2개 이상이면 종료
		//1-22개 이하라도 ice의 size가 0이면 중지
		
		//2. 둘러쌓인 0 수 만큼 숫지 줄이기 
		//2-1. 줄인것의 값이 0이면 ice에서 삭제
		
		//3 1번반복
		
		q = new LinkedList<Main.Node>();
		tq = new LinkedList<Main.Node>();
		
		for(Node i: ice) {
			q.add(i);
		}
		
		
		
		while(true) {
			if(check()>=2) {
				break;
			}
			if(check()<=0) {
				time = -1;
				break;
			}
			
			melt();
			time++;
			for(Node i : tq) {
				q.add(i);
			}
		}
		
		if(time == -1) {
			System.out.println(0);
		}else {
			System.out.println(time);
		}
	}
	
	public static void melt() {
		tq = new LinkedList<Main.Node>();
		Queue<Node> qq = new LinkedList<Main.Node>();
		while(!q.isEmpty()) {
			int tt=0;
			Node node = q.poll();
			for(int k = 0; k<4;k++) {
				int mx = node.x+movex[k];
				int my = node.y+movey[k];
				
				if(mx>=0&&mx<n&&my>=0&&my<m) {
					if(arr[mx][my]==0) {
						tt++;
					}
				}
			}
			
			
			
			
			if(node.h-tt>0) {
				tq.add(new Node(node.x, node.y, node.h-tt));
				arr[node.x][node.y] = node.h-tt;
			}else {
				qq.add(new Node(node.x,node.y,node.h));
				arr[node.x][node.y] = -1;
			}
		}
		
		
		while(!qq.isEmpty()) {
			Node temp = qq.poll();
			
			arr[temp.x][temp.y] = 0;
		}
	}
	
	public static int check() {
		int count = 0;
		visited = new int[n][m];
		
		for(int i =0 ;i<n; i++) {
			for(int j =0; j<m; j++) {
				if(arr[i][j]!=0&&visited[i][j]==0) {
					visited[i][j] = 1;
					dfs(i,j);
					count++;
				}
			}
		}
		
		return count;
	}
	
	public static void dfs(int x, int y) {
		for(int i =0; i<4; i++) {
			int mx = x+movex[i];
			int my = y+movey[i];
			
			if(mx>=0&&mx<n&&my>=0&&my<m) {
				if(arr[mx][my]!=0&&visited[mx][my]==0) {
					visited[mx][my]++;
					dfs(mx,my);
				}
			}
		}
	}
}
