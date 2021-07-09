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
	
	private static final String ANSI_RESET = "\u001B[0m";
	private static final String START = "\u001B[31m";
	private static final String GOAL = "\u001B[32m";
	private static final String PATH = "\u001B[33m";
	private static final String BLOCK = "\u001B[35m";
	public static final String ROAD = "\u001B[30m";

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
		for(int i = 0; i < temp.length; i++) {
			try	(Scanner scn = new Scanner(temp[i])) {
				int x = scn.nextInt();
				int y = scn.nextInt();
				int val = scn.nextInt();
				grid[x][y] = val;
			}
		}
		grid[start[0]][start[1]] = 2;
		System.out.println("Start is "+start[0]+", "+start[1]+" and grid num is "+grid[start[0]][start[1]]);
		grid[goal[0]][goal[1]] = 3;
		System.out.println("Goal is "+goal[0]+", "+goal[1]+" and grid num is "+grid[goal[0]][goal[1]]);
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

	public void display() {
		for(int i = 0; i < mazeSize; i++) {
			for(int j = 0; j < mazeSize; j++) {
				/*switch(grid[i][j]) {
					case 1:
						System.out.print(BLOCK + grid[i][j] + " " + ANSI_RESET);
						break;
					case 2:
						System.out.print(START + grid[i][j] + " " + ANSI_RESET);
						break;
					case 3:
						System.out.print(GOAL + grid[i][j] + " " + ANSI_RESET);
						break;
					case 4:
						System.out.print(PATH + grid[i][j] + " " + ANSI_RESET);
						break;
					default:
						System.out.print(ROAD + grid[i][j] + " " + ANSI_RESET); 
				} */
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		} 
	}
	
}
