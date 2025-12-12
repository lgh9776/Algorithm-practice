import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node{
		int e, c;
		Node(int e, int c){
			this.e = e;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		List<Node>[] adj = new ArrayList[D+1];
		for (int i = 0; i <= D; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < D; i++) {
			adj[i].add(new Node(i+1, 1));
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(e > D || e - s <= c) {
				continue;
			}
			
			adj[s].add(new Node(e, c));
		}
		
		int INF = Integer.MAX_VALUE / 2;
		int[] dist = new int[D + 1];
		Arrays.fill(dist, INF);
		
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.c - b.c);
		pq.add(new Node(0, 0));
		dist[0] = 0;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(dist[now.e] < now.c) {
				continue;
			}
			
			if(now.e == D) {
				break;
			}
			
			for(Node node : adj[now.e]) {
				int newDist = dist[now.e] + node.c;
				
				if(dist[node.e] <= newDist) {
					continue;
				}
				
				dist[node.e] = newDist;
				pq.offer(new Node(node.e, newDist));
			}
		}
		
		System.out.println(dist[D]);
	}
}