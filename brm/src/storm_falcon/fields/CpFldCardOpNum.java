package storm_falcon.fields;

import com.portal.pcm.StrField;

/**
 * Specific Field subclasses. This subclasses of <code>Field</code>
 * is used with the FList class to specifiy which field is being
 * accessed, and its type.  The type information is used to provide
 * compile time type checking.  These classes are auto generated.
 * @version 1.0 Wed Nov 28 13:54:46 CST 2007
 * @author Autogenerated
 */


public class CpFldCardOpNum extends StrField {

	/**
	 * Constructs an instance of <code>CpFldCardOpNum</code>
	 */
	public CpFldCardOpNum() { super(10119, 5); }

	/**
	 * Returns an instance of <code>CpFldCardOpNum</code>
	 * @return An instance of <code>CpFldCardOpNum</code>
	 */
	public static synchronized CpFldCardOpNum getInst() { 
		if( me == null ) me = new CpFldCardOpNum();
		return me;
	}
	private static CpFldCardOpNum me;
}
