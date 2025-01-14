import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 트리는 노드의 리스트 형태
 * 각 노드는 값, 자식 노드 저장 리스트를 가짐
 * 
 * 1. 노드 개수 입력 받기
 * 2. 부모 노드를 입력 받아서 해당 노드 리스트로 추가하기
 * 3. 지울 노드 입력 받기
 * 4. 지울 노드와 해당 노드의 자식들 리스트에서 제거
 * 5. 삭제할 노드가 자식 리스트에 포함되어 있으면 제거
 * 6. 자식 노드 리스트가 비어있는 노드의 개수 출력
 */

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	
	static class Node{
		List<Integer> child;
		
		public Node() {
			child = new ArrayList<>();
		}
	}
	
	static Node[] tree;
	static int nodeCnt;
	static int removeNode;
	static int root;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		//1. 노드 개수 입력 받기
		nodeCnt = Integer.parseInt(st.nextToken());
		
		//2. 부모 노드를 입력 받아서 해당 노드 자식 리스트에 추가하기
		tree = new Node[nodeCnt];
		for (int index = 0; index < nodeCnt; index++) {
			tree[index] = new Node();
		}
		
		st = new StringTokenizer(br.readLine());
		for (int value = 0; value < nodeCnt; value++) {
			int parent = Integer.parseInt(st.nextToken());
			if(parent != -1) {
				tree[parent].child.add(value);
			}
			else {
				root = value;
			}
		}
		
		//3. 지울 노드 입력 받기
		st = new StringTokenizer(br.readLine());
		removeNode = Integer.parseInt(st.nextToken());
		
		if(removeNode == root) {
			System.out.println(0);
			return;
		}
		
		//4. 지울 노드와 해당 노드의 자식들 리스트에서 제거
		remove(removeNode);
		tree[removeNode] = null;
		
		//5. 삭제할 노드가 자식 리스트에 포함되어 있으면 제거
		for (Node node : tree) {
			if(node != null) {
				for (int i = 0; i < node.child.size(); i++) {
					if(node.child.get(i).equals(removeNode)) {
						node.child.remove(i);
						break;
					}
				}
			}
		}
		
		//6. 자식 노드 리스트가 비어있는 노드의 개수 출력
		int result = 0;
		for (Node node : tree) {
			if(node != null && node.child.isEmpty()) {
				result++;
			}
		}
		System.out.println(result);
	}
	
	static void remove(int index) {
		if(tree[index] == null) {
			return;
		}
		
		//해당 노드의 자식이 없으면 return
		if(tree[index].child.isEmpty()) {
			return;
		}
		
		//해당 노드의 자식노드부터 제거
		for (int i = tree[index].child.size() - 1; i >= 0; i--) {
			int delNode = tree[index].child.get(i);
				
			remove(delNode);
		
			tree[index].child.remove(i); //자식리스트에서 제거
			tree[delNode] = null; //해당 노드 제거 
		}
	}
}
