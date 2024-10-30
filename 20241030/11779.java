import java.io.*;
import java.util.*;

public class Main {
	public static class Node{
		int y,score;
		
		public Node(int y, int score) {
			this.y = y;
			this.score =score;
		}
	}
	
	public static class pNode implements Comparable<pNode>{
		int index,score;
		
		public pNode(int index, int score) {
			this.index = index;
			this.score =score;
		}

		@Override
		public int compareTo(Main.pNode o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.score, o.score);
		}
		
	}
	
	public static int n,m;
	
	public static ArrayList<Node>[] arr;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//도시 입력
		n = Integer.parseInt(br.readLine());
		
		//버스 입력
		m = Integer.parseInt(br.readLine());
		
		arr = new ArrayList[n+1];
		
		for(int i =0; i<=n; i++) {
			arr[i] = new ArrayList<Node>();
		}

		for(int i =0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int score = Integer.parseInt(st.nextToken());
			
			//버스추가
			arr[x].add(new Node(y, score));
			
		}
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		Dijkstra(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
	}
	
	public static void Dijkstra(int strat, int end) {
		//다익스트라를 통해 구하는데 
		//작은거 찾을 때마다 리스트 업데이트 
		PriorityQueue<pNode> pq = new PriorityQueue<pNode>();
		
		int[] city = new int[n+1];
		boolean[] visit = new boolean[n+1];
		int[] route = new int[n+1];
		Arrays.fill(city, Integer.MAX_VALUE);
		
		city[strat] = 0;
		route[strat] = 0;
		pq.add(new pNode(strat, 0));
		
		
		while(!pq.isEmpty()) {
			int nowVertex = pq.poll().index;
			
			if(visit[nowVertex]) {
				continue;
			} 
			
			
			visit[nowVertex] = true;
			
			//index의 연결된 정점 비교
			for(Node next : arr[nowVertex]) {
				if(city[next.y] > city[nowVertex]+ next.score) {
					city[next.y] = city[nowVertex]+next.score;
					pq.offer(new pNode(next.y, city[next.y]));
					route[next.y] = nowVertex;
				}
			}
		}
		
		//최단 거리 체크
		System.out.println(city[end]);
		
		//역추적으로 루트 찾기
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
        while(end != 0) {
            stack.push(end);
            end = route[end];
        }

        System.out.println(stack.size());
        while(!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb.toString());
	}
	
	
}
