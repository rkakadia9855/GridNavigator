import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Grid {
	
	// 0 - represents a free space where the agent can move
	// 1 - represents a block where the agent cannot move
	// 2 - represents the starting point
	// 3 - represents the ending point
	// 4 - represents the free space used in the path
	private int[][] grid;
	private GridNode start;
	private GridNode goal;
	private int mazeSize;
	private ArrayList<ArrayList<Integer>> freePositions = new ArrayList<ArrayList<Integer>>();

	public Grid(File gridFile, int mazeSize, int[] start, int[] goal) throws FileNotFoundException {
		this.mazeSize = mazeSize;
		this.goal = new GridNode(goal[0], goal[1]);
		this.start = new GridNode(start[0], start[1]);
		buildGrid(gridFile);
	}
	
	public int[][] getGrid() {
		return this.grid;
	}
	
	public ArrayList<ArrayList<Integer>> getPositions() {
		return freePositions;
	}

	public int getSize(){
		return this.mazeSize;
	}
	
	private void buildGrid(File gridFile) throws FileNotFoundException {
		String gridToText = "";
		try (Scanner input = new Scanner(gridFile)) {
            while (input.hasNextLine()) {
                gridToText += input.nextLine() + "\n";
            }
        }
		grid = new int[mazeSize][mazeSize];
		String temp[] = gridToText.split("[\r]?\n");
		for(int i = 0; i < temp.length; i++) {
			try	(Scanner scn = new Scanner(temp[i])) {
				int x = scn.nextInt(); //row of an array
				int y = scn.nextInt(); //column of an array
				int val = scn.nextInt();
				grid[x][y] = val;
				if(val == 0) {
					ArrayList<Integer> tempList = new ArrayList<Integer>();
					tempList.add(x);
					tempList.add(y);
					freePositions.add(tempList);
				}
			}
		}
		if(start.getX() == -1)
			start = new GridNode(0, 0);
		if(goal.getX() == -1)
			goal = new GridNode(0,0);
		grid[start.getX()][start.getY()] = 2;
 		grid[goal.getX()][goal.getY()] = 3;
	}
	
	public boolean validNode(GridNode aNode) {
		if(aNode.getX() >= 0 && aNode.getX() < mazeSize
				&&
		   aNode.getY() >= 0 && aNode.getY() < mazeSize)
			return true;
		return false;
	}
	
	public GridNode getStart() {
		return this.start;
	}
	
	public GridNode getGoal() {
		return this.goal;
	}

	public static void drawPath(int[][] arr){

        //to increase or decrease size of image change length and width values here
        //the number corresponds to the number of pixels of the length and width of the image
      /*  int length = 500;
        int width = 500;
        int mazeLength = arr.length;
        int tileSize = length / mazeLength;

        Picture path = new Picture(length, width);
        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                path.set(i, j, Color.WHITE);
            }
        }
        Color color = Color.WHITE;

        for(int i = 0; i < mazeLength; i++){
            for(int j = 0; j < mazeLength; j++){
                if(arr[i][j] == 0) color = Color.WHITE;
                else if(arr[i][j] == 1) color = Color.BLACK;
                else if(arr[i][j] == 2) color = Color.GREEN;
                else if(arr[i][j] == 3) color = Color.RED;
                else if(arr[i][j] == 4) color = Color.YELLOW;

                for(int k = i*tileSize; k < (i*tileSize + tileSize); k++){
                    for(int l = j*tileSize; l < (j*tileSize + tileSize); l++){
                        path.set(k, l, color);
                    }
                }
            }
        }

        path.show(); */
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr.length; j++) {
				switch(arr[i][j]) {
					case 0:
						System.out.print("_ ");
						break;
					case 1:
						System.out.print("\u001B[32m" + "# " + "\u001B[0m");
						break;
					case 2:
						System.out.print("\u001B[31m" + "S " + "\u001B[0m");
						break;
					case 3: 
						System.out.print("\u001B[31m" +  "E " + "\u001B[0m");
						break;
					default:
						System.out.print("\u001B[33m" + "* " + "\u001B[0m");
				}
			}
			System.out.println();
		} 
    }
	
}
