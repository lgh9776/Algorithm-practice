import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int mapRow;
    static int mapCol;
    static int[][] map;
    static boolean[][] isVisited;

    static int result;

    static int[] delta_x = {-1, 0, 1, 0, -1, -1, 1, 1};
    static int[] delta_y = {0, 1, 0, -1, -1, 1, -1, 1};

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        input();

        // 3. 차례대로 방문하지 않은 칸에서 산봉우리 탐색
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                if (!isVisited[row][col] && map[row][col] != 0) {
                    if (bfs(row, col)) {
                        result++;
                    }
                }
            }
        }

        System.out.println(result);
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());

        // 1. 농장 사이즈 입력 받기
        mapRow = Integer.parseInt(st.nextToken());
        mapCol = Integer.parseInt(st.nextToken());

        // 2. 농장 정보 입력 받기
        map = new int[mapRow][mapCol];
        for (int row = 0; row < mapRow; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < mapCol; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        isVisited = new boolean[mapRow][mapCol];
        result = 0;
    }

    static boolean bfs(int x, int y) {
        Deque<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(x, y));
        isVisited[x][y] = true;

        boolean isPeak = true; // 산봉우리 여부
        int currentHeight = map[x][y];

        while (!q.isEmpty()) {
            Pos now = q.poll();

            for (int index = 0; index < delta_x.length; index++) {
                int nextX = now.x + delta_x[index];
                int nextY = now.y + delta_y[index];

                // 범위 체크
                if (nextX < 0 || nextX >= mapRow || nextY < 0 || nextY >= mapCol) {
                    continue;
                }

                // 산봉우리보다 높은 곳이 인접하면 산봉우리가 아님
                if (map[nextX][nextY] > currentHeight) {
                    isPeak = false;
                }

                // 같은 높이의 연결된 격자를 탐색
                if (!isVisited[nextX][nextY] && map[nextX][nextY] == currentHeight) {
                    q.offer(new Pos(nextX, nextY));
                    isVisited[nextX][nextY] = true;
                }
            }
        }

        return isPeak;
    }
}
