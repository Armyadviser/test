package storm_falcon.lambdatest.pattern.order;

/**
 * Created by Storm_Falcon on 2016/7/3.
 * 打开操作的代理
 */
public class Open implements Action {

	private final Editor editor;

	public Open(Editor editor) {
		this.editor = editor;
	}

	@Override
	public void perform() {
		editor.open();
	}
}
