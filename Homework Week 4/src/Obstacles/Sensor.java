package Obstacles;
import Common.Location;


public class Sensor extends Locatable_Obstacle {
    private final double range;

    public Sensor(Location location, double range) {
        super(location);
        this.range = range;
    }
    @Override
    public boolean is_location_blocked(int x, int y) {
        double distance = Math.sqrt(Math.pow(location.get_x_coord() - x, 2) + Math.pow(location.get_y_coord() - y, 2));
        return distance <= range;
    }
    @Override
    public char get_symbol() {
        return ObstacleType.SENSOR.get_symbol();
    }

    public static Sensor conversion(String arg) {
        String[] parts = arg.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Sensor must have 3 inputs (x, y, range)");
        }
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        double range = Double.parseDouble(parts[2]);
        Location location = new Location(x, y);
        return new Sensor(location, range);
    }
}
