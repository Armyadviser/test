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


public class CpFldSubStateFlag extends EnumField {

	/**
	 * Constructs an instance of <code>CpFldSubStateFlag</code>
	 */
	public CpFldSubStateFlag() { super(10049, 3); }

	/**
	 * Returns an instance of <code>CpFldSubStateFlag</code>
	 * @return An instance of <code>CpFldSubStateFlag</code>
	 */
	public static synchronized CpFldSubStateFlag getInst() { 
		if( me == null ) me = new CpFldSubStateFlag();
		return me;
	}
	private static CpFldSubStateFlag me;
}