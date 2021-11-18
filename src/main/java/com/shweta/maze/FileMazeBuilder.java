package com.shweta.maze;

import java.io.File;

public class FileMazeBuilder implements MazeBuilder {

	private File file;

	public FileMazeBuilder(String file) {
		if (file.isEmpty()) {
			throw new IllegalArgumentException("File cannot be null");
		}
		this.file = new File(file);
	}

	public Maze build() {
		try {
			Location[][] locations = buildMazeLocations(file);
			return new Maze(locations);
		} catch (IllegalArgumentException exp) {
			throw new IllegalArgumentException(exp.getMessage());
		}
	}

	public Location[][] buildMazeLocations(String[] mazeLines) {
		int height = mazeLines.length;
		int width = mazeLines[0].length();
		int startCount = 0;
		int exitCount = 0;
		Location[][] locations = new Location[height][width];

		for (int row = 0; row < height; row++) {
			if (mazeLines[row].length() != width) {
				throw new IllegalArgumentException(
						"line " + (row + 1) + " wrong length " + mazeLines[row].length() + ", should be " + width);
			}

			for (int col = 0; col < width; col++) {
				Location location = new Location(row, col, mazeLines[row].charAt(col));
				locations[row][col] = location;
				if (location.isStart())
					startCount++;

				if (location.isExit())
					exitCount++;
			}
		}

		if (startCount != 1 || exitCount != 1)
			throw new IllegalArgumentException(
					"Invalid location data - should have one and only one Start point 'S' and one and only one exit 'F'");

		return locations;
	}

	public Location[][] buildMazeLocations(File file) {
		if (file == null || !file.exists())
			throw new IllegalArgumentException("File cannot be found.");
		return buildMazeLocations(MazeFileUtil.convertFileToArrayString(file));
	}
}