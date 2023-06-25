package wareaglecounter;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
	static PrintWriter pw = new PrintWriter(System.out);
	static Scanner sc = new Scanner(System.in);
	static int n, dx[] = { 0, 0, 1, -1, -1, 1, -1, 1 }, dy[] = { 1, -1, 0, 0, -1, 1, 1, -1 };

	static boolean visited[][], war[][];

	static void dfs(int i, int j) {
		if (i < 0 || j < 0 || i >= n || j >= n || !war[i][j] || visited[i][j])
			return;
		visited[i][j] = true;
		for (int x = 0; x < 8; x++)
			dfs(i + dx[x], dy[x] + j);

	}

	public static void main(String[] args) throws IOException {
		int t = 1;
		while (true) {
			n = sc.nextInt();
			visited = new boolean[n][n];
			war = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				String s = sc.next();
				for (int j = 0; j < n; j++)
					war[i][j] = s.charAt(j) == '1';
			}
			int ans = 0;
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					if (!visited[i][j] && war[i][j]) {
						ans++;
						dfs(i, j);
					}
			pw.printf("Image number %d contains %d war eagles.\n", t++, ans);
			if (!sc.ready())
				break;
		}
		pw.flush();
		pw.close();
	}

	static class Scanner {
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s) {
			br = new BufferedReader(new InputStreamReader(s));
		}

		public String next() throws IOException {
			while (st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		public long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		public String nextLine() throws IOException {
			return br.readLine();
		}

		public double nextDouble() throws IOException {
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if (x.charAt(0) == '-') {
				neg = true;
				start++;
			}
			for (int i = start; i < x.length(); i++)
				if (x.charAt(i) == '.') {
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				} else {
					sb.append(x.charAt(i));
					if (dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg ? -1 : 1);
		}

		public boolean ready() throws IOException {
			return br.ready() || st.hasMoreTokens();
		}

	}
}