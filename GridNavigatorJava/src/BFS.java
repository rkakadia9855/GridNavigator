import java.util.*;

public class BFS {

	private int[][] path;
	private int cost;
	private int nodesExpanded = 1;

    public BFS(Grid maze){

		int[][] bfsPath = new int[maze.getSize()][maze.getSize()];
        boolean[][] marked = new boolean[maze.getSize()][maze.getSize()];
		for(int i = 0; i < maze.getSize(); i++){
			for(int j = 0; j < maze.getSize(); j++){
				bfsPath[i][j] = maze.getGrid()[i][j];
                marked[i][j] = false;
			}
		}

		Queue<GridNode> frontier = new LinkedList<GridNode>();

		GridNode start = new GridNode(maze.getStart().getX(), maze.getStart().getY(), null);
		GridNode goal = new GridNode(maze.getGoal().getX(), maze.getGoal().getY(), null);

		frontier.add(start);
		marked[start.getX()][start.getY()] = true;
        GridNode finish = null;

		while(!frontier.isEmpty()){
			GridNode temp = frontier.remove();
			if(temp.sameNode(goal)){
                finish = temp;
				break;
			}
            GridNode left = new GridNode(temp.getX() - 1, temp.getY(), temp);
			if(maze.validNode(left)){
				if(!marked[left.getX()][left.getY()]){
					marked[left.getX()][left.getY()] = true;
                    if(bfsPath[left.getX()][left.getY()] != 1) {
                    	frontier.add(left);
                    	nodesExpanded++;
                    }
				}
			}
            GridNode right = new GridNode(temp.getX() + 1, temp.getY(), temp);
			if(maze.validNode(right)){
				if(!marked[right.getX()][right.getY()]){
					marked[right.getX()][right.getY()] = true;
					if(bfsPath[right.getX()][right.getY()] != 1) {
						frontier.add(right);
						nodesExpanded++;
					}
				}
			}
            GridNode up = new GridNode(temp.getX(), temp.getY() - 1, temp);
            if(maze.validNode(up)){
				if(!marked[up.getX()][up.getY()]){
					marked[up.getX()][up.getY()] = true;
					if(bfsPath[up.getX()][up.getY()] != 1) {
						frontier.add(up);
						nodesExpanded++;
					}
				}
			}
            GridNode down = new GridNode(temp.getX(), temp.getY() + 1, temp);
            if(maze.validNode(down)){
				if(!marked[down.getX()][down.getY()]){
					marked[down.getX()][down.getY()] = true;
					if(bfsPath[down.getX()][down.getY()] != 1) {
						frontier.add(down);
						nodesExpanded++;
					}
				}
			}
		}
		

		if(finish != null){
			this.cost += cost(finish);
			finish =  finish.getCaller();
		}
		else
			System.out.println("No path found by BFS");

        while(finish != null){
        	if(finish.getCaller() == null) {
        		bfsPath[finish.getX()][finish.getY()] = 2;
        		break;
        	} 
            bfsPath[finish.getX()][finish.getY()] = 4;
			this.cost += cost(finish);
            finish = finish.getCaller();
        }

		bfsPath[maze.getStart().getX()][maze.getStart().getY()] = 2;
        bfsPath[maze.getGoal().getX()][maze.getGoal().getY()] = 3;
		this.path = bfsPath;
	} 

	private static int cost(GridNode node){
		if(node.getCaller() == null)
			return 0;
        if(node.getCaller().getX() != node.getX()) return 2;
        else if(node.getCaller().getY() != node.getY()) return 1;
        else return 0;
    }
	
	public int getNodesExpanded() {
		return this.nodesExpanded;
	}

    public int getCost(){
        return this.cost;
    }

    public int[][] getPath(){
        return this.path;
    }
}
