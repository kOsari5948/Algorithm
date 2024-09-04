import java.io.*;
import java.util.*;
 

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Long W = Long.parseLong(st.nextToken());
		Long WH = W*Long.parseLong(st.nextToken());
		Long f = Long.parseLong(st.nextToken());
		Long c = Long.parseLong(st.nextToken());
		Long x1 = Long.parseLong(st.nextToken());
		Long y1 = Long.parseLong(st.nextToken());
		Long x2 = Long.parseLong(st.nextToken());
		Long y2 = Long.parseLong(st.nextToken());
		Long xf = (long) 0;
		
		//반이상 접혔나
		if (f > W-f) {
			//반이상 접혔다.
			xf = W-f;
		}
		else {
			//반이상 안접혔다.
			//반이 접히거나 기존 오른쪽이 남음
			xf = f;
		}
		
		//접힌 만큼의 길이 wf 보다 x1이 크면 겹치는 부분이 없음 상하로 접은 만큼만 빼주기
		if (xf <= x1) {
			WH -= (x2-x1)*(y2-y1)*(c+1);
		}
		else if (xf < x2) { // wf보다 x2가 작다면 모든 면이 접힌 부분에 포함
			//두번 접힌 부분 빼기
			WH -= (xf-x1)*(y2-y1)*2*(c+1);
			
			//한번 접힌 부분 빼기
			WH -= (x2-xf)*(y2-y1)*(c+1);
		}
		else {
			//모든 면이 2번 접힘
			WH -= (x2-x1)*(y2-y1)*2*(c+1);
		}
		System.out.println(WH);
	}
}