package com.shweta.maze;

import java.util.ArrayList;
import java.util.List;

public class Maze {

    private Location[][] locations;
    private Path path;
    private int height;
    private int width;

    public Maze(final Location[][] locations) {
        if (locations == null)
            throw new IllegalArgumentException("Cannot have empty locations in Maze");
        this.locations = locations;
        this.path = new Path();
        this.height = locations.length;
        this.width = locations[0].length;
    }

    public Path getPath() {
        return path;
    }

    public Location[][] getLocations() {
        return this.locations;
    }

    public Location getLocation(final int x, final int y) {
        if (!validateCoordinates(x, y))
            return null;
        return locations[x][y];
    }

    public Location getExitLocation() {
        List<Location> exits = findLocation(LocationState.EXIT);
        return exits.isEmpty() ? null : exits.get(0);
    }

    public boolean validateCoordinates(int row, int col) {
        return !(row < 0 || row >= getHeight() || col < 0 || col >= getWidth());
    }

    public Location getStartLocation() {
        List<Location> starts = findLocation(LocationState.START);
        return starts.isEmpty() ? null : starts.get(0);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getOpenSpacesCount() {
        return findLocation(LocationState.OPEN).size();
    }

    public int getWallCount() {
        return findLocation(LocationState.WALLED).size();
    }

    public int getStartCount() {
        return findLocation(LocationState.START).size();
    }

    public int getExitCount() {
        return findLocation(LocationState.EXIT).size();
    }

    private List<Location> findLocation(final LocationState state) {
        List<Location> results = new ArrayList<Location>();
        for (int i = 0 ; i < getHeight(); i ++) {
            for (int j = 0; j < getWidth(); j++) {
                Location s = getLocation(i, j);
                if (s.getState() == state)
                    results.add(s);
            }
        }
        return results;
    }
}