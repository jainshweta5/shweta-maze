import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;
import com.shweta.maze.FileMazeBuilder;
import com.shweta.maze.Location;
import com.shweta.maze.Maze;

public class MazeTest {

    private Maze maze;

    @Before
    public void before() {
        this.maze = new FileMazeBuilder("src/test/resources/ExampleMaze.txt").build();
    }

    @Test
    public void testMazeNullValueShouldThrowException() {
        try {
            this.maze = new FileMazeBuilder("NOT_EXIST").build();
        } catch (IllegalArgumentException s) {
            assertThat(s.getMessage(), containsString("File cannot be found."));
        }
    }

    @Test
    public void testMazeNullSafety() {
        Assert.assertNotNull(maze);
        Assert.assertNotNull(maze.getLocations());
    }
    
    @Test
    public void testMazeContainsOnlyOneStart() {
    	 Assert.assertEquals("Maze should contain only one start", 1, maze.getStartCount());
    }
    
    @Test
    public void testMazeContainsOnlyOneExit() {
    	 Assert.assertEquals("Maze should contain only one exit", 1, maze.getExitCount());
    }

    @Test
    public void testMazeAvailableToAccess() {
        Assert.assertNotNull(maze);
        int startCount = 0;
        int exitCount = 0;
        for (int i = 0 ; i < maze.getHeight(); i ++) {
            for (int j = 0; j < maze.getWidth(); j ++) {
                Location s = maze.getLocation(i, j);
                Assert.assertNotNull(s);
                Assert.assertTrue(s.isValidRepresentation());

                if (s.isStart()) {
                    startCount ++;
                }

                if (s.isExit()) {
                    exitCount ++;
                }
            }
        }
        Assert.assertEquals(1, startCount);
        Assert.assertEquals(1, exitCount);
    }

    @Test
    public void testMazeHeightAndWidth() {
        Assert.assertNotNull(maze);
        Assert.assertEquals(15, maze.getHeight());
        Assert.assertEquals(15, maze.getWidth());
    }

    @Test
    public void testOpenSpacesCount() {
        Assert.assertEquals(74, maze.getOpenSpacesCount());
    }

    @Test
    public void testWalledCount() {
        Assert.assertEquals(15 * 15 - 74 - 2, maze.getWallCount());
    }

    @Test
    public void testOutOfRangeCoordinates() {
        try {
            Assert.assertNull(maze.getLocation(-1, -1));
            Assert.assertNull(maze.getLocation(20, -1));
            Assert.assertNull(maze.getLocation(20, 20));
            Assert.assertNull(maze.getLocation(-1, 20));
        } catch (ArrayIndexOutOfBoundsException ae) {
            Assert.fail("Coordinates are out of supported range.");
        }
    }

    @Test
    public void testWalledCoordinate() {
        Assert.assertNotNull(maze.getLocation(0,0));
        Assert.assertTrue(maze.getLocation(0,0).isWalled());
        Assert.assertFalse(maze.getLocation(0,0).isOpen());
        Assert.assertFalse(maze.getLocation(0,0).isExit());
        Assert.assertFalse(maze.getLocation(0,0).isStart());
    }

    @Test
    public void testStartCoordinate() {
        Assert.assertNotNull(maze.getLocation(3,3));
        Assert.assertTrue(maze.getLocation(3,3).isStart());
        Assert.assertTrue(maze.getLocation(3,3).isOpen());
        Assert.assertFalse(maze.getLocation(3,3).isExit());
        Assert.assertFalse(maze.getLocation(3,3).isWalled());
    }

    @Test
    public void testOpenCoordinate() {
        Assert.assertNotNull(maze.getLocation(1,1));
        Assert.assertTrue(maze.getLocation(1,1).isOpen());
        Assert.assertFalse(maze.getLocation(1,1).isWalled());
        Assert.assertFalse(maze.getLocation(1,1).isExit());
        Assert.assertFalse(maze.getLocation(1,1).isStart());
    }

    @Test
    public void testExitCoordinate() {
        Assert.assertNotNull(maze.getLocation(14,1));
        Assert.assertTrue(maze.getLocation(14,1).isExit());
        Assert.assertTrue(maze.getLocation(14,1).isOpen());
        Assert.assertFalse(maze.getLocation(14,1).isWalled());
        Assert.assertFalse(maze.getLocation(14,1).isStart());
    }
}