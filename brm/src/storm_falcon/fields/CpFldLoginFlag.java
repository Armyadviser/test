package storm_falcon.fields;

import com.portal.pcm.EnumField;

/**
 * Specific Field subclasses. This subclasses of <code>Field</code>
 * is used with the FList class to specifiy which field is being
 * accessed, and its type.  The type information is used to provide
 * compile time type checking.  These classes are auto generated.
 * @version 1.0 Wed Nov 28 13:54:46 CST 2007
 * @author Autogenerated
 */


public class CpFldLoginFlag extends EnumField {

	/**
	 * Constructs an instance of <code>CpFldLoginFlag</code>
	 */
	public CpFldLoginFlag() { super(10039, 3); }

	/**
	 * Returns an instance of <code>CpFldLoginFlag</code>
	 * @return An instance of <code>CpFldLoginFlag</code>
	 */
	public static synchronized CpFldLoginFlag getInst() { 
		if( me == null ) me = new CpFldLoginFlag();
		return me;
	}
	private static CpFldLoginFlag me;
}
