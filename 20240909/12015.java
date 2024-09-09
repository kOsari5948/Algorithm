import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[n];
		int[] LIS = new int[n];
		
		
		for(int i =0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		LIS[0] = arr[0];
		int len = 1; 
		
		for(int i =1; i<n; i++) {
			int key = arr[i];
			
			//만약 최대 값 보다 현재값이 크면
			//그냥 넣기
			if(LIS[len-1]<key) {
				len++;
				LIS[len-1] =key;
			}else {
				//아니라면
				//이분탐색 진행
				//변환 진행
				int left = 0;
				int right = len;
				
				while(left<right) {
					int mid = (left+right) /2;
					
					if(LIS[mid]<key) {
						left = mid+1;
					}else {
						right = mid;
					}
					
				}
				
				//이분 탐색으로 진행이 끝나면 변환
				LIS[left] = key;
				
			}
			
			
			
		}
		
		System.out.println(len);
		
	}	
}