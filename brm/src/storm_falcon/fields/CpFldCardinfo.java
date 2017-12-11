package storm_falcon.fields;

import com.portal.pcm.SubStructField;

/**
 * Specific Field subclasses. This subclasses of <code>Field</code>
 * is used with the FList class to specifiy which field is being
 * accessed, and its type.  The type information is used to provide
 * compile time type checking.  These classes are auto generated.
 * @version 1.0 Wed Nov 28 13:54:46 CST 2007
 * @author Autogenerated
 */


public class CpFldCardinfo extends SubStructField {

	/**
	 * Constructs an instance of <code>CpFldCardinfo</code>
	 */
	public CpFldCardinfo() { super(10038, 10); }

	/**
	 * Returns an instance of <code>CpFldCardinfo</code>
	 * @return An instance of <code>CpFldCardinfo</code>
	 */
	public static synchronized CpFldCardinfo getInst() { 
		if( me == null ) me = new CpFldCardinfo();
		return me;
	}
	private static CpFldCardinfo me;
}
