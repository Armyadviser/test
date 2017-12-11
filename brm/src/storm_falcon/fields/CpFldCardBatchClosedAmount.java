package storm_falcon.fields;

import com.portal.pcm.IntField;

/**
 * Specific Field subclasses. This subclasses of <code>Field</code>
 * is used with the FList class to specifiy which field is being
 * accessed, and its type.  The type information is used to provide
 * compile time type checking.  These classes are auto generated.
 * @version 1.0 Wed Nov 28 13:54:46 CST 2007
 * @author Autogenerated
 */


public class CpFldCardBatchClosedAmount extends IntField {

	/**
	 * Constructs an instance of <code>CpFldCardBatchClosedAmount</code>
	 */
	public CpFldCardBatchClosedAmount() { super(10102, 1); }

	/**
	 * Returns an instance of <code>CpFldCardBatchClosedAmount</code>
	 * @return An instance of <code>CpFldCardBatchClosedAmount</code>
	 */
	public static synchronized CpFldCardBatchClosedAmount getInst() { 
		if( me == null ) me = new CpFldCardBatchClosedAmount();
		return me;
	}
	private static CpFldCardBatchClosedAmount me;
}
