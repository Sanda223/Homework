package Obstacles;

import Common.Location;


public abstract class Locatable_Obstacle implements Obstacle {
    protected final Location location;


    public Locatable_Obstacle(Location location) {
        this.location = location;
    }


    public Locatable_Obstacle(int x, int y) {
        this.location = new Location(x, y);
    }

    public Location get_location() {
        return location;
    }

    public abstract boolean is_location_blocked(int x, int y);
    public abstract char get_symbol();
}