import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class Main {
    static final int INF = 987654321; //초기화 값(최대 값)
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        
        int [] item = new int[n+1];
        
        st = new StringTokenizer(br.readLine());
        for(int i =1; i<=n; i++) {
        	item[i] = Integer.parseInt(st.nextToken());
        }
 
        //노드들 초기화
        int[][] arr = new int[n + 1][n + 1];
 
        //같으면 0 다르면 무한대
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    arr[i][j] = INF;
                }
            }
        }
 
        //입력 받기
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
 
            arr[a][b] = c;
            arr[b][a] = c;
        }
 
        // 플로이드 와샬 알고리즘 수행
        // i에서 j 까지 가는데 k를 거쳐서 가는 경로중 최솟값
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) {
                        continue;
                    }
 
                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }
 
        // 모든 경로 살펴보기
        int ans = -1;
        for (int i = 1; i <= n; i++) {
        	int temp = item[i];
            for (int j = 1; j <= n; j++) {
            	 if (i==j || arr[i][j] > m) {
            		 continue;
            	 }

                 temp += item[j];
            }
            
            if(ans<temp) {
            	ans = temp;
            }
        }
 
        // ans가 초기값이면 사이클이 존재하지 않음.
 
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
        
    }
 
}