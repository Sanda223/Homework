package Path_Finding;
import Common.*;
import Common.Map;
import java.util.*;


public class BFSPathFinder implements grid_path_finder {
    private Map map;

    public BFSPathFinder(Map map) {
        this.map = map;
    }

    @Override
    public Path find_path(Location startLocation, Location endLocation) {
        Queue<Location> queue = new LinkedList<>();
        ArrayList<Location> visited = new ArrayList<>();
        HashMap<Location, Location> previous = new HashMap<>();

        queue.add(startLocation);
        while (!queue.isEmpty()) {
            Location current = queue.remove();
            if (current.equals(endLocation)) {
                break;
            }
            if (!visited.contains(current)) {
                visited.add(current);
                Iterable<Location> neighbors = get_neighbors(current);
                for (Location neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        previous.put(neighbor, current);
                        queue.add(neighbor);
                    }
                }
            }
        }

        List<Location> path = new Stack<>();
        Location current = endLocation;
        while (previous.containsKey(current)) {
            path.add(current);
            current = previous.get(current);
        }
        path.add(startLocation);
        Collections.reverse(path);
        return new Path(path);
    }

    @Override
    public Iterable<Location> get_neighbors(Location location) {
        ArrayList<Location> neighbors = new ArrayList<>();
        int x = location.get_x_coord();
        int y = location.get_y_coord();

        if (!map.is_location_blocked(x - 1, y)) {
            neighbors.add(new Location(x - 1, y));
        }
        if (!map.is_location_blocked(x + 1, y)) {
            neighbors.add(new Location(x + 1, y));
        }
        if (!map.is_location_blocked(x, y - 1)) {
            neighbors.add(new Location(x, y - 1));
        }
        if (!map.is_location_blocked(x, y + 1)) {
            neighbors.add(new Location(x, y + 1));
        }
        return neighbors;
    }
}