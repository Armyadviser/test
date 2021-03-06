package storm_falcon.fields;

import com.portal.pcm.ArrayField;

/**
 * Specific Field subclasses. This subclasses of <code>Field</code>
 * is used with the FList class to specifiy which field is being
 * accessed, and its type.  The type information is used to provide
 * compile time type checking.  These classes are auto generated.
 * @version 1.0 Wed Nov 28 13:54:46 CST 2007
 * @author Autogenerated
 */


public class CpFldValidDnis extends ArrayField {

	/**
	 * Constructs an instance of <code>CpFldValidDnis</code>
	 */
	public CpFldValidDnis() { super(10029, 9); }

	/**
	 * Returns an instance of <code>CpFldValidDnis</code>
	 * @return An instance of <code>CpFldValidDnis</code>
	 */
	public static synchronized CpFldValidDnis getInst() { 
		if( me == null ) me = new CpFldValidDnis();
		return me;
	}
	private static CpFldValidDnis me;
}
