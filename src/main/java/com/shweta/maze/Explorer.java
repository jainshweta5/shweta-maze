package com.shweta.maze;

import java.util.HashSet;
import java.util.Set;

public class Explorer {

	private Maze maze;
	public boolean[][] explored;

	public void markExplored(int x, int y, boolean visited) {
		if (!maze.validateCoordinates(x, y))
			throw new IllegalArgumentException("Coordinates x: " + x + ", y: " + y + " is not valid");
		explored[x][y] = visited;
		maze.getPath().addPath(maze.getLocation(x, y));
	}

	public boolean isExplored(final int x, final int y) {
		return explored[x][y];
	}

	public boolean[][] getExplored() {
		return explored;
	}

	public Explorer(Maze maze) {
		if (maze == null)
			throw new IllegalArgumentException("Maze not provided in explorer.");

		this.maze = maze;
	}

	public void exploreMaze() {
		explored = new boolean[maze.getHeight()][maze.getWidth()];
		Location startLocation = maze.getStartLocation();
		move(startLocation.getRow(), startLocation.getColumn());
	}

	public Location getPreviousExplored() {
		return maze.getPath().getPreviousExploredLocation();
	}

	private void move(final int row, final int col) {

		Location currentPosition = maze.getLocation(row, col);

		if (currentPosition.isExit()) {
			markExplored(row, col, true);
		} else {
			markExplored(row, col, true);
			Location nextLocation = turn(row, col);

			if (nextLocation != null) {
				move(nextLocation.getRow(), nextLocation.getColumn());
			} else {
				Location previousExplored = getPreviousExplored();
				if (previousExplored != null) {
					move(previousExplored.getRow(), previousExplored.getColumn());
				}
			}
		}
	}

	private Location turn(final int row, final int col) {
		Set<Location> openLocations = getMovementOptions(maze, row, col);

		int size = openLocations.size();
		if (size > 0) {
			return (Location) openLocations.toArray()[0];
		}
		return null;
	}

	public Set<Location> getMovementOptions(Maze maze, final int currentRow, final int currentCol) {
		Set<Location> openLocations = new HashSet<Location>();

		if (maze.getLocation(currentRow, currentCol - 1).isOpen() && !isExplored(currentRow, currentCol - 1)) {
			openLocations.add(maze.getLocation(currentRow, currentCol - 1));
		}

		if (maze.getLocation(currentRow - 1, currentCol).isOpen() && !isExplored(currentRow - 1, currentCol)) {
			openLocations.add(maze.getLocation(currentRow - 1, currentCol));
		}

		if (maze.getLocation(currentRow + 1, currentCol).isOpen() && !isExplored(currentRow + 1, currentCol)) {
			openLocations.add(maze.getLocation(currentRow + 1, currentCol));
		}

		if (maze.getLocation(currentRow, currentCol + 1).isOpen() && !isExplored(currentRow, currentCol + 1)) {
			openLocations.add(maze.getLocation(currentRow, currentCol + 1));
		}

		return openLocations;
	}
}