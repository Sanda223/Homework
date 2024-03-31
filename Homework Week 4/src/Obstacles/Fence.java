package Obstacles;
import Common.Location;


public class Fence extends Locatable_Obstacle {
    private final Location end;


    public Fence(Location start, Location end) {
        super(start);
        this.end = end;
        if (!isAxial()) {
            throw new IllegalArgumentException("Fence must be vertical or horizontal");
        }
    }

    private boolean isAxial() {
        return location.get_x_coord() == end.get_x_coord() || location.get_y_coord() == end.get_y_coord();
    }

    @Override
    public boolean is_location_blocked(int x, int y) {
        int xDiffEnd = x - end.get_x_coord();
        int yDiffEnd = y - end.get_y_coord();
        int xDiffStart = x - location.get_x_coord();
        int yDiffStart = y - location.get_y_coord();

        return xDiffEnd * xDiffStart <= 0 && yDiffEnd * yDiffStart <= 0;
    }

    @Override
    public char get_symbol() {
        return ObstacleType.FENCE.get_symbol();
    }


    public static Fence conversion(String arg) {
        String[] parts = arg.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Fence must have 4 coordinates: startX,startY,endX,endY");
        }
        int startX = Integer.parseInt(parts[0]);
        int startY = Integer.parseInt(parts[1]);
        int endX = Integer.parseInt(parts[2]);
        int endY = Integer.parseInt(parts[3]);
        Location start = new Location(startX, startY);
        Location end = new Location(endX, endY);
        return new Fence(start, end);
    }
}