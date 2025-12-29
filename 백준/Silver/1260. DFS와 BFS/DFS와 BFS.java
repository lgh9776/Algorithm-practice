import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static StringBuilder sb;
	
	static int N, M, V;
	static List<Integer>[] adj;
	static boolean[] isVisit;

	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			adj[n1].add(n2);
			adj[n2].add(n1);
		}
		
		for (int i = 1; i <= N; i++) {
			Collections.sort(adj[i]);
		}
		
		isVisit = new boolean[N+1];
		sb.append(V).append(" ");
		isVisit[V] = true;
		dfs(V, 0);
		
		sb.append("\n");
		
		isVisit = new boolean[N+1];
		bfs(V);
		
		System.out.println(sb);
	}

	static void dfs(int s, int d) {
		if(d == N) {		
			return;
		}
		
		for(int i : adj[s]) {
			if(isVisit[i] == true) {
				continue;
			}
			
			isVisit[i] = true;
			sb.append(i).append(" ");
			dfs(i, d + 1);
		}		
	}
	
	static void bfs(int s) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(s);
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				int now = q.poll();
				
				if(isVisit[now] == true) {
					continue;
				}
				
				isVisit[now] = true;
				sb.append(now).append(" ");
				
				for(int j : adj[now]) {
					if(isVisit[j] == true) {
						continue;
					}
					q.offer(j);
				}				
			}
		}
	}
}