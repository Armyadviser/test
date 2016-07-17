package storm_falcon.lambdatest.pattern.order;

/**
 * Created by Storm_Falcon on 2016/7/3.
 *
 */
public class Main {

	public static void main(String[] args) {
		Editor editor = new Editor() {
			@Override
			public void save() {
				System.out.println("save");
			}

			@Override
			public void open() {
				System.out.println("open");
			}

			@Override
			public void close() {
				System.out.println("close");
			}
		};

	    Macro macro = new Macro();
		macro.record(new Open(editor));
		macro.record(new Save(editor));
		macro.record(new Close(editor));
		macro.run();

		//改用Lambda表达式
		macro.record(editor::open);
		macro.record(editor::save);
		macro.record(editor::close);
		macro.run();
		//可以省掉Open、Save、Close这些类
	}
}
