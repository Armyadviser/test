package storm_falcon.swing.mathpic;

public interface ColorGenerator {
	
	default double _sq(double x) {
		return x * x;
	}

	char R(int i, int j);

	char G(int i, int j);

	char B(int i, int j);
}
