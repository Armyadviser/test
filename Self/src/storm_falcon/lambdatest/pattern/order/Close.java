package storm_falcon.lambdatest.pattern.order;

/**
 * Created by Storm_Falcon on 2016/7/3.
 *
 */
public class Close implements Action {

	private final Editor editor;

	public Close(Editor editor) {
		this.editor = editor;
	}

	@Override
	public void perform() {
		editor.open();
	}
}
