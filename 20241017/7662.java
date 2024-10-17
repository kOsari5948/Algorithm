import java.io.*;
import java.util.*;
public class Main {
	
	static Map<Integer, Integer> map;
	
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	int t  = Integer.parseInt(br.readLine());
    	
    	for(int tt =0; tt<t; tt++) {
    		PriorityQueue<Integer> maxque = new PriorityQueue<>();
        	PriorityQueue<Integer> minque = new PriorityQueue<>(Collections.reverseOrder());
        	map = new HashMap<>();
        	
        	int n =Integer.parseInt(br.readLine());
        	
        	for(int i=0; i<n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String op = st.nextToken();
				
				if(op.equals("I")) {
					int num = Integer.parseInt(st.nextToken());
					maxque.add(num);
					minque.add(num);
					map.put(num, map.getOrDefault(num, 0)+1);
				}else {
					int type = Integer.parseInt(st.nextToken());
					
					if(map.size()==0) continue;
					if(type == 1) { //최댓값 삭제 
						delete(maxque);
					}else { // 최솟값 삭제
						delete(minque);
					}
				}
			}
        	
        	if (map.size()==0) {
	            sb.append("EMPTY\n");
	        } else {
	        	int res = delete(maxque);
	        	sb.append(res+" "); // 최댓값 
	        	if(map.size()>0) res = delete(minque);
	        	sb.append(res+"\n"); // 최솟값
	        }
			
    	}
    	System.out.println(sb.toString());
    	
    }
    
    
    static int delete(Queue<Integer> q) {
		int res = 0;
		while(true) {
			res = q.poll();
			
			int cnt = map.getOrDefault(res, 0);
			if(cnt ==0) continue;
			
			//중복 키의 경우 1보다 클텐데 그럼 작은 값을 만들어서 넣어준다.
			if(cnt ==1) map.remove(res);
			else map.put(res, cnt-1);
			break;
		}
		
		return res;
	}
}