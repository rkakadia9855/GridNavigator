import java.util.LinkedList;

public class ManhattanHeuristic {
	//All possible directions the agent can move
	private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	int[][] manhattanGrid;
	Grid builtGrid;
	
	public ManhattanHeuristic(Grid builtGrid) {
		this.builtGrid = builtGrid;
		this.manhattanGrid = builtGrid.getGrid();
	}
	
	public int[][] solveMaze() {
		LinkedList<GridNode> frontier = new LinkedList<>();
		GridNode start = builtGrid.getStart();
		frontier.add(start);
		
		while(!frontier.isEmpty()) {
			GridNode tracker = frontier.remove();
			
			if(manhattanGrid[tracker.getX()][tracker.getY()] == 4
					|| manhattanGrid[tracker.getX()][tracker.getY()] == 1)
				continue;
			
			if(manhattanGrid[tracker.getX()][tracker.getY()] == 3)
				return manhattanGrid;
			for (int[] direction : DIRECTIONS) {
				GridNode temp = new GridNode(tracker.getX() + direction[0], tracker.getY() + direction[1]);
				if(builtGrid.validNode(temp)) {
					frontier.add(temp);
				}
				manhattanGrid[tracker.getX()][tracker.getY()] = 4;
			}
		}
		return null;
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
