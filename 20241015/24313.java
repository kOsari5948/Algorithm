import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
    	Scanner sc = new Scanner(System.in);
    	
    	int n = sc.nextInt();
    	int m = sc.nextInt();
    	
    	int c= sc.nextInt();
    	int k = sc.nextInt();
    	
    	boolean flag = true;
    	
    	for(int i =k; i<100; i++) {
    		if(n*i+m>c*i) {
    			flag= false;
    			break;
    		}
    	}
    	
    	if(flag) {
    		System.out.println(1);
    	}else {
    		System.out.println(0);
    	}
    }
}