package Obstacles;
import Common.*;


public class Camera extends Locatable_Obstacle {
    private final Direction direction;

    public Camera(Location location, Direction direction) {
        super(location);
        this.direction = direction;
    }

    @Override
    public boolean is_location_blocked(int x, int y) {
        int xDiff = x - location.get_x_coord();
        int yDiff = y - location.get_y_coord();
        switch (direction) {
            case NORTH:
                return Math.abs(xDiff) <= -yDiff;
            case EAST:
                return Math.abs(yDiff) <= xDiff;
            case SOUTH:
                return Math.abs(xDiff) <= yDiff;
            case WEST:
                return Math.abs(yDiff) <= -xDiff;
            default:
                throw new IllegalStateException("Camera direction must be one of NORTH, EAST, SOUTH, WEST");
        }
    }

    @Override
    public char get_symbol() {
        return ObstacleType.CAMERA.get_symbol();
    }

    public static Camera conversion(String arg) {
        String[] parts = arg.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Camera must have 3 inputs (x, y, direction)");
        }
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        Direction direction = Direction.conversion(parts[2]);
        Location location = new Location(x, y);
        return new Camera(location, direction);
    }
}
