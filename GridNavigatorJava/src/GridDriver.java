import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GridDriver {

	public static void main(String[] args) throws FileNotFoundException {
		File problemFile = new File("src/resources/problem.txt");
		int mazeSize = 0;
		//The 0th index of start represents the x-coordinate, and the 1st index represents y-coordinate 
		int[] start = new int[2];
		//The 0th index of goal represents the x-coordinate, and the 1st index represents y-coordinate 
		int[] goal = new int[2];
		int algoIndex = 0;
		int gridNumber = 0;
		try (Scanner input = new Scanner(problemFile)) {
			// Assuming the problem.txt has integers without any error
			mazeSize = input.nextInt();
			start[0] = input.nextInt();
			start[1] = input.nextInt();
			goal[0] = input.nextInt();
			goal[1] = input.nextInt();
			algoIndex = input.nextInt();
			gridNumber = input.nextInt();
        } catch (FileNotFoundException e) {
        	System.out.println("Problem.txt does not exist");
		}
		
		String gridFileName = "maze_"+gridNumber+".txt";
		File gridFile = new File(gridFileName);
		navigateGrid(gridFile, mazeSize, start, goal, algoIndex);
	}
	
	private static void navigateGrid(File gridFile, int mazeSize, int[] start, int[] goal, int algoIndex) throws FileNotFoundException {
		Grid problemGrid = new Grid(gridFile, mazeSize, start, goal);
		// add a swith statement to call the appropriate algo
		switch(algoIndex) {
			case 0:
				problemGrid.gridBFS();
			case 1:
				problemGrid.gridBidirectional();
			case 2: 
				problemGrid.gridManhattan();
			case 3: 
				problemGrid.gridHFive();
			case 4:
				problemGrid.gridOwnHeuristic();
			default:
				System.out.println("Invalid maze algorithm provided");
				
		}
	}

}
