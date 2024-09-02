import java.io.*;
import java.util.*;

public class Main {
	public static class Node implements Comparable<Node>{
		int y, money;
		
		public Node(int y, int money) {
			this.y=y;
			this.money = money;
		}
		
		 @Override
		    public int compareTo(Node a){
		        return money - a.money;
		    }
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		List<Node>[] net = new LinkedList[n+1];
		boolean[] visited = new boolean[n+1];
		for(int i =0; i<n+1;i++) {
			net[i] = new LinkedList<Main.Node>();
		}
		
		for(int i =0; i<m; i++) {
			int x = sc.nextInt();
			int y= sc.nextInt();
			int money = sc.nextInt();
			
			net[x].add( new Node(y, money));
			
			net[y].add( new Node(x, money));
			
		}
		
		Queue<Node> q = new PriorityQueue<>();
		
		//1부터 체크
		for(Node i : net[1]) {
			q.add(i);
		}
		visited[1] = true;
		
		int sum =0;
		
		
		  while(!q.isEmpty()){
	           Node a = q.poll();

	           if(visited[a.y])
	               continue;
	           visited[a.y] = true;
	           sum += a.money;
	           for(Node i :net[a.y]) {
	        	   q.add(i);
	           }
	       }
		  
		 System.out.println(sum);
	}
	
	
	
}