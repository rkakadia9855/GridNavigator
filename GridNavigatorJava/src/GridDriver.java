import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GridDriver {

	public static void main(String[] args) throws FileNotFoundException {
		File problemFile = new File(args[0]);
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
		System.out.println("Parsing of problem.txt completed.");
		System.out.println("Maze size: "+mazeSize);
		System.out.println("Algorith to use: "+ algoIndex);
		File gridFile = new File("resources/mazes/"+gridFileName);
		navigateGrid(gridFile, mazeSize, start, goal, algoIndex);
//		File gridFile = new File("resources/example_9x9.txt");
//		navigateGrid(gridFile, 9, new int[] {1, 0}, new int[] {8,7}, 0);
		
		//Now time to run all the algorithms in 50 different environments
		
		
		Scanner furtherScan = new Scanner(System.in);
		System.out.println("Would you like to run all algorithms in 50 environments? Reply using yes or no.");
		String reply = furtherScan.next();
		reply = reply.toLowerCase();
		if(reply.contains("yes")) {
			//Running Algo 0
			gridNumber = "0";
			mazeSize = 101;
			
			int ownNodes = 0;
			int fiveNodes = 0;
			int manhattanNodes = 0;
			int dfsNodes = 0;
			int bfsNodes = 0;
			
			int ownCost = 0;
			int fiveCost = 0;
			int manhattanCost = 0;
			int dfsCost = 0;
			int bfsCost = 0;
			
			long ownTime = 0;
			long fiveTime = 0;
			long manhattanTime = 0;
			long dfsTime = 0;
			long bfsTime = 0;
			
			long startTime = 0;
			long endTime = 0;
			
			//Maybe first find start and goal positions for all grids
			ArrayList<int[][]> bfsPaths = new ArrayList<int[][]>();
			ArrayList<int[][]> dfsPaths = new ArrayList<int[][]>();
			ArrayList<int[][]> manhattanPaths = new ArrayList<int[][]>();
			ArrayList<int[][]> fivePaths = new ArrayList<int[][]>();
			ArrayList<int[][]> ownPaths = new ArrayList<int[][]>();
			ArrayList<ArrayList<Integer>> positions = null;
			for(int i = 10; i < 60; i++) {
				System.out.println("Iteration number "+(i-9));
				gridNumber = "0";
				gridNumber += i;
				gridFile = new File("resources/mazes/maze_"+gridNumber+".txt");
				Grid tempGrid = new Grid(gridFile, 101, new int[] {-1, -1}, new int[] {-1, -1});
				positions = tempGrid.getPositions();
				Random random = new Random();
				int startIndex = random.nextInt(positions.size());
				int goalIndex = random.nextInt(positions.size());
				while(goalIndex == startIndex)
					goalIndex = random.nextInt(positions.size());
				
				System.out.println("Grid: maze_"+gridNumber+".txt");
				System.out.print("Start: ("+positions.get(startIndex).get(0)+", "+positions.get(startIndex).get(1)+") ");
				System.out.println("Goal: ("+positions.get(goalIndex).get(0)+", "+positions.get(goalIndex).get(1)+") ");
				
				
				//Run BFS
				Grid algoGrid = new Grid(gridFile, 101, new int[] {positions.get(startIndex).get(0), positions.get(startIndex).get(1)},
						new int[] {positions.get(goalIndex).get(0), positions.get(goalIndex).get(1)});
				startTime = System.nanoTime();
				BFS bfs = new BFS(algoGrid);
				endTime = System.nanoTime();
				bfsTime += (endTime - startTime);
				bfsNodes += bfs.getNodesExpanded();
				bfsCost += bfs.getCost();
				bfsPaths.add(bfs.getPath());
				
				
				//Run DFSLimit
				algoGrid = new Grid(gridFile, 101, new int[] {positions.get(startIndex).get(0), positions.get(startIndex).get(1)},
						new int[] {positions.get(goalIndex).get(0), positions.get(goalIndex).get(1)});
				startTime = System.nanoTime();
				BFS dfs = new BFS(algoGrid);
				endTime = System.nanoTime();
				dfsTime += (endTime - startTime);
				dfsNodes += dfs.getNodesExpanded();
				dfsCost += dfs.getCost();
				dfsPaths.add(dfs.getPath());
				
				
				//Manhattan
				algoGrid = new Grid(gridFile, 101, new int[] {positions.get(startIndex).get(0), positions.get(startIndex).get(1)},
						new int[] {positions.get(goalIndex).get(0), positions.get(goalIndex).get(1)});
				ManhattanHeuristic manhattan = new ManhattanHeuristic(algoGrid);
				startTime = System.nanoTime();
				manhattanPaths.add(manhattan.solveMaze());
				endTime = System.nanoTime();
				manhattanTime += (endTime - startTime);
				manhattanNodes += manhattan.getNumNodesExpanded();
				manhattanCost += manhattan.getTotalCost();
				
				//Max heuristic
				algoGrid = new Grid(gridFile, 101, new int[] {positions.get(startIndex).get(0), positions.get(startIndex).get(1)},
						new int[] {positions.get(goalIndex).get(0), positions.get(goalIndex).get(1)});
				HeuristicFive heurFive = new HeuristicFive(algoGrid);
				startTime = System.nanoTime();
				fivePaths.add(heurFive.solveMaze());
				endTime = System.nanoTime();
				fiveTime += (endTime - startTime);
				fiveNodes += heurFive.getNumNodesExpanded();
				fiveCost += heurFive.getTotalCost();
				
				//Average Heuristic
				algoGrid = new Grid(gridFile, 101, new int[] {positions.get(startIndex).get(0), positions.get(startIndex).get(1)},
						new int[] {positions.get(goalIndex).get(0), positions.get(goalIndex).get(1)});
				OwnHeuristic ownHeur = new OwnHeuristic(algoGrid);
				startTime = System.nanoTime();
				ownPaths.add(ownHeur.solveMaze());
				endTime = System.nanoTime();
				ownTime += (endTime - startTime);
				ownNodes += ownHeur.getNumNodesExpanded();
				ownCost += ownHeur.getTotalCost();
			}
			System.out.println("_____________________");
			System.out.println("BFS results: ");
			System.out.println("Average nodes expanded: "+(bfsNodes/50));
			System.out.println("Average cost of finding path: "+(bfsCost/50));
			System.out.println("Average execution time: "+(bfsTime/50)+" nano seconds.");
			
			System.out.println("_____________________");
			System.out.println("DFS Depth Limited results: ");
			System.out.println("Average nodes expanded: "+(dfsNodes/50));
			System.out.println("Average cost of finding path: "+(dfsCost/50));
			System.out.println("Average execution time: "+(dfsTime/50)+" nano seconds.");
			
			System.out.println("_____________________");
			System.out.println("A* with Manhattan Heuristic results: ");
			System.out.println("Average nodes expanded: "+(manhattanNodes/50));
			System.out.println("Average cost of finding path: "+(manhattanCost/50));
			System.out.println("Average execution time: "+(manhattanTime/50)+" nano seconds.");
			
			System.out.println("_____________________");
			System.out.println("A* with max(h0, h1) heuristic results: ");
			System.out.println("Average nodes expanded: "+(fiveNodes/50));
			System.out.println("Average cost of finding path: "+(fiveCost/50));
			System.out.println("Average execution time: "+(fiveTime/50)+" nano seconds.");
			
			System.out.println("_____________________");
			System.out.println("Average heuristic results: ");
			System.out.println("Average nodes expanded: "+(ownNodes/50));
			System.out.println("Average cost of finding path: "+(ownCost/50));
			System.out.println("Average execution time: "+(ownTime/50)+" nano seconds.");
			
			System.out.println();
			if(bfsPaths.size() != 50)
				System.out.println("BFSPATHS size: "+bfsPaths.size());
			if(dfsPaths.size() != 50)
				System.out.println("DFSPATHS size: "+dfsPaths.size());
			if(manhattanPaths.size() != 50)
				System.out.println("Manhattan Paths size: "+manhattanPaths.size());
			if(fivePaths.size() != 50)
				System.out.println("Five paths size: "+fivePaths.size());
			if(ownPaths.size() != 50)
				System.out.println("own paths size: "+ownPaths.size());
			System.out.println("Would you like to print any of the solved grids from above?");
			furtherScan = new Scanner(System.in);
			reply = furtherScan.next();
			if(reply.contains("yes")) {
				while(!reply.contains("quit")) {
					System.out.println("Enter grid number from (10-59) inclusive. Be sure to enter number to prevent crash");
					int displayGridNumber = furtherScan.nextInt();
					if(displayGridNumber < 10 || displayGridNumber > 59) {
						System.out.println("You didn't provide correct grid number. Exiting Program.");
						continue;
					}
					System.out.println("Enter algorithm index for which you would like to print the grid: (0-4)");
					System.out.println("0 - BFS");
					System.out.println("1 - DFS Depth Limited");
					System.out.println("2 - A* with manhattan heuristic");
					System.out.println("3 - A* with max(euclidean, manhattan) heuristic");
					System.out.println("4 - A* with AVG(euclidean, manhattan) heuristic");
					int displayAlgoIndex = furtherScan.nextInt();
					switch(displayAlgoIndex) {
					case 0:
						Grid.drawPath(bfsPaths.get(displayGridNumber-10));
						break;
					case 1:
						Grid.drawPath(dfsPaths.get(displayGridNumber-10));
						break;
					case 2:
						Grid.drawPath(manhattanPaths.get(displayGridNumber-10));
						break;
					case 3:
						Grid.drawPath(fivePaths.get(displayGridNumber-10));
						break;
					case 4:
						Grid.drawPath(ownPaths.get(displayGridNumber-10));
						break;
					default:
						System.out.println("You didn't provide correct algorithm number. Exiting Program.");
						continue;
					}
					System.out.println("Print another grid? If not, type \"quit\"");
					reply = furtherScan.next();
				}
			}
			furtherScan.close();
		}
	}
	
	private static void navigateGrid(File gridFile, int mazeSize, int[] start, int[] goal, int algoIndex) throws FileNotFoundException {
		Grid problemGrid = new Grid(gridFile, mazeSize, start, goal);
		System.out.println("Initial Grid: ");
		Grid.drawPath(problemGrid.getGrid());
		System.out.println("Terminology: ");
		System.out.println("S: Starting positing of agent");
		System.out.println("E: Position the agent should reach");
		System.out.println("#: Walls/Blocks that the agent cannot go through.");
		System.out.println("_: Free space that agent can go through.");
		System.out.println("*: Free space utilised by the agent. The space included in the path to reach goal");
		System.out.println("Outputting the grid: ");
		// add a switch statement to call the appropriate algo
		switch(algoIndex) {
			case 0:
				BFS bfs = new BFS(problemGrid);
				Grid.drawPath(bfs.getPath());
				System.out.println("Number of nodes expanded: "+bfs.getNodesExpanded());
				System.out.println("Total cost: "+bfs.getCost());
				break;
			case 1:
				DFSLimit dfs = new DFSLimit(problemGrid);
				Grid.drawPath(dfs.getPath());
				System.out.println("Number of nodes expanded: "+dfs.getNodesExpanded());
				System.out.println("Total cost: "+dfs.getCost());
				break;
			case 2: 
				ManhattanHeuristic manhattan = new ManhattanHeuristic(problemGrid);
				Grid.drawPath(manhattan.solveMaze());
				System.out.println("Number of nodes expanded: "+manhattan.getNumNodesExpanded());
				System.out.println("Total cost: "+manhattan.getTotalCost());
				break;
			case 3: 
				HeuristicFive maxHeur = new HeuristicFive(problemGrid);
				Grid.drawPath(maxHeur.solveMaze());
				System.out.println("Number of nodes expanded: "+maxHeur.getNumNodesExpanded());
				System.out.println("Total cost: "+maxHeur.getTotalCost());
				break;
			case 4:
				OwnHeuristic ownHeur = new OwnHeuristic(problemGrid);
				Grid.drawPath(ownHeur.solveMaze());
				System.out.println("Number of nodes expanded: "+ownHeur.getNumNodesExpanded());
				System.out.println("Total cost: "+ownHeur.getTotalCost());
				break;
			default:
				System.out.println("Invalid maze algorithm provided: "+algoIndex);
				
		}
	}

}
