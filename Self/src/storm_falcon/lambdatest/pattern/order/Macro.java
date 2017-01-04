package storm_falcon.lambdatest.pattern.order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Storm_Falcon on 2016/7/3.
 * 宏，定义了一系列的Action操作
 */
@SuppressWarnings({"FieldCanBeLocal", "MismatchedQueryAndUpdateOfCollection"})
public class Macro {

	private final List<Action> actions;

	private final List<Runnable> runnableList;

	public Macro() {
		actions = new ArrayList<>();
		runnableList = new ArrayList<>();
	}

	public void record(Action action) {
		actions.add(action);
	}

//	public void record(Runnable action) {
//		runnableList.add(action);
//	}

	public void run() {
		actions.forEach(Action::perform);
	}
}