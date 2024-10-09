import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
    	
    	int n = sc.nextInt();
    	
    	int[] arr = new int[n];
    	for(int i=0; i<n; i++) {
    		arr[i] = sc.nextInt();
    	}
    	
    	//입력받은 추를 정렬하고 해당 원소들을 더해 구할 수 있는 무게를 계산 
    	//https://hy-ung.tistory.com/48 블로그 참조
    	
    	Arrays.sort(arr);
    	
    	int sum = 0;//원소의 합
    	
    	for(int i =0; i<n; i++) {
    		if(sum+1<arr[i]) {
    			break;
    		}
    		sum+=arr[i];
    	}
    	
    	System.out.println(sum+1);
    	
	}
}