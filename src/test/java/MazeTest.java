import com.shweta.maze.FileMazeBuilder;
import com.shweta.maze.Location;
import com.shweta.maze.Maze;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeTest {

    private Maze maze;

    @BeforeEach
    public void before() {
        this.maze = new FileMazeBuilder("src/test/resources/ExampleMaze.txt").build();
    }

    @Test
    public void testMazeNullValueShouldThrowException() {
        try {
            this.maze = new FileMazeBuilder("NOT_EXIST").build();
        } catch (IllegalArgumentException s) {
            Assertions.assertEquals(s.getMessage(), ("File cannot be found."));
        }
    }

    @Test
    public void testMazeNullSafety() {
        Assertions.assertNotNull(maze);
        Assertions.assertNotNull(maze.getLocations());
    }
    
    @Test
    public void testMazeContainsOnlyOneStart() {
        Assertions.assertEquals( 1, maze.getStartCount(), "Maze should contain only one start");
    }
    
    @Test
    public void testMazeContainsOnlyOneExit() {
    	 Assertions.assertEquals( 1, maze.getExitCount(), "Maze should contain only one exit");
    }

    @Test
    public void testMazeAvailableToAccess() {
        Assertions.assertNotNull(maze);
        int startCount = 0;
        int exitCount = 0;
        for (int i = 0 ; i < maze.getHeight(); i ++) {
            for (int j = 0; j < maze.getWidth(); j ++) {
                Location s = maze.getLocation(i, j);
                Assertions.assertNotNull(s);
                Assertions.assertTrue(s.isValidRepresentation());

                if (s.isStart()) {
                    startCount ++;
                }

                if (s.isExit()) {
                    exitCount ++;
                }
            }
        }
        Assertions.assertEquals(1, startCount);
        Assertions.assertEquals(1, exitCount);
    }

    @Test
    public void testMazeHeightAndWidth() {
        Assertions.assertNotNull(maze);
        Assertions.assertEquals(15, maze.getHeight());
        Assertions.assertEquals(15, maze.getWidth());
    }

    @Test
    public void testOpenSpacesCount() {
        Assertions.assertEquals(74, maze.getOpenSpacesCount());
    }

    @Test
    public void testWalledCount() {
        Assertions.assertEquals(15 * 15 - 74 - 2, maze.getWallCount());
    }

    @Test
    public void testOutOfRangeCoordinates() {
        try {
            Assertions.assertNull(maze.getLocation(-1, -1));
            Assertions.assertNull(maze.getLocation(20, -1));
            Assertions.assertNull(maze.getLocation(20, 20));
            Assertions.assertNull(maze.getLocation(-1, 20));
        } catch (ArrayIndexOutOfBoundsException ae) {
            Assertions.fail("Coordinates are out of supported range.");
        }
    }

    @Test
    public void testWalledCoordinate() {
        Assertions.assertNotNull(maze.getLocation(0,0));
        Assertions.assertTrue(maze.getLocation(0,0).isWalled());
        Assertions.assertFalse(maze.getLocation(0,0).isOpen());
        Assertions.assertFalse(maze.getLocation(0,0).isExit());
        Assertions.assertFalse(maze.getLocation(0,0).isStart());
    }

    @Test
    public void testStartCoordinate() {
        Assertions.assertNotNull(maze.getLocation(3,3));
        Assertions.assertTrue(maze.getLocation(3,3).isStart());
        Assertions.assertTrue(maze.getLocation(3,3).isOpen());
        Assertions.assertFalse(maze.getLocation(3,3).isExit());
        Assertions.assertFalse(maze.getLocation(3,3).isWalled());
    }

    @Test
    public void testOpenCoordinate() {
        Assertions.assertNotNull(maze.getLocation(1,1));
        Assertions.assertTrue(maze.getLocation(1,1).isOpen());
        Assertions.assertFalse(maze.getLocation(1,1).isWalled());
        Assertions.assertFalse(maze.getLocation(1,1).isExit());
        Assertions.assertFalse(maze.getLocation(1,1).isStart());
    }

    @Test
    public void testExitCoordinate() {
        Assertions.assertNotNull(maze.getLocation(14,1));
        Assertions.assertTrue(maze.getLocation(14,1).isExit());
        Assertions.assertTrue(maze.getLocation(14,1).isOpen());
        Assertions.assertFalse(maze.getLocation(14,1).isWalled());
        Assertions.assertFalse(maze.getLocation(14,1).isStart());
    }
}
