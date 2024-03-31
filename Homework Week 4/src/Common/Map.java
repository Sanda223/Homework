package Common;
import Obstacles.Obstacle;
import java.util.ArrayList;
import Path_Finding.*;


public class Map {
    private final ArrayList<Obstacle> obstacles = new ArrayList<>();
    private final int PADDING = 2;


    public Map(ArrayList<Obstacle> obstacles) {
        this.obstacles.addAll(obstacles);
    }


    private Obstacle get_obstacle_at_location(int x, int y) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.is_location_blocked(x, y)) {
                return obstacle;
            }
        }
        return null;
    }


    private String matrix_to_string(char[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (char[] row : matrix) {
            for (char symbol : row) {
                sb.append(symbol);
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    public String get_solved_map(Location start, Location target) {
        grid_path_finder pathFinder = new BFSPathFinder(this);
        Path path = pathFinder.find_path(start, target);

        Location topLeft, bottomRight;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE,
                minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        for (Location location : path) {
            int x = location.get_x_coord();
            int y = location.get_y_coord();
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
        }
        topLeft = new Location(minX - PADDING, minY - PADDING);
        bottomRight = new Location(maxX + PADDING, maxY + PADDING);


        char[][] solvedMap = new char[bottomRight.get_y_coord() - topLeft.get_y_coord() + 1][bottomRight.get_x_coord() - topLeft.get_x_coord() + 1];
        for (int y = topLeft.get_y_coord(); y <= bottomRight.get_y_coord(); y++) {
            for (int x = topLeft.get_x_coord(); x <= bottomRight.get_x_coord(); x++) {
                if (path.is_location_in_path(x, y)) {
                    solvedMap[y - topLeft.get_y_coord()][x - topLeft.get_x_coord()] = path.get_symbol_for_location(x, y);
                    continue;
                }

                // 2. Check obstruction
                Obstacle obstructing_obstacle = get_obstacle_at_location(x, y);
                // Calculate the index in the map 2D array
                int j = y - topLeft.get_y_coord();
                int i = x - topLeft.get_x_coord();
                if (obstructing_obstacle != null) {
                    solvedMap[j][i] = obstructing_obstacle.get_symbol();
                    continue;
                }

                // 3. Empty space
                solvedMap[j][i] = '.';
            }
        }

        // Convert the map to a string
        return matrix_to_string(solvedMap);
    }

    public boolean is_location_blocked(int x, int y) {
        return get_obstacle_at_location(x, y) != null;
    }
}