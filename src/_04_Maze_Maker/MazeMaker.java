package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.security.auth.x500.X500Principal;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start
		selectNextPath(maze.cells[randGen.nextInt(w)][randGen.nextInt(h)]);

		// 5. call selectNextPath method with the randomly selected cell ^^

		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.setBeenVisited(true);
		// B. Get an ArrayList of unvisited neighbors using the current cell and the
		// method below
		ArrayList<Cell> unvisitedNeighbor = getUnvisitedNeighbors(currentCell);
		// C. if has unvisited neighbors,
		if (unvisitedNeighbor.size() >= 1) {

			// C1. select one at random.
			int x = randGen.nextInt(unvisitedNeighbor.size());
			uncheckedCells.push(unvisitedNeighbor.get(x));
			// C2. push it to the stack

			// C3. remove the wall between the two cells
			removeWalls(currentCell, unvisitedNeighbor.get(x));
			// C4. make the new cell the current cell and mark it as visited
			currentCell = unvisitedNeighbor.get(x);
			currentCell.setBeenVisited(true);;
			// C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		// D. if all neighbors are visited
		else if (unvisitedNeighbor.size() == 0) {

			// D1. if the stack is not empty
			if (uncheckedCells.size() != 0) {

				// D1a. pop a cell from the stack
				currentCell = uncheckedCells.pop();
				// D1b. make that the current cell

				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}
	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {

		if (c1.getX() == c2.getX() + 1) {
			c2.setEastWall(false);
			c1.setWestWall(false);
		}
		if (c1.getX() == c2.getX() - 1) {
			c1.setEastWall(false);
			c2.setWestWall(false);

		}
		if (c1.getY() == c2.getY() + 1) {
			c1.setNorthWall(false);
			c2.setSouthWall(false);
		}
		if (c1.getY() == c2.getY() - 1) {
			c2.setNorthWall(false);
			c1.setSouthWall(false);
		}
	}

	// here below
	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> cells = new ArrayList<Cell>();
		int Hmax = height - 1;
		int Wmax = width - 1;
		if (c.getX() < Wmax) {
			
			if (maze.cells[c.getX() + 1][c.getY()].hasBeenVisited() == false) {
				cells.add(maze.cells[c.getX() + 1][c.getY()]);
			}
		}
		if (c.getX() > 0) {
			if (maze.cells[c.getX() - 1][c.getY()].hasBeenVisited() == false) {
				cells.add(maze.cells[c.getX() - 1][c.getY()]);
			}
			
		}
		if (c.getY() < Hmax) {
			if (maze.cells[c.getX()][c.getY() + 1].hasBeenVisited() == false) {
				cells.add(maze.cells[c.getX()][c.getY() + 1]);
			}
		}
		if (c.getY() > 0) {
			if (maze.cells[c.getX()][c.getY() - 1].hasBeenVisited() == false) {
				cells.add(maze.cells[c.getX()][c.getY() - 1]);
			}
		}
		return cells;
	}
}
/**
 * 
 *  
 *  
 **/
