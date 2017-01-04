package storm_falcon.swing.mathpic;

public class Pic1 implements ColorGenerator {

	public char R(int i, int j) {
	    return (char) (_sq(Math.cos(Math.atan2(j-512, i-512)/2))*255);
	}

	public char G(int i, int j) {
	    return (char) (_sq(Math.cos(Math.atan2(j-512, i-512)/2-2*Math.acos(-1)/3))*255);
	}

	public char B(int i, int j) {
	    return (char) (_sq(Math.cos(Math.atan2(j-512, i-512)/2+2*Math.acos(-1)/3))*255);
	}
	
}
