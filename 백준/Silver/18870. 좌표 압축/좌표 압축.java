import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int size;
	static int[] originArr;
	static List<Integer> zipArr;
	
	public static void main(String[] args) throws IOException {
		
		inputData();
		
		int index = 0;
		for (int i = 0; i < size; i++) {
			index = binarySearch(originArr[i]);
			if(index == -1) {
				continue;
			}
			sb.append(index).append(" ");
		}
		System.out.println(sb);
	}
	
	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		size = Integer.parseInt(st.nextToken());
		
		originArr = new int[size];
		HashSet<Integer> temp = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < size; i++) {
			originArr[i] = Integer.parseInt(st.nextToken());
			temp.add(originArr[i]);
		}
		
		zipArr = new ArrayList<Integer>();
		for(int num : temp) {
			zipArr.add(num);
		}
		Collections.sort(zipArr);
	}
	
	static int binarySearch(int target) {
		int start = 0;
		int end = zipArr.size()-1;
		
		int mid = 0;
		while(start <= end) {
			mid = start + (end - start) / 2;
			
			if(zipArr.get(mid) == target) {
				return mid;
			}
			else if(zipArr.get(mid) > target) {
				end = mid - 1;
			}
			else if(zipArr.get(mid) < target) {
				start = mid + 1;
			}
		}
		return -1;
	}

}