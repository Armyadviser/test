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


public class CpFldCardBatchCode extends StrField {

	/**
	 * Constructs an instance of <code>CpFldCardBatchCode</code>
	 */
	public CpFldCardBatchCode() { super(10092, 5); }

	/**
	 * Returns an instance of <code>CpFldCardBatchCode</code>
	 * @return An instance of <code>CpFldCardBatchCode</code>
	 */
	public static synchronized CpFldCardBatchCode getInst() { 
		if( me == null ) me = new CpFldCardBatchCode();
		return me;
	}
	private static CpFldCardBatchCode me;
}
