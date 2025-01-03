package code;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    //도로 및 포탈의 정보 관련 클래스
    static class node{
        int position, time;
        public node(int position, int time) {
            this.position = position;
            this.time = time;
        }
    }
    static final int MAX_VALUE = 25000001;	//나올 수 있는 최대 시간 + 1 값
    public static void main(String[] args) throws IOException{
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] distance;
        ArrayList<ArrayList<node>> graph;
        //각 테스트케이스 진행!
        for(int test_case = 0;test_case<t;test_case++) {
            st = new StringTokenizer(br.readLine()," ");
            graph = new ArrayList<>();
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            for(int i=0;i<=N;i++)
                graph.add(new ArrayList<>());

            //도로에 대한 정보 저장
            for(int i=0;i<M;i++) {
                st = new StringTokenizer(br.readLine()," ");
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                graph.get(S).add(new node(E, T));
                graph.get(E).add(new node(S, T));
            }
            //웜홀에 대한 정보 저장
            for(int i=0;i<W;i++) {
                st = new StringTokenizer(br.readLine()," ");
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                graph.get(S).add(new node(E, T * -1));
            }
            distance = new int[N+1];
            boolean check = false;
            //각 지역을 시작으로 벨먼 포드 진행!
            for(int i=1;i<=N;i++){
                //음수 싸이클 존재할 때
                if(bellman(i, distance, graph, N)){
                    bw.write("YES\n");		//YES BufferedWriter 저장
                    check = true;
                    break;
                }
            }
            if(!check)		//음수 싸이클 존재 X
                bw.write("NO\n");		//NO BufferedWriter 저장
        }
        bw.flush();		//결과 출력
        bw.close();
        br.close();
    }
    //벨먼 포드 알고리즘으로 음수 싸이클 존재하는지 확인하는 함수
    static boolean bellman(int start, int[] distance, ArrayList<ArrayList<node>> graph, int N) {
        boolean check = false;
        Arrays.fill(distance, MAX_VALUE);	//거리 배열 MAX_VALUE로 초기화
        distance[start] = 0;	//시작 지역 0으로 초기화
        //(N-1)번 최단 거리 탐색!
        for (int i = 1; i < N; i++) {
            check = false;
            for (int j = 1; j <= N; j++) {
                for (node next : graph.get(j)) {
                    if (distance[j] != MAX_VALUE && distance[next.position] > distance[j] + next.time) {
                        distance[next.position] = distance[j] + next.time;
                        check = true;
                    }
                }
            }
            if (!check)	//더 이상 최단 거리가 존재하지 않을 때 == 음수 사이클 X
                break;
        }
        //(N-1)번 탐색이 종료된 뒤, 음수 사이클이 존재하는지 확인!
        if(check){
            for(int i=1;i<=N;i++)
                for(node next : graph.get(i))
                    //최단 거리가 변경되었기 때문에 음수 사이클 존재!
                    if(distance[i] != MAX_VALUE && distance[next.position] > distance[i] + next.time)
                        return true;
        }
        return false;		//음수 사이클 존재 X
    }
}