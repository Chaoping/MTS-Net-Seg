package cosc520.project;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//TODO Read in Data
		
		
		// A table of graphs
		int n = 0;
		Graph[][] graphTable = new Graph[n][n];
		for(int start = 0; start < n; start++) {
			for( int end = start; end < n; n++) {
				graphTable[start][end] = new Graph(null, start, end);
			}
		}

	}

}
