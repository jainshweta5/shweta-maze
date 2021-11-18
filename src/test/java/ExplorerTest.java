import com.shweta.maze.Explorer;
import com.shweta.maze.FileMazeBuilder;
import com.shweta.maze.Location;
import com.shweta.maze.LocationState;
import com.shweta.maze.Maze;
import com.shweta.maze.MazeBuilder;
import com.shweta.maze.MazeUtil;

import java.util.Stack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ExplorerTest {

    private Maze maze;
    private Explorer mazeExplorer;

    @BeforeEach
    public void setup() {
        resetMaze();
    }

    @Test
    public void testExplorerMazeNullSafety() {
        try {
            new Explorer(null);
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals(e.getMessage(), "Maze not provided in explorer");
        }
    }

    @Test
    public void testExplorerCanBeDroppedAtStartPoint() {
        Location startLocationInMaze = maze.getStartLocation();
        Assertions.assertEquals(3, startLocationInMaze.getRow());
        Assertions.assertEquals(3, startLocationInMaze.getColumn());

        mazeExplorer.exploreMaze();

        Location startLocationInExploredPath = MazeUtil.getStartLocationFromPath(maze);
        Assertions.assertEquals(3, startLocationInExploredPath.getRow());
        Assertions.assertEquals(3, startLocationInExploredPath.getColumn());

        Assertions.assertEquals(startLocationInExploredPath, startLocationInMaze);
    }

    @Test
    public void  testExploredPathDoesnotHaveWallInBetween(){
    	
    	boolean isWalled = false;
    	    	
    	Stack<Location> locations = maze.getPath().getPathLocations();
    	for(Location location : locations){
    		if(location.getState().equals(LocationState.WALLED)){
    		 	isWalled = true;
    		 	break;
    		}
    	}
        Assertions.assertEquals(false, isWalled);
    }
    
    @Test
    public void testExplorerCanMove() {
        TestUtil.setExitLocation(maze.getLocations(), maze.getExitLocation(), 5, 11, LocationState.WALLED);
        mazeExplorer.exploreMaze();

        Assertions.assertEquals(11, maze.getPath().getPathLocations().size());
        Assertions.assertTrue(maze.getPath().getPathLocations().contains(new Location(3, 11, LocationState.OPEN))); // last straight location
        Assertions.assertTrue(maze.getPath().getPathLocations().contains(new Location(4, 11, LocationState.OPEN))); // first location after turned.
    }

    @Test
    public void testExplorerKeepsRecordOfExploredPath() {
    	mazeExplorer.exploreMaze();
        Assertions.assertNotNull(mazeExplorer.getExplored());
        Assertions.assertTrue(mazeExplorer.getExplored().length > 0);
    }


    private void resetMaze() {
        MazeBuilder builder = new FileMazeBuilder("src/test/resources/ExampleMaze.txt");
        maze = builder.build();
        mazeExplorer = new Explorer(maze);
    }
}