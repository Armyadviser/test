package storm_falcon.fields;

import com.portal.pcm.TStampField;

/**
 * Specific Field subclasses. This subclasses of <code>Field</code>
 * is used with the FList class to specifiy which field is being
 * accessed, and its type.  The type information is used to provide
 * compile time type checking.  These classes are auto generated.
 * @version 1.0 Wed Nov 28 13:54:46 CST 2007
 * @author Autogenerated
 */


public class CpFldFirstT extends TStampField {

	/**
	 * Constructs an instance of <code>CpFldFirstT</code>
	 */
	public CpFldFirstT() { super(10043, 8); }

	/**
	 * Returns an instance of <code>CpFldFirstT</code>
	 * @return An instance of <code>CpFldFirstT</code>
	 */
	public static synchronized CpFldFirstT getInst() { 
		if( me == null ) me = new CpFldFirstT();
		return me;
	}
	private static CpFldFirstT me;
}
