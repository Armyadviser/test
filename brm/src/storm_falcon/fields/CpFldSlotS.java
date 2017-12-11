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


public class CpFldSlotS extends StrField {

	/**
	 * Constructs an instance of <code>CpFldSlotS</code>
	 */
	public CpFldSlotS() { super(10106, 5); }

	/**
	 * Returns an instance of <code>CpFldSlotS</code>
	 * @return An instance of <code>CpFldSlotS</code>
	 */
	public static synchronized CpFldSlotS getInst() { 
		if( me == null ) me = new CpFldSlotS();
		return me;
	}
	private static CpFldSlotS me;
}