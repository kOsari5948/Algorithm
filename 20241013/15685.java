import java.io.*;
import java.util.*;

public class Main {
	private static final int RIGHT = 0;
    private static final int UP = 1;
    private static final int LEFT = 2;
    private static final int DOWN = 3;
    private static final int LENGTH = 101;
 
    private static boolean[][] map = new boolean[LENGTH][LENGTH];
    
    
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int n  = sc.nextInt();
		
		for(int i =0; i<n; i++){
			//처음위치
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			//처음 방향 
			int d = sc.nextInt();
			
			//세대
			int g = sc.nextInt();
			
			//입력된 값으로 그림 그리기
			draw(x, y, getDirections(d, g));
		}
		
		//정사각형 찾기
		System.out.println(getNumberOfSquares());
	}
	
	//이전 위치를 기반으로 위치 찾기
	 public static List<Integer> getDirections(int d, int g) {
		 /*이전 위치 기준으로 ↓(3)에서 90도 회전 할 경우 ->(0)으로 이동 
		 */
		 
	      List<Integer> directions = new ArrayList<>();
	      directions.add(d);
	      while (g-- > 0) {
	       for (int i = directions.size() - 1; i >= 0; i--) {
	   		   int direction = (directions.get(i) + 1) % 4;
	    	   directions.add(direction);
	   		   }
	   	   }
	      return directions;
	 }
	 //위치를 가지고 그림 그리기
	 public static void draw(int x, int y, List<Integer> directions) {
	      /*
	       * 0: (→) x++
	       * 1: (↑) y--
	       * 2: (←) x--
	       * 3: (↓) y++
	       * 이동 방향 설정  
	       */
		  
		  map[x][y] = true;
	 
	       for (int direction : directions) {
            switch (direction) {
	               case RIGHT:
	                   map[++x][y] = true;
	                   break;
	               case UP:
	                   map[x][--y] = true;
	                   break;
	               case LEFT:
	                   map[--x][y] = true;
	                   break;
	               case DOWN:
	                   map[x][++y] = true;
	                   break;
	           }
	       }
	   }
	 //완전 탐색으로 통한 정사각형 찾기
	  private static int getNumberOfSquares() {
	        int count = 0;
	 
	        for (int x = 0; x < LENGTH-1; x++) {
	            for (int y = 0; y < LENGTH-1; y++) {
	                if (map[x][y] && map[x + 1][y] && map[x][y + 1] && map[x + 1][y + 1])
	                    count++;
	            }
	        }
	 
	        return count;
	    }
}
