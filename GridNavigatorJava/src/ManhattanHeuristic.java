import java.util.Comparator;
import java.util.PriorityQueue;

public class ManhattanHeuristic {
	//All possible directions the agent can move
	private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	int[][] manhattanGrid;
	int[][] visited;
	Grid builtGrid;
	int costOfPath = 0;
	int numNodesExpanded = 1;
	
	public ManhattanHeuristic(Grid builtGrid) {
		this.builtGrid = builtGrid;
		manhattanGrid = new int[builtGrid.getGrid().length][builtGrid.getGrid().length];
		visited = new int[builtGrid.getGrid().length][builtGrid.getGrid().length];
		for(int i = 0; i < builtGrid.getGrid().length; i++) {
			for(int j = 0; j < builtGrid.getGrid().length; j++) {
				manhattanGrid[i][j] = builtGrid.getGrid()[i][j];
				visited[i][j] = builtGrid.getGrid()[i][j];
			}
		}
	}
	
	public int[][] solveMaze() {
		Comparator<GridNode> manhattanComparator = new ManhattanComparator(builtGrid.getGoal());
		PriorityQueue<GridNode> frontier = new PriorityQueue<GridNode>(10, manhattanComparator);
		GridNode start = builtGrid.getStart();
		frontier.add(start);
		
		while(!frontier.isEmpty()) {
			GridNode tracker = frontier.remove();
			
			if(visited[tracker.getX()][tracker.getY()] == 4
					|| visited[tracker.getX()][tracker.getY()] == 1)
				continue;
			
			if(manhattanGrid[tracker.getX()][tracker.getY()] == 3) {
				GridNode tempTracker = tracker.getCaller();
				this.costOfPath = tempTracker.getCost();
				if(tracker.getX() != 0)
					this.costOfPath += 1;
				else if(tracker.getY() != 0)
					this.costOfPath += 2;
				while(tempTracker != null) {
					if(!(tempTracker.getCaller() == null))
						manhattanGrid[tempTracker.getX()][tempTracker.getY()] = 4;
					tempTracker = tempTracker.getCaller();
					
				} 
				return manhattanGrid;
			}
			visited[tracker.getX()][tracker.getY()] = 4;
			for (int[] direction : DIRECTIONS) {
				GridNode temp = new GridNode(tracker.getX() + direction[0], tracker.getY() + direction[1]);
				if(builtGrid.validNode(temp) && visited[temp.getX()][temp.getY()] != 4 && !(frontier.contains(temp))
						&& visited[temp.getX()][temp.getY()] != 1) {
					numNodesExpanded++;
					temp = new GridNode(tracker.getX() + direction[0], tracker.getY() + direction[1], tracker);
					if(direction[0] != 0)
						temp.addToCost(1);
					if(direction[1] != 0)
						temp.addToCost(2);
					temp.addToCost(tracker.getCost());
			//		System.out.println("("+temp.getX()+", "+temp.getY()+") added to frontier");
					frontier.add(temp);
				}
			}
			
		}
		System.out.println("No path found");
		return null;
	}
	
	public int getNumNodesExpanded() {
		return numNodesExpanded;
	}
	
	public int getTotalCost() {
		return costOfPath;
	}
	
	public void printGrid() {
		for(int i = 0; i < manhattanGrid.length; i++) {
			for(int j = 0; j < manhattanGrid.length; j++) {
				System.out.print(manhattanGrid[i][j] + " ");
			}
			System.out.println();
		}
	}
}
