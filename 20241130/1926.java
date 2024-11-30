import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static int[][] map;
    public static int[][] visit;
    public static int n,m,count;
    public static int[] movex = {0,0,1,-1};
    public static int[] movey = {1,-1,0,0};

    public static class Node{
        int x,y;
        public Node(int x, int y){
            this.x=x;
            this.y=y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visit = new int[n][m];
        for(int i =0; i<n; i++){
            st =new StringTokenizer(br.readLine());
            for(int j =0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ArrayList<Integer>arr = new ArrayList<>();

        for(int i =0; i<n; i++){
            for(int j =0; j<m; j++){
                if(visit[i][j] == 0){
                    visit[i][j] = 1;
                    count = 0;
                    bfs(i,j);
                    if(count != 0){
                       arr.add(count);
                    }
                }
            }
        }

        System.out.println(arr.size());

        Collections.sort(arr,Collections.reverseOrder());

        if(!arr.isEmpty()){
            System.out.println(arr.get(0));
        }else{
            System.out.println(0);
        }

    }

    public static void bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x,y));

        while(!q.isEmpty()){
            Node node = q.poll();
            //4방향 이동
            if(map[node.x][node.y] == 1){
                count++;
            }else{
                continue;
            }
            for(int i =0 ;i<4; i++){
                int mx = node.x+movex[i];
                int my = node.y+movey[i];

                if(mx>=0&&mx<n&&my>=0&&my<m){
                    if(visit[mx][my] ==0){
                        q.add(new Node(mx,my));
                        visit[mx][my] = 1;
                    }
                }
            }

        }

    }
}