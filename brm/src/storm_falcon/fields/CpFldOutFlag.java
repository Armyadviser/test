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


public class CpFldOutFlag extends EnumField {

	/**
	 * Constructs an instance of <code>CpFldOutFlag</code>
	 */
	public CpFldOutFlag() { super(10046, 3); }

	/**
	 * Returns an instance of <code>CpFldOutFlag</code>
	 * @return An instance of <code>CpFldOutFlag</code>
	 */
	public static synchronized CpFldOutFlag getInst() { 
		if( me == null ) me = new CpFldOutFlag();
		return me;
	}
	private static CpFldOutFlag me;
}
