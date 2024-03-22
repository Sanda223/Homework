import java.util.ArrayList;/** Imports the hashmap and arraylist classes */
import java.util.HashMap;

class Main{
    public static void main(String[] args){ /** Making the hashmap named hashmap_val
                                                by calling a function val_conversions */
        HashMap<String, ArrayList<String>> hashmap_val = val_conversion(args);

        System.out.println(hashmap_val);/** Function to print hashmap_val */
    }

    private static HashMap<String, ArrayList<String>> val_conversion(String[] args)
    {                                      /** Creating and empty hashmap and an empty array list */
        HashMap<String, ArrayList<String>> hashmap_val = new HashMap<>();
        ArrayList<String> arg_vals = null;

        for (String arg : args){   /** the for loop is used for iterating through the command line arguments*/

            if (arg.startsWith("-")){    /** if it starts with a "-" then it adds the value to the array */
                arg_vals = new ArrayList<>();
                hashmap_val.put(arg, arg_vals); /** then it adds the arguments and the argument values to the hashmap */
                continue;
            }
            if (arg_vals != null){ /** If the argument values are not null it adds it to the hashmap */
                arg_vals.add(arg);
            }
        }
        return hashmap_val;
    }
}

