import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.shweta.maze.Explorer;
import com.shweta.maze.FileMazeBuilder;
import com.shweta.maze.Location;
import com.shweta.maze.LocationState;
import com.shweta.maze.Maze;
import com.shweta.maze.MazeBuilder;
import com.shweta.maze.MazeUtil;

import java.util.Stack;

import static org.hamcrest.Matchers.containsString;

public class ExplorerTest {

    private Maze maze;
    private Explorer mazeExplorer;

    @Before
    public void before() {
        resetMaze();
    }

    @Test
    public void testExplorerMazeNullSafety() {
        try {
            new Explorer(null);
        } catch (IllegalArgumentException e) {
            Assert.assertThat(e.getMessage(), containsString("Maze not provided in explorer"));
        }
    }

    @Test
    public void testExplorerCanBeDroppedAtStartPoint() {
        Location startLocationInMaze = maze.getStartLocation();
        Assert.assertEquals(3, startLocationInMaze.getRow());
        Assert.assertEquals(3, startLocationInMaze.getColumn());

        mazeExplorer.exploreMaze();

        Location startLocationInExploredPath = MazeUtil.getStartLocationFromPath(maze);
        Assert.assertEquals(3, startLocationInExploredPath.getRow());
        Assert.assertEquals(3, startLocationInExploredPath.getColumn());

        Assert.assertEquals(startLocationInExploredPath, startLocationInMaze);
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
    	Assert.assertEquals(false, isWalled);
    }
    
    @Test
    public void testExplorerCanMove() {
        TestUtil.setExitLocation(maze.getLocations(), maze.getExitLocation(), 5, 11, LocationState.WALLED);
        mazeExplorer.exploreMaze();

        Assert.assertEquals(11, maze.getPath().getPathLocations().size());
        Assert.assertTrue(maze.getPath().getPathLocations().contains(new Location(3, 11, LocationState.OPEN))); // last straight location
        Assert.assertTrue(maze.getPath().getPathLocations().contains(new Location(4, 11, LocationState.OPEN))); // first location after turned.
    }

    @Test
    public void testExplorerKeepsRecordOfExploredPath() {
    	mazeExplorer.exploreMaze();
        Assert.assertNotNull(mazeExplorer.getExplored());
        Assert.assertTrue(mazeExplorer.getExplored().length > 0);
    }


    private void resetMaze() {
        MazeBuilder builder = new FileMazeBuilder("src/test/resources/ExampleMaze.txt");
        maze = builder.build();
        mazeExplorer = new Explorer(maze);
    }
}