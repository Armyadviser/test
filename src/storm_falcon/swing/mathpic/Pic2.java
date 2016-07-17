package storm_falcon.swing.mathpic;

public class Pic2 implements ColorGenerator {
	
	@Override
	public char R(int i, int j) {
		double x=0, y=0;
		int k;
		for (k=0;k++<256;) {
			double a=x*x-y*y+(i-768.0)/512;
			y=2*x*y+(j-512.0)/512;
			x=a;
			if (x*x+y*y>4) {
				break;
			}
		}
		return (char) (Math.log(k)*47);
	}

	@Override
	public char G(int i, int j) {
		double x=0, y=0;
		int k;
		for (k=0;k++<256;) {
			double a=x*x-y*y+(i-768.0)/512;
			y=2*x*y+(j-512.0)/512;
			x=a;
			if (x*x+y*y>4) {
				break;
			}
		}
		return (char) (Math.log(k)*47);
	}

	@Override
	public char B(int i, int j) {
		double x=0, y=0;
		int k;
		for (k=0;k++<256;) {
			double a=x*x-y*y+(i-768.0)/512;
			y=2*x*y+(j-512.0)/512;
			x=a;
			if (x*x+y*y>4) {
				break;
			}
		}
		return (char) (128-Math.log(k)*23);
	}
}
