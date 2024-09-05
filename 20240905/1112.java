import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int x = sc.nextInt();
        int b = sc.nextInt();

        boolean isNegative = false;
        if (x < 0 && b > 0) {
            x *= -1;
            isNegative = true;
        }

        ArrayList<Integer> result = new ArrayList<>();

        //나머지 구해서 리스트에 담기
        while (x != 0) {
            int q = x / b;
            int r = x % b;
            	
            //단 r(나머지)가 음수라면 뒤집어 줘야함
            if (r < 0) {
                r -= b;
                q++;
            }

            result.add(r);
            x = q;
        }

        if (result.isEmpty()) {
            result.add(0);
        }
        StringBuilder sb = new StringBuilder();
        if (isNegative) {
        	sb.append("-");
        }

        //나머지를 통해 구했으니 반대로출력
        for (int i = result.size()-1; i>=0; i--) {
            sb.append(result.get(i));
        }

        System.out.println(sb.toString());
    }
}