import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int n, min, max;
	static int Init = 1_000_000_001;
	static int[] elements,minTree, maxTree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		//수들 입력 받기
		elements = new int[n+1];
		for(int i=1; i<n+1; i++) {
			elements[i] = Integer.parseInt(br.readLine());
		}
		
		//트리 사이즈 체크
		int size = getTreeSize();
		
		//최대 트리 최소 트리 각각 하나씩 만들어 뽑음
		minTree =new int[size];
		maxTree =new int[size];
		init(0, minTree, 1,n,1);
		init(1, maxTree, 1,n,1);
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			min = Init; max =-1;
			query(0, minTree, 1,n,1,a,b);
			query(1,maxTree, 1,n,1,a,b);
			sb.append(min+" "+max+"\n");
		}
		System.out.println(sb.toString());
	}
	
	// 1-1. 세그먼트 트리 사이즈 구하기
	static int getTreeSize() {
		//세그먼트 트리는 이진 트리니 높이는 나누고 올림한것에 +1
		int h = (int)Math.ceil(Math.log(n)/Math.log(2)) +1;
		
		//높이를 이용해 거듭제곱
		return (int)Math.pow(2, h);
	}
	
	// 1-2. 세그먼트 트리 생성
	// type 0: 최소, type 1: 최대
	static void init(int type, int[] tree, int start, int end, int node) {
		
		//시작이랑 끝이 같으면 그냥 집어넣기
		if(start == end) {
			tree[node] = elements[start];
		}else {
			//다르다면 중앙값 기준으로 2개 만들기
			int mid = (start+end)/2;
			init(type, tree, start, mid, node*2);
			init(type, tree, mid+1, end, node*2+1);
			
			if(type ==0) {
				//0이면 최소(내림차순)이기에 현제 값을 작은 값으로 넣기
				if(tree[node*2] < tree[node*2+1]) {
					tree[node] = tree[node*2];
				}else {
					tree[node] = tree[node*2+1];
				}
			}else {
				//1이면 최대이니 위랑 반대
				if(tree[node*2] > tree[node*2+1]) {
					tree[node] = tree[node*2];
				}else {
					tree[node] = tree[node*2+1];
				}
			}
		}
	}
	
	// 2. 구간 [l,r] 최대 최소 구하기
	// type 0: 최소, type 1: 최대
	static void query(int type, int[] tree, int start, int end, int node, int l, int r) {
		if(l > end || r < start) return;
		if(l <= start && end <= r) {
			if(type==0) {
				min = Math.min(min, tree[node]);
			}else {
				max = Math.max(max, tree[node]);
			}
			return;
		}

		int mid = (start+end)/2;
		query(type, tree, start, mid, node*2, l ,r);
		query(type, tree, mid+1, end, node*2+1, l ,r);
	}
}