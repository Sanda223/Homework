package Common;

import java.util.Objects;

public class Location {
    private final int x;
    private final int y;


    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int get_x_coord() { return x; }


    public int get_y_coord() { return y; }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Location location = (Location) obj;
        return x == location.x && y == location.y;
    }


    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }


    public static Location conversion(String s) {
        String[] parts = s.split(",");
        return new Location(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }
}