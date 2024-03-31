import java.util.ArrayList;
import java.util.HashMap;
import Obstacles.*;
import Common.*;

class Main {
    public static void main(String[] args) {

        HashMap<String, ArrayList<String>> parsedArgs = val_conversion(args);
        if (parsedArgs == null) {
            System.out.println("No arguments provided.");
            return;
        }

        ArrayList<Obstacle> obstacles = parse_obstacles(parsedArgs);
        Map map = new Map(obstacles);

        ArrayList<String> startArgsList = parsedArgs.get("-start");
        if (startArgsList != null && !startArgsList.isEmpty()) {
            String startArg = stripParentheses(startArgsList.get(0));
            String targetArg = stripParentheses(parsedArgs.get("-target").get(0));
            Location start = Location.conversion(startArg);
            Location target = Location.conversion(targetArg);

            System.out.println(map.get_solved_map(start, target));
        } else {
            System.out.println("No value provided for -start argument.");
        }
    }

    private static HashMap<String, ArrayList<String>> val_conversion(String[] args) {
        if (args == null || args.length == 0) {
            return null;
        }

        HashMap<String, ArrayList<String>> hashmap_val = new HashMap<>();
        ArrayList<String> arg_vals = null;

        for (String arg : args) {
            if (arg.startsWith("-")) {
                arg_vals = new ArrayList<>();
                hashmap_val.put(arg, arg_vals);
                continue;
            }
            if (arg_vals != null) {
                arg_vals.add(arg);
            }
        }
        return hashmap_val;
    }

    private static String stripParentheses(String arg) {
        return arg.substring(1, arg.length() - 1);
    }

    public static ArrayList<Obstacle> parse_obstacles(HashMap<String, ArrayList<String>> parsedArgs) {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        for (ObstacleType type : ObstacleType.values()) {
            String key = "-" + type.get_argument_name();
            ArrayList<String> args = parsedArgs.get(key);
            if (args == null) {
                continue;
            }
            for (String arg : args) {
                String cleanedArg = stripParentheses(arg);
                Obstacle obstacle = switch (type) {
                    case GUARD -> Guard.conversion(cleanedArg);
                    case FENCE -> Fence.conversion(cleanedArg);
                    case SENSOR -> Sensor.conversion(cleanedArg);
                    case CAMERA -> Camera.conversion(cleanedArg);
                };
                obstacles.add(obstacle);
            }
        }
        return obstacles;
    }
}
