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
		String gridNumber = "";
		try (Scanner input = new Scanner(problemFile)) {
			// Assuming the problem.txt has integers without any error
			mazeSize = input.nextInt();
			start[0] = input.nextInt();
			start[1] = input.nextInt();
			goal[0] = input.nextInt();
			goal[1] = input.nextInt();
			algoIndex = input.nextInt();
			gridNumber = input.next();
        } catch (FileNotFoundException e) {
        	System.out.println("Problem.txt does not exist");
		}
		
		String gridFileName = "maze_"+gridNumber+".txt";
		File gridFile = new File("src/resources/mazes/"+gridFileName);
		navigateGrid(gridFile, mazeSize, start, goal, algoIndex);
		
		//Now time to run all the algorithms in 50 different environments
	}
	
	private static void navigateGrid(File gridFile, int mazeSize, int[] start, int[] goal, int algoIndex) throws FileNotFoundException {
		Grid problemGrid = new Grid(gridFile, mazeSize, start, goal);
		int[][] solvedGrid;
		// add a switch statement to call the appropriate algo
		switch(algoIndex) {
			case 0:
				ManhattanHeuristic bfs = new ManhattanHeuristic(problemGrid);
				solvedGrid = bfs.solveMaze();
				bfs.printGrid();
				break;
			case 1:
				ManhattanHeuristic dfs = new ManhattanHeuristic(problemGrid);
				solvedGrid = dfs.solveMaze();
				dfs.printGrid();
				break;
			case 2: 
				ManhattanHeuristic manhattan = new ManhattanHeuristic(problemGrid);
				solvedGrid = manhattan.solveMaze();
				manhattan.printGrid();
				break;
			case 3: 
				ManhattanHeuristic otherHeur = new ManhattanHeuristic(problemGrid);
				solvedGrid = otherHeur.solveMaze();
				otherHeur.printGrid();
				break;
			case 4:
				ManhattanHeuristic ownHeur = new ManhattanHeuristic(problemGrid);
				solvedGrid = ownHeur.solveMaze();
				ownHeur.printGrid();
				break;
			default:
				System.out.println("Invalid maze algorithm provided: "+algoIndex);
				
		} 
	
	}

}
