import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static List<Integer> nn = new ArrayList<>();
	static List<Integer> pn = new ArrayList<>();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		
		//양수는 양수끼리 음수는 음수끼리 합치기
		for (int i = 0; i < n; i++) {
			int t = Integer.parseInt(br.readLine());
			if (t > 0)
				pn.add(t);
			else
				nn.add(t);
		}

		//pm은 내림차순 nn은 오름 차순 정렬
		Collections.sort(pn, Collections.reverseOrder());
		Collections.sort(nn);

		
		// 양수들 끼리 먼저 묶자
		// 단 양수가 1일 경우엔 곱하는 것보다 더하는게 이득임
		int sum = 0;
		int i = 0;
		while (i < pn.size()) {
			//마지막이 아니면서 현재와 다음값 모두 1이 아닐 경우 만 값을 곱하고 
			//합치는 수가 짝수가 아니거나 (즉 양수가 홀수 개) 곱하는 수 중 하나라고 1이면 그냥 더하기
			if (i + 1 < pn.size() && pn.get(i) != 1 && pn.get(i + 1) != 1) {
				sum += pn.get(i++) * pn.get(i++);
			}
			else {
				sum += pn.get(i++);
			}
				
		}

		//음수들 묶어주기 (즉 음수들 곱해서 양수 만들기)
		i = 0;
		while (i < nn.size()) {
			//음수들도 마지막이 아니라면 곱하고 마지막이라면 넘어가기
			if (i + 1 < nn.size() ) {
				sum += nn.get(i++) * nn.get(i++);
			}
			else {
				sum += nn.get(i++);
			}
		}

		System.out.println(sum);
	}
}