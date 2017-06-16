package storm_falcon.demo.concurrent.simulation.restaurant;

/**
 * @author gewp
 */
public class Food {
    private Course course;

    public Food(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return course + "";
    }
}
