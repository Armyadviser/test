package storm_falcon.swing.mathpic;

public interface ColorGenerator {
	
	default double _sq(double x) {
		return x * x;
	}

	public char R(int i, int j);

	public char G(int i, int j);

	public char B(int i, int j);
}
