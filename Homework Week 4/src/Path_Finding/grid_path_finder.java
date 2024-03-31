package Path_Finding;

import Common.Location;

public interface grid_path_finder {

    Path find_path(Location startLocation, Location endLocation);


    Iterable<Location> get_neighbors(Location location);
}