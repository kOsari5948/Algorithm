import java.io.*;
import java.util.*;

public class Main {
	static class Rotation {
		int x1, y1, x2, y2;
		public Rotation(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}    
	
    static int N, M, K;
    static int[][] A; // 기존 배열
    static int[][] arr; // 회전시킬 배열
    static Rotation[] rotation;
    
    static int min = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N+1][M+1];
        arr = new int[N+1][M+1];

        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<M+1; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rotation = new Rotation[K];
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            rotation[i] = new Rotation(r-s, c-s, r+s, c+s);
        }

        boolean[] visited = new boolean[K];
        int[] result = new int[K];
        permutation(0, visited, result);

        System.out.println(min);
        br.close();
    }
    
    // 회전 배열 되돌리기
    static void getNewArr() {
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<M+1; j++) {
                arr[i][j] = A[i][j];
            }
        }
    }
    
    // 회전하기
    static void rotate(int x1, int y1, int x2, int y2) {
        while(x1<x2 && y1<y2) {
            int next = arr[x1][y1];
            int now = 0;

            // 오른쪽
            for(int i=y1+1; i<=y2; i++) {
                now = arr[x1][i];
                arr[x1][i] = next;
                next = now;
            }

            // 아래
            for(int i=x1+1; i<=x2; i++) {
                now = arr[i][y2];
                arr[i][y2] = next;
                next = now;
            }

            // 왼쪽
            for(int i=y2-1; i>=y1; i--) {
                now = arr[x2][i];
                arr[x2][i] = next;
                next = now;
            }

            // 위
            for(int i=x2-1; i>=x1; i--) {
                now = arr[i][y1];
                arr[i][y1] = next;
                next = now;
            }

            x1++;
            y1++;
            x2--;
            y2--;
        }
    }

	// 최솟값 찾기
    static int getMin() {
        int result = Integer.MAX_VALUE;

        for(int i=1; i<arr.length; i++) {
            int count = 0;
            for(int j=1; j<arr[0].length; j++) {
                count += arr[i][j];
            }
            result = Math.min(count, result);
        }

        return result;
    }
    
    // 회전 순서를 결정하는 순열
    static void permutation(int depth, boolean[] visited, int[] result) {
        if(depth == K) {
            getNewArr();
            for(int n: result) {
                rotate(rotation[n].x1, rotation[n].y1, rotation[n].x2, rotation[n].y2);
            }
            min = Math.min(min, getMin());
            return;
        }

        for(int i=0; i<K; i++) {
            if(!visited[i]) {
                visited[i] = true;
                result[depth] = i;
                permutation(depth+1, visited, result);
                visited[i] = false;
            }
        }
    }

 
}