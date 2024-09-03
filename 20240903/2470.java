import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[] arr = new int[n];
		
		for(int i =0; i<n; i++) {
			arr[i] =  sc.nextInt();
		}
		
		int left = 0;
		int right = n-1;
		
		int releft = left;
		int reright = right;
		
		
		
		Arrays.sort(arr);
		int min = arr[ releft] +arr[ reright];
		while(left < right) {
			if(Math.abs(0-min)>Math.abs(0-(arr[left]+arr[right]))) {
				min = arr[left]+arr[right];
				
				releft = left;
				reright = right;
				if(min ==0 ) {
					break;
				}
			}
			
			if(arr[left]+arr[right]<0) {
				left+=1;
				continue;
			}
			
			if(arr[left]+arr[right]>0) {
				right-=1;
				continue;
			}
			
			if(arr[left]+arr[right] == 0) {
				releft = left;
				reright = right;
				break;
			}
		}
		
		
		
		System.out.println(arr[releft] + " "+ arr[reright]);
		
	}
}