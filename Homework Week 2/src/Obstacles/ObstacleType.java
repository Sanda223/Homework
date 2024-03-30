package Obstacles;


public enum ObstacleType {
    GUARD("g", 'g');

    private final String argumentName;
    private final char symbol;


    ObstacleType(String argumentName, char symbol) {
        this.argumentName = argumentName;
        this.symbol = symbol;
    }


    public String get_argument_name() {
        return argumentName;
    }

    public char get_symbol() {
        return symbol;
    }
}