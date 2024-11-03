import java.io.*;
import java.util.*;

public class Main {
	public static class Tree{
		int x,y,age;
		Boolean dead;
		public Tree(int x, int y, int age,boolean dead) {
			this.x=x;
			this.y=y;
			this.age=age;
			this.dead =dead;
		}
	}
	public static class Node{
		int x,y;
		public Node(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
	public static int[] ex = {-1,-1,-1,0,0,1,1,1};
	public static int[] ey = {-1,0,1,-1,1,-1,0,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int [][] map = new int[n][n];
		int [][] energe = new int[n][n];
		for(int i =0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = 5;
				energe[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ArrayList<Tree> treeList = new ArrayList<>();
		
		for(int i =0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x= Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			int age= Integer.parseInt(st.nextToken());
			treeList.add(new Tree(x,y,age,false));
		}
		
		for(int y=0; y<k;y++) {
			
			
			//봄
			//나무 돌면서 지력 뽑기
			//지력 못 뽑고 죽은것들 저장
			ArrayList<Integer>deleteIndex = new ArrayList<>();
			//트리 나이별로 정렬
			Collections.sort(treeList,new Comparator<Tree>() {
				public int compare(Tree o1, Tree o2) {
					return o1.age-o2.age;
				};
			});
			for(int i =0; i<treeList.size();i++) {
				Tree tree = treeList.get(i);
				if(map[tree.x-1][tree.y-1]>=tree.age) {
					map[tree.x-1][tree.y-1]-=tree.age;
					treeList.set(i, new Tree(tree.x, tree.y, tree.age+1,false));
				}else {
					deleteIndex.add(i);
				}
			}
			
			//여름
			//봄에 양분 못 빤 나무들 전부 죽이기
			ArrayList<Tree>deleteTree = new ArrayList<>();
			for(int t = deleteIndex.size()-1; t>=0;t--) {
				int i = deleteIndex.get(t);
				Tree tree = treeList.get(i);
				map[tree.x-1][tree.y-1]+=(tree.age/2);
				tree.dead = true;
			}
			
			
			//가을
			//나무 번식
			int size = treeList.size();
			for(int t =0; t<size;t++) {
				Tree tree = treeList.get(t);
				if(!tree.dead) {
					if(tree.age%5==0) {
						for(int i=0; i<8;i++) {
							int mx = tree.x+ex[i]-1;
							int my = tree.y+ey[i]-1;
							
							if(mx>=0&&mx<n&&my>=0&&my<n) {
								treeList.add(new Tree(mx+1, my+1, 1,false));
							}
						}
					}
				}
			}
			
			
			//겨울
			//영양소 추가
			for(int i =0; i<n; i++) {
				for(int j=0;j<n;j++) {
					map[i][j]+=energe[i][j];
				}
			}
			
			//트리 바꿔치기
			ArrayList<Tree> newTreeList = new ArrayList<>();
			for(Tree tree : treeList) {
				if(!tree.dead) {
					newTreeList.add(tree);
				}
			}
			
			treeList = newTreeList;
			
			
		}
		
		System.out.println(treeList.size());
		
		
	}
}