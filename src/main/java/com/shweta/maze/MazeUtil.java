package com.shweta.maze;

import java.util.Stack;

public class MazeUtil {

    public static Location findLocationByState(Location[][] locations, final LocationState state) {

        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[0].length; j++) {
                Location s = locations[i][j];
                if (s.getState() == state)
                    return s;
            }
        }
        return null;
    }
    
    public static Location getStartLocationFromPath(Maze maze){
    	Stack<Location> locations = maze.getPath().getPathLocations();
    	for(Location location: locations){
    	if (location.getState() == LocationState.START)
            return location;
    	}
    	return null;
    }   
}