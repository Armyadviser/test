package storm_falcon.lambdatest.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Storm_Falcon on 2016/7/4.
 * 被观察者，持有一组观察者实例
 */
public class Moon {
    private final List<LandingObserver> observers = new ArrayList<>();

    public void land(String name) {
        for (LandingObserver observer : observers) {
            observer.observeLanding(name);
        }
    }

    public void startSpying(LandingObserver observer) {
        observers.add(observer);
    }
}
