import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
    	Scanner sc = new Scanner(System.in);
    	
    	char[] arr = sc.nextLine().toCharArray();
    	
    	char[] n = sc.nextLine().toCharArray();
    	
    	int i =0;
    	int count = 0;
    	while(i<arr.length) {
    		boolean flag = true;
    		int j=0;
    		
    		if(i+n.length>arr.length) {
    			break;
    		}
    		
    		for(j=0; j<n.length;j++) {
    			if(arr[i+j]!=n[j]) {
    				flag = false;
    				break;
    			}
    		}
    		
    		if(flag) {
    			count++;
    			i+=j;
    			continue;
    		}
    		i++;
    	}
    	
    	System.out.println(count);
    	
    	
    }
}