import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Grid {
	
	// 0 - represents a free space where the agent can move
	// 1 - represents a block where the agent cannot move
	// 2 - represents the starting point
	// 3 - represents the ending point
	// 4 - represents the free space used in the path
	private int[][] grid;
	private int[] start;
	private int[] goal;
	private int mazeSize;

	public Grid(File gridFile, int mazeSize, int[] start, int[] goal) throws FileNotFoundException {
		this.mazeSize = mazeSize;
		this.start = start;
		this.goal = goal;
		buildGrid(gridFile);
	}
	
	private void buildGrid(File gridFile) throws FileNotFoundException {
		String gridToText = "";
		try (Scanner input = new Scanner(gridFile)) {
            while (input.hasNextLine()) {
                gridToText += input.nextLine() + "\n";
            }
        }
		grid = new int[mazeSize][mazeSize];
		String temp[] = gridToText.split("[\r]?\n");
		for(int i = 0; i < mazeSize; i++) {
			for(int j = 0; j < mazeSize; j++) {
				if(temp[i].charAt(j) == '0')
					grid[i][j] = 0;
				else if(temp[i].charAt(j) == '1')
					grid[i][j] = 1;
			}
		}
		grid[start[0]][start[1]] = 2;
		grid[goal[0]][goal[1]] = 3;
	}

	public void gridBFS() {
		// TODO Auto-generated method stub
		
	}

	public void gridBidirectional() {
		// TODO Auto-generated method stub
		
	}

	public void gridManhattan() {
		// TODO Auto-generated method stub
		
	}

	public void gridHFive() {
		// TODO Auto-generated method stub
		
	}

	public void gridOwnHeuristic() {
		// TODO Auto-generated method stub
		
	}
	
}
