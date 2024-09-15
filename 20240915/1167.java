import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	 static ArrayList<Node>[] arr;
	 static boolean[] visited;
	 static int max = 0;
	 static int node;
	
	 public static class Node {
		 int end;
		 int cost;
		 
		 public Node(int end, int cost) {
			 this.end = end;
			 this.cost = cost;
		 }
	 }
	 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		arr = new ArrayList[n+1];
		for(int i = 1; i < n + 1; i++) {
            arr[i] = new ArrayList<Node>();
        }
		
		for(int i = 0; i < n; i++) {
            int s = sc.nextInt();
            while(true) {
                int e = sc.nextInt();
                if(e == -1) break;
                int cost = sc.nextInt();
                arr[s].add(new Node(e, cost));
            }
        }
		visited = new boolean[n + 1];
		//1부터 시작해서 구하기
		dfs(1, 0);
		
		visited = new boolean[n + 1];
		
		//선택된 node로 부터 구하기
        dfs(node, 0);
        
        System.out.println(max);
		
	}
	
	
	public static void dfs(int x, int len) {
		//가장큰 길이의 node를 구하기
        if(len > max) {
            max = len;
            node = x;
        }
        visited[x] = true;
        
        for(int i = 0; i < arr[x].size(); i++) {
            Node n = arr[x].get(i);
            if(visited[n.end] == false) {
                dfs(n.end, n.cost + len);
                visited[n.end] = true;
            }
        }
        
    }
}