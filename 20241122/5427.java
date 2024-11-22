import java.io.*;
import java.util.*;

public class Main {
    
    static int w, h;
    static char map[][];
    static Queue<Point> fire;
    static int dx[] = {-1,1,0,0};
    static int dy[] = {0,0,-1,1};
    static boolean visit[][];
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;
        sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        int x = 0, y = 0;
        
        for(int t = 0; t < tc; t++) {
            stz = new StringTokenizer(br.readLine());
            w = Integer.parseInt(stz.nextToken());
            h = Integer.parseInt(stz.nextToken());
            map = new char[h][w];
            fire = new LinkedList<>();
            
            for(int i = 0; i < h; i++) {
                String line = br.readLine();
                for(int j = 0; j < w; j++) {
                    map[i][j] = line.charAt(j);
                    //현재 위치가 사람이면 사람위치 저장 (첫 시작)
                    if(map[i][j] == '@') {
                        x = i;
                        y = j;
                    }
                    // 불이면 불 저장 (불 확산)
                    else if(map[i][j] == '*')
                        fire.add(new Point(i, j));
                }
            }
            
            escape(x, y);
        }
        
        System.out.println(sb.toString());
    }
    
    public static void escape(int sx, int sy) {
        Queue<Point> q = new LinkedList<>();
        visit = new boolean[h][w];
        visit[sx][sy] = true;
        q.offer(new Point(-1, -1));
        q.offer(new Point(sx, sy));
        int time = -1;
        
        while(!q.isEmpty()) {
            Point now = q.poll();
            
            //현재 시간동안 움직임이 끝났다면 불 확산시키기
            if(now.x == -1 && now.y == -1) {
                burn();
                
                //확산후에도 사람이 탈출 못했다면 현재위치 다시 추가
                //why -1 -1인 위치에서 불 확산 할꺼임
                //즉 아직 사람 탈출 못하면 다음 반복에서 불 확산해야함
                if(!q.isEmpty()) {
                    q.offer(now);
                }
                
                //한 사이클 돌아서 시간 추가
                time++;
                continue;
            }
            
            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                //밖이면 탈출임
                if(nx >= h || ny >= w || nx < 0 || ny < 0) {
                    sb.append(time+1 + "\n");
                    return;
                }
                
                //탈출이 아니라면 4방향으로 움직이기
                if(map[nx][ny] == '.' && !visit[nx][ny]) {
                    q.offer(new Point(nx, ny));
                    visit[nx][ny] = true;
                }
            }
        }
        
        //사람도 불도 없는데 탈출 한 상태가 아니라면 탈출 못하는 상태
        // 즉 impossible 출력
        
        sb.append("IMPOSSIBLE\n");
    }
    
    public static void burn() {
    	
    	//불 따로 저장하 둔 만큼 확산
        int size = fire.size();
        
        for(int s = 0; s < size; s++) {
            Point now = fire.poll();
           
            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                //불 확산하면서 추가된 불은 추가해 주고 지도에도 추가하기
                if(nx >= 0 && ny >= 0 && nx < h && ny < w && (map[nx][ny] == '.' || map[nx][ny] == '@')) {
                    fire.offer(new Point(nx, ny));
                    map[nx][ny] = '*';
                }
            }
        }
    }
    
    static class Point {
        int x, y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}