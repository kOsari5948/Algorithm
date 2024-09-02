import java.io.*;
import java.util.*;

public class Main {
	public static class Node{
		int y;
		int len;
		
		public Node(int y, int len) {
			this.y =y;
			this.len =len;
		}
	}
	
	public static int max,n,max_index;
	public static boolean[] visited;
	public static ArrayList<Node>[] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		arr = new ArrayList[n+1];
		for(int i =0; i<=n; i++) {
			arr[i] = new ArrayList<Node>();
		}
		
		for(int i =0; i<n-1; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int s = sc.nextInt();
			
			
			arr[x].add(new Node(y, s));
			arr[y].add(new Node(x, s));
		}
		
		max = -1;

	//귀류 법 참조 https://blog.naver.com/adamdoha/222121145206
	visited = new boolean[n+1];
        visited[1] = true;
        dfs(1,0);
        
        
        visited = new boolean[n+1];
        visited[max_index] = true;
        dfs(max_index,0);
        System.out.println(max);
		
		
	}
	
	public static void dfs(int node, int sum) {
		visited[node]=true;
		
		if(max<sum) {
			max = sum;
			max_index = node;
		}
		
		for(Node temp: arr[node]) {
			int next = temp.y;
			int cost = temp.len;
			if(!visited[next]){
				dfs(next,sum+cost);
			}
		}
	}
}
