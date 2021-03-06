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


public class CpFldVciS extends StrField {

	/**
	 * Constructs an instance of <code>CpFldVciS</code>
	 */
	public CpFldVciS() { super(10104, 5); }

	/**
	 * Returns an instance of <code>CpFldVciS</code>
	 * @return An instance of <code>CpFldVciS</code>
	 */
	public static synchronized CpFldVciS getInst() { 
		if( me == null ) me = new CpFldVciS();
		return me;
	}
	private static CpFldVciS me;
}
