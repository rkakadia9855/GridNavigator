import java.util.Comparator;

public class OwnComparator implements Comparator<GridNode> {
	
	GridNode goal;
	
	public OwnComparator(GridNode goal) {
		this.goal = goal;
	}

	@Override
	public int compare(GridNode o1, GridNode o2) {
		if(getTotalDistance(o1, goal) < getTotalDistance(o2, goal)) {
			return -1;
		}
		else if(getTotalDistance(o1, goal) > getTotalDistance(o2, goal)) {
			return 1;
		}
		return 0;
	}
	
	// manhattandistance + totalcostofpath
	private double getTotalDistance(GridNode o1, GridNode goal) {
		double manhattan = (Math.abs(goal.getX() - o1.getX())) + (2 * (Math.abs(goal.getY() - o1.getY())));
		double euclidean = Math.sqrt(Math.pow((Math.abs(goal.getX() - o1.getX())) , 2) + Math.pow(((Math.abs(goal.getY() - o1.getY()))*2), 2));
		return  ((manhattan+euclidean)/2) + o1.getCost();
	}

}
