import java.util.*;

public class DFSLimit {

    public int[][] DFSPath(Grid maze){

		int[][] dfsPath = new int[maze.getSize()][maze.getSize()];
        boolean[][] marked = new boolean[maze.getSize()][maze.getSize()];
		for(int i = 0; i < maze.getSize(); i++){
			for(int j = 0; j < maze.getSize(); j++){
				dfsPath[i][j] = maze.getGrid()[i][j];
                marked[i][j] = false;
			}
		}

		Stack<GridNode> frontier = new Stack<GridNode>();
        Stack<Integer> depth = new Stack<Integer>();

		GridNode start = new GridNode(maze.getStart().getX(), maze.getStart().getY(), null);
		GridNode goal = new GridNode(maze.getGoal().getX(), maze.getGoal().getY(), null);
        int maxDepth = MaxDepth(maze.getSize());

		frontier.push(start);
        depth.push(0);
		marked[start.getX()][start.getY()] = true;

        GridNode finish = null;
		while(!frontier.isEmpty()){
			GridNode temp = frontier.pop();
            int currentDepth = depth.pop();
            if(currentDepth>maxDepth){
                continue;
            }

			if(temp.sameNode(goal)){
                finish = temp;
				break;
			}
            GridNode left = new GridNode(temp.getX() - 1, temp.getY(), temp);
			if(maze.validNode(left)){
				if(!marked[left.getX()][left.getY()]){
					marked[left.getX()][left.getY()] = true;
                    if(dfsPath[left.getX()][left.getY()] != 1){
                        depth.push(currentDepth+1);
                        frontier.push(left);
                    } 
				}
			}
            GridNode right = new GridNode(temp.getX() + 1, temp.getY(), temp);
			if(maze.validNode(right)){
				if(!marked[right.getX()][right.getY()]){
					marked[right.getX()][right.getY()] = true;
					if(dfsPath[right.getX()][right.getY()] != 1){
                        depth.push(currentDepth+1);
                        frontier.push(right);
                    } 
				}
			}
            GridNode up = new GridNode(temp.getX(), temp.getY() - 1, temp);
            if(maze.validNode(up)){
				if(!marked[up.getX()][up.getY()]){
					marked[up.getX()][up.getY()] = true;
					if(dfsPath[up.getX()][up.getY()] != 1){
                        depth.push(currentDepth+1);
                        frontier.push(up);
                    } 
				}
			}
            GridNode down = new GridNode(temp.getX(), temp.getY() + 1, temp);
            if(maze.validNode(down)){
				if(!marked[down.getX()][down.getY()]){
					marked[down.getX()][down.getY()] = true;
					if(dfsPath[down.getX()][down.getY()] != 1){
                        depth.push(currentDepth+1);
                        frontier.push(down);
                    } 
				}
			}
		}

        finish =  finish.getCaller();
        while(finish != null){
            dfsPath[finish.getX()][finish.getY()] = 4;
            finish = finish.getCaller();
        }


		return dfsPath;
	} 

    public int MaxDepth(int size){
        //currently returning size^2 so the algorithm is guaranteed to be complete.
        //can change function for MaxDepth if neccessary 
        return size*size;
    }

}
