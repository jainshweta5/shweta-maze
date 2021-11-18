package com.shweta.maze;

import java.util.Stack;

public class Path {

    private Stack<Location> paths;

    public Path() {
        paths = new Stack<Location>();
    }

    public Location getPreviousExploredLocation() {
       if (!paths.isEmpty()) 
            paths.pop();
        return paths.isEmpty() ? null : paths.pop();
    }

    public void addPath(Location location) {
        paths.push(location);
    }

    public Stack<Location> getPathLocations() {
        return paths;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Location l : paths) {
            builder.append("X" + l.getRow() + ":Y" + l.getColumn());
        }
        return builder.toString();
    }
}