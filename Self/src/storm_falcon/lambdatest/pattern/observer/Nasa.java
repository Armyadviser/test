package storm_falcon.lambdatest.pattern.observer;

/**
 * Created by Storm_Falcon on 2016/7/4.
 * NASA也能观察到有人登陆月球
 */
public class Nasa implements LandingObserver {
    @Override
    public void observeLanding(String name) {
        if (name.contains("Apollo")) {
            System.out.println("We made it!");
        }
    }
}
