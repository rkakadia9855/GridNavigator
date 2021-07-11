import java.util.*;

public class BFS {

    public int[][] BFSPath(Grid maze){

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
                    if(bfsPath[left.getX()][left.getY()] != 1) frontier.add(left);
				}
			}
            GridNode right = new GridNode(temp.getX() + 1, temp.getY(), temp);
			if(maze.validNode(right)){
				if(!marked[right.getX()][right.getY()]){
					marked[right.getX()][right.getY()] = true;
					if(bfsPath[right.getX()][right.getY()] != 1) frontier.add(right);
				}
			}
            GridNode up = new GridNode(temp.getX(), temp.getY() - 1, temp);
            if(maze.validNode(up)){
				if(!marked[up.getX()][up.getY()]){
					marked[up.getX()][up.getY()] = true;
					if(bfsPath[up.getX()][up.getY()] != 1) frontier.add(up);
				}
			}
            GridNode down = new GridNode(temp.getX(), temp.getY() + 1, temp);
            if(maze.validNode(down)){
				if(!marked[down.getX()][down.getY()]){
					marked[down.getX()][down.getY()] = true;
					if(bfsPath[down.getX()][down.getY()] != 1) frontier.add(down);
				}
			}
		}

        finish =  finish.getCaller();
        while(finish != null){
            bfsPath[finish.getX()][finish.getY()] = 4;
            finish = finish.getCaller();
        }


		return bfsPath;
	} 
}
