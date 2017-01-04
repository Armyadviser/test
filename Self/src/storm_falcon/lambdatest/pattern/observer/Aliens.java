package storm_falcon.lambdatest.pattern.observer;

/**
 * Created by Storm_Falcon on 2016/7/4.
 * 外星人观察到人类登陆地球
 */
public class Aliens implements LandingObserver {
    @Override
    public void observeLanding(String name) {
        if (name.contains("Apollo")) {
            System.out.println("They are distracted, lets invade earth!");
        }
    }
}
