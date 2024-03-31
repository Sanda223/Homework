package Obstacles;


public interface Obstacle {

    char get_symbol();


    boolean is_location_blocked(int x, int y);
}