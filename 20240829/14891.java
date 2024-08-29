import java.io.*;
import java.util.*;

public class Main {
	
	public static int[][] arr;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		arr = new int[4][8];
		
		for(int i =0; i<4;i++) {
			char[] t = sc.next().toCharArray();
			for(int j =0; j<8; j++) {
				arr[i][j] = t[j]-'0';
			}
		}
		
		int n = sc.nextInt();
		for(int i =0 ;i <n; i++) {
			int loc = sc.nextInt();
			int rotation = sc.nextInt();
			
			run(loc-1,rotation);
		}
		
		int sum = 0;
		
		if(arr[0][0] == 1) {
			sum+=1;
		}
		
		if(arr[1][0] == 1) {
			sum+=2;
		}
		
		if(arr[2][0] == 1) {
			sum+=4;
		}
		
		if(arr[3][0] == 1) {
			sum+=8;
		}
		
		
		System.out.println(sum);
		
	}
	
	public static void run (int x, int ro) {
		//2번이 오른쪽
		//6번이 왼쪽 톱니
		int o_right = arr[x][2];
		int o_left = arr[x][6]; 
		int o_ro   = ro;
		
		
		int right = o_right;
		int left = o_left;
		//최초의 녀석 돌리기
		move(x,ro);
		
		//본인 왼쪽 처리
		for(int i =x-1; i>=0; i--) {
			if(left != arr[i][2]) {
				right = arr[i][2];
				left = arr[i][6];
				ro = (ro)*-1;
				move(i,ro);
			}else {
				break;
			}
			
		}
		
		right = o_right;
		left  = o_left;
		ro  = o_ro ;
		//본인 오른쪽 처리
		for(int i =x+1; i<4; i++) {
			if(right != arr[i][6]) {
				right = arr[i][2];
				left = arr[i][6];
				ro = (ro)*-1;
				move(i,ro);
			}else {
				break;
			}
		}
		
	}
	
	public static void move(int x, int ro) {
		if(ro == 1) {
			int temp = arr[x][7];
			
			for(int i =6 ;i>=0; i--) {
				arr[x][i+1] = arr[x][i]; 
			}
			
			arr[x][0] = temp;
		}else {
			int temp = arr[x][0];
			
			for(int i =1 ;i<8; i++) {
				arr[x][i-1] = arr[x][i]; 
			}
			
			arr[x][7] = temp;
		}
	}

}
