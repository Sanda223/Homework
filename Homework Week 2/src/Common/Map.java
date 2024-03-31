package Common;

import Obstacles.Obstacle;

import java.util.ArrayList;


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
        Location topLeft, bottomRight;
        int maxX, maxY, minX, minY;
        maxX = Math.max(start.get_x_coord(), target.get_x_coord());
        maxY = Math.max(start.get_y_coord(), target.get_y_coord());
        minX = Math.min(start.get_x_coord(), target.get_x_coord());
        minY = Math.min(start.get_y_coord(), target.get_y_coord());
        topLeft = new Location(minX - PADDING, minY - PADDING);
        bottomRight = new Location(maxX + PADDING, maxY + PADDING);


        char[][] solvedMap = new char[bottomRight.get_y_coord() - topLeft.get_y_coord() + 1][bottomRight.get_x_coord() - topLeft.get_x_coord() + 1];
        for (int y = topLeft.get_y_coord(); y <= bottomRight.get_y_coord(); y++) {
            for (int x = topLeft.get_x_coord(); x <= bottomRight.get_x_coord(); x++) {
                if (x == start.get_x_coord() && y == start.get_y_coord()) {
                    solvedMap[y - topLeft.get_y_coord()][x - topLeft.get_x_coord()] = 'S';
                    continue;
                }
                if (x == target.get_x_coord() && y == target.get_y_coord()) {
                    solvedMap[y - topLeft.get_y_coord()][x - topLeft.get_x_coord()] = 'E';
                    continue;
                }

                Obstacle obstructing_obstacle = get_obstacle_at_location(x, y);
                int j = y - topLeft.get_y_coord();
                int i = x - topLeft.get_x_coord();
                if (obstructing_obstacle != null) {
                    solvedMap[j][i] = obstructing_obstacle.get_symbol();
                    continue;
                }


                solvedMap[j][i] = '.';
            }
        }


        return matrix_to_string(solvedMap);
    }
}