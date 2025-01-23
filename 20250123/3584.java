import java.util.Scanner;


public class Main {
	static int N;
	static int[] p;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for(int T=1;T<=t;T++) {
			N = sc.nextInt();//노드의 수 
			
			
			//N-1개의 노드로 tree를 만든다.
			p = new int[N+1]; //부모를 저장하는 배열
			for(int i=1;i<=N-1;i++) {
				int pa = sc.nextInt(); //부모
				int c = sc.nextInt(); // 자식
				
				p[c]=pa;
			}
			
			//두 노드를 받아 공통 부모를 갖는 이를 찾는다.
			int a_node = sc.nextInt();
			int b_node = sc.nextInt();
			

			//1. a_node의 전체 부모를 구해라 
			//a_parent_list에는 인덱스가 작을수록 a_node와 가까운 부모
			visited = new boolean[N+1];
			total_parent(a_node);
			
			
			//2. b_node의 부모를 추적하면서 같은 수가 있는지 확인 > 결과 출력 
			System.out.println(same_parent(b_node));

		}//test end
	}
	
	//전체 부모를 구해라
	public static void total_parent(int a_node) {

		int c = a_node;
		while(c!=0) {
			//부모는 방문 처리하고 해당 부모의 부모를 찾으러 올라간다.
			visited[c] = true;
			c=p[c];
		}
	}
	
	//같은 부모가 있는지 확인
	public static int same_parent(int b_node) {
		
		int c = b_node;
		int result = 0;
		
		//c를 루트가 나올때 까지 반복하면서
		while(c!=0) {
			//동일한 조상이 나오면 리턴
			if(visited[c]) {
				result = c;
				break;
			}
			//아니라면 해당 부모의 부모를 찾음
			c = p[c];
		}
		
		return result;
		
	}
}
