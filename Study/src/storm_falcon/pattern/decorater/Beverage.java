package storm_falcon.pattern.decorater;

public abstract class Beverage {
    protected String description = "Unknown";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
