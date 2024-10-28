import java.io.*;
import java.util.*;
import java.math.*;


public class Main {
	public static int lie,n,m;
	public static boolean[] people;
	public static ArrayList<Integer>[] party;
	public static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());//전체 사람 수
		m = Integer.parseInt(st.nextToken());//파티 수
		
		people = new boolean[n+1];//사람들이 진실을 아는지 여부
		parent = new int[n+1];   //부모 체크
		
		
		//초기 진실을 아는 사람들 저장
		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		for(int i = 0; i<t;i++) {
			people[Integer.parseInt(st.nextToken())] = true;
		}
		
		//파티 만들기
		 party = new ArrayList[m];//파티 저장 배열 생성
		
		//파티 초기화
		for(int i =0; i<m; i++) {
			party[i] = new ArrayList<Integer>();
		}
		//파티 저장
		for(int i =0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			for(int j =0; j<size;j++) {
				party[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		//부모 세팅
		for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
		//union 체크 파티에서 첫번째 사람 잡아서 첫번째 사람 기준으로 합치기
		for (int i = 0; i < m; i++) {
            int first_man = party[i].get(0);
            for (int j = 1; j < party[i].size(); j++) {
                union(first_man, party[i].get(j));
            } // 각각의 파티에 참석한 인원들은 같은 집합으로 분류함
        }
		
		
		lie = 0;
		
		//진실을 아는 사람 체크해주기
		for(int i =0; i<=n; i++) {
			for(int j=0;j<=n;j++) {
				if(people[j]) {
					if(find(i)==find(j)) {
						people[i] = true;
						break;
					}
				}
			}
		}
		
		//현재 파티에 진실 알고있는 사람 있는지 
		//없으면 거짓말 올려줌
		for(int i =0; i<m; i++) {
			boolean flag = true;
			for(int temp : party[i]) {
				if(people[temp]) {
					flag =false;
					break;
				}
			}
			if(flag) {
				lie++;
			}
		}
		
		System.out.println(lie);
	}
	
	public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            // a 와 b 가 다른 집합에 속한다면
            parent[b] = a; // 같은 집합으로 묶어주기
        }
    }

    public static int find(int a) {
        if (parent[a] == a) {
            // a 의 부모가 a 이면(root)
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }
	
}