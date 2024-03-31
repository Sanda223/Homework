package Path_Finding;
import Common.*;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;


public class Path implements Iterable<Location> {
    private List<Location> inner;

    public Path(List<Location> inner) {
        this.inner = inner;
    }


    public boolean is_location_in_path(int x, int y) {
        return inner.contains(new Location(x, y));
    }


    public char get_symbol_for_location(int x, int y) {
        Location location = new Location(x, y);
        int locationIndex = inner.indexOf(location);
        if (locationIndex == 0) {
            return 'S';
        }
        if (locationIndex == inner.size() - 1) {
            return 'E';
        }
        Location previousLocation = inner.get(locationIndex - 1);
        Location nextLocation = inner.get(locationIndex + 1);
        Direction directionFrom = Direction.get_direction(previousLocation, location);
        Direction directionTo = Direction.get_direction(location, nextLocation);
        return get_symbol_from_directions(directionFrom, directionTo);
    }


    private char get_symbol_from_directions(Direction from, Direction to) {
        if (from == to) {
            switch (from) {
                case NORTH, SOUTH:
                    return '║';
                case EAST, WEST:
                    return '═';
                default:
                    break;
            }
        }
        if (from == Direction.SOUTH && to == Direction.EAST ||
                from == Direction.WEST && to == Direction.NORTH) {
            return '╚';
        }
        if (from == Direction.SOUTH && to == Direction.WEST ||
                from == Direction.EAST && to == Direction.NORTH) {
            return '╝';
        }
        if (from == Direction.NORTH && to == Direction.EAST ||
                from == Direction.WEST && to == Direction.SOUTH) {
            return '╔';
        }
        if (from == Direction.NORTH && to == Direction.WEST ||
                from == Direction.EAST && to == Direction.SOUTH) {
            return '╗';
        }
        return ' ';
    }

    @Override
    public Iterator<Location> iterator() {
        return inner.iterator();
    }

    @Override
    public void forEach(Consumer<? super Location> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Location> spliterator() {
        return Iterable.super.spliterator();
    }
}