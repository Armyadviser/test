package storm_falcon.lambdatest.pattern.order;

/**
 * Created by Storm_Falcon on 2016/7/3.
 * 保存操作的代理
 */
public class Save implements Action {

	private final Editor editor;

	public Save(Editor editor) {
		this.editor = editor;
	}

	@Override
	public void perform() {
		editor.save();
	}
}
