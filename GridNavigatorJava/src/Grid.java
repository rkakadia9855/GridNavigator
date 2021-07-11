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
	private GridNode start;
	private GridNode goal;
	private int mazeSize;

	public Grid(File gridFile, int mazeSize, int[] start, int[] goal) throws FileNotFoundException {
		this.mazeSize = mazeSize;
		this.goal = new GridNode(goal[0], goal[1]);
		this.start = new GridNode(start[0], start[1]);
		buildGrid(gridFile);
	}
	
	public int[][] getGrid() {
		return this.grid;
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
		for(int i = 0; i < temp.length; i++) {
			try	(Scanner scn = new Scanner(temp[i])) {
				int x = scn.nextInt(); //row of an array
				int y = scn.nextInt(); //column of an array
				int val = scn.nextInt();
				grid[x][y] = val;
			}
		}
		grid[start.getX()][start.getY()] = 2;
		grid[goal.getX()][goal.getY()] = 3;
	}
	
	public boolean validNode(GridNode aNode) {
		if(aNode.getX() >= 0 && aNode.getX() < mazeSize
				&&
		   aNode.getY() >= 0 && aNode.getY() < mazeSize)
			return true;
		return false;
	}
	
	public GridNode getStart() {
		return this.start;
	}
	
	public GridNode getGoal() {
		return this.goal;
	}

	public int getSize() {
		return this.grid.length;
	}
	
}
