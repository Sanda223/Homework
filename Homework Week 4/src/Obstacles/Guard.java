package Obstacles;
import Common.Location;

public class Guard extends Locatable_Obstacle {

    public Guard(Location location) {
        super(location);
    }

    @Override
    public boolean is_location_blocked(int x, int y) {
        return location.get_x_coord() == x && location.get_y_coord() == y;
    }

    @Override
    public char get_symbol() {
        return ObstacleType.GUARD.get_symbol();
    }


    public static Guard conversion(String arg) {
        Location location = Location.conversion(arg);
        return new Guard(location);
    }
}