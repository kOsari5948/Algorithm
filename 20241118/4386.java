import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static class Star{
		double x,y;
		int num;
		
		public Star(double x, double y,int num) {
			this.x =x;
			this.y=y;
			this.num = num;
		}
	}
	
	public static class Edge  implements Comparable<Edge>{
		int x,y;
		double score;
		
		public Edge(int x, int y,double score) {
			this.x =x;
			this.y=y;
			this.score=score;
		}

		@Override
		public int compareTo(Main.Edge o) {
			// TODO Auto-generated method stub
			return (int) (this.score - o.score);
		}
		
		
	}
	static int[] parent;
   public static void main(String[] args) throws NumberFormatException, IOException {
	   Scanner sc = new Scanner(System.in);
	   
	   int  n = sc.nextInt();
	   
	   //모든 별 수집
	   Star[] star = new Star[n];
	   
	   for(int i =0; i<n; i++) {
		   double x = sc.nextDouble();
		   double y = sc.nextDouble();
		   
		   star[i] = new Star(x, y,i);
	   }
	   
	   //별 다 모은 뒤에 모든 별 까지의 경로 저장
	   //별은 큐에다 넣기
	   
	   PriorityQueue<Edge> q = new PriorityQueue<Edge>();
	   
	   for(int i =0 ; i<n; i++) {
		   
		   for(int j =0; j<n; j++) {
			   if(i==j) {
				   continue;
			   }
			   
			   q.add(new Edge(star[i].num, star[j].num, distance(star[i],star[j])));
			   
		   }
	   }
	   
	   //크루크칼 알고리즘 사용 
	   //순환은 유니온 파인드로 체크
	   
	   //최초 부모 초기화
	   parent = new int[n];
	   for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
	   
	   double ans = 0;
		
		// 크루스칼 알고리즘 수행.
		while(!q.isEmpty()) {
			
			Edge edge = q.poll();

			if (find(edge.x) != find(edge.y)) {
				ans += edge.score;
				union(edge.x, edge.y);
			}
		}
		
		System.out.println(String.format("%.2f", ans));
	   
   }
   public static double distance(Star p1, Star p2) {
		return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}
   
   public static int find(int x) {
		if (x == parent[x]) {
			return x;
		}

		return parent[x] = find(parent[x]);
	}

	public static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y) {
			parent[y] = x;
		}
	}
}