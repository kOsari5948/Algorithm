import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    static class Node {
        int num;
        Node left, right;
        
        //값을 넣기 값을 넣을 땐 새로운 값 추가 좌우 넣어주기
        Node(int num, Node left, Node right) {
            this.num = num;
            this.left = left;
            this.right = right;
        }

        
        //값추가 (트리 만들기)
        void insert(int n) {
            if (n < this.num) {
            	//현재 값보다 들어온 값이 더 작다면 
            	//left가 없으면 만들어서 넣어주고 
            	//left 있으면 그 아랫 단계 insert 진행
                if (this.left == null)
                    this.left = new Node(n,null,null);
                else this.left.insert(n);
            } else {
            	//현재 값보다 들어온 값이 더 크다면
            	//right가 없으면 만들어서 넣어주고 
            	//right 있으면 그 아랫 단계 insert 진행
                if (this.right == null)
                    this.right = new Node(n,null,null);
                else this.right.insert(n);
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        
        //최초 값 추가
        Node root = new Node(Integer.parseInt(br.readLine()),null,null);
        
        String input;
        while (true) {
            input = br.readLine();
            if (input == null || input.equals("")) {
                break;
            }
            root.insert(Integer.parseInt(input));
        }

        postOrder(root);
    }

    static void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.num);
    }
}