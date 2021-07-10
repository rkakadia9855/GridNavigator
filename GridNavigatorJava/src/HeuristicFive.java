import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HeuristicFive {
	//All possible directions the agent can move
	private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	int[][] heuristicGrid;
	int[][] visited;
	Grid builtGrid;
	
	public HeuristicFive(Grid builtGrid) {
		this.builtGrid = builtGrid;
		heuristicGrid = new int[builtGrid.getGrid().length][builtGrid.getGrid().length];
		visited = new int[builtGrid.getGrid().length][builtGrid.getGrid().length];
		for(int i = 0; i < builtGrid.getGrid().length; i++) {
			for(int j = 0; j < builtGrid.getGrid().length; j++) {
				heuristicGrid[i][j] = builtGrid.getGrid()[i][j];
				visited[i][j] = builtGrid.getGrid()[i][j];
			}
		}
	}
	
	public int[][] solveMaze() {
		Comparator<GridNode> comparator = new HeuristicComparator(builtGrid.getGoal());
		PriorityQueue<GridNode> frontier = new PriorityQueue<GridNode>(10, comparator);
		GridNode start = builtGrid.getStart();
		frontier.add(start);
		
		while(!frontier.isEmpty()) {
			GridNode tracker = frontier.remove();
			
			if(visited[tracker.getX()][tracker.getY()] == 4
					|| visited[tracker.getX()][tracker.getY()] == 1)
				continue;
			
			if(heuristicGrid[tracker.getX()][tracker.getY()] == 3) {
				ArrayList<GridNode> temp = new ArrayList<GridNode>();
				GridNode tempTracker = tracker.getCaller();
				while(tempTracker != null) {
					if(!(tempTracker.getCaller() == null))
						heuristicGrid[tempTracker.getX()][tempTracker.getY()] = 4;
					tempTracker = tempTracker.getCaller();
					
				} 
				return heuristicGrid;
			}
			visited[tracker.getX()][tracker.getY()] = 4;
			for (int[] direction : DIRECTIONS) {
				GridNode temp = new GridNode(tracker.getX() + direction[0], tracker.getY() + direction[1]);
				if(builtGrid.validNode(temp) && visited[temp.getX()][temp.getY()] != 4 && !(frontier.contains(temp))
						&& visited[temp.getX()][temp.getY()] != 1) {
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
		return null;
	}
	
	public void printGrid() {
		for(int i = 0; i < heuristicGrid.length; i++) {
			for(int j = 0; j < heuristicGrid.length; j++) {
				System.out.print(heuristicGrid[i][j] + " ");
			}
			System.out.println();
		}
	}
}
