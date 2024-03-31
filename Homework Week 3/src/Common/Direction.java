package Common;

public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST;


    public static Direction conversion(String arg) {
        switch (arg.toUpperCase()) {
            case "N":
                return NORTH;
            case "S":
                return SOUTH;
            case "E":
                return EAST;
            case "W":
                return WEST;
            default:
                throw new IllegalArgumentException("Direction must be one of n, s, e, w");
        }
    }


    public static Direction get_direction(Location from, Location to) {
        int xDiff = to.get_x_coord() - from.get_x_coord();
        int yDiff = to.get_y_coord() - from.get_y_coord();
        if (xDiff == 0 && yDiff == 0) {
            return null;
        }
        if (xDiff == 0) {
            return yDiff > 0 ? SOUTH : NORTH;
        }
        if (yDiff == 0) {
            return xDiff > 0 ? EAST : WEST;
        }
        return null;
    }
}