import java.io.*;
import java.util.*;

public class Main {
	public static class Node{
		int x,y,num;
		
		public Node(int x, int y, int num) {
			this.x =x;
			this.y =y;
			this.num = num;
		}
	}
	
	public static int[][] movex= {{},{0,1,2,3},{0,1},{0,1,2,3},{0,1,2,3},{0}};
	public static int[][] arr,tmp;
	public static int n,m,count;
	public static ArrayList<Node> cctv;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		arr = new int[n][m];
		cctv = new ArrayList<Node>();
		
		
		for(int i =0; i<n; i++) {
			for(int j =0; j<m; j++) {
				arr[i][j] = sc.nextInt();
				if(arr[i][j]!=0&&arr[i][j]!=6) {
					cctv.add(new Node(i, j, arr[i][j]));
				}
			}
		}
		
		
		count = Integer.MAX_VALUE;
		int [] visited = new int[cctv.size()];
		dfs(0,visited);
		
		
		System.out.println(count);
	}
	
	public static void dfs(int c, int[] visited) {
		if(c == cctv.size() ) {
			count = Math.min(count, check(visited));
			return;
		}
		
		int kind = cctv.get(c).num;
		
		for(int i =0 ; i<movex[kind].length;i++) {
			visited[c] = i;
			dfs(c+1,visited);
		}
	}
	
	public static int check(int[] visited) {
		int count = 0;
		
		tmp = new int[n][m];
		for(int f =0; f<n; f++) {
			tmp[f] = arr[f].clone();
		}
		
		
		for(int i =0; i<cctv.size(); i++) {
			int loc = visited[i];
			Node t = cctv.get(i);
			
			if(t.num == 1) {
				if(loc == 0) {
					fillright(t.x, t.y);
				}else if(loc ==1) {
					filldown(t.x, t.y);
				}else if(loc ==2) {
					fillleft(t.x, t.y);
				}else if(loc ==3) {
					fillup(t.x, t.y);
				}
			}
			
			if(t.num == 2) {
				if(loc == 0) {
					fillright(t.x, t.y);
					fillleft(t.x, t.y);
				}else if(loc ==1) {
					filldown(t.x, t.y);
					fillup(t.x, t.y);
				}
			}
			
			if(t.num == 3) {
				if(loc == 0) {
					fillup(t.x, t.y);
					fillright(t.x, t.y);
				}else if(loc ==1) {
					fillright(t.x, t.y);
					filldown(t.x, t.y);
				}else if(loc ==2) {
					filldown(t.x, t.y);
					fillleft(t.x, t.y);
				}else if(loc ==3) {
					fillup(t.x, t.y);
					fillleft(t.x, t.y);
				}
			}
			
			if(t.num == 4) {
				if(loc == 0) {
					fillup(t.x, t.y);
					fillright(t.x, t.y);
					fillleft(t.x, t.y);
				}else if(loc ==1) {
					fillup(t.x, t.y);
					fillright(t.x, t.y);
					filldown(t.x, t.y);
				}else if(loc ==2) {
					filldown(t.x, t.y);
					fillleft(t.x, t.y);
					fillright(t.x, t.y);
				}else if(loc ==3) {
					fillup(t.x, t.y);
					fillleft(t.x, t.y);
					filldown(t.x, t.y);
				}
			}
			
			if(t.num == 5) {
					fillup(t.x, t.y);
					fillright(t.x, t.y);
					fillleft(t.x, t.y);
					filldown(t.x, t.y);
			}
			
			
		}
		
		for(int i =0; i<n; i++) {
			for(int j =0; j<m; j++) {
				if(tmp[i][j] == 0) {
					count++;
				}
			}
		}
		
		return count;
	}
	
	public static void fillleft(int x, int y) {
		for(int i = y-1; i>=0; i-- ) {
			if(arr[x][i] == 6) {
				break;
			}
			
			if(arr[x][i]==0) {
				tmp[x][i] = -1;
			}
		}
	}
	public static void fillright(int x, int y) {
		
		for(int i = y+1; i<m; i++ ) {
			if(arr[x][i] == 6) {
				break;
			}
			
			if(arr[x][i]==0) {
				tmp[x][i] = -1;
			}
		}
		
	}
	public static void fillup(int x, int y) {
		
		for(int i = x-1; i>=0; i-- ) {
			if(arr[i][y] == 6) {
				break;
			}
			
			if(arr[i][y]==0) {
				tmp[i][y] = -1;
			}
		}
	
	}
	public static void filldown(int x, int y) {
	
		for(int i = x+1; i<n; i++ ) {
			if(arr[i][y] == 6) {
				break;
			}
			
			if(arr[i][y]==0) {
				tmp[i][y] = -1;
			}
		}
	}
}