import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long count = 0;

        long[] prefixSum = new long[N + 1];
        long[] remainArr = new long[M];
        for (int i = 1; i <= N; ++i) {
            prefixSum[i] = arr[i - 1] + prefixSum[i - 1];
            int remainder = (int) (prefixSum[i] % M);
            if(remainder == 0) ++count;
            ++remainArr[remainder];
        }

        for(int i = 0; i < M; ++i){
            if(remainArr[i] >= 2) count += (remainArr[i] * (remainArr[i] - 1) / 2);
        }

        System.out.println(count);
    }
}