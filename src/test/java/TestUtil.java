import com.shweta.maze.Location;
import com.shweta.maze.LocationState;

public class TestUtil {
  
    /**
     * reset the exit location to any point chosen in the locations. This is for testing only.
     */
    public static void setExitLocation(Location[][] locations, Location existingExitPoint, int xOrdinal, int yOrdinal, LocationState state) {
        int yLength = locations[0].length - 1;
        if (xOrdinal < 0 || xOrdinal> locations.length - 1 || yOrdinal < 0 || yOrdinal > yLength) {
            return; 
        }

        int x = existingExitPoint.getRow();
        int y = existingExitPoint.getColumn();

        locations[x][y] = new Location(x, y, state);

        locations[xOrdinal][yOrdinal] = new Location(xOrdinal,yOrdinal, 'F');
     }

    public static void setLocation(Location[][] locations, int xOrdinal, int yOrdinal, LocationState state) {
        locations[xOrdinal][yOrdinal] = new Location(xOrdinal, yOrdinal, state);
    }    
}