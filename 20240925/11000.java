import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static class Node{
		int x,y;
		
		public Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		ArrayList<Node> arr = new ArrayList<Main.Node>();
		
		for(int i =0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			arr.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(arr, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if(o1.x==o2.x) {
					return o1.y -o2.y;
				}
				return o1.x-o2.x;
			}
		});
		
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(arr.get(0).y);
		
		 for (int t =1; t<n;t++) {
			 Node i = arr.get(t);
	            // 우선순위 큐에서 가장 작은 종료 시간과
	            // 현재 lectures[i]의 시작 시간을 비교한다.
	            if (pq.peek() <= i.x) {
	                pq.poll();
	            }
	            pq.offer(i.y);
	        }
		
		System.out.println(pq.size());
		
		
		
	}
}